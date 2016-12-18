package ng.bayue.backend.controller.item;

import java.util.List;

import javax.validation.Valid;

import ng.bayue.backend.ao.basedata.SpecGroupAO;
import ng.bayue.backend.ao.item.ItemDetailAO;
import ng.bayue.backend.controller.common.BaseController;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.item.domain.dto.ItemDTO;
import ng.bayue.item.domain.dto.ItemDetailDTO;
import ng.bayue.item.enums.ItemStatusEnum;
import ng.bayue.util.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String add(Model model, String iframeName) {
		model.addAttribute("itemStatus", ItemStatusEnum.values());
		model.addAttribute("listIframeName", iframeName);
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
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultMessage save(@Valid ItemDetailDTO itemDetialDTO, Errors error){
		if(error.hasErrors()){
			List<ObjectError> list = error.getAllErrors();
			ObjectError oe = list.get(0);
			String message = oe.getDefaultMessage();
			return new ResultMessage(ResultMessage.Failure, message);
		}
		
		return itemDetailAO.saveItemDetail(itemDetialDTO);
	}
	
	@RequestMapping({"/edit"})
	public String edit(Model model, Long detailId, String iframeName){
		if(null == detailId){
			return null;
		}
		ItemDetailDTO detailDto = itemDetailAO.selectDetailById(detailId);
		model.addAttribute("detailDO", detailDto);
		return BASE_VIEW + "edit";
	}

}
