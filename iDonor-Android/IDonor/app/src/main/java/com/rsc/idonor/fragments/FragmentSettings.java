package com.rsc.idonor.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.rsc.idonor.R;
import com.rsc.idonor.baseclasses.BaseActionBarActivity;
import com.rsc.idonor.baseclasses.BasePreferenceFragment;
import com.rsc.idonor.gamefication.FacebookManager;
import com.rsc.idonor.utils.Preferences;
import com.rsc.idonor.utils.Utils;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class FragmentSettings extends BasePreferenceFragment implements Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener{

    public static FragmentSettings newFragment() {
        return new FragmentSettings();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);

        CheckBoxPreference allowNotifications = (CheckBoxPreference) getPreferenceManager().findPreference("push_notifications");

        allowNotifications.setChecked(Preferences.getNotificationAllowed(this.getActivity()));
        allowNotifications.setOnPreferenceChangeListener(this);

        PreferenceScreen logoutPreference = (PreferenceScreen) getPreferenceManager().findPreference("logout");
        logoutPreference.setOnPreferenceClickListener(this);


    }

    @Override
    protected void initUI() {

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {

        if (preference.getKey().equals("push_notifications")) {
            boolean selectedValue = (Boolean) o;
            ((CheckBoxPreference) preference).setChecked(Preferences.setNotificationAllowed(this.getActivity(), selectedValue));

            Log.d("MyApp", "Pref " + preference.getKey() + " changed to " + o.toString());
        }

        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {

        if (preference.getKey().equals("logout")) {

            Utils.showYesNoDialog(getActivity(), "Do you wish to Logout?", "Yes", "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (i == DialogInterface.BUTTON_POSITIVE) {
                        Preferences.saveUser(FragmentSettings.this.getActivity(), null);
                        FacebookManager manager = FacebookManager.getInstance(FragmentSettings.this.getActivity());

                        manager.logoutFacebook();

                        ((BaseActionBarActivity) FragmentSettings.this.getActivity()).update();
                    }
                }
            });


            return true;
        }

        return false;
    }

    @Override
    public String getScreenTitle() {
        return getActivity().getString(R.string.fragment_title_settings);
    }
}
