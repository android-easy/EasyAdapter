package com.androideasy.adapter;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androideasy.adapter.holder.EasyViewHolder;
import com.androideasy.adapter.viewbinder.EasyViewBinder;

import java.util.List;
import java.util.Map;

/**
 * 类功能:多Adapter
 * 工作室：AndroidEasy
 * 作者：AndroidEasy
 * 邮箱：AndroidEasy@126.com   QQ：1400100300
 */

abstract class BaseMultipleRecyclerAdapter<T> extends RecyclerView.Adapter<EasyViewHolder> {

    private SparseArrayCompat<EasyViewBinder<T>> viewBindMap = new SparseArrayCompat<>();
    private Map<String, Integer> typeMap = new ArrayMap<>();
    private int defType = 20000;
    /**
     * 保存上下文对象Context
     */
    protected Context mContext;
    /**
     * 对应的实体类列表
     */
    protected List<T> mList;
    /**
     * 布局加载器
     */
    protected LayoutInflater mLayoutInflater;
    /**
     * 点击事件
     */
    protected OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public BaseMultipleRecyclerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout_id = viewBindMap.get(viewType).getLayoutId();
        View convertView = mLayoutInflater.inflate(layout_id, null);
        return new EasyViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final EasyViewHolder holder,   int position) {

        EasyViewBinder viewBinder = getViewBind(position);
        viewBinder.bindViewHolder(holder, mList.get(position), position);
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder, mList.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                }
            });

        }
    }

    @Override
    public int getItemViewType(int position) {
        EasyViewBinder easyViewBinder = getViewBind(position);
        if (typeMap.get(easyViewBinder.getTypeTag()) == null) {
            addViewBinder(easyViewBinder);
        }
        return typeMap.get(easyViewBinder.getTypeTag());
    }

    public void addViewBinder(EasyViewBinder viewBinder) {
        int type = defType++;
        typeMap.put(viewBinder.getTypeTag(), type);
        viewBinder.setContext(mContext);
        viewBindMap.put(type, viewBinder);
    }

    public abstract EasyViewBinder getViewBind(int position);

    public boolean getSpanSizeMultiple(int position) {
        int viewType = getItemViewType(position);
        EasyViewBinder easyViewBinder = viewBindMap.get(viewType);
        if (easyViewBinder != null) {
            return easyViewBinder.isGridColumn();
        }
        return false;
    }
}
