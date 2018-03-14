package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.jumei.JumeiOrderService;

/**
 * 
 * 外部订单拆分补偿
 *
 */
@Component("outOrderSplitCompensate")
public class OutOrderSplitCompensate  extends AbstractJobRunnable {
	
	private static final Logger log = LoggerFactory.getLogger(OutOrderSplitCompensate.class);
	
	private static final String CURRENT_JOB_PREFIXED = "outOrderSplitCompensate";
	
	@Autowired
	private JumeiOrderService jumeiOrderService;

	@Override
	public void execute() {
		log.info("start to run outOrderSplitCompensate");
		try {
			jumeiOrderService.orderSplitCompensate();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		log.info("end to run outOrderSplitCompensate");
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}
	
	

}
