package com.example.app1.day3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app1.R;

public class DBActivity extends Activity {
	DBAdapter dbAdapter = new DBAdapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dbinput);
		
		Button buttonDivsion = (Button) findViewById(R.id.buttonDivision);
		buttonDivsion.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View arg0) {
				chooseDivision();
			}
		});
		
		Button buttonSave = (Button) findViewById(R.id.buttonDbSave);
		buttonSave.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View arg0) {
				saveEmployee();
			}
		});
		
		Button buttonShow = (Button) findViewById(R.id.buttonDbShow);
		buttonShow.setOnClickListener(new OnClickListener(
				) {
			
			@Override
			public void onClick(View arg0) {
				showEmployees();
			}
		});
		
	}
	private void saveEmployee() {
		EditText et1 = (EditText) findViewById(R.id.editDbFirst);
		EditText et2 = (EditText) findViewById(R.id.editDbLast);
		EditText et3 = (EditText) findViewById(R.id.editDbSalary);
		
		String first = et1.getText().toString();
		String last = et2.getText().toString();
		String salary = et3.getText().toString();
		String division = getDivision();
		Integer iSalary = Integer.parseInt(salary);
		
		dbAdapter.open();
		long result = dbAdapter.insertMitarbeiter(first, last, division, iSalary);
		dbAdapter.close();
		
		if (result > 0) {
			Toast.makeText(this, "Mitarbeiterdaten wurden gespeichert.", Toast.LENGTH_SHORT).show();
		}
		else {
			Toast.makeText(this, "Mitarbeiterdaten wurden nicht gespeichert.", Toast.LENGTH_SHORT).show();
		}
	}

	private void showEmployees() {
		Intent intent2 = new Intent(this, DBEmployeeListActivity.class);
		intent2.putExtra("DIVISION", getDivision());
		startActivity(intent2);
	}
	
	private void chooseDivision() {
		Intent intent2 = new Intent(this, DBDivisionListActivity.class);
		startActivityForResult(intent2, 4711);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		String division = (String) data.getExtras().get("DIVISION");
		
		setDivision(division);
	}
	
	private String getDivision() {
		EditText et4 = (EditText) findViewById(R.id.editDbDivision);
		return et4 == null ? null : et4.getText().toString();
	}
	
	private void setDivision(String division) {
		EditText et4 = (EditText) findViewById(R.id.editDbDivision);
		if (et4 != null) {
			et4.setText(division);
		}
	}
}
