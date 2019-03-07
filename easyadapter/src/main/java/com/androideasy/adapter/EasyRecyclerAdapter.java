package com.androideasy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.androideasy.adapter.holder.EasyViewHolder;

import java.util.List;

/**
 * 类功能:单Adapter
 * 工作室：AndroidEasy
 * 作者：AndroidEasy
 * 邮箱：AndroidEasy@126.com   QQ：1400100300
 */

public abstract class EasyRecyclerAdapter<T> extends RecyclerView.Adapter<EasyViewHolder> {
    /**
     * 保存上下文对象Context
     */
    protected Context mContext;
    /**
     * 对应的实体类列表
     */
    protected List<T> mList;

    private EasyRecyclerAdapter<T> baseAdapter;

    private HeadAndFootRecyclerAdapter<T> headAndFootAdapter;
    private BaseEasyRecyclerAdapter<T> baseEasyRecyclerAdapter;

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener) {
        baseEasyRecyclerAdapter.setOnItemClickListener(onItemClickListener);
    }

    public EasyRecyclerAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mList = list;
        baseAdapter = this;
        baseEasyRecyclerAdapter = new BaseEasyRecyclerAdapter<T>(context, list) {

            @Override
            public int getLayoutId() {
                return baseAdapter.getLayoutId();
            }

            @Override
            public void bindViewHolder(EasyViewHolder holder, T t, int position) {
                baseAdapter.bindViewHolder(holder, t, position);
            }
        };

        headAndFootAdapter = new HeadAndFootRecyclerAdapter<T>(baseEasyRecyclerAdapter);

    }


    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return headAndFootAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        return headAndFootAdapter.getItemViewType(position);
    }

    public int getRealItemCount() {
        return headAndFootAdapter.getRealItemCount();
    }

    @Override
    public int getItemCount() {
        return headAndFootAdapter.getItemCount();
    }

    @Override
    public void onBindViewHolder(EasyViewHolder holder, int position) {
        headAndFootAdapter.onBindViewHolder(holder, position);
    }

    public void addHeaderView(View headerView) {
        headAndFootAdapter.addHeaderView(headerView);
    }

    public void addFootView(View footView) {
        headAndFootAdapter.addFootView(footView);
    }

    //返回布局id
    public abstract int getLayoutId();

    //绑定具体View
    public abstract void bindViewHolder(EasyViewHolder holder, T t, int position);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        headAndFootAdapter.onAttachedToRecyclerView(recyclerView);
    }
}
