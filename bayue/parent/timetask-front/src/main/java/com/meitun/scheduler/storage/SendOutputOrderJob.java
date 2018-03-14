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
import com.meitun.storage.domain.OutputOrderDO;
import com.meitun.storage.domain.OutputOrderDetailDO;
import com.meitun.storage.exception.DAOException;
import com.meitun.storage.service.OutputOrderSendService;
import com.meitun.storage.service.OutputOrderService;

/**
 * 重发失败出库指令，最多次数根据 SEND_ORDER_WMS_MAX_FAIL_TIMES 进行配置
 * @author beck
 *
 */
public class SendOutputOrderJob {

	private Logger logger = LoggerFactory.getLogger(SendOutputOrderJob.class);
	
	@Autowired
	private OutputOrderSendService outputOrderSendService;
	
	@Autowired
	private OutputOrderService outputOrderService;
	
	private static final String RUN_WORK_KEY = "TASK_SENDOUTPUTORDERJOB";//每个定时任务一个key

	@Autowired
	JedisCacheUtil jedisCacheUtil;
	
	public void sendOutputOrderService(){
		boolean lock = jedisCacheUtil.lock(RUN_WORK_KEY);// 获得锁
		if(!lock){
			return;
		}
		try {
			int maxTime = StorageConstant.SEND_ORDER_WMS_MAX_FAIL_TIMES.intValue();
			int limitSize = 20;
			logger.info("查询需要重新发送出库指令的订单（采购退货/销售订单）>>>>>>>>>>>>>>>>>");
			List<OutputOrderDO> orderDOs = outputOrderService.selectFailOutputOrder(maxTime,limitSize);
			if(CollectionUtils.isEmpty(orderDOs)){
				logger.info("没有查询到需要重新发送出库指令的订单（采购退货/销售订单）<<<<<<<<<<<<<<<");
				return;
			}
			logger.info("查询需要重新发送出库指令的订单（采购退货/销售订单） size:{}",orderDOs.size());
			
			for (OutputOrderDO outputOrderDO : orderDOs) {
				List<OutputOrderDetailDO> orderDetailDOs = outputOrderService.selectOuputorderDetailByOrderId(outputOrderDO.getId());
				if(CollectionUtils.isEmpty(orderDetailDOs)){
					continue;
				}
				logger.info("重新发送出库指令,单号：{}",outputOrderDO.getOrderCode());
				outputOrderSendService.sendOutputOrder(outputOrderDO, orderDetailDOs);
			}
			logger.info("重新发送出库指令的订单（采购退货/销售订单）完成<<<<<<<<<<<<<<<<<<");
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
