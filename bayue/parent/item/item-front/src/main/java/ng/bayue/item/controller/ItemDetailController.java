package ng.bayue.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ng.bayue.item.ao.TestAO;

@Controller
@RequestMapping({"/item/itemDetail"})
public class ItemDetailController {
	
	@Autowired
	TestAO testAO;
	
	@RequestMapping({"/detail"})
	public void detail(Model model){
		testAO.test();
	}

}
