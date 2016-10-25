package ng.bayue.backend.ao.sysUser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.service.SysRoleService;

@Service
public class SysRoleAO {

	private static final Logger logger = LoggerFactory.getLogger(SysRoleAO.class);
	
	@Autowired
	private SysRoleService sysRoleService;
	
	public List<SysRoleDO> selectAllRole(){
		List<SysRoleDO> list = sysRoleService.selectDynamic(new SysRoleDO());
		return list;
	}
	
	
}
