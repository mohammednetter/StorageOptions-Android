package com.example.storageassignment;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_SharedPref extends Activity {

	Button save, cancel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_pref);

		save = (Button) findViewById(R.id.buttonSave);
		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				saveSharedPref();

			}
		});

		cancel = (Button) findViewById(R.id.ButtonCancel);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	protected void saveSharedPref() {

		EditText bookName = (EditText) findViewById(R.id.editTextBookName);
		EditText bookAuthor = (EditText) findViewById(R.id.EditTextBookAuthor);
		EditText bookDescription = (EditText) findViewById(R.id.editTextDescription);

		SharedPreferences sharedPref = getSharedPreferences("BookData",
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		editor.putString("BookName", bookName.getText().toString());
		editor.putString("BookAuthor", bookAuthor.getText().toString());
		editor.putString("BookDescription", bookDescription.getText()
				.toString());
		editor.commit();
		Toast.makeText(getApplicationContext(), "Data was saved successfully",
				Toast.LENGTH_LONG).show();
		
		bookName.setText("");
		bookAuthor.setText("");
		bookDescription.setText("");
		SimpleDateFormat df = new SimpleDateFormat("EEE, dd MMM yyyy, HH:mm");
		String date = df.format(Calendar.getInstance().getTime());
		
		Intent main = new Intent(Activity_SharedPref.this, MainActivity.class);
		main.putExtra("fromPref", "Shared Preference 1, " + date);
		startActivity(main);
	}

}
