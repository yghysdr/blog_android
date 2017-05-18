package com.shun.blog.ui.home.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.MyBaseHolder;
import com.shun.blog.bean.HomeBean;

import butterknife.BindView;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class HomeHolder extends MyBaseHolder<HomeBean> {
    @BindView(R.id.home_item_title)
    TextView homeItemTitle;

    public HomeHolder(Context context, ViewGroup root) {
        super(context, root, R.layout.item_home);
    }

    @Override
    public void initData(HomeBean data) {
        homeItemTitle.setText(data.title);
    }
}
