package com.rsc.idonor.adapters;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.rsc.idonor.fragments.FragmentEvents;
import com.rsc.idonor.fragments.FragmentMap;
import com.rsc.idonor.fragments.FragmentSettings;
import com.rsc.idonor.fragments.FragmentUserProfile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    List<Fragment> dataSource;

    public PagerAdapter(FragmentManager fm) {
        super(fm);

        dataSource = new ArrayList<Fragment>();

        dataSource.add(FragmentUserProfile.newFragment());
        dataSource.add(FragmentMap.newFragment());
        dataSource.add(FragmentEvents.newFragment());
        dataSource.add(FragmentSettings.newFragment());
    }

    @Override
    public Fragment getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }
}
