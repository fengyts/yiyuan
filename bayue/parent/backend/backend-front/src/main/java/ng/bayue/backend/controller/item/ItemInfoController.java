package ng.bayue.backend.controller.item;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import ng.bayue.backend.ao.basedata.CategoryAO;
import ng.bayue.backend.ao.basedata.DictionaryAO;
import ng.bayue.backend.ao.item.ItemInfoAO;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.enums.DictionaryEnums;
import ng.bayue.item.domain.ItemInfoDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/item/itemInfo/" })
public class ItemInfoController {

	@Autowired
	private ItemInfoAO itemInfoAO;
	@Autowired
	private CategoryAO categoryAO;
	@Autowired
	private DictionaryAO dictionaryAO;

	@RequestMapping({ "/list" })
	public String listItemInfo(Model model) {

		return "/backend/item/iteminfo/list";
	}
	
	@RequestMapping({"/addItemInfo"})
	public String addItemInfo(Model model){
		List<CategoryDO> listFir = categoryAO.listFirst();
		DictionaryDO dictionaryDO = new DictionaryDO();
		
		dictionaryDO.setCode(DictionaryEnums.c1001.getCode());
		List<DictionaryDO> unitList = dictionaryAO.selectDynamic(dictionaryDO);
		
		model.addAttribute("categoryFirList", listFir);
		model.addAttribute("unitList",unitList);
		
		return "/backend/item/add_spu";
	}
	
	@RequestMapping({"/itemInfoJson"})
	@ResponseBody
	public ItemInfoDO itemInfoBySpu(String spu){
		ItemInfoDO infoDO = itemInfoAO.getInfoBySPU(spu);
		return infoDO;
	}
	

}
