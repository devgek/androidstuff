package com.example.app1.day1;

import com.example.app1.R;
import com.example.app1.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Aktiv2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aktiv2);
	}

	@Override
	protected void onPause() {
		Log.v("Aktiv2", "onPause reached!");
		super.onPause();
	}

	@Override
	protected void onStart() {
		Log.v("Aktiv2", "onStart reached!");
		super.onStart();
	}

}
