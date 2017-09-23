package ng.bayue.backend.domain;

import java.util.Date;

import ng.bayue.backend.common.BaseDO;

/**
 * 后台系统用户
 * 
 * @author haisheng.long Tue Oct 25 13:31:48 CST 2016
 */

public class SysUserDO extends BaseDO {

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 7991724439049430418L;

	/** 主键 */
	private Long id;

	/** 登录名 */
	private String loginName;

	/** 用户名 */
	private String userName;

	/** 密码 */
	private String password;

	/** 登录盐 */
	private String salt;

	/** email */
	private String email;

	/** 手机号 */
	private String mobile;

	/** 创建人id */
	private Long createUserId;

	/** 创建时间 */
	private Date createTime;

	/** 修改人id */
	private Long modifyUserId;

	/** 修改时间 */
	private Date modifyTime;

	/** 最后登录时间 */
	private Date lastLoginTime;

	/** 最后登录ip */
	private String lastLoginIp;

	/** 用户状态:1-有效;0-无效 */
	private Boolean status;

	/** 用户扩展信息 */
	private String userDetail;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 登录名
	 * 
	 * @param loginName
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * 设置 用户名
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 设置 密码
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置 登录盐
	 * 
	 * @param salt
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * 设置 email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 设置 手机号
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	 * 设置 修改人id
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
	 * 设置 最后登录时间
	 * 
	 * @param lastLoginTime
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 设置 最后登录ip
	 * 
	 * @param lastLoginIp
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 设置 用户状态:1-有效;0-无效
	 * 
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * 设置 用户扩展信息
	 * 
	 * @param userDetail
	 */
	public void setUserDetail(String userDetail) {
		this.userDetail = userDetail;
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
	 * 获取 登录名
	 * 
	 * @return loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * 获取 用户名
	 * 
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 获取 密码
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 获取 登录盐
	 * 
	 * @return salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * 获取 email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 获取 手机号
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
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
	 * 获取 修改人id
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

	/**
	 * 获取 最后登录时间
	 * 
	 * @return lastLoginTime
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 获取 最后登录ip
	 * 
	 * @return lastLoginIp
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * 获取 用户状态:1-有效;0-无效
	 * 
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * 获取 用户扩展信息
	 * 
	 * @return userDetail
	 */
	public String getUserDetail() {
		return userDetail;
	}

}