package ng.bayue.promotion.domain;

import ng.bayue.util.BaseDO;

import java.util.Date;

/**
 * 专题商品关联
 * 
 * @author fengyts Mon Dec 26 16:28:48 CST 2016
 */

public class TopicItemDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 2929984852668840869L;

	/** 主键 */
	private Long id;

	/** 专题id */
	private Long topicId;

	/** 商品id */
	private Long itemId;

	/** 是否测试专场商品：0-否;1-是 */
	private Boolean isTest;

	/** 商品在专题中的排序 */
	private Integer sort;

	/** 市场价,冗余商品详情表字段 */
	private Double basicPrice;

	/** 活动价格 */
	private Double topicPrice;

	/** 该商品参与总人数 */
	private Integer snatchNumber;

	/** 商品spu */
	private String spu;

	/** 商品详情id */
	private Long detailId;
	
	/** 商品prdid,冗余字段*/
	private String prdid;

	/** 商品spu名称，冗余商品表字段 */
	private String mainTitle;

	/** 品类小类id，冗余字段 */
	private Long smallId;

	/** 是否为爆款商品 0-否 1-是 */
	private Boolean isHot;

	/** 销售数量 */
	private Long saledAmount;

	/** 是否有库存：0-无；1-有 */
	private Boolean hasInventory;

	/** 该商品在该专题中状态：0-无效；1-有效 */
	private Boolean status;

	/** 备注 */
	private String remark;

	/** 创建时间 */
	private Date createTime;

	/** 创建人id */
	private Long createUserId;

	/** 修改时间 */
	private Date modifyTime;

	/** 修改人id */
	private Long modifyUserId;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 专题id
	 * 
	 * @param topicId
	 */
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	/**
	 * 设置 商品id
	 * 
	 * @param itemId
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * 设置 是否测试专场商品：0-否;1-是
	 * 
	 * @param isTest
	 */
	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	/**
	 * 设置 商品在专题中的排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 设置 市场价,冗余商品详情表字段
	 * 
	 * @param basicPrice
	 */
	public void setBasicPrice(Double basicPrice) {
		this.basicPrice = basicPrice;
	}

	/**
	 * 设置 活动价格
	 * 
	 * @param topicPrice
	 */
	public void setTopicPrice(Double topicPrice) {
		this.topicPrice = topicPrice;
	}

	/**
	 * 设置 该商品参与总人数
	 * 
	 * @param snatchNumber
	 */
	public void setSnatchNumber(Integer snatchNumber) {
		this.snatchNumber = snatchNumber;
	}

	/**
	 * 设置 商品spu
	 * 
	 * @param spu
	 */
	public void setSpu(String spu) {
		this.spu = spu;
	}

	/**
	 * 设置 商品详情id
	 * 
	 * @param detailId
	 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/**
	 * 设置 商品spu名称，冗余商品表字段
	 * 
	 * @param mainTitle
	 */
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	/**
	 * 设置 品类小类id，冗余字段
	 * 
	 * @param smallId
	 */
	public void setSmallId(Long smallId) {
		this.smallId = smallId;
	}

	/**
	 * 设置 是否为爆款商品 0-否 1-是
	 * 
	 * @param isHot
	 */
	public void setIsHot(Boolean isHot) {
		this.isHot = isHot;
	}

	/**
	 * 设置 销售数量
	 * 
	 * @param saledAmount
	 */
	public void setSaledAmount(Long saledAmount) {
		this.saledAmount = saledAmount;
	}

	/**
	 * 设置 是否有库存：0-无；1-有
	 * 
	 * @param hasInventory
	 */
	public void setHasInventory(Boolean hasInventory) {
		this.hasInventory = hasInventory;
	}

	/**
	 * 设置 该商品在该专题中状态：0-无效；1-有效
	 * 
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
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
	 * 设置 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 创建人id
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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
	 * 设置 修改人id
	 * 
	 * @param modifyUserId
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	 * 获取 专题id
	 * 
	 * @return topicId
	 */
	public Long getTopicId() {
		return topicId;
	}

	/**
	 * 获取 商品id
	 * 
	 * @return itemId
	 */
	public Long getItemId() {
		return itemId;
	}

	/**
	 * 获取 是否测试专场商品：0-否;1-是
	 * 
	 * @return isTest
	 */
	public Boolean getIsTest() {
		return isTest;
	}

	/**
	 * 获取 商品在专题中的排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 获取 市场价,冗余商品详情表字段
	 * 
	 * @return basicPrice
	 */
	public Double getBasicPrice() {
		return basicPrice;
	}

	/**
	 * 获取 活动价格
	 * 
	 * @return topicPrice
	 */
	public Double getTopicPrice() {
		return topicPrice;
	}

	/**
	 * 获取 该商品参与总人数
	 * 
	 * @return snatchNumber
	 */
	public Integer getSnatchNumber() {
		return snatchNumber;
	}

	/**
	 * 获取 商品spu
	 * 
	 * @return spu
	 */
	public String getSpu() {
		return spu;
	}

	/**
	 * 获取 商品详情id
	 * 
	 * @return detailId
	 */
	public Long getDetailId() {
		return detailId;
	}

	/**
	 * 获取 商品spu名称，冗余商品表字段
	 * 
	 * @return mainTitle
	 */
	public String getMainTitle() {
		return mainTitle;
	}

	/**
	 * 获取 品类小类id，冗余字段
	 * 
	 * @return smallId
	 */
	public Long getSmallId() {
		return smallId;
	}

	/**
	 * 获取 是否为爆款商品 0-否 1-是
	 * 
	 * @return isHot
	 */
	public Boolean getIsHot() {
		return isHot;
	}

	/**
	 * 获取 销售数量
	 * 
	 * @return saledAmount
	 */
	public Long getSaledAmount() {
		return saledAmount;
	}

	/**
	 * 获取 是否有库存：0-无；1-有
	 * 
	 * @return hasInventory
	 */
	public Boolean getHasInventory() {
		return hasInventory;
	}

	/**
	 * 获取 该商品在该专题中状态：0-无效；1-有效
	 * 
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
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
	 * 获取 创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 创建人id
	 * 
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 获取 修改人id
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid = prdid;
	}
	
	

}