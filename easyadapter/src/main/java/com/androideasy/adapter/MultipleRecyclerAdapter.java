package com.androideasy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.androideasy.adapter.holder.EasyViewHolder;
import com.androideasy.adapter.viewbinder.EasyViewBinder;

import java.util.List;

/**
 * 类功能:单Adapter
 * 工作室：AndroidEasy
 * 作者：AndroidEasy
 * 邮箱：AndroidEasy@126.com   QQ：1400100300
 */

public abstract class MultipleRecyclerAdapter<T> extends RecyclerView.Adapter<EasyViewHolder> {
    protected List<T> list;

    private MultipleRecyclerAdapter<T> baseAdapter;

    private HeadAndFootRecyclerAdapter<T> headAndFootAdapter;

    private BaseMultipleRecyclerAdapter<T> baseMultipleRecyclerAdapter;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        baseMultipleRecyclerAdapter.setOnItemClickListener(onItemClickListener);
    }

    public MultipleRecyclerAdapter(Context context, List<T> list) {
        this.list = list;
        baseAdapter = this;
        baseMultipleRecyclerAdapter = new BaseMultipleRecyclerAdapter<T>(context, list) {

            @Override
            public int getItemViewType(int position) {
                return super.getItemViewType(position);
            }

            @Override
            public EasyViewBinder getViewBind(int position) {
                return baseAdapter.getViewBind(position);
            }
        };
        headAndFootAdapter = new HeadAndFootRecyclerAdapter<T>(baseMultipleRecyclerAdapter);

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

    public void addHeaderView(View view) {
        headAndFootAdapter.addHeaderView(view);
    }

    public void addFootView(View view) {
        headAndFootAdapter.addFootView(view);
    }


    public abstract EasyViewBinder getViewBind(int position);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        headAndFootAdapter.onAttachedToRecyclerView(recyclerView);
    }
}
