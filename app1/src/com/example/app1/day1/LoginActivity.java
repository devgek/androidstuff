package com.example.app1.day1;

import com.example.app1.R;
import com.example.app1.R.id;
import com.example.app1.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button loginButton = (Button) findViewById(R.id.loginButton);
		loginButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				checkLogin();
			}

			private void checkLogin() {
				EditText user = (EditText) findViewById(R.id.user);
				EditText pass = (EditText) findViewById(R.id.pass);
				Log.v("LoginA", "user:" + user.getText());
				Log.v("LoginA", "pass:" + pass.getText());
				TextView loginResult = (TextView) findViewById(R.id.loginResult);
				
				if (user.getText() != null && user.getText().toString().equals("homer") &&
						pass.getText() != null && pass.getText().toString().equals("simpson")) {
					startApp();
				}
				else {
					loginResult.setText("Illegal login.");
				}
			}

			private void startApp() {
				Intent urlListActivity = new Intent(getApplicationContext(), UrlListActivity.class);
				startActivity(urlListActivity);
			}
		});
	}

}
