package ng.bayue.snatch.controler;

import ng.bayue.util.CaptchaGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/test/" })
public class TestController {

	@RequestMapping(value = "captcha")
	public String captcha(Model model) {
		CaptchaGenerator ch = new CaptchaGenerator();
		String generateCaptcha = ch.toImageBase64(ch.generateCaptcha());
		model.addAttribute("captcha", generateCaptcha);
		return "/captcha";
	}

	@RequestMapping({ "abc" })
	public void testAbc() {
		System.out.println("dfd");
	}


}
