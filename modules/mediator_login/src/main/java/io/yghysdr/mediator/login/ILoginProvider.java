package io.yghysdr.mediator.login;

import com.alibaba.android.arouter.facade.template.IProvider;


public interface ILoginProvider extends IProvider {

    void exitLogin();

    boolean isLogin();
}
