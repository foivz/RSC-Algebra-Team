package com.rsc.idonor.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.facebook.android.Util;
import com.rsc.idonor.R;
import com.rsc.idonor.adapters.SpinnerBloodTypeAdapter;
import com.rsc.idonor.baseclasses.BaseActionBarActivity;
import com.rsc.idonor.model.BloodType;
import com.rsc.idonor.model.User;
import com.rsc.idonor.utils.Preferences;
import com.rsc.idonor.utils.Utils;
import com.rsc.idonor.views.TextViewBold;


public class RegisterActivity extends BaseActionBarActivity implements View.OnClickListener {

    // SPoC for showing Activity
    public static boolean showActivity(Context context, boolean isRegister) {
        if (context == null)
            return false;

        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra("isRegister", isRegister);
        context.startActivity(intent);

        return true;
    }

    @Override
    public String getScreenTitle() {
        return "Register";
    }

    private EditText etEmail;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPassword;
    private EditText etPasswordConfirm;
    private EditText etAge;
    private Spinner spinnerBloodType;

    private TextViewBold btnRegister;

    @Override
    protected void initUI() {
        final ActionBar actionBar = getActionBar();

        boolean isRegister = getIntent().getBooleanExtra("isRegister", false);

        if (actionBar != null) {
            String title =  isRegister? "Register" : "Edit";
            actionBar.setTitle(title);
            btnRegister.setText(title);

            User user = Preferences.getUser(this);

            if (user != null) {
                BloodType userBloodType = null;

                for (BloodType type : BloodType.getBloodTypes()) {
                    if (type.getBloodType() == user.getBloodType()) {
                        userBloodType = type;
                        break;
                    }
                }

                if (userBloodType != null)
                    spinnerBloodType.setSelection(BloodType.getBloodTypes().indexOf(userBloodType));

                etFirstName.setText(user.getFirstName());
                etLastName.setText(user.getLastName());
                etEmail.setText(user.getEmail());
                etAge.setText(String.valueOf(user.getAge()));
            }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        btnRegister = (TextViewBold) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        spinnerBloodType = (Spinner) findViewById(R.id.spinnerBloodType);
        SpinnerBloodTypeAdapter dataAdapter = new SpinnerBloodTypeAdapter(this, R.layout.custom_spinner_item, BloodType.getBloodTypes());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodType.setAdapter(dataAdapter);


        etEmail = (EditText) findViewById(R.id.etRegisterEmail);
        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etAge = (EditText) findViewById(R.id.etAge);

    }


    @Override
    public void onClick(View view) {
        if (validation()) {
            User user;
            if (getIntent().getBooleanExtra("isRegister", true)) {
                user = new User();

                user.setEmail(etEmail.getText().toString());
                user.setLastName(etLastName.getText().toString());
                user.setFirstName(etFirstName.getText().toString());
                user.setAge(Integer.parseInt(etAge.getText().toString()));
                user.setUsername(etEmail.getText().toString());
                user.setBloodType(((BloodType)spinnerBloodType.getSelectedItem()).getBloodType());
            } else {
                user = Preferences.getUser(this);

                user.setEmail(etEmail.getText().toString());
                user.setLastName(etLastName.getText().toString());
                user.setFirstName(etFirstName.getText().toString());
                user.setAge(Integer.parseInt(etAge.getText().toString()));
                user.setUsername(etEmail.getText().toString());
                user.setBloodType(((BloodType)spinnerBloodType.getSelectedItem()).getBloodType());
            }

        }
    }

    private boolean validation() {
        if (etEmail.getText().toString().isEmpty()) {
            Utils.showToast(this, "Please input email address");
            return false;
        }

        if (etFirstName.getText().toString().isEmpty()) {
            Utils.showToast(this, "Please input first name");
            return false;
        }

        if (etLastName.getText().toString().isEmpty()) {
            Utils.showToast(this, "Please input last name");
            return false;
        }

        return true;
    }
}
