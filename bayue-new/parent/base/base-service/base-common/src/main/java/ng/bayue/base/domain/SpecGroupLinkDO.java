package ng.bayue.base.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 规格与规格组关系
 * 
 * @author fengyts Tue Jul 26 10:08:30 CST 2016
 */

public class SpecGroupLinkDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 7262640341552231611L;

	/** 主键 */
	private Long id;

	/** g规格组id */
	private Long groupId;

	/** 规格id */
	private Long specId;

	/** 规格在该组中的排序 */
	private Integer sort;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
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
	 * 设置 g规格组id
	 * 
	 * @param groupId
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * 设置 规格id
	 * 
	 * @param specId
	 */
	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	/**
	 * 设置 规格在该组中的排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
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
	 * 设置 更新时间
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
	 * 获取 g规格组id
	 * 
	 * @return groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * 获取 规格id
	 * 
	 * @return specId
	 */
	public Long getSpecId() {
		return specId;
	}

	/**
	 * 获取 规格在该组中的排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
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
	 * 获取 更新时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}