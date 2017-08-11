package com.yghysdr.srecycleview;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 */
public class BaseFooterHolder extends BaseHolder implements IFooter {

    @Status
    int mCurState;

    TextView itemMoreLoading;
    TextView itemMoreNo;
    TextView itemError;

    public BaseFooterHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_footer);
        itemMoreNo = (TextView) itemView.findViewById(R.id.item_more_no);
        itemMoreLoading = (TextView) itemView.findViewById(R.id.item_more_loading);
        itemError = (TextView) itemView.findViewById(R.id.item_error);
    }

    public BaseFooterHolder(Context context, ViewGroup root, int layoutRes) {
        super(context, root, layoutRes);
    }

    @Override
    public void initData(Object data) {

    }

    @Override
    public void setStatus(@Status int status) {
        mCurState = status;
        itemMoreLoading.setVisibility(status == HAVE_MORE ? View.VISIBLE : View.GONE);
        itemMoreNo.setVisibility(status == NO_MORE ? View.VISIBLE : View.GONE);
        itemError.setVisibility(status == ERROR ? View.VISIBLE : View.GONE);
    }

    @Status
    @Override
    public int getStatus() {
        return mCurState;
    }
}
