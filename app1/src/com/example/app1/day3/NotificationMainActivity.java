package com.example.app1.day3;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.app1.R;

public class NotificationMainActivity extends Activity {
	private DBNoteAdapter dbAdapter = new DBNoteAdapter(this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.notification_main);
		
		Button buttonCreateNotificationData = (Button) findViewById(R.id.buttonCreateNotificationData);
		buttonCreateNotificationData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				createNotificationData();
			}
		});

		Button buttonCreateNotification = (Button) findViewById(R.id.buttonCreateNotification);
		buttonCreateNotification.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				dbAdapter.open();
				Note note = dbAdapter.getNote(1);
				dbAdapter.close();
				createNotification(note);
			}
		});
	}

	public void createNotificationData() {
		dbAdapter.open();
		
		Note n1 = new Note(0, "a@b.c", "Erinnerung", "Ihre Bestellung ist angekommen.");
		Note n2 = new Note(0, "hugo@maxi.com", "Fertig", "Ihr Produkt ist fertig, bitte abholen.");
		Note n3 = new Note(0, "x@y.c", "Nachricht", "Kommen Sie bitte sofort.");
		Note n4 = new Note(0, "gek1@gmx.at", "Geschafft", "Das Beispiel wurde soeben fertig implementiert.");
		dbAdapter.insertNote(n1);
		dbAdapter.insertNote(n2);
		dbAdapter.insertNote(n3);
		dbAdapter.insertNote(n4);
		
		dbAdapter.close();
		
		Toast.makeText(this, "Daten wurden angelegt.", Toast.LENGTH_SHORT).show();
	}
	public void createNotification(Note note) {
		NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		Intent intent = new Intent(this, NotificationReceiverActivity.class);
		intent.putExtra("id", note.getId());

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

		Notification notification = new Notification(R.drawable.ic_launcher, "Neue Nachricht", System.currentTimeMillis());

		notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE;
		notification.tickerText = "Neue Nachricht";
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.number++;

		notification.setLatestEventInfo(this, "Nachricht", "Es ist eine neue Nachricht vorhanden, id=" + note.getId() , pendingIntent);

		RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.notification_layout);
		notification.contentView = rv;
		notification.contentView.setTextViewText(R.id.tvNotification1, note.getSender());
		notification.contentView.setTextViewText(R.id.tvNotification2, note.getDescription());
		notification.contentIntent = pendingIntent;

		notifyManager.notify(3, notification);
		// notifyManager.cancel(0);
	}
}
