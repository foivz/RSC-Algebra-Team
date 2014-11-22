package com.rsc.idonor.views;

import android.content.Context;
import android.util.AttributeSet;

import com.rsc.idonor.utils.FontFace;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class TextViewBold extends CustomFontTextView {

    public TextViewBold(Context context) {
        super(context);
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        if (!isInEditMode()) {
            this.typeface = FontFace.getFaceBold();
            this.setTypeface(this.typeface);
        }
    }
}
