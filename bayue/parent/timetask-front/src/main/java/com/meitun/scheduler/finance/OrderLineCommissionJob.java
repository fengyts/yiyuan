/**
 * 
 */
package com.meitun.scheduler.finance;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.meitun.finance.domain.commission.OffsetDO;
import com.meitun.finance.domain.commission.RefundDO;
import com.meitun.finance.service.OffsetService;
import com.meitun.finance.service.OrderLineCommissionService;
import com.meitun.finance.service.RefundService;
import com.meitun.finance.util.TimeUtils;

/**
 * 生成销售订单的佣金、退货订单佣金，补偿单数据
 * 
 * @author huchao
 */
public class OrderLineCommissionJob extends AbstractFinanceJob {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderLineCommissionJob.class);
    
    private static final String JOB_KEY = "ORDERLINE_COMMISSION";// 每个定时任务一个key
    
    @Autowired
    private OrderLineCommissionService orderLineCommissionService;
    
    @Autowired
    private RefundService refundService;
    
    
    @Autowired
    private OffsetService offsetService;
    
    public void doJob() {
            Calendar calendar = TimeUtils.getCalendar();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
            String time = TimeUtils.format(TimeUtils.PATTERN_YEAR_DATE, calendar.getTime());
        
            LOGGER.info("当前处理的日期是：{}", time);
            Date date = TimeUtils.parse(TimeUtils.PATTERN_YEAR_DATE, time);
            
			try {
				LOGGER.info("清理{}订单数据.....", date );
				orderLineCommissionService.removeCommissionsByDeliveryTime(date);
				LOGGER.info("开始抽取{}并保存订单佣金数据.....", date);
				orderLineCommissionService.fetchAndSaveOrderLineByDeliveryTime(date);
			} catch (Exception e) {
				LOGGER.error("保存订单数据出错：", e);
			}
           
			try {
				LOGGER.info("查询退单数据...");
				List<RefundDO> refundList = refundService.fetchRefundsByCompleteTime(date);
				// 保存退款数据
	            LOGGER.info("保存退单数据...");
	            refundService.batchSaveRefunds(refundList);
			} catch (Exception e) {
				LOGGER.error("保存退单数据出错：", e);
			}
            
            try {
				LOGGER.info("查询补偿单数据...");
				List<OffsetDO> offsetList = offsetService.fetchOffsetsByPaymentTime(date);
				// 保存补偿单数据
				offsetService.batchSaveOffsets(offsetList);
			} catch (Exception e) {
				LOGGER.error("保存补偿单数据出错：", e);
			}
    }
    
    @Override
    protected String getJobKey() {
        return JOB_KEY;
    }
}
