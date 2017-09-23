package ng.bayue.base.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 前台类目跳转链接
 * 
 * @author fengyts Wed Apr 12 11:03:06 CST 2017
 */

public class FrontCategoryLinkDO extends BaseDO {

	private static final long serialVersionUID = -4882199157351575562L;

	/** 主键 */
	private Long id;

	/** 前台类目id */
	private Long frontCategoryId;

	/** 跳转方式:1-后台分类,2-固定页面,3-商品,4-品牌,5-搜索词 */
	private Integer linkType;

	/**  */
	private String linkContent;

	/** 后台大类ids */
	private String largeCategoryIds;

	/** 后台小类ids */
	private String smallCategoryIds;

	/**  */
	private String linkUrlPc;

	/**  */
	private String linkUrlApp;

	/**  */
	private String linkUrlWap;

	/** 状态:0-正常，1-删除 */
	private Boolean status;

	/**  */
	private Long createUserId;

	/**  */
	private Date createTime;

	/**  */
	private Long modifyUserId;

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
	 * 设置 前台类目id
	 * 
	 * @param frontCategoryId
	 */
	public void setFrontCategoryId(Long frontCategoryId) {
		this.frontCategoryId = frontCategoryId;
	}

	/**
	 * 设置 跳转方式:1-后台分类,2-固定页面,3-商品,4-品牌,5-搜索词
	 * 
	 * @param linkType
	 */
	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	/**
	 * 设置
	 * 
	 * @param linkContent
	 */
	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}

	/**
	 * 设置 后台大类ids
	 * 
	 * @param largeCategoryIds
	 */
	public void setLargeCategoryIds(String largeCategoryIds) {
		this.largeCategoryIds = largeCategoryIds;
	}

	/**
	 * 设置 后台小类ids
	 * 
	 * @param smallCategoryIds
	 */
	public void setSmallCategoryIds(String smallCategoryIds) {
		this.smallCategoryIds = smallCategoryIds;
	}

	/**
	 * 设置
	 * 
	 * @param linkUrlPc
	 */
	public void setLinkUrlPc(String linkUrlPc) {
		this.linkUrlPc = linkUrlPc;
	}

	/**
	 * 设置
	 * 
	 * @param linkUrlApp
	 */
	public void setLinkUrlApp(String linkUrlApp) {
		this.linkUrlApp = linkUrlApp;
	}

	/**
	 * 设置
	 * 
	 * @param linkUrlWap
	 */
	public void setLinkUrlWap(String linkUrlWap) {
		this.linkUrlWap = linkUrlWap;
	}

	/**
	 * 设置 状态:0-正常，1-删除
	 * 
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * 设置
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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
	 * @param modifyUserId
	 */
	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
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
	 * 获取 前台类目id
	 * 
	 * @return frontCategoryId
	 */
	public Long getFrontCategoryId() {
		return frontCategoryId;
	}

	/**
	 * 获取 跳转方式:1-后台分类,2-固定页面,3-商品,4-品牌,5-搜索词
	 * 
	 * @return linkType
	 */
	public Integer getLinkType() {
		return linkType;
	}

	/**
	 * 获取
	 * 
	 * @return linkContent
	 */
	public String getLinkContent() {
		return linkContent;
	}

	/**
	 * 获取 后台大类ids
	 * 
	 * @return largeCategoryIds
	 */
	public String getLargeCategoryIds() {
		return largeCategoryIds;
	}

	/**
	 * 获取 后台小类ids
	 * 
	 * @return smallCategoryIds
	 */
	public String getSmallCategoryIds() {
		return smallCategoryIds;
	}

	/**
	 * 获取
	 * 
	 * @return linkUrlPc
	 */
	public String getLinkUrlPc() {
		return linkUrlPc;
	}

	/**
	 * 获取
	 * 
	 * @return linkUrlApp
	 */
	public String getLinkUrlApp() {
		return linkUrlApp;
	}

	/**
	 * 获取
	 * 
	 * @return linkUrlWap
	 */
	public String getLinkUrlWap() {
		return linkUrlWap;
	}

	/**
	 * 获取 状态:0-正常，1-删除
	 * 
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * 获取
	 * 
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
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
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
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