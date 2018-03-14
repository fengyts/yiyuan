package com.meitun.scheduler.salesorder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.meitun.hitaoorder.dto.GoodDetailDTO;
import com.meitun.hitaoorder.dto.OrderApplyDTO;
import com.meitun.hitaoorder.dto.OrderApplyResultDTO;
import com.meitun.hitaoorder.dto.PromotionDTO;
import com.meitun.hitaoorder.enums.HitaoEnums;
import com.meitun.hitaoorder.service.HitaoOrderService;
import com.meitun.salesorder.constants.SubOrderConstant;
import com.meitun.salesorder.domain.SubOrderDO;
import com.meitun.salesorder.dto.SalesOrderTaxDTO;
import com.meitun.salesorder.dto.SalesOrderTaxLineDTO;
import com.meitun.salesorder.dto.remote.MemRealinfoDTO;
import com.meitun.salesorder.dto.remote.OrderConsigneeDTO;
import com.meitun.salesorder.dto.remote.OrderLineDTO;
import com.meitun.salesorder.dto.remote.OrderPromotionDTO;
import com.meitun.salesorder.dto.remote.SubOrder4BackendDTO;
import com.meitun.salesorder.service.remote.SalesOrderRemoteService;
import com.meitun.salesorder.utilities.MathUtils;

