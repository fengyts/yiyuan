package ng.bayue.entity;

import java.io.Serializable;
import java.util.List;

public class LearnAcceptEntity implements Serializable{
	
	/**  */
	private static final long serialVersionUID = 4764465891634637431L;
	
	private Long id;
	private String name;
	private String mobile;
	
	List<String> params;

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<String> getParams() {
		return params;
	}

	public void setParams(List<String> params) {
		this.params = params;
	}
	
	

}
