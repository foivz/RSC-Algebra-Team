package com.rsc.idonor.baseclasses;

import android.app.Fragment;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public abstract class BaseFragment extends Fragment implements Base {


    protected abstract void initUI();

    @Override
    public void update() {
        initUI();
    }
}
