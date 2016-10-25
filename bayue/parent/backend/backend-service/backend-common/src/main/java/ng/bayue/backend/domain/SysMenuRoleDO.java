package ng.bayue.backend.domain;

import ng.bayue.backend.common.BaseDO;

/**
 * 角色菜单关系
 * 
 * @author haisheng.long Tue Oct 25 13:31:48 CST 2016
 */

public class SysMenuRoleDO extends BaseDO {

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 4570724327564998502L;

	/** 主键 */
	private Long roleId;

	/** 主键 */
	private Long menuId;

	/**
	 * 设置 主键
	 * 
	 * @param roleId
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * 设置 主键
	 * 
	 * @param menuId
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取 主键
	 * 
	 * @return roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * 获取 主键
	 * 
	 * @return menuId
	 */
	public Long getMenuId() {
		return menuId;
	}

}