package com.meitun.scheduler.payment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.payment.domain.PaymentInfoDO;
import com.meitun.payment.service.PaymentService;
import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.salesorder.constants.OrderConstant;
import com.meitun.salesorder.domain.SalesOrderDO;
import com.meitun.salesorder.domain.SubOrderDO;
import com.meitun.salesorder.service.local.PaymentMQBug;
import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.scheduler.SynchronizedJob;

/**
 * 检查订单是否已完成支付
 * 
 * @author luxiaoming
 *
 */
public class PaymentCompensateJob  extends SynchronizedJob{
	
	@Autowired
	PaymentService paymentService;
	@Autowired
	JedisCacheUtil jedisCacheUtil;
	@Autowired
	private PaymentMQBug salesOrderPaidCallback;
	@Autowired
    private SalesOrderRemoteService salesOrderRemoteService;
	
	private static final String JOB_KEY = "TASK_PAYMENT_COMPENSATE";//每个定时任务一个key
	
	@Override
	protected void jobImpl() {
		boolean lock = jedisCacheUtil.lock(JOB_KEY);// 获得锁
		if(!lock){
			return;
		}
		logger.info("订单状态补偿机制开始.....");
		List<PaymentInfoDO> list = paymentService.queryAfter5MinutePayed();
		
		
		try {
			for(PaymentInfoDO paymentInfo : list){
				String code = null;
				if(paymentInfo.getBizCode().startsWith(OrderConstant.DOCUMENT_TYPE.SO_ORDER.code.toString())){
					SalesOrderDO salesOrder = salesOrderRemoteService.findSalesOrderByCode(paymentInfo.getBizCode());
					if(OrderConstant.ORDER_STATUS.PAYMENT.code.intValue() == salesOrder.getStatus().intValue()){
						code = paymentInfo.getBizCode();
					}
				}
				else if(paymentInfo.getBizCode().startsWith(OrderConstant.DOCUMENT_TYPE.SO_SUB_ORDER.code.toString())){
					SubOrderDO subOrder = salesOrderRemoteService.findSubOrderByCode(paymentInfo.getBizCode());
					if(OrderConstant.ORDER_STATUS.PAYMENT.code.intValue() == subOrder.getStatus().intValue()){
						code = paymentInfo.getBizCode();
					}
				}
				if(code != null){
					logger.info("订单{}状态与支付状态不一致，通过补偿机制更改", code);
					salesOrderPaidCallback.fix(code);
				}
			}
		} catch (Exception e) {
			logger.error("订单状态补偿异常 {}",e.getMessage());
		} finally {
			if (lock) {
				jedisCacheUtil.unLock(JOB_KEY);// 释放锁
			}
		}
	
	}
}
