package ng.bayue.backend.controller.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ng.bayue.backend.ao.promotion.TopicAO;
import ng.bayue.backend.controller.common.BaseController;
import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.enums.TopicProgressEnum;
import ng.bayue.promotion.enums.TopicTypeEnum;
import ng.bayue.util.Page;

@Controller
@RequestMapping({"/topic"})
public class TopicController extends BaseController{
	
	private static final String BASE_VIEW_PATH = "/backend/promotion/topic/";
	
	@Autowired
	private TopicAO topicAO;
	
	
	@RequestMapping("/list")
	public String list(Model model,TopicDO topicDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		Page<TopicDO> page = topicAO.queryPageList(topicDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("topicDO", topicDO);
		model.addAttribute("topicProgress", TopicProgressEnum.values());
		model.addAttribute("topicType", TopicTypeEnum.values());
		noRecords(model, page);
		return BASE_VIEW_PATH + "list";
	}
	
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("topicProgress", TopicProgressEnum.values());
		model.addAttribute("topicType", TopicTypeEnum.values());
		return BASE_VIEW_PATH + "add";
	}

}
