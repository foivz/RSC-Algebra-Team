package com.rsc.idonor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.rsc.idonor.model.User;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class Preferences {

    private static final String PREF_KEY = "iDonor_preferences";

    //User Keys
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_USERNAME = "username";
    private static final String KEY_USER_PASSWORD = "password";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_FIRST_NAME = "first_name";
    private static final String KEY_USER_LAST_NAME = "last_name";
    private static final String KEY_USER_DOB = "dob";
    private static final String KEY_USER_WEIGHT = "weight";
    private static final String KEY_USER_BLOOD_TYPE_ID = "blood_type";
    private static final String KEY_USER_TOWN = "town";
    private static final String KEY_USER_ADDRESS = "address";
    private static final String KEY_USER_PHONE_NUMBER = "phone";
    private static final String KEY_USER_POSTAL_NUMBER = "postal";

    public static User getUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);

        User user = new User();
        user.setUserId(preferences.getInt(KEY_USER_ID, -1));
        user.setUsername(preferences.getString(KEY_USER_USERNAME, ""));
        user.setPassword(preferences.getString(KEY_USER_PASSWORD, ""));
        user.setEmail(preferences.getString(KEY_USER_EMAIL, ""));
        user.setFirstName(preferences.getString(KEY_USER_FIRST_NAME, ""));
        user.setLastName(preferences.getString(KEY_USER_LAST_NAME, ""));
        user.setDob(preferences.getString(KEY_USER_DOB, ""));
        user.setWeight(preferences.getFloat(KEY_USER_WEIGHT, 0));
        user.setBloodType(preferences.getInt(KEY_USER_BLOOD_TYPE_ID, 0));
        user.setTown(preferences.getString(KEY_USER_TOWN, ""));
        user.setAddress(preferences.getString(KEY_USER_ADDRESS, ""));
        user.setPhone(preferences.getString(KEY_USER_PHONE_NUMBER, ""));
        user.setPostal(preferences.getString(KEY_USER_POSTAL_NUMBER, ""));

        return user.getUsername().isEmpty() ? null : user;
    }

    public static void saveUser(Context context, User user) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);

        if (user == null) {
            cleanUser(preferences);
        } else {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(KEY_USER_ID, user.getUserId());
            editor.putString(KEY_USER_USERNAME, user.getUsername());
            editor.putString(KEY_USER_PASSWORD, user.getPassword());
            editor.putString(KEY_USER_EMAIL, user.getEmail());
            editor.putString(KEY_USER_FIRST_NAME, user.getFirstName());
            editor.putString(KEY_USER_LAST_NAME, user.getLastName());
            editor.putString(KEY_USER_DOB, user.getDob());
            editor.putFloat(KEY_USER_WEIGHT, user.getWeight());
            editor.putInt(KEY_USER_BLOOD_TYPE_ID, user.getBloodType());
            editor.putString(KEY_USER_TOWN, user.getTown());
            editor.putString(KEY_USER_ADDRESS, user.getAddress());
            editor.putString(KEY_USER_PHONE_NUMBER, user.getPhone());
            editor.putString(KEY_USER_POSTAL_NUMBER, user.getPostal());
            editor.apply();
        }
    }

    private static void cleanUser(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_USER_ID);
        editor.remove(KEY_USER_USERNAME);
        editor.remove(KEY_USER_PASSWORD);
        editor.remove(KEY_USER_EMAIL);
        editor.remove(KEY_USER_FIRST_NAME);
        editor.remove(KEY_USER_LAST_NAME);
        editor.remove(KEY_USER_DOB);
        editor.remove(KEY_USER_WEIGHT);
        editor.remove(KEY_USER_BLOOD_TYPE_ID);
        editor.remove(KEY_USER_TOWN);
        editor.remove(KEY_USER_ADDRESS);
        editor.remove(KEY_USER_PHONE_NUMBER);
        editor.remove(KEY_USER_POSTAL_NUMBER);
        editor.apply();
    }

}
