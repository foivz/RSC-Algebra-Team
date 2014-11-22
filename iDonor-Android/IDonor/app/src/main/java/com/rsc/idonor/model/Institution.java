package com.rsc.idonor.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class Institution {

    @SerializedName("name")
    private String name;

    @SerializedName("city")
    private String city;

    @SerializedName("address")
    private String address;

    @SerializedName("postalNumber")
    private int postalNumber;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("bloodLevels")
    List<BloodLevel> bloodLevels;

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public int getPostalNumber() {
        return postalNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<BloodLevel> getBloodLevels() {
        return bloodLevels;
    }
}
