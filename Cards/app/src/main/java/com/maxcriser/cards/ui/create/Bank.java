package com.maxcriser.cards.ui.create;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.StaticPageNames;
import com.maxcriser.cards.database.custom.ListTableItems;
import com.maxcriser.cards.reader.PreviewReader;
import com.maxcriser.cards.ui.pager.ViewPagerPreviewCard;
import com.maxcriser.cards.ui.pager.ViewPagerPreviewType;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import java.util.List;

public class Bank extends AppCompatActivity {

    static int PAGE_COUNT;
    static int PAGE_COUNT_TEMPLATE;
    static final int pagerMargin = 16;
    ViewPager pagerTypes;
    ViewPager pagerTemplate;
    PagerAdapter pagerAdapterTypes;
    PagerAdapter pagerAdapterTemplate;

    ListTableItems listColors;
    String myColorName;
    String myColorCode;
    String myTypeCard;
    public static List<ListTableItems> previewColors;
    public static List<String> previewTypes;
    // Color mColor - putExtra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(StaticPageNames.NEW_BANK_TITLE);

        final PreviewReader tcReader = PreviewReader.getInstance();
        tcReader.setTypeCard();
        previewTypes = tcReader.getTypeCard();

        myTypeCard = previewTypes.get(0);
        Log.d("TAG", myTypeCard);

        PAGE_COUNT = previewTypes.size();

        pagerTypes = (ViewPager) findViewById(R.id.type_card);
//        pagerTypes.setPageMargin(pagerMargin);
        pagerAdapterTypes = new MyFragmentPagerAdapterTypes(getSupportFragmentManager());
        pagerTypes.setAdapter(pagerAdapterTypes);
        pagerTypes.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                myTypeCard = previewTypes.get(position);
                Log.d("TAG", myTypeCard);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

        // VIEWPAGER template start)

        tcReader.setPreviewColors();
        previewColors = tcReader.getPreviewColors();

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorTable();
        myColorCode = listColors.getCodeColorTable();
        Log.d("TAG", myColorName + " " + myColorCode);

        PAGE_COUNT_TEMPLATE = previewColors.size();

        pagerTemplate = (ViewPager) findViewById(R.id.pager);
        pagerTemplate.setPageMargin(pagerMargin);
        pagerAdapterTemplate = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager());
        pagerTemplate.setAdapter(pagerAdapterTemplate);
        pagerTemplate.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                listColors = previewColors.get(position);
                myColorName = listColors.getNameColorTable();
                myColorCode = listColors.getCodeColorTable();
                Log.d("TAG", myColorName + " " + myColorCode);

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onCreateCardClicked(View view) {
    }

    private class MyFragmentPagerAdapterTemplate extends FragmentPagerAdapter {
        public MyFragmentPagerAdapterTemplate(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewPagerPreviewCard.newInstance(position);
        }

        @Override

        public int getCount() {
            return PAGE_COUNT_TEMPLATE;
        }
    }

    private class MyFragmentPagerAdapterTypes extends FragmentPagerAdapter {
        public MyFragmentPagerAdapterTypes(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewPagerPreviewType.newInstance(position);
        }

        @Override

        public int getCount() {
            return PAGE_COUNT;
        }
    }

}