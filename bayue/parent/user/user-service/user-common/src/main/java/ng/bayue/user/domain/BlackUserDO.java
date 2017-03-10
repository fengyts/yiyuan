package ng.bayue.user.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 用户黑名单
 * 
 * @author fengyts Fri Mar 10 13:32:17 CST 2017
 */

public class BlackUserDO extends BaseDO {

	private static final long serialVersionUID = 310251443911016287L;

	/** 主键 */
	private Long id;

	/** 用户id */
	private Long userId;

	/** 冻结类型(1-时效，0-永久) */
	private Boolean freezeType;

	/** 黑名单类型(1-禁止登陆，0-禁止购买) */
	private Boolean blackType;

	/** 冻结原因 */
	private String reason;

	/** 冻结开始时间 */
	private Date beginTime;

	/** 冻结结束时间 */
	private Date endTime;

	/** 冻结人id */
	private Long createUserId;

	/** 修改人id */
	private Long modifyUserId;

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
	 * 设置 用户id
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置 冻结类型(1-时效，0-永久)
	 * 
	 * @param freezeType
	 */
	public void setFreezeType(Boolean freezeType) {
		this.freezeType = freezeType;
	}

	/**
	 * 设置 黑名单类型(1-禁止登陆，0-禁止购买)
	 * 
	 * @param blackType
	 */
	public void setBlackType(Boolean blackType) {
		this.blackType = blackType;
	}

	/**
	 * 设置 冻结原因
	 * 
	 * @param reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	/**
	 * 设置 冻结开始时间
	 * 
	 * @param beginTime
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	/**
	 * 设置 冻结结束时间
	 * 
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 设置 冻结人id
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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
	 * 获取 用户id
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 获取 冻结类型(1-时效，0-永久)
	 * 
	 * @return freezeType
	 */
	public Boolean getFreezeType() {
		return freezeType;
	}

	/**
	 * 获取 黑名单类型(1-禁止登陆，0-禁止购买)
	 * 
	 * @return blackType
	 */
	public Boolean getBlackType() {
		return blackType;
	}

	/**
	 * 获取 冻结原因
	 * 
	 * @return reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * 获取 冻结开始时间
	 * 
	 * @return beginTime
	 */
	public Date getBeginTime() {
		return beginTime;
	}

	/**
	 * 获取 冻结结束时间
	 * 
	 * @return endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 获取 冻结人id
	 * 
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
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