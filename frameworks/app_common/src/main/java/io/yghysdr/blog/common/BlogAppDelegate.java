package io.yghysdr.blog.common;

import android.app.Application;

import cn.refactor.multistatelayout.MultiStateConfiguration;
import cn.refactor.multistatelayout.MultiStateLayout;

import com.github.yghysdr.base.IApplicationDelegate;
import com.github.yghysdr.http.HttpConfiguration;

public class BlogAppDelegate implements IApplicationDelegate {

    @Override
    public void onCreate(Application application) {
        TokenHelper.init();
        HttpConfiguration.setBuilder(new HttpConfiguration
                .Builder()
                .setContext(application)
//                .setAuthenticator(new TokenAuthenticator())
                .setHost(new HostImp())
                .initStetho(application));
        MultiStateConfiguration.Builder builder = new MultiStateConfiguration.Builder();
        builder.setCommonEmptyLayout(R.layout.layout_empty)
                .setCommonNetworkErrorLayout(R.layout.layout_error_net)
                .setCommonErrorLayout(R.layout.layout_error)
                .setCommonLoadingLayout(R.layout.layout_loading);
        MultiStateLayout.setConfiguration(builder);
    }
}
