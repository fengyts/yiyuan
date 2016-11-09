package ng.bayue.backend.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import ng.bayue.backend.domain.SysUserDO;

@Component
public class UserHandler {

	public static SysUserDO getUser() {
		Subject subject = SecurityUtils.getSubject();
		SysUserDO userDO = (SysUserDO) subject.getPrincipal();
		if(null == userDO){
			userDO = new SysUserDO();
			userDO.setId(1L);
//		userDO.setLoginName("superadmin");
		}
		return userDO;
	}
	
	public static Subject getSubject(){
		return SecurityUtils.getSubject();
	}
	
	public static Session getSession(){
		return getSubject().getSession();
	}
	

}
