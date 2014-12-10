package com.example.app1.day4;

import java.util.Date;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Date now = new Date();
		Log.v("SMSReceiver", "Sms arrived at " + now.toString());
	}

}
