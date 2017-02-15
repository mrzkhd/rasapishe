package com.rasapishe.boom.service.dto;

/**
 * Created by Mohammadreza on 2/16/2017 AD.
 */

public interface LoginsCallBack {
	void onLoginFailed();

	void onLoginSuccess();

	void onLoginBoomFailed();

	void onLoginBoomSuccess();

}
