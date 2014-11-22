package com.rsc.idonor.baseclasses;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity implements Base{

    protected abstract void initUI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUI();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void update() {
        initUI();
    }

}
