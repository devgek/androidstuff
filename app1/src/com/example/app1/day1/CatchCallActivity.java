package com.example.app1.day1;

import com.example.app1.R;
import com.example.app1.R.id;
import com.example.app1.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CatchCallActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.catch_call);
		TextView tv = (TextView)findViewById(R.id.calledNumber);
		
		Intent intent = getIntent();
		String calledNumber = intent.getDataString();
		tv.setText(calledNumber);
	}

}
