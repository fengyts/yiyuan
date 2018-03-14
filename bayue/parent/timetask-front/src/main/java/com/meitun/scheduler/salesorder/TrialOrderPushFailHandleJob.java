package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.remote.TrialOrderRemoteService;

/**
 * 
 * 帮宝适试用订单推送失败处理job
 */
@Component
public class TrialOrderPushFailHandleJob extends AbstractJobRunnable {
	
	private static final Logger LOG = LoggerFactory.getLogger(TrialOrderPushFailHandleJob.class);
	
	private static final String CURRENT_JOB_PREFIXED = "trialOrderPushFailHandleJob";
	
	@Autowired
    private TrialOrderRemoteService trialOrderRemoteService;
	
	@Override
    public void execute() {

		LOG.info("[帮宝适试用]推送失败订单重新推送job启动<<<");
        try {
        	trialOrderRemoteService.pushFailOrders();
        } catch(Exception e) {
        	LOG.error(e.getMessage(),e);
        }
        LOG.info("[帮宝适试用]推送失败订单重新推送job完成>>>");
        
    }

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
