package com.example.app1.day4;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.app1.R;

public class SMSMainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.sms_main);
		
		Button butSendIntent = (Button) findViewById(R.id.butSmsSendIntent);
		butSendIntent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sendSmsWithIntent();
			}
		});
		
		Button butSendProg = (Button) findViewById(R.id.butSmsSendProg);
		butSendProg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sendSmsWithProgram();
			}
		});
		
		Button butSendSelf = (Button) findViewById(R.id.butSmsSendSelf);
		butSendSelf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				sendMailToMe();
			}
		});
	}

	public void sendSmsWithIntent() {
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
		intent.putExtra("address", getAddress());
		intent.putExtra("sms_body", "Servas du Wappler!");
		intent.setType("vnd.android-dir/mms-sms");
		
		startActivity(intent);
	}
	
	public void sendSmsWithProgram() {
		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage(getAddress(), null, "Servas Oida!", null, null);
	}
	
	public void sendMailToMe() {
		String[] empf = { "office@wifiwien.at", "info@wifiwien.at" };
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.putExtra(Intent.EXTRA_EMAIL, empf);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Erinnern an " + getMessage());
		emailIntent.putExtra(Intent.EXTRA_TEXT, "nicht vergessen: " + getMessage());
		emailIntent.setType("message/rfc822");
		startActivity(Intent.createChooser(emailIntent, "E-Mail :"));	}
	
	public String getAddress() {
		EditText et = (EditText) findViewById(R.id.etSmsAddress);
		return et.getText().toString();
	}
	
	public String getMessage() {
		EditText et = (EditText) findViewById(R.id.etSmsMessage);
		return et.getText().toString();
	}
}
