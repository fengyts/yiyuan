package ng.bayue.base.dto;

import java.io.Serializable;
import java.util.Date;

public class SpecDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -801555373907025066L;

	/** 规格id */
	private Long id;

	/** 规格编号 */
	private String code;

	/** 规格 */
	private String spec;

	/** 规格排序 */
	private Integer sort;

	/** 默认1有效,0无效(状态) */
	private Boolean status;

	/** 规格备注 */
	private String remark;

	// --------------------------------------------

	/** 规格组id */
	private Long idSpecGroup;

	/** 规格组编号 */
	private Integer codeSpecGroup;

	/** 规格组名称(前端展示) */
	private String name;

	/** 规格组别名 */
	private String aliasName;

	/** 1为有效 ,0为无效 */
	private Boolean statusSpecGroup;

	/** 备注 */
	private String remarkSpecGroup;
	
	/** 规格组创建时间 */
	private Date createTime;
	
	/**  规格组修改时间 */
	private Date modifyTime;

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

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getIdSpecGroup() {
		return idSpecGroup;
	}

	public void setIdSpecGroup(Long idSpecGroup) {
		this.idSpecGroup = idSpecGroup;
	}

	public Integer getCodeSpecGroup() {
		return codeSpecGroup;
	}

	public void setCodeSpecGroup(Integer codeSpecGroup) {
		this.codeSpecGroup = codeSpecGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public Boolean getStatusSpecGroup() {
		return statusSpecGroup;
	}

	public void setStatusSpecGroup(Boolean statusSpecGroup) {
		this.statusSpecGroup = statusSpecGroup;
	}

	public String getRemarkSpecGroup() {
		return remarkSpecGroup;
	}

	public void setRemarkSpecGroup(String remarkSpecGroup) {
		this.remarkSpecGroup = remarkSpecGroup;
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

}
