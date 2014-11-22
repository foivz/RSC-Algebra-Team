package com.rsc.idonor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsc.idonor.R;
import com.rsc.idonor.baseclasses.BaseFragment;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class FragmentTab3 extends BaseFragment {

    private String mTitle = "Events";

    public static FragmentTab3 newFragment() {
        return new FragmentTab3();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);

        return rootView != null ? rootView : super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    protected void initUI() {

    }
}
