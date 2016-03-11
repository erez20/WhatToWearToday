package com.kiss.hackathontlv.whattoweartoday.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;
import com.kiss.hackathontlv.whattoweartoday.Data.FiveDaysForcast;
import com.kiss.hackathontlv.whattoweartoday.MainActivity;
import com.kiss.hackathontlv.whattoweartoday.R;
import com.kiss.hackathontlv.whattoweartoday.internet.WeatherFromInternet;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by erez on 09/03/16.
 *
 */
public class MainFragment extends Fragment implements  WeatherFromInternet.onWeatherOkListener, View.OnClickListener {
    MainActivity mainActivity;
    View root;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        super.onAttach(context);
        this.mainActivity = (MainActivity) context;
    }

    @Override
    public void onResume() {
        super.onResume();
        mainActivity.setToolbarNavigationIcon(getNavigationIcon());
        mainActivity.isToolbarNavigtionOpenDrawer(true);

    }

    @Override
    public void onClick(View view) {
        CityDetails cityDetails = CityDetails.retrieveFromPrfences(getContext());
        WeatherFromInternet.getWeatherFromInternet(this, cityDetails);
    }

    @Override
    public void onWeatherOK(String weatherJSONAsString) {
       // Toast.makeText(getContext(), weatherJSONAsString, Toast.LENGTH_SHORT).show();
        try {
            JSONObject JSONWhole = new JSONObject(weatherJSONAsString);
            FiveDaysForcast.getInstance(JSONWhole);
            Log.e("EEEEE", FiveDaysForcast.getInstance(null).whatToWearTodayOrTomorrow().toString());
            Log.e("EEEEE", FiveDaysForcast.getInstance(null).moreDaysWear(1).toString());
            Log.e("EEEEE", FiveDaysForcast.getInstance(null).moreDaysWear(2).toString());
            Log.e("EEEEE", FiveDaysForcast.getInstance(null).moreDaysWear(3).toString());
            Log.e("EEEEE", FiveDaysForcast.getInstance(null).moreDaysWear(4).toString());



        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "JSON PARSING ERROR", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onWeatherError(String Error) {
        Toast.makeText(getContext(), Error, Toast.LENGTH_SHORT).show();


    }
    public int getNavigationIcon() {
        return R.drawable.ic_menu_24dp;
    }

}
