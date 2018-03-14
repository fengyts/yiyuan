package com.meitun.scheduler.salesorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.domain.TrialOrderDO;
import com.meitun.salesorder.service.remote.BabytreeTrialOrderCancelRemoteService;
import com.meitun.salesorder.service.remote.TrialOrderCancelRemoteService;
import com.meitun.salesorder.service.remote.TrialOrderRemoteService;
import com.meitun.scheduler.JobConstant;
import com.meitun.user.domain.UserDO;
import com.meitun.user.service.UserService;

/**
 * 取消试用订单任务调度
 *
 * @author liuzy
 */
@Component
public class CancelTrialOrderJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(CancelTrialOrderJob.class);

    private static final String CURRENT_JOB_PREFIXED = "canceltrialorder";

    private static final int UN_PAYED_EXPIRED_MINUTE_DEFAULT = 60;

    /** 宝宝树取消试用订单的apiKey */
    @Value("#{config['babytree.trialorder.apiKey']}")
    public String apiKey;

    /** 宝宝树取消试用订单的sig */
    @Value("#{config['babytree.trialorder.apiSecret']}")
    public String sig;

    @Autowired
    private TrialOrderCancelRemoteService trialOrderCancelRemoteService;

    @Autowired
    private TrialOrderRemoteService trialOrderRemoteService;

    @Autowired
    private BabytreeTrialOrderCancelRemoteService babytreeTrialOrderCancelRemoteService;

    @Autowired
    private UserService userService;

    @Override
    public void execute() {
        Integer expired = JobConstant.getTrialCancelUnpayMinute();
        if (null == expired) {
            expired = UN_PAYED_EXPIRED_MINUTE_DEFAULT;
        }
        LOG.info("未支付订单[帮宝适试用]超过{}分钟[取消]job启动<<<", expired);

        List<TrialOrderDO> trialOrderList = trialOrderRemoteService.queryTrialOrderByUnPayIsExpired(expired);
        if (CollectionUtils.isNotEmpty(trialOrderList)) {

            List<Long> memberIds = new ArrayList<Long>();
            // 根据试用订单list组装memberId list
            for (TrialOrderDO trialOrderDO : trialOrderList) {
                memberIds.add(trialOrderDO.getMemberId());
            }

            // 根据memberId list查询 user list
            Map<Long, String> userMap = new HashMap<Long, String>();
            List<UserDO> users = userService.selectByIds(memberIds);
            if (CollectionUtils.isNotEmpty(users)) {
                for (UserDO user : users) {
                    userMap.put(user.getId(), user.getNickName());
                }
            }

            // 未支付的订单，60分钟后自动取消
            if (CollectionUtils.isNotEmpty(trialOrderList)) {
                for (TrialOrderDO trialOrder : trialOrderList) {
                    try {
                        Long memberId = trialOrder.getMemberId();
                        trialOrderCancelRemoteService.cancelTrialOrderByBackendWithoutNotify(trialOrder.getCode(), memberId, userMap.get(memberId));
                    } catch (Exception e) {
                        LOG.error("trialOrderCode = {} 取消出错 {}", new Object[] { trialOrder.getCode(), e });
                    }
                }
            }

        } else {
            LOG.info("不存在过期未支付订单[帮宝适试用]");
        }

        LOG.info("未支付订单[帮宝适试用]超过{}分钟[取消]job完成>>>", expired);
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
