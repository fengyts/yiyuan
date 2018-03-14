package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.remote.TrialOrderRemoteService;

/**
 * {帮宝适试用订单发货 0 0 0,4,8,12,18,22 * * ?} <br>
 * Create on : 2016年4月28日 下午3:11:44<br>
 * 
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class SendTrialOrderJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(SendTrialOrderJob.class);

    private static final String CURRENT_JOB_PREFIXED = "sendtrialorderjob";

    /**
     * <code>DELIVERY_DAY</code> - {最近55天}.
     */
    @Value("#{config['trialorder.deliveryday']}")
    public Integer DELIVERY_DAY;

    @Autowired
    private TrialOrderRemoteService trialOrderRemoteService;

    @Override
    public void execute() {

        LOG.info("[帮宝适试用]订单发货job启动<<<");
        try {
        	trialOrderRemoteService.execDelivery(DELIVERY_DAY);
        } catch(Exception e) {
            LOG.info("trialOrderRemoteService.execDelivery timeout.");
            // LOG.error(e.getMessage(),e);
        }
        LOG.info("[帮宝适试用]订单发货job完成>>>");
        
    }

	@Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
