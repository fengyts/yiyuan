//package com.meitun.scheduler.salesorder;
//
//import java.util.List;
//
//import org.apache.commons.collections.CollectionUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.meitun.finance.domain.settle.SettleOrderItemDO;
//import com.meitun.finance.service.settle.SettleOrderItemService;
//import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
//import com.meitun.scheduler.JobConstant;
//
///**
// * {活动结束或长期活动到结算周期后，并且订单发货后结算} <br>
// * Create on : 2015年10月28日 下午1:31:47<br>
// *
// * @author Administrator<br>
// * @version timetask-front v0.0.1
// */
//@Component
//public class OrderItemSettleJob extends AbstractJobRunnable {
//
//    private static final Logger LOG = LoggerFactory.getLogger(OrderItemSettleJob.class);
//
//    private static final String CURRENT_JOB_PREFIXED = "orderitemsettle";
//
//    private static final int BATCH_NUMBER_DEFAULT = 100;
//
//    @Autowired
//    private SettleOrderItemService settleOrderItemService;
//
//    @Autowired
//    private SalesOrderRemoteService salesOrderRemoteService;
//
//    /**
//     * 1.查询已发货的订单、未推送的商品项（暂时把全部匹配订单推送到结算系统） 2.根据订单商品项上的活动ID查询活动是否要进行结算 3.组装结算数据 4.推送到结算平台
//     */
//    @Override
//    public void execute() {
//
//        LOG.info("OrderItemSettleJob start>>>");
//
//        Integer batchNumber = JobConstant.getBatchNumber();
//        if (null == batchNumber) {
//            batchNumber = BATCH_NUMBER_DEFAULT;
//        }
//
//        // 查询待收货的子订单数量
//        Long sum = salesOrderRemoteService.queryOrderItemCountBySettle();
//
//        LOG.info("salesOrderRemoteService.queryOrderItemCountBySettle sum:", sum);
//
//        Integer page = 0;
//        if (sum % batchNumber == 0) {
//            page = sum.intValue() / batchNumber;
//        } else {
//            page = sum.intValue() / batchNumber + 1;
//        }
//
//        for (int i = 0; i < page; i++) {
//            List<SettleOrderItemDO> settleOrderItemDOs = salesOrderRemoteService.queryOrderItemSettle(batchNumber, i + 1);
//            if (CollectionUtils.isNotEmpty(settleOrderItemDOs)) {
//                try {
//                    List<SettleOrderItemDO> orderItemDOs = settleOrderItemService.addSettleOrderItems(settleOrderItemDOs);
//                    if (CollectionUtils.isNotEmpty(orderItemDOs)) {
//                        salesOrderRemoteService.updateOrderLineInfos(orderItemDOs);
//                    }
//                } catch (Exception e) {
//                    LOG.error("Push settlement data error！", e);
//                }
//            }
//        }
//
//        LOG.info("OrderItemSettleJob end<<<");
//    }
//
//    @Override
//    public String getFixed() {
//        return CURRENT_JOB_PREFIXED;
//    }
//
//}
