package com.meitun.scheduler.finance;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.finance.service.CommissionGatherService;

public class CommissionGatherJob extends AbstractFinanceJob{

private static final Logger LOGGER = LoggerFactory.getLogger(CommissionGatherJob.class);
    
    private static final String JOB_KEY = "COMMISSION_GATHER";// 每个定时任务一个key
    
    @Autowired
    private CommissionGatherService commissionGatherService;
	
	@Override
	protected void doJob() {
		 LOGGER.info(" 开始统计订单佣金信息 ");
		 
		 try {
			//本月初统计上一个月的订单佣金信息
			 Date date = getFirstDayInLastMonth(new Date());
			 commissionGatherService.createCommissionGathers(date);
		} catch (Exception e) {
			 LOGGER.info(" 统计订单佣金信息失败 ", e);
		}
		 LOGGER.info(" 统计订单佣金信息结束 ");
	}

	/**
	 * 得到上一个月的第一天
	 * @param date
	 * @return
	 */
	public static Date getFirstDayInLastMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1, 00, 00, 00);
        return calendar.getTime();
    }
	
	@Override
	protected String getJobKey() {
		return JOB_KEY;
	}

}
