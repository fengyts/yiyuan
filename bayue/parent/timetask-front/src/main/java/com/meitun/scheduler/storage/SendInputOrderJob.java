package com.meitun.scheduler.storage;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.storage.constant.StorageConstant;
import com.meitun.storage.domain.InputOrderDO;
import com.meitun.storage.domain.InputOrderDetailDO;
import com.meitun.storage.exception.DAOException;
import com.meitun.storage.service.InputOrderService;

/**
 * 重发失败的入库指令，最多三次
 * @author beck
 *
 */
public class SendInputOrderJob {

	private Logger logger = LoggerFactory.getLogger(SendInputOrderJob.class);
	
	@Autowired
	private InputOrderService inputOrderService;
	
	private static final String RUN_WORK_KEY = "TASK_SENDINPUTORDERJOB";//每个定时任务一个key
	private static final String OPERATOT_USER_NAME = "BBT"; 	// 入库单操作人
	@Autowired
	JedisCacheUtil jedisCacheUtil;
	
	public void sendInputOrderService(){
		boolean lock = jedisCacheUtil.lock(RUN_WORK_KEY);// 获得锁
		if(!lock){
			return;
		}
		try {
			int maxTime = StorageConstant.SEND_ORDER_WMS_MAX_FAIL_TIMES.intValue();
			int limitSize = 20;
			logger.info("查询需要重新发送入库指令的订单（采购订单）>>>>>>>>>>");
			List<InputOrderDO> orderDOs = inputOrderService.selectFailInputOrder(maxTime,limitSize);
			if(CollectionUtils.isEmpty(orderDOs)){
				logger.info("没有查询到需要重新发送入库指令的订单（采购订单）<<<<<<<<<<<<<<<<");
				return;
			}
			logger.info("查询需要重新发送入库指令的订单（采购订单） size:{}",orderDOs.size());
		
			for (InputOrderDO inputOrderDO : orderDOs) {
				List<InputOrderDetailDO> orderDetailDOs = inputOrderService.selectOrderDetailByOrderId(inputOrderDO.getId());
				if(CollectionUtils.isEmpty(orderDetailDOs)){
					continue;
				}
				logger.info("重新发送入库指令,单号：{}",inputOrderDO.getOrderCode());
				inputOrderService.sendInputOrderToWms(inputOrderDO, orderDetailDOs, OPERATOT_USER_NAME);
			}
			logger.info("重新发送入库指令的订单（采购订单）完成<<<<<<<<<<<");
		} catch (RemoteException e) {
		} catch (MalformedURLException e) {
		} catch (DAOException e) {
		} finally {
			if (lock) {
				jedisCacheUtil.unLock(RUN_WORK_KEY);// 释放锁
			}
		}
	}

	public void close() {
		jedisCacheUtil.unLock(RUN_WORK_KEY);// 释放锁
	}

}
