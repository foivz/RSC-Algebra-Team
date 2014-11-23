package com.rsc.idonor.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkosmoljo on 23/11/14.
 */
public class BloodType {

    public static final int UNKNOWN = 0;
    public static final int ZERO_MINUS = 1;
    public static final int ZERO_PLUS = 2;
    public static final int A_MINUS = 3;
    public static final int A_PLUS = 4;
    public static final int B_MINUS = 5;
    public static final int B_PLUS = 6;
    public static final int AB_MINUS = 7;
    public static final int AB_PLUS = 8;

    public static String getString(int bloodType) {
        switch (bloodType) {
            case ZERO_MINUS:
                return "0-";
            case ZERO_PLUS:
                return "0+";
            case A_MINUS:
                return "A-";
            case A_PLUS:
                return "A+";
            case B_MINUS:
                return "B-";
            case B_PLUS:
                return "B+";
            case AB_MINUS:
                return "AB-";
            case AB_PLUS:
                return "AB+";
            case UNKNOWN:
            default:
                return "Unknown";

        }
    }

    public static List<BloodType> getBloodTypes() {
        List<BloodType> allBloodTypes = new ArrayList<BloodType>();

        BloodType o1 = new BloodType(ZERO_MINUS);
        BloodType o2 = new BloodType(ZERO_PLUS);
        BloodType a1 = new BloodType(A_MINUS);
        BloodType a2 = new BloodType(A_PLUS);
        BloodType b1 = new BloodType(B_MINUS);
        BloodType b2 = new BloodType(B_PLUS);
        BloodType ab1 = new BloodType(AB_MINUS);
        BloodType ab2 = new BloodType(AB_PLUS);
        BloodType unknown = new BloodType(UNKNOWN);

        allBloodTypes.add(o1);
        allBloodTypes.add(o2);
        allBloodTypes.add(a1);
        allBloodTypes.add(a2);
        allBloodTypes.add(b1);
        allBloodTypes.add(b2);
        allBloodTypes.add(ab1);
        allBloodTypes.add(ab2);
        allBloodTypes.add(unknown);

        return allBloodTypes;

    }

    public BloodType(int type) {
        this.bloodType = type;
    }

    @SerializedName("bloodType")
    private int bloodType;

    public int getBloodType() {
        return bloodType;
    }

    @Override
    public String toString() {
        return BloodType.getString(this.bloodType);
    }
}
