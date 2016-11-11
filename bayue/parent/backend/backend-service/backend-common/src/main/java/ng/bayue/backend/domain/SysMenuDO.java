package ng.bayue.backend.domain;


import java.util.Date;

import ng.bayue.backend.common.BaseDO;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SysMenuDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 7601848353598273965L;

	public static Integer MENU_TYPE_1 = 1;
	public static Integer MENU_TYPE_2 = 2;

	/** 主键 */
	private Long id;
	
	/***/
	private String code;

	/** 父菜单id，如果为空，则为根菜单 */
	private Long parentId;

	/** 菜单名称 */
	private String name;

	/** 菜单请求链接 */
	private String url;
	
	/** 菜单请求链接 */
	private String category;
	
	/** 菜单类型 1：菜单  2：按钮 */
	private Integer menuType;
	
	/** 菜单排序值 */
	private Integer sort;

	/** 创建人id */
	private Long createUserId;

	/** 修改人id */
	private Long modifyUserId;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/** 状态 */
	private Boolean status;
	
	/**  */
	private Long location;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("state", this.status)
				.append("startPage", this.getStartPage())
				.append("createUserId", this.createUserId)
				.append("menuType", this.menuType).append("code", this.code)
				.append("parentId", this.parentId)
				.append("location", this.location)
				.append("start", this.getStart())
				.append("category", this.category).append("url", this.url)
				.append("modifyUserId", this.modifyUserId)
				.append("createTime", this.createTime)
				.append("name", this.name)
				.append("pageSize", this.getPageSize())
				.append("modifyTime", this.modifyTime).append("id", this.id).append("sort", this.sort)
				.toString();
	}

}
