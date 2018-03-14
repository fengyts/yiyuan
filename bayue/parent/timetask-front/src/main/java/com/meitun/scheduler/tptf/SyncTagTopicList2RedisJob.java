package com.meitun.scheduler.tptf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.meitun.scheduler.salesorder.AbstractJobRunnable;
import com.meitun.tptf.service.remote.TopicRemoteService;

/**
 * 同步北京tag-->topicList数据
 * @author hehaifei
 *
 */
@Component
public class SyncTagTopicList2RedisJob extends AbstractJobRunnable{
	private static final Logger LOG = LoggerFactory.getLogger(SyncTagTopicList2RedisJob.class);
	private static final String CURRENT_JOB_PREFIXED = "synctagtopiclist2redisjob";
	
	@Autowired
	private TopicRemoteService topicRemoteService;

	@Override
	@Transactional
	public void execute(){
		LOG.info("同步北京所有tag--->topicList job启动....");
		int count = topicRemoteService.syncTagTopicListFromBigData();
	
		if(count > 0){
			LOG.info("同步北京所有tag--->topicList job完成, 更新数量={}",count);
		}else{
			LOG.info("同步北京所有tag--->topicList 失败");
		}
		
	}
	
	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
