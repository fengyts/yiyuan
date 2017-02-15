package ng.bayue.base.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 攻略
 * 
 * @author fengyts Tue Jul 12 13:50:40 CST 2016
 */

public class StrategyDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 3772768202133944158L;

	/** 主键 */
	private Long id;

	/** 攻略模块(eg.抽奖|开奖|优惠买单...) */
	private String module;

	/** 级别(1:一级模块,2-具体攻略) */
	private Integer level;

	/** 标题 */
	private String title;

	/** 攻略内容 */
	private String content;

	/** 所属攻略模块id */
	private Long parentId;

	/** 状态-1：有效,0：无效 */
	private Boolean status;

	/** 备注 */
	private String remark;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/** 创建人 */
	private Long createUserId;

	/** 修改人 */
	private Long modifyUserId;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 攻略模块(eg.抽奖|开奖|优惠买单...)
	 * 
	 * @param module
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * 设置 级别(1:一级模块,2-具体攻略)
	 * 
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 设置 标题
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 设置 攻略内容
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置 所属攻略模块id
	 * 
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 设置 状态-1：有效,0：无效
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
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
	 * 设置 修改人
	 * 
	 * @param modifyUserId
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	 * 获取 攻略模块(eg.抽奖|开奖|优惠买单...)
	 * 
	 * @return module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * 获取 级别(1:一级模块,2-具体攻略)
	 * 
	 * @return level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 获取 标题
	 * 
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 获取 攻略内容
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 获取 所属攻略模块id
	 * 
	 * @return parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 获取 状态-1：有效,0：无效
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
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
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
	 * 获取 修改人
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

}