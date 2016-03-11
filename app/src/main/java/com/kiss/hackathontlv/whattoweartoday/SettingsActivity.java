package com.kiss.hackathontlv.whattoweartoday;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;
import com.kiss.hackathontlv.whattoweartoday.internet.CityInfoFromInternet;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CityInfoFromInternet.onCityRerieveListener {

    private ImageView citySendIV;
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
        citySendIV.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        if (citySendIV == v) {
            CityInfoFromInternet.getCityInfoFromInternet(this, cityET.getText().toString());
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
