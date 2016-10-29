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
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.BarcodeConverter;
import com.maxcriser.cards.async.FalseAsyncTask;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.barcode.BarcodeScanner;
import com.maxcriser.cards.barcode.EAN13CodeBuilder;
import com.maxcriser.cards.reader.PreviewColor.PreviewColorReader;
import com.maxcriser.cards.ui.ViewPagerPreviewCard;
import com.maxcriser.cards.view.EANP72TextView;

import java.util.List;

public class Discount extends AppCompatActivity {

    ContentValues cv;
    SQLiteDatabase db;

    static int PAGE_COUNT;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    EAN13CodeBuilder mEAN13CodeBuilder;
    EANP72TextView mEANP72TextView;
    String mBarcode;

    String title; // database
    String generateBarcode; // database
    String myColor;
    public static List<String> previewColors;
    // Color mColor - putExtra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);

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
        final PreviewColorReader tcReader = PreviewColorReader.getInstance();
        tcReader.setPreviewColors();

        previewColors = tcReader.getPreviewColors();
        myColor = previewColors.get(0);
        PAGE_COUNT = previewColors.size();

        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                myColor = previewColors.get(position);
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
//        mDBHelper.close();
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onCreateCardClicked(View view) {
//                TODO write to database
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ViewPagerPreviewCard.newInstance(position);
        }

        @Override

        public int getCount() {
            return PAGE_COUNT;
        }
    }
}