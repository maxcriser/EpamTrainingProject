package com.maxcriser.cards.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.constant.ListPreview;

import static com.maxcriser.cards.constant.ListConstants.ARGUMENT_PAGE_NUMBER_TYPE;

public class FragmentPreviewTypeCards extends Fragment {

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
        final String type = ListPreview.types.get(pageNumberType);
        switch (type) {
            case ListConstants.Cards.VISA:
                tvPage.setImageResource(R.drawable.type_visa);
                break;
            case ListConstants.Cards.MASTERCARD:
                tvPage.setImageResource(R.drawable.type_mastercard);
                break;
            case ListConstants.Cards.AMEX:
                tvPage.setImageResource(R.drawable.type_amex);
                break;
            case ListConstants.Cards.MAESTRO:
                tvPage.setImageResource(R.drawable.type_maestro);
                break;
            case ListConstants.Cards.WESTERN_UNION:
                tvPage.setImageResource(R.drawable.type_western_union);
                break;
            case ListConstants.Cards.JCB:
                tvPage.setImageResource(R.drawable.type_jcb);
                break;
            case ListConstants.Cards.DINERS_CLUB:
                tvPage.setImageResource(R.drawable.type_diners_club);
                break;
            case ListConstants.Cards.BELCARD:
                tvPage.setImageResource(R.drawable.type_belcard);
                break;
        }
        return view;
    }
}
