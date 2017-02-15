package ng.bayue.item.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 商品基础信息
 * 
 * @author fengyts Sun Jul 17 14:49:58 CST 2016
 */

public class ItemInfoDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = -1547492555344393864L;

	/** 主键 */
	private Long id;

	/** 小类编号+4位流水码 */
	private String spu;

	/** spu名称 */
	private String mainTitle;

	/** 大类ID */
	private Long largeId;

	/** 小类ID */
	private Long smallId;

	/** 单位 */
	private Long unitId;

	/** 备注 */
	private String remark;

	/** 创建时间 */
	private Date createTime;

	/** 创建人 */
	private Long createUserId;

	/** 修改时间 */
	private Date modifyTime;

	/** 修改人 */
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
	 * 设置 小类编号+4位流水码
	 * 
	 * @param spu
	 */
	public void setSpu(String spu) {
		this.spu = spu;
	}

	/**
	 * 设置 spu名称
	 * 
	 * @param mainTitle
	 */
	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	/**
	 * 设置 大类ID
	 * 
	 * @param largeId
	 */
	public void setLargeId(Long largeId) {
		this.largeId = largeId;
	}

	/**
	 * 设置 小类ID
	 * 
	 * @param smallId
	 */
	public void setSmallId(Long smallId) {
		this.smallId = smallId;
	}

	/**
	 * 设置 单位
	 * 
	 * @param unitId
	 */
	public void setUnitId(Long unitId) {
		this.unitId = unitId;
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
	 * 设置 创建人
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
	 * 设置 修改人
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
	 * 获取 小类编号+4位流水码
	 * 
	 * @return spu
	 */
	public String getSpu() {
		return spu;
	}

	/**
	 * 获取 spu名称
	 * 
	 * @return mainTitle
	 */
	public String getMainTitle() {
		return mainTitle;
	}

	/**
	 * 获取 大类ID
	 * 
	 * @return largeId
	 */
	public Long getLargeId() {
		return largeId;
	}

	/**
	 * 获取 小类ID
	 * 
	 * @return smallId
	 */
	public Long getSmallId() {
		return smallId;
	}

	/**
	 * 获取 单位
	 * 
	 * @return unitId
	 */
	public Long getUnitId() {
		return unitId;
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
	 * 获取 创建人
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
	 * 获取 修改人
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

}