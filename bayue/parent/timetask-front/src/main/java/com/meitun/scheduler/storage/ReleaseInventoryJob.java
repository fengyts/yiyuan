package com.meitun.scheduler.storage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.salesorder.constants.OrderConstant.ORDER_STATUS;
import com.meitun.salesorder.dto.remote.SubOrderDTO;
import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.storage.domain.InventoryOccupyDO;
import com.meitun.storage.enums.InventoryOccupyState;
import com.meitun.storage.service.InventoryOperService;
import com.meitun.storage.service.InventoryQueryService;
import com.meitun.storage.util.Page;

/**
 * 释放已完成订单占用的库存
 * <pre>
 * 	适用场景，订单状态为待收货、已收货、已取消、订单不存在时，库存没有释放的情况
 * 	此时将减去占用库存信息
 * </pre>
 * @author beck
 *
 */
@Component("releaseInventoryJob")
public class ReleaseInventoryJob {

	private Logger logger = LoggerFactory.getLogger(SendInputOrderJob.class);
	
	@Autowired
	private InventoryOperService inventoryOperService;
	
	@Autowired
	private InventoryQueryService inventoryQueryService;
	
	@Autowired
	private SalesOrderRemoteService salesOrderRemoteService;
	
	/** 15日之前的占用 */
	@Value("#{config['quartz.releaseInventoryJob.occupyDay']}")
	int OCCUPY_DAY = 5;
	
	private static final String RUN_WORK_KEY = "TASK_ReleaseInventoryJob";//每个定时任务一个key
	
	@Autowired
	JedisCacheUtil jedisCacheUtil;
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.add(Calendar.DATE, -5);
		System.out.println(calendar.getTime());
	}
	public void releaseInventory(){
		boolean lock = jedisCacheUtil.lock(RUN_WORK_KEY);// 获得锁
		if(!lock){
			return;
		}
		try {
			List<String> orderCodeList = null;
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, -OCCUPY_DAY);
			int pageNo = 0;
			logger.info("开始处理占用库存时间超过{}天的记录>>>>>>>>>>>>>",OCCUPY_DAY);
			while(true){
				//获取需要处理的冻结库存信息
				pageNo++;
				Page<InventoryOccupyDO> page = new Page<InventoryOccupyDO>(pageNo,100);
				page = inventoryQueryService.selectInventoryOccupyByPage(page,calendar.getTime(),InventoryOccupyState.normal);
				if(page!=null&&CollectionUtils.isNotEmpty(page.getList())){
					orderCodeList = new ArrayList<String>();
					List<InventoryOccupyDO> inventoryOccupyDOs = page.getList();
					for (InventoryOccupyDO inventoryOccupyDO : inventoryOccupyDOs) {
						String orderCode = inventoryOccupyDO.getOrderNo();
						if(orderCodeList.contains(orderCode)){
							continue;
						}
						orderCodeList.add(orderCode);
					}
					logger.info("获得占用库存超过{}天的订单记录：{} 条",new Object[]{OCCUPY_DAY, orderCodeList.size()});
					
					//获取这些订单在订单系统中状态
					List<SubOrderDTO> subOrderDTOs = salesOrderRemoteService.findSubOrderDTOListBySubCodeList(orderCodeList);
					logger.info("查询订单系统，获得记录：{} 条",(subOrderDTOs!=null?subOrderDTOs.size():0));
					List<String> releaseOrderCodes = new ArrayList<String>();
					List<String> cancelOrderCodes = new ArrayList<String>();
					List<String> allSubOrderCodes = new ArrayList<String>();
					for (SubOrderDTO subOrderDTO : subOrderDTOs) {
						String orderCode = subOrderDTO.getCode();
						allSubOrderCodes.add(orderCode);
						int status = subOrderDTO.getStatus().intValue();
						//已发货（待收货）、已收货、取消的订单
						if( status == ORDER_STATUS.RECEIPT.getCode().intValue()||
								status == ORDER_STATUS.FINISH.getCode().intValue()){
							releaseOrderCodes.add(orderCode);
						}
						if(status == ORDER_STATUS.CANCEL.getCode().intValue()){
							cancelOrderCodes.add(orderCode);
						}
							
					}
					//筛选出有冻结记录，但是没有订单信息的记录
					List<String> noOrderInfoCodes = new ArrayList<String>();
					for (String code : orderCodeList) {
						if(allSubOrderCodes.contains(code)){
							continue;
						}
						noOrderInfoCodes.add(code);
					}
					
					//释放库存，即删除占用库存记录，减少冻结数量，减少现货库存数量
					if(CollectionUtils.isNotEmpty(cancelOrderCodes)){
						logger.info("释放占用库存超过{}天且已取消的订单库存：{} 条",OCCUPY_DAY,cancelOrderCodes.size());
						inventoryOperService.releaseInventoryWithState(cancelOrderCodes,InventoryOccupyState.cancel);
					}
					
					//释放库存，即删除占用库存记录，减少冻结数量，减少现货库存数量
					if(CollectionUtils.isNotEmpty(noOrderInfoCodes)){
						logger.info("释放占用库存超过{}天且不在订单系统中的订单库存：{} 条",OCCUPY_DAY,noOrderInfoCodes.size());
						inventoryOperService.releaseInventoryWithState(noOrderInfoCodes,InventoryOccupyState.error);
					}
					
					//释放库存，即删除占用库存记录，减少冻结数量，减少现货库存数量
					if(CollectionUtils.isNotEmpty(releaseOrderCodes)){
						logger.info("释放占用库存超过{}天且已发货、已完成的订单库存：{} 条",OCCUPY_DAY,releaseOrderCodes.size());
						inventoryOperService.releaseInventoryWithState(releaseOrderCodes,InventoryOccupyState.complete);
					}
				}else{
					break;
				}
				
			}
		} catch (Exception e) {
			logger.error("释放超期占用的库存：{}",e);
		} finally {
			if (lock) {
				jedisCacheUtil.unLock(RUN_WORK_KEY);// 释放锁
			}
		}
		logger.info("结束处理占用库存时间超过{}天的记录<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",OCCUPY_DAY);
	}
	

	public void close() {
		jedisCacheUtil.unLock(RUN_WORK_KEY);// 释放锁
	}

}
