package com.rasapishe.boom.service.dto;

/**
 * Created by Mohammadreza on 2/16/2017 AD.
 */

public interface ServiceCallBack {

	public void onLoginFailed();

	public void onLoginBoomFailed();

	public void onDepositTransferFailed();

	public void onDepositTransferSuccess(DepositTransferResp depositTransferResp);


}
