package ng.bayue.other.learn.designmodel.obligation;

/**
 * <pre>
 * 抽象领导类
 * </pre>
 *
 * @author lenovopc
 * @version $Id: Leader.java, v 0.1 2017年5月19日 下午5:23:08 lenovopc Exp $
 */
public abstract class Leader {

	/** 姓名 */
	private String name;

	/** 后续领导 */
	protected Leader successor;

	public Leader(String name) {
		this.name = name;
	}

	/**
	 * 
	 * 处理请假
	 * 
	 * @param leaveNode
	 */
	public abstract void handleRequest(LeaveNode leaveNode);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Leader getSuccessor() {
		return successor;
	}

	public void setSuccessor(Leader successor) {
		this.successor = successor;
	}

}
