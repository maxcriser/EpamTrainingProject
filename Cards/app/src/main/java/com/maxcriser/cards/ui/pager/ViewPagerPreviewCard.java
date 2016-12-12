package com.maxcriser.cards.ui.pager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.reader.Colors;

import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

//TODO rename class
public class ViewPagerPreviewCard extends Fragment {

    public static final String EXCEPTION_VP = "ExceptionVP";
    public static Object previewView;
    public static Object icon = R.drawable.type_visa;
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
//        icon = R.drawable.type_visa;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate((Integer) previewView, null);
        TextView tvPage = (TextView) view.findViewById(R.id.title_main_cards);
        FrameLayout mLinearCard = (FrameLayout) view.findViewById(R.id.linear_card);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon_bank_cards);
        if(imageView!=null) {

        }
        //TODO it's bad solution, handle exceptions
        try {
            imageView.setImageResource((Integer) icon);
        } catch (Exception e) {
            Log.d(EXCEPTION_VP, e.toString());
        }
        Colors color = previewColors.get(pageNumberDiscount);
        mLinearCard.setBackgroundColor(Color.parseColor(color.getCodeColorCards()));
        tvPage.setText(color.getNameColorCards());

        return view;
    }
}
