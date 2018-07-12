package io.yghysdr.article.presenter;

import android.content.Context;
import android.view.ViewGroup;

import com.github.yghysdr.recycleview.BaseRVAdapter;
import com.github.yghysdr.recycleview.BaseRVHolder;
import io.yghysdr.article.view.ArchiveHolder;
import io.yghysdr.article.view.ArchiveHolderTime;

/**
 * Created by yghysdr on 2017/6/30.
 */

public class ArchiveListAdapter extends BaseRVAdapter {
    private static final int TYPE_YEAR = 1;

    public ArchiveListAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected BaseRVHolder myOnCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_YEAR) {
            return new ArchiveHolderTime(mContext, parent);
        }
        return new ArchiveHolder(mContext, parent);
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.get(position) instanceof Long) {
            return TYPE_YEAR;
        }
        return super.getItemViewType(position);
    }
}
