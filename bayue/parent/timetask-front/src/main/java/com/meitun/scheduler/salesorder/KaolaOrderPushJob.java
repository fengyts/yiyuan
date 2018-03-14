package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.kaola.KaolaService;

/**
 * 考拉订单推送
 *
 */
@Component
public class KaolaOrderPushJob  extends AbstractJobRunnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KaolaOrderPushJob.class);
	
	private String kaolaPushOrderKey = "kaolaOrderPushJob";
	
	@Autowired
	private KaolaService kaolaService;

	@Override
	public void execute() {
		LOGGER.info("KaolaOrderPushJob start");
		try {
			kaolaService.pushOrderToKaola();
		} catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		LOGGER.info("KaolaOrderPushJob end");
	}

	@Override
	public String getFixed() {
		return kaolaPushOrderKey;
	}

}
