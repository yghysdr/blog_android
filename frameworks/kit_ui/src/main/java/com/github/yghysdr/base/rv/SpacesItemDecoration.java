package com.github.yghysdr.base.rv;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.HashMap;

/**
 * Created by yghysdr on 2017/12/20.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {


    private HashMap<String, Integer> mSpaceValueMap;

    public static final String TOP = "top_decoration";
    public static final String BOTTOM = "bottom_decoration";
    public static final String LEFT = "left_decoration";
    public static final String RIGHT = "right_decoration";

    public SpacesItemDecoration(HashMap<String, Integer> spaceValueMap) {
        this.mSpaceValueMap = spaceValueMap;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mSpaceValueMap.get(TOP) != null) {
            outRect.top = mSpaceValueMap.get(TOP);
        }
        if (mSpaceValueMap.get(LEFT) != null) {
            outRect.left = mSpaceValueMap.get(LEFT);
        }
        if (mSpaceValueMap.get(RIGHT) != null) {
            outRect.right = mSpaceValueMap.get(RIGHT);
        }
        if (mSpaceValueMap.get(BOTTOM) != null) {
            outRect.bottom = mSpaceValueMap.get(BOTTOM);
        }
    }
}