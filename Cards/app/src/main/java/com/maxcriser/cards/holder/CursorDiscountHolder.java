package com.maxcriser.cards.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxcriser.cards.R;

public class CursorDiscountHolder extends RecyclerView.ViewHolder {

    public ImageView mIcon;
    public TextView mTitle;
    public FrameLayout mLinearCard;

    public CursorDiscountHolder(View itemView) {
        super(itemView);

        mLinearCard = (FrameLayout) itemView.findViewById(R.id.linear_card);
        mTitle = (TextView) itemView.findViewById(R.id.title_main_cards);
    }
}
