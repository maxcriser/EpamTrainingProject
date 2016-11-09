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
import com.maxcriser.cards.ui.create.Discount;
import com.maxcriser.cards.view.TextViews.RobotoThinTextView;

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

        ListTableItems color = Discount.previewColors.get(pageNumber);
        mLinearCard.setBackgroundColor(Color.parseColor(color.getCodeColorTable()));
        tvPage.setText(color.getNameColorTable());

        return view;
    }
}
