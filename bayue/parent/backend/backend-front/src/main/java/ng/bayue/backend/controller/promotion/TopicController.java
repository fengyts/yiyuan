package ng.bayue.backend.controller.promotion;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import ng.bayue.backend.ao.item.ItemDetailAO;
import ng.bayue.backend.ao.promotion.TopicAO;
import ng.bayue.backend.ao.promotion.TopicItemAO;
import ng.bayue.backend.controller.common.BaseController;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.item.domain.dto.ItemDTO;
import ng.bayue.item.domain.dto.ItemDetailDTO;
import ng.bayue.promotion.domain.TopicDO;
import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.dto.TopicDTO;
import ng.bayue.promotion.enums.TopicProgressEnum;
import ng.bayue.promotion.enums.TopicTypeEnum;
import ng.bayue.util.ErrorMessage;
import ng.bayue.util.Page;
import ng.bayue.validate.GenerateValidator;

@Controller
@RequestMapping({"/topic"})
public class TopicController extends BaseController{
	
	private static final String BASE_VIEW_PATH = "/backend/promotion/topic/";
	
	@Autowired
	private TopicAO topicAO;
	@Autowired
	private TopicItemAO topicItemAO;
	@Autowired
	private ItemDetailAO itemDetailAO;
	
	
	@RequestMapping("/list")
	public String list(Model model,TopicDO topicDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		Page<TopicDTO> page = topicAO.queryPageList(topicDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("topicDO", topicDO);
		model.addAttribute("topicProgress", TopicProgressEnum.values());
		model.addAttribute("topicType", TopicTypeEnum.values());
		noRecords(model, page);
		return BASE_VIEW_PATH + "list";
	}
	
	@RequestMapping("/add")
	public String add(Model model, String iframeName){
		model.addAttribute("topicProgress", TopicProgressEnum.values());
		model.addAttribute("topicType", TopicTypeEnum.values());
		model.addAttribute("listIframeName", iframeName);
		return BASE_VIEW_PATH + "add";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultMessage save(String data, HttpServletRequest request){
		if(StringUtils.isEmpty(data)){
			return ResultMessage.serverInnerError();
		}
		TopicDO topicDO = JSONObject.parseObject(data, TopicDO.class);
		ErrorMessage validMsg = GenerateValidator.validate(topicDO);
		if(validMsg.hasError){
			return new ResultMessage(ResultMessage.Failure,validMsg.message);
		}
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multiRequest.getFileMap();
		return topicAO.save(topicDO,map);
	}
	
	@RequestMapping("/edit")
	public String edit(Model model, String iframeName, Long topicId){
		if(null == topicId){
			return null;
		}
		TopicDO topicDO = topicAO.selectById(topicId);
		model.addAttribute("topicDO", topicDO);
		
		model.addAttribute("topicProgress", TopicProgressEnum.values());
		model.addAttribute("topicType", TopicTypeEnum.values());
		model.addAttribute("listIframeName", iframeName);
		return BASE_VIEW_PATH + "edit";
	} 
	
	@RequestMapping("/update")
	@ResponseBody
	public ResultMessage update(String data, boolean imgChanged, HttpServletRequest request){
		if(StringUtils.isEmpty(data)){
			return ResultMessage.serverInnerError();
		}
		TopicDO topicDO = JSONObject.parseObject(data, TopicDO.class);
		ErrorMessage validMsg = GenerateValidator.validate(topicDO);
		if(validMsg.hasError){
			return new ResultMessage(ResultMessage.Failure,validMsg.message);
		}
		Map<String, MultipartFile> map = null;
//		boolean flag = imgChanged != null && (1 == imgChanged) ? true : false; 
		if(imgChanged){ // 图片是否有改动
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			map = multiRequest.getFileMap();
		}
		return topicAO.update(topicDO, map);
	}
	
	@RequestMapping(value = "/topicItemList", method={RequestMethod.GET,RequestMethod.POST})
	public String topicItemList(Model model,TopicItemDO topicItemDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		Page<TopicItemDO> page = topicItemAO.queryTopicItemList(topicItemDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("topicItemDO", topicItemDO);
		model.addAttribute("topicId", topicItemDO.getTopicId());
		noRecords(model, page);
		return BASE_VIEW_PATH + "topicItemList";
	}
	
	@RequestMapping("/initItemDetailList")
	public String initItemDetail(Model model, ItemDetailDTO itemDetialDTO, Long topicId,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize){
		Page<ItemDTO> page = itemDetailAO.queryPageList(itemDetialDTO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("detailDO", itemDetialDTO);
		model.addAttribute("topicId", topicId);
		noRecords(model, page);
		return BASE_VIEW_PATH + "itemDetailList";
	}

}
