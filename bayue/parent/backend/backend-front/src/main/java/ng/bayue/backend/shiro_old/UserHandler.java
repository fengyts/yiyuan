package ng.bayue.backend.shiro_old;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import ng.bayue.backend.domain.SysUserDO;

public class UserHandler {

	public SysUserDO getUser() {
		Subject subject = SecurityUtils.getSubject();
		SysUserDO sysUserDO = (SysUserDO) subject.getPrincipal();
		return sysUserDO;
	}

}
