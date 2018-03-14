package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.wwd.WwdBackService;

/**
 * 
 * 推送wwd退货单补偿定时任务
 *
 */
@Component
public class PushWwdRejectInfoCompensateJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(PushWwdRejectInfoCompensateJob.class);

    private static final String CURRENT_JOB_PREFIXED = "pushWwdRejectInfoCompensateJob";
    
    @Autowired
    private WwdBackService wwdBackService;

    @Override
    public void execute() {

        LOG.info("[推送wwd退货补偿定时任务]启动<<<");
        try {
        	wwdBackService.pushCompensate();
        } catch(Exception e) {
        	LOG.error(e.getMessage(),e);
        }
        LOG.info("[推送wwd退货补偿定时任务]完成>>>");
        
    }

	@Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
