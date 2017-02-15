package com.rasapishe.boom.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.gson.Gson;
import com.rasapishe.boom.RasaApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Mohammadreza on 8/16/15 AD.
 */
public class ServiceUtil {

	private static Typeface bYekanTypeface;
	private static Gson gson = new Gson();


	public static boolean isInternetAccess() {

		ConnectivityManager cm = (ConnectivityManager) RasaApplication.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null);
	}

	public static String getIMEI() {
		TelephonyManager tManager = (TelephonyManager) RasaApplication.applicationContext.getSystemService(Context.TELEPHONY_SERVICE);
		String uid = tManager.getDeviceId();
		return uid;

	}

	public static void setTypeFaces(Typeface typeFace, ViewGroup parent) {
		for (int i = 0; i < parent.getChildCount(); i++) {
			View v = parent.getChildAt(i);
			if (v instanceof ViewGroup) {
				setTypeFaces(typeFace, (ViewGroup) v);
			} else if (v instanceof TextView) {
				TextView tv = (TextView) v;
				tv.setTypeface(typeFace);
				// For making the font anti-aliased.
				tv.setPaintFlags(tv.getPaintFlags() | Paint.SUBPIXEL_TEXT_FLAG);
			}
		}
	}

	public static boolean isAppForground(Context context) {

		ActivityManager mActivityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningAppProcessInfo> l = mActivityManager
				.getRunningAppProcesses();
		Iterator<ActivityManager.RunningAppProcessInfo> i = l.iterator();
		while (i.hasNext()) {
			ActivityManager.RunningAppProcessInfo info = i.next();

			if (info.uid == context.getApplicationInfo().uid && info.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}
		return false;
	}

	public static String getCurrentDate() {

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println("Date: " + strDate);
		return strDate;
	}

   /* public static String getPersianDate(String dateStr) {
        String out;
        if (dateStr == null || dateStr.equals(""))
            return "--";
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
            PersianCalendar persianCalendar = new PersianCalendar();


            *//** convert date to persian calendar *//*
            Date date = sdfDate.parse(dateStr);
            persianCalendar.setTime(date);
            String calendarStr = persianCalendar.getPersianWeekDayName() + " " + persianCalendar.getPersianShortDate();

            */

	/**
	 * convert date to Time
	 *//*
	        sdfDate = new SimpleDateFormat("HH:mm");
            String time = sdfDate.format(date);

            out = calendarStr + " ساعت: " + time;
            System.out.println(out);
            return out;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "خطا";

    }*/
	public static String amountSplitter(String amount) {

		if (TextUtils.isEmpty(amount))
			return "۰";
		// remove all comma
		amount = amount.replaceAll("،", "");
		int length = amount.length();

		String amountWithSplitter = amount;

		String beforComma;
		String afterComma;

		int insertIndex = 3;

		if (length >= 4) {
			for (int i = 0; i < (length - 1) / 3; i++)

			{
				beforComma = amountWithSplitter.substring(0, length - insertIndex);
				afterComma = amountWithSplitter.substring(length - insertIndex, amountWithSplitter.length());
				amountWithSplitter = beforComma + "،" + afterComma;

				insertIndex = insertIndex + 3;

			}
		}
		return amountWithSplitter;


	}//end amountSplitter

	public static JSONObject getJsonObject(Object object) {

		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(gson.toJson(object));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}


	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) return false;
		}
		return true;
	}


	public static void setFocus(View view, Context context) {
		view.requestFocus();
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
	}

	public static void removeFocus(View view, Context context) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	public static boolean validateTaaminId(String billId) {

		boolean matches = Pattern.compile("^[0-9]{29}$").matcher(billId).matches();
		if (!matches)
			return false;

		int[] d = new int[29];
		for (int i = 0; i < billId.length(); i++) {
			d[i] = Integer.parseInt(billId.substring(i, i + 1));
		}
		int d16 = d[0] * 2 + d[1] * 3 + d[2] * 4 + d[3] * 5 + d[4] * 6 + d[5] * 7 + d[6] * 2 + d[7] * 3 + d[8] * 4
				+ d[9] * 5 + d[10] * 6 + d[11] * 7 + d[12] * 2 + d[13] * 3 + d[14] * 4;
		d16 = d16 % 11;
		if (d16 == 10)
			d16 = 0;
		if (d16 != d[15])
			return false;

		int d17 = d[0] * 3 + d[1] * 4 + d[2] * 5 + d[3] * 3 + d[4] * 4 + d[5] * 5 + d[6] * 3 + d[7] * 4 + d[8] * 5 +
				d[9] * 3 + d[10] * 4 + d[11] * 5 + d[12] * 3 + d[13] * 4 + d[14] * 5 + d[15] * 6 + d[17] * 7 + d[18] * 2 + d[19] * 3
				+ d[20] * 4 + d[21] * 5 + d[22] * 6 + d[23] * 7 + d[24] * 2 + d[25] * 3 + d[26] * 4 + d[27] * 3 + d[28] * 4;
		d17 = d17 % 11;
		if (d17 == 10)
			d17 = 0;
		if (d17 != d[16])
			return false;

		System.out.println("");
		//every thing is fine
		return true;
	}
}

