package com.kiss.hackathontlv.whattoweartoday.internet;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kiss.hackathontlv.whattoweartoday.MyApplication;

/**
 * Created by erez on 06/03/16.
 */
public class VolleySingleton {
    private static VolleySingleton instance = null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    public interface onVolleyResponseListener {
        public void onResponse(String response);
        public void onErrorResponse(String message);
    }

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            private LruCache<String, Bitmap> cache =
                    new LruCache<>((int)Runtime.getRuntime().maxMemory()/1024/8);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance() {
        if (instance == null) {
            instance = new VolleySingleton();
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public void fetchURL(final onVolleyResponseListener listener, String URL) {
        RequestQueue requestQueue = VolleySingleton.getInstance().getRequestQueue();
        StringRequest stringRequestText = new StringRequest(
                Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        listener.onResponse(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onErrorResponse(error.getMessage());
                    }
                }
        );
        requestQueue.add(stringRequestText);

    }

}
