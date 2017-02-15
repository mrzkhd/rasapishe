package com.rasapishe.boom.service.dto;

/**
 * Created by Mohammadreza on 2/16/2017 AD.
 */

public class LoginBoomRespDto {
	private String token;
	private String first_name;
	private String last_name;
	private String avatar;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
