package ng.bayue.backend.domain;

import ng.bayue.backend.common.BaseDO;

/**
 * 系统用户角色关系
 * 
 * @author haisheng.long Tue Oct 25 13:31:48 CST 2016
 */

public class SysUserRoleDO extends BaseDO {

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = -8748139769626558396L;

	/** 主键 */
	private Long userId;

	/** 主键 */
	private Long roleId;

	/**
	 * 设置 主键
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置 主键
	 * 
	 * @param roleId
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 获取 主键
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 获取 主键
	 * 
	 * @return roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

}