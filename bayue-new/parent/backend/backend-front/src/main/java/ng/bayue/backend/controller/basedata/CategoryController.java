package ng.bayue.backend.controller.basedata;

import java.util.List;

import ng.bayue.backend.ao.basedata.CategoryAO;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.constant.CategoryConstant;
import ng.bayue.base.domain.CategoryDO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


/**
 * <pre>
 * 
 * </pre>
 *
 * @author fengyts
 * @version $Id: CategoryController.java, v 0.1 2016年7月15日 下午4:13:54 fengyts Exp $
 */
@Controller
@RequestMapping({"/basedata/category"})
public class CategoryController {
	
	@Autowired
	private CategoryAO categoryAO;
	
	@RequestMapping({"/list"})
	public String list(Model model){
		return "/backend/basedata/category/list";
	}
	
	@RequestMapping({"/categoryJsonData"})
	@ResponseBody
	public JSONObject categoryJsonData(){
		JSONObject o = categoryAO.categoryJsonData();
//		JSONObject o = demoData();
		return o;
	}
	
	@RequestMapping({"/add"})
	public String add(Model model){
		List<CategoryDO> listFir = categoryAO.listFirst();
		model.addAttribute("listFirst", listFir);
		return "/backend/basedata/category/add";
	}
	
	@RequestMapping({"/save"})
	@ResponseBody
	public ResultMessage saveCategory(CategoryDO categoryDO){
		ResultMessage msg = categoryAO.addCategory(categoryDO);
		return msg;
	}
	
	@RequestMapping({"/edit"})
	public String edit(Model model,Long id){
		CategoryDO categoryDO = categoryAO.selectById(id);
		if(CategoryConstant.LEVEL.MIDDLE == categoryDO.getLevel().intValue()){
			String ansName = categoryAO.selectById(categoryDO.getParentId()).getName();
			model.addAttribute("ansName", ansName + " >> " + categoryDO.getName());
		}else{
			model.addAttribute("ansName", categoryDO.getName());
			model.addAttribute("statusPrompt","一级分类状态改为无效时，其所有子类也会改为无效");
		}
		
		model.addAttribute("categoryDO", categoryDO);
		return "/backend/basedata/category/edit";
	}
	
	@RequestMapping({"/update"})
	@ResponseBody
	public ResultMessage updateCategory(CategoryDO categoryDO,Boolean oldStatus){
		ResultMessage msg = categoryAO.updateCategory(categoryDO, oldStatus);
		return msg;
	}
	
	/**
	 * <pre>
	 * 下拉列表二级联动
	 * </pre>
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping({"/linkageAjaxJson"})
	@ResponseBody
	public JSONArray selectAjaxJson(Long id){
		JSONArray arr = new JSONArray();
		if(null != id){
			List<CategoryDO> list = categoryAO.selectByParentId(id);
			arr = (JSONArray) JSONArray.toJSON(list);
		}
		return arr;
	}
	
	
//	private JSONObject demoData(){
//		
//		String data = "{\"rows\":"
//				+ "["
//				+ "{\"id\":1, \"name\": \"家居\",\"code\":\"01\",\"remark\":\"\","
//				+ "\"status\":\"true\",\"level\":\"1\", \"parentId\":\"0\",\"isLeaf\":false,"
//				+ "\"expanded\":false},"
//				+ "{\"id\":2, \"name\": \"家居1\",\"code\":\"0101\",\"remark\":\"\","
//				+ "\"status\":\"true\",\"level\":\"2\", \"parentId\":\"1\",\"isLeaf\":true,"
//				+ "\"expanded\":false},"
//				+ "{\"id\":3, \"name\": \"家居2\",\"code\":\"0102\",\"remark\":\"\","
//				+ "\"status\":\"true\",\"level\":\"2\", \"parentId\":\"1\",\"isLeaf\":true,"
//				+ "\"expanded\":false},"
//				+ "{\"id\":4, \"name\": \"数码\",\"code\":\"02\",\"remark\":\"\","
//				+ "\"status\":\"true\",\"level\":\"1\", \"parentId\":\"0\",\"isLeaf\":false,"
//				+ "\"expanded\":false},"
//				+ "{\"id\":5, \"name\": \"相机\",\"code\":\"0201\",\"remark\":\"\","
//				+ "\"status\":\"true\",\"level\":\"2\", \"parentId\":\"4\",\"isLeaf\":true,"
//				+ "\"expanded\":false},"
//				+ "{\"id\":6, \"name\": \"电脑\",\"code\":\"0202\",\"remark\":\"\","
//				+ "\"status\":\"true\",\"level\":\"2\", \"parentId\":\"4\",\"isLeaf\":true,"
//				+ "\"expanded\":false},"
//				+ "]"
//				+ "}";
//		
//		JSONObject o = JSONObject.parseObject(data);
//		return o;
//	}
	

}
