package com.meitun.scheduler;


import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ng.bayue.service.RedisCacheService;

/**
 * 任务调度常量
 * 
 * @author szy
 */
@Component
public class JobConstant {

    private static final Logger LOG = LoggerFactory.getLogger(JobConstant.class);

    private static JobConstant instance = null;

    private Properties properties;

    @Autowired
    private RedisCacheService redisCacheService;

    public JobConstant() {
        init();

    }

    private void init() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/config/metainfo.properties"));
        } catch (IOException e) {
            LOG.error("没有找到配置文件config.properties");
        }
    }

    public static JobConstant getInstance() {
        if (instance == null) {
            synchronized (JobConstant.class) {
                instance = new JobConstant();
            }
        }
        return instance;
    }

    public boolean isGobleRunnable() {
        return isRunnableByJobBasePre("goble");
    }

    public boolean isRunnableByJobPreName(String preFixed) {
        return isGobleRunnable() && isRunnableByJobBasePre(preFixed) && isRunningSign(preFixed);
    }

    public static boolean isRunnableByJobBasePre(String preFixed) {
        String runnable = getProperties().getProperty(preFixed + ".isrun");
        if ("true".equalsIgnoreCase(runnable) || "yes".equalsIgnoreCase(runnable)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Integer getCancelUnpayMinute() {
        String minute = getProperties().getProperty("cancelorder.expiredminute");
        if (StringUtils.isNotBlank(minute) && NumberUtils.isNumber(minute)) {
            return Integer.getInteger(minute);
        }
        return null;
    }
    
	public static Integer getCancelUnpayMinute4Insurance(){
		String minute = getProperties().getProperty("cancelinsuranceorder.expiredminute");
		if(StringUtils.isNotBlank(minute) && NumberUtils.isNumber(minute)){
			return Integer.valueOf(minute);
		}
		return null;
	}
	

    public static Integer getTrialCancelUnpayMinute() {
        String minute = getProperties().getProperty("canceltrialorder.expiredminute");
        if (StringUtils.isNotBlank(minute) && NumberUtils.isNumber(minute)) {
            return Integer.getInteger(minute);
        }
        return null;
    }

    public static Integer getTrialCancelUnpayDay() {
        String day = getProperties().getProperty("cancelnosendtrialorder.expiredday");
        if (StringUtils.isNotBlank(day) && NumberUtils.isNumber(day)) {
            return Integer.getInteger(day);
        }
        return null;
    }

    // 用户下单15分钟内未付款
    public static Integer getSendMobileMessageMinute() {
        String minute = getProperties().getProperty("sendmobilemessage.expiredminute");
        if (StringUtils.isNotBlank(minute) && NumberUtils.isNumber(minute)) {
            return Integer.getInteger(minute);
        }
        return null;
    }

    public static Integer getReceivedDays() {
        String minute = getProperties().getProperty("receivedgoods.receiveddays");
        if (StringUtils.isNotBlank(minute) && NumberUtils.isNumber(minute)) {
            return Integer.getInteger(minute);
        }
        return null;
    }

    public static Properties getProperties() {
        return getInstance().properties;
    }

    public boolean isRunningSign(String preFixed) {
        final String key = "job:salesorder:" + preFixed;
        synchronized (key) {
            if (redisCacheService.lock(key)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public void cleanSign(String preFixed) {
        synchronized (preFixed) {
            if (redisCacheService.keyExists("job:salesorder:" + preFixed)) {
            	redisCacheService.unLock("job:salesorder:" + preFixed);
            }
        }
    }

    public String[] getUnPutPayedOrder() {
        String unPutPayedOrders = getProperties().getProperty("putpayedunputsuborder.unputpayedorder");
        if (StringUtils.isNoneBlank(unPutPayedOrders)) {
            return unPutPayedOrders.split(",");
        }
        return null;
    }

    // 获取推送财务每批处理子单数量
    public static Integer getBatchNumber() {
        String batchNumber = getProperties().getProperty("orderitemsettle.batchnumber");
        if (StringUtils.isNotBlank(batchNumber) && NumberUtils.isNumber(batchNumber)) {
            return Integer.valueOf(batchNumber);
        }
        return null;
    }

    public static Integer getDeliveryGoodRemindBeginTimeHour() {
        String beginTimeHour = getProperties().getProperty("deliveryGoodRemindJob.beginTimeHour");
        if (StringUtils.isNotBlank(beginTimeHour) && NumberUtils.isNumber(beginTimeHour)) {
            return Integer.valueOf(beginTimeHour);
        }
        return null;
    }

    public static Integer getDeliveryGoodRemindLastTimeHour() {
        String lastTimeHour = getProperties().getProperty("deliveryGoodRemindJob.lastTimeHour");
        if (StringUtils.isNotBlank(lastTimeHour) && NumberUtils.isNumber(lastTimeHour)) {
            return Integer.valueOf(lastTimeHour);
        }
        return null;
    }

    public static String getDeliveryGoodRemindString() {
        String batchNumber = getProperties().getProperty("deliveryGoodRemindJob.string");
        return batchNumber;
    }

    public static boolean getDeliveryGoodRemindIsSendSms() {
        String isSendSms = getProperties().getProperty("deliveryGoodRemindJob.isSendSms");
        if ("yes".equalsIgnoreCase(isSendSms) || "y".equalsIgnoreCase(isSendSms)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
