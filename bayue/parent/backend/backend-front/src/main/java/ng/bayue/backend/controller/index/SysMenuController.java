package ng.bayue.backend.controller.index;

import java.util.List;

import ng.bayue.backend.ao.index.SysMenuAO;
import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.enums.SysMenuTypeEnum;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/index/sysMenu")
public class SysMenuController {

	@Autowired
	private SysMenuAO sysMenuAO;

	@RequestMapping({ "/listHome" })
	public String listHomeMenu(Model model, SysMenuDO sysMenuDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue= "10")Integer pageSize) {
		
		Page<SysMenuDO> page = sysMenuAO.pageQueryList(sysMenuDO, pageNo, pageSize);
		
		model.addAttribute("page", page);
		model.addAttribute("sysMenuDO",sysMenuDO);
		
		model.addAttribute("sysMenuTypes", SysMenuTypeEnum.values());
		
		return "/index/sysMenu/homeMenu";
	}
	
	@RequestMapping({"/add"})
	public void add(Model model){
		SysMenuDO sysMenuDO = new SysMenuDO();
		sysMenuDO.setMenuType(0);
		List<SysMenuDO> list = sysMenuAO.listBeforeTwoMenu();
		
		model.addAttribute("parentMenus", list);
	}
	
	@RequestMapping({"/save"})
	@ResponseBody
	public ResultMessage addMenu(SysMenuDO sysMenuDO) {
		if (null == sysMenuDO) {
			return null;
		}
		ResultMessage message = sysMenuAO.addSysMenu(sysMenuDO);

		return message;
	}
	
	@RequestMapping({"/edit"})
	public void edit(Model model,Long id){
		SysMenuDO sysMenuDO = sysMenuAO.selectById(id);
		List<SysMenuDO> list = sysMenuAO.listBeforeTwoMenu();
		model.addAttribute("sysMenuDO", sysMenuDO);
		model.addAttribute("parentMenus", list);
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public ResultMessage update(SysMenuDO sysMenuDO){
		ResultMessage msg = sysMenuAO.updateSysMenu(sysMenuDO);
		return msg;
	}

}
