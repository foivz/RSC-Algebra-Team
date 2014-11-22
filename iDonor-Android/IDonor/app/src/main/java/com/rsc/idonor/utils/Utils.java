package com.rsc.idonor.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

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

}
