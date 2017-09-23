package ng.bayue.other.learn.designmodel.obligation.concrete;

import ng.bayue.other.learn.designmodel.obligation.Leader;
import ng.bayue.other.learn.designmodel.obligation.LeaveNode;

/**
 * <pre>
 * 院长
 * </pre>
 *
 * @author lenovopc
 * @version $Id: Dean.java, v 0.1 2017年5月19日 下午5:37:36 lenovopc Exp $
 */
public class Dean extends Leader {

	public Dean(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveNode leaveNode) {
		if (leaveNode.getDays() > 7 && leaveNode.getDays() <= 10) {
			System.out.println("请假天数小于10天,院长:" + name + "批准了" + leaveNode.getPerson() + "假期");
		} else {
			if (this.successor != null) {
				this.successor.handleRequest(leaveNode);
			}
		}
	}

}
