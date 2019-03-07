package com.androideasy.adapter.holder;

import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * 类功能:通用的ViewHolder
 * 工作室：AndroidEasy
 * 作者：AndroidEasy
 * 邮箱：AndroidEasy@126.com   QQ：1400100300
 */
public class EasyViewHolder extends RecyclerView.ViewHolder {

    /**
     * Item控件的容器
     */
    private final SparseArrayCompat<View> mViews = new SparseArrayCompat<>();


    public EasyViewHolder(View itemView) {
        super(itemView);
     }

    public <T extends View> T getViewById(int viewId) {
        //先从SparseArray里面取 跟从map 取是一个道理的
        View view = mViews.get(viewId);
        if (view == null) {
            // 如果没有就用 ConvertView 获取这个view，然后添加到SparseArray里面，下次就可以直接取了
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置TextView的值
     */
    public TextView setText(int viewId, String text) {
        TextView textView = getViewById(viewId);
        textView.setText(text);
        return textView;
    }

    /**
     * 设置TextView 文字颜色
     */
    public TextView setTextColor(int viewId, int textColor) {
        TextView textView = getViewById(viewId);
        textView.setTextColor(textColor);
        return textView;
    }

    /**
     * 隐藏View
     */
    public <T extends View> T setViewVisible(int viewId, boolean visible) {
        T view = getViewById(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return view;
    }

}
