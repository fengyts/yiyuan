package ng.bayue.other.learn.designmodel.obligation.concrete;

import ng.bayue.other.learn.designmodel.obligation.Leader;
import ng.bayue.other.learn.designmodel.obligation.LeaveNode;

/**
 * <pre>
 * 系主任
 * </pre>
 *
 * @author lenovopc
 * @version $Id: DepartmentHead.java, v 0.1 2017年5月19日 下午5:35:33 lenovopc Exp $
 */
public class DepartmentHead extends Leader {

	public DepartmentHead(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveNode leaveNode) {
		if (leaveNode.getDays() > 3 && leaveNode.getDays() <= 7) {
			System.out.println("请假天数小于7天,系主任批准了" + leaveNode.getPerson() + "假期");
		} else {
			if (this.successor != null) {
				this.successor.handleRequest(leaveNode);
			}
		}
	}

}
