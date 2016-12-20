package ng.bayue.item.domain.dto;

import java.io.Serializable;

public class DetailSpecDTO implements Serializable{

	/**  */
	private static final long serialVersionUID = 6341627240990326546L;
	
	/** 商品规格关联信息表主键 */
	private Long id;
	
	/** prid纬度 detailid */
	private Long detailId;

	/** 规格所属规格组 */
	private Long specGroupId;

	/** 当前规格组排列顺序 */
	private Integer sort;
	
	private String groupName;
	
	private String groupNameAlias;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public Long getSpecGroupId() {
		return specGroupId;
	}

	public void setSpecGroupId(Long specGroupId) {
		this.specGroupId = specGroupId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupNameAlias() {
		return groupNameAlias;
	}

	public void setGroupNameAlias(String groupNameAlias) {
		this.groupNameAlias = groupNameAlias;
	}
	
	

}
