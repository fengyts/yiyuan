package com.meitun.scheduler.salesorder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.salesorder.constants.OrderConstant.ORDER_STATUS;
import com.meitun.salesorder.domain.TrialOrderDO;
import com.meitun.salesorder.dto.remote.TrialOrderQO;
import com.meitun.salesorder.service.remote.BabytreeTrialOrderCancelRemoteService;
import com.meitun.salesorder.service.remote.TrialOrderCancelRemoteService;
import com.meitun.salesorder.service.remote.TrialOrderRemoteService;
import com.meitun.salesorder.util.Page;
import com.meitun.scheduler.JobConstant;
import com.meitun.user.domain.UserDO;
import com.meitun.user.service.UserService;

/**
 * {帮宝适未获取物流信息，取消订单 0 0 0,4,8,12,18,22 * * ?} <br>
 * Create on : 2015年10月28日 下午12:00:16<br>
 *
 * @author admin<br>
 * @version timetask-front v0.0.1
 */
@Component
public class CancelNoSendTrialOrderJob extends AbstractJobRunnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(CancelNoSendTrialOrderJob.class);

    private static final String CURRENT_JOB_PREFIXED = "cancelnosendtrialorder";

    private static final int UN_SENDED_EXPIRED_DAY_DEFAULT = 14;

    private static final String TRIAL_ORDER_JOB_KEY = "TASK_TRIAL_ORDER_JOB_KEY_";

    /** 宝宝树取消试用订单的apiKey */
    @Value("#{config['babytree.trialorder.apiKey']}")
    public String apiKey;

    /** 宝宝树取消试用订单的sig */
    @Value("#{config['babytree.trialorder.apiSecret']}")
    public String sig;

    @Autowired
    private TrialOrderRemoteService trialOrderRemoteService;

    @Autowired
    private TrialOrderCancelRemoteService trialOrderCancelRemoteService;

    @Autowired
    private BabytreeTrialOrderCancelRemoteService babytreeTrialOrderCancelRemoteService;

    @Autowired
    private UserService userService;

    @Autowired
    private JedisCacheUtil jedisCacheUtil;

    @Override
    public void execute() {
        boolean lock = jedisCacheUtil.lock(TRIAL_ORDER_JOB_KEY);
        if (!lock) {
            LOGGER.debug("can't obtain a lock.{}", TRIAL_ORDER_JOB_KEY);
            return;
        }
        try {
            cancelNoSendOrder();
        } catch (Exception e) {
            LOGGER.error("Error cancel order for trial order {}", e);
        } finally {
            jedisCacheUtil.unLock(TRIAL_ORDER_JOB_KEY);
        }
    }

    private void cancelNoSendOrder() {
        Integer expired = JobConstant.getTrialCancelUnpayDay();
        if (null == expired) {
            expired = UN_SENDED_EXPIRED_DAY_DEFAULT;
        }
        LOGGER.info("More than {} days of non delivery Pampers tiral orders automatically canceled,start<<<", expired);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -UN_SENDED_EXPIRED_DAY_DEFAULT);
        Date endTime = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -100);
        Date startTime = calendar.getTime();

        TrialOrderQO query = new TrialOrderQO();
        query.setStartTime(startTime);
        query.setEndTime(endTime);
        query.setStatus(ORDER_STATUS.DELIVERY.code);
        query.setPageSize(100);

        Page<TrialOrderDO> trialOrderList = null;
        int pageNo = 1;
        do {
            query.setStartPage(pageNo++);

            trialOrderList = trialOrderRemoteService.queryTrialOrderList(query);
            List<TrialOrderDO> listOrder = null;
            if (trialOrderList != null) {
                listOrder = trialOrderList.getList();
            }

            if (CollectionUtils.isNotEmpty(listOrder)) {
                List<Long> memberIds = new ArrayList<Long>();
                for (TrialOrderDO trialOrderDO : listOrder) {
                    memberIds.add(trialOrderDO.getMemberId());
                }

                Map<Long, String> userMap = new HashMap<Long, String>();
                List<UserDO> users = userService.selectByIds(memberIds);
                if (CollectionUtils.isNotEmpty(users)) {
                    for (UserDO user : users) {
                        userMap.put(user.getId(), user.getNickName());
                    }
                }

                for (TrialOrderDO trialOrder : listOrder) {
                    // 美囤试用订单取消
                    Long memberId = trialOrder.getMemberId();
                    trialOrderCancelRemoteService.cancelTrialOrderByBackend(trialOrder.getCode(), memberId, userMap.get(memberId));
                }

            } else {
                LOGGER.info("There is no more than {} days Pampers trial order to delivery,query:{}", expired, ToStringBuilder.reflectionToString(query));
            }
        } while (trialOrderList != null && CollectionUtils.isNotEmpty(trialOrderList.getList()));

        LOGGER.info("More than {} days of non delivery Pampers tiral orders automatically canceled,end>>>", expired);

    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
