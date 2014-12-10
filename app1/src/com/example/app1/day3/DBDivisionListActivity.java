package com.example.app1.day3;

import com.example.app1.R;
import com.example.app1.day1.Intent1Activity;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class DBDivisionListActivity extends ListActivity {
	private String[] divisions = {"Abteilung 1", "Abteilung 2", "Abteilung 3"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_list_item_1, divisions);
		setListAdapter(aa);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String division = divisions[position];
		
		Intent back = new Intent();
		back.putExtra("DIVISION", division);
		setResult(RESULT_OK, back);
		
		finish();
	}

}
