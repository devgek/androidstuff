package com.example.app1.day3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.app1.R;

public class NotificationReceiverActivity extends Activity {
	private DBNoteAdapter dbAdapter = new DBNoteAdapter(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notication_receiver);
		
		int id = getIntent().getExtras().getInt("id", 4);

		Note note = getNote(id);
		
		setSender(note.getSender());
		setDescription(note.getDescription());
		setConent(note.getContent());
	}

	private Note getNote(int id) {
		dbAdapter.open();
		Note note = dbAdapter.getNote(id);
		dbAdapter.close();
		
		return note;
	}

	private void setConent(String content) {
		TextView tv = (TextView)findViewById(R.id.txContent);
		tv.setText(content);
	}

	private void setDescription(String description) {
		TextView tv = (TextView)findViewById(R.id.txDescription);
		tv.setText(description);
	}

	private void setSender(String sender) {
		TextView tv = (TextView)findViewById(R.id.txSender);
		tv.setText(sender);
	}

}
