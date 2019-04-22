package my.common.shiro.authc;

import java.util.Map;

import org.apache.shiro.authc.UsernamePasswordToken;

public class FormToken extends UsernamePasswordToken {
	private String captcha;
	private Map<String, Object> params;
	private String ssoToken;
	private static final long serialVersionUID = 1L;

	public FormToken(String username, char[] password, boolean rememberMe, String host, String captcha,
			Map<String, Object> var6) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.params = var6;
	}

	public Map<String, Object> getParams() {
		return this.params;
	}

	public void setSsoToken(String ssoToken) {
		this.ssoToken = ssoToken;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getCaptcha() {
		return this.captcha;
	}

	public FormToken() {
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void setPassword(String password) {
		this.setPassword(password != null ? password.toCharArray() : null);
	}

	public String getSsoToken() {
		return this.ssoToken;
	}

	public FormToken(String username, String password, boolean rememberMe, String host, String captcha,
			Map<String, Object> var6) {
		super(username, password, rememberMe, host);
		this.captcha = captcha;
		this.params = var6;
	}
}
