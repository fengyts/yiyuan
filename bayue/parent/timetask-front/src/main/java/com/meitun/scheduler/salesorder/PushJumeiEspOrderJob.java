package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.jumei.JumeiEspOrderService;
import com.meitun.redis.util.JedisCacheUtil;

/**
 * {推送已审核通过的聚美ESP订单} <br>
 * Create on : 2016年4月13日 下午12:32:10<br>
 *
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class PushJumeiEspOrderJob extends AbstractJobRunnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushJumeiEspOrderJob.class);

    private static final String CURRENT_JOB_PREFIXED = "pushJumeiEspOrder";

    private static final String JOB_KEY = "PUSH_JUMEI_ESP_ORDER";

    @Autowired
    private JumeiEspOrderService jumeiEspOrderService;

    @Autowired
    private JedisCacheUtil jedisCacheUtil;

    @Override
    public void execute() {
        LOGGER.info("PushJumeiOrderJob start>>>");

        boolean lock = jedisCacheUtil.lock(JOB_KEY);
        if (!lock) {
            return;
        }

        try {
            jumeiEspOrderService.push();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            jedisCacheUtil.unLock(JOB_KEY);
        }

        LOGGER.info("PushJumeiOrderJob end>>>");
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
