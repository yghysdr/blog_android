package com.github.yghysdr.base.rv;

import android.content.Context;
import android.view.ViewGroup;

import com.github.yghysdr.base.R;


public class EmptyHolder extends BaseRVHolder {
    public EmptyHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_empty);
    }

    @Override
    public void initData(Object data) {

    }
}
