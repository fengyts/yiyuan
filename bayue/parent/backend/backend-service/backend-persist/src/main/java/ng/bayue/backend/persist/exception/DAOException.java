package ng.bayue.backend.persist.exception;

public class DAOException extends Exception {

	private static final long serialVersionUID = 9110644449097294948L;

	public DAOException() {
		super();
	}

	public DAOException(String msg) {
		super(msg);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
