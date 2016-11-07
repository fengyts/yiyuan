package ng.bayue.backend.controller.permission;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller

public class KaptchaController {

	@Autowired
	private Producer kaptchaProducer;

	@RequestMapping({ "/kaptcha" })
	public void securityCode(HttpServletResponse response, HttpServletRequest request) {
		HttpSession session = request.getSession();
		/*String code = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		System.out.println("******************验证码是: " + code + "******************");*/

		response.setDateHeader("Expires", 0);

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		String capText = kaptchaProducer.createText();

		// store the text in the session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		// create the image with the text
		BufferedImage bi = kaptchaProducer.createImage(capText);
		ServletOutputStream out = null;
		try {
			// write the data out
			out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.flush();
		} catch (IOException e1) {
		} finally {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		
	}
	
	
}
