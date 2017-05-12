package com.yghysdr.srecycleview;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 */
public class FooterHolder extends BaseHolder implements IFooterHolder {

    TextView itemMoreLoading;
    TextView itemMoreNo;

    public FooterHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_footer);
        itemMoreNo = (TextView) itemView.findViewById(R.id.item_more_no);
        itemMoreLoading = (TextView) itemView.findViewById(R.id.item_more_loading);
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
