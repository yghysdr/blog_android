package com.shun.blog.base.net;

import com.shun.blog.base.ui.BaseResponse;
import com.socks.library.KLog;

import rx.Subscriber;

/**
 * 对网络请求结果处理的封装，
 * 1，方便更换网络请求，
 * 2，简化网络请求的处理。
 */

public abstract class JsonCallback<R extends BaseResponse> extends Subscriber<R> {

    public abstract void onSuccess(R result);

    public void onFailure(int code, String msg) {
        KLog.d("http_error", "code:" + code + "" + "msg:" + msg);
    }

    public void onFinish() {
    }

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        ExceptionHandle.ResponseThrowable error = ExceptionHandle.handleException(e);
        onFailure(error.code, error.message);

    }

    @Override
    public void onNext(R r) {
        if (r.code == 0) {
            onSuccess(r);
        } else {
            onFailure(r.code, r.msg);
        }
    }
}
