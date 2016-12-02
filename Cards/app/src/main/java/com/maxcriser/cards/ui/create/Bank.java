package com.maxcriser.cards.ui.create;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.ui.pager.ViewPagerPreviewCard;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.AMEX;
import static com.maxcriser.cards.constant.constants.BELCARD;
import static com.maxcriser.cards.constant.constants.DINERS_CLUB;
import static com.maxcriser.cards.constant.constants.ID_BANK_CARD_ITEM;
import static com.maxcriser.cards.constant.constants.ID_BANK_CARD_ITEM_TYPE;
import static com.maxcriser.cards.constant.constants.JCB;
import static com.maxcriser.cards.constant.constants.MAESTRO;
import static com.maxcriser.cards.constant.constants.MASTERCARD;
import static com.maxcriser.cards.constant.constants.NEW_BANK_TITLE;
import static com.maxcriser.cards.constant.constants.VISA;
import static com.maxcriser.cards.constant.constants.WESTERN_UNION;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

public class Bank extends AppCompatActivity {

    public static final String BANK = "Bank";
    int currentPositionColors;
    static int PAGE_COUNT;
    static int PAGE_COUNT_TEMPLATE;
    static final int pagerMargin = 16;
    ViewPager pagerTypes;
    ViewPager pagerTemplate;
    PagerAdapter pagerAdapterTypes;
    PagerAdapter pagerAdapterTemplate;

    Colors listColors;
    String myColorName;
    String myColorCode;
    String myTypeCard;
    // Color mColor - putExtra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);

        currentPositionColors = 0;

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(NEW_BANK_TITLE);

        // VIEWPAGER template start)

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorCards();
        myColorCode = listColors.getCodeColorCards();
        Log.d(BANK, myColorName + " " + myColorCode);

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
                myColorName = listColors.getNameColorCards();
                myColorCode = listColors.getCodeColorCards();
                Log.d(BANK, myColorName + " " + myColorCode);
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
        Log.d(BANK, myTypeCard);

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
                Log.d(BANK, myTypeCard);
                if (myTypeCard.equals(VISA)) {
                    ViewPagerPreviewCard.icon = R.drawable.type_visa;
                } else if (myTypeCard.equals(MAESTRO)) {
                    ViewPagerPreviewCard.icon = R.drawable.type_maestro;
                } else if (myTypeCard.equals(MASTERCARD)) {
                    ViewPagerPreviewCard.icon = R.drawable.type_mastercard;
                } else if (myTypeCard.equals(AMEX)) {
                    ViewPagerPreviewCard.icon = R.drawable.type_amex;
                } else if (myTypeCard.equals(WESTERN_UNION)) {
                    ViewPagerPreviewCard.icon = R.drawable.type_western_union;
                } else if (myTypeCard.equals(JCB)) {
                    ViewPagerPreviewCard.icon = R.drawable.type_jcb;
                } else if (myTypeCard.equals(DINERS_CLUB)) {
                    ViewPagerPreviewCard.icon = R.drawable.type_diners_club;
                } else if (myTypeCard.equals(BELCARD)) {
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

    public void onToolbarBackClicked(View view) {
    }
}