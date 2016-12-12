package com.maxcriser.cards.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.view.TextViews.RobotoThin;

public class CursorTicketHolder extends RecyclerView.ViewHolder {

    public TextView mTitle;
    public TextView mSubhead;
    public FrameLayout mLinearCard;
    public RobotoThin mDate;
    public TextView mTime;

    public CursorTicketHolder(View itemView) {
        super(itemView);
        mLinearCard = (FrameLayout) itemView.findViewById(R.id.linear_card);
        mTitle = (TextView) itemView.findViewById(R.id.title_main_cards);
        mSubhead = (TextView) itemView.findViewById(R.id.subhead_main_cards);
        mDate = (RobotoThin) itemView.findViewById(R.id.date);
        mTime = (TextView) itemView.findViewById(R.id.time);
    }
}
