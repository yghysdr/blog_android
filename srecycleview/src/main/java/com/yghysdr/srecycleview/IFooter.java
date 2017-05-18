package com.yghysdr.srecycleview;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by yghysdr on 2017/5/8.
 */

public interface IFooter {

    int HAVE_MORE = 0;
    int NO_MORE = 1;
    int ERROR = 2;

    @IntDef({ERROR, NO_MORE, HAVE_MORE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    /**
     * @param status
     */
    void setStatus(@Status int status);

    @Status
    int getStatus();
}
