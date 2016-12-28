package ng.bayue.backend.controller.promotion;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import ng.bayue.backend.ao.promotion.TopicAO;
import ng.bayue.backend.controller.common.BaseController;
import ng.bayue.backend.util.ResultMessage;
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
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultMessage save(TopicDO topicDO, HttpServletRequest request){
		
	    
		return new ResultMessage();
	}

}
