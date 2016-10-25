package ng.bayue.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/test"})
public class TestController {
	
	@Value("${test.value}")
	private String testValue;
//	@Value("#{meta['test.value']}")
//	private String testValue1;
	@Value("#{meta['item.picture.MaxSize']}")
	private int maxSize;

	@RequestMapping({"/testPropertiesValue"})
	public void testProperValue(){
		System.out.println(testValue);
//		System.out.println(testValue1);
	}
	
	@RequestMapping({"/mobile"})
	public String mobile(Model model){
		return "/appfont/home";
	}
	
	@RequestMapping({"/test"})
	public void testdfdfd(){
		System.out.println("maxSize:"+maxSize);
	}
	
}
