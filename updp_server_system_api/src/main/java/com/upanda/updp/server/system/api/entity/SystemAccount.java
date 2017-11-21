package com.upanda.updp.server.system.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * 自动建表实例
 * 
 * @date 2017年9月6日
 * @author Front Ng
 *
 */
@Entity
@Table(name = "system_account", uniqueConstraints = @UniqueConstraint(columnNames = "loginAccount"))
public class SystemAccount extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;


	/**
	 * 账号
	 */
	@Column(columnDefinition = "varchar(32) COMMENT '账号'", nullable = false, unique = true)
	private String loginAccount;

	/**
	 * 密码
	 */
	@Column(columnDefinition = "varchar(32) COMMENT '密码(加密)'", nullable = false)
	private String password;
	
	/**
	 * 随机盐
	 */
	@Column(columnDefinition = "varchar(32) COMMENT '随机盐'", nullable = false)
	private String salt;

	/**
	 * 姓名
	 */
	@Column(columnDefinition = "varchar(20) COMMENT '姓名'")
	private String name;
	
	
	/**
	 * 禁用
	 */
	@Column(columnDefinition = "bit COMMENT '禁用'")
	private Boolean isDisable;
	
	/**
	 * 锁定
	 */
	@Column(columnDefinition = "bit COMMENT '锁定'")
	private Boolean isLock;
 

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Boolean getIsDisable() {
		return isDisable;
	}

	public void setIsDisable(Boolean isDisable) {
		this.isDisable = isDisable;
	}

	public Boolean getIsLock() {
		return isLock;
	}

	public void setIsLock(Boolean isLock) {
		this.isLock = isLock;
	}

	
}
