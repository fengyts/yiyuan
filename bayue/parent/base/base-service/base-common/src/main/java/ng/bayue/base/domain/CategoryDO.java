package ng.bayue.base.domain;

import java.util.Date;

import ng.bayue.util.BaseDO;

/**
 * 商品类别
 * 
 * @author fengyts Mon Jul 04 10:53:55 CST 2016
 */

public class CategoryDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 4544008733702664247L;

	/** 主键 */
	private Long id;

	/** 类别名称 */
	private String name;

	/** 类别编号 */
	private String code;

	/** 级别(1:大类、2:中类、3:小类) */
	private Integer level;

	/** 状态 0-无效 1-有效 */
	private Boolean status;

	/** 父类别 */
	private Long parentId;

	/** 路径(冗余)，以-连接 */
	private String path;

	/** 备注 */
	private String remark;

	/** 是否颜色管理：0-否、1-是，默认1 */
	private Integer colorAttributeSign;

	/** 是否尺码管理：0-否、1-是、默认1 */
	private Integer sizeAbbtributeSign;

	/** 创建时间 */
	private Date createTime;

	/** 创建人 */
	private Long createUserId;

	/** 修改时间 */
	private Date modifyTime;

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
	 * 设置 类别名称
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 设置 类别编号
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设置 级别(1:大类、2:中类、3:小类)
	 * 
	 * @param level
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * 设置 状态 0-无效 1-有效
	 * 
	 * @param status
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}

	/**
	 * 设置 父类别
	 * 
	 * @param parentId
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 设置 路径(冗余)，以-连接
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * 设置 是否颜色管理：0-否、1-是，默认1
	 * 
	 * @param colorAttributeSign
	 */
	public void setColorAttributeSign(Integer colorAttributeSign) {
		this.colorAttributeSign = colorAttributeSign;
	}

	/**
	 * 设置 是否尺码管理：0-否、1-是、默认1
	 * 
	 * @param sizeAbbtributeSign
	 */
	public void setSizeAbbtributeSign(Integer sizeAbbtributeSign) {
		this.sizeAbbtributeSign = sizeAbbtributeSign;
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
	 * 设置 创建人
	 * 
	 * @param createUserId
	 */
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
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
	 * 获取 类别名称
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 获取 类别编号
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取 级别(1:大类、2:中类、3:小类)
	 * 
	 * @return level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * 获取 状态 0-无效 1-有效
	 * 
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}

	/**
	 * 获取 父类别
	 * 
	 * @return parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 获取 路径(冗余)，以-连接
	 * 
	 * @return path
	 */
	public String getPath() {
		return path;
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
	 * 获取 是否颜色管理：0-否、1-是，默认1
	 * 
	 * @return colorAttributeSign
	 */
	public Integer getColorAttributeSign() {
		return colorAttributeSign;
	}

	/**
	 * 获取 是否尺码管理：0-否、1-是、默认1
	 * 
	 * @return sizeAbbtributeSign
	 */
	public Integer getSizeAbbtributeSign() {
		return sizeAbbtributeSign;
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
	 * 获取 创建人
	 * 
	 * @return createUserId
	 */
	public Long getCreateUserId() {
		return createUserId;
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
	 * 获取 修改人
	 * 
	 * @return modifyUserId
	 */
	public Long getModifyUserId() {
		return modifyUserId;
	}

}