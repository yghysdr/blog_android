package io.yghysdr.article.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;


import butterknife.BindView;
import com.github.yghysdr.recycleview.BaseRVHolder;
import io.yghysdr.article.R;
import io.yghysdr.article.R2;
import com.github.yghysdr.util.DateUtil;

/**
 * Created by yghysdr on 2017/5/8.
 */

public class ArchiveHolderTime extends BaseRVHolder<Long> {


    @BindView(R2.id.archive_year)
    TextView archiveYear;


    public ArchiveHolderTime(Context context, ViewGroup root) {
        super(context, root, R.layout.articcle_item_archive_year);
    }

    @Override
    public void initData(Long data) {
        archiveYear.setText(DateUtil.long2Str(data, DateUtil.FORMAT_YM));
    }
}
