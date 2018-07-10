package io.yghysdr.mediator.user;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class MediatorUser {
    public static Fragment getUserFragment() {
        return (Fragment) ARouter
                .getInstance()
                .build(IContentUser.USER_FRAGMENT_USER)
                .navigation();
    }

    static IUserProvider iUserProvider;

    public static IUserProvider getUserProvider() {
        if (iUserProvider == null) {
            ARouter.getInstance()
                    .build(IContentUser.USER_SERVICE_USER_INFO)
                    .navigation();
        }
        return iUserProvider;
    }
}
