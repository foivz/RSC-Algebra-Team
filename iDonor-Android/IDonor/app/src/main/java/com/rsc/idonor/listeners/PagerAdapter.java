package com.rsc.idonor.listeners;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.rsc.idonor.baseclasses.BaseFragment;
import com.rsc.idonor.fragments.FragmentTab1;
import com.rsc.idonor.fragments.FragmentTab2;
import com.rsc.idonor.fragments.FragmentTab3;
import com.rsc.idonor.fragments.FragmentTab4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    List<BaseFragment> dataSource;

    public PagerAdapter(FragmentManager fm) {
        super(fm);

        dataSource = new ArrayList<BaseFragment>();

        dataSource.add(FragmentTab1.newFragment());
        dataSource.add(FragmentTab2.newFragment());
        dataSource.add(FragmentTab3.newFragment());
        dataSource.add(FragmentTab4.newFragment());
    }

    @Override
    public BaseFragment getItem(int i) {
        return dataSource.get(i);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }
}
