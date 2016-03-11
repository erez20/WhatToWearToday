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
public class MainFragment extends Fragment  {
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


    public int getNavigationIcon() {
        return R.drawable.ic_menu_24dp;
    }

}
