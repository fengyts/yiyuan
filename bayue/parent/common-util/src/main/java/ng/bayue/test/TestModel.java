package ng.bayue.test;

import java.util.Date;
import java.util.List;
import java.util.Map;

import ng.bayue.annotation.GenerValidate;
import ng.bayue.annotation.ValidateType;

public class TestModel {

	private Long id;
	@GenerValidate(type = {ValidateType.NOTEMPTY,ValidateType.NUMERIC},message= "必须是数字")
	private String name;
	@GenerValidate(type = {ValidateType.NOTBLANK},message = "age不能为空")
	private Integer age;
	private Boolean flag;
//	@GenerValidate(type = ValidateType.NOTEMPTY,message="列表不能为空")
	private List<String> list;
	
//	@GenerValidate(type = ValidateType.NOTEMPTY,message="列表不能为空")
	private Map<String,Integer> map;
	
	@GenerValidate(type = ValidateType.NOTBLANK,message = "createTime不能为空")
	private Date createTime;
	

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	public void setMap(Map<String, Integer> map) {
		this.map = map;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
