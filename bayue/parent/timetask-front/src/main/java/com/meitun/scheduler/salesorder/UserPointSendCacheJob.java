package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.remote.SalesPointRemoteService;

/**
 * 
 * 用户当日已使用积分补偿机制
 *
 */
@Component
public class UserPointSendCacheJob  extends AbstractJobRunnable {
	
	@Autowired
	private SalesPointRemoteService salesPointRemoteService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserPointSendCacheJob.class);
	private static final String CURRENT_JOB_PREFIXED = "userPointSendCacheJob";

	@Override
	public void execute() {
		LOGGER.info("userPointSendCacheJob start");
		try {
			salesPointRemoteService.userPointToCache();
        } catch(Exception e) {
        	LOGGER.error(e.getMessage(),e);
        }
		LOGGER.info("userPointSendCacheJob end");
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
