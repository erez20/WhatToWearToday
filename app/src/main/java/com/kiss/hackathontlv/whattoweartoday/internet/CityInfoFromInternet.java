package com.kiss.hackathontlv.whattoweartoday.internet;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kiss.hackathontlv.whattoweartoday.Data.CityDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by erez on 07/03/16.
 */
public class CityInfoFromInternet {

//    private static final String baseUrlString = "https://maps.googleapis.com/maps/api/geocode/json?&address=";
    private static final String baseUrlString = "maps.googleapis.com/maps/api/geocode/json?&address=";

    public interface onCityRerieveListener {
        public void onCityRetrieveOK(CityDetails cityDetails);

        public void onCityRetrieveError(String error);

        public void onNetworkError(String Error);

    }

    public static void getCityInfoFromInternet(final onCityRerieveListener listener, String cityFromUser) {
        try {
            URI uri = new URI("http",baseUrlString, "/"+cityFromUser+"/","");
            URL url = uri.toURL();
            RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
            StringRequest stringRequestText = new StringRequest(
                    Request.Method.GET,
                    url.toExternalForm(),
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            handleOKResponse(listener, response);
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static void handleOKResponse(onCityRerieveListener listener, String response) {
        try {
            JSONObject mainJsonObject = new JSONObject(response);
            if (mainJsonObject.getString("status").equals("OK")) {
                JSONArray resultJsonArray = mainJsonObject.getJSONArray("results");
                JSONObject resultesFirstItemJson = (JSONObject) resultJsonArray.get(0);
                String formattedCity = resultesFirstItemJson.getString("formatted_address");
                JSONObject geometryJSONObject = resultesFirstItemJson.getJSONObject("geometry");
                JSONObject locationJsonObject = geometryJSONObject.getJSONObject("location");
                String lat =locationJsonObject.getString("lat");
                String lng = locationJsonObject.getString("lng");
                listener.onCityRetrieveOK(new CityDetails(formattedCity, lat, lng));

            } else {
                listener.onCityRetrieveError("Invalid city name");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
