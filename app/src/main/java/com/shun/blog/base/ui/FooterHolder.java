package com.shun.blog.base.ui;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.yghysdr.srecycleview.BaseFooterHolder;
import com.yghysdr.srecycleview.IFooter;

/**
 * Created by yghysdr on 2017/8/9.
 */

public class FooterHolder extends BaseFooterHolder implements IFooter {

    @Status
    int mCurState;

    TextView itemFooterStatusTv;

    public FooterHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_list_footer);
        itemFooterStatusTv = (TextView) itemView.findViewById(R.id.item_footer_status_tv);
    }

    @Override
    public void initData(Object data) {

    }

    @Override
    public void setStatus(@Status int status) {
        mCurState = status;
        switch (status) {
            case IFooter.ERROR:
                itemFooterStatusTv.setText("出了点问题");
                break;
            case IFooter.NO_MORE:
                itemFooterStatusTv.setText("到底部了");
                break;
            default:
                itemFooterStatusTv.setText("加载中..");
        }
    }

    @Status
    @Override
    public int getStatus() {
        return mCurState;
    }
}
