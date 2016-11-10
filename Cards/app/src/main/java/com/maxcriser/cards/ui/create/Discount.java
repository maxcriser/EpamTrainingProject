package com.maxcriser.cards.ui.create;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import com.maxcriser.cards.async.FalseAsyncTask;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.task.BarcodeConverter;
import com.maxcriser.cards.barcode.BarcodeBuilder;
import com.maxcriser.cards.barcode.BarcodeScanner;
import com.maxcriser.cards.constant.StaticPageNames;
import com.maxcriser.cards.database.custom.ListTableItems;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.ui.pager.ViewPagerPreviewCard;
import com.maxcriser.cards.view.TextViews.EANP72TextView;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import static com.maxcriser.cards.constant.ViewPagerTemplate.ID_DISCOUNT_ITEM;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class Discount extends AppCompatActivity {

    ContentValues cv;
    SQLiteDatabase db;

    static int PAGE_COUNT;
    static final int pagerMargin = 16;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    BarcodeBuilder mBarcodeBuilder;
    EANP72TextView mEANP72TextView;
    String mBarcode;

    ListTableItems listColors;
    String myColorName;
    String myColorCode;
    String title; // database
    String generateBarcode; // database
    // Color mColor - putExtra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(StaticPageNames.NEW_DISCOUNT_TITLE);

        Intent barcodeIntent = getIntent();
        mBarcode = barcodeIntent.getStringExtra(BarcodeScanner.TAG_BARCODE);
        mEANP72TextView = (EANP72TextView) findViewById(R.id.generate_barcode);

        FalseAsyncTask barcodeGenerator = new FalseAsyncTask();
        barcodeGenerator.execute(new BarcodeConverter(), mBarcode, new OnResultCallback<String, String>() {
            @Override
            public void onSuccess(String pS) {
                mEANP72TextView.setText(pS);
            }

            @Override
            public void onError(Exception pE) {
            }

            @Override
            public void onProgressChanged(String pS) {
            }
        });

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorTable();
        myColorCode = listColors.getCodeColorTable();
        Log.d("TAG", myColorName + " " + myColorCode);

        PAGE_COUNT = previewColors.size();

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setPageMargin(pagerMargin);
        pagerAdapter = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_DISCOUNT_ITEM,
                PAGE_COUNT);

        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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