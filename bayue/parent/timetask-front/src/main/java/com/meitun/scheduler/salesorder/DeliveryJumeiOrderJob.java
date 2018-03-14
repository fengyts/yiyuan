package com.meitun.scheduler.salesorder;

import org.springframework.stereotype.Component;

/**
 * {反查聚美订单更新美囤订单状态} <br>
 * Create on : 2015年10月28日 下午11:03:07<br>
 * 
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class DeliveryJumeiOrderJob extends AbstractJobRunnable {

    private static final String CURRENT_JOB_PREFIXED = "deliveryJumeiOrder";

    // @Autowired
    // private JumeiOrderService jumeiOrderService;

    @Override
    public void execute() {
        // jumeiOrderService.delivery();
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
