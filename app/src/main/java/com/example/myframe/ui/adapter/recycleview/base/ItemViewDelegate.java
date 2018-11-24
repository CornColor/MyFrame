package com.example.myframe.ui.adapter.recycleview.base;


import java.util.List;

/**
 * Created by zhy on 16/6/22.
 */
public interface ItemViewDelegate<T>
{

    int getItemViewLayoutId();

    boolean isForViewType(T item, int position);

    void convert(ViewHolder holder, T t, int position);

    void convert(ViewHolder holder, T t, int position, List<Object> payloads);
}
