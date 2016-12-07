package com.maxcriser.cards.ui.create;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.FalseAsyncTask;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.task.BarcodeConverter;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.ui.BarcodeScanner;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.view.TextViews.EANP72TextView;
import com.maxcriser.cards.view.TextViews.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.ID_DISCOUNT_ITEM;
import static com.maxcriser.cards.constant.constants.NEW_DISCOUNT_TITLE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class Discount extends AppCompatActivity {

    public static final String DISCOUNT = "Discount";
    ContentValues cvNewDiscount;
    DatabaseHelper db;

    static int PAGE_COUNT;
    static final int pagerMargin = 16;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    EditText mEditText;

    EANP72TextView mEANP72TextView;
    String mBarcode;

    Colors listColors;
    String myColorName;
    String myColorCode;
    String titleStr; // database
    String generateBarcode; // database
    RobotoRegular title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();

        db = DatabaseHelper.getInstance(this, 1);
        title.setText(NEW_DISCOUNT_TITLE);
        Intent barcodeIntent = getIntent();
        mBarcode = barcodeIntent.getStringExtra(BarcodeScanner.TAG_BARCODE);
        FalseAsyncTask barcodeGenerator = new FalseAsyncTask();
        barcodeGenerator.execute(new BarcodeConverter(), mBarcode, new OnResultCallback<String, String>() {
            @Override
            public void onSuccess(String pS) {
                generateBarcode = pS;
                mEANP72TextView.setText(generateBarcode);
            }

            @Override
            public void onError(Exception pE) {
            }

            @Override
            public void onProgressChanged(String pS) {
            }
        });

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
        mEANP72TextView = (EANP72TextView) findViewById(R.id.generate_barcode);
        mEditText = (EditText) findViewById(R.id.id_edit_text_name_discount);
        pager = (ViewPager) findViewById(R.id.pager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onCreateCardClicked(View view) {
        titleStr = mEditText.getText().toString();
        if (!titleStr.equals("")) {
            cvNewDiscount = new ContentValues();
            cvNewDiscount.put(ModelDiscountCards.DISCOUNT_TITLE, titleStr);
            cvNewDiscount.put(ModelDiscountCards.DISCOUNT_BARCODE, generateBarcode);
            cvNewDiscount.put(ModelDiscountCards.DISCOUNT_BACKGROUND_COLOR, myColorCode);
            cvNewDiscount.put(ModelDiscountCards.DISCOUNT_ID, (Integer) null);

            db.insert(ModelDiscountCards.class, cvNewDiscount, new OnResultCallback<Long, Void>() {
                @Override
                public void onSuccess(Long pLong) {
                    Log.d(DISCOUNT + " ID", pLong.toString());
                }

                @Override
                public void onError(Exception pE) {
                }

                @Override
                public void onProgressChanged(Void pVoid) {
                }
            });

            onBackClicked(null);
        }
    }

    public void onToolbarBackClicked(View view) {
    }

    public void onCancelClicked(View view) {
        onBackClicked(null);
    }
}