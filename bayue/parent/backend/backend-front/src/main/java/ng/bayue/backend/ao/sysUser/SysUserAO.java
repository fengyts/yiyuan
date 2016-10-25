package ng.bayue.backend.ao.sysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.service.SysUserService;
import ng.bayue.util.Page;

@Service
public class SysUserAO {
	
	@Autowired
	private SysUserService sysUserService;
	
	public Page<SysUserDO> queryPage(SysUserDO sysUserDO,Integer pageNo,Integer pageSize){
		Page<SysUserDO> page = sysUserService.queryPageListBySysUserDOAndStartPageSize(sysUserDO, pageNo, pageSize);
		return page;
	}

}
