package com.example.storageassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.widget.Toast;

public class SQLDatabaseAdapter extends SQLiteOpenHelper {

	public SQLDatabaseAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	static Context context;

	public static final String DATABASE_NAME = "mySQLDatabase.db";
	public static final String TABLE_NAME = "table1";
	public static final String UID = "_id";
	public static final String BLOG = "Blog_Message";
	public static final int DATABASE_VERSION = 1;

	public long insert(String message) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(BLOG, message);
		long id = db.insert(TABLE_NAME, null, contentValues);
		return id;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		try {
			db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + UID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + BLOG
					+ " VARCHAR(3000));");
			Toast.makeText(context, "onCreateDB Called", Toast.LENGTH_LONG)
					.show();
		} catch (SQLException e) {
			e.printStackTrace();
			Toast.makeText(context, "exception onCreate", Toast.LENGTH_LONG)
					.show();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		try {
			db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
			onCreate(db);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
