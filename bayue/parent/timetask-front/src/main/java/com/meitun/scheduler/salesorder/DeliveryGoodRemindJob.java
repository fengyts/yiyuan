package com.meitun.scheduler.salesorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.scheduler.JobConstant;
import com.meitun.supplier.domain.SupplierDO;
import com.meitun.supplier.domain.result.SupplierResult;
import com.meitun.supplier.exception.DAOException;
import com.meitun.supplier.service.PurchasingManagementService;
import com.meitun.user.service.SendSmsService;
import com.meitun.user.util.sms.common.Sms;
import com.meitun.util.DateUtils;

/**
 * {供应商发货超时 短信提供} <br>
 * Create on : 2015年10月29日 下午2:19:36<br>
 *
 * @author Administrator<br>
 * @version timetask-front v0.0.1
 */
@Component
public class DeliveryGoodRemindJob extends AbstractJobRunnable {

    private static final Logger LOG = LoggerFactory.getLogger(DeliveryGoodRemindJob.class);

    private static final String CURRENT_JOB_PREFIXED = "deliveryGoodRemindJob";

    private static final int LAST_TIME = 72;

    @Autowired
    private SalesOrderRemoteService salesOrderRemoteService;

    @Autowired
    private PurchasingManagementService purchasingManagementService;

    @Autowired
    private SendSmsService sendSmsService;

    private final Integer beginTime = JobConstant.getDeliveryGoodRemindBeginTimeHour();

    private Integer lastTime = JobConstant.getDeliveryGoodRemindLastTimeHour();

    private final String remindString = JobConstant.getDeliveryGoodRemindString();

    @Override
    public void execute() {

        LOG.info("超时未发货提醒供应商job启动<<<");

        String days = DateUtils.format(new Date(), "MM月dd日");
        Boolean isSendSms = JobConstant.getDeliveryGoodRemindIsSendSms();
        if (lastTime == null) {
            lastTime = LAST_TIME;
        }

        // 查询超时未发货的订单数
        List<Map<String, Object>> list = salesOrderRemoteService.searchOverTimeUnDeliveryOrder(beginTime, lastTime);
        Map<Long, RemindDeliveryDTO> deliveryMap = new HashMap<Long, RemindDeliveryDTO>();
        if (CollectionUtils.isNotEmpty(list)) {
            List<Long> ids = new ArrayList<Long>();
            for (Map<String, Object> map : list) {
                Long supplierId = (Long) map.get("supplier_id");
                ids.add(supplierId);
            }
            try {
                SupplierResult supplierResult = purchasingManagementService.getSuppliersByIds(ids);
                if (null != supplierResult && CollectionUtils.isNotEmpty(supplierResult.getSupplierList())) {
                    for (SupplierDO supplierDO : supplierResult.getSupplierList()) {
                        if (StringUtils.isNotBlank(supplierDO.getPhone())) {
                            RemindDeliveryDTO remindDeliveryDTO = new RemindDeliveryDTO();
                            remindDeliveryDTO.supplierId = supplierDO.getId();
                            remindDeliveryDTO.supplierName = supplierDO.getName();
                            remindDeliveryDTO.phone = supplierDO.getPhone();
                            deliveryMap.put(remindDeliveryDTO.supplierId, remindDeliveryDTO);
                        } else {
                            LOG.info("supplier_id={},not find phone!", supplierDO.getId());
                        }
                    }
                    for (Map<String, Object> map : list) {
                        Long supplierId = (Long) map.get("supplier_id");
                        if (deliveryMap.containsKey(supplierId)) {
                            RemindDeliveryDTO remindDeliveryDTO = deliveryMap.get(supplierId);
                            if (map.get("ct_type").equals(beginTime)) {
                                remindDeliveryDTO.beginCount = String.valueOf(map.get("ct"));
                            } else {
                                remindDeliveryDTO.lastCount = String.valueOf(map.get("ct"));
                            }
                        }
                    }

                    if (MapUtils.isNotEmpty(deliveryMap)) {
                        List<Sms> smsList = new ArrayList<Sms>();
                        for (RemindDeliveryDTO remindDeliveryDTO : deliveryMap.values()) {
                            Sms sms = new Sms();
                            sms.setContent(String.format(remindString, new Object[] { remindDeliveryDTO.supplierName, days, remindDeliveryDTO.beginCount,
                                String.valueOf(beginTime), remindDeliveryDTO.lastCount, String.valueOf(lastTime) }));
                            sms.setMobile(remindDeliveryDTO.phone);
                            sms.setSendTime(new Date());
                            smsList.add(sms);
                            if (!isSendSms) {
                                LOG.info("发送短信息为:{}", sms);
                            }
                        }
                        if (isSendSms) {
                            sendSmsService.batchSendSms(smsList);
                        }
                    } else {
                        LOG.info("没有找到供应商信息");
                    }
                }
            } catch (DAOException e) {
                LOG.error("查询供应商列表信息出错：{}", e);
            }
        } else {
            LOG.info("超时未发货提醒job查询到数据");
        }
        LOG.info("超时未发货提醒job结束");
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

    private class RemindDeliveryDTO {
        public Long supplierId;
        public String supplierName;
        public String phone;
        public String beginCount = "0";
        public String lastCount = "0";
    }
}
