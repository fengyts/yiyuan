package ng.bayue.backend.controller.sysUser;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ng.bayue.backend.ao.index.SysMenuAO;
import ng.bayue.backend.ao.sysUser.SysRoleAO;
import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.util.HanyuPinyinUtil;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.util.Page;

@Controller
@RequestMapping({ "/sys/sysRole" })
public class SysRoleController {

	@Autowired
	private SysRoleAO sysRoleAO;
	@Autowired
	private SysMenuAO sysMenuAO;

	@RequestMapping({ "/list" })
	public void sysRoleList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, SysRoleDO sysRoleDO) {
		Page<SysRoleDO> page = sysRoleAO.queryPage(sysRoleDO, pageNo, pageSize);
		model.addAttribute("page", page);
	}

	@RequestMapping({ "/add" })
	public void sysRoleAdd(Model model) {
//		List<SysMenuDO> sysMenus = sysMenuAO.listAllMenus();
//		model.addAttribute("sysMenus", sysMenus);
	}

	@RequestMapping({ "/save" })
	@ResponseBody
	public ResultMessage sysRoleSave(SysRoleDO sysRoleDO,String menuIds) {
		String name = sysRoleDO.getName();
		if (StringUtils.isEmpty(name)) {
			return ResultMessage.validParameterNull("name");
		}
		String code = sysRoleDO.getCode();
		if (StringUtils.isEmpty(code)) {
			String codeHanyu = HanyuPinyinUtil.hanyuToPinyin(name);
			if (StringUtils.isEmpty(codeHanyu)) {
//				return ResultMessage.serverInnerError();
				sysRoleDO.setCode(code);
			}
			sysRoleDO.setCode(codeHanyu);
		}
		return sysRoleAO.saveSysRole(sysRoleDO, menuIds);
	}

	@RequestMapping({ "/edit" })
	public void sysRoleEdit(Model model, Long id) {
		model.addAttribute("sysRole", sysRoleAO.selectById(id));
	}

	@RequestMapping({ "/update" })
	@ResponseBody
	public ResultMessage sysRoleUpdate(SysRoleDO sysRoleDO) {
		String name = sysRoleDO.getName();
		if (StringUtils.isEmpty(name)) {
			return ResultMessage.validParameterNull("name");
		}
		if (StringUtils.isEmpty(sysRoleDO.getCode())) {
			String code = HanyuPinyinUtil.hanyuToPinyin(name);
			if (StringUtils.isEmpty(code)) {
				return ResultMessage.serverInnerError();
			}
			sysRoleDO.setCode(code);
		}
		return sysRoleAO.updateSysRole(sysRoleDO);
		
	}

}
