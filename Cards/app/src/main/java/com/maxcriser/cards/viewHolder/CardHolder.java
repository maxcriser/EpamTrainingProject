package com.maxcriser.cards.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.view.labels.RobotoThin;

public class CardHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public TextView mSubhead;
    public ImageView mIcon;
    public FrameLayout mLinearCard;
    public RobotoThin mDate;
    public TextView mTime;
    public ImageView backgroundCredit;

    public CardHolder(final View itemView) {
        super(itemView);
        backgroundCredit = (ImageView) itemView.findViewById(R.id.background_photo);
        mLinearCard = (FrameLayout) itemView.findViewById(R.id.linear_card);
        mTitle = (TextView) itemView.findViewById(R.id.title_main_cards);
        mSubhead = (TextView) itemView.findViewById(R.id.subhead_main_cards);
        mIcon = (ImageView) itemView.findViewById(R.id.icon_bank_cards);
        mDate = (RobotoThin) itemView.findViewById(R.id.date);
        mTime = (TextView) itemView.findViewById(R.id.time);
    }
}