@Component
public class SeaWashesDeliveryJob extends AbstractJobRunnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeaWashesDeliveryJob.class);

    private static final String CURRENT_JOB_PREFIXED = "seawashesdelivery";

    private static final int UN_PAYED_EXPIRED_MINUTE_DEFAULT = 30;

    @Autowired
    private SalesOrderRemoteService salesOrderRemoteService;

    @Autowired
    private HitaoOrderService hitaoOrderService;

    @Override
    public void execute() {
        long now = System.currentTimeMillis();
        LOGGER.info("{} SeaWashesDeliveryJob start......", now);

        int size = 0;
        do {
            size = 0;
            List<SubOrderDO> subOrderList = null;
            try {
                subOrderList = salesOrderRemoteService.querySubOrderByWaitPutSeaWashes(UN_PAYED_EXPIRED_MINUTE_DEFAULT);
            } catch (Exception e1) {
                LOGGER.error(e1.getMessage(), e1);
            }

            if (CollectionUtils.isNotEmpty(subOrderList)) {
                size = subOrderList.size();
                LOGGER.info("NeedToPut.size:{}", size);

                subOrderList = salesOrderRemoteService.setPayTradeNo(subOrderList);
                for (SubOrderDO subOrderDO : subOrderList) {
                    try {
                        salesOrderRemoteService.putWareHouseShippingBySubOrder(subOrderDO);
                    } catch (Exception e) {
                        LOGGER.error("Push suborder info to warehouse shipping exception", e);
                        continue;
                    }
                    LOGGER.info("code:{},channelId:{}", subOrderDO.getCode(), subOrderDO.getSeaChannel());

                    // 宁波保税区订单
                    if (SubOrderConstant.OrderType.BONDEDAREA.code.equals(subOrderDO.getType()) && subOrderDO.getSeaChannel().intValue() == 9) {
                        OrderApplyDTO applyDTO = createOrderApplyDTO(subOrderDO);
                        OrderApplyResultDTO o = hitaoOrderService.apply(applyDTO);
                        if (o != null && !o.isSuccess()) {
                            LOGGER.info("OrderApplyDTO:{},OrderApplyResultDTO:{}", JSONObject.toJSONString(applyDTO), JSONObject.toJSONString(o));
                        }
                    }
                }
            }
        } while (size > 0);

        LOGGER.info("{} SeaWashesDeliveryJob end......", now);
    }

    private OrderApplyDTO createOrderApplyDTO(final SubOrderDO subOrderDO) {
        OrderApplyDTO applyDTO = new OrderApplyDTO();
        List<PromotionDTO> promotions = applyDTO.getPromotions();
        List<GoodDetailDTO> goodDetailList = new ArrayList<GoodDetailDTO>();
        
        List<OrderPromotionDTO> couponList = salesOrderRemoteService.findPromotionListByOrderCode(subOrderDO.getCode());
        SubOrder4BackendDTO subOrder = salesOrderRemoteService.findSubOrder4BackendByCode(subOrderDO.getCode());
        
        if (CollectionUtils.isNotEmpty(couponList)) {
            for (OrderPromotionDTO coupon : couponList) {
                PromotionDTO promotion = new PromotionDTO();
                promotion.setProAmount(String.valueOf(coupon.getDiscount()));
                promotion.setProRemark(coupon.getPromotionName());
                promotions.add(promotion);
            }
        }
        List<OrderLineDTO> lineList = subOrder.getLineList();
        BigDecimal goodsTotalAmount = new BigDecimal(0);
        BigDecimal goodsTotalWeight = new BigDecimal(0);
        if (CollectionUtils.isNotEmpty(lineList)) {
            for (OrderLineDTO orderLine : lineList) {
            	SalesOrderTaxLineDTO taxLine = new SalesOrderTaxLineDTO(orderLine);
                GoodDetailDTO goodDetailDTO = new GoodDetailDTO();
                goodDetailDTO.setAmount(MathUtils.multiply(taxLine.getPrice(), orderLine.getQuantity()).setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
                goodDetailDTO.setGoodsName(orderLine.getSpuName());
                goodDetailDTO.setPrice(taxLine.getPrice());
                goodDetailDTO.setProductId(orderLine.getProductCode());
                goodDetailDTO.setQty(orderLine.getQuantity());
                goodDetailDTO.setUnit(orderLine.getUnit());
                goodDetailList.add(goodDetailDTO);
                goodsTotalAmount = MathUtils.add(goodsTotalAmount, goodDetailDTO.getAmount());
                goodsTotalWeight = MathUtils.add(goodsTotalWeight, orderLine.getWeight());
            }
            SalesOrderTaxDTO salesOrderTax = new SalesOrderTaxDTO(lineList);
            // applyDTO.setTariffAmount(salesOrderTax.getOrderTaxAmount());
            applyDTO.setAddedValueTaxAmount(new BigDecimal(salesOrderTax.getVatAmount()).setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
            applyDTO.setConsumptionDutyAmount(new BigDecimal(salesOrderTax.getConsumerTaxAmount()).setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
            applyDTO.setGrossWeight(goodsTotalWeight.setScale(3, BigDecimal.ROUND_DOWN).doubleValue());
            
            applyDTO.setTaxAmount(applyDTO.getAddedValueTaxAmount() + applyDTO.getConsumptionDutyAmount());
            applyDTO.setPostFee(salesOrderTax.getOriginalFreight());
        }

        applyDTO.setChannelId(subOrderDO.getSeaChannel());
        applyDTO.setGoods(goodDetailList);
        applyDTO.setOperation("0");
        applyDTO.setOrderNo(subOrderDO.getCode());
        applyDTO.setAmount(MathUtils.add(subOrderDO.getTotal(), subOrderDO.getFreight()).doubleValue());// 支付费用，需要加上运费
        applyDTO.setBuyerAccount(subOrderDO.getAccountName());
        
        applyDTO.setDisAmount(subOrderDO.getDiscount());
        
        applyDTO.setPromotions(promotions);
        applyDTO.setPaymentNo(subOrderDO.getPayCode());
        applyDTO.setPayTime(subOrderDO.getPayTime());
        applyDTO.setOrderSeqNo(subOrderDO.getPayCode());
        applyDTO.setSource(HitaoEnums.PAYMENT_MODE.getCode(subOrderDO.getPayWay()));
        MemRealinfoDTO memRealinfo = subOrder.getMemRealinfo();
        OrderConsigneeDTO orderConsigneeDTO = subOrder.getConsignee();
        if (null != memRealinfo) {
            applyDTO.setIdnum(memRealinfo.getIdentityCode());
            applyDTO.setName(memRealinfo.getRealName());
        }
        if (null != orderConsigneeDTO) {
            applyDTO.setPhone(orderConsigneeDTO.getMobile());
            applyDTO.setEmail(orderConsigneeDTO.getEmail());
            applyDTO.setConsignee(orderConsigneeDTO.getName());
            applyDTO.setProvince(orderConsigneeDTO.getProvince());
            applyDTO.setCity(orderConsigneeDTO.getCity());
            applyDTO.setDistrict(orderConsigneeDTO.getCounty());
            applyDTO.setConsigneeAddr(orderConsigneeDTO.getProvince() + orderConsigneeDTO.getCity() + orderConsigneeDTO.getCounty()
                + orderConsigneeDTO.getTown() + " " + orderConsigneeDTO.getAddress());
            applyDTO.setConsigneeTel(StringUtils.isEmpty(orderConsigneeDTO.getTelephone()) ? orderConsigneeDTO.getMobile() : orderConsigneeDTO.getTelephone());
            applyDTO.setMailNo(orderConsigneeDTO.getEmail());
        }
        applyDTO.setLogisticsName("圆通速递");
        return applyDTO;
    }

    @Override
    public String getFixed() {
        return CURRENT_JOB_PREFIXED;
    }

}
