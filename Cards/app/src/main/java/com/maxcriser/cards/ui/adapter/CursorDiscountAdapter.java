package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.holder.CursorDiscountHolder;

public class CursorDiscountAdapter extends RecyclerView.Adapter<CursorDiscountHolder> {

    private final Cursor mCursor;
    private final Context mContext;
    private Object mView;

    public CursorDiscountAdapter(Cursor pCursor, Context pContext, Object mObject) {
        mCursor = pCursor;
        mContext = pContext;
        mView = mObject;
    }

    @Override
    public CursorDiscountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate((Integer) mView, parent, false);
        return new CursorDiscountHolder(view);
    }

    @Override
    public void onBindViewHolder(CursorDiscountHolder holder, int position) {
        if (mCursor.moveToPosition(position)) {
            holder.mLinearCard.setBackgroundColor(Color.parseColor(mCursor
                    .getString(mCursor.getColumnIndex(ModelDiscountCards.BACKGROUND_COLOR))));
            holder.mTitle.setText(mCursor
                    .getString(mCursor.getColumnIndex(ModelDiscountCards.TITLE)));
            holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelDiscountCards.ID)));
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}