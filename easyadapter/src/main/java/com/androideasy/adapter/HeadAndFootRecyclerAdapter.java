package com.androideasy.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.androideasy.adapter.holder.EasyViewHolder;


/**
 * 类功能:单Adapter
 * 工作室：AndroidEasy
 * 作者：AndroidEasy
 * 邮箱：AndroidEasy@126.com   QQ：1400100300
 */

class HeadAndFootRecyclerAdapter<T> extends RecyclerView.Adapter<EasyViewHolder> {
    private static final int ITEM_TYPE_HEADER = 11100111;
    private static final int ITEM_TYPE_FOOTER = 22200222;

    private View mHeaderView;
    private View mFootView;
    HeadAndFootRecyclerAdapter<T> baseAdapter;
    RecyclerView.Adapter<EasyViewHolder> mAdapter;

    public HeadAndFootRecyclerAdapter(RecyclerView.Adapter adapter) {
        baseAdapter = this;
        mAdapter = adapter;
    }

    @Override
    public EasyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == ITEM_TYPE_HEADER) {
            return new EasyViewHolder(mHeaderView);

        } else if (mFootView != null && viewType == ITEM_TYPE_FOOTER) {
            return new EasyViewHolder(mFootView);
        }
        return mAdapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView != null && position == 0) {
            return ITEM_TYPE_HEADER;
        }
        if (mFootView != null && position == getItemCount() - 1) {
            return ITEM_TYPE_FOOTER;
        }

        return mAdapter.getItemViewType(hasHeaderView() ? position - 1 : position);
    }

    protected int getRealItemCount() {
        return mAdapter.getItemCount();
    }

    @Override
    public int getItemCount() {
        int count = getRealItemCount();
        if (mHeaderView != null) {
            count++;
        }
        if (mFootView != null) {
            count++;
        }
        return count;
    }

    @Override
    public void onBindViewHolder(EasyViewHolder holder, int position) {

        //默认绑定head和foot
        if (hasHeaderView() && position == 0) {
            return;
        }
        if (hasFootView() && position == getItemCount() - 1) {
            return;
        }

        //绑定其他
        if (hasHeaderView()) {//如果没有头 就直接绑定子类mAdapter
            mAdapter.onBindViewHolder(holder, position - 1);
        } else {
            mAdapter.onBindViewHolder(holder, position);
        }

    }

    protected boolean hasHeaderView() {
        if (mHeaderView != null) {
            return true;
        }
        return false;
    }

    protected boolean hasFootView() {
        if (mFootView != null) {
            return true;
        }
        return false;
    }

    private boolean isFootView(int position) {
        if (hasFootView() && position == getItemCount() - 1) {
            return true;
        }
        return false;
    }

    private boolean isHeadView(int position) {
        if (hasHeaderView() && position == 0) {
            return true;
        }

        return false;
    }

    public void addHeaderView(View view) {
        mHeaderView = view;
    }

    public void addFootView(View view) {
        mFootView = view;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mAdapter.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();

            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return getSpanSizeMultiple(gridLayoutManager, spanSizeLookup, position);
                }
            });
            gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
        }
    }

    public int getSpanSizeMultiple(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
        if (isHeadView(position)) {
            return layoutManager.getSpanCount();
        } else if (isFootView(position)) {
            return layoutManager.getSpanCount();
        }

        if (mAdapter instanceof BaseMultipleRecyclerAdapter) {
            int mAdapterPosition = position;
            if(hasHeaderView()){
                mAdapterPosition --;
            }
            if (((BaseMultipleRecyclerAdapter) mAdapter).getSpanSizeMultiple(mAdapterPosition)) {
                return layoutManager.getSpanCount();
            }
        }
        if (oldLookup != null) {
            return oldLookup.getSpanSize(position);
        }
        return 1;
    }
}
