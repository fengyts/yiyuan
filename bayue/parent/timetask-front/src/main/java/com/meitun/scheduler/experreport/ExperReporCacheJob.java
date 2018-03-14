/**
 * NewHeight.com Inc.
 * Copyright (c) 2008-2010 All Rights Reserved.
 */
package com.meitun.scheduler.experreport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.comment.service.ComExperReportService;

/**
 * <pre>
 * 体验报告定时缓存任务
 * </pre>
 *
 * @author ruanh
 * @version $Id: ExperReporCacheJob.java, v 0.1 2015年11月28日 上午10:23:53 ruanh Exp $
 */
public class ExperReporCacheJob {

	private Logger logger = LoggerFactory.getLogger(ExperReporCacheJob.class);
	@Autowired
	private ComExperReportService comExperReportService;
	/**
	 * <pre>
	 * 查找关联关系表并进行遍历，从宝宝树接口拉取数据进行缓存
	 * </pre>
	 *
	 */
	public void doSetReporCache(){
		logger.info("体验报告拉取宝宝树接口数据任务开始.............");
		comExperReportService.processTimeJob();
	}
	public void doSyncExportReport(){
		logger.info("同步美屯体验报告到宝宝树任务开始.............");
		comExperReportService.doSyncExportReport();
	}
}