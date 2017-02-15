package ng.bayue.item.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 商品详情
 * 
 * @author fengyts Tue Jul 26 18:47:20 CST 2016
 */

public class ItemDetailDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = -639081360617742408L;

	/** 主键 */
	private Long id;

	/** 小类编号+4位流水码 冗余字段 */
	private String spu;

	/** 商品ID */
	private Long itemId;

	/** 编号 spu+2位随机码 */
	private String prdid;

	/** 商品名称 */
	private String itemTitle;

	/** 产品前台展示名称 */
	private String mainTitle;

	/** 副标题 */
	private String subTitle;

	/** 条码(全局唯一) */
	private String barcode;

	/** 商品类型：1-正常商品，2-服务商品，3-二手商品,4-报废商品 默认1 */
	private Integer itemType;

	/** 市场价 */
	private Double basicPrice;

	/** 无理由退货期限 单位 天 */
	private Integer returnDays;

	/** 状态0-未上架 1-上架 2-作废 默认0 */
	private Integer status;

	/** 大类id,冗余字段 */
	private Long largeId;

	/** 小类id,冗余字段 */
	private Long smallId;

	/** 单位id,冗余字段 */
	private Long unitId;

	/** 毛重 单位g */
	private Double weight;

	/** 净重 单位g */
	private Double weightNet;

	/** 体积-宽度 */
	private Integer volumeWidth;

	/** 体积-长度 */
	private Integer volumeLength;

	/** 体积-高度 */
	private Integer volumeHigh;

	/** 品类三级code 冗余字段 */
	private String categoryCode;

	/** 规格 */
	private String specifications;

	/** 备注 */
	private String remark;

	/** 创建人 */
	private Long createUserId;

	/** 修改人 */
	private Long modifyUserId;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 小类编号+4位流水码 冗余字段
	 * 
	 * @param spu
	 */
	public void setSpu(String spu) {
		this.spu = spu;
	}

	/**
	 * 设置 商品ID
	 * 
	 * @param itemId
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * 设置 编号 spu+2位随机码
	 * 
	 * @param prdid
	 */
	public void setPrdid(String prdid) {
		this.prdid = prdid;
	}

	/**
	 * 设置 商品名称
	 * 
	 * @param itemTitle
	 */
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	/**
	 * 设置 产品前台展示名称
	 * 
	 * @param mainTitle
	 */
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	/**
	 * 设置 副标题
	 * 
	 * @param subTitle
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	/**
	 * 设置 条码(全局唯一)
	 * 
	 * @param barcode
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * 设置 商品类型：1-正常商品，2-服务商品，3-二手商品,4-报废商品 默认1
	 * 
	 * @param itemType
	 */
	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	/**
	 * 设置 市场价
	 * 
	 * @param basicPrice
	 */
	public void setBasicPrice(Double basicPrice) {
		this.basicPrice = basicPrice;
	}

	/**
	 * 设置 无理由退货期限 单位 天
	 * 
	 * @param returnDays
	 */
	public void setReturnDays(Integer returnDays) {
		this.returnDays = returnDays;
	}

	/**
	 * 设置 状态0-未上架 1-上架 2-作废 默认0
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 设置 大类id,冗余字段
	 * 
	 * @param largeId
	 */
	public void setLargeId(Long largeId) {
		this.largeId = largeId;
	}

	/**
	 * 设置 小类id,冗余字段
	 * 
	 * @param smallId
	 */
	public void setSmallId(Long smallId) {
		this.smallId = smallId;
	}

	/**
	 * 设置 单位id,冗余字段
	 * 
	 * @param unitId
	 */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	/**
	 * 设置 毛重 单位g
	 * 
	 * @param weight
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * 设置 净重 单位g
	 * 
	 * @param weightNet
	 */
	public void setWeightNet(Double weightNet) {
		this.weightNet = weightNet;
	}

	/**
	 * 设置 体积-宽度
	 * 
	 * @param volumeWidth
	 */
	public void setVolumeWidth(Integer volumeWidth) {
		this.volumeWidth = volumeWidth;
	}

	/**
	 * 设置 体积-长度
	 * 
	 * @param volumeLength
	 */
	public void setVolumeLength(Integer volumeLength) {
		this.volumeLength = volumeLength;
	}

	/**
	 * 设置 体积-高度
	 * 
	 * @param volumeHigh
	 */
	public void setVolumeHigh(Integer volumeHigh) {
		this.volumeHigh = volumeHigh;
	}

	/**
	 * 设置 品类三级code 冗余字段
	 * 
	 * @param categoryCode
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * 设置 规格
	 * 
	 * @param specifications
	 */
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	/**
	 * 设置 备注
	 * 
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 设置 创建人
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 设置 修改人
	 * 
	 * @param modifyUserId
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	/**
	 * 设置 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取 主键
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 获取 小类编号+4位流水码 冗余字段
	 * 
	 * @return spu
	 */
	public String getSpu() {
		return spu;
	}

	/**
	 * 获取 商品ID
	 * 
	 * @return itemId
	 */
	public Long getItemId() {
		return itemId;
	}

	/**
	 * 获取 编号 spu+2位随机码
	 * 
	 * @return prdid
	 */
	public String getPrdid() {
		return prdid;
	}

	/**
	 * 获取 商品名称
	 * 
	 * @return itemTitle
	 */
	public String getItemTitle() {
		return itemTitle;
	}

	/**
	 * 获取 产品前台展示名称
	 * 
	 * @return mainTitle
	 */
	public String getMainTitle() {
		return mainTitle;
	}

	/**
	 * 获取 副标题
	 * 
	 * @return subTitle
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * 获取 条码(全局唯一)
	 * 
	 * @return barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * 获取 商品类型：1-正常商品，2-服务商品，3-二手商品,4-报废商品 默认1
	 * 
	 * @return itemType
	 */
	public Integer getItemType() {
		return itemType;
	}

	/**
	 * 获取 市场价
	 * 
	 * @return basicPrice
	 */
	public Double getBasicPrice() {
		return basicPrice;
	}

	/**
	 * 获取 无理由退货期限 单位 天
	 * 
	 * @return returnDays
	 */
	public Integer getReturnDays() {
		return returnDays;
	}

	/**
	 * 获取 状态0-未上架 1-上架 2-作废 默认0
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 获取 大类id,冗余字段
	 * 
	 * @return largeId
	 */
	public Long getLargeId() {
		return largeId;
	}

	/**
	 * 获取 小类id,冗余字段
	 * 
	 * @return smallId
	 */
	public Long getSmallId() {
		return smallId;
	}

	/**
	 * 获取 单位id,冗余字段
	 * 
	 * @return unitId
	 */
	public Long getUnitId() {
		return unitId;
	}

	/**
	 * 获取 毛重 单位g
	 * 
	 * @return weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * 获取 净重 单位g
	 * 
	 * @return weightNet
	 */
	public Double getWeightNet() {
		return weightNet;
	}

	/**
	 * 获取 体积-宽度
	 * 
	 * @return volumeWidth
	 */
	public Integer getVolumeWidth() {
		return volumeWidth;
	}

	/**
	 * 获取 体积-长度
	 * 
	 * @return volumeLength
	 */
	public Integer getVolumeLength() {
		return volumeLength;
	}

	/**
	 * 获取 体积-高度
	 * 
	 * @return volumeHigh
	 */
	public Integer getVolumeHigh() {
		return volumeHigh;
	}

	/**
	 * 获取 品类三级code 冗余字段
	 * 
	 * @return categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * 获取 规格
	 * 
	 * @return specifications
	 */
	public String getSpecifications() {
		return specifications;
	}

	/**
	 * 获取 备注
	 * 
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 获取 创建人
	 * 
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 获取 修改人
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

	/**
	 * 获取 创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}