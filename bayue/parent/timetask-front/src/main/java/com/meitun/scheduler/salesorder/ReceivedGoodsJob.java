package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.scheduler.JobConstant;

@Component
public class ReceivedGoodsJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(ReceivedGoodsJob.class);

    private static final String CURRENT_JOB_PREFIXED = "receivedgoods";

    private static final Integer RECEIVED_DAYS = 15;

    private static final Integer SEA_RECEIVED_DAYS = 45; // 海外直邮需要45天自动完成

    private static final Integer PAGE_SIZE = 500;

    @Autowired
    private SalesOrderRemoteService salesOrderRemoteService;

    @Override
    public void execute() {
        LOG.info("ReceivedGoodsJob begin running");

        while (true) {
            try {
                int result = salesOrderRemoteService.finishOrderInBatch(PAGE_SIZE, getReceivedDays(),
                    SEA_RECEIVED_DAYS);

                if (result < PAGE_SIZE) {
                    break;
                }
            } catch (Exception e) {
                LOG.error("订单自动完成异常", e);
                break;
            }
        }
        LOG.info("ReceivedGoodsJob end run");
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

    private Integer getReceivedDays() {
        Integer days = JobConstant.getReceivedDays();
        if (null == days) {
            return RECEIVED_DAYS;
        }
        return days;
    }
}
