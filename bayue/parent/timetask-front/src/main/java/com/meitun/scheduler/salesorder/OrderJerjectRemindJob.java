package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.redis.util.JedisCacheUtil;
import com.meitun.salesorder.service.RejectInfoService;

/**
 * {活动结束或长期活动到结算周期后，并且订单发货后结算} <br>
 * Create on : 2015年10月28日 下午1:31:47<br>
 *
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class OrderJerjectRemindJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(OrderJerjectRemindJob.class);

    private static final String CURRENT_JOB_PREFIXED = "order.reject.remind";
	
	//每个定时任务一个key
	private static final String JOB_KEY = "ORDER_REJECT_REMIND";

    @Autowired
    private RejectInfoService rejectInfoService;

    @Autowired
	JedisCacheUtil jedisCacheUtil;

    /**
     * 退货申请提交后7天未上传寄回单号，短信提醒
     */
    @Override
    public void execute() {

        LOG.info("OrderJerjectRemindJob start>>>");

		// TODO Auto-generated method stub
				boolean lock = jedisCacheUtil.lock(JOB_KEY);
				
				if(!lock){
					return;
				}
				
				try {
					rejectInfoService.RejectRemind();
						
				} catch (Exception e) {
					LOG.error(e.getMessage(),e);
				}finally{
					jedisCacheUtil.unLock(JOB_KEY);
				}
	
       
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
