package ng.bayue.other.learn.designmodel.obligation;

import java.util.Date;

import ng.bayue.other.learn.designmodel.obligation.concrete.Dean;
import ng.bayue.other.learn.designmodel.obligation.concrete.DepartmentHead;
import ng.bayue.other.learn.designmodel.obligation.concrete.Instructor;
import ng.bayue.other.learn.designmodel.obligation.concrete.President;

public class TestObligation {
	
	public static void test(){
		Instructor instructor = new Instructor("辅导员A");
		DepartmentHead dh = new DepartmentHead("系主任B");
		Dean dean = new Dean("院长C");
		President president = new President("校长D");
		
		//设置后继者
		instructor.setSuccessor(dh);
		dh.setSuccessor(dean);
		dean.setSuccessor(president);
		
		Client client = new Client();
		
		LeaveNode leaveNode1 = new LeaveNode();
		leaveNode1.setPerson("张三");
		leaveNode1.setDate(new Date());
		leaveNode1.setReason("事假");
		leaveNode1.setDays(3);
		instructor.handleRequest(leaveNode1);
		
		LeaveNode leaveNode2 = new LeaveNode();
		leaveNode2.setPerson("李四");
		leaveNode2.setDate(new Date());
		leaveNode2.setReason("事假");
		leaveNode2.setDays(5);
		instructor.handleRequest(leaveNode2);
		
		LeaveNode leaveNode3 = new LeaveNode();
		leaveNode3.setPerson("王五");
		leaveNode3.setDate(new Date());
		leaveNode3.setReason("事假");
		leaveNode3.setDays(10);
		instructor.handleRequest(leaveNode3);
		
		LeaveNode leaveNode4 = new LeaveNode();
		leaveNode4.setPerson("赵六");
		leaveNode4.setDate(new Date());
		leaveNode4.setReason("事假");
		leaveNode4.setDays(15);
		instructor.handleRequest(leaveNode4);
		
		LeaveNode leaveNode5 = new LeaveNode();
		leaveNode5.setPerson("孙七");
		leaveNode5.setDate(new Date());
		leaveNode5.setReason("事假");
		leaveNode5.setDays(20);
		instructor.handleRequest(leaveNode5);
		
	}
	
	public static void main(String[] args) {
		test();
	}

}
