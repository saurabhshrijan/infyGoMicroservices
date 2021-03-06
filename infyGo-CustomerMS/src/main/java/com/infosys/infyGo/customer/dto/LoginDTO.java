package com.infosys.infyGo.customer.dto;

import javax.validation.constraints.NotEmpty;

public class LoginDTO {
	@Override
	public String toString() {
		return "LoginDTO [userId=" + userId + ", password=" + password + "]";
	}

	@NotEmpty(message = "Please enter your userid")
	private String userId;

	@NotEmpty(message = "Please enter your password.")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userName
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
