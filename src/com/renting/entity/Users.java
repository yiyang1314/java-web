package com.renting.entity;

import java.io.Serializable;

public class Users implements Serializable {
	private Integer userid;
	private String loginname;
	private String loginpwd;
	private String phone;
	private String realname;

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public void setLoginpwd(String loginpwd) {
		this.loginpwd = loginpwd;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getUserid() {
		return userid;
	}

	public String getLoginname() {
		return loginname;
	}

	public String getLoginpwd() {
		return loginpwd;
	}

	public String getPhone() {
		return phone;
	}

	public String getRealname() {
		return realname;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", loginname=" + loginname + ", loginpwd=" + loginpwd + ", phone=" + phone
				+ ", realname=" + realname + "]";
	}

	
}