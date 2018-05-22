package com.shun.blog.base.net;

import com.shun.blog.base.ui.BaseResponse;
import com.shun.blog.utils.ToastUtils;

import rx.Observer;
import rx.Subscriber;

/**
 * 对网络请求结果处理的封装，
 * 1，方便更换网络请求，
 * 2，简化网络请求的处理。
 */

public abstract class JsonCallback<T> extends Subscriber<T> {

    public abstract void onSuccess(T result);

    public void onFailure(int code, String msg) {
        ToastUtils.showToastLimitTime(msg);
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
    public void onNext(T r) {
        if (r instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) r;
            if (baseResponse.code == 0) {
                onSuccess(r);
            } else {
                onFailure(baseResponse.code, baseResponse.msg);
            }
        } else {
            onSuccess(r);
        }
    }

}
