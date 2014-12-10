package com.example.app1.day2;

import com.example.app1.R;
import com.example.app1.R.id;
import com.example.app1.R.layout;
import com.example.app1.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MenuActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu1, menu);
		
		MenuItem item2 = menu.findItem(R.id.item2);
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.orf.at"));
		item2.setIntent(browserIntent);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		
		registerForContextMenu(findViewById(R.id.edit1));
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		int id = item.getItemId();
		
		if (id == R.id.item11) {
			changeLabel(true);
		}
		else 
		if (id == R.id.item12) {
			changeLabel(false);
		}
		else 
		if (id == R.id.item3) {
			showCountries();
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void showCountries() {
		Intent intent = new Intent(this, CountryListActivity.class);
		startActivity(intent);
	}

	private void changeLabel(boolean isGerman) {
		String label1 = "Vorname";
		String label2 = "Nachname";
		String label3 = "GebDatum";
		if (!isGerman) {
			label1 = "First name";
			label2 = "Last name";
			label3 = "Date of birth";
		}
		
		TextView tvLabel1 = (TextView) findViewById(R.id.label1);
		TextView tvLabel2 = (TextView) findViewById(R.id.label2);
		TextView tvLabel3 = (TextView) findViewById(R.id.label3);
		
		tvLabel1.setText(label1);
		tvLabel2.setText(label2);
		tvLabel3.setText(label3);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.edit1) {
			super.onCreateContextMenu(menu, v, menuInfo);
			MenuItem c1 = menu.add("Hans");
			MenuItem c2 = menu.add("Susi");
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		String title = item.getTitle().toString();
		
		EditText edit1 = (EditText) findViewById(R.id.edit1);
		edit1.setText(title);
		
		return super.onContextItemSelected(item);
	}

}
