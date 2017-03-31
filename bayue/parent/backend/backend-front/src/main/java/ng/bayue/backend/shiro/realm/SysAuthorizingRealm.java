package ng.bayue.backend.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import ng.bayue.backend.ao.sys.SysUserAO;
import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.dto.SysUserVO;
import ng.bayue.backend.util.UserHandler;

public class SysAuthorizingRealm extends AuthorizingRealm {

	@Autowired
	private SysUserAO sysUserAO;
	
	@Override
	public String getName() {
		return super.getName();
	}

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
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String principal = (String) token.getPrincipal();
//		SysUserDO sysUser = sysUserAO.findByLoginNameOrMobileOrEmail(principal);
		SysUserVO sysUserVo = sysUserAO.findByAccountContainsMenusAndRoles(principal);

		if (null == sysUserVo) {// 用户不存在
			throw new UnknownAccountException("用户不存在");
		}
		if (!sysUserVo.getStatus()) {
			throw new LockedAccountException("该账户已锁定，请联系管理员");
		}

		AuthenticationInfo info = new SimpleAuthenticationInfo(
				sysUserVo, sysUserVo.getPassword(),ByteSource.Util.bytes(sysUserVo.getSalt()), getName());
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	protected void clearCache() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
	
	public void onLogout(PrincipalCollection principals)  
    { 
		clearCache();
    }
	
	

}
