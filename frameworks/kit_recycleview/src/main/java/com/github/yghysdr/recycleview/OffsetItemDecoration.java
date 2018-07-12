package com.github.yghysdr.recycleview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class OffsetItemDecoration extends RecyclerView.ItemDecoration {

    int mOffsetTop, mOffsetRight, mOffsetBottom, mOffsetLeft;

    public OffsetItemDecoration(int top, int right, int bot, int left) {
        mOffsetTop = top;
        mOffsetRight = right;
        mOffsetBottom = bot;
        mOffsetLeft = left;
    }

    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        if (mOffsetTop != 0) {
//            outRect.top = UIUtils.dip2Px(BaseApp.getContext(), mOffsetTop);
//        }
//        if (mOffsetRight != 0) {
//            outRect.right = UIUtils.dip2Px(BaseApp.getContext(), mOffsetRight);
//        }
//        if (mOffsetBottom != 0) {
//            outRect.bottom = UIUtils.dip2Px(BaseApp.getContext(), mOffsetBottom);
//        }
//        if (mOffsetLeft != 0) {
//            outRect.left = UIUtils.dip2Px(BaseApp.getContext(), mOffsetLeft);
//        }
    }
}
