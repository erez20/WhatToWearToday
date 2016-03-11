package com.kiss.hackathontlv.whattoweartoday;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;
import com.kiss.hackathontlv.whattoweartoday.internet.CityInfoFromInternet;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CityInfoFromInternet.onCityRerieveListener {

    private ImageView citySendIV;
    private ImageView notificationTimeIV;
    private EditText cityET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolBar();

        cityET = (EditText)findViewById(R.id.cityET);
        CityDetails cityDetails = CityDetails.retrieveFromPrfences(this);
        if (cityDetails != null)
            cityET.setText(cityDetails.getName());
        citySendIV = (ImageView)findViewById(R.id.cityIV);
        notificationTimeIV = (ImageView) findViewById(R.id.notificationTimeIV);
        citySendIV.setOnClickListener(this);
        notificationTimeIV.setOnClickListener(this);

    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.action_settings));
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        if (citySendIV == v) {
            CityInfoFromInternet.getCityInfoFromInternet(this, cityET.getText().toString());
        } else  if (notificationTimeIV == v) {
//            TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
//            AlarmManager alarmMgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//            Calendar calendar = Calendar.getInstance();
//            calendar.setTimeInMillis(System.currentTimeMillis());
//            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getCurrentHour());
//            calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());
//            Intent intentAlarm = new Intent(this, AlarmReceiver.class);
//
//            // Get the Alarm Service.
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//            // Set the alarm for a particular time.
//            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
//            Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();
        }

    }



    @Override
    public void onCityRetrieveOK(CityDetails cityDetails) {
        cityET.setText(cityDetails.getName());
        cityDetails.saveToPrefences(this);
    }


    @Override
    public void onCityRetrieveError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNetworkError(String Error) {
    }
}
