package com.github.yghysdr.base.rv;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yghysdr on 2017/5/8.
 */
@IntDef({IStatus.ERROR, IStatus.NO_MORE, IStatus.HAVE_MORE, IStatus.INIT, IStatus.EMPTY})
@Retention(RetentionPolicy.SOURCE)
public @interface IStatus {

    int HAVE_MORE = 0;
    int NO_MORE = 1;
    int ERROR = 2;
    int INIT = 3;
    int EMPTY = 4;
}
