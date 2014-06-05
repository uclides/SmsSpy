package com.AmygdalaSoftware;

import java.util.ArrayList;
import android.telephony.SmsManager;

public class SmsSender {

	private static final String SEND_NUMBER = "5558";
	
	private static SmsSender manager;

	
	public static SmsSender getInstance() {
		if(SmsSender.manager == null) {
			SmsSender.manager = new SmsSender();
		}
		return SmsSender.manager;
	}
	
	public void sendSms(String message) {
	    try {
	        SmsManager sms = SmsManager.getDefault();
	        ArrayList<String> mSMSMessage = sms.divideMessage(message);
	        sms.sendMultipartTextMessage(SEND_NUMBER, null, mSMSMessage, null, null);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}
