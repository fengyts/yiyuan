package ng.bayue.backend.controller.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import ng.bayue.backend.ao.sys.SysRoleAO;
import ng.bayue.backend.ao.sys.SysUserAO;
import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.util.Page;

@Controller
@RequestMapping({ "/sys/sysUser" })
public class SysUserController {

	@Autowired
	private SysUserAO sysUserAO;
	@Autowired
	private SysRoleAO sysRoleAO;

	@RequestMapping({ "/list" })
	public void sysUserList(Model model, @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, SysUserDO sysUserDO) {
		Page<SysUserDO> page = sysUserAO.queryPage(sysUserDO, pageNo, pageSize);
		model.addAttribute("page", page);
	}

	@RequestMapping({ "/add" })
	public void sysUserAdd(Model model) {
		List<SysRoleDO> roles = sysRoleAO.selectAllRole();
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		for (SysRoleDO sr : roles) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", sr.getId().toString());
			map.put("name", sr.getName());
			list.add(map);
		}
		String obj = JSONObject.toJSONString(list);

		model.addAttribute("roles", obj);
	}

	@RequestMapping({ "/save" })
	@ResponseBody
	public ResultMessage sysUserSave(SysUserDO sysUserDO) {

		return new ResultMessage();
	}

	@RequestMapping({ "/edit" })
	public void sysUserEdit(Model model) {

	}

	@RequestMapping({ "/update" })
	@ResponseBody
	public ResultMessage sysUserUpdate() {

		return new ResultMessage();
	}

	public static void main(String[] args) {
		List<Map<Long,String>> list = new ArrayList<Map<Long,String>>();
		Map<Long, String> map = new HashMap<Long, String>();
		map.put(1L, "ceshi");
		list.add(map);
		Map<Long, String> map1 = new HashMap<Long, String>();
		map1.put(2L, "hha");
		list.add(map1);
		Map<Long, String> map2 = new HashMap<Long, String>();
		map2.put(3L, "菜单");
		list.add(map2);
		String str = JSONObject.toJSONString(list);
		System.out.println(str);
	}

}
