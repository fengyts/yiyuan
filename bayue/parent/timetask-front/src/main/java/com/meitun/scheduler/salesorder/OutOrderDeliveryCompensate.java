package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.jumei.JumeiOrderService;

/**
 * 
 * 外部订单发货发货补偿
 *
 */
@Component("outOrderDeliveryCompensate")
public class OutOrderDeliveryCompensate  extends AbstractJobRunnable {
	
	private static final Logger log = LoggerFactory.getLogger(OutOrderDeliveryCompensate.class);
	
	private static final String CURRENT_JOB_PREFIXED = "outOrderDeliveryCompensate";
	
	@Autowired
	private JumeiOrderService jumeiOrderService;

	@Override
	public void execute() {
		log.info("start to run outOrderDeliveryCompensate");
		try {
			jumeiOrderService.orderDeliveryCompensate();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		log.info("end to run outOrderDeliveryCompensate");
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}
	
	

}
