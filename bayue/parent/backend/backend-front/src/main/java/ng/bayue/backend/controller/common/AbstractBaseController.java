package ng.bayue.backend.controller.common;

import java.text.SimpleDateFormat; 
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


public abstract class AbstractBaseController {

	@InitBinder
	public void init(HttpServletRequest request, ServletRequestDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
//	public String getUserName(){
//		UserDO user = (UserDO) SecurityUtils.getSubject().getPrincipal();
//		return user.getUserName();
//	}
//	
//	public UserDO getUser(){
//		return (UserDO) SecurityUtils.getSubject().getPrincipal();
//	}
}
