package com.rsc.idonor.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rsc.idonor.model.BloodDonations;
import com.rsc.idonor.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class Preferences {

    private static final String PREF_KEY = "iDonor_preferences";

    //User Keys

    private static final String KEY_USER = "user";
    private static final String KEY_PUSH_NOTIFICATIONS = "push_notifications";

    public static User getUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);

        User user = null;
        String userJson = preferences.getString(KEY_USER, "");

        if (!userJson.isEmpty()) {
            Gson gson = new GsonBuilder().create();
            user = gson.fromJson(userJson, User.class);
        }

        return user;
    }

    public static User saveUser(Context context, User user) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);

        if (user == null) {
            cleanUser(preferences);
        } else {
            SharedPreferences.Editor editor = preferences.edit();

            Gson gson = new GsonBuilder().create();
            String userJson = gson.toJson(user);

            editor.putString(KEY_USER, userJson);

            editor.apply();
        }

        return user;
    }

    private static void cleanUser(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(KEY_USER);
        editor.apply();
    }

    public static boolean getNotificationAllowed(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);

        return preferences.getBoolean(KEY_PUSH_NOTIFICATIONS, false);
    }

    public static boolean setNotificationAllowed(Context context, boolean allowNotifications) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_PUSH_NOTIFICATIONS, allowNotifications);
        editor.apply();

        return allowNotifications;
    }

}
