package io.yghysdr.mediator.discover;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class MediatorDiscover {

    public static Fragment getDiscoverFragment() {
        return (Fragment) ARouter.getInstance()
                .build(IConstantDiscover.DISCOVER_FRAGMENT_DISCOVER)
                .navigation();
    }
}
