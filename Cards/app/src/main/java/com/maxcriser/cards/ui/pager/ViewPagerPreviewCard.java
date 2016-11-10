package com.maxcriser.cards.ui.pager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.maxcriser.cards.R;
import com.maxcriser.cards.database.custom.ListTableItems;
import com.maxcriser.cards.view.TextViews.RobotoThinTextView;

import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class ViewPagerPreviewCard extends Fragment {

    public static Object previewView;

    static final String ARGUMENT_PAGE_NUMBER_DISCOUNT = "arg_page_number";

    int pageNumberDiscount;

    public static ViewPagerPreviewCard newInstance(int pageDiscount, Object pView) {
        previewView = pView;
        ViewPagerPreviewCard viewPagerPreviewCard = new ViewPagerPreviewCard();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER_DISCOUNT, pageDiscount);
        viewPagerPreviewCard.setArguments(arguments);
        return viewPagerPreviewCard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumberDiscount = getArguments().getInt(ARGUMENT_PAGE_NUMBER_DISCOUNT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate((Integer) previewView, null);

        RobotoThinTextView tvPage = (RobotoThinTextView) view.findViewById(R.id.title_main_cards);
        FrameLayout mLinearCard = (FrameLayout) view.findViewById(R.id.linear_card);

        ListTableItems color = previewColors.get(pageNumberDiscount);
        mLinearCard.setBackgroundColor(Color.parseColor(color.getCodeColorTable()));
        tvPage.setText(color.getNameColorTable());

        return view;
    }
}
