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
public class FragmentEvents extends BaseFragment {

    public static FragmentEvents newFragment() {
        return new FragmentEvents();
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
    protected void initUI() {

    }

    @Override
    public String getScreenTitle() {
        return getActivity().getString(R.string.fragment_title_events);
    }
}
