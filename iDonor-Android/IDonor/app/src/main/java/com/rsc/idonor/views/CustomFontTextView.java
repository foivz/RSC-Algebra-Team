package com.rsc.idonor.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.rsc.idonor.utils.FontFace;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public abstract class CustomFontTextView extends TextView {

    protected Typeface typeface;

    public CustomFontTextView(Context context) {
        super(context);
        init();
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    protected abstract void init();
}
