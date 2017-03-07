package ng.bayue.item.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 首页幻灯片轮播
 * 
 * @author fengyts Tue Mar 07 11:55:22 CST 2017
 */

public class CarouselDO extends BaseDO {

	private static final long serialVersionUID = 1444863184691482818L;

	/** 主键 */
	private Long id;

	/** 图片地址 */
	private String picture;

	/** 轮播图顺序 */
	private Integer sort;

	/** 图片链接地址 */
	private String linkUrl;

	/** 图片状态，0-作废；1-有效 */
	private Boolean status;

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
	 * 设置 图片地址
	 * 
	 * @param picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * 设置 轮播图顺序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 设置 图片链接地址
	 * 
	 * @param linkUrl
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	/**
	 * 设置 图片状态，0-作废；1-有效
	 * 
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
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
	 * 获取 图片地址
	 * 
	 * @return picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * 获取 轮播图顺序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 获取 图片链接地址
	 * 
	 * @return linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}

	/**
	 * 获取 图片状态，0-作废；1-有效
	 * 
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
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