package ng.bayue.backend.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class MyUsernamePasswordToken extends UsernamePasswordToken {

	private static final long serialVersionUID = 1L;

	private String kaptcha;

	public MyUsernamePasswordToken(String loginName, char[] password, boolean rememberMe,
			String host, String kaptcha) {
		super(loginName, password, rememberMe, host);
		this.kaptcha = kaptcha;
	}

	public String getKaptcha() {
		return kaptcha;
	}

	public void setKaptcha(String kaptcha) {
		this.kaptcha = kaptcha;
	}

}
