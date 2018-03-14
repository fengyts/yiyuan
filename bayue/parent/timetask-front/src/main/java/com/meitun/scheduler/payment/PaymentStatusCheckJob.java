package com.meitun.scheduler.payment;

import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.payment.service.PaymentService;
import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.scheduler.SynchronizedJob;
import com.meitun.storage.exception.StorageServiceException;

/**
 * 检查订单是否已完成支付
 * 
 * @author luxiaoming
 *
 */
public class PaymentStatusCheckJob  extends SynchronizedJob{
	
	@Autowired
	PaymentService paymentService;
	@Autowired
	JedisCacheUtil jedisCacheUtil;
	
	private static final String JOB_KEY = "TASK_PAYMENT_CHECK_PAY";//每个定时任务一个key
	
	@Override
	protected void jobImpl() {
		boolean lock = jedisCacheUtil.lock(JOB_KEY);// 获得锁
		if(!lock){
			return;
		}
		try {
			logger.info("检查订单是否已完成支付 任务开始...");
			paymentService.checkPaymentStatus();
			logger.info("检查订单是否已完成支付　任务完成");
		} catch (StorageServiceException e) {
			logger.error("库存快照备份失败 {}",e.getMessage());
		} finally {
			if (lock) {
				jedisCacheUtil.unLock(JOB_KEY);// 释放锁
			}
		}
	
	}
}
