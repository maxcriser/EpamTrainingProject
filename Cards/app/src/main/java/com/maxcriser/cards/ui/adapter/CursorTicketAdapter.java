package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.holder.CursorTicketHolder;

public class CursorTicketAdapter extends RecyclerView.Adapter<CursorTicketHolder> {

    private final Cursor mCursor;
    private final Context mContext;
    private Object mView;

    public CursorTicketAdapter(Cursor pCursor, Context pContext, Object mObject) {
        mCursor = pCursor;
        mContext = pContext;
        mView = mObject;
    }

    @Override
    public CursorTicketHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate((Integer) mView, parent, false);
        return new CursorTicketHolder(view);
    }

    @Override
    public void onBindViewHolder(CursorTicketHolder holder, int position) {
        if (mCursor.moveToPosition(position)) {
            holder.mLinearCard.setBackgroundColor(Color.parseColor(mCursor
                    .getString(mCursor.getColumnIndex(ModelTickets.BACKGROUND_COLOR))));
            holder.mTitle.setText(mCursor
                    .getString(mCursor.getColumnIndex(ModelTickets.TITLE)));
            holder.mSubhead.setText(mCursor
                    .getString(mCursor.getColumnIndex(ModelTickets.CARDHOLDER)));
            holder.mDate.setText(mCursor
                    .getString(mCursor.getColumnIndex(ModelTickets.DATE)));
            holder.mTime.setText(mCursor
                    .getString(mCursor.getColumnIndex(ModelTickets.TIME)));
            holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelTickets.ID)));
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}