package com.kiss.hackathontlv.whattoweartoday.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ToolbarWidgetWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;
import com.kiss.hackathontlv.whattoweartoday.MainActivity;
import com.kiss.hackathontlv.whattoweartoday.R;
import com.kiss.hackathontlv.whattoweartoday.internet.CityInfoFromInternet;

import java.util.TooManyListenersException;

/**
 * Created by erez on 07/03/16.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener, CityInfoFromInternet.onCityRerieveListener {
    private MainActivity mainActivity;
    ImageView citySendIV;
    EditText cityET;
    public SettingsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mainActivity = (MainActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setToolbarNavigationIcon(getNavigationIcon());
        mainActivity.isToolbarNavigtionOpenDrawer(false);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // View root = inflater.inflate(R.layout.fragment_settings, null);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        cityET = (EditText) root.findViewById(R.id.cityET);
        CityDetails cityDetails = CityDetails.retrieveFromPrfences(mainActivity);
        if (cityDetails != null)
            cityET.setText(cityDetails.getName());
        citySendIV = (ImageView) root.findViewById(R.id.cityIV);
        citySendIV.setOnClickListener(this);
        return root;

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
        cityDetails.saveToPrefences(mainActivity);
    }


    @Override
    public void onCityRetrieveError(String error) {
        Toast.makeText(getContext(),error, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNetworkError(String Error) {

    }


    public int getNavigationIcon() {
        return R.drawable.ic_arrow_back_24dp;
    }

}
