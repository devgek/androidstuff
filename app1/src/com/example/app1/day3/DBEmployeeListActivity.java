package com.example.app1.day3;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class DBEmployeeListActivity extends ListActivity {
	DBAdapter dbAdapter = new DBAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String division = (String) getIntent().getExtras().get("DIVISION");
		
		
		List<String> employees = getEmployees(division);
		if (employees.isEmpty()) {
			Toast.makeText(this, "Keine Daten vorhanden", Toast.LENGTH_SHORT).show();
		}
		
		ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, employees.toArray(new String[employees.size()]));
		setListAdapter(aa);
	}

	private List<String> getEmployees(String division) {
		List<String> l = new ArrayList<String>();
		
		dbAdapter.open();
		Cursor cursor = dbAdapter.alleMitarbeiter();
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String div = cursor.getString(3);
				if (division.equals(div)) {
					l.add(cursor.getString(1) + " " + cursor.getString(2));
				}
			}
		}
		
		dbAdapter.close();
		return l;
	}

}
