package com.meitun.scheduler.salesorder;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.constants.ExceptionBusinessLogConstant.LogStatus;
import com.meitun.salesorder.domain.ExceptionBusinessLogDO;
import com.meitun.salesorder.service.local.ExceptionBusinessLogLocalService;

/**
 * 订单支付补偿
 *
 * @author 项硕
 * @version 2015年6月3日 下午5:41:37
 */
@Component("orderPaymentCompensation")
public class OrderPaymentCompensationJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(OrderPaymentCompensationJob.class);

    private static final String CURRENT_JOB_PREFIXED = "orderPayment";

    private static final int COUNT = 50;

    @Autowired
    private ExceptionBusinessLogLocalService exceptionBusinessLogLocalService;

    @Override
    public void execute() {
        LOG.debug("start to OrderPaymentCompensationJob");
        List<ExceptionBusinessLogDO> exLogList = exceptionBusinessLogLocalService.selectListByStatusWithLimit(LogStatus.NEW, COUNT);

        if (CollectionUtils.isNotEmpty(exLogList)) {
            for (ExceptionBusinessLogDO exLog : exLogList) {
                if (exceptionBusinessLogLocalService.compensate(exLog)) {
                    // 补偿成功
                    LOG.info("compensate success, type={}, code={}", exLog.getType(), exLog.getBizCode());
                } else {
                    // 补偿失败
                    LOG.info("compensate fail, type={}, code={}", exLog.getType(), exLog.getBizCode());
                }
            }
        }
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
