package ng.bayue.backend.controller.basedata;

import ng.bayue.backend.ao.basedata.ForbiddenWordsAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.common.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({"/basedata/forbiddenWords"})
public class ForbiddenWordsController {
	
	@Autowired
	private ForbiddenWordsAO forbiddenWordsAO;
	
	@RequestMapping({"/list"})
	public String list(Model model,ForbiddenWordsDO forbiddenWordsDO,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
		Page<ForbiddenWordsDO> page = forbiddenWordsAO.pageQueryList(forbiddenWordsDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("forbiddenWordsDO", forbiddenWordsDO);
		return "/backend/basedata/forbiddenWords/list";
	}
	
	@RequestMapping({"/add"})
	public String add(Model model){
		return "/backend/basedata/forbiddenWords/add";
	}
	
	@RequestMapping({"/save"})
	@ResponseBody
	public ResultMessage save(ForbiddenWordsDO forbiddenWordsDO){
		ResultMessage msg = forbiddenWordsAO.addForbiddenWords(forbiddenWordsDO);
		return msg;
	}
	
	@RequestMapping({"/edit"})
	public String edit(Model model,Long id){
		ForbiddenWordsDO forbiddenWordsDO = forbiddenWordsAO.selectById(id);
		model.addAttribute("forbiddenWordsDO", forbiddenWordsDO);
		return "/backend/basedata/forbiddenWords/edit";
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public ResultMessage update(ForbiddenWordsDO forbiddenWordsDO){
		ResultMessage msg = forbiddenWordsAO.updateForbiddenWords(forbiddenWordsDO);
		return msg;
	}

}
