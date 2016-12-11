package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.constants;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.holder.CursorBankHolder;

public class CursorBankAdapter extends RecyclerView.Adapter<CursorBankHolder> {

    private final Cursor mCursor;
    private final Context mContext;
    private Object mView;

    public CursorBankAdapter(Cursor pCursor, Context pContext, Object mObject) {
        mCursor = pCursor;
        mContext = pContext;
        mView = mObject;
    }

    @Override
    public CursorBankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate((Integer) mView, parent, false);
        return new CursorBankHolder(view);
    }

    @Override
    public void onBindViewHolder(CursorBankHolder holder, int position) {
        if (mCursor.moveToPosition(position)) {
            String type = mCursor.getString(mCursor.getColumnIndex(ModelBankCards.BANK_TYPE));
            Integer typeID;
            if (type.equals(constants.VISA)) {
                typeID = R.drawable.type_visa;
            } else if (type.equals(constants.MASTERCARD)) {
                typeID = R.drawable.type_mastercard;
            } else if (type.equals(constants.AMEX)) {
                typeID = R.drawable.type_amex;
            } else if (type.equals(constants.MAESTRO)) {
                typeID = R.drawable.type_maestro;
            } else if (type.equals(constants.WESTERN_UNION)) {
                typeID = R.drawable.type_western_union;
            } else if (type.equals(constants.JCB)) {
                typeID = R.drawable.type_jcb;
            } else if (type.equals(constants.DINERS_CLUB)) {
                typeID = R.drawable.type_diners_club;
            } else {
                typeID = R.drawable.type_belcard;
            }
            holder.mIcon.setBackgroundResource(typeID);
            holder.mLinearCard.setBackgroundColor(Color.parseColor(mCursor
                    .getString(mCursor.getColumnIndex(ModelBankCards.BANK_BACKGROUND_COLOR))));
            holder.mTitle.setText(mCursor
                    .getString(mCursor.getColumnIndex(ModelBankCards.BANK_TITLE)));
            holder.mSubhead.setText(mCursor
                    .getString(mCursor.getColumnIndex(ModelBankCards.BANK_CARDHOLDER)));
            holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelBankCards.BANK_ID)));
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}