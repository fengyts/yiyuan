package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.scheduler.JobConstant;

public abstract class AbstractJobRunnable implements Runnable,DisposableBean{
	private static final Logger LOG = LoggerFactory.getLogger(AbstractJobRunnable.class);
	@Autowired
	private JobConstant jobConstant;
	
	@Override
	public void run(){
		if(jobConstant.isRunnableByJobPreName(getFixed())){
			try{
				execute();
			}catch(Exception e){
				LOG.error(getFixed(),e);	
			}finally{
				jobConstant.cleanSign(getFixed());
			}
		}
	}
	
	public abstract void execute();
	
	public abstract String getFixed();
	
	public void destroy() throws Exception{
		jobConstant.cleanSign(getFixed());
	}
}
