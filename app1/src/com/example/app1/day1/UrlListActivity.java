package com.example.app1.day1;

import com.example.app1.R;
import com.example.app1.R.id;
import com.example.app1.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class UrlListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.show_url);
		
		String[] urls = {"http://www.orf.at", "http://www.wifiwien.at", "http://www.entwickler.com"};
		
		ListView lv = (ListView) findViewById(R.id.listView1);
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, urls);
		
		lv.setAdapter(aa);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String url = ((TextView)arg1).getText().toString();
				WebView wv = (WebView) findViewById(R.id.webView1);
				wv.setWebViewClient(new WebViewClient() {

					@Override
					public boolean shouldOverrideUrlLoading(WebView view,
							String url) {
						view.loadUrl(url);
						return false;
					}
					
				});
				wv.getSettings().setJavaScriptEnabled(true);
				wv.loadUrl(url);
			}
		});
		
	}

}
