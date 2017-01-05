package ng.bayue.backend.controller.promotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ng.bayue.backend.ao.promotion.TopicItemAO;
import ng.bayue.backend.controller.common.BaseController;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.util.Page;

@Controller
@RequestMapping("/topicItem/")
public class TopicItemController extends BaseController{
	
	private static final String BASE_VIEW_PATH = "/backend/promotion/topicItem/";
	
	@Autowired
	private TopicItemAO topicItemAO;
	
	@RequestMapping("list")
	public String list(Model model,TopicItemDO topicItemDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		Page<TopicItemDO> page = topicItemAO.queryTopicItemList(topicItemDO, pageNo, pageSize);
		model.addAttribute("page", page);
		noRecords(model, page);
		return BASE_VIEW_PATH + "list";
	}
	
	@RequestMapping("save")
	@ResponseBody
	public ResultMessage save(String itemList,Long topicId,Boolean itemStatus){
		
		return new ResultMessage();
	}

}
