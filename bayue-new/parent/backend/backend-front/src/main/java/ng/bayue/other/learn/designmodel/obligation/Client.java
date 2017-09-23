package ng.bayue.other.learn.designmodel.obligation;

import ng.bayue.other.learn.designmodel.obligation.concrete.Instructor;

public class Client {

	private String name;

	private LeaveNode leaveNode;

	private Instructor instructor;

	public Client() {
	}

	public Client(String name, LeaveNode leaveNode, Instructor instructor) {
		this.name = name;
		this.leaveNode = leaveNode;
		this.instructor = instructor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LeaveNode getLeaveNode() {
		return leaveNode;
	}

	public void setLeaveNode(LeaveNode leaveNode) {
		this.leaveNode = leaveNode;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

}
