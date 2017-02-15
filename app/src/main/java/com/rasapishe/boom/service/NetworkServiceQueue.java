package com.rasapishe.boom.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.rasapishe.boom.RasaApplication;

public class NetworkServiceQueue {

	private static NetworkServiceQueue mInstance;
	private static Context context;
	private RequestQueue mRequestQueue;

	private NetworkServiceQueue(Context context) {
		this.context = context;
		mRequestQueue = getRequestQueue();
	}

	public static synchronized NetworkServiceQueue getInstance() {
		if (null == mInstance) {
			mInstance = new NetworkServiceQueue(RasaApplication.applicationContext);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		try {
			if (mRequestQueue == null) {
				mRequestQueue = Volley.newRequestQueue(context);
				mRequestQueue.start();
			}
			return mRequestQueue;
		} catch (Exception e) {
			Log.e("Initial Exception", "Can not init ssl applicationContext", e);
		}
		return null;
	}


}
