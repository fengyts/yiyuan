package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.kaola.KaolaService;

@Component
public class KaolaOrderDeliveryJob extends AbstractJobRunnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KaolaOrderDeliveryJob.class);
	
	private String kaolaOrderDeliveryJob = "kaolaOrderDeliveryJob";
	
	
	@Autowired
	private KaolaService kaolaService;

	@Override
	public void execute() {
		LOGGER.info("KaolaOrderDeliveryJob start");
		try {
			kaolaService.kaolaOrderDelivery();
		} catch(Exception e) {
			LOGGER.error(e.getMessage(),e);
		}
		LOGGER.info("KaolaOrderDeliveryJob end");
	}

	@Override
	public String getFixed() {
		return kaolaOrderDeliveryJob;
	}

}
