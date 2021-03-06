package com.maxcriser.cards.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.create.Discount;
import com.maxcriser.cards.view.RobotoThinTextView;

public class ViewPagerPreviewCard extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;

    public static ViewPagerPreviewCard newInstance(int page) {
        ViewPagerPreviewCard viewPagerPreviewCard = new ViewPagerPreviewCard();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        viewPagerPreviewCard.setArguments(arguments);
        return viewPagerPreviewCard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discount_item, null);

        RobotoThinTextView tvPage = (RobotoThinTextView) view.findViewById(R.id.title_main_cards);
        FrameLayout mLinearCard = (FrameLayout) view.findViewById(R.id.linear_card);

        mLinearCard.setBackgroundColor(Color.parseColor(Discount.previewColors.get(pageNumber)));
        tvPage.setText(Discount.previewColors.get(pageNumber));

        return view;
    }
}
