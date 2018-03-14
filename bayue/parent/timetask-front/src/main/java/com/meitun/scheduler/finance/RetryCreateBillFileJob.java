package com.meitun.scheduler.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.finance.service.RetryGenerateBillService;

public class RetryCreateBillFileJob extends AbstractFinanceJob{

	private static final Logger LOGGER = LoggerFactory.getLogger(RetryCreateBillFileJob.class);
	private static final String JOB_KEY = "RETRY_CREATE_BILL_FILE";// 每个定时任务一个key
	
	@Autowired
	private RetryGenerateBillService retryGenerateBillService;
	
	@Override
	protected void doJob() throws Exception {
		LOGGER.info("开始重新生成账单任务");
		retryGenerateBillService.retryGenerateBill();
	}

	@Override
	protected String getJobKey() {
		return JOB_KEY;
	}

}
