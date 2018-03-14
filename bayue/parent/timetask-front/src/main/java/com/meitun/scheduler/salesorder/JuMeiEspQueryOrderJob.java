package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.jumei.JumeiEspOrderService;
import com.meitun.redis.util.JedisCacheUtil;


@Component
public class JuMeiEspQueryOrderJob extends AbstractJobRunnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JuMeiEspQueryOrderJob.class);

	@Autowired
	JumeiEspOrderService jumeiEspOrderService;
	
	@Autowired
	JedisCacheUtil jedisCacheUtil;
	
	private static final String CURRENT_JOB_PREFIXED = "queryorder.jumei.esp";
	
	//每个定时任务一个key
	private static final String JOB_KEY = "QUERY_JUMEI_ESP_ORDER_CHECK_PAY";

	@Override
	public void execute() {
		// TODO Auto-generated method stub
				boolean lock = jedisCacheUtil.lock(JOB_KEY);
				
				if(!lock){
					return;
				}
				
				try {
					//聚美商户订单查询 ESP
					jumeiEspOrderService.delivery();
						
				} catch (Exception e) {
					LOGGER.error(e.getMessage(),e);
				}finally{
					jedisCacheUtil.unLock(JOB_KEY);
				}
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}
}
