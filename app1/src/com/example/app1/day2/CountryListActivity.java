package com.example.app1.day2;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class CountryListActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String[] countries = {"Österreich", "Ungarn", "Deutschland", "Schweiz"};
		
		ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countries);
		setListAdapter(aa);
	}

}
