package com.github.yghysdr.base.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by yghysdr on 2017/8/23.
 * 高度固定的RecyclerView
 */

public class HRecycleView extends RecyclerView {
    public HRecycleView(Context context) {
        super(context);
    }

    public HRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    //空白地方点击
    private GestureDetectorCompat gestureDetector;

    public void addBlankListener(BlankListener listener) {
        this.listener = listener;
        this.gestureDetector = new GestureDetectorCompat(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    private BlankListener listener;

    public interface BlankListener {
        void onBlankClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (gestureDetector != null && listener != null) {
            if (gestureDetector.onTouchEvent(e)) {
                View childView = findChildViewUnder(e.getX(), e.getY());
                if (childView == null) {
                    listener.onBlankClick();
                    return true;
                }
            }
        }
        return super.onTouchEvent(e);
    }

}
