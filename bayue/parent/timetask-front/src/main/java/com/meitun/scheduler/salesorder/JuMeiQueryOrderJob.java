package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.jumei.JumeiOrderService;
import com.meitun.redis.util.JedisCacheUtil;

@Component
public class JuMeiQueryOrderJob extends AbstractJobRunnable {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JuMeiQueryOrderJob.class);

	@Autowired
	JumeiOrderService jumeiOrderService;

	@Autowired
	JedisCacheUtil jedisCacheUtil;

	private static final String CURRENT_JOB_PREFIXED = "queryorder.jumei";

	// 每个定时任务一个key
	private static final String JOB_KEY = "QUERY_JUMEI_ORDER_CHECK_PAY";

	@Override
	public void execute() {
		boolean lock = jedisCacheUtil.lock(JOB_KEY);
		if (!lock) {
			return;
		}
		
		try {
			jumeiOrderService.delivery();
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
