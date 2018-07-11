package com.github.yghysdr.base.rv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 对RecycleView.Adapter的封装，
 * 原理：
 * --Adapter只根据Type返回不同的Holder。
 * --Holder里面封装了对本Holder的一切操作，如根布局，事件监听，数据填充。
 * --整个Adapter所需要的数据，在该Adapter维护。增删改查，数据，然后调用Adapter的刷新界面的方法，更新UI,
 * --当需要改变UI的时候是对Adapter中维护的数据更改，然后调用Adapter的刷新界面的方法，更新UI。
 * 实现过程：
 * --根据getItemViewType，在onCreateViewHolder返回不同的holder。
 * --配合BaseRVHolder,在onBindViewHolder中调用holder的bindData
 * 使用方法：
 * --继承BaseRVAdapter
 * --重写myOnCreateViewHolder，返回需要的holder
 * --当需列表中有多种视图类型的时候，重写getItemViewType方法，但是要调用super
 * 对外暴露的方法
 * --addDataList，添加一个列表，当有底部（加载更多）的时候，会自动添加到list最后一条的上面。
 * --addAllTop 在顶部添加数据
 * --addFooterModel 设置底部，需要自定义的
 * --clear 清空数据
 */
public abstract class BaseRVAdapter extends RecyclerView.Adapter<BaseRVHolder> {
    public static final int ITEM_TYPE_FOOTER = 10002;  //footerHolder
    public static final int ITEM_TYPE_EMPTY = 10003;  //emptyHolder

    protected Context mContext;
    /**
     * 数据放在adapter中维护
     */
    protected List mDataList;
    protected BaseFooterHolder mBaseFooterHolder;

    public BaseRVAdapter(Context context) {
        this(context, new ArrayList<>());
    }

    public BaseRVAdapter(Context mContext, List modelList) {
        this.mContext = mContext;
        this.mDataList = modelList;
    }

    @Override
    public BaseRVHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_FOOTER) {
            mBaseFooterHolder = createFooterHolder(mContext, parent);
            if (mAddHolderListener != null) {
                mAddHolderListener.footer(mBaseFooterHolder);
            }
            return mBaseFooterHolder;
        } else if (viewType == ITEM_TYPE_EMPTY) {
            return new EmptyHolder(mContext, parent);
        }
        return myOnCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseRVHolder holder, int position) {
        holder.bindData(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDataList.get(position) instanceof FoodModel) {
            return ITEM_TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    /**
     * 重写此方法，自定义FooterHolder
     *
     * @param context
     * @param parent
     * @return
     */
    public BaseFooterHolder createFooterHolder(Context context, ViewGroup parent) {
        return new FooterHolder(context, parent);
    }

    private CreateHolderListener mAddHolderListener;

    public interface CreateHolderListener {
        void footer(BaseFooterHolder baseFooterHolder);
    }

    public void createHolderListener(CreateHolderListener addHolderListener) {
        mAddHolderListener = addHolderListener;
    }

    /**
     * 添加一个集合,在底部与加载更多之间
     *
     * @param list
     */
    public void addDataList(Collection list) {
        if (list == null) {
            return;
        }
        mDataList.addAll(list);
    }

    public void addDataListByPosition(int position, Collection list) {
        if (list == null) {
            return;
        }
        mDataList.addAll(position, list);
    }

    /**
     * 在顶部添加一条数据
     *
     * @param model
     */
    public void addTop(Object model) {
        if (model == null) {
            return;
        }
        mDataList.add(0, model);
    }

    public void clear() {
        mDataList.clear();
    }

    protected abstract BaseRVHolder myOnCreateViewHolder(ViewGroup parent, int viewType);

    public Object getData(int position) {
        if (mDataList != null && mDataList.size() > position) {
            return mDataList.get(position);
        } else {
            return null;
        }
    }

    public void addDataByIndex(int index, Object model) {
        mDataList.add(index, model);
    }


    public void addData(Object model) {
        mDataList.add(model);
    }

    //修改数据并修改ui
    public void addDataNotify(Object data) {
        int start = mDataList.size();
        mDataList.add(data);
        notifyItemInserted(start);
    }

    public void addDataListNotify(List dataList) {
        int start = mDataList.size();
        mDataList.addAll(dataList);
        notifyItemRangeInserted(start, dataList.size());
    }

    public void addDataListByPositionNotify(int position, Collection list) {
        mDataList.addAll(position, list);
        notifyItemRangeInserted(position, list.size());
    }

    public void removeDataNotify(Object data) {
        for (int position = 0; position < mDataList.size(); position++) {
            if (mDataList.get(position).equals(data)) {
                mDataList.remove(position);
                notifyItemRemoved(position);
                break;
            }
        }
    }

    public void updateDataNotify(Object data, IFindListener listener) {
        for (int position = 0; position < mDataList.size(); position++) {
            if (mDataList.get(position).equals(data)) {
                if (listener != null) {
                    listener.onSuccess(mDataList.get(position), position);
                }
                notifyItemChanged(position);
                break;
            }
        }
    }

    public interface IFindListener {
        void onSuccess(Object data, int position);
    }

    public void removeDataByPositionNotify(int position) {
        if (mDataList != null && mDataList.size() > position) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }
    }

}
