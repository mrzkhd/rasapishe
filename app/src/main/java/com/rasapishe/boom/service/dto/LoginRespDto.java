package com.rasapishe.boom.service.dto;

/**
 * Created by Mohammadreza on 2/15/2017 AD.
 */

public class LoginRespDto {
	private String code;
	private String foreign_name;
	private String gender;
	private String last_login_date;
	private String name;
	private String session_id;
	private String title;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getForeign_name() {
		return foreign_name;
	}

	public void setForeign_name(String foreign_name) {
		this.foreign_name = foreign_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLast_login_date() {
		return last_login_date;
	}

	public void setLast_login_date(String last_login_date) {
		this.last_login_date = last_login_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
