package com.upanda.updp.server.system.api.vo;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 登录VO
 * @date 2017年9月5日
 * @author Front Ng
 *
 */
public class LoginVO implements Serializable {
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private String loginAccount;
	
	private String password;
	
	private Boolean remeberMe;
	
	public Boolean getRemeberMe() {
		return remeberMe;
	}
	public void setRemeberMe(Boolean remeberMe) {
		this.remeberMe = remeberMe;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
