package com.github.yghysdr.widget;

import android.content.Context;
import android.util.TypedValue;

public class DisplayUtils {
    /**
     * dip-->px
     */
    public static int dip2Px(Context context, int dip) {
        float density = context.getResources().getDisplayMetrics().density;
        int px = (int) (dip * density + 0.5f);
        return px;
    }

    /**
     * px-->dip
     */
    public static int px2dip(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        int dip = (int) (px / density + 0.5f);
        return dip;
    }

    /**
     * sp-->px
     */
    public static int sp2px(Context context, int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics()) + 0.5f);
    }
}
