package com.shun.blog.ui.home.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shun.blog.R;
import com.shun.blog.base.ui.MyBaseHolder;
import com.shun.blog.utils.DateUtil;

import butterknife.BindView;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArchiveHolderYear extends MyBaseHolder<Long> {


    @BindView(R.id.archive_year)
    TextView archiveYear;


    public ArchiveHolderYear(Context context, ViewGroup root) {
        super(context, root, R.layout.item_archive_year);
    }

    @Override
    public void initData(Long data) {
        archiveYear.setText(DateUtil.long2Str(data, DateUtil.FORMAT_Y) + "年");
    }
}
