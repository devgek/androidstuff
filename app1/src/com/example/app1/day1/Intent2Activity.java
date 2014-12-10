package com.example.app1.day1;

import com.example.app1.R;
import com.example.app1.R.id;
import com.example.app1.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Intent2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent2);
		
		Button buttonBack = (Button) findViewById(R.id.buttonBackToIntent1);
		buttonBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				finish();
			}
		});
	}

	@Override
	public void finish() {
		Intent back = new Intent(getApplicationContext(), Intent1Activity.class);
		EditText country = (EditText) findViewById(R.id.editCountry);
		back.putExtra("COUNTRY", country.getText().toString());
		setResult(RESULT_OK, back);
		super.finish();
	}
}
