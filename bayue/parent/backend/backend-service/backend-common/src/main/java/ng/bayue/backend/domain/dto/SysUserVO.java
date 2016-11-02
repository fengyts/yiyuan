package ng.bayue.backend.domain.dto;

import java.util.List;

import ng.bayue.backend.domain.SysUserDO;

public class SysUserVO extends SysUserDO {

	private static final long serialVersionUID = 4165829077032805591L;
	
	List<String> roles;

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
