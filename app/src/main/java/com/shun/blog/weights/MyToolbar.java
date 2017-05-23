package com.shun.blog.weights;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.shun.blog.R;

/**
 * Created by yghysdr on 2017/4/28.
 */

public class MyToolbar extends FrameLayout implements View.OnClickListener {

    ImageView appBackIv;
    TextView appTitleTv;
    FrameLayout appTitleFl;
    private Context mContext;
    private View mRootView;
    private String mAppName;
    private boolean mCloseActivity;

    public MyToolbar(Context context) {
        this(context, null);
    }

    public MyToolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttrs(context, attrs);
        initDate();
    }

    private void initDate() {
        appTitleTv.setText(mAppName);
        if (!mCloseActivity) {
            appBackIv.setVisibility(GONE);
        }
    }

    public void setBackgroundResource(@DrawableRes int resId) {
        appTitleFl.setBackgroundResource(resId);
    }

    public void setTitleTextColor(@ColorInt int color) {
        appTitleTv.setTextColor(color);
    }

    private void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.view_my_toolbar, this);
        appBackIv = (ImageView) mRootView.findViewById(R.id.app_back_iv);
        appTitleTv = (TextView) mRootView.findViewById(R.id.app_title_tv);
        appTitleFl = (FrameLayout) mRootView.findViewById(R.id.app_title_fl);
        appBackIv.setOnClickListener(this);
        appTitleTv.setOnClickListener(this);
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.toolbar_attr);
        mCloseActivity = a.getBoolean(R.styleable.toolbar_attr_closeActivity, true);
        mAppName = a.getString(R.styleable.toolbar_attr_appName);
        //回收资源，这一句必须调用
        a.recycle();
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(CharSequence title) {
        appTitleTv.setText(title);
    }

    public void goneBack() {
        appBackIv.setVisibility(GONE);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.app_back_iv:
                ((Activity) mContext).onBackPressed();
                break;
            case R.id.app_title_tv:
                break;
        }
    }
}
