package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.database.models.ModelNFCItems;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.viewHolder.CardHolder;

import static com.maxcriser.cards.constant.ListConstants.RATIO_CREDIT_CARD;

public class CardCursorAdapter extends RecyclerView.Adapter<CardHolder> {

    private final Cursor mCursor;
    private final Context mContext;
    private final Object mView;
    private double viewWidth;
    private double viewHeight;

    public CardCursorAdapter(final Cursor pCursor, final Context pContext, final Object mObject) {
        mCursor = pCursor;
        mContext = pContext;
        mView = mObject;

        final WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        final int INT_DOUBLE_PADDING = 32;
        viewWidth = size.x - INT_DOUBLE_PADDING;
        viewHeight = viewWidth / RATIO_CREDIT_CARD;
        viewWidth /= 2;
        viewHeight /= 2;
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
                if (type.equals(ListConstants.Cards.VISA)) {
                    typeID = R.drawable.type_visa;
                } else if (type.equals(ListConstants.Cards.MASTERCARD)) {
                    typeID = R.drawable.type_mastercard;
                } else if (type.equals(ListConstants.Cards.AMEX)) {
                    typeID = R.drawable.type_amex;
                } else if (type.equals(ListConstants.Cards.MAESTRO)) {
                    typeID = R.drawable.type_maestro;
                } else if (type.equals(ListConstants.Cards.WESTERN_UNION)) {
                    typeID = R.drawable.type_western_union;
                } else if (type.equals(ListConstants.Cards.JCB)) {
                    typeID = R.drawable.type_jcb;
                } else if (type.equals(ListConstants.Cards.DINERS_CLUB)) {
                    typeID = R.drawable.type_diners_club;
                } else {
                    typeID = R.drawable.type_belcard;
                }
                holder.mIcon.setBackgroundResource(typeID);
                holder.mLinearCard.setBackgroundColor(getColor(mCursor, ModelBankCards.BACKGROUND_COLOR));
                holder.mTitle.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelBankCards.TITLE)));
                holder.mSubhead.setText(mCursor
                        .getString(mCursor.getColumnIndex(ModelBankCards.CARDHOLDER)));
                holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelBankCards.ID)));
                final String frontPhoto = mCursor.getString(mCursor.getColumnIndex(ModelBankCards.PHOTO_FRONT));
                final String backPhoto = mCursor.getString(mCursor.getColumnIndex(ModelBankCards.PHOTO_BACK));
                if (!frontPhoto.isEmpty()) {
                    ImageLoader.getInstance().downloadToView(frontPhoto, holder.backgroundCredit,
                            null, (int) viewWidth, (int) viewHeight);
                }
                if (!backPhoto.isEmpty()) {
                    ImageLoader.getInstance().downloadToView(backPhoto, holder.backgroundCreditBack,
                            null, (int) viewWidth, (int) viewHeight);
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