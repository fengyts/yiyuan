package ng.bayue.backend.ao.sysUser;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.service.SysRoleService;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.util.Page;

@Service
public class SysRoleAO {

	private static final Logger logger = LoggerFactory.getLogger(SysRoleAO.class);

	@Autowired
	private SysRoleService sysRoleService;

	public Page<SysRoleDO> queryPage(SysRoleDO sysRoleDO, Integer pageNo, Integer pageSize) {
		Page<SysRoleDO> page = sysRoleService.queryPageListBySysRoleDOAndStartPageSize(sysRoleDO, pageNo, pageSize);
		return page;
	}

	public List<SysRoleDO> selectAllRole() {
		List<SysRoleDO> list = sysRoleService.selectDynamic(new SysRoleDO());
		return list;
	}

	public SysRoleDO selectById(Long id) {
		if (null == id) {
			return null;
		}
		SysRoleDO sysRoleDO = sysRoleService.selectById(id);
		return sysRoleDO;
	}

	public void saveSysRole(SysRoleDO sysRoleDO) {
		sysRoleDO.setCreateTime(new Date());
		sysRoleDO.setModifyTime(new Date());
		sysRoleDO.setCreateUserId(UserHandler.getUser().getId());
		sysRoleService.insert(sysRoleDO);
	}

	public void updateSysRole(SysRoleDO sysRoleDO) {
		sysRoleDO.setModifyTime(new Date());
		sysRoleService.update(sysRoleDO, false);
	}

}
