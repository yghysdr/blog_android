package com.github.yghysdr.http;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.Window;

import io.reactivex.disposables.Disposable;

public abstract class DialogObserver<T> extends NetObserver<T> {

    private ProgressDialog dialog;
    private boolean mCanBack = false;

    private void initDialog(Context context, String msg) {
        if (context instanceof Activity) {
            dialog = new ProgressDialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage(msg);
            dialog.setOnKeyListener((dialogInterface, i, keyEvent) -> {
                switch (keyEvent.getKeyCode()) {
                    case KeyEvent.KEYCODE_BACK:
                        onBack();
                }
                return !mCanBack;
            });
        } else {
            dialog = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (dialog != null) {
            dialog.show();
        }
    }

    public DialogObserver(Context context) {
        super();
        initDialog(context, "网络请求中...");
    }

    public DialogObserver(String msg, Context context) {
        super();
        initDialog(context, msg);
    }


    @Override
    public void onComplete() {
        super.onComplete();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        super.onError(e);
    }

    /**
     * 弹窗时点击返回键
     */
    public void onBack() {

    }
}
