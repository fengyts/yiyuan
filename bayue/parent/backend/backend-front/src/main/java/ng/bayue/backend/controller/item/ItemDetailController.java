package ng.bayue.backend.controller.item;

import ng.bayue.backend.ao.basedata.SpecGroupAO;
import ng.bayue.backend.ao.item.ItemDetailAO;
import ng.bayue.backend.controller.common.BaseController;
import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.item.domain.dto.ItemDTO;
import ng.bayue.item.domain.dto.ItemDetailDTO;
import ng.bayue.item.enums.ItemStatusEnum;
import ng.bayue.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({ "/item/itemDetail" })
public class ItemDetailController extends BaseController {

	private static final String BASE_VIEW = "/backend/item/itemdetail/";

	@Autowired
	private ItemDetailAO itemDetailAO;
	@Autowired
	private SpecGroupAO specGroupAO;

	@RequestMapping({ "/list" })
	public String listDetail(Model model, ItemDetailDTO itemDetialDTO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<ItemDTO> page = itemDetailAO.queryPageList(itemDetialDTO, pageNo, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("detailDO", itemDetialDTO);
		noRecords(model, page);
		return BASE_VIEW + "list";

	}

	@RequestMapping({ "/add" })
	public String add(Model model) {
		model.addAttribute("itemStatus", ItemStatusEnum.values());
		return BASE_VIEW + "add";
	}

	@RequestMapping("/initSpecGroup")
	public String initSpecGroup(Model model, SpecGroupDO specGroupDO,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Page<SpecGroupDO> page = specGroupAO.queryPageList(specGroupDO, pageNo, pageSize);
		model.addAttribute("page", page);
		noRecords(model, page);
		return BASE_VIEW + "associateSpecGroup";
	}

}
