package ng.bayue.backend.controller.promotion;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import ng.bayue.backend.ao.promotion.TopicItemAO;
import ng.bayue.backend.controller.common.BaseController;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.dto.TopicItemDTO;
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
		Page<TopicItemDTO> page = topicItemAO.queryTopicItemList(topicItemDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("topicItemDO", topicItemDO);
		noRecords(model, page);
		return BASE_VIEW_PATH + "list";
	}
	
	@RequestMapping("save")
	@ResponseBody
	public ResultMessage save(String itemList){
		if(StringUtils.isBlank(itemList)){
			return ResultMessage.validParameterNull("itemList");
		}
		List<TopicItemDO> list = (List<TopicItemDO>) JSONObject.parseArray(itemList, TopicItemDO.class);
		return topicItemAO.save(list);
	}
	
	@RequestMapping("edit")
	public String edit(Model model, Long id){
		model.addAttribute("topicItemDO", topicItemAO.selectTopicItemById(id));
		return BASE_VIEW_PATH + "edit";
	}

}
