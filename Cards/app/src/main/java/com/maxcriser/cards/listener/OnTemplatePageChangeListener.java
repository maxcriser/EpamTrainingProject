//TODO listeners
package com.maxcriser.cards.listener;

import android.support.v4.view.ViewPager;

import com.maxcriser.cards.model.PreviewColor;

import static com.maxcriser.cards.ui.activities.LaunchScreenActivity.previewColors;

public class OnTemplatePageChangeListener implements ViewPager.OnPageChangeListener {
    public interface OnPageChangeListener {
        void onResult(int position, String codeColor, String nameColor);
    }

    private final OnPageChangeListener mListener;

    public OnTemplatePageChangeListener(final OnPageChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(final int position) {
        //TODO move array to parameter of class
        final PreviewColor mListPreviewColor = previewColors.get(position);
        mListener.onResult(position, mListPreviewColor.getCodeColorCards(),
                mListPreviewColor.getNameColorCards());
    }

    @Override
    public void onPageScrollStateChanged(final int state) {

    }
}
