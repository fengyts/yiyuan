package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.salesorder.service.remote.OrderAuditRemoteService;

/**
 * {订单审核} <br>
 * Create on : 2015年11月24日 下午11:50:40<br>
 *
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class AuditOrderJob extends AbstractJobRunnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditOrderJob.class);

    private static final String CURRENT_JOB_PREFIXED = "auditOrder";

    private static final String AUDIT_ORDER_JOB_KEY = "TASK_AUDIT_ORDER_JOB_KEY_";

    @Autowired
    private JedisCacheUtil jedisCacheUtil;

    @Autowired
    private OrderAuditRemoteService orderAuditRemoteService;

    @Override
    public void execute() {
        boolean lock = jedisCacheUtil.lock(AUDIT_ORDER_JOB_KEY);
        if (!lock) {
            LOGGER.info("can't obtain a lock.{}", AUDIT_ORDER_JOB_KEY);
            return;
        }

        try {
            auditOrder();
        } catch (Exception e) {
            LOGGER.error("Error audit order. {}", e);
        } finally {
            jedisCacheUtil.unLock(AUDIT_ORDER_JOB_KEY);
        }
    }

    /**
     * {海淘订单自动审核}.
     */
    private void auditOrder() {
        LOGGER.info("run auditOrder start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        orderAuditRemoteService.auditOrder();
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
