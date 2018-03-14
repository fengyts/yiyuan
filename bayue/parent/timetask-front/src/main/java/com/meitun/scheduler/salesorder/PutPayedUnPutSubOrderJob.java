package com.meitun.scheduler.salesorder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.domain.SubOrderDO;
import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.scheduler.JobConstant;

@Component
public class PutPayedUnPutSubOrderJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(PutPayedUnPutSubOrderJob.class);

    private static final String CURRENT_JOB_PREFIXED = "putpayedunputsuborderjob";

    @Autowired
    private SalesOrderRemoteService salesOrderRemoteService;

    @Override
    public void execute() {
        LOG.info("推送支付后未推送的订单到仓库begin......");
        String[] unPutOrder = JobConstant.getInstance().getUnPutPayedOrder();
        if (null != unPutOrder) {
            for (String code : unPutOrder) {
                try {
                    SubOrderDO subOrderDO = salesOrderRemoteService.findSubOrderByCode(code);
                    salesOrderRemoteService.putWareHouseShippingBySubOrder(subOrderDO);
                } catch (Exception e) {
                    LOG.error("{} 推送失败 {}", code, e);
                }
            }
        }
        LOG.info("推送支付后未推送的订单到仓库end......");
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }
}
