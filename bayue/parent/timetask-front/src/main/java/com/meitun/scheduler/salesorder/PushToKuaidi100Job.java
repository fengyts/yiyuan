/**
 * NewHeight.com Inc.
 * Copyright (c) 2007-2014 All Rights Reserved.
 */
package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.salesorder.service.local.Kuaidi100LocalService;

/**
 * {推送快递100平台JOB} <br>
 * Create on : 2016年5月3日 下午3:55:17<br>
 * 
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class PushToKuaidi100Job extends AbstractJobRunnable{

	private static final Logger LOGGER = LoggerFactory.getLogger(PushToKuaidi100Job.class);

	private static final String CURRENT_JOB_PREFIXED = "pushExpressToKuaidi100";
	
    private static final String JOB_KEY = "PUSH_TO_KUAIDI100_JOB";

    @Autowired
    private JedisCacheUtil jedisCacheUtil;

	@Autowired
	private Kuaidi100LocalService kuaidi100LocalService;
	
	@Override
	public void execute() {
        boolean lock = jedisCacheUtil.lock(JOB_KEY);
        if (!lock) {
            return;
        }

        long st = System.currentTimeMillis();
		try {
			kuaidi100LocalService.pushExpressToKuaidi100();
		} catch (Exception e) {
            LOGGER.info("kuaidi100LocalService.pushExpressToKuaidi100() timeout.");
            // LOGGER.error(e.getMessage(), e);
        } finally {
            jedisCacheUtil.unLock(JOB_KEY);
        }

        LOGGER.info("pushExpressToKuaidi100 use:{}ms", System.currentTimeMillis() - st);

	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
