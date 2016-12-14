package com.maxcriser.cards.util;

import android.support.v4.view.ViewPager;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.Constants;

import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

public class OnTypePageChangeListener implements ViewPager.OnPageChangeListener {
    public interface OnPageChangeListener {
        void onResult(int position, Integer icon, String type);
    }

    private OnPageChangeListener mListener;

    public OnTypePageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        String myTypeCard = previewTypes.get(position);
        switch (myTypeCard) {
            case Constants.Cards.VISA:
                mListener.onResult(position, R.drawable.type_visa, myTypeCard);
                break;
            case Constants.Cards.MAESTRO:
                mListener.onResult(position, R.drawable.type_maestro, myTypeCard);
                break;
            case Constants.Cards.MASTERCARD:
                mListener.onResult(position, R.drawable.type_mastercard, myTypeCard);
                break;
            case Constants.Cards.AMEX:
                mListener.onResult(position, R.drawable.type_amex, myTypeCard);
                break;
            case Constants.Cards.WESTERN_UNION:
                mListener.onResult(position, R.drawable.type_western_union, myTypeCard);
                break;
            case Constants.Cards.JCB:
                mListener.onResult(position, R.drawable.type_jcb, myTypeCard);
                break;
            case Constants.Cards.DINERS_CLUB:
                mListener.onResult(position, R.drawable.type_diners_club, myTypeCard);
                break;
            case Constants.Cards.BELCARD:
                mListener.onResult(position, R.drawable.type_belcard, myTypeCard);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
