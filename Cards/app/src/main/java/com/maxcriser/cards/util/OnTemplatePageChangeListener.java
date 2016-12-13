package com.maxcriser.cards.util;

import android.support.v4.view.ViewPager;

import com.maxcriser.cards.setter.PreviewColorsSetter;

import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class OnTemplatePageChangeListener implements ViewPager.OnPageChangeListener {
    public interface OnPageChangeListener {
        void onResult(int position, String codeColor, String nameColor);
    }

    private OnPageChangeListener mListener;

    public OnTemplatePageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        PreviewColorsSetter mListPreviewColorsSetter = previewColors.get(position);
        mListener.onResult(position, mListPreviewColorsSetter.getCodeColorCards(),
                mListPreviewColorsSetter.getNameColorCards());
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
