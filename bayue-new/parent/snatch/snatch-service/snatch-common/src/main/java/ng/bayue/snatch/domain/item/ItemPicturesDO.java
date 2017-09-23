package ng.bayue.snatch.domain.item;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 商品图片信息
 * 
 * @author fengyts Wed Jul 13 13:17:51 CST 2016
 */

public class ItemPicturesDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 185939284533739473L;

	/** 主键 */
	private Long id;

	/** 商品id 外键 */
	private Long itemId;

	/**  */
	private Long detailId;

	/** 图片路径 */
	private String picture;

	/** 是否主图 默认为0-否 1-是 */
	private Boolean isMaster;

	/** 记录创建时间 */
	private Date createTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 商品id 外键
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
	 * 设置 图片路径
	 * 
	 * @param picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * 设置 是否主图 默认为0-否 1-是
	 * 
	 * @param isMaster
	 */
	public void setIsMaster(Boolean isMaster) {
		this.isMaster = isMaster;
	}

	/**
	 * 设置 记录创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	 * 获取 商品id 外键
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
	 * 获取 图片路径
	 * 
	 * @return picture
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * 获取 是否主图 默认为0-否 1-是
	 * 
	 * @return isMaster
	 */
	public Boolean getIsMaster() {
		return isMaster;
	}

	/**
	 * 获取 记录创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

}