package ng.bayue.backend.controller.sysUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ng.bayue.backend.ao.sysUser.SysRoleAO;
import ng.bayue.backend.ao.sysUser.SysUserAO;
import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.util.Page;

@Controller
@RequestMapping({"/sysUser"})
public class SysUserController {
	
	@Autowired
	private SysUserAO sysUserAO;
	@Autowired
	private SysRoleAO sysRoleAO;
	
	@RequestMapping({"/list"})
	public void sysUserList(Model model,@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize,
			SysUserDO sysUserDO){
		Page<SysUserDO> page = sysUserAO.queryPage(sysUserDO, pageNo, pageSize);
		model.addAttribute("page", page);
	}
	
	@RequestMapping({"/add"})
	public void sysUserAdd(Model model){
		List<SysRoleDO> roles = sysRoleAO.selectAllRole();
		model.addAttribute("roles", roles);
	}

}
