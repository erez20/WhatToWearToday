package com.kiss.hackathontlv.whattoweartoday.Data;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by erez on 10/03/16.
 */
public class FiveDaysForcast implements WeatherConstant {
    private static FiveDaysForcast instance = null;

    public static synchronized FiveDaysForcast getInstance(JSONObject JSONAsString) {
        if (instance == null)
            instance = new FiveDaysForcast(JSONAsString);
        return instance;
    }


    ArrayList<ThreeHoursForcast> threeHoursForcastList = new ArrayList<ThreeHoursForcast>();


    private FiveDaysForcast(JSONObject json) {
        try {
            JSONArray jsonArrayList = json.getJSONArray("list");
            for (int i = 0; i < jsonArrayList.length(); i++) {

                JSONObject jsonObjectWholeDay = (JSONObject) jsonArrayList.get(i);
                //-----------------------------
                String timeStamp = jsonObjectWholeDay.getString("dt");
                //------------------------------
                JSONObject jsonMain = jsonObjectWholeDay.getJSONObject("main");
                int maxTemp = jsonMain.getInt("temp_max");
                int minTemp = jsonMain.getInt("temp_min");
                //-------------------------------
                JSONArray jsonWeatherArray = jsonObjectWholeDay.getJSONArray("weather");
                JSONObject jsonWeatherFirstObject = (JSONObject) jsonWeatherArray.get(0);

                int id = jsonWeatherFirstObject.getInt("id");
                String skyDescription = jsonWeatherFirstObject.getString("description");
                Log.e("moshe", timeStamp);
                threeHoursForcastList.add(new ThreeHoursForcast(timeStamp, id, skyDescription, maxTemp, minTemp));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public WhatToWearData whatToWearTodayOrTomorrow() {
        Calendar calendar = GregorianCalendar.getInstance();
        Date date = calendar.getTime();

        if (date.getHours()>17 && date.getHours()<24)
            return moreDaysWear(1);
        else
            return TodayWear();
    }

    private WhatToWearData TodayWear() {
        Calendar calendar = GregorianCalendar.getInstance();
        long minUnixTimeDiv1000 = calendar.getTimeInMillis()/1000;
        calendar.set(Calendar.HOUR, 18);
        long maxUnixTimeDiv1000 = calendar.getTimeInMillis()/1000;
        return  WhatToWearBetweenTimes(minUnixTimeDiv1000, maxUnixTimeDiv1000,true);
    }

    public WhatToWearData moreDaysWear(int moreDays) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, moreDays);
        calendar.set(Calendar.HOUR, 6);
        long minUnixTimeDiv1000 = calendar.getTimeInMillis()/1000;
        calendar.set(Calendar.HOUR, 18);
        long maxUnixTimeDiv1000 = calendar.getTimeInMillis()/1000;
        return  WhatToWearBetweenTimes(minUnixTimeDiv1000, maxUnixTimeDiv1000,false);
    }

    private WhatToWearData WhatToWearBetweenTimes(long minUnixTimeDiv1000, long maxUnixTimeDiv1000, Boolean isDataForToday) {
        Boolean isToday = isDataForToday;
        Boolean isScarf = false;
        Boolean isCoat = false;
        Boolean isBoot = false;
        Boolean isHat  = false;
        int sky = 1;

        for (ThreeHoursForcast threeHoursForcast : threeHoursForcastList) {
            Log.e("MMMM",threeHoursForcast.dateAsLong +"  " +minUnixTimeDiv1000+  "  " + maxUnixTimeDiv1000);
            if (threeHoursForcast.dateAsLong >= minUnixTimeDiv1000 && threeHoursForcast.dateAsLong <= maxUnixTimeDiv1000) {
                if (threeHoursForcast.minTemp < 10) {
                    isScarf = true;
                    isHat = true;
                }
                if (threeHoursForcast.minTemp < 15)
                    isCoat = true;
                if (threeHoursForcast.sky == SKY_RAIN || threeHoursForcast.sky == SKY_SNOW)
                    isBoot = true;
                sky = Math.max(sky, threeHoursForcast.sky);


            }

        }

        return new WhatToWearData(isToday,  isScarf,  isCoat,  isBoot,  isHat, sky) ;

    }


}
