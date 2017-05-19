package ng.bayue.other.learn.designmodel.obligation;

import java.util.Date;

/**
 * <pre>
 * 请假条
 * </pre>
 *
 * @author lenovopc
 * @version $Id: LeaveNode.java, v 0.1 2017年5月19日 下午5:20:35 lenovopc Exp $
 */
public class LeaveNode {

	/** 请假人 */
	private String person;

	/** 请假天数 */
	private int days;

	/** 请假原因 */
	private String reason;

	/** 请假时间 */
	private Date date;

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
