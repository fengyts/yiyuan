package ng.bayue.backend.controller.index;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.domain.SysUserDO;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping({"/","/index"})
	public String index(Model model){
		Subject subject = SecurityUtils.getSubject();
		SysUserDO sysUser = (SysUserDO) subject.getPrincipal();
		if(null == sysUser){
			return "redirect:login";
		}
		model.addAttribute("user", sysUser);
		return "/index/index";
	}
	
	
	@RequestMapping({"/favrite/list"})
	public String listMenu(Model model){
		List<SysMenuDO> menuDOs = new ArrayList<SysMenuDO>();
		model.addAttribute("menuDOs", menuDOs);
		return "/index/favrite_index";
	}

}
