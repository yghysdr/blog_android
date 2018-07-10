package io.yghysdr.common;

import cn.refactor.multistatelayout.MultiStateConfiguration;
import cn.refactor.multistatelayout.MultiStateLayout;
import com.github.yghysdr.http.HttpConfiguration;
import com.github.yghysdr.base.BaseApp;

public class BlogApp extends BaseApp {
    @Override
    protected void afterOnCreate() {
        super.afterOnCreate();
        HttpConfiguration.setBuilder(new HttpConfiguration
                .Builder()
                .setContext(getApplicationContext())
//                .setAuthenticator(new TokenAuthenticator())
                .setHost(new HostImp())
                .initStetho(this));
        MultiStateConfiguration.Builder builder = new MultiStateConfiguration.Builder();
        builder.setCommonEmptyLayout(R.layout.layout_empty)
                .setCommonNetworkErrorLayout(R.layout.layout_error_net)
                .setCommonErrorLayout(R.layout.layout_error)
                .setCommonLoadingLayout(R.layout.layout_loading);
        MultiStateLayout.setConfiguration(builder);
    }
}
