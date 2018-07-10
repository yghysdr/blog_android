package com.github.yghysdr.base.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.github.yghysdr.base.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * 基础DialogFragment
 */
public abstract class BaseDialogFragment<T extends BasePresenter> extends DialogFragment {

    protected static final String TAG = "base_bottom_dialog";

    protected static final float DEFAULT_DIM = 0.2f;

    protected T mPresenter;
    protected Unbinder unbind;
    protected PresenterManager mPresenterManager;
    protected DisposableManager mDisposableManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, getStyleId());
        mDisposableManager = new DisposableManager();
        EventBus.getDefault().register(this);
        mPresenterManager = new PresenterManager();
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenterManager.mPresenterList.add(mPresenter);
        }
        addPresenter(mPresenterManager.mPresenterList);
        mPresenterManager.attach();
    }

    protected void addPresenter(List<BasePresenter> mPresenterList) {

    }

    public int getStyleId() {
        return R.style.CenterDialog;
    }

    protected T createPresenter() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(getCancelOutside());

        View v = inflater.inflate(getLayoutRes(), container, false);
        unbind = ButterKnife.bind(this, v);
        bindView(v);
        return v;
    }

    @LayoutRes
    public abstract int getLayoutRes();

    public abstract void bindView(View v);

    @Override
    public void onStart() {
        super.onStart();
        initWindow();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(Object event) {

    }

    public void addDisposable(Disposable disposable) {
        mDisposableManager.addDisposable(disposable);
    }


    protected void initWindow() {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        params.dimAmount = getDimAmount();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        if (getHeight() > 0) {
            params.height = getHeight();
        } else {
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        }
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }


    public int getHeight() {
        return -1;
    }

    public float getDimAmount() {
        return DEFAULT_DIM;
    }

    public boolean getCancelOutside() {
        return true;
    }

    public String getFragmentTag() {
        return TAG;
    }

    public void show(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(this, getFragmentTag());
        ft.commitAllowingStateLoss();
    }

    @Override
    public void dismiss() {
        dismissAllowingStateLoss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbind != null) {
            unbind.unbind();
        }
        EventBus.getDefault().unregister(this);
        mPresenterManager.detach();
        mDisposableManager.clear();
    }

    //监听这个事件屏蔽掉返回键
    protected DialogInterface.OnKeyListener mBackKeyListener = (dialog, keyCode, event) -> {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return false;
    };

}
