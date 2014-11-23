package com.rsc.idonor.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AppEventsLogger;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.LoginButton;
import com.rsc.idonor.MainActivity;
import com.rsc.idonor.R;
import com.rsc.idonor.baseclasses.BaseActionBarActivity;
import com.rsc.idonor.gamefication.FacebookManager;
import com.rsc.idonor.model.User;
import com.rsc.idonor.utils.Preferences;
import com.rsc.idonor.views.TextViewBoldItalic;


public class LoginActivity extends BaseActionBarActivity implements View.OnClickListener {

    // SPoC for showing Activity
    public static boolean showActivity(Context context) {
        if (context == null)
            return false;

        if (context instanceof Activity)
            ((Activity)context).finish();

        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

        return true;
    }

    @Override
    public String getScreenTitle() {
        return "Login";
    }

    TextViewBoldItalic mLoginButton;
    LoginButton mLoginFacebookButton;
    TextViewBoldItalic mRegisterButton;

    @Override
    protected void initUI() {
        final ActionBar actionBar = getActionBar();

        if (actionBar != null) {

            mLoginButton = (TextViewBoldItalic) findViewById(R.id.btnLogin);
            mLoginButton.setOnClickListener(this);
            mLoginFacebookButton = (LoginButton) findViewById(R.id.btnLoginWithFacebook);
            mLoginFacebookButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
                @Override
                public void onUserInfoFetched(GraphUser user) {
                    if (user != null) {
                        Log.e("TAG", user.getId());

                        User realUser = new User(user);

                        Preferences.saveUser(LoginActivity.this, realUser);
                        MainActivity.showActivity(LoginActivity.this, true);
                    }
                }
            });
            mRegisterButton = (TextViewBoldItalic) findViewById(R.id.btnGoRegister);
            mRegisterButton.setOnClickListener(this);

            actionBar.setTitle(getScreenTitle());
        }

    }

    private FacebookManager mFacebookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFacebookManager = FacebookManager.getInstance(this);
        mFacebookManager.setUiHelper(new UiLifecycleHelper(this, mFacebookManager));
        mFacebookManager.getUiHelper().onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            String pendingAction = savedInstanceState.getString(FacebookManager.PENDING_ACTION_BUNDLE_KEY);
            mFacebookManager.setPendingAction(pendingAction);
        }

        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mFacebookManager.getUiHelper().onPause();

        // Call the 'deactivateApp' method to log an app event for use in analytics and advertising
        // reporting.  Do so in the onPause methods of the primary Activities that an app may be launched into.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFacebookManager.getUiHelper().onDestroy();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mFacebookManager.getUiHelper().onSaveInstanceState(outState);

        outState.putString(FacebookManager.PENDING_ACTION_BUNDLE_KEY, mFacebookManager.getPendingAction().name());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookManager.getUiHelper().onActivityResult(requestCode, resultCode, data, dialogCallback);
    }

    @Override
    public void onClick(View view) {
        if (view instanceof TextViewBoldItalic) {
            if(view == mLoginButton) {
                new LoginLogic().login(new LoginCallback() {
                    @Override
                    public void onLoginFinished() {

                    }
                });
            } else if (view == mRegisterButton) {
                RegisterActivity.showActivity(this, true);
            }
        }
    }

    private FacebookDialog.Callback dialogCallback = new FacebookDialog.Callback() {
        @Override
        public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
            Log.d("HelloFacebook", String.format("Error: %s", error.toString()));
        }

        @Override
        public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
            Log.d("HelloFacebook", "Success!");
        }
    };



    private interface LoginCallback {
        public void onLoginFinished();
    }

    private class LoginLogic {

        public void login(LoginCallback callback) {

        }

        public void loginFacebook(LoginCallback callback) {

        }

        public void loginTwitter(LoginCallback callback) {

        }
    }
}
