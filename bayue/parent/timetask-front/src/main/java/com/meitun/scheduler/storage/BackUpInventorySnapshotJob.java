package com.meitun.scheduler.storage;

import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.storage.exception.StorageServiceException;
import com.meitun.storage.service.SnapshotService;

public class BackUpInventorySnapshotJob {

	private Logger logger =LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SnapshotService snapshotService;

	
	private static final String RUN_WORK_KEY = "TASK_BACKUPINVENTORYSNAPSHOTJOB";//每个定时任务一个key

	@Autowired
	JedisCacheUtil jedisCacheUtil;

	public void backUpInventorySnapShot() throws IllegalAccessException, InvocationTargetException {
		boolean lock = jedisCacheUtil.lock(RUN_WORK_KEY);// 获得锁
		if(!lock){
			return;
		}
		try {
			logger.info("开始生成库存快照>>>>>>>>>>>");
			snapshotService.backUpInventorySnapShot();
			logger.info("库存快照生成完毕<<<<<<<<<<<<<");
		} catch (StorageServiceException e) {
			logger.error("库存快照备份失败 {}",e.getMessage());
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
