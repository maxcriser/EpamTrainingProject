package com.maxcriser.cards.ui.create_item;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.task.BarcodeConverter;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.setter.PreviewColorsSetter;
import com.maxcriser.cards.ui.BarcodeScannerActivity;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.view.text_view.BarcodeEan;
import com.maxcriser.cards.view.text_view.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.sPreviewColorSetters;

public class CreateDiscountActivity extends AppCompatActivity {

    public static final String DISCOUNT_ID = "CreateDiscountActivity";
    ContentValues cvNewDiscount;
    DatabaseHelperImpl db;
    ScrollView mScrollView;

    static int PAGE_COUNT;
    static final int pagerMargin = 16;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    EditText mEditText;
    //TODO private
    BarcodeEan mBarcodeEan;
    String mBarcode;

    PreviewColorsSetter mListPreviewColorsSetter;
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

        db = DatabaseHelperImpl.getInstance(this);
        title.setText(Constants.NEW_TITLES.NEW_DISCOUNT_TITLE);
        Intent barcodeIntent = getIntent();
        mBarcode = barcodeIntent.getStringExtra(BarcodeScannerActivity.TAG_BARCODE);
        OwnAsyncTask barcodeGenerator = new OwnAsyncTask();
        barcodeGenerator.execute(new BarcodeConverter(), mBarcode, new OnResultCallback<String, String>() {
            @Override
            public void onSuccess(String pS) {
                generateBarcode = pS;
                mBarcodeEan.setText(generateBarcode);
            }

            @Override
            public void onError(Exception pE) {
            }

            @Override
            public void onProgressChanged(String pS) {
            }
        });

        mListPreviewColorsSetter = sPreviewColorSetters.get(0);
        myColorName = mListPreviewColorsSetter.getNameColorCards();
        myColorCode = mListPreviewColorsSetter.getCodeColorCards();
        Log.d(DISCOUNT_ID, myColorName + " " + myColorCode);

        PAGE_COUNT = sPreviewColorSetters.size();
        //TODO move to common class
        pager.setPageMargin(pagerMargin);
        pagerAdapter = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                Constants.ID_PAGERS.ID_DISCOUNT_ITEM,
                PAGE_COUNT);

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mListPreviewColorsSetter = sPreviewColorSetters.get(position);
                myColorName = mListPreviewColorsSetter.getNameColorCards();
                myColorCode = mListPreviewColorsSetter.getCodeColorCards();
                Log.d(DISCOUNT_ID, myColorName + " " + myColorCode);

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
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        mBarcodeEan = (BarcodeEan) findViewById(R.id.generate_barcode);
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
        if (!titleStr.equals(Constants.EMPTY_STRING)) {
            cvNewDiscount = new ContentValues();
            cvNewDiscount.put(ModelDiscountCards.TITLE, titleStr);
            cvNewDiscount.put(ModelDiscountCards.BARCODE, generateBarcode);
            cvNewDiscount.put(ModelDiscountCards.BACKGROUND_COLOR, myColorCode);
            cvNewDiscount.put(ModelDiscountCards.ID, (Integer) null);

            db.insert(ModelDiscountCards.class, cvNewDiscount, new OnResultCallback<Long, Void>() {
                @Override
                public void onSuccess(Long pLong) {
                    Log.d(DISCOUNT_ID + " ID", pLong.toString());
                }

                @Override
                public void onError(Exception pE) {
                }

                @Override
                public void onProgressChanged(Void pVoid) {
                }
            });
            onBackClicked(null);
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
        }
    }

    public void onToolbarBackClicked(View view) {
    }

    public void onCancelClicked(View view) {
        onBackClicked(null);
    }

    public void onPrevColorPagerClicked(View view) {
        pager.setCurrentItem(pager.getCurrentItem() - 1);
    }

    public void onNextColorPagerClicked(View view) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }
}