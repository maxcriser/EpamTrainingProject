package com.maxcriser.cards.viewHolder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
    public ImageView backgroundCreditBack;
    public CardView frontCardView;
    public CardView backCardView;
    public RelativeLayout recyclerViewToFlip;

    public CardHolder(final View itemView) {
        super(itemView);
        recyclerViewToFlip = (RelativeLayout) itemView.findViewById(R.id.relative_to_flip);
        frontCardView = (CardView) itemView.findViewById(R.id.front_cardview_to_flip);
        backCardView = (CardView) itemView.findViewById(R.id.back_cardview_to_flip);
        backgroundCredit = (ImageView) itemView.findViewById(R.id.background_photo);
        backgroundCreditBack = (ImageView) itemView.findViewById(R.id.background_photo_back);
        mLinearCard = (FrameLayout) itemView.findViewById(R.id.linear_card);
        mTitle = (TextView) itemView.findViewById(R.id.title_main_cards);
        mSubhead = (TextView) itemView.findViewById(R.id.subhead_main_cards);
        mIcon = (ImageView) itemView.findViewById(R.id.icon_bank_cards);
        mDate = (RobotoThin) itemView.findViewById(R.id.date);
        mTime = (TextView) itemView.findViewById(R.id.time);
    }
}
