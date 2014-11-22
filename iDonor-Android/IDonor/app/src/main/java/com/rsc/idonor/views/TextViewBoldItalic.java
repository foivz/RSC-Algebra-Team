package com.rsc.idonor.views;

import android.content.Context;
import android.util.AttributeSet;

import com.rsc.idonor.utils.FontFace;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class TextViewBoldItalic extends CustomFontTextView {

    public TextViewBoldItalic(Context context) {
        super(context);
    }

    public TextViewBoldItalic(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewBoldItalic(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void init() {
        if (!isInEditMode()) {
            this.typeface = FontFace.getFaceBoldItalic();
            this.setTypeface(this.typeface);
        }
    }
}
