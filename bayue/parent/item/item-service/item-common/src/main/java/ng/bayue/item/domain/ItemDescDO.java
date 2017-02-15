package ng.bayue.item.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 商品介绍-详情页图文混排
 * 
 * @author fengyts Sat Dec 10 09:24:15 CST 2016
 */

public class ItemDescDO extends BaseDO {

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 4607874576284110486L;

	/** 主键 */
	private Long id;

	/** 商品ID */
	private Long itemId;

	/** 商品详情id */
	private Long detailId;

	/** 商品描述 */
	private String description;

	/** 创建人 */
	private Long createUserId;

	/** 创建时间 */
	private Date createTime;

	/** 修改人 */
	private Long modifyUserId;

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
	 * 设置 商品ID
	 * 
	 * @param itemId
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * 设置
	 * 
	 * @param detailId
	 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/**
	 * 设置 商品描述
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * 设置 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	 * 获取 商品ID
	 * 
	 * @return itemId
	 */
	public Long getItemId() {
		return itemId;
	}

	/**
	 * 获取
	 * 
	 * @return detailId
	 */
	public Long getDetailId() {
		return detailId;
	}

	/**
	 * 获取 商品描述
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
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
	 * 获取 创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
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
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}