package ng.bayue.other.learn.designmodel.obligation.concrete;

import ng.bayue.other.learn.designmodel.obligation.Leader;
import ng.bayue.other.learn.designmodel.obligation.LeaveNode;

/**
 * <pre>
 * 校长
 * </pre>
 *
 * @author fengyts
 * @version $Id: President.java, v 0.1 2017年5月19日 下午8:18:34 fengyts Exp $
 */
public class President extends Leader {

	public President(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveNode leaveNode) {
		if (leaveNode.getDays() <= 15) {
			System.out.println("请假天数小于15天,校长:" + name + "批准了" + leaveNode.getPerson() + "假期");
		} else {
			System.out.println("请假天数大于15天,校长:" + name + "不批准！");
		}
	}

}
