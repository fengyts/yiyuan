package ng.bayue.snatch.exception;

import ng.bayue.exception.CommonDAOException;

public class DAOException extends CommonDAOException {
	
	private static final long serialVersionUID = 5122280185969230388L;

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
