package com.maxcriser.cards.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxcriser.cards.R;

public class RecyclerViewHolderTypes extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public ImageView mIcon;

    public RecyclerViewHolderTypes(View itemView) {
        super(itemView);

        mTitle = (TextView) itemView.findViewById(R.id.title_main_cards);
        mIcon = (ImageView) itemView.findViewById(R.id.icon_main_cards);
    }
}
