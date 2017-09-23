package ng.bayue.backend.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ng.bayue.backend.util.ApplicationContextUtil;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.entity.LearnAcceptEntity;
import ng.bayue.service.EmailService;

@Controller
@RequestMapping({"/test"})
public class TestController {
	
	@Autowired
	EmailService emailService;
	
	@Value("${test.value}")
	private String testValue;
//	@Value("#{meta['test.value']}")
//	private String testValue1;
	@Value("#{meta['item.picture.MaxSize']}")
	private int maxSize;

	@RequestMapping({"/testPropertiesValue"})
	public void testProperValue(){
		System.out.println(testValue);
//		System.out.println(testValue1);
	}
	
	@RequestMapping({"/mobile"})
	public String mobile(Model model){
		Session session = UserHandler.getSession();
		System.out.println(session.getId());
		Collection<Session> collection = UserHandler.getActiveSession();
		System.out.println(collection.size());
		SessionDAO sessionDAO = (SessionDAO) ApplicationContextUtil.getBean("sessionDAO");
		Collection<Session> result = UserHandler.getSessionByPrincipal("superadmin");
		return "/appfont/home";
	}
	
	@RequestMapping(value={"/testContent"},produces={"application/json"})
	public void testdfdfd(String level,Integer pageNo){
		System.out.println(level);
		System.out.println(pageNo);
		System.out.println("maxSize:"+maxSize);
	}
	
	@RequestMapping("/sendEmail")
	@ResponseBody
	public String sendEmail(){
		StringBuilder builder = new StringBuilder();
        builder.append("<html><body>你好！<br />");
        builder.append("&nbsp&nbsp&nbsp&nbsp附件是个人清单。<br />");
        builder.append("&nbsp&nbsp&nbsp&nbsp其中人信息；<br />");
        builder.append("</body></html>");
		emailService.emailManage(builder.toString());
		System.out.println(123232);
		return "success";
	}
	
	@RequestMapping("/acceptListParam")
	public void acceptListParam(LearnAcceptEntity entity,@RequestParam("list[]")List<String> list){
		System.out.println();
		System.out.println();
	}
	
	
}
