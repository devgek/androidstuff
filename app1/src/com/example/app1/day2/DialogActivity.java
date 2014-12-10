package com.example.app1.day2;

import com.example.app1.R;
import com.example.app1.R.drawable;
import com.example.app1.R.id;
import com.example.app1.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DialogActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog);
		
		Button buttonWait = (Button)findViewById(R.id.buttonWait);
		buttonWait.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showProgressDialog();
			}
		});
		
		Button buttonSave = (Button)findViewById(R.id.buttonSave);
		buttonSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showAlertDialog();
			}
		});

		Button buttonDimension = (Button)findViewById(R.id.buttonDimension);
		buttonDimension.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showDimensionDialog();
			}
		});
		
		Button buttonPrice = (Button)findViewById(R.id.buttonPrice);
		buttonPrice.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				showResult();
			}
		});
		
}

	public void showResult() {
		EditText etPrice = (EditText) findViewById(R.id.price);
		EditText etQuantity = (EditText) findViewById(R.id.quantity);
		String price = etPrice.getText().toString();
		String quantity = etQuantity.getText().toString();
		int iPrice = Integer.parseInt(price);
		int iQuantity = Integer.parseInt(quantity);
		int iResult = iPrice * iQuantity;
		String result = Integer.valueOf(iResult).toString();
		
		showToast("Preis: " + price + ", Menge: " + quantity + ", Gesamt: " + result);
	}
	
	public void showDimensionDialog() {
		Intent intent = new Intent(this, DialogDimensionActivity.class);
		startActivityForResult(intent, 4711);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == 4711) {
				String length = (String) data.getExtras().get("LENGTH");
				String width = (String) data.getExtras().get("WIDTH");
				TextView labelDimension = (TextView) findViewById(R.id.labelDimension);
				labelDimension.setText(length + "/" + width);
				Log.v("Intent1Activity", "labelDimension set to:" + labelDimension.getText().toString());
			}
			else {
				Log.v("Intent1Activity", "Request code unknown:" + requestCode);
			}
		}
		else {
			Log.v("Intent1Activity", "Result is " + resultCode);
		}
	}

	public void showProgressDialog() {
		ProgressDialog pd = new ProgressDialog(DialogActivity.this);
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		pd.setMessage("Bitte warten");
		pd.setIcon(R.drawable.ic_launcher);
		pd.setButton(DialogInterface.BUTTON_POSITIVE, "Abbruch",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.cancel();
					}
				});
		pd.setButton(DialogInterface.BUTTON_NEGATIVE, "Weiter",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						showToast("weiter warten");
						arg0.cancel();
					}
				});
		pd.show();
	}
	
	public void showAlertDialog() {
		AlertDialog.Builder ad = new AlertDialog.Builder(DialogActivity.this);
		ad.setTitle("Speichern");
		ad.setMessage("Wirklich Speichern");
		ad.setPositiveButton("Ja",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						showToast("Gespeichert!");
						arg0.cancel();

					}
				});
		ad.setNegativeButton("Nein",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						arg0.cancel();
					}
				});
		AlertDialog ad_echt = ad.create();
		ad_echt.show();
	}
	
	public void showToast(String text) {
		Toast.makeText(DialogActivity.this, text, Toast.LENGTH_SHORT).show();
	}
}
