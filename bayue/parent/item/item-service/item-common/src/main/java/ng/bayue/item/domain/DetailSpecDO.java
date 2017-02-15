package ng.bayue.item.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 商品规格关联信息
 * 
 * @author fengyts Tue Jul 26 09:58:30 CST 2016
 */

public class DetailSpecDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = -2744976961176777464L;

	/** 主键 */
	private Long id;

	/** prid纬度 detailid */
	private Long detailId;

	/** 规格所属规格组 */
	private Long specGroupId;

	/** 当前规格组排列顺序 */
	private Integer sort;

	/** 添加时间 */
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
	 * 设置 prid纬度 detailid
	 * 
	 * @param detailId
	 */
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	/**
	 * 设置 规格所属规格组
	 * 
	 * @param specGroupId
	 */
	public void setSpecGroupId(Long specGroupId) {
		this.specGroupId = specGroupId;
	}

	/**
	 * 设置 当前规格组排列顺序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 设置 添加时间
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
	 * 获取 prid纬度 detailid
	 * 
	 * @return detailId
	 */
	public Long getDetailId() {
		return detailId;
	}

	/**
	 * 获取 规格所属规格组
	 * 
	 * @return specGroupId
	 */
	public Long getSpecGroupId() {
		return specGroupId;
	}

	/**
	 * 获取 当前规格组排列顺序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 获取 添加时间
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