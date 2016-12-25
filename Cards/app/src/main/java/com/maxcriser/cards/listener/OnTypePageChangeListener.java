package com.maxcriser.cards.listener;

import android.support.v4.view.ViewPager;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.constant.ListPreview;

public class OnTypePageChangeListener implements ViewPager.OnPageChangeListener {

    public interface OnPageChangeListener {

        void onResult(int position, Integer icon, String type);
    }

    private final OnPageChangeListener mListener;

    public OnTypePageChangeListener(final OnPageChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        final String myTypeCard = ListPreview.types.get(position);
        switch (myTypeCard) {
            case ListConstants.Cards.VISA:
                mListener.onResult(position, R.drawable.type_visa, myTypeCard);
                break;
            case ListConstants.Cards.MAESTRO:
                mListener.onResult(position, R.drawable.type_maestro, myTypeCard);
                break;
            case ListConstants.Cards.MASTERCARD:
                mListener.onResult(position, R.drawable.type_mastercard, myTypeCard);
                break;
            case ListConstants.Cards.AMEX:
                mListener.onResult(position, R.drawable.type_amex, myTypeCard);
                break;
            case ListConstants.Cards.WESTERN_UNION:
                mListener.onResult(position, R.drawable.type_western_union, myTypeCard);
                break;
            case ListConstants.Cards.JCB:
                mListener.onResult(position, R.drawable.type_jcb, myTypeCard);
                break;
            case ListConstants.Cards.DINERS_CLUB:
                mListener.onResult(position, R.drawable.type_diners_club, myTypeCard);
                break;
            case ListConstants.Cards.BELCARD:
                mListener.onResult(position, R.drawable.type_belcard, myTypeCard);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(final int state) {

    }
}
