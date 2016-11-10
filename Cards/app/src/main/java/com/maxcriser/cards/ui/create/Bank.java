package com.maxcriser.cards.ui.create;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.StaticPageNames;
import com.maxcriser.cards.database.custom.ListTableItems;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import static com.maxcriser.cards.constant.ViewPagerTemplate.ID_BANK_CARD_ITEM;
import static com.maxcriser.cards.constant.ViewPagerTemplate.ID_TYPE_BANK_CARD_ITEM;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

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
    // Color mColor - putExtra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(StaticPageNames.NEW_BANK_TITLE);

        myTypeCard = previewTypes.get(0);
        Log.d("TAG", myTypeCard);

        PAGE_COUNT = previewTypes.size();

        pagerTypes = (ViewPager) findViewById(R.id.type_card);
//        pagerTypes.setPageMargin(pagerMargin);
        pagerAdapterTypes = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_TYPE_BANK_CARD_ITEM,
                PAGE_COUNT);

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

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorTable();
        myColorCode = listColors.getCodeColorTable();
        Log.d("TAG", myColorName + " " + myColorCode);

        PAGE_COUNT_TEMPLATE = previewColors.size();

        pagerTemplate = (ViewPager) findViewById(R.id.pager);
        pagerTemplate.setPageMargin(pagerMargin);
        pagerAdapterTemplate = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_BANK_CARD_ITEM,
                PAGE_COUNT_TEMPLATE);

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

}