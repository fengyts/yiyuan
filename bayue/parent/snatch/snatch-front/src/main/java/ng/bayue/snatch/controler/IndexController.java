package ng.bayue.snatch.controler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

import ng.bayue.base.dto.FrontCategoryViewDTO;
import ng.bayue.snatch.ao.IndexAO;
import ng.bayue.snatch.dto.ReturnData;

@Controller
public class IndexController {

	@Autowired
	private IndexAO indexAO;

	@RequestMapping(value = { "/", "/index" })
	public String index(HttpServletRequest request, Model model) {
		request.setAttribute("carousels", indexAO.getAllCarousel());
		model.addAttribute("hotItems", indexAO.getHostItems());
		return "/index";
	}

	@RequestMapping(value = "loadfc")
	@ResponseBody
	public JSONPObject frontCategory(HttpServletRequest request, @RequestParam String callback) {
		List<FrontCategoryViewDTO> list = indexAO.loadFrontCategory();

		ReturnData returnDate = null;
		if (list != null && list.size() > 0) {
			returnDate = new ReturnData(1, list);
		} else {
			returnDate = new ReturnData(0, null);
		}
		return new JSONPObject(callback, returnDate);
	}

	@RequestMapping("topicSpecially")
	public String hd(HttpServletRequest rquest) {
		return "";
	}

}
