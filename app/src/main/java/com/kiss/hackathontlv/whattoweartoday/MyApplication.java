package com.kiss.hackathontlv.whattoweartoday;

import android.app.Application;
import android.content.Context;

/**
 * Created by erez on 06/03/16.
 */
public class MyApplication extends Application {
    public static MyApplication instace;

    @Override
    public void onCreate() {
        super.onCreate();
        instace = this;
    }

    public static MyApplication getInstance() {
        return instace;
    }

    public static Context getAppContext() {
        return getInstance().getApplicationContext();
    }
}