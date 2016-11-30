package com.maxcriser.cards.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxcriser.cards.R;

public class RecyclerViewHolderTypes extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public TextView mSubhead;
    public ImageView mIcon;
    public FrameLayout mLinearCard;

    public RecyclerViewHolderTypes(View itemView) {
        super(itemView);

        mSubhead = (TextView) itemView.findViewById(R.id.subtitle_main_cards);
        mLinearCard = (FrameLayout) itemView.findViewById(R.id.linear_card);
        mTitle = (TextView) itemView.findViewById(R.id.title_main_cards);
    }
}
