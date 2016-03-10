package com.kiss.hackathontlv.whattoweartoday.Data;

/**
 * Created by erez on 10/03/16.
 */
public class ThreeHoursForcast implements WeatherConstant {
    String timeStamp;
    int sky=1;
    int maxTemp;
    int minTemp;
    String skyDescription;
    Long dateAsLong;

    public ThreeHoursForcast(String timeStamp, int skyCode, String skyDescription, int maxTemp, int minTemp) {
        dateAsLong = Long.parseLong(timeStamp);
        this.timeStamp = timeStamp;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        sky = calculateCode(skyCode);
        this.skyDescription  = skyDescription;
    }

    private int calculateCode(int skyCode) {
        // TODO not perfect
        if (skyCode <300 && skyCode >=200)
            return SKY_THUNDER;
        else if (skyCode == 800)
            return SKY_CLEAR;
        else if (skyCode >=801 && skyCode <900)
            return SKY_CLOUDS;
        else if (skyCode < 600 && skyCode>=500)
            return SKY_RAIN;
        else if (skyCode>=600 && skyCode <700)
            return SKY_SNOW;
        return SKY_CLOUDS;
    }


}


