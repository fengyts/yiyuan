package ng.bayue.appfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/test"})
public class TestController {

	@RequestMapping({"/mobile"})
	public String mobile(Model model){
		return "/appfront/home";
	}
	
	@RequestMapping({"/setting"})
	public String setting(){
		return "/appfront/setting";
	}
	
	@RequestMapping({"page"})
	public String page(){
		return "/appfront/page1";
	}
	
}
