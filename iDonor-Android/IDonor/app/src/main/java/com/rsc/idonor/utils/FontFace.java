package com.rsc.idonor.utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by darkosmoljo on 22/11/14.
 */
public class FontFace {

    static Typeface faceRegular;
    static Typeface faceBold;
    static Typeface faceBoldItalic;
    static Typeface faceItalic;

    public FontFace(Context ctx){
        setFontRegular(ctx);
        setFontBold(ctx);
        setFontBoldItalic(ctx);
        setFontItalic(ctx);
    }

    public void setFontRegular(Context ctx){
        FontFace.faceRegular = Typeface.createFromAsset(ctx.getAssets(), "fonts/LiberationSans_Regular.ttf");
    }

    public void setFontBold(Context ctx){
        FontFace.faceBold = Typeface.createFromAsset(ctx.getAssets(), "fonts/LiberationSans_Bold.ttf");
    }

    public void setFontBoldItalic(Context ctx){
        FontFace.faceBoldItalic = Typeface.createFromAsset(ctx.getAssets(), "fonts/LiberationSans_BoldItalic.ttf");
    }

    public void setFontItalic(Context ctx){
        FontFace.faceItalic = Typeface.createFromAsset(ctx.getAssets(), "fonts/LiberationSans_Italic.ttf");
    }

    public static Typeface getFaceRegular() {
        return faceRegular;
    }

    public static Typeface getFaceBold() {
        return faceBold;
    }

    public static Typeface getFaceBoldItalic() {
        return faceBoldItalic;
    }

    public static Typeface getFaceItalic() {
        return faceItalic;
    }
}
