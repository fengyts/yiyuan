package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.remote.SalesPointRemoteService;

/**
 * 
 * 订单积分补偿定时任务
 *
 */
@Component
public class OrderPointPushSendJob  extends AbstractJobRunnable {
	
	@Autowired
	private SalesPointRemoteService salesPointRemoteService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderPointPushSendJob.class);
	private static final String CURRENT_JOB_PREFIXED = "orderPointPushSendJob";

	@Override
	public void execute() {
		LOGGER.info("orderPointPushSendJob start");
		try {
			salesPointRemoteService.sendPointByOrderPushLogFail();
        } catch(Exception e) {
        	LOGGER.error(e.getMessage(),e);
        }
		LOGGER.info("orderPointPushSendJob end");
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
