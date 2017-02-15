package ng.bayue.backend.controller.basedata;

import java.util.List;

import ng.bayue.backend.ao.basedata.SpecAO;
import ng.bayue.backend.ao.basedata.SpecGroupAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.SpecDO;
import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.common.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/basedata/specGroup"})
public class SpecGroupController {
	
	@Autowired
	private SpecGroupAO specGroupAO;
	
	@Autowired
	private SpecAO specAO;
	
	@RequestMapping({"/list"})
	public String list (Model model,SpecGroupDO specGroupDO,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
		Page<SpecGroupDO> page = specGroupAO.queryPageList(specGroupDO, pageNo, pageSize);
		model.addAttribute("page", page);
		return "/backend/basedata/spec/specGroup/list";
	}
	
	@RequestMapping({"/add"})
	public String add(){
		return "/backend/basedata/spec/specGroup/add";
	}
	
	@RequestMapping({"/save"})
	@ResponseBody
	public ResultMessage save(SpecGroupDO specGroupDO,String specIds){
		ResultMessage msg = specGroupAO.saveSepcGroup(specGroupDO,specIds);
		return msg;
	}
	
	@RequestMapping({"/edit"})
	public String edit(Model model,Long id){
		SpecGroupDO specGroupDO = specGroupAO.selectById(id);
		model.addAttribute("specGroupDO", specGroupDO);
		List<SpecDO> listSpec = specGroupAO.listSpecsByGroupIds(id);
		model.addAttribute("listSpec", listSpec);
		return "/backend/basedata/spec/specGroup/edit";
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public ResultMessage update(SpecGroupDO specGroupDO){
		ResultMessage msg = new ResultMessage();
		return msg;
	}
	
	@RequestMapping({"/listSpec"})
	public String listSpec(Model model,SpecDO specDO,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
		Page<SpecDO> page = specAO.queryPageList(specDO, pageNo, pageSize);
		model.addAttribute("page", page);
		return "/backend/basedata/spec/specGroup/listSpec";
	}

}
