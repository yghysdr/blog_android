package com.shun.blog.weights;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.app.AlertDialog;

/**
 * Created by yghysdr on 2017/5/22.
 */

public class AppDialog extends AlertDialog {


    protected AppDialog(@NonNull Context context) {
        super(context);
    }

    public AppDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public AppDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }
}
