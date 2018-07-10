package com.github.yghysdr.http;

import android.content.Context;

import com.facebook.stetho.Stetho;

import okhttp3.Authenticator;
import okhttp3.Interceptor;

public class HttpConfiguration {
    private static Builder mBuilder;

    public static void setBuilder(Builder builder) {
        mBuilder = builder;
    }

    public static Builder getBuilder() {
        if (mBuilder == null) {
            mBuilder = new Builder();
        }
        return mBuilder;
    }

    public static class Builder {
        private Context context;
        private Interceptor interceptor;
        private Authenticator authenticator;
        private IHost iHost;
        private INetExceptionHandler iNetExceptionHandler = new NetExceptionHandler();
        private long connectTimeout = 30 * 1000;//30秒
        private boolean retryOnConnectionFailure = false;//错误重试

        public Builder() {
        }

        public Context getContext() {
            return context;
        }

        public Builder setContext(Context context) {
            this.context = context;
            return this;
        }

        public Interceptor getInterceptor() {
            return interceptor;
        }

        public Builder setInterceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public Authenticator getAuthenticator() {
            return authenticator;
        }

        public Builder setAuthenticator(Authenticator authenticator) {
            this.authenticator = authenticator;
            return this;
        }

        public INetExceptionHandler getNetExceptionHandler() {
            return iNetExceptionHandler;
        }

        public Builder setNetExceptionHandler(INetExceptionHandler iNetExceptionHandler) {
            this.iNetExceptionHandler = iNetExceptionHandler;
            return this;
        }

        public long getConnectTimeout() {
            return connectTimeout;
        }

        public Builder setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public boolean isRetryOnConnectionFailure() {
            return retryOnConnectionFailure;
        }

        public Builder setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
            this.retryOnConnectionFailure = retryOnConnectionFailure;
            return this;
        }

        public IHost getHost() {
            return iHost;
        }

        public Builder setHost(IHost iHost) {
            this.iHost = iHost;
            return this;
        }

        public Builder initStetho(Context context) {
            Stetho.initializeWithDefaults(context);
            return this;
        }
    }
}
