package ng.bayue.backend.controller.item;

import ng.bayue.backend.ao.item.ItemDetailAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/item/itemDetail" })
public class ItemDetailController {

	@Autowired
	private ItemDetailAO itemDetailAO;
	
	@RequestMapping({"/listDetail"})
	public String listDetail(Model model,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
		
		
		return "/backend/item/list";
		
	}
	
	@RequestMapping({"/addDetail"})
	public String add(Model model){
		return "/backend/item/add_detail";
	}

}
