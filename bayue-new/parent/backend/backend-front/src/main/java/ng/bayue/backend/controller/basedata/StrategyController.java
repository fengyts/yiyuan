package ng.bayue.backend.controller.basedata;

import java.util.List;

import ng.bayue.backend.ao.basedata.StrategyAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.StrategyDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping({"/basedata/strategy"})
public class StrategyController {

	@Autowired
	private StrategyAO strategyAO;
	
	
	@RequestMapping({"/list"})
	public String listStrategy(Model model){
		return "/backend/basedata/strategy/list";
	}
	
	@RequestMapping({"/strategyJsonData"})
	@ResponseBody
	public JSONArray jsonData(){
		JSONArray arr = strategyAO.strategyJsonData();
		return arr;
	}
	
	@RequestMapping({"/add"})
	public String add(Model model){
		List<StrategyDO> listParents = strategyAO.selectParents();
		model.addAttribute("listParents", listParents);
		return "/backend/basedata/strategy/add";
	}
	
	@RequestMapping({"/save"})
	@ResponseBody
	public ResultMessage save(StrategyDO strategyDO){
		ResultMessage msg = strategyAO.addStrategy(strategyDO);
		
		return msg;
	}
	
	@RequestMapping({"/edit"})
	public String edit(Model model,Long id){
		StrategyDO strategyDO = strategyAO.selectById(id);
		List<StrategyDO> listParents = strategyAO.selectParents();
		
		model.addAttribute("strategyDO", strategyDO);
		model.addAttribute("listParents", listParents);
		return "/backend/basedata/strategy/edit";
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public ResultMessage update(StrategyDO strategyDO){
		ResultMessage msg = strategyAO.updateStrategy(strategyDO);
		return msg;
	}
	
	
	
}
