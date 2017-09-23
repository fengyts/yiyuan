package ng.bayue.backend.controller.item;

import java.util.List;

import ng.bayue.backend.ao.basedata.CategoryAO;
import ng.bayue.backend.ao.basedata.DictionaryAO;
import ng.bayue.backend.ao.item.ItemDetailAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.enums.DictionaryEnums;
import ng.bayue.common.Page;
import ng.bayue.item.dto.ItemDTO;
import ng.bayue.item.dto.ItemDetailDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <pre>
 * 商品管理通用controller
 * </pre>
 *
 * @author fengyts
 * @version $Id: ItemController.java, v 0.1 2016年7月14日 下午10:12:34 fengyts Exp $
 */
@Controller
@RequestMapping({ "/item" })
public class ItemController {
	
	@Autowired
	private CategoryAO categoryAO;
	@Autowired
	private DictionaryAO dictionaryAO;
	@Autowired
	private ItemDetailAO detailAO;
	
	@RequestMapping({"/list"})
	public String itemList(
			Model model,
			ItemDetailDTO detailDTO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		
		Page<ItemDTO> page = detailAO.queryPageList(detailDTO,pageNo,pageSize);
		return "/backend/item/list";
	}
	
	@RequestMapping({"/add"})
	public String add(Model model){
		List<CategoryDO> listFir = categoryAO.listFirst();
		DictionaryDO dictionaryDO = new DictionaryDO();
		
		dictionaryDO.setCode(DictionaryEnums.c1001.getCode());
		List<DictionaryDO> unitList = dictionaryAO.selectDynamic(dictionaryDO);
		
		model.addAttribute("categoryFirList", listFir);
		model.addAttribute("unitList",unitList);
		
		return "/backend/item/add";
	}
	
	@RequestMapping({"/save"})
	@ResponseBody
	public ResultMessage save(ItemDTO dto,String path,String picList){
		
		ResultMessage msg = detailAO.saveItemInfoAndDetail(dto);
		
		return new ResultMessage();
	}
	
	@RequestMapping({"/edit"})
	public String edit(Model model,Long id){
		
		return "/backend/item/edit";
	}
	
	
	
	

}
