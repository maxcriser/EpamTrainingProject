package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxcriser.cards.holder.RecyclerViewHolderTypes;

import java.util.List;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolderTypes> {

    private List<String> mItems;
    private LayoutInflater mInflater;
    private Object mView;

    public ItemsRecyclerAdapter(Context context, List<String> pItems, Object pObject) {
        mView = pObject;
        mItems = pItems;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolderTypes onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = mInflater.inflate((Integer) mView, parent, false);

        return new RecyclerViewHolderTypes(myView);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderTypes holder, int position) {

        holder.mTitle.setText(mItems.get(position));
        holder.mTitle.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
