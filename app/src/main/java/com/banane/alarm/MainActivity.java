package com.banane.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


	private static final String TAG = "BIRTHDAY";
	public AlarmManager alarmManager;
	Intent alarmIntent;
	PendingIntent pendingIntent;
	Button bananaButton;
	TextView notificationCount;
	TextView notificationCountLabel;
	int mNotificationCount;
	static final String NOTIFICATION_COUNT = "notificationCount";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		if (savedInstanceState != null) {
	        // Restore value of members from saved state
			mNotificationCount = savedInstanceState.getInt(NOTIFICATION_COUNT);

        }



		bananaButton = (Button)findViewById(R.id.bananaButton);
		notificationCount = (TextView)findViewById(R.id.notificationCount);
		notificationCountLabel = (TextView)findViewById(R.id.notificationCountLabel);

		Button rbtn;
		rbtn = (Button) findViewById(R.id.button2);
		rbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplicationContext(), Wallpaper.class);
				startActivity(i);
			} });

	}
//    public void addListenerOnButton() {
//        Button rbtn;
//        rbtn = (Button) findViewById(R.id.button2);
//        rbtn.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), Wallpaper.class);
//                startActivity(i);
//            } });
//
//    }
	
	public void onSaveInstanceState(Bundle savedInstanceState) {
	    // Save the user's current game state
	    savedInstanceState.putInt(NOTIFICATION_COUNT, mNotificationCount);
	    super.onSaveInstanceState(savedInstanceState);
	}
	
	 @Override
	 protected void onNewIntent( Intent intent ) {
		 Log.i( TAG, "onNewIntent(), intent = " + intent );
	        if (intent.getExtras() != null)
	        {
	            Log.i(TAG, "in onNewIntent = " + intent.getExtras().getString("test"));
	        }
	        super.onNewIntent( intent );
	        setIntent( intent );
	  }

	
	public void triggerAlarm(View v){
		setAlarm();
		bananaButton.setVisibility(View.GONE);
		notificationCountLabel.setVisibility(View.VISIBLE);
		notificationCount.setVisibility(View.VISIBLE);
		notificationCount.setText("0");
	}
	
	public void setAlarm(){
		alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

		alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
		pendingIntent = PendingIntent.getBroadcast(  MainActivity.this, 0, alarmIntent, 0);

		Calendar alarmStartTime = Calendar.getInstance();
		//alarmStartTime.add(Calendar.MINUTE, 2);
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MONTH,4);
		calendar.set(Calendar.YEAR, 2016);
		calendar.set(Calendar.DAY_OF_MONTH, 1);

		calendar.set(Calendar.HOUR_OF_DAY, 14);
		calendar.set(Calendar.MINUTE, 23);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.AM_PM, Calendar.PM);
		//alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), getInterval(), pendingIntent);
	 	//Log.i(TAG,"Alarms set every five minutes.");
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),getInterval(),pendingIntent);

	}
	
	private int getInterval(){
	    int seconds = 60;
	    int milliseconds = 1000;
	    int repeatMS = seconds * 5 * milliseconds;
	    return repeatMS;
	}
	
	@Override 
	protected void onStart(){
		super.onStart();
		updateUI();
	}
	
	public void cancelNotifications(){
		Log.i(TAG,"All notifications cancelled.");
	}
	
	public void updateUI(){
		MyAlarm app = (MyAlarm)getApplicationContext();
		mNotificationCount = app.getNotificationCount();
		notificationCount.setText(Integer.toString(mNotificationCount));
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		if(this.getIntent().getExtras() != null){
			Log.i(TAG,"extras: " + this.getIntent().getExtras());
			updateUI();
			
		}
	}

	

}
