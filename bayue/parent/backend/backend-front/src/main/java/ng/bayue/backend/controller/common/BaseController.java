package ng.bayue.backend.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.support.WebRequestDataBinder;

public class BaseController {

	@InitBinder
	public void dataBinder(WebRequestDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
	}

}
