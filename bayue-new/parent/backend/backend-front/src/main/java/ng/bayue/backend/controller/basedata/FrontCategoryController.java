package ng.bayue.backend.controller.basedata;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import ng.bayue.backend.ao.basedata.FrontCategoryAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.dto.FrontCategoryDTO;
import ng.bayue.base.enums.FrontCategoryLinkTypeEnums;

@Controller
@RequestMapping("/basedata/frontCategory/")
public class FrontCategoryController {

	private static final String BASE_VIEW_PATH = "/backend/basedata/frontCategory/";
	
	@Autowired
	private FrontCategoryAO frontCategoryAO;

	@RequestMapping("importFCate")
	public String importFrontCate() {
		return BASE_VIEW_PATH + "importFCate";
	}

	@RequestMapping("uploadFCate")
	@ResponseBody
	public ResultMessage uploadFrontCate(HttpServletRequest request) {
		MultipartHttpServletRequest mres = (MultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> multiFileMap = mres.getMultiFileMap();
		// 功能待完善
		// ...
		return new ResultMessage();
	}

	@RequestMapping("list")
	public String list(){
		return BASE_VIEW_PATH + "list";
	}
	
	@RequestMapping("fcJsonData")
	@ResponseBody
	public JSONObject frontCategoryJsonData() {

		return frontCategoryAO.getFrontCategoryJSON();
	}

	@RequestMapping("add")
	public String add(Model model) {
		model.addAttribute("linkTypes", FrontCategoryLinkTypeEnums.values());
		model.addAttribute("listFirst", frontCategoryAO.selectAllTop());
		return BASE_VIEW_PATH + "add";
	}

	@RequestMapping("save")
	@ResponseBody
	public ResultMessage save(FrontCategoryDTO frontCategoryDO) {
		return frontCategoryAO.save(frontCategoryDO);
	}

	@RequestMapping("edit")
	public String edit(Model model, Long id) {
		model.addAttribute("linkTypes", FrontCategoryLinkTypeEnums.values());
		model.addAttribute("fcate", frontCategoryAO.selectById(id));
		return BASE_VIEW_PATH + "edit";
	}

	@RequestMapping("update")
	@ResponseBody
	public ResultMessage update(FrontCategoryDTO frontCategoryDO) {
		return frontCategoryAO.update(frontCategoryDO);
	}

}
