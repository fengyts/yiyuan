package ng.bayue.other.learn.designmodel.obligation.concrete;

import ng.bayue.other.learn.designmodel.obligation.Leader;
import ng.bayue.other.learn.designmodel.obligation.LeaveNode;

/**
 * <pre>
 * 辅导员类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: Instructor.java, v 0.1 2017年5月19日 下午5:32:31 lenovopc Exp $
 */
public class Instructor extends Leader {

	public Instructor(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveNode leaveNode) {
		if (leaveNode.getDays() <= 3) {
			System.out.println("请假天数小于3天,辅导员:" + name + "批准了 " + leaveNode.getPerson() + " 的假期");
		} else {
			if (this.successor != null) {
				this.successor.handleRequest(leaveNode);
			}
		}
	}

}
