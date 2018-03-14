package com.meitun.scheduler.storage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.promotion.domain.result.TopicInfo;
import com.meitun.promotion.enums.TopicStatus;
import com.meitun.promotion.service.TopicService;
import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.storage.domain.InventoryDistributeDO;
import com.meitun.storage.domain.result.ResultMessage;
import com.meitun.storage.enums.App;
import com.meitun.storage.enums.InventoryType;
import com.meitun.storage.service.InventoryDistributeService;
import com.meitun.storage.service.InventoryOperService;
import com.meitun.storage.service.InventoryQueryService;

/**
 * 释放无效的活动占用的库存
 * <pre>
 * 	适用场景，活动无效但没有归还库存
 * 	此时将释放活动分配库存
 * </pre>
 *
 */
@Component("releaseDistInventoryJob")
public class ReleaseDistInventoryJob {

	private Logger logger = LoggerFactory.getLogger(ReleaseDistInventoryJob.class);
	
	@Autowired
	private TopicService topicService;
	
	@Autowired
	private InventoryOperService inventoryOperService;
	
	@Autowired
	private InventoryQueryService inventoryQueryService;
	
	@Autowired
	private InventoryDistributeService inventoryDistributeService;
	
	
	/** 2天之前的专题分配 */
	private static final int CREATE_DAY = 2;
	
	private static final String RUN_WORK_KEY = "TASK_ReleaseDistInventoryJob";//每个定时任务一个key
	
	@Autowired
	JedisCacheUtil jedisCacheUtil;
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());
		calendar.add(Calendar.DATE, -100);
		System.out.println(calendar.getTime());
	}
	public void releaseDistInventory(){
		boolean lock = jedisCacheUtil.lock(RUN_WORK_KEY);// 获得锁
		if(!lock){
			return;
		}
		int releaseNum = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -CREATE_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date queryTime = calendar.getTime();
		try {
			logger.info("开始处理{}之前的无效且未还库的专题活动分配库存>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",queryTime);
			int startPage = 1;
			int pageSize = 500;
			InventoryDistributeDO distInventoryQO = new InventoryDistributeDO();
			distInventoryQO.setType(InventoryType.lock.getCode());
			distInventoryQO.setCreateTime(queryTime);
			distInventoryQO.setStartPage(startPage);
			distInventoryQO.setPageSize(pageSize);
			List<String> bizIds = inventoryDistributeService.selectBizId4TimeTask(distInventoryQO);
			while (CollectionUtils.isNotEmpty(bizIds)) {
				List<Long> topicIds = new ArrayList<Long>();// 活动Ids
				for (String bizId : bizIds) {
					topicIds.add(Long.valueOf(bizId));
				}
				
				List<Long> allTopics = new ArrayList<Long>();
				List<Long> failTopics = new ArrayList<Long>();
				List<Long> delTopics = new ArrayList<Long>();
				List<TopicInfo> topicInfoList = topicService.queryTopicList(topicIds);
				
				for (Long topicId : topicIds) {
					TopicInfo topic = null;
					for (TopicInfo topicInfo : topicInfoList) {
						if (topicInfo.getTopicId() == topicId.longValue()) {
							topic = topicInfo;
							break;
						}
					}
					
					ResultMessage result = null;
					if (topic == null) {
						// 已分配了库存，但活动已被物理删除
						result = inventoryOperService.backRequestInventory(App.PROMOTION, String.valueOf(topicId));
						delTopics.add(topicId);
					} else {
						// 活动还在，判断活动状态
						if (topic.getStatus()==TopicStatus.EDITING.ordinal() || topic.getStatus()==TopicStatus.CANCELED.ordinal() ||
								topic.getStatus()==TopicStatus.REFUSED.ordinal() || topic.getStatus()==TopicStatus.STOP.ordinal()) {
							result = inventoryOperService.backRequestInventory(App.PROMOTION, String.valueOf(topic.getTopicId()));
						}
					}
					
					if (result != null) {
						allTopics.add(topicId);
						if (result.getResult() == ResultMessage.FAIL) {
							failTopics.add(topicId);
						}
					}
				}
				
				logger.info("共处理了"+allTopics.size()+"条无效且未还库的专题库存 专题ID：{} 已删除专题ID：{} 失败专题ID："+StringUtils.join(failTopics,","),StringUtils.join(allTopics,","),StringUtils.join(delTopics,","));
				
				releaseNum += allTopics.size();
				startPage++;
				distInventoryQO.setStartPage(startPage);
				bizIds = inventoryDistributeService.selectBizId4TimeTask(distInventoryQO);
			}
		} catch (Exception e) {
			logger.error("释放无效且未还库的专题活动分配库存：{}",e);
		} finally {
			if (lock) {
				jedisCacheUtil.unLock(RUN_WORK_KEY);// 释放锁
			}
		}
		logger.info("结束处理{}之前的无效且未还库专题活动分配库存，共处理了{}条活动记录>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",queryTime,releaseNum);
	}
	

	public void close() {
		jedisCacheUtil.unLock(RUN_WORK_KEY);// 释放锁
	}

}
