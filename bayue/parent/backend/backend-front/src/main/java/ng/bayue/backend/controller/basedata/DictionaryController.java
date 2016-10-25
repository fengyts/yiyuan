package ng.bayue.backend.controller.basedata;

import java.util.List;

import ng.bayue.backend.ao.basedata.DictionaryAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping({"/basedata/dictionary"})
public class DictionaryController {
	
	@Autowired
	private DictionaryAO dictionaryAO;
	
	@RequestMapping({"/list"})
	public String list(Model model,DictionaryDO dictionaryDO,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
		
		Page<DictionaryDO> page = dictionaryAO.pageQueryList(dictionaryDO, pageNo, pageSize);
		model.addAttribute("page", page);
		
		return "/backend/basedata/dictionary/list";
	}
	
	@RequestMapping({"/add"})
	public String add(Model model){
		List<DictionaryDO> list = dictionaryAO.listAllCode();
		model.addAttribute("listCode", list);
		return "/backend/basedata/dictionary/add";
	}
	
	@RequestMapping({"save"})
	@ResponseBody
	public ResultMessage save(DictionaryDO dictionaryDO){
		ResultMessage msg = dictionaryAO.addDictionary(dictionaryDO);
		return msg;
	}
	
	@RequestMapping({"/edit"})
	public String edit(Model model,Long id){
		DictionaryDO dictionaryDO = dictionaryAO.selectById(id);
		List<DictionaryDO> listCode = dictionaryAO.listAllCode();
		model.addAttribute("dictionaryDO", dictionaryDO);
		model.addAttribute("listCode",listCode);
		return "/backend/basedata/dictionary/edit";
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public ResultMessage update(DictionaryDO dictionaryDO){
		ResultMessage msg = dictionaryAO.updateDictionary(dictionaryDO);
		
		return msg;
	}
	
	@RequestMapping({"/getCode"})
	@ResponseBody
	public JSONArray getCode(){
		List<DictionaryDO> list = dictionaryAO.listAllCode();
		JSONArray arr = (JSONArray) JSONArray.toJSON(list);
		return arr;
	}

}
