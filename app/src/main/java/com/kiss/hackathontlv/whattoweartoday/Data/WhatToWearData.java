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
    int sky;

    public WhatToWearData(Boolean isToday, Boolean isScarf, Boolean isCoat, Boolean isBoot, Boolean isHat, int sky) {
        this.isToday = isToday;
        this.isScarf = isScarf;
        this.isCoat = isCoat;
        this.isBoot = isBoot;
        this.isHat = isHat;
        this.sky = sky;
    }

    @Override
    public String toString() {
        return "WhatToWearData{" +
                "isToday=" + isToday +
                ", isScarf=" + isScarf +
                ", isCoat=" + isCoat +
                ", isBoot=" + isBoot +
                ", isHat=" + isHat +
                ", sky=" + sky +
                '}';
    }
}
