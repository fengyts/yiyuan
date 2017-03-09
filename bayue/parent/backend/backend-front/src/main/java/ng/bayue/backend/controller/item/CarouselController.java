package ng.bayue.backend.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public ResultMessage save(CarouselDO carouselDO) {
		return carouselAO.save(carouselDO);
	}

	@RequestMapping("edit")
	public String edit(Model model, Long id) {
		CarouselDO carouselDO = carouselAO.selectById(id);
		model.addAttribute("carouselDO", carouselDO);
		return BASE_VIEW_PATH + "edit";
	}

	@RequestMapping("update")
	@ResponseBody
	public ResultMessage update(CarouselDO carouselDO) {
		return carouselAO.update(carouselDO);
	}

}
