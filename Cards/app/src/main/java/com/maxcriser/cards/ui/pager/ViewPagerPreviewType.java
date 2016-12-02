package com.maxcriser.cards.ui.pager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maxcriser.cards.R;

import static com.maxcriser.cards.constant.constants.AMEX;
import static com.maxcriser.cards.constant.constants.BELCARD;
import static com.maxcriser.cards.constant.constants.DINERS_CLUB;
import static com.maxcriser.cards.constant.constants.JCB;
import static com.maxcriser.cards.constant.constants.MAESTRO;
import static com.maxcriser.cards.constant.constants.MASTERCARD;
import static com.maxcriser.cards.constant.constants.VISA;
import static com.maxcriser.cards.constant.constants.WESTERN_UNION;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

public class ViewPagerPreviewType extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER_TYPE = "arg_page_number_type";

    int pageNumberType;

    public static ViewPagerPreviewType newInstance(int page) {
        ViewPagerPreviewType viewPagerPreviewCard = new ViewPagerPreviewType();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER_TYPE, page);
        viewPagerPreviewCard.setArguments(arguments);
        return viewPagerPreviewCard;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumberType = getArguments().getInt(ARGUMENT_PAGE_NUMBER_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_type_item, null);
        // pageNumberType
        ImageView tvPage = (ImageView) view.findViewById(R.id.icon_type_card);
        String type = previewTypes.get(pageNumberType);
        if (type.equals(VISA)) {
            tvPage.setImageResource(R.drawable.type_visa);
        } else if (type.equals(MASTERCARD)) {
            tvPage.setImageResource(R.drawable.type_mastercard);
        } else if (type.equals(AMEX)) {
            tvPage.setImageResource(R.drawable.type_amex);
        } else if (type.equals(MAESTRO)) {
            tvPage.setImageResource(R.drawable.type_maestro);
        } else if (type.equals(WESTERN_UNION)) {
            tvPage.setImageResource(R.drawable.type_western_union);
        } else if (type.equals(JCB)) {
            tvPage.setImageResource(R.drawable.type_jcb);
        } else if (type.equals(DINERS_CLUB)) {
            tvPage.setImageResource(R.drawable.type_diners_club);
        } else if (type.equals(BELCARD)) {
            tvPage.setImageResource(R.drawable.type_belcard);
        }
        return view;
    }
}
