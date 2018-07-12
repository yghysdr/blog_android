package com.github.yghysdr.recycleview;


import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


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
                ? mContext.getText(R.string.comm_empty) : data.empty);
    }

}
