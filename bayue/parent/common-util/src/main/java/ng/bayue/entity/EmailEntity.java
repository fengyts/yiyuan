package ng.bayue.entity;

import java.io.Serializable;
import java.util.Map;

public class EmailEntity implements Serializable {

	private static final long serialVersionUID = -3823243631882356546L;

	/** 收件人和抄送，多个以分号';'分隔 */
	private String[] toEmails;

	/** 邮件主题 */
	private String subject;

	/** 邮件内容 */
	private String conteng;

	/**
	 * 邮件中的图片;为空时无图片;map中的key为图片ID或名称,value为图片地址
	 */
	private Map<String, String> pictures;
	/**
	 * 邮件中的附件;为空时无附件;map中的key为附件ID或名称,value为附件地址
	 */
	private Map<String, String> attachments;

	public String[] getToEmails() {
		return toEmails;
	}

	public void setToEmails(String... toEmails) {
		this.toEmails = toEmails;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getConteng() {
		return conteng;
	}

	public void setConteng(String conteng) {
		this.conteng = conteng;
	}

	public Map<String, String> getPictures() {
		return pictures;
	}

	public void setPictures(Map<String, String> pictures) {
		this.pictures = pictures;
	}

	public Map<String, String> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, String> attachments) {
		this.attachments = attachments;
	}

}
