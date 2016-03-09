package com.kiss.hackathontlv.whattoweartoday.internet;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by erez on 08/03/16.
 */
public class WeatherFromInternet {
    private static final String baseUrlString = "http://api.openweathermap.org/data/2.5/forecast?";
    private static final String appid = "dc4c6633970461e98ae7bc382eb546a9";
    onWeatherOkInterface listener;

    public interface onWeatherOkInterface {
        public void onWeatherOK(String weatherJSONAsString);

        public void onWeatherError(String Error);
    }


    public WeatherFromInternet(final onWeatherOkInterface listener, CityDetails cityDetails) {
        String url = baseUrlString + "lat=" + cityDetails.getLat() + "&lon=" + cityDetails.getLng() + "&appid=" + appid;
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest stringRequestText = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        listener.onWeatherOK(response);
                    }
                }
                ,
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onWeatherError(error.getMessage());

                    }
                }
        );
        requestQueue.add(stringRequestText);


    }
}
