package ng.bayue.backend.controller.basedata;

import ng.bayue.backend.ao.basedata.SpecAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.SpecDO;
import ng.bayue.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/basedata/spec"})
public class SpecController {
	
	@Autowired
	private SpecAO specAO;
	
	@RequestMapping({"/list"})
	public String list(Model model,SpecDO specDO,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
		Page<SpecDO> page = specAO.queryPageList(specDO, pageNo, pageSize);
		model.addAttribute("page", page);
		return "/backend/basedata/spec/list";
	}
	
	@RequestMapping({"/add"})
	public String add(){
		return "/backend/basedata/spec/add";
	}
	
	@RequestMapping({"/save"})
	@ResponseBody
	public ResultMessage save(SpecDO specDO){
		ResultMessage msg = specAO.saveSpecDO(specDO);
		return msg;
	}
	
	@RequestMapping({"/edit"})
	public ModelAndView edit(Long id){
		ModelAndView mv = new ModelAndView();
		SpecDO specDO = specAO.selectById(id);
		mv.addObject("specDO", specDO);
		mv.setViewName("/backend/basedata/spec/edit");
		return mv;
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public ResultMessage update(SpecDO specDO,String oldSpec){
		ResultMessage msg = specAO.updateSpecDO(specDO, oldSpec);
		return msg;
	}
	
}
