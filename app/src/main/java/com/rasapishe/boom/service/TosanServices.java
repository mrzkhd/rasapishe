package com.rasapishe.boom.service;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.rasapishe.boom.RasaApplication;
import com.rasapishe.boom.enums.BankId;
import com.rasapishe.boom.service.dto.DepositTransferReq;
import com.rasapishe.boom.service.dto.DepositTransferResp;
import com.rasapishe.boom.service.dto.LoginBoomRespDto;
import com.rasapishe.boom.service.dto.LoginReqDto;
import com.rasapishe.boom.service.dto.LoginRespDto;
import com.rasapishe.boom.service.dto.LoginsCallBack;
import com.rasapishe.boom.service.dto.ServiceCallBack;
import com.rasapishe.boom.service.dto.SessionData;
import com.rasapishe.boom.utils.ServiceUtil;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Mohammadreza on 2/15/2017 AD.
 */

public class TosanServices {


	private void login(LoginReqDto reqDto, final LoginsCallBack loginsCallBack) {

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.PUT,
				RasaApplication.URL_TOSAN_LOGIN,
				ServiceUtil.getJsonObject(reqDto)
				, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				LoginRespDto loginRespDto = new
						Gson().fromJson(((JSONObject) response).toString(), LoginRespDto.class);
				SessionData.loginRespDto = loginRespDto;
				loginsCallBack.onLoginSuccess();

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				loginsCallBack.onLoginFailed();

			}
		});

		NetworkServiceQueue.getInstance().getRequestQueue().add(jsonObjectRequest);

	}

	private void loginBoom(LoginReqDto reqDto, final LoginsCallBack loginsCallBack) {

		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.PUT,
				RasaApplication.URL_TOSAN_BOOM_LOGIN,
				ServiceUtil.getJsonObject(reqDto)
				, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				LoginBoomRespDto loginRespDto = new
						Gson().fromJson(((JSONObject) response).toString(), LoginBoomRespDto.class);
				SessionData.loginBoomRespDto = loginRespDto;
				loginsCallBack.onLoginBoomSuccess();

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				loginsCallBack.onLoginBoomFailed();

			}
		});
		NetworkServiceQueue.getInstance().getRequestQueue().add(jsonObjectRequest);

	}

	public void depositeTransfer(final DepositTransferReq reqDto,
	                             final BankId bankId,
	                             final String TraceNum,
	                             final String TraceDate,
	                             final ServiceCallBack serviceCallBack) {
		LoginsCallBack loginsCallBack = new LoginsCallBack() {
			@Override
			public void onLoginFailed() {
				serviceCallBack.onLoginFailed();
			}

			@Override
			public void onLoginSuccess() {
				LoginReqDto loginReq = new LoginReqDto();
				loginReq.setUsername(RasaApplication.TOSAN_USERNAME);
				loginReq.setPassword(RasaApplication.TOSAN_PASSORD);

				loginBoom(loginReq, this);
			}

			@Override
			public void onLoginBoomFailed() {
				serviceCallBack.onLoginBoomFailed();
			}

			@Override
			public void onLoginBoomSuccess() {
				transfer(reqDto, bankId, TraceNum, TraceDate, serviceCallBack);
			}
		};

		LoginReqDto loginReq = new LoginReqDto();
		loginReq.setUsername(RasaApplication.TOSAN_USERNAME);
		loginReq.setPassword(RasaApplication.TOSAN_PASSORD);

		login(loginReq, loginsCallBack);


	}

	private void transfer(DepositTransferReq reqDto, final BankId bankId, final String TraceNum, final String TraceDate, final ServiceCallBack serviceCallBack) {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
				Request.Method.PUT,
				RasaApplication.URL_TOSAN_BOOM_LOGIN,
				ServiceUtil.getJsonObject(reqDto)
				, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				DepositTransferResp resp = new
						Gson().fromJson(((JSONObject) response).toString(), DepositTransferResp.class);

				serviceCallBack.onDepositTransferSuccess(resp);

			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				serviceCallBack.onDepositTransferFailed();

			}
		}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map mapHeaders = super.getHeaders();
				mapHeaders.put("Device-Id", "");
				mapHeaders.put("Session", SessionData.loginRespDto.getSession_id());
				mapHeaders.put("App-Key", RasaApplication.APP_KEY);
				mapHeaders.put("Bank-Id", bankId);
				mapHeaders.put("Trace-No", TraceNum);
				mapHeaders.put("Trace-Date", TraceDate);
				mapHeaders.put("Sandbox", RasaApplication.SANDBOX);
				mapHeaders.put("Token-Id", SessionData.loginBoomRespDto.getToken());

				return mapHeaders;

			}
		};

		NetworkServiceQueue.getInstance().getRequestQueue().add(jsonObjectRequest);

	}


}
