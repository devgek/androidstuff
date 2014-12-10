package com.example.app1.day2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.app1.R;
import com.example.app1.R.id;
import com.example.app1.R.layout;
import com.example.app1.R.raw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FilesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.files);
		
		ListView lv = (ListView) findViewById(R.id.listViewFirstNames);
		String[] firstNames = getFirstNames();
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, firstNames);
		lv.setAdapter(aa);
		
		Button buttonSavePass = (Button) findViewById(R.id.buttonSavePass);
		buttonSavePass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				EditText et = (EditText) findViewById(R.id.editPass);
				savePass(et.getText().toString());
			}
		});
		
		Button buttonLoadPass = (Button) findViewById(R.id.buttonLoadPass);
		buttonLoadPass.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showPass();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem item = menu.add("switch to orf.at");
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.orf.at/m"));
		item.setIntent(browserIntent);
		return super.onCreateOptionsMenu(menu);
	}

	private void savePass(String pass) {
		FileOutputStream fos = null;
		try {
			fos = openFileOutput("pw.txt", Context.MODE_PRIVATE);
			fos.write(pass.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void showPass() {
		int size = 0;
		byte[] buffer = null;

		FileInputStream fis = null;
		try {
			fis = openFileInput("pw.txt");
			size = fis.available();
			buffer = new byte[size];
			fis.read(buffer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String pass = new String(buffer);
		Toast.makeText(getApplicationContext(), pass, Toast.LENGTH_SHORT).show();
	}

	private String[] getFirstNames() {
		List<String> l = new ArrayList<String>();
		
		InputStream datei = getResources().openRawResource(R.raw.vornamen);
		BufferedReader r = new BufferedReader(new InputStreamReader(datei));
		String zeile;
		try {
			while ((zeile = r.readLine()) != null) {
				l.add(zeile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return l.toArray(new String[l.size()]);
	}

	@Override
	protected void onPause() {
		saveState();
		super.onPause();
	}

	private void saveState() {
		EditText et1 = (EditText) findViewById(R.id.editText1);
		SharedPreferences meinePrefs = getSharedPreferences("files_prefs", Activity.MODE_PRIVATE);
		SharedPreferences.Editor edit = meinePrefs.edit();
		edit.putString("editText1", et1.getText().toString());
		edit.putString("maxi", "susi");
		edit.commit();
	}

	@Override
	protected void onResume() {
		loadState();
		super.onResume();
	}

	private void loadState() {
		SharedPreferences meinePrefs = getSharedPreferences("files_prefs", Activity.MODE_PRIVATE);

		EditText et1 = (EditText) findViewById(R.id.editText1);
		et1.setText(meinePrefs.getString("editText1", "not set"));
	}

}
