package com.kiss.hackathontlv.whattoweartoday.internet;

import android.app.DownloadManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

/**
 * Created by erez on 07/03/16.
 */
public class CityRetriever {

    private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json?&address=";

    public interface onCityRerieveInterface {
        public void onCityRetrieveOK(String retrievedCity);

        public void onCityRetrieveError(String error);

        public void onNetworkError(String Error);
    }

    public CityRetriever(final onCityRerieveInterface listener, String cityFromUser) {
        try {
            URI uri = new URI(URL +cityFromUser);
            RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
            StringRequest stringRequestText = new StringRequest(
                    Request.Method.GET,
                    //   URLEncoder.encode(URL+cityFromUser),
                    uri.toString(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            listener.onCityRetrieveOK(response);
                        }
                    }
                    ,
                    new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            listener.onCityRetrieveError(error.getMessage());

                        }
                    }
            );
            requestQueue.add(stringRequestText);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
