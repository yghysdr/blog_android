package com.github.yghysdr.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.github.yghysdr.base.R;


/**
 * 点击会缩小的按钮
 */
public class ScaleButton extends RelativeLayout {
    public ScaleButton(Context context) {
        super(context);
    }

    public ScaleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                beginScale(R.anim.set_scale_in);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                beginScale(R.anim.set_scale_out);
                break;
            case MotionEvent.ACTION_CANCEL:
                beginScale(R.anim.set_scale_out);
                break;
        }
        return true;
    }

    private synchronized void beginScale(int animation) {
        Animation an = AnimationUtils.loadAnimation(getContext(), animation);
        an.setDuration(80);
        an.setFillAfter(true);
        this.startAnimation(an);
    }

    @Override
    public void setVisibility(int visibility) {
        clearAnimation();
        super.setVisibility(visibility);
    }
}
