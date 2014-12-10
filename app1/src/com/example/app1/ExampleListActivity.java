package com.example.app1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.app1.day1.Aktiv2Activity;
import com.example.app1.day1.HelloActivity;
import com.example.app1.day1.Intent1Activity;
import com.example.app1.day1.LoginActivity;
import com.example.app1.day2.DialogActivity;
import com.example.app1.day2.FilesActivity;
import com.example.app1.day2.MenuActivity;
import com.example.app1.day3.CustomViewMainActivity;
import com.example.app1.day3.DBActivity;
import com.example.app1.day3.NotificationMainActivity;
import com.example.app1.day4.SMSMainActivity;
import com.example.app1.day4.WebserviceActivity;
import com.example.app1.home.CardListActivity;
import com.example.app1.home.PositionActivity;

public class ExampleListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] examples = {"ex1_Hello", "ex1_Aktiv2", "ex2_Views", "ex3_Intents", "ex4_Dialogs", "ex5_Menus", "ex6_Files",
				"ex7_SQLite", "ex8_Notifications", "ex9_CustomViews", "ex11_SMS", "ex12_Webservices", "home01_LocationServices", "home02_CardListExample"};
		
		ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.example_list_item, examples);
		setListAdapter(aa);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String example = ((TextView)v).getText().toString();
		if (example.startsWith("home01_")) {
			startExampleActivity(PositionActivity.class);
		}
		else
		if (example.startsWith("home02_")) {
			startExampleActivity(CardListActivity.class);
		}
		else
		if (example.startsWith("ex12_")) {
			startExampleActivity(WebserviceActivity.class);
		}
		else
		if (example.startsWith("ex11_")) {
			startExampleActivity(SMSMainActivity.class);
		}
		else
		if (example.startsWith("ex8_")) {
			startExampleActivity(NotificationMainActivity.class);
		}
		else
		if (example.startsWith("ex7_")) {
			startExampleActivity(DBActivity.class);
		}
		else
		if (example.startsWith("ex9_")) {
			startExampleActivity(CustomViewMainActivity.class);
		}
		else
		if (example.startsWith("ex6_")) {
			startExampleActivity(FilesActivity.class);
		}
		else
		if (example.startsWith("ex5_")) {
			startExampleActivity(MenuActivity.class);
		}
		else
		if (example.startsWith("ex4_")) {
			startExampleActivity(DialogActivity.class);
		}
		else
		if (example.startsWith("ex3_")) {
			startExampleActivity(Intent1Activity.class);
		}
		else
		if (example.startsWith("ex2_")) {
			startExampleActivity(LoginActivity.class);
		}
		else
		if (example.startsWith("ex1_Aktiv2")) {
			startExampleActivity(Aktiv2Activity.class);
		}
		else
		if (example.startsWith("ex1_Hello")) {
			startExampleActivity(HelloActivity.class);
		}
		else
		if (example.startsWith("ex1_Hello")) {
			startExampleActivity(HelloActivity.class);
		}
	}

	private void startExampleActivity(Class activityClass) {
		Intent exampleActivity = new Intent(this, activityClass);
		startActivity(exampleActivity);
	}

}
