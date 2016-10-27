package ng.bayue.backend.ao.sys;

import java.util.List;

import ng.bayue.backend.domain.SysMenuRoleDO;
import ng.bayue.backend.service.SysMenuRoleService;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysMenuRoleAO {

	private static final Logger logger = LoggerFactory.getLogger(SysMenuRoleAO.class);

	@Autowired
	private SysMenuRoleService sysMenuRoleService;

	public String selectMenuIdsByRoleId(Long roleId) {
		List<SysMenuRoleDO> list = sysMenuRoleService.selectByRoleId(roleId);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		String menuIds = "";
		for (SysMenuRoleDO smr : list) {
			menuIds += smr.getMenuId() + ",";
		}
		return menuIds.substring(0, menuIds.length() - 1);
	}
	

}
