package com.github.yghysdr.base.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

public class TranslateLayout extends FrameLayout {

    private Animation mHideAnim, mShowAnim;

    public TranslateLayout(@NonNull Context context) {
        this(context, null);
    }

    public TranslateLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TranslateLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAnim();
    }

    private void initAnim() {
        mHideAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF
                , 0.0f, Animation.RELATIVE_TO_SELF, 1.0f);
        mHideAnim.setDuration(200);
        mShowAnim = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF
                , 1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAnim.setDuration(200);
    }

    public void setHideAnim(Animation anim) {
        mHideAnim = anim;
    }

    public void setShowAnim(Animation anim) {
        mShowAnim = anim;
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == getVisibility()) {
            return;
        }
        if (visibility == VISIBLE) {
            startAnimation(mShowAnim);
            super.setVisibility(visibility);
        } else {
            startAnimation(mHideAnim);
            TranslateLayout.super.setVisibility(visibility);
        }
    }
}
