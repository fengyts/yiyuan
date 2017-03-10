package ng.bayue.user.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 用户基本信息
 * 
 * @author fengyts Fri Mar 10 13:32:18 CST 2017
 */

public class UserDO extends BaseDO {

	private static final long serialVersionUID = -8088599702694445914L;

	/** 主键 */
	private Long id;

	/** 会员id,用户唯一标识，显示给用户 */
	private String memberId;

	/** 昵称 */
	private String nickname;

	/** 登录盐 */
	private String salt;

	/** 登陆密码 */
	private String password;

	/** 手机号 */
	private String mobile;

	/** email */
	private String email;

	/** 性别,男-1,女-0 */
	private Boolean sex;

	/** 手机是否验证 */
	private Boolean mobileVarified;

	/** 推荐人id */
	private Long referrerId;

	/** 是否推荐人 */
	private Boolean isReferrer;

	/** 推荐的用户总数量 */
	private Integer referralCount;

	/**  */
	private Date createTime;

	/**  */
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
	 * 设置 会员id,用户唯一标识，显示给用户
	 * 
	 * @param memberId
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * 设置 昵称
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
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
	 * 设置 登陆密码
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
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
	 * 设置 email
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 设置 性别,男-1,女-0
	 * 
	 * @param sex
	 */
	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	/**
	 * 设置 手机是否验证
	 * 
	 * @param mobileVarified
	 */
	public void setMobileVarified(Boolean mobileVarified) {
		this.mobileVarified = mobileVarified;
	}

	/**
	 * 设置 推荐人id
	 * 
	 * @param referrerId
	 */
	public void setReferrerId(Long referrerId) {
		this.referrerId = referrerId;
	}

	/**
	 * 设置 是否推荐人
	 * 
	 * @param isReferrer
	 */
	public void setIsReferrer(Boolean isReferrer) {
		this.isReferrer = isReferrer;
	}

	/**
	 * 设置 推荐的用户总数量
	 * 
	 * @param referralCount
	 */
	public void setReferralCount(Integer referralCount) {
		this.referralCount = referralCount;
	}

	/**
	 * 设置
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置
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
	 * 获取 会员id,用户唯一标识，显示给用户
	 * 
	 * @return memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * 获取 昵称
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
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
	 * 获取 登陆密码
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
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
	 * 获取 email
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 获取 性别,男-1,女-0
	 * 
	 * @return sex
	 */
	public Boolean getSex() {
		return sex;
	}

	/**
	 * 获取 手机是否验证
	 * 
	 * @return mobileVarified
	 */
	public Boolean getMobileVarified() {
		return mobileVarified;
	}

	/**
	 * 获取 推荐人id
	 * 
	 * @return referrerId
	 */
	public Long getReferrerId() {
		return referrerId;
	}

	/**
	 * 获取 是否推荐人
	 * 
	 * @return isReferrer
	 */
	public Boolean getIsReferrer() {
		return isReferrer;
	}

	/**
	 * 获取 推荐的用户总数量
	 * 
	 * @return referralCount
	 */
	public Integer getReferralCount() {
		return referralCount;
	}

	/**
	 * 获取
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}