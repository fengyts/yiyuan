package ng.bayue.backend.dto;

import ng.bayue.backend.domain.SysUserDO;

public class SysUserDTO extends SysUserDO{

	private static final long serialVersionUID = 6095083779743776958L;

	private String roles;

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
