package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.order.wwd.WwdPushOrderService;

/**
 * 
 * 推送wwd订单补偿定时任务
 *
 */
@Component
public class PushWwdOrderCompensateJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(PushWwdOrderCompensateJob.class);

    private static final String CURRENT_JOB_PREFIXED = "pushWwdOrderCompensateJob";
    
    @Autowired
    private WwdPushOrderService wwdPushOrderService;

    @Override
    public void execute() {

        LOG.info("[推送wwd定时任务]启动<<<");
        try {
        	wwdPushOrderService.pushCompensate();
        } catch(Exception e) {
        	LOG.error(e.getMessage(),e);
        }
        LOG.info("[推送wwd定时任务]完成>>>");
        
    }

	@Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
