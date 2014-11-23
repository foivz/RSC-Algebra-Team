package com.rsc.idonor.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsc.idonor.R;
import com.rsc.idonor.activities.RegisterActivity;
import com.rsc.idonor.baseclasses.BaseFragment;
import com.rsc.idonor.model.User;
import com.rsc.idonor.model.BloodType;
import com.rsc.idonor.utils.Preferences;
import com.rsc.idonor.views.TextViewBold;
import com.rsc.idonor.views.TextViewBoldItalic;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class FragmentUserProfile extends BaseFragment {

    public static FragmentUserProfile newFragment() {
        return new FragmentUserProfile();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TextViewBold tvEditProfile;
    TextViewBoldItalic tvBloodType;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragmenttab1, container, false);

        tvEditProfile = (TextViewBold) rootView.findViewById(R.id.btnEditProfile);

        tvEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegisterActivity.showActivity(FragmentUserProfile.this.getActivity(), false);
            }
        });

        tvBloodType = (TextViewBoldItalic) rootView.findViewById(R.id.tvBloodType);

        return rootView;
    }

    @Override
    protected void initUI() {
        User user = Preferences.getUser(getActivity());

        tvBloodType.setText(BloodType.getString(user.getBloodType()));
    }

    @Override
    public String getScreenTitle() {
        return "Home";
    }
}
