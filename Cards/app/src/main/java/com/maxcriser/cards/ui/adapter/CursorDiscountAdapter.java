package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.holder.CursorDiscountHolder;

public class CursorDiscountAdapter extends RecyclerView.Adapter<CursorDiscountHolder> {

    private final Cursor mCursor;
    private final Context mContext;
    private final String filterString;
    private int added = 0;
    private Object mView;

    public CursorDiscountAdapter(Cursor pCursor, Context pContext, Object mObject, String filterString) {
        mCursor = pCursor;
        mContext = pContext;
        mView = mObject;
        this.filterString = filterString;
    }

    @Override
    public CursorDiscountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate((Integer) mView, parent, false);
        return new CursorDiscountHolder(view);
    }

    public void setContent(CursorDiscountHolder holder, int position) {
        holder.mLinearCard.setBackgroundColor(Color.parseColor(mCursor
                .getString(mCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_BACKGROUND_COLOR))));
        holder.mTitle.setText(mCursor
                .getString(mCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_TITLE)));
        holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_ID)));
    }

    @Override
    public void onBindViewHolder(CursorDiscountHolder holder, int position) {
        if (filterString.equals("")) {
            added = 0;
            if (mCursor.moveToPosition(position)) {
                setContent(holder, position);
            }
        } else {
            if (mCursor.moveToPosition(position)) {
                if (mCursor.getString(mCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_TITLE)).startsWith(filterString)) {
                    Log.d("TAGGDSFGSD", mCursor.getString(mCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_TITLE)));
                    added++;
                    setContent(holder, position);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (added != 0)
            return added;
        else
            return mCursor.getCount();
    }
}