package com.example.app1.day1;

import com.example.app1.R;
import com.example.app1.R.id;
import com.example.app1.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class Intent1Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent1);
		
		Button buttonContent = (Button) findViewById(R.id.buttonContent);
		Button buttonCopyright = (Button) findViewById(R.id.buttonCopyright);
		
		buttonContent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startContent();
			}
		});
		
		buttonCopyright.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startCopyright();
			}
		});

		String[] urls = {"http://www.orf.at/m", "http://www.wifiwien.at", "http://m.kurier.at"};
		
		ListView lv = (ListView) findViewById(R.id.listViewIntent);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, urls);
		
		lv.setAdapter(aa);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String url = ((TextView)arg1).getText().toString();
				startBrowser(url);
			}
		});
		
		Button buttonCountry = (Button) findViewById(R.id.buttonCountry);
		
		buttonCountry.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startIntent2();
			}
		});
		
		Button buttonDial = (Button) findViewById(R.id.buttonDial);
		
		buttonDial.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dial();
			}
		});

	}

	public void dial() {
		EditText et = (EditText) findViewById(R.id.editNumber);
		String number = et.getText().toString();
		
		Intent dialer = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
		startActivity(dialer);
	}
	public void startContent() {
		Intent content = new Intent(this, ContentActivity.class);
		startActivity(content);
	}
	public void startCopyright() {
		Intent copyright = new Intent(this, CopyrightActivity.class);
		startActivity(copyright);
	}
	
	public void startBrowser(String url) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		startActivity(browserIntent);
	}
	
	public void startIntent2() {
		Intent intent2 = new Intent(this, Intent2Activity.class);
		startActivityForResult(intent2, 4711);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == 4711) {
				String country = (String) data.getExtras().get("COUNTRY");
				TextView labelCountry = (TextView) findViewById(R.id.labelCountry);
				labelCountry.setText(country);
				Log.v("Intent1Activity", "labelCountry set to:" + country);
			}
			else {
				Log.v("Intent1Activity", "Request code unknown:" + requestCode);
			}
		}
		else {
			Log.v("Intent1Activity", "Result is " + resultCode);
		}
	}
	
}
