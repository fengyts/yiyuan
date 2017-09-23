package ng.bayue.snatch.domain.promotion;

import ng.bayue.annotation.GenerValidate;
import ng.bayue.common.BaseDO;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 活动专题
 * 
 * @author fengyts Mon Dec 26 16:28:48 CST 2016
 */

public class TopicDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = -9221476157373242657L;

	/** 主键 */
	private Long id;

	/** 专题名称 */
	@NotBlank(message="专题名称不能为空")
	@GenerValidate(message="专题名称不能为空")
	private String name;

	/** 是否测试专场：0-否;1-是 */
	private Boolean isTest;

	/** 专题类型:1-单品团;2-主题团;3-品牌团 */
	private Integer type;

	/** 参与活动所需积分 */
	private Integer needPoint;

	/** 积分抵扣额度 */
	private Double pointDeduction;

	/** 专题编号 */
	private Long number;

	/** 专题排序 */
	private Integer sort;

	/** 专题是否锁定排序位置：0-未锁定 1-锁定 */
	private Boolean sortLock;

	/** 专题状态 0-编辑中 1-审批中 2-已取消 3-审核通过 4-已驳回 5-终止 */
	private Integer status;

	/** 专题进度：0-未开始1-进行中 2已结束 */
	private Integer progress;

	/** 专题持续类型：0-长期有效 1-固定期限 */
	private Boolean durationType;

	/** 专题开始时间 */
	private Date startTime;

	/** 专题结束时间 */
	private Date endTime;

	/** 专题图片 */
	private String image;

	/** 专题描述 */
	private String description;

	/** 是否删除，逻辑删除 */
	private String isDeletion;

	/** 备注 */
	private String remark;

	/** 专题创建时间 */
	private Date createTime;

	/** 专题创建人id */
	private Long createUserId;

	/** 专题修改时间 */
	private Date modifyTime;

	/** 专题修改人id */
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
	 * 设置 专题名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置 是否测试专场：0-否;1-是
	 * 
	 * @param isTest
	 */
	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	/**
	 * 设置 专题类型:0-全部;1-单品团;2-主题团;3-品牌团
	 * 
	 * @param type
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置 参与活动所需积分
	 * 
	 * @param needPoint
	 */
	public void setNeedPoint(Integer needPoint) {
		this.needPoint = needPoint;
	}

	/**
	 * 设置 积分抵扣额度
	 * 
	 * @param pointDeduction
	 */
	public void setPointDeduction(Double pointDeduction) {
		this.pointDeduction = pointDeduction;
	}

	/**
	 * 设置 专题编号
	 * 
	 * @param number
	 */
	public void setNumber(Long number) {
		this.number = number;
	}

	/**
	 * 设置 专题排序
	 * 
	 * @param sort
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

	/**
	 * 设置 专题是否锁定排序位置：0-未锁定 1-锁定
	 * 
	 * @param sortLock
	 */
	public void setSortLock(Boolean sortLock) {
		this.sortLock = sortLock;
	}

	/**
	 * 设置 专题状态 0-编辑中 1-审批中 2-已取消 3-审核通过 4-已驳回 5-终止
	 * 
	 * @param status
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 设置 专题进度：0-未开始1-进行中 2已结束
	 * 
	 * @param progress
	 */
	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	/**
	 * 设置 专题持续类型：0-长期有效 1-固定期限
	 * 
	 * @param durationType
	 */
	public void setDurationType(Boolean durationType) {
		this.durationType = durationType;
	}

	/**
	 * 设置 专题开始时间
	 * 
	 * @param startTime
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 设置 专题结束时间
	 * 
	 * @param endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 设置 专题图片
	 * 
	 * @param image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 设置 专题描述
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 设置 是否删除，逻辑删除
	 * 
	 * @param isDeletion
	 */
	public void setIsDeletion(String isDeletion) {
		this.isDeletion = isDeletion;
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
	 * 设置 专题创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 专题创建人id
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	/**
	 * 设置 专题修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 设置 专题修改人id
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
	 * 获取 专题名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取 是否测试专场：0-否;1-是
	 * 
	 * @return isTest
	 */
	public Boolean getIsTest() {
		return isTest;
	}

	/**
	 * 获取 专题类型:0-全部;1-单品团;2-主题团;3-品牌团
	 * 
	 * @return type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 获取 参与活动所需积分
	 * 
	 * @return needPoint
	 */
	public Integer getNeedPoint() {
		return needPoint;
	}

	/**
	 * 获取 积分抵扣额度
	 * 
	 * @return pointDeduction
	 */
	public Double getPointDeduction() {
		return pointDeduction;
	}

	/**
	 * 获取 专题编号
	 * 
	 * @return number
	 */
	public Long getNumber() {
		return number;
	}

	/**
	 * 获取 专题排序
	 * 
	 * @return sort
	 */
	public Integer getSort() {
		return sort;
	}

	/**
	 * 获取 专题是否锁定排序位置：0-未锁定 1-锁定
	 * 
	 * @return sortLock
	 */
	public Boolean getSortLock() {
		return sortLock;
	}

	/**
	 * 获取 专题状态 0-编辑中 1-审批中 2-已取消 3-审核通过 4-已驳回 5-终止
	 * 
	 * @return status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 获取 专题进度：0-未开始1-进行中 2已结束
	 * 
	 * @return progress
	 */
	public Integer getProgress() {
		return progress;
	}

	/**
	 * 获取 专题持续类型：0-长期有效 1-固定期限
	 * 
	 * @return durationType
	 */
	public Boolean getDurationType() {
		return durationType;
	}

	/**
	 * 获取 专题开始时间
	 * 
	 * @return startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 获取 专题结束时间
	 * 
	 * @return endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 获取 专题图片
	 * 
	 * @return image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * 获取 专题描述
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取 是否删除，逻辑删除
	 * 
	 * @return isDeletion
	 */
	public String getIsDeletion() {
		return isDeletion;
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
	 * 获取 专题创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 专题创建人id
	 * 
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
	}

	/**
	 * 获取 专题修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 获取 专题修改人id
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

}