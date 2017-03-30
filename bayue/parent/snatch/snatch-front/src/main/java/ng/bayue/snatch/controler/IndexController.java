package ng.bayue.snatch.controler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping(value = { "/", "/index" })
	public String index(HttpServletRequest request) {
		return "/index";
	}

}
