package ng.bayue.backend.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import ng.bayue.util.Page;


public class BaseController {

	@InitBinder
	public void dataBinder(HttpServletRequest request,ServletRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}
	
	public <T> void noRecords(Model model,Page<T> page){
		if (CollectionUtils.isEmpty(page.getList())) {
			model.addAttribute("noRecords", "暂无数据");
		}
	}
	
}
