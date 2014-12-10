package com.example.app1.day2;

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

public class DialogDimensionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dimension);
		
		Button buttonSaveDimension = (Button) findViewById(R.id.buttonSaveDimension);
		buttonSaveDimension.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}

	@Override
	public void finish() {
		Intent back = new Intent();
		EditText width = (EditText) findViewById(R.id.dimWidth);
		EditText length = (EditText) findViewById(R.id.dimLength);
		back.putExtra("WIDTH", width.getText().toString());
		back.putExtra("LENGTH", length.getText().toString());
		setResult(RESULT_OK, back);
		
		super.finish();
	}


}
