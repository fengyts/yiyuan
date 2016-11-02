package ng.bayue.backend.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import ng.bayue.backend.ao.sys.SysUserAO;
import ng.bayue.backend.domain.SysUserDO;

public class SysAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private SysUserAO sysUserAO;

	/**
	 * <pre>
	 * 身份认证
	 * </pre>
	 * 
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String principal = (String) token.getPrincipal();
//		SysUserDO sysUser = sysUserAO.findByLoginNameOrMobileOrEmail(principal);
		SysUserDO sysUser = new SysUserDO();
		sysUser.setId(1L);
		sysUser.setUserName("superadmin");
		sysUser.setLoginName("superadmin");
		sysUser.setPassword("123456");
		sysUser.setStatus(true);
		
		if(null == sysUser){//用户不存在
			throw new UnknownAccountException("用户不存在");
		}
		if(!sysUser.getStatus()){
			throw new LockedAccountException("该账户已锁定，请联系管理员");
		}

		 UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		 String credentials = (String) userToken.getCredentials();

		 if(!sysUser.getPassword().equals(credentials)){
			 throw new IncorrectCredentialsException("账号或者密码有误");
		 }

		AuthenticationInfo authenticatinInfo = new SimpleAuthenticationInfo(sysUser, credentials,
				"sysAuthorizingRealm");
		return authenticatinInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}
	
	

}
