package com.example.app1.day3;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.app1.R;

public class CustomTableLayout extends TableLayout {
	private EditText firstName;
	private EditText lastName;
	private CheckBox married;
	private Button create;
	
	public CustomTableLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public CustomTableLayout(Context context) {
		super(context);
		init();
	}

	public void init() {
		LayoutInflater flater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		flater.inflate(R.layout.custom_table, this, true);
		
		firstName = (EditText) findViewById(R.id.custumEditText1);
		lastName = (EditText) findViewById(R.id.custumEditText2);
		married = (CheckBox) findViewById(R.id.custumCheckBox1);
		create = (Button) findViewById(R.id.buttonCustomTable);

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				doCreate();
			}
		});
	}
	
	public void doCreate() {
		String firstName = this.firstName.getText().toString();
		String lastName = this.lastName.getText().toString();
		boolean isMarried = this.married.isChecked();
		
		StringBuffer buf = new StringBuffer();
		buf.append(firstName);
		buf.append("/");
		buf.append(lastName);
		buf.append("/");
		buf.append(isMarried ? "verheiratet" : "ledig");
		
		Toast.makeText(getContext(), buf.toString(), Toast.LENGTH_LONG).show();
	}
}
