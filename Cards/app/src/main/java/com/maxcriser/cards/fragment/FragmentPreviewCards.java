package com.maxcriser.cards.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.model.PreviewColor;

import static com.maxcriser.cards.constant.ListConstants.ARGUMENT_PAGE_NUMBER_DISCOUNT;
import static com.maxcriser.cards.ui.activities.LaunchScreenActivity.previewColors;

public class FragmentPreviewCards extends Fragment {

    public static Object previewView;
    int pageNumberDiscount;

    public static FragmentPreviewCards newInstance(final int pageDiscount, final Object pView) {
        previewView = pView;
        final FragmentPreviewCards fragmentPreviewCards = new FragmentPreviewCards();
        final Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER_DISCOUNT, pageDiscount);
        fragmentPreviewCards.setArguments(arguments);
        return fragmentPreviewCards;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumberDiscount = getArguments().getInt(ARGUMENT_PAGE_NUMBER_DISCOUNT);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate((Integer) previewView, null);
        final TextView tvPage = (TextView) view.findViewById(R.id.title_main_cards);
        final FrameLayout mLinearCard = (FrameLayout) view.findViewById(R.id.linear_card);
        final PreviewColor color = previewColors.get(pageNumberDiscount);
        mLinearCard.setBackgroundColor(Color.parseColor(color.getCodeColorCards()));
        tvPage.setText(color.getNameColorCards());
        return view;
    }
}
