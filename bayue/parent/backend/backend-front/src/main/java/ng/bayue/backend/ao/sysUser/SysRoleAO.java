package ng.bayue.backend.ao.sysUser;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.service.SysRoleService;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.util.Page;

@Service
public class SysRoleAO {

	private static final Logger logger = LoggerFactory.getLogger(SysRoleAO.class);

	@Autowired
	private SysRoleService sysRoleService;

	public Page<SysRoleDO> queryPage(SysRoleDO sysRoleDO, Integer pageNo, Integer pageSize) {
		Page<SysRoleDO> page = sysRoleService.queryPageListBySysRoleDOAndStartPageSize(sysRoleDO,
				pageNo, pageSize);
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

	public List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO) {
		return sysRoleService.selectDynamic(sysRoleDO);
	}

	/**
	 * <pre>
	 * 校验角色是否存在
	 * </pre>
	 *
	 * @param sysRoleDO
	 * @return
	 */
	public boolean isExist(SysRoleDO sysRoleDO) {
		List<SysRoleDO> list = selectDynamic(sysRoleDO);
		return null == list || 0 == list.size() ? false : true;
	}

	public ResultMessage saveSysRole(SysRoleDO sysRoleDO) {
		if(isExist(sysRoleDO)){
			return ResultMessage.validIsExist();
		}
		sysRoleDO.setCreateTime(new Date());
		sysRoleDO.setModifyTime(new Date());
		sysRoleDO.setCreateUserId(UserHandler.getUser().getId());
		Long id = sysRoleService.insert(sysRoleDO);
		return null == id || 1 > id ? ResultMessage.serverInnerError() : new ResultMessage();
	}

	public ResultMessage updateSysRole(SysRoleDO sysRoleDO) {
		SysRoleDO sysRoleDOValid = new SysRoleDO();
		sysRoleDOValid.setId(sysRoleDO.getId());
		sysRoleDOValid.setName(sysRoleDO.getName());
		sysRoleDOValid.setCode(sysRoleDO.getCode());
		if(isExist(sysRoleDOValid)){
			return ResultMessage.validIsExist();
		}
		sysRoleDO.setModifyTime(new Date());
		sysRoleService.update(sysRoleDO, false);
		return new ResultMessage();
	}

}
