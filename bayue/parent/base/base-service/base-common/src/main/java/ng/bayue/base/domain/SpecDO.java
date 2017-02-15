package ng.bayue.base.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 规格
 * 
 * @author fengyts Tue Jul 26 10:08:30 CST 2016
 */

public class SpecDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 2701171620170008319L;

	/** 主键 */
	private Long id;

	/** 规格编号 */
	private String code;

	/** 规格 */
	private String spec;

	/** 规格排序 */
	private Integer sort;

	/** 默认1有效,0无效(状态) */
	private Boolean status;

	/** 备注 */
	private String remark;

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
	 * 设置 规格编号
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设置 规格
	 * 
	 * @param spec
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}

	/**
	 * 设置 规格排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 设置 默认1有效,0无效(状态)
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
	 * 获取 规格编号
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取 规格
	 * 
	 * @return spec
	 */
	public String getSpec() {
		return spec;
	}

	/**
	 * 获取 规格排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 获取 默认1有效,0无效(状态)
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
	 * 获取 更新时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}