package ng.bayue.backend.ao.sys;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

	public ResultMessage saveSysRole(SysRoleDO sysRoleDO, String menuIds) {
		if(isExist(sysRoleDO)){
			return ResultMessage.validIsExist();
		}
		sysRoleDO.setCreateTime(new Date());
		sysRoleDO.setModifyTime(new Date());
		sysRoleDO.setCreateUserId(UserHandler.getUser().getId());
		
		if(StringUtils.isEmpty(menuIds)){
			Long id = sysRoleService.insert(sysRoleDO);
			return null == id || 1 > id ? ResultMessage.serverInnerError() : new ResultMessage();
		}
		
		List<Long> listMenuIds = new ArrayList<Long>();
		String[] menuIdsStr = menuIds.split(",");
		for(String str : menuIdsStr){
			listMenuIds.add(Long.valueOf(str.trim()));
		}
		sysRoleService.insertSysRoleAndRoleMenuRelation(sysRoleDO, listMenuIds);
		
		return new ResultMessage();
	}

	public ResultMessage updateSysRole(SysRoleDO sysRoleDO, String menuIds) {
		SysRoleDO sysRoleDOValid = new SysRoleDO();
		Long roleId = sysRoleDO.getId();
		sysRoleDOValid.setId(roleId);
		sysRoleDOValid.setName(sysRoleDO.getName());
		sysRoleDOValid.setCode(sysRoleDO.getCode());
		
//		if(isExist(sysRoleDOValid)){
//			return ResultMessage.validIsExist();
//		}
		sysRoleDO.setModifyTime(new Date());
		
		if(StringUtils.isEmpty(menuIds)){
			sysRoleService.update(sysRoleDO, false);
		}else{
			List<Long> ids = new ArrayList<Long>();
			String[] arr = menuIds.split(",");
			for(String str : arr){
				ids.add(Long.valueOf(str));
			}
			sysRoleService.updateSysRoleAndRoleMenuRelation(sysRoleDO, roleId, ids);
		}
		return new ResultMessage();
	}

}
