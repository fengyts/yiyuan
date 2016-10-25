package ng.bayue.backend.util;

import org.springframework.stereotype.Component;

import ng.bayue.backend.domain.SysUserDO;

@Component
public class UserHandler {

	public static SysUserDO getUser() {
		SysUserDO userDO = new SysUserDO();
		userDO.setId(1L);
		return userDO;
	}

}
