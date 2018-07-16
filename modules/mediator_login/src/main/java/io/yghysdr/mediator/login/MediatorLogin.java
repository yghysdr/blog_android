package io.yghysdr.mediator.login;


import com.alibaba.android.arouter.launcher.ARouter;

public class MediatorLogin {


    private static ILoginProvider loginProvider;

    public static ILoginProvider getLoginProvider() {
        if (loginProvider == null) {
            loginProvider = (ILoginProvider) ARouter.getInstance()
                    .build(IConstantLogin.LOGIN_SERVICE_USER)
                    .navigation();
        }
        return loginProvider;
    }

    public static void startLogin() {
        ARouter.getInstance()
                .build(IConstantLogin.LOGIN_ACTIVITY_LOGIN)
                .navigation();
    }
}
