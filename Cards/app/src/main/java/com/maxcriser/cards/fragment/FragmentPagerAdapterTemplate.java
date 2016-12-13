package com.maxcriser.cards.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maxcriser.cards.R;

public class FragmentPagerAdapterTemplate extends FragmentPagerAdapter {

    private int adapter;
    private int page;

    public FragmentPagerAdapterTemplate(FragmentManager fm, int adapter, int page) {
        super(fm);
        this.adapter = adapter;
        this.page = page;
    }

    @Override
    public Fragment getItem(int position) {
        if (adapter == 1)
            return FragmentPreviewTypeCards.newInstance(position);
        else if (adapter == 2)
            return FragmentPreviewCards.newInstance(position, R.layout.item_discount);
        else if (adapter == 3)
            return FragmentPreviewCards.newInstance(position, R.layout.item_ticket);
        else
            return FragmentPreviewCards.newInstance(position, R.layout.item_list_bank);
    }

    @Override
    public int getCount() {
        return page;
    }
}