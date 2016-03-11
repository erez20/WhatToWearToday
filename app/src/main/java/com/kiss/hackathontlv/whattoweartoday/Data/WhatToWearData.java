package com.kiss.hackathontlv.whattoweartoday.Data;

/**
 * Created by erez on 10/03/16.
 */
public class WhatToWearData {
    Boolean isToday;
    Boolean isScarf;
    Boolean isCoat;
    Boolean isBoot;
    Boolean isHat;
    int minTemp;
    int maxTemp;
    int sky;
    String dayHumanRead;

    public Boolean getIsToday() {
        return isToday;
    }

    public Boolean getIsScarf() {
        return isScarf;
    }

    public Boolean getIsCoat() {
        return isCoat;
    }

    public Boolean getIsBoot() {
        return isBoot;
    }

    public Boolean getIsHat() {
        return isHat;
    }

    public int getSky() {
        return sky;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public String getDayHumanRead() {
        return dayHumanRead;
    }

    public WhatToWearData(Boolean isToday, Boolean isScarf, Boolean isCoat,
                          Boolean isBoot, Boolean isHat, int sky, int minTemp, int maxTemp, String dayHumanRead) {
        this.isToday = isToday;
        this.isScarf = isScarf;
        this.isCoat = isCoat;
        this.isBoot = isBoot;
        this.isHat = isHat;
        this.sky = sky;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.dayHumanRead = dayHumanRead;
    }

    @Override
    public String toString() {
        return "WhatToWearData{" +
                "isToday=" + isToday +
                ", isScarf=" + isScarf +
                ", isCoat=" + isCoat +
                ", isBoot=" + isBoot +
                ", isHat=" + isHat +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", sky=" + sky +
                '}';
    }
}
