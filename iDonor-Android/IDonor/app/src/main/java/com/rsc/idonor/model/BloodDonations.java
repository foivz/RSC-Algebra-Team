package com.rsc.idonor.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class BloodDonations {

    @SerializedName("bloodDonationId")
    private int bloodDonationId;

    @SerializedName("date")
    private String date;

    @SerializedName("institutionID")
    private int institutionId;

    @SerializedName("institutionName")
    private String institutionName;

    public int getBloodDonationId() {
        return bloodDonationId;
    }

    public String getDate() {
        return date;
    }

    public int getInstitutionId() {
        return institutionId;
    }

    public String getInstitutionName() {
        return institutionName;
    }
}
