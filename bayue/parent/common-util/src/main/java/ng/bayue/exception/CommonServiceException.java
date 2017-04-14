package ng.bayue.exception;

public class CommonServiceException extends RuntimeException {

	/**  */
	private static final long serialVersionUID = 1L;

	public CommonServiceException() {
		super();
	}

	public CommonServiceException(String message) {
		super(message);
	}

	public CommonServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommonServiceException(Throwable cause) {
		super(cause);
	}

}
