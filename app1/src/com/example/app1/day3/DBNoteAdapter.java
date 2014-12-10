package com.example.app1.day3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBNoteAdapter {
	final Context context;
	final String TABELLEN_NAME = "nachricht";

	DatenbankHelper DBHelper;
	SQLiteDatabase db;

	public DBNoteAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatenbankHelper(context);
	}

	public DBNoteAdapter open() {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public long insertNote(Note note) {
		ContentValues zeile = new ContentValues();
		zeile.put("sender", note.getSender());
		zeile.put("betreff", note.getDescription());
		zeile.put("inhalt", note.getContent());
		return db.insert(TABELLEN_NAME, null, zeile);
	}

	public boolean deleteMitarbeiter(long id) {
		return db.delete(TABELLEN_NAME, "_id=" + id, null) > 0;
	}

	public Cursor alleMitarbeiter() {
		return db.query(TABELLEN_NAME, new String[] { "_id", "vorname",
				"nachname", "abteilung", "gehalt" }, null, null, null, null, null);
	}

	public Note getNote(int id) {
		Cursor curs = db.query(TABELLEN_NAME, new String[] { "_id", "sender", "betreff", "inhalt" }, "_id=" + id, null, null, null, null);
		Note note = new Note();
		if (curs != null) {
			curs.moveToFirst();
			note.setId(curs.getInt(0));
			note.setSender(curs.getString(1));
			note.setDescription(curs.getString(2));
			note.setContent(curs.getString(3));
		}
		
		return note;
	}

	private static class DatenbankHelper extends SQLiteOpenHelper {
		DatenbankHelper(Context context) {
			super(context, "db2", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table nachricht(_id integer primary key autoincrement, sender text not null, betreff text not null, inhalt text not null);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists nachricht");
			onCreate(db);
		}

	}

}
