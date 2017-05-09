package com.shun.blog.base.weight;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.shun.blog.bean.BaseBean;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by yghysdr on 2016/12/5.
 * <p>
 * 包含底部加载更多的网络Adapter的封装
 * 重写底部Holder建议继承FooterHolder
 */

public abstract class BaseRVAdapter extends RecyclerView.Adapter<BaseHolder> {

    public static final int ITEM_TYPE_FOOTER = 10002;  //说明是带有Footer的
    public static final int ITEM_TYPE_NORMAL = 0;  //说明是不带有header和footer的

    private BaseHolder mFooterHolder;
    protected Context mContext;
    /**
     * 数据放在adapter中维护
     */
    protected ArrayList<BaseBean> mDataList = new ArrayList<>();

    public BaseRVAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mFooterHolder != null && viewType == ITEM_TYPE_FOOTER) {
            return mFooterHolder;
        }
        return myOnCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        holder.bindData(mDataList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.get(position) instanceof FooterBean) {
            return ITEM_TYPE_FOOTER;
        }
        return ITEM_TYPE_NORMAL;
    }

    /**
     * 添加一个集合
     *
     * @param list
     */
    public void addAll(Collection<BaseBean> list) {
        if (list == null) {
            return;
        }
        int lastIndex = this.mDataList.size();
        if (mFooterHolder != null && lastIndex > 0) {
            lastIndex = lastIndex - 1;
        }
        mDataList.addAll(lastIndex, list);
        notifyItemRangeInserted(lastIndex, list.size());
    }

    public void setFooterView(BaseHolder footerHolder) {
        mDataList.add(new FooterBean());
        notifyItemInserted(mDataList.size() - 1);
        mFooterHolder = footerHolder;
    }

    public void clear() {
        mDataList.clear();
        notifyDataSetChanged();
    }

    protected abstract BaseHolder myOnCreateViewHolder(ViewGroup parent, int viewType);
}
