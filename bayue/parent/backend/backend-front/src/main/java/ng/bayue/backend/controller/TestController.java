package ng.bayue.backend.controller;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ng.bayue.backend.util.UserHandler;

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
		Session session = UserHandler.getSession();
		System.out.println(session.getId());
		return "/appfont/home";
	}
	
	@RequestMapping(value={"/testContent"},produces={"application/json"})
	public void testdfdfd(String level,Integer pageNo){
		System.out.println(level);
		System.out.println(pageNo);
		System.out.println("maxSize:"+maxSize);
	}
	
	
}
