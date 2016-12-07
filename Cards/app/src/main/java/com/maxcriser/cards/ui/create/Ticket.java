package com.maxcriser.cards.ui.create;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.maxcriser.cards.R;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.view.TextViews.EANP72TextView;
import com.maxcriser.cards.view.TextViews.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.ID_DISCOUNT_ITEM;
import static com.maxcriser.cards.constant.constants.NEW_TICKET_TITLE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class Ticket extends AppCompatActivity {

    public static final String DISCOUNT = "Ticket";
    DatabaseHelper db;

    static int PAGE_COUNT;
    static final int pagerMargin = 16;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    Colors listColors;
    String myColorName;
    String myColorCode;
    RobotoRegular title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();

        db = DatabaseHelper.getInstance(this, 1);
        title.setText(NEW_TICKET_TITLE);

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorCards();
        myColorCode = listColors.getCodeColorCards();
        Log.d(DISCOUNT, myColorName + " " + myColorCode);
        PAGE_COUNT = previewColors.size();

        pager.setPageMargin(pagerMargin);
        pagerAdapter = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_DISCOUNT_ITEM,
                PAGE_COUNT);

        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                listColors = previewColors.get(position);
                myColorName = listColors.getNameColorCards();
                myColorCode = listColors.getCodeColorCards();
                Log.d(DISCOUNT, myColorName + " " + myColorCode);
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

    private void initViews() {
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onToolbarBackClicked(View view) {
    }
}