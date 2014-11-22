package com.rsc.idonor.listeners;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import com.rsc.idonor.baseclasses.BaseFragment;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class TabListener implements ActionBar.TabListener {

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private ActionBar mActionBar;

    public TabListener(ViewPager viewPager, ActionBar actionBar) {
        this.mViewPager = viewPager;
        this.mPagerAdapter = (PagerAdapter) this.mViewPager.getAdapter();
        this.mActionBar = actionBar;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        mViewPager.setCurrentItem(tab.getPosition());

        BaseFragment fragment = (BaseFragment) mPagerAdapter.getItem(tab.getPosition());

        mActionBar.setTitle(fragment.getTitle());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        BaseFragment fragment = (BaseFragment) mPagerAdapter.getItem(tab.getPosition());
        fragment.update();
    }
}
