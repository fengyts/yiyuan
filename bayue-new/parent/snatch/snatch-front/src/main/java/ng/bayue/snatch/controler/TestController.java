package ng.bayue.snatch.controler;

import ng.bayue.common.ResultMessage;
import ng.bayue.util.CaptchaHelpler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping({ "/test/" })
public class TestController {

	@RequestMapping(value = "captcha")
	public String captcha(Model model) {
		CaptchaHelpler ch = new CaptchaHelpler();
		String generateCaptcha = ch.toImageBase64(ch.generateCaptcha());
		model.addAttribute("captcha", generateCaptcha);
		return "/captcha";
	}

	@RequestMapping({ "abc" })
	public void testAbc() {
		System.out.println("dfd");
	}

	@RequestMapping(value = "weixin")
	@ResponseBody
	public ResultMessage testWeixin() {
		ResultMessage msg = new ResultMessage();
		msg.setData("hahahaha哈哈好的，欢迎来到盘蛇岛!");
		return msg;
	}

}
