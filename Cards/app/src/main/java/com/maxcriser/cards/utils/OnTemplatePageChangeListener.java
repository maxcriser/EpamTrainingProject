//TODO listeners
package com.maxcriser.cards.utils;

import android.support.v4.view.ViewPager;

import com.maxcriser.cards.model.PreviewColor;

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

    }

    @Override
    public void onPageSelected(int position) {
        //TODO move array to parameter of class
        PreviewColor mListPreviewColor = previewColors.get(position);
        mListener.onResult(position, mListPreviewColor.getCodeColorCards(),
                mListPreviewColor.getNameColorCards());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
