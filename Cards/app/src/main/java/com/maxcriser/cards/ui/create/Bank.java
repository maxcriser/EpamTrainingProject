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
import com.maxcriser.cards.ui.pager.ViewPagerPreviewCard;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import static com.maxcriser.cards.constant.BankCardTypes.AMEX;
import static com.maxcriser.cards.constant.BankCardTypes.BELCARD;
import static com.maxcriser.cards.constant.BankCardTypes.DINERS_CLUB;
import static com.maxcriser.cards.constant.BankCardTypes.JCB;
import static com.maxcriser.cards.constant.BankCardTypes.MAESTRO;
import static com.maxcriser.cards.constant.BankCardTypes.MASTERCARD;
import static com.maxcriser.cards.constant.BankCardTypes.VISA;
import static com.maxcriser.cards.constant.BankCardTypes.WESTERN_UNION;
import static com.maxcriser.cards.constant.ViewPagerTemplate.ID_BANK_CARD_ITEM;
import static com.maxcriser.cards.constant.ViewPagerTemplate.ID_BANK_CARD_ITEM_TYPE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

public class Bank extends AppCompatActivity {

    int currentPositionColors;
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

        currentPositionColors = 0;

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(StaticPageNames.NEW_BANK_TITLE);

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
                currentPositionColors = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });

//        Preview Colors
        myTypeCard = previewTypes.get(0);
        Log.d("TAG", myTypeCard);

        PAGE_COUNT = previewTypes.size();

        pagerTypes = (ViewPager) findViewById(R.id.type_card);
        pagerAdapterTypes = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_BANK_CARD_ITEM_TYPE,
                PAGE_COUNT);

        pagerTypes.setAdapter(pagerAdapterTypes);
        ViewPagerPreviewCard.icon = R.drawable.type_visa;
        pagerTypes.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                myTypeCard = previewTypes.get(position);
                Log.d("TAG", myTypeCard);
                if (myTypeCard == VISA) {
                    ViewPagerPreviewCard.icon = R.drawable.type_visa;
                } else if (myTypeCard == MAESTRO) {
                    ViewPagerPreviewCard.icon = R.drawable.type_maestro;
                } else if (myTypeCard == MASTERCARD) {
                    ViewPagerPreviewCard.icon = R.drawable.type_mastercard;
                } else if (myTypeCard == AMEX) {
                    ViewPagerPreviewCard.icon = R.drawable.type_amex;
                } else if (myTypeCard == WESTERN_UNION) {
                    ViewPagerPreviewCard.icon = R.drawable.type_western_union;
                } else if (myTypeCard == JCB) {
                    ViewPagerPreviewCard.icon = R.drawable.type_jcb;
                } else if (myTypeCard == DINERS_CLUB) {
                    ViewPagerPreviewCard.icon = R.drawable.type_diners_club;
                } else if (myTypeCard == BELCARD){
                    ViewPagerPreviewCard.icon = R.drawable.type_belcard;
                }
                pagerTemplate.setAdapter(pagerAdapterTemplate);
                pagerTemplate.setCurrentItem(currentPositionColors);
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