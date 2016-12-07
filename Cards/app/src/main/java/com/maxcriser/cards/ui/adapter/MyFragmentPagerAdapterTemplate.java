package com.maxcriser.cards.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.pager.ViewPagerPreviewCard;
import com.maxcriser.cards.ui.pager.ViewPagerPreviewType;

public class MyFragmentPagerAdapterTemplate extends FragmentPagerAdapter {

    private int adapter;
    private int page;

    public MyFragmentPagerAdapterTemplate(FragmentManager fm, int adapter, int page) {
        super(fm);
        this.adapter = adapter;
        this.page = page;
    }

    @Override
    public Fragment getItem(int position) {
        if (adapter == 1)
            return ViewPagerPreviewType.newInstance(position);
        else if (adapter == 2)
            return ViewPagerPreviewCard.newInstance(position, R.layout.item_discount);
        else if (adapter == 3)
            return ViewPagerPreviewCard.newInstance(position, R.layout.item_ticket);
        else
            return ViewPagerPreviewCard.newInstance(position, R.layout.item_list_bank);
    }

    @Override
    public int getCount() {
        return page;
    }
}