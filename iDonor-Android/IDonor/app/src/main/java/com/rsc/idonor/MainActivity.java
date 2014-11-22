package com.rsc.idonor;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.rsc.idonor.baseclasses.BaseActionBarActivity;
import com.rsc.idonor.listeners.PagerAdapter;
import com.rsc.idonor.listeners.TabListener;
import com.rsc.idonor.utils.FontFace;


public class MainActivity extends BaseActionBarActivity {

    // SPoC for showing Activity
    public static boolean showActivity(Context context) {
        if (context == null)
            return false;

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);

        return true;
    }

    PagerAdapter mPagerAdapter;
    ViewPager mViewPager;
    TabListener mTabListener;

    @Override
    protected void initUI() {
        final ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            // Hide Actionbar Icon
            actionBar.setDisplayShowHomeEnabled(true);
            // Hide Actionbar Title
            actionBar.setDisplayShowTitleEnabled(true);

            // Create Actionbar Tabs
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            mPagerAdapter = new PagerAdapter(getFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.vpPager);
            mViewPager.setAdapter(mPagerAdapter);
            mTabListener = new TabListener(mViewPager, actionBar);

            mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    actionBar.setSelectedNavigationItem(position);
                }
            });

            for (int i = 0; i < mPagerAdapter.getCount(); i++) {

                ActionBar.Tab tab = actionBar.newTab();
                tab.setTabListener(mTabListener);

                tab.setText(mPagerAdapter.getItem(i).getTitle());

                actionBar.addTab(tab);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new FontFace(getApplicationContext());
    }
}
