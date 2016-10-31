package ng.bayue.snatch.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"/test"})
public class TestController {

	@RequestMapping({"/abc"})
	public void testAbc(){
		System.out.println("dfd");
	}
	
	
}
