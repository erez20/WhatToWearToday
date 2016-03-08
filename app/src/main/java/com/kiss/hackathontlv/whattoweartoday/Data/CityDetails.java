package com.kiss.hackathontlv.whattoweartoday.Data;

import android.content.Context;
import android.content.SharedPreferences;

import com.kiss.hackathontlv.whattoweartoday.MainActivity;

/**
 * Created by erez on 08/03/16.
 */
public class CityDetails {
    private static final String NAME = "NAME";
    private static final String LAT = "LAT";
    private static final String LNG = "LNG";
    private static final String DEFAULT_VALUE = "?";
    String name;
    String lat;
    String lng;

    public CityDetails(String name, String lat, String lng) {
        this.name = name;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public void saveToPrefences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("UserData", MainActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(NAME, name).
                putString(LAT, lat).
                putString(LNG, lng);

        //flush to the file
        editor.commit();
    }

    public static CityDetails retrieveFromPrfences(Context context) {
        SharedPreferences preferences;
        preferences = context.getSharedPreferences("UserData", MainActivity.MODE_PRIVATE);

        //checks if the file exists, wont break if file does not exist
        if (preferences.contains("NAME")) {
            return (new CityDetails(
                    preferences.getString(NAME, DEFAULT_VALUE),
                    preferences.getString(LAT, DEFAULT_VALUE),
                    preferences.getString(LNG, DEFAULT_VALUE)));

        } else {
            return null;
    }

}
}
