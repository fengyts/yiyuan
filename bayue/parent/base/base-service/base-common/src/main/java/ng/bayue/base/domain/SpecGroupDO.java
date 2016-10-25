package ng.bayue.base.domain;

import java.util.Date;

import ng.bayue.util.BaseDO;

/**
 * 规格组
 * 
 * @author fengyts Wed Jul 27 14:57:24 CST 2016
 */

public class SpecGroupDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 417832992241414727L;

	/** 主键 */
	private Long id;

	/** 规格组编号 */
	private String code;

	/** 规格组名称(前端展示) */
	private String name;

	/** 规格组别名 */
	private String alias;

	/** 规格组排序 */
	private Integer sort;

	/** 1为有效 ,0为无效 */
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
	 * 设置 规格组编号
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设置 规格组名称(前端展示)
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置 规格组别名
	 * 
	 * @param alias
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	/**
	 * 设置 规格组排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 设置 1为有效 ,0为无效
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
	 * 获取 规格组编号
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取 规格组名称(前端展示)
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取 规格组别名
	 * 
	 * @return alias
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * 获取 规格组排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 获取 1为有效 ,0为无效
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