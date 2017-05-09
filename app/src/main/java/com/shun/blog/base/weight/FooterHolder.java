package com.shun.blog.base.weight;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;

import butterknife.BindView;

/**
 */
public class FooterHolder extends BaseHolder implements IFooterHolder {

    @BindView(R.id.item_more_loading)
    TextView itemMoreLoading;
    @BindView(R.id.item_more_no)
    TextView itemMoreNo;

    public FooterHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_footer);
    }

    @Override
    public void initData(Object data) {

    }

    @Override
    public void setStatus(int status) {
        if (status == 0) {
            itemMoreLoading.setVisibility(View.VISIBLE);
            itemMoreNo.setVisibility(View.GONE);
        } else {
            itemMoreLoading.setVisibility(View.GONE);
            itemMoreNo.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void getStatus() {

    }
}
