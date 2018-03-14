package com.meitun.scheduler.finance;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.redis.util.JedisCacheUtil;

/**
 * job模板类
 * 
 * @author huchao
 *         
 */
public abstract class AbstractFinanceJob {
    
    /**
     * 锁失效时间
     */
    public final static int LOCK_EXPIRE_SECONDS = 30 * 60;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractFinanceJob.class);
    
    private static final String JOB_KEY_PREFIX = "TASK_FINANCE_JOB_";// 财务定时任务key前缀
    
    private static final Long TIMESTAMP = System.currentTimeMillis();
    
    @Autowired
    protected JedisCacheUtil jedisCacheUtil;
    
    public void execute() {
        long before = System.currentTimeMillis();
        if (!getLock()) {
            LOGGER.warn("任务{}正在运行中, 本次任务终止", getKey());
            return;
        }
        LOGGER.info("任务{}开始....", getKey());
        try {
            doJob();
        } catch (Exception e) {
            LOGGER.error(MessageFormat.format("任务{0}运行时发生错误", getKey()), e);
        } finally {
            long after = System.currentTimeMillis();
            LOGGER.info("任务{}结束....耗时：{}ms.....", getKey(), after - before);
            jedisCacheUtil.unLock(getKey());// 释放锁
        }
    }
    
    protected String getKey() {
        if (getJobKey() == null) {
            return JOB_KEY_PREFIX + TIMESTAMP;
        }
        return JOB_KEY_PREFIX.concat(getJobKey());
    }
    
    protected abstract void doJob() throws Exception;
    
    protected abstract String getJobKey();
    
    protected boolean getLock() {
        return jedisCacheUtil.lock(getKey(), LOCK_EXPIRE_SECONDS);
    }
    
}
