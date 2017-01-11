package ng.bayue.service.impl;

import ng.bayue.exception.ServiceException;
import ng.bayue.service.EmailService;

public class EmailServiceImpl implements EmailService {

	@Override
	public void sendEmail() throws ServiceException {
		
		org.springframework.mail.javamail.JavaMailSender j;
	}

}
