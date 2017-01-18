package ng.bayue.service;

import ng.bayue.entity.EmailEntity;
import ng.bayue.exception.ServiceException;

public interface EmailService {
	
	void sendEmail(EmailEntity mail) throws ServiceException;
	
	/**
	 * <pre>
	 * 发送html格式的邮件
	 * </pre>
	 *
	 * @param htmlContent
	 */
	@Deprecated
	void emailManage(String htmlContent);

}
