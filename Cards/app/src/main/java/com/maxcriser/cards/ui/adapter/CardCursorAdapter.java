package com.maxcriser.cards.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.maxcriser.cards.R;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.database.models.ModelNFCItems;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.viewHolder.CardHolder;
import com.squareup.picasso.Picasso;

import static com.maxcriser.cards.constant.ListConstants.RATIO_CREDIT_CARD;

public class CardCursorAdapter extends RecyclerView.Adapter<CardHolder> {

    private final Cursor mCursor;
    private final Context mContext;
    private final Object mView;
    private final double viewWidth;
    private final double viewHeight;

    public CardCursorAdapter(final Cursor pCursor, final Context pContext, final Object mObject) {
        mCursor = pCursor;
        mContext = pContext;
        mView = mObject;

        final WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        final Display display = wm.getDefaultDisplay();
        final Point size = new Point();
        display.getSize(size);
        final int intDoublePadding = 32;
        viewWidth = size.x - intDoublePadding;
        viewHeight = viewWidth / RATIO_CREDIT_CARD;
    }

    @Override
    public CardHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(mContext).inflate((Integer) mView, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(final CardHolder holder, final int position) {
        if (mCursor.moveToPosition(position)) {
            if (mView.equals(R.layout.item_bank) || mView.equals(R.layout.item_discount)) {
                holder.mTitle.setTag(mCursor.getInt(mCursor.getColumnIndex(ModelBankCards.ID)));
                final String frontPhoto = mCursor.getString(mCursor.getColumnIndex(ModelBankCards.PHOTO_FRONT));
                final String backPhoto = mCursor.getString(mCursor.getColumnIndex(ModelBankCards.PHOTO_BACK));
                int idBackgroundSvg = R.drawable.camera_card_size;
                if (!frontPhoto.isEmpty()) {
                    // TODO error drawable
                    if (mView.equals(R.layout.item_bank)) {
                        idBackgroundSvg = R.drawable.svg_credit_card;
                    } else if (mView.equals(R.layout.item_discount)) {
                        idBackgroundSvg = R.drawable.svg_discount_card;
                    }
                    Picasso.with(mContext).load(Uri.parse(frontPhoto)).placeholder(idBackgroundSvg)
                            .resize((int) viewWidth, (int) viewHeight).into(holder.backgroundCredit);
                }
                if (!backPhoto.isEmpty()) {
                    Picasso.with(mContext).load(Uri.parse(backPhoto)).placeholder(idBackgroundSvg)
                            .resize((int) viewWidth, (int) viewHeight).into(holder.backgroundCreditBack);
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