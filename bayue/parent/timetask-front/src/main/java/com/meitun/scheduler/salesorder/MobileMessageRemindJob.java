package com.meitun.scheduler.salesorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.meitun.salesorder.domain.SalesOrderDO;
import com.meitun.salesorder.dto.remote.SalesOrderDTO;
import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.scheduler.JobConstant;
import com.meitun.user.domain.UserDO;
import com.meitun.user.service.SendSmsService;
import com.meitun.user.service.UserService;
import com.meitun.user.util.sms.common.Sms;

/**
 * 用户下单后15分钟内未付款发短信提醒任务调度
 * 
 * @author yanji
 *
 */
@Component
public class MobileMessageRemindJob extends AbstractJobRunnable {
	private static final Logger LOG = LoggerFactory.getLogger(MobileMessageRemindJob.class);
	private static final String CURRENT_JOB_PREFIXED = "sendmobilemessage";

	private static final int UN_PAYED_EXPIRED_MINUTE_DEFAULT = 15;
	
	@Value("#{config['order.cancel.fifteenminutes.message']}")
	private String orderCancelMessage; 

	@Autowired
	private SendSmsService sendSmsService;
	@Autowired
	private SalesOrderRemoteService salesOrderRemoteService;
	@Autowired
	private UserService userService;

	@Override
	public void execute() {
		Integer expired = JobConstant.getSendMobileMessageMinute();
		if (null == expired) {
			expired = UN_PAYED_EXPIRED_MINUTE_DEFAULT;
		}
		LOG.info("用户下单后15分钟内未付款发短信提醒job启动....");
		List<SalesOrderDO> salesOrderList = salesOrderRemoteService.querySalesOrderByUnPayOverFifteenMinutes(expired);
		
		if (CollectionUtils.isNotEmpty(salesOrderList)) {

			List<Long> memberIds = new ArrayList<Long>();

			// 根据父订单list组装memberId list
			for (SalesOrderDO salesOrderDO : salesOrderList) {
				memberIds.add(salesOrderDO.getMemberId());
			}

			// 根据memberId list查询 user list
			Map<Long, String> userMap = new HashMap<Long, String>();
			List<UserDO> users = userService.selectByIds(memberIds);
			if (CollectionUtils.isNotEmpty(users)) {
				for (UserDO user : users) {
					userMap.put(user.getId(), user.getMobile());
				}
			}

			// 未支付的订单，15分钟后发送短信提醒
			List<Sms> smsList = new ArrayList<Sms>();
			Set<String> batchNum = new HashSet<String>();
			for (SalesOrderDO salesOrder : salesOrderList) {
				Long memberId = salesOrder.getMemberId();
				String content = String.format(orderCancelMessage, salesOrder.getCode());
				if(SalesOrderDTO.IS_MERGED_ORDER.equals(salesOrder.getIsMergedOrder())) {
					if(!batchNum.contains(salesOrder.getBatchNum())) {
						content = String.format(orderCancelMessage, salesOrder.getBatchNum()); 
						batchNum.add(salesOrder.getBatchNum());
					} else {
						continue;
					}
				}
				smsList.add(new Sms(userMap.get(memberId), content));
			}
			try {
				LOG.debug("调用发送短信接口入参：smsList：{}", JSONArray.fromObject(smsList).toString());
				sendSmsService.batchSendSms(smsList);
			} catch (Exception e) {
				LOG.error("调批量发送短信接口发送失败：" + e);
			}

		} else {
			LOG.info("不存在用户下单后15分钟内未支付订单");
		}
		LOG.info("用户下单后15分钟内未付款发短信提醒job完成");
	}

	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
