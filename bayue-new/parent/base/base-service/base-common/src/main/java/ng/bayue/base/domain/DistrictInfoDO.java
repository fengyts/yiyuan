package ng.bayue.base.domain;

import java.util.Date;

import ng.bayue.common.BaseDO;

/**
 * 地区信息
 * 
 * @author fengyts Tue Jul 12 20:29:58 CST 2016
 */

public class DistrictInfoDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 1784343641058685566L;

	/** 主键 */
	private Long id;

	/** 地区名称 */
	private String name;

	/** 中文拼音 */
	private String spelling;

	/** 中文拼音简称 */
	private String simpleSpelling;

	/** 父级ID */
	private Integer parentId;

	/** 层级 0-顶级 1-大洲 2-国家 3-政区 4-省 5-市 6-区 7-街道 */
	private Boolean type;

	/** 是否有效：0-无效、1-有效，默认为1 */
	private Boolean status;

	/**  */
	private String pathUrl;

	/** 排序 */
	private Integer sortNo;

	/** 创建时间 */
	private Date createTime;

	/** 更新时间 */
	private Date modifyTime;

	/** 国标编码 */
	private String nationalCode;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 地区名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置 中文拼音
	 * 
	 * @param spelling
	 */
	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}

	/**
	 * 设置 中文拼音简称
	 * 
	 * @param simpleSpelling
	 */
	public void setSimpleSpelling(String simpleSpelling) {
		this.simpleSpelling = simpleSpelling;
	}

	/**
	 * 设置 父级ID
	 * 
	 * @param parentId
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * 设置 层级 0-顶级 1-大洲 2-国家 3-政区 4-省 5-市 6-区 7-街道
	 * 
	 * @param type
	 */
	public void setType(Boolean type) {
		this.type = type;
	}

	/**
	 * 设置 是否有效：0-无效、1-有效，默认为1
	 * 
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * 设置
	 * 
	 * @param pathUrl
	 */
	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}

	/**
	 * 设置 排序
	 * 
	 * @param sortNo
	 */
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
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
	 * 设置 国标编码
	 * 
	 * @param nationalCode
	 */
	public void setNationalCode(String nationalCode) {
		this.nationalCode = nationalCode;
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
	 * 获取 地区名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取 中文拼音
	 * 
	 * @return spelling
	 */
	public String getSpelling() {
		return spelling;
	}

	/**
	 * 获取 中文拼音简称
	 * 
	 * @return simpleSpelling
	 */
	public String getSimpleSpelling() {
		return simpleSpelling;
	}

	/**
	 * 获取 父级ID
	 * 
	 * @return parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * 获取 层级 0-顶级 1-大洲 2-国家 3-政区 4-省 5-市 6-区 7-街道
	 * 
	 * @return type
	 */
	public Boolean getType() {
		return type;
	}

	/**
	 * 获取 是否有效：0-无效、1-有效，默认为1
	 * 
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * 获取
	 * 
	 * @return pathUrl
	 */
	public String getPathUrl() {
		return pathUrl;
	}

	/**
	 * 获取 排序
	 * 
	 * @return sortNo
	 */
	public Integer getSortNo() {
		return sortNo;
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

	/**
	 * 获取 国标编码
	 * 
	 * @return nationalCode
	 */
	public String getNationalCode() {
		return nationalCode;
	}

}