package ng.bayue.backend.controller.item;

import java.util.List;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ng.bayue.backend.ao.basedata.CategoryAO;
import ng.bayue.backend.ao.basedata.DictionaryAO;
import ng.bayue.backend.ao.item.ItemInfoAO;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.enums.DictionaryEnums;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.dto.ItemInfoDTO;
import ng.bayue.util.Page;

@Controller
@RequestMapping({ "/item/itemInfo/" })
public class ItemInfoController {

	private static final String VIEW_PATH = "/backend/item/iteminfo/";

	@Autowired
	private ItemInfoAO itemInfoAO;
	@Autowired
	private CategoryAO categoryAO;
	@Autowired
	private DictionaryAO dictionaryAO;

	@RequestMapping({ "/list" })
	public String listItemInfo(Model model, ItemInfoDO infoDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<ItemInfoDTO> page = itemInfoAO.queryPageList(infoDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("infoDO", infoDO);
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return VIEW_PATH + "list";
	}

	@RequestMapping({ "/addItemInfo" })
	public String addItemInfo(Model model) {
		List<CategoryDO> listFir = categoryAO.listFirst();
		DictionaryDO dictionaryDO = new DictionaryDO();

		dictionaryDO.setCode(DictionaryEnums.c1001.getCode());
		List<DictionaryDO> unitList = dictionaryAO.selectDynamic(dictionaryDO);

		model.addAttribute("categoryFirList", listFir);
		model.addAttribute("unitList", unitList);

		return VIEW_PATH + "add";
	}

	@RequestMapping({ "/itemInfoJson" })
	@ResponseBody
	public ItemInfoDO itemInfoBySpu(String spu) {
		ItemInfoDO infoDO = itemInfoAO.getInfoBySPU(spu);
		return infoDO;
	}

}
