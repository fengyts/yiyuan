package ng.bayue.backend.controller.item;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import ng.bayue.backend.ao.item.CarouselAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.common.Page;
import ng.bayue.item.domain.CarouselDO;

@Controller
@RequestMapping({ "/item/carousel/" })
public class CarouselController {
	
	private static final String BASE_VIEW_PATH = "/backend/item/carousel/";

	@Autowired
	private CarouselAO carouselAO;

	@RequestMapping("list")
	public String list(Model model, CarouselDO carouselDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<CarouselDO> page = carouselAO.queryPage(carouselDO, pageNo, pageSize);
		model.addAttribute("page", page);
		return BASE_VIEW_PATH + "list";
	}

	@RequestMapping("add")
	public String add() {
		return BASE_VIEW_PATH + "add";
	}

	@RequestMapping("save")
	@ResponseBody
	public ResultMessage save(String data, HttpServletRequest request) {
		MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> map = multiRequest.getFileMap();
		CarouselDO carouselDO = JSONObject.parseObject(data, CarouselDO.class);
		return carouselAO.save(carouselDO, map);
	}

	@RequestMapping("edit")
	public String edit(Model model, Long id) {
		CarouselDO carouselDO = carouselAO.selectById(id);
		model.addAttribute("carouselDO", carouselDO);
		return BASE_VIEW_PATH + "edit";
	}

	@RequestMapping("update")
	@ResponseBody
	public ResultMessage update(String data, boolean imgChanged, HttpServletRequest request) {
		Map<String, MultipartFile> map = null;
//		boolean flag = imgChanged != null && (1 == imgChanged) ? true : false; 
		if(imgChanged){ // 图片是否有改动
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			map = multiRequest.getFileMap();
		}
		CarouselDO carouselDO = JSONObject.parseObject(data, CarouselDO.class);
		return carouselAO.update(carouselDO, map);
	}

}
