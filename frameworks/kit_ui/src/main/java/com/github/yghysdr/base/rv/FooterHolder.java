package com.github.yghysdr.base.rv;


import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.yghysdr.base.BaseApp;
import com.github.yghysdr.base.R;


/**
 * 通用的的footerholder
 */
public class FooterHolder extends BaseFooterHolder {

    public FooterHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_footer);
        itemMoreNo = (TextView) itemView.findViewById(R.id.item_more_no);
        itemMoreLoading = (LinearLayout) itemView.findViewById(R.id.item_more_loading);
        itemError = (TextView) itemView.findViewById(R.id.item_error);
        itemEmpty = (TextView) itemView.findViewById(R.id.item_empty_tv);

    }

    @Override
    public void initData(FoodModel data) {
        ((TextView) itemEmpty).setText(TextUtils.isEmpty(data.empty)
                ? BaseApp.getResStr(R.string.comm_empty) : data.empty);
    }

}
