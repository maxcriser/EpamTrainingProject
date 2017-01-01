package com.maxcriser.cards.ui.display;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.maxcriser.cards.CoreApplication;
import com.maxcriser.cards.R;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.view.labels.BarcodeEan;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_BARCODE;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_TITLE;

public class DiscountCardActivity extends Activity {

    private final int DELAY_MILLIS = 300;
    private FloatingActionMenu materialDesignFAM;
    private LinearLayout editLinear;
    private ScrollView mScrollView;
    private TextView titleView;
    private EditText editName;
    private String editString;
    private LinearLayout linearFrameAction;
    private String id;
    private DatabaseHelper dbHelper;
    private Animation animScaleDown;
    private Animation animScaleUp;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_discount);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        final FloatingActionButton floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        final FloatingActionButton floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        titleView = (TextView) findViewById(R.id.title_show_discount);
        final BarcodeEan barcodeView = (BarcodeEan) findViewById(R.id.show_barcode);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down_floating);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up_floating);
        registerForContextMenu(materialDesignFAM);
        dbHelper = ((CoreApplication) getApplication()).getDatabaseHelper(this);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {
                dbHelper.delete(ModelDiscountCards.class, null, ModelDiscountCards.ID + " = ?", String.valueOf(id));
                onBackClicked(null);

            }
        });
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                editLinear.setVisibility(VISIBLE);
                titleView.setVisibility(GONE);
                linearFrameAction.setVisibility(VISIBLE);
                editString = titleView.getText().toString();
                editName.setText(editString);
                materialDesignFAM.close(true);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        materialDesignFAM.startAnimation(animScaleDown);
                        materialDesignFAM.setVisibility(GONE);
                    }
                }, DELAY_MILLIS);
            }
        });

        final Intent barcodeIntent = getIntent();
        id = barcodeIntent.getStringExtra(EXTRA_DISCOUNT_ID);
        final String title = barcodeIntent.getStringExtra(EXTRA_DISCOUNT_TITLE);
        final String barcode = barcodeIntent.getStringExtra(EXTRA_DISCOUNT_BARCODE);

        titleView.setText(title);

        barcodeView.setText(barcode);

        final WindowManager.LayoutParams layoutParam = getWindow().getAttributes();
        layoutParam.screenBrightness = 1.0f;
        getWindow().setAttributes(layoutParam);
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    public void onCancelClicked(final View view) {
        editLinear.setVisibility(GONE);
        titleView.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);
    }

    public void onCreateCardClicked(final View view) {
        editString = editName.getText().toString();
        if (!editString.isEmpty()) {
            titleView.setText(editString);
            editLinear.setVisibility(GONE);
            titleView.setVisibility(VISIBLE);
            linearFrameAction.setVisibility(GONE);
            materialDesignFAM.setVisibility(VISIBLE);
            materialDesignFAM.startAnimation(animScaleUp);

            dbHelper.edit(ModelDiscountCards.class, ModelDiscountCards.TITLE,
                    editString, ModelDiscountCards.ID, String.valueOf(id), null);
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, R.string.empty_card_name, Toast.LENGTH_LONG).show();
        }
    }

    public void onToolbarBackClicked(final View view) {
    }
}