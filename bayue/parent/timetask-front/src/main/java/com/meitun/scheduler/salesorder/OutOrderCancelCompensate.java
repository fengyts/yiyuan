package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.jumei.JumeiOrderService;

/**
 * 
 * 外部订单取消确认补偿
 *
 */
@Component("outOrderCancelCompensate")
public class OutOrderCancelCompensate  extends AbstractJobRunnable{
	
	private static final Logger log = LoggerFactory.getLogger(OutOrderCancelCompensate.class);
	private static final String CURRENT_JOB_PREFIXED = "outOrderCancelCompensate";
	
	@Autowired
	private JumeiOrderService jumeiOrderService;

	@Override
	public void execute() {
		log.info("start to run outOrderCancelCompensate");
		try {
			jumeiOrderService.orderCancelCompensate();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
		}
		log.info("end to run outOrderCancelCompensate");
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}
	
	

}
