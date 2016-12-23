package com.maxcriser.cards.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.constants;

import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

public class FragmentPreviewTypeCards extends Fragment {

    static final String ARGUMENT_PAGE_NUMBER_TYPE = "arg_page_number_type";

    int pageNumberType;

    public static FragmentPreviewTypeCards newInstance(final int page) {
        final FragmentPreviewTypeCards viewPagerPreviewCard = new FragmentPreviewTypeCards();
        final Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER_TYPE, page);
        viewPagerPreviewCard.setArguments(arguments);
        return viewPagerPreviewCard;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumberType = getArguments().getInt(ARGUMENT_PAGE_NUMBER_TYPE);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.preview_type_item, null);
        final ImageView tvPage = (ImageView) view.findViewById(R.id.icon_type_card);
        final String type = previewTypes.get(pageNumberType);
        switch (type) {
            case constants.Cards.VISA:
                tvPage.setImageResource(R.drawable.type_visa);
                break;
            case constants.Cards.MASTERCARD:
                tvPage.setImageResource(R.drawable.type_mastercard);
                break;
            case constants.Cards.AMEX:
                tvPage.setImageResource(R.drawable.type_amex);
                break;
            case constants.Cards.MAESTRO:
                tvPage.setImageResource(R.drawable.type_maestro);
                break;
            case constants.Cards.WESTERN_UNION:
                tvPage.setImageResource(R.drawable.type_western_union);
                break;
            case constants.Cards.JCB:
                tvPage.setImageResource(R.drawable.type_jcb);
                break;
            case constants.Cards.DINERS_CLUB:
                tvPage.setImageResource(R.drawable.type_diners_club);
                break;
            case constants.Cards.BELCARD:
                tvPage.setImageResource(R.drawable.type_belcard);
                break;
        }
        return view;
    }
}
