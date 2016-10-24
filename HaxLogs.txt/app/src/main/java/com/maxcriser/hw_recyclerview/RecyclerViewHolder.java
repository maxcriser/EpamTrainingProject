package com.maxcriser.hw_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView myTitle;
    TextView myDescription;
    ImageView myIcon;

    public RecyclerViewHolder(View itemView) {
        super(itemView);

        myTitle = (TextView) itemView.findViewById(R.id.card_title);
        myDescription = (TextView) itemView.findViewById(R.id.list_desc);
        myIcon = (ImageView) itemView.findViewById(R.id.icon);

    }
}
