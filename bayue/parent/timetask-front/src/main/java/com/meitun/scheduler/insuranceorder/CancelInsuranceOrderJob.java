package com.meitun.scheduler.insuranceorder;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.meitun.bean.ResultTO;
import com.meitun.finplat.dto.insurance.InsuranceOrderDTO;
import com.meitun.finplat.enums.OrderStatus;
import com.meitun.finplat.enums.PolicyEnum;
import com.meitun.finplat.query.ebao.QueryGetPolicy;
import com.meitun.finplat.result.ebao.ResultGetPolicy;
import com.meitun.finplat.service.remote.InsuranceOrderCancelRemoteService;
import com.meitun.finplat.service.remote.InsuranceOrderRemoteService;
import com.meitun.finplat.util.DateUtil;
import com.meitun.finplat.util.StringUtil;
import com.meitun.scheduler.JobConstant;
import com.meitun.scheduler.salesorder.AbstractJobRunnable;

/**
 * 取消保单任务调度
 * @author hehaifei
 *
 */
@Component
public class CancelInsuranceOrderJob extends AbstractJobRunnable{
	private static final Logger LOG = LoggerFactory.getLogger(CancelInsuranceOrderJob.class);
	private static final String CURRENT_JOB_PREFIXED = "cancelinsuranceorder";
	
	private static final int UN_PAYED_EXPIRED_MINUTE_DEFAULT = 2*60;//俩小时
	
	@Autowired
	private InsuranceOrderCancelRemoteService insuranceOrderCancelRemoteService;
	@Autowired
	private InsuranceOrderRemoteService insuranceOrderRemoteService;

	@Override
	@Transactional
	public void execute(){
		Integer expired = JobConstant.getCancelUnpayMinute4Insurance();
		if(null==expired){
			expired = UN_PAYED_EXPIRED_MINUTE_DEFAULT;
		}
		LOG.info("取消{}分钟未支付保单job启动....", expired);
		List<InsuranceOrderDTO> insuranceOrderList = insuranceOrderRemoteService.queryInsuranceOrderByUnPayIsExpired(expired);
		
		//目前最多获取100条数据
		if(CollectionUtils.isNotEmpty(insuranceOrderList)){
			for(InsuranceOrderDTO orderDTO : insuranceOrderList){
	        	 QueryGetPolicy policy = new QueryGetPolicy();
	        	 policy.setReferenceNumber(orderDTO.getOrderCode());
	        	 policy.setProductcode(orderDTO.getProductCode());
				//1.调用易保查询接口
				 ResultTO<ResultGetPolicy> policyResult = insuranceOrderRemoteService.getYBPolicy(policy);
				 //保单已生效
			     if(policyResult != null && policyResult.isSuccess() 
			    		 && (PolicyEnum.checkPolicyStatus.EFFECTIVE.name()).equals(policyResult.getData().getStatusCode()) ){
					 // if 订单状态是支付完成  则修改状态 待支付--->支付完成
					 InsuranceOrderDTO insuranceOrderDTO = new InsuranceOrderDTO();
					 insuranceOrderDTO.setDelFlag("job");
					 insuranceOrderDTO.setOrderCode(orderDTO.getOrderCode());
					 insuranceOrderDTO.setStatus(OrderStatus.PAYMENT.getCode());//支付状态--已支付
					 insuranceOrderDTO.setInsuranceCode(policyResult.getData().getPolicyNumber());//出单后的保单号
					 insuranceOrderDTO.setInsuredAmount(StringUtil.getDoubleValue(policyResult.getData().getLimitAmount()));//保额
					 insuranceOrderDTO.setPayTime(DateUtil.getDateByISODate(policyResult.getData().getPaymentDate()));//支付完成时间
					 insuranceOrderDTO.setEffectiveTime(DateUtil.getDateByISODate(policyResult.getData().getEffectiveDate()));//生效日期
					 insuranceOrderDTO.setExpiredTime(DateUtil.getDateByISODate(policyResult.getData().getExpiredDate()));//失效日期
					 insuranceOrderRemoteService.modify(insuranceOrderDTO);
				//2.取消订单
				 }else {
					 insuranceOrderCancelRemoteService.cancelOrder(orderDTO.getOrderCode());
				 }
			}
		}else{
			LOG.info("不存在过期未支付保单");
		}
		
		LOG.info("取消过期未支付保单job完成");
	}
	
	@Override
	public String getFixed() {
		return CURRENT_JOB_PREFIXED;
	}

}
