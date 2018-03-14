package com.meitun.scheduler.finance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.finance.service.GenerateBillService;

public class CreateBillFileJob extends AbstractFinanceJob{
	
	public static final Logger LOGGER = LoggerFactory.getLogger(CreateBillFileJob.class);
	private static final String JOB_KEY = "CREATE_BILL_FILE";// 每个定时任务一个key
	 
	@Autowired
	private GenerateBillService generateBillService;
	
	
	@Override
	protected void doJob() throws Exception {
		generateBillService.generateAllSallerBill();
	}

	@Override
	protected String getJobKey() {
		return JOB_KEY;
	}

	
}
