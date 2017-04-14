package ng.bayue.base.dto;

import java.io.Serializable;

public class FrontCategoryDTO implements Serializable {

	private static final long serialVersionUID = -804513517096690878L;

	/** 主键 */
	private Long id;

	/** 类目名称 */
	private String name;

	/** 类目编码 */
	private String code;

	/** 类目级别:1-一级类目,2-二级类目,3-三级类目 */
	private Integer level;

	/** 状态:0-有效，1-无效 */
	private Boolean status;

	/** 是否突出展示：0-是，1-否 */
	private Boolean isHighlight;

	/** 类目url地址 */
	private String logoUrl;

	/** 父类目ID */
	private Long parentId;

	/** 顺序 */
	private Integer sort;

	/** 是否发布:0-发布，1-否 */
	private Boolean isPublish;

	// ----------------- 前端分类链接属性 -------------------

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getIsHighlight() {
		return isHighlight;
	}

	public void setIsHighlight(Boolean isHighlight) {
		this.isHighlight = isHighlight;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getIsPublish() {
		return isPublish;
	}

	public void setIsPublish(Boolean isPublish) {
		this.isPublish = isPublish;
	}

	public Long getFrontCategoryId() {
		return frontCategoryId;
	}

	public void setFrontCategoryId(Long frontCategoryId) {
		this.frontCategoryId = frontCategoryId;
	}

	public Integer getLinkType() {
		return linkType;
	}

	public void setLinkType(Integer linkType) {
		this.linkType = linkType;
	}

	public String getLinkContent() {
		return linkContent;
	}

	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}

	public String getLargeCategoryIds() {
		return largeCategoryIds;
	}

	public void setLargeCategoryIds(String largeCategoryIds) {
		this.largeCategoryIds = largeCategoryIds;
	}

	public String getSmallCategoryIds() {
		return smallCategoryIds;
	}

	public void setSmallCategoryIds(String smallCategoryIds) {
		this.smallCategoryIds = smallCategoryIds;
	}

	public String getLinkUrlPc() {
		return linkUrlPc;
	}

	public void setLinkUrlPc(String linkUrlPc) {
		this.linkUrlPc = linkUrlPc;
	}

	public String getLinkUrlApp() {
		return linkUrlApp;
	}

	public void setLinkUrlApp(String linkUrlApp) {
		this.linkUrlApp = linkUrlApp;
	}

	public String getLinkUrlWap() {
		return linkUrlWap;
	}

	public void setLinkUrlWap(String linkUrlWap) {
		this.linkUrlWap = linkUrlWap;
	}

}
