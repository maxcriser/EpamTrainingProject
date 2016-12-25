package com.maxcriser.cards.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.ListConstants;

public class FragmentPagerAdapterTemplate extends FragmentPagerAdapter {

    private final int adapter;
    private final int page;

    public FragmentPagerAdapterTemplate(final FragmentManager fm, final int adapter, final int page) {
        super(fm);
        this.adapter = adapter;
        this.page = page;
    }

    @Override
    public Fragment getItem(final int position) {
        if (adapter == ListConstants.PagerIDs.ID_BANK_CARD_ITEM_TYPE) {
            return FragmentPreviewTypeCards.newInstance(position);
        } else if (adapter == ListConstants.PagerIDs.ID_DISCOUNT_ITEM) {
            return FragmentPreviewCards.newInstance(position, R.layout.item_discount);
        } else if (adapter == ListConstants.PagerIDs.ID_TICKET_ITEM) {
            return FragmentPreviewCards.newInstance(position, R.layout.item_ticket);
        } else {
            return FragmentPreviewCards.newInstance(position, R.layout.item_list_bank);
        }
    }

    @Override
    public int getCount() {
        return page;
    }
}