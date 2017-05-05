package com.shun.blog.baserx;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.Window;

import com.shun.blog.base.BaseResponse;

/**
 * 对网络请求结果处理的封装，
 * 1，方便更换网络请求，
 * 2，简化网络请求的处理。
 */

public abstract class DialogCallback<R extends BaseResponse> extends JsonCallback<R> {

    private ProgressDialog dialog;

    private void initDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("请求网络中...");
        dialog.show();
    }

    public DialogCallback(Context context) {
        super();
        initDialog(context);
    }

    @Override
    public void onNext(R r) {
        super.onNext(r);
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onFailure(int code, String msg) {
        super.onFailure(code, msg);
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
