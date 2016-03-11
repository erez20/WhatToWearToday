package com.kiss.hackathontlv.whattoweartoday;

import android.app.ProgressDialog;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;
import com.kiss.hackathontlv.whattoweartoday.internet.CityInfoFromInternet;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, CityInfoFromInternet.onCityRerieveListener, TextView.OnEditorActionListener {

    private ImageView citySendIV;
    private EditText cityET;
    private CoordinatorLayout coordinatorLayout;
    private ProgressDialog progressDialog;
    private Button citySendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initToolBar();

        cityET = (EditText)findViewById(R.id.cityET);
        CityDetails cityDetails = CityDetails.retrieveFromPrfences(this);
        if (cityDetails != null)
            cityET.setText(cityDetails.getName());
        cityET.setOnEditorActionListener(this);
        citySendBtn = (Button)findViewById(R.id.city_btn);
        citySendBtn.setOnClickListener(this);
        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.settings_coordinator_layout);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.searching));

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
        if (citySendBtn == v) {
            progressDialog.show();
            CityInfoFromInternet.getCityInfoFromInternet(this, cityET.getText().toString());
        }
    }


    @Override
    public void onCityRetrieveOK(CityDetails cityDetails) {
        cityET.setText(cityDetails.getName());
        cityDetails.saveToPrefences(this);
        progressDialog.dismiss();
        Snackbar.make(coordinatorLayout, "City Saved Successfully", Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void onCityRetrieveError(String error) {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNetworkError(String Error) {
        progressDialog.dismiss();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (cityET == v) {
            progressDialog.show();
            CityInfoFromInternet.getCityInfoFromInternet(this, cityET.getText().toString());
        }
        return false;
    }
}
