package com.rsc.idonor.baseclasses;

import android.preference.PreferenceFragment;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public abstract class BasePreferenceFragment extends PreferenceFragment implements Base{

    protected abstract void initUI();

    @Override
    public void update() {
        initUI();
    }
}
