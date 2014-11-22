package com.rsc.idonor.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class BloodLevel {

    @SerializedName("bloodTypeId")
    private int bloodTypeId;

    @SerializedName("level")
    private int level;

    public int getBloodTypeId() {
        return bloodTypeId;
    }

    public int getLevel() {
        return level;
    }
}
