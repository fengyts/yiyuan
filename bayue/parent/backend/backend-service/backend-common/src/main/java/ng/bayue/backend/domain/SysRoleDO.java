package ng.bayue.backend.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonWriteNullProperties;

import ng.bayue.backend.common.BaseDO;

/**
 * 系统用户角色
 * 
 * @author haisheng.long Tue Oct 25 13:31:48 CST 2016
 */

public class SysRoleDO extends BaseDO {

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = -370049191830593337L;

	/** 主键 */
	private Long id;

	/** 角色名称 */
	private String name;

	/** 角色代码 */
	private String code;

	/** 角色类型 */
	private String type;

	/** 是否可用:1-可用;0-不可用 */
	private Boolean status;

	/** 备注 */
	private String remark;

	/** 创建人id */
	private Long createUserId;

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
	 * 设置 角色名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置 角色代码
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设置 角色类型
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 设置 是否可用:1-可用;0-不可用
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
	 * 设置 创建人id
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
	 * 获取 角色名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取 角色代码
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取 角色类型
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 获取 是否可用:1-可用;0-不可用
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
	 * 获取 创建人id
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
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}