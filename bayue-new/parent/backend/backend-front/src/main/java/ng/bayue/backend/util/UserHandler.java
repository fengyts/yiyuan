package ng.bayue.backend.util;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.dto.SysUserVO;

@Component
public class UserHandler {

	private static SessionDAO sessionDAO;

	@Resource
	public void setSessionDAO(SessionDAO sessionDAO) {
		this.sessionDAO = sessionDAO;
	}

	public static SysUserDO getUser() {
		Subject subject = SecurityUtils.getSubject();
		SysUserDO userDO = (SysUserDO) subject.getPrincipal();
		if (null == userDO) {
			userDO = new SysUserDO();
			userDO.setId(1L);
			// userDO.setLoginName("superadmin");
		}
		return userDO;
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static Session getSession() {
		return getSubject().getSession();
	}

	public static Collection<Session> getActiveSession() {
		Collection<Session> collection = sessionDAO.getActiveSessions();
		return collection;
	}

	public static Collection<Session> getSessionByPrincipal(Object principal) {
		Collection<Session> collection = getActiveSession();
		if (collection.isEmpty()) {
			return null;
		}
		Collection<Session> results = Collections.emptyList();
		for (Session session : collection) {
			if (principal instanceof String && session.getAttribute(principal) != null) {
				results.add(session);
			}
			if (principal instanceof SysUserVO) {
				SysUserVO sysUser = (SysUserVO) principal;
				if (null != session.getAttribute(sysUser.getLoginName())) {
					results.add(session);
				}
			}
			if (principal instanceof SysUserDO) {
				SysUserDO sysUser = (SysUserDO) principal;
				if (null != session.getAttribute(sysUser.getLoginName())) {
					results.add(session);
				}
			}
		}
		return results;
	}

}


