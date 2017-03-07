package ng.bayue.backend.controller.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ng.bayue.backend.ao.item.CarouselAO;
import ng.bayue.item.domain.CarouselDO;

@Controller
@RequestMapping({ "/item/carousel/" })
public class CarouselController {

	@Autowired
	private CarouselAO carouselAO;

	@RequestMapping("list")
	public String list(Model model, CarouselDO carouselDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		return "";
	}

	@RequestMapping("add")
	public void add() {
	}

	@RequestMapping("save")
	@ResponseBody
	public String save(CarouselDO carouselDO) {
		return "";
	}

	@RequestMapping("edit")
	public void edit(Model model, Long id) {
		CarouselDO carouselDO = carouselAO.selectById(id);
		model.addAttribute("carouselDO", carouselDO);
	}

	@RequestMapping("update")
	@ResponseBody
	public String update(CarouselDO carouselDO) {
		return "";
	}

}
