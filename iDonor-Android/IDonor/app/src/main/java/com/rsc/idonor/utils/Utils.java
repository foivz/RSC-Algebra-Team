package com.rsc.idonor.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class Utils {

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    public static void showYesNoDialog(Context context, String title, String positiveButtonTitle, String negativeButtonTitle, DialogInterface.OnClickListener clickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(title)
                .setPositiveButton(positiveButtonTitle, clickListener)
                .setNegativeButton(negativeButtonTitle, clickListener)
                .show();
    }

    public static String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line;

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    private static final String urlPrefix = "http://10.0.21.9/idonors/";

    public static String returnUrlWithParams(String methodName, String... params) {

        if (params != null && params.length > 0) {
            for (String str : params) {
                methodName = methodName.concat(str + File.separator);
            }
        }

        return urlPrefix.concat(methodName);
    }

}
