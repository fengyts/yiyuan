package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.hitaoorder.service.fisher.FisherECMService;

/**
 * 费舍尔订单推送接口
 * 
 * @author hekanglei
 * @date 2015年9月11日下午4:59:19
 */
@Component
public class PushFisherOrderInfoJob extends AbstractJobRunnable {
	private static final Logger logger = LoggerFactory
			.getLogger(PushFisherOrderInfoJob.class);
	private static final String CURRENT_JOB_PREFIXED = "pushFisherOrder";
	@Autowired
	private FisherECMService fisherECMService;

	/*
	 * 执行批量推送订单至费舍尔 (non-Javadoc)
	 * 
	 * @see com.meitun.scheduler.salesorder.AbstractJobRunnable#execute()
	 */
	@Override
	public void execute() {
		logger.debug("费舍尔定时推送任务begin");
		fisherECMService.push();
		logger.debug("费舍尔定时推送任务end");
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
