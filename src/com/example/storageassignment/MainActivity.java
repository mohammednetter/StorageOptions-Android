package com.example.storageassignment;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {

	int count_pref = 0, count_SQL = 0;
	Button sharedButton, sqlButton;
	Activity_SQL activity_sql;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView info1 = (TextView) findViewById(R.id.textViewActivity1);
		TextView info2 = (TextView) findViewById(R.id.textViewActivity2);
		//activity_sql = new Activity_SQL();
		sharedButton = (Button) findViewById(R.id.buttonPreferences);
		sharedButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent preferenceActivity = new Intent(MainActivity.this,
						Activity_SharedPref.class);
				startActivity(preferenceActivity);
			}
		});
		
		sqlButton = (Button) findViewById(R.id.buttonSQLLite);
		sqlButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent sqlActivity = new Intent(MainActivity.this,
						Activity_SQL.class);
				
				startActivity(sqlActivity);
			}
		});
	}

	protected void onResume() {
		super.onResume();
		TextView info1 = (TextView) findViewById(R.id.textViewActivity1);
		TextView info2 = (TextView) findViewById(R.id.textViewActivity2);

		try {
			Bundle bundle = getIntent().getExtras();

			String fromPref = bundle.getString("fromPref", "N/A");
			//String fromSQL = bundle.getString("fromSQL", "N/A");

			if (!fromPref.equals("N/A")) {
				String temp = info1.getText().toString();
				info1.setText(fromPref);
				info2.setText(temp);
			} /*else if (!fromSQL.equals("N/A")) {
				String temp = info1.getText().toString();
				info1.setText(fromSQL);
				info2.setText(temp);
			}*/

		} catch (NullPointerException e) {
			info1.setText("N/A");
			info2.setText("N/A");
		}
	}
}
