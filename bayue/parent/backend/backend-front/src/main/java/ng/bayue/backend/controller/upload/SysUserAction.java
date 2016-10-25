package ng.bayue.backend.controller.upload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Scope(value = "prototype")
@RequestMapping(value = "/sys")
public class SysUserAction extends BaseAction {
	Logger log = LoggerFactory.getLogger(SysUserAction.class);

	@RequestMapping("/uploadHeadPic")
	public String uploadHeadPic(@RequestParam("file") MultipartFile file,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			super.upload(file, "/upload/user/", request);
			response.getWriter().print(super.getFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}