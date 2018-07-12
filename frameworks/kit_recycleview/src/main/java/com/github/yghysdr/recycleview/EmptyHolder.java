package com.github.yghysdr.recycleview;

import android.content.Context;
import android.view.ViewGroup;



public class EmptyHolder extends BaseRVHolder {
    public EmptyHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_empty);
    }

    @Override
    public void initData(Object data) {

    }
}
