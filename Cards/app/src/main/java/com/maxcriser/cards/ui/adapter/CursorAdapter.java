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
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.database.models.ModelNFCItems;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.viewHolder.CardHolder;

public class CursorAdapter extends RecyclerView.Adapter<CardHolder> {

    private final Cursor mCursor;
    private final Context mContext;
    private final Object mView;

    public CursorAdapter(final Cursor pCursor, final Context pContext, final Object mObject) {
        mCursor = pCursor;
        mContext = pContext;
        mView = mObject;
    }

    @Override
    public CardHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate((Integer) mView, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        if (mCursor.moveToPosition(position)) {
            if (mView.equals(R.layout.item_bank)) {
                final String type = mCursor.getString(mCursor.getColumnIndex(ModelBankCards.TYPE));
                final Integer typeID;
                if (type.equals(constants.Cards.VISA)) {
                    typeID = R.drawable.type_visa;
                } else if (type.equals(constants.Cards.MASTERCARD)) {
                    typeID = R.drawable.type_mastercard;
                } else if (type.equals(constants.Cards.AMEX)) {
                    typeID = R.drawable.type_amex;
                } else if (type.equals(constants.Cards.MAESTRO)) {
                    typeID = R.drawable.type_maestro;
                } else if (type.equals(constants.Cards.WESTERN_UNION)) {
                    typeID = R.drawable.type_western_union;
                } else if (type.equals(constants.Cards.JCB)) {
                    typeID = R.drawable.type_jcb;
                } else if (type.equals(constants.Cards.DINERS_CLUB)) {
                    typeID = R.drawable.type_diners_club;
                } else {
                    typeID = R.drawable.type_belcard;
                }
                holder.mIcon.setBackgroundResource(typeID);
                //TODO move to Utils method
                holder.mLinearCard.setBackgroundColor(getColor(mCursor, ModelBankCards.BACKGROUND_COLOR));
                holder.mTitle.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelBankCards.TITLE)));
                holder.mSubhead.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelBankCards.CARDHOLDER)));
                holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelBankCards.ID)));

                final String frontPhoto = mCursor.getString(mCursor.getColumnIndex(ModelBankCards.PHOTO_FRONT));
                if (!frontPhoto.isEmpty()) {
                    ImageLoader.getInstance().downloadToView(frontPhoto, holder.backgroundCredit, null);
                }

            } else if (mView.equals(R.layout.item_ticket)) {
                holder.mLinearCard.setBackgroundColor(getColor(mCursor, ModelTickets.BACKGROUND_COLOR));
                holder.mTitle.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelTickets.TITLE)));
                holder.mSubhead.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelTickets.CARDHOLDER)));
                holder.mDate.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelTickets.DATE)));
                holder.mTime.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelTickets.TIME)));
                holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelTickets.ID)));

            } else if (mView.equals(R.layout.item_discount)) {
                holder.mLinearCard.setBackgroundColor(getColor(mCursor, ModelDiscountCards.BACKGROUND_COLOR));
                holder.mTitle.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelDiscountCards.TITLE)));
                holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelDiscountCards.ID)));
            } else if (mView.equals(R.layout.item_nfc)) {
                holder.mLinearCard.setBackgroundColor(getColor(mCursor, ModelNFCItems.BACKGROUND_COLOR));
                holder.mTitle.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelNFCItems.TITLE)));
                holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelNFCItems.ID)));
            }
        }
    }

    private int getColor(final Cursor pCursor, final String stringColumn) {
        return Color.parseColor(pCursor.getString(pCursor
                .getColumnIndex(stringColumn)));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
}