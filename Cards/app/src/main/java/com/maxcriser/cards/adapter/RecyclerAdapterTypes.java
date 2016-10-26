package com.maxcriser.cards.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxcriser.cards.R;
import com.maxcriser.cards.holder.RecyclerViewHolderTypes;

import java.util.List;


public class RecyclerAdapterTypes extends RecyclerView.Adapter<RecyclerViewHolderTypes> {


    private List<String> mItems;
    private Context mContext;
    private LayoutInflater mInflater;

    public RecyclerAdapterTypes(Context context, List<String> pItems) {

        mItems = pItems;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolderTypes onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = mInflater.inflate(R.layout.item_list, parent, false);

        RecyclerViewHolderTypes viewHolder = new RecyclerViewHolderTypes(myView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderTypes holder, int position) {

//        holder.mIcon.setImageBitmap(...);

        holder.mTitle.setText(mItems.get(position));
        holder.mTitle.setTag(holder);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
