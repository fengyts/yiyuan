package ng.bayue.exception;

public class CommonDAOException extends Exception {

	private static final long serialVersionUID = 5122280185969230388L;

	public CommonDAOException() {
		super();
	}

	public CommonDAOException(String msg) {
		super(msg);
	}

	public CommonDAOException(Throwable cause) {
		super(cause);
	}

	public CommonDAOException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
