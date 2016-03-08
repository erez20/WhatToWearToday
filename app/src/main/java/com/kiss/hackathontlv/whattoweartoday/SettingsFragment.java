package com.kiss.hackathontlv.whattoweartoday;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;
import com.kiss.hackathontlv.whattoweartoday.internet.cityInfoFromInternet;

/**
 * Created by erez on 07/03/16.
 */
public class SettingsFragment extends Fragment implements View.OnClickListener, cityInfoFromInternet.onCityRerieveInterface {
    private Context context;
    ImageView citySendIV;
    EditText cityET;
    public SettingsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       // View root = inflater.inflate(R.layout.fragment_settings, null);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        cityET = (EditText) root.findViewById(R.id.cityET);
        CityDetails cityDetails = CityDetails.retrieveFromPrfences(context);
        if (cityDetails != null)
            cityET.setText(cityDetails.getName());
        citySendIV = (ImageView) root.findViewById(R.id.cityIV);
        citySendIV.setOnClickListener(this);

        return root;

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
        if (citySendIV == v) {
            cityInfoFromInternet cityRetriever = new cityInfoFromInternet(this, cityET.getText().toString());
        }
    }


    @Override
    public void onCityRetrieveOK(CityDetails cityDetails) {
        cityET.setText(cityDetails.getName());
        cityDetails.saveToPrefences(context);
    }


    @Override
    public void onCityRetrieveError(String error) {
        Toast.makeText(getContext(),error, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNetworkError(String Error) {

    }
}
