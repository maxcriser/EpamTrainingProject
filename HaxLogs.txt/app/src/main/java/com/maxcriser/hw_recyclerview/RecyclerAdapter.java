package com.maxcriser.hw_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    List<String> mItems;

    Context mContext;
    LayoutInflater mInflater;

    public RecyclerAdapter(Context context, List<String> pItems) {
        mItems = pItems;
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = mInflater.inflate(R.layout.item_list, parent, false);

        RecyclerViewHolder viewHolder = new RecyclerViewHolder(myView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.myTitle.setText(mItems.get(position));
        holder.myIcon.setOnClickListener(clickListener);
        holder.myIcon.setOnLongClickListener(longClickListener);
        holder.myIcon.setTag(holder);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            RecyclerViewHolder vholder = (RecyclerViewHolder) v.getTag();
            int position = vholder.getPosition();

            mItems.set(position, "isClicked");

            notifyDataSetChanged();
        }
    };

    View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            // some act for long clicked

            return false;
        }
    };


    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
