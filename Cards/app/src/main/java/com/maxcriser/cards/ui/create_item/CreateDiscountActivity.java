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
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.BarcodeConverter;
import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.constant.ListPreview;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.listener.OnTemplatePageChangeListener;
import com.maxcriser.cards.model.PreviewColor;
import com.maxcriser.cards.ui.activities.BarcodeScannerActivity;
import com.maxcriser.cards.view.labels.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.ListConstants.TAG_BARCODE;

public class CreateDiscountActivity extends AppCompatActivity {

    public final String DISCOUNT_ID = "CreateDiscountActivity";
    private DatabaseHelper db;
    private ScrollView mScrollView;
    private ViewPager pager;
    private EditText mEditText;
    private String myColorName;
    private String myColorCode;
    private String generateBarcode;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        final RobotoRegular title = (RobotoRegular) findViewById(R.id.title_toolbar);
        mEditText = (EditText) findViewById(R.id.id_edit_text_name_discount);
        pager = (ViewPager) findViewById(R.id.pager);
        db = DatabaseHelper.getInstance(this);
        title.setText(getResources().getString(R.string.new_discount_title));
        final Intent barcodeIntent = getIntent();
        final String barcode = barcodeIntent.getStringExtra(TAG_BARCODE);
        final OwnAsyncTask barcodeGenerator = new OwnAsyncTask();
        barcodeGenerator.execute(new BarcodeConverter(), barcode, new OnResultCallback<String, String>() {

            @Override
            public void onSuccess(final String pS) {
                generateBarcode = pS;
            }

            @Override
            public void onError(final Exception pE) {
                Toast.makeText(CreateDiscountActivity.this, R.string.cannot_convert_barcode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChanged(final String pS) {
            }
        });

        final PreviewColor listPreviewColor = ListPreview.colors.get(0);
        myColorName = listPreviewColor.getNameColorCards();
        myColorCode = listPreviewColor.getCodeColorCards();
        Log.d(DISCOUNT_ID, myColorName + " " + myColorCode);

        final int pageCount = ListPreview.colors.size();
        pager.setPageMargin(ListConstants.PAGER_MARGIN_PREVIEW);
        final PagerAdapter pagerAdapter = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ListConstants.PagerIDs.ID_DISCOUNT_ITEM,
                pageCount);

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new OnTemplatePageChangeListener(new OnTemplatePageChangeListener.OnPageChangeListener() {

            @Override
            public void onResult(final int position, final String codeColor, final String nameColor) {
                myColorCode = codeColor;
                myColorName = nameColor;
                Log.d("COLOR", position + myColorName + myColorCode);
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    public void onCreateCardClicked(final View view) {
        final String titleStr = mEditText.getText().toString();
        Log.d("titleStr", titleStr);
        if (!titleStr.isEmpty()) {
            Log.d("titleStr", titleStr);
            final ContentValues cvNewDiscount = new ContentValues();
            cvNewDiscount.put(ModelDiscountCards.TITLE, titleStr);
            cvNewDiscount.put(ModelDiscountCards.BARCODE, generateBarcode);
            cvNewDiscount.put(ModelDiscountCards.BACKGROUND_COLOR, myColorCode);
            cvNewDiscount.put(ModelDiscountCards.ID, (Integer) null);

            db.insert(ModelDiscountCards.class, cvNewDiscount, new OnResultCallback<Long, Void>() {

                @Override
                public void onSuccess(final Long pLong) {
                    onBackClicked(null);
                }

                @Override
                public void onError(final Exception pE) {
                    Toast.makeText(CreateDiscountActivity.this, R.string.cannot_insert_card_to_database,
                            Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProgressChanged(final Void pVoid) {
                }
            });
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
        }
    }

    public void onToolbarBackClicked(final View view) {
    }

    public void onCancelClicked(final View view) {
        onBackClicked(null);
    }

    public void onPrevColorPagerClicked(final View view) {
        pager.setCurrentItem(pager.getCurrentItem() - 1);
    }

    public void onNextColorPagerClicked(final View view) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }
}