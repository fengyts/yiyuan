package ng.bayue.other.learn.designmodel.obligation.concrete;

import ng.bayue.other.learn.designmodel.obligation.Leader;
import ng.bayue.other.learn.designmodel.obligation.LeaveNode;

public class President extends Leader{

	public President(String name) {
		super(name);
	}

	@Override
	public void handleRequest(LeaveNode leaveNode) {
	}

}
