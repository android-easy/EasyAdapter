package com.androideasy.adapter.viewbinder;

import android.content.Context;
import android.text.TextUtils;

import com.androideasy.adapter.holder.EasyViewHolder;


/**
 * 类功能:通用的ViewBinder
 * 工作室：AndroidEasy
 * 作者：AndroidEasy
 * 邮箱：AndroidEasy@126.com   QQ：1400100300
 */


public abstract class EasyViewBinder<T> {

    protected Context mContext;

    protected boolean isGridColumn;

    public EasyViewBinder() {
    }

    public EasyViewBinder(boolean isGridColumn) {
        this.isGridColumn = isGridColumn;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isGridColumn() {
        return isGridColumn;
    }

    public void setGridColumn(boolean gridColumn) {
        isGridColumn = gridColumn;
    }

    protected String typeTag;

    public String getTypeTag() {
        if (TextUtils.isEmpty(typeTag)) {
            typeTag = getClass().getName();
        }
        return typeTag;
    }

    //
    public abstract int getLayoutId();

    //
    public abstract void bindViewHolder(EasyViewHolder holder, T t, int position);
}
