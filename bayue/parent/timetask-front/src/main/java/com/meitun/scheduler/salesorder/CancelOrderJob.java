package com.meitun.scheduler.salesorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.domain.SalesOrderDO;
import com.meitun.salesorder.domain.SubOrderDO;
import com.meitun.salesorder.service.remote.OrderCancelRemoteService;
import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.salesorder.utilities.SalesOrderUtils;
import com.meitun.scheduler.JobConstant;
import com.meitun.user.domain.UserDO;
import com.meitun.user.service.UserService;

/**
 * {取消订单任务} <br>
 * Create on : 2015年12月10日 上午10:51:11<br>
 *
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class CancelOrderJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(CancelOrderJob.class);

    private static final String CURRENT_JOB_PREFIXED = "cancelorder";

    private static final int UN_PAYED_EXPIRED_MINUTE_DEFAULT = 60;

    @Autowired
    private OrderCancelRemoteService orderCancelRemoteService;

    @Autowired
    private SalesOrderRemoteService salesOrderRemoteService;

    @Autowired
    private UserService userService;

    @Override
    public void execute() {
        Integer expired = JobConstant.getCancelUnpayMinute();
        if (null == expired) {
            expired = UN_PAYED_EXPIRED_MINUTE_DEFAULT;
        }

        // 获取60分钟之前 待支付 父订单
        // status = 2 AND create_time &lt;= date_sub(now(), interval #{value}+1 minute) ORDER BY create_time ASC Limit
        // 0,100
        List<SalesOrderDO> salesOrders = salesOrderRemoteService.querySalesOrderByUnPayIsExpired(expired);

        // status = 2 AND type in (3,4,5) AND create_time &lt;= date_sub(now(), interval #{value}+1 minute) AND
        // create_time &gt; date_sub(now(), interval 2 day) ORDER BY create_time ASC Limit 0,1000
        List<SubOrderDO> subOrders = salesOrderRemoteService.querySubOrderBySeaOrderUnPayIsExpired(expired);

        Map<Long, String> userMap = new HashMap<Long, String>();
        if (CollectionUtils.isNotEmpty(salesOrders)) {
            List<Long> memberIds = new ArrayList<Long>();
            for (SalesOrderDO salesOrderDO : salesOrders) {
                memberIds.add(salesOrderDO.getMemberId());
            }

            List<UserDO> users = userService.selectByIds(memberIds);
            if (CollectionUtils.isNotEmpty(users)) {
                for (UserDO user : users) {
                    userMap.put(user.getId(), user.getNickName());
                }
            }

            for (SalesOrderDO salesOrder : salesOrders) {
                Boolean isparent = Boolean.TRUE;
                if (CollectionUtils.isNotEmpty(subOrders)) {
                    for (SubOrderDO subOrder : subOrders) {
                        if (subOrder.getOrderCode().equals(salesOrder.getCode())) {
                            isparent = Boolean.FALSE;
                            break;
                        }
                    }
                }
                if (isparent) {
                    try {
                        Long memberId = salesOrder.getMemberId();
                        orderCancelRemoteService.cancelOrder(salesOrder.getCode(), memberId, userMap.get(memberId));
                    } catch (Exception e) {
                        LOG.error("parent orderCode = {} 取消出错 {}", new Object[] { salesOrder.getCode(), e });
                    }
                }
            }
        }

        if (CollectionUtils.isNotEmpty(subOrders)) {
            for (SubOrderDO subOrder : subOrders) {
                if (SalesOrderUtils.isSeaOrder(subOrder)) {
                    try {
                        Long memberId = subOrder.getMemberId();
                        orderCancelRemoteService.cancelOrder(subOrder.getCode(), memberId, userMap.get(memberId));
                    } catch (Exception e) {
                        LOG.error("sub orderCode = {} 取消出错 {}", new Object[] { subOrder.getCode(), e });
                    }
                }
            }
        }

    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
