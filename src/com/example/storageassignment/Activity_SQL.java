package com.example.storageassignment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_SQL extends Activity {

	EditText blogMessage;
	SQLDatabaseAdapter sqlHelperObj;
	Button save, cancel;
	SQLiteDatabase sqlDatabase ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sql);
		sqlDatabase = sqlHelperObj.getWritableDatabase();
		blogMessage = (EditText) findViewById(R.id.editTextBlog);
		sqlHelperObj = new SQLDatabaseAdapter(this);
		
		save = (Button) findViewById(R.id.buttonSaveSQL);
		save.setOnClickListener(new OnClickListener() {
			
			@SuppressLint({ "SimpleDateFormat", "ShowToast" })
			@Override
			public void onClick(View v) {
				
				long row_id = addMessage();
				Intent main_1 = new Intent(Activity_SQL.this, MainActivity.class);
				if (row_id > 0) {
					Toast.makeText(Activity_SQL.this,
							"Data Entry Successful\nRow No: " + String.valueOf(row_id),
							Toast.LENGTH_LONG).show();
					SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm");
					String date = df.format(Calendar.getInstance().getTime());
					main_1.putExtra("fromSQL", "SQL 1, " + date);
				}
				else {
					Toast.makeText(Activity_SQL.this, "Data Entry Unsuccessful", Toast.LENGTH_LONG).show();
					main_1.putExtra("fromSQL", "N/A");
				}
				startActivity(main_1);
			}
		});

	}

	public long addMessage() {
		String message = blogMessage.getText().toString();
		ContentValues contentValues = new ContentValues();
		contentValues.put(SQLDatabaseAdapter.BLOG, message);
		long id = sqlDatabase.insert(SQLDatabaseAdapter.TABLE_NAME, null, contentValues);
		return id;
	}
}
