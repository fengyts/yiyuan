package ng.bayue.entity;

import java.io.Serializable;

public class EmailAuthenticator implements Serializable {

	/**  */
	private static final long serialVersionUID = -4617635585493479699L;

	private String userName;

	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}