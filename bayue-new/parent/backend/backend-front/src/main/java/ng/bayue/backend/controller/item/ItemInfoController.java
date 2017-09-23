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
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.enums.DictionaryEnums;
import ng.bayue.common.Page;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.dto.ItemInfoDTO;

@Controller
@RequestMapping({ "/item/itemInfo/" })
public class ItemInfoController {

	private static final String BASE_VIEW = "/backend/item/iteminfo/";

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
		return BASE_VIEW + "list";
	}

	@RequestMapping({ "/addItemInfo" })
	public String addItemInfo(Model model) {
		initCategoryAndDictionary(model);

		return BASE_VIEW + "add";
	}

	/**
	 * <pre>
	 * 初始化大类和单位
	 * </pre>
	 *
	 * @param model
	 */
	private void initCategoryAndDictionary(Model model) {
		List<CategoryDO> listFir = categoryAO.listFirst();
		DictionaryDO dictionaryDO = new DictionaryDO();

		dictionaryDO.setCode(DictionaryEnums.c1001.getCode());
		List<DictionaryDO> unitList = dictionaryAO.selectDynamic(dictionaryDO);

		model.addAttribute("categoryFirList", listFir);
		model.addAttribute("unitList", unitList);
	}

	@RequestMapping({ "/save" })
	public @ResponseBody ResultMessage saveItemInfo(ItemInfoDO infoDO) {

		return itemInfoAO.saveItemInfo(infoDO);
	}

	@RequestMapping({ "/itemInfoJson" })
	@ResponseBody
	public ItemInfoDO itemInfoBySpu(String spu) {
		ItemInfoDO infoDO = itemInfoAO.getInfoBySPU(spu);
		return infoDO;
	}

	@RequestMapping("/edit")
	public String edit(Model model, Long id) {
		if (null == id) {
			return null;
		}
		ItemInfoDO infoDO = itemInfoAO.selectItemInfoById(id);
		model.addAttribute("infoDO", infoDO);

		// 初始化大类和单位
		initCategoryAndDictionary(model);

		// 根据大类初始化二级分类
		long largeId = infoDO.getLargeId();
		List<CategoryDO> smalCate = categoryAO.selectByParentId(largeId);
		model.addAttribute("smallCate", smalCate);

		return BASE_VIEW + "edit";
	}

	@RequestMapping("update")
	@ResponseBody
	public ResultMessage updateInfoDO(ItemInfoDO infoDO, Long oldSmallId) {
		if (null == oldSmallId) {
			return ResultMessage.serverInnerError();
		}
		boolean isRebuildSPU = infoDO.getSmallId().longValue() == oldSmallId.longValue();
		return itemInfoAO.updateItemInfo(infoDO, isRebuildSPU);
	}

	@RequestMapping("initSpuList")
	public String initSpuList(Model model, ItemInfoDO infoDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<ItemInfoDTO> page = itemInfoAO.queryPageList(infoDO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("infoDO", infoDO);
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecoders", "暂无数据");
		}
		return "/backend/item/itemdetail/initInfo";
	}

}
