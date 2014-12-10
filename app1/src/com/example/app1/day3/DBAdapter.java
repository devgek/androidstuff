package com.example.app1.day3;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
	final Context context;
	final String TABELLEN_NAME = "mitarbeiter";

	DatenbankHelper DBHelper;
	SQLiteDatabase db;

	public DBAdapter(Context ctx) {
		this.context = ctx;
		DBHelper = new DatenbankHelper(context);
	}

	public DBAdapter open() {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		DBHelper.close();
	}

	public long insertMitarbeiter(String vorname, String nachname, String abteilung, Integer gehalt) {
		ContentValues zeile = new ContentValues();
		zeile.put("vorname", vorname);
		zeile.put("nachname", nachname);
		zeile.put("abteilung", abteilung);
		zeile.put("gehalt", gehalt);
		return db.insert(TABELLEN_NAME, null, zeile);
	}

	public boolean deleteMitarbeiter(long id) {
		return db.delete(TABELLEN_NAME, "_id=" + id, null) > 0;
	}

	public Cursor alleMitarbeiter() {
		return db.query(TABELLEN_NAME, new String[] { "_id", "vorname",
				"nachname", "abteilung", "gehalt" }, null, null, null, null, null);
	}

	public Cursor einMitarbeiter(int id) {
		Cursor curs = db.query(TABELLEN_NAME, new String[] { "_id", "vorname",
				"nachname", "abteilung", "gehalt" }, "_id=" + id, null, null, null, "nachname");
		if (curs != null) {
			curs.moveToFirst();

		}
		return curs;

	}

	private static class DatenbankHelper extends SQLiteOpenHelper {
		DatenbankHelper(Context context) {
			super(context, "db1", null, 1);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("create table mitarbeiter(_id integer primary key autoincrement, vorname text not null, nachname text not null, abteilung text not null, gehalt integer not null);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("drop table if exists mitarbeiter");
			onCreate(db);
		}

	}

}
