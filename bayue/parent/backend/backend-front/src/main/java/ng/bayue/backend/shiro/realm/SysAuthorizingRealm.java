package ng.bayue.backend.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import ng.bayue.backend.ao.sysUser.SysUserAO;
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
		
		//用户不存在
		String userTempDB = "admin";
		if(!principal.equals(userTempDB)){
			return null;
		}

		// UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		// String principal1 = (String) userToken.getPrincipal();
		String credentials = "123456";

		SysUserDO sysUserDO = new SysUserDO();
		sysUserDO.setUserName(principal);
		sysUserDO.setId(1L);
		sysUserDO.setPassword(credentials);


		AuthenticationInfo authenticatinInfo = new SimpleAuthenticationInfo(sysUserDO, credentials,
				"sysAuthorizingRealm");
		return authenticatinInfo;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

}
