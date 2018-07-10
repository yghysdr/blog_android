package com.github.yghysdr.base.rv;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * 不实现具体逻辑，只有结构，具体实现参考FooterHolder
 */
public abstract class BaseFooterHolder extends BaseRVHolder<FoodModel> {

    protected View itemMoreLoading;
    protected View itemMoreNo;
    protected View itemError;
    protected View itemEmpty;

    public BaseFooterHolder(Context context, ViewGroup root, int layoutRes) {
        super(context, root, layoutRes);
    }

    protected Listener mListener;

    public void addListener(Listener listener) {
        mListener = listener;
    }

    public interface Listener {
        void bottomTry();
    }

    @Override
    public void bindData(FoodModel data) {
        super.bindData(data);
        setStatus(data.status);
        if (itemError != null) {
            itemError.setOnClickListener(v -> {
                if (mListener != null) {
                    setStatus(IStatus.HAVE_MORE);
                    mListener.bottomTry();
                }
            });
        }
    }

    public void setStatus(@IStatus int status) {
        itemMoreLoading.setVisibility(status == IStatus.HAVE_MORE ? View.VISIBLE : View.GONE);
        itemMoreNo.setVisibility(status == IStatus.NO_MORE ? View.VISIBLE : View.GONE);
        itemError.setVisibility(status == IStatus.ERROR ? View.VISIBLE : View.GONE);
        itemEmpty.setVisibility(status == IStatus.EMPTY ? View.VISIBLE : View.GONE);
    }
}
