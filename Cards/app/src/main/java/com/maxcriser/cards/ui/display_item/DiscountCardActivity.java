package com.maxcriser.cards.ui.display_item;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.view.text_view.BarcodeEan;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_BARCODE;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_TITLE;

public class DiscountCardActivity extends Activity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButtonDelete, floatingActionButtonEdit;
    LinearLayout editLinear;
    ScrollView mScrollView;

    TextView titleView;
    EditText editName;
    String editString;
    BarcodeEan barcodeView;
    LinearLayout linearFrameAction;
    String id;
    String title;
    String barcode;
    String color;
    DatabaseHelperImpl dbHelper;
    Handler mHandler;
    Animation animScaleDown;
    Animation animScaleUp;

    Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            materialDesignFAM.startAnimation(animScaleDown);
            materialDesignFAM.setVisibility(GONE);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_discount);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
        mHandler = new Handler(hc);

        animScaleDown = AnimationUtils.loadAnimation(DiscountCardActivity.this, R.anim.scale_down);
        animScaleUp = AnimationUtils.loadAnimation(DiscountCardActivity.this, R.anim.scale_up);
        registerForContextMenu(materialDesignFAM);
        dbHelper = DatabaseHelperImpl.getInstance(this);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbHelper.delete(ModelDiscountCards.class, null, ModelDiscountCards.ID + " = ?", String.valueOf(id));
                onBackClicked(null);

            }
        });
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                editLinear.setVisibility(VISIBLE);
                titleView.setVisibility(GONE);
                linearFrameAction.setVisibility(VISIBLE);
                editString = titleView.getText().toString();
                editName.setText(editString);
                materialDesignFAM.close(true);
                mHandler.sendEmptyMessageDelayed(1, 300);
            }
        });

        Intent barcodeIntent = getIntent();
        id = barcodeIntent.getStringExtra(EXTRA_DISCOUNT_ID);
        title = barcodeIntent.getStringExtra(EXTRA_DISCOUNT_TITLE);
        barcode = barcodeIntent.getStringExtra(EXTRA_DISCOUNT_BARCODE);
        color = barcodeIntent.getStringExtra(EXTRA_DISCOUNT_COLOR);

        titleView.setText(title);

        barcodeView.setText(barcode);

        WindowManager.LayoutParams layoutParam = getWindow().getAttributes();
        layoutParam.screenBrightness = 1.0f;
        getWindow().setAttributes(layoutParam);
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        titleView = (TextView) findViewById(R.id.title_show_discount);
        barcodeView = (BarcodeEan) findViewById(R.id.show_barcode);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
    }

    public void onBackClicked(View view) {
        // TODO: 07.12.2016   handler close materialFloating
        super.onBackPressed();
        }

    public void onCancelClicked(View view) {
        editLinear.setVisibility(GONE);
        titleView.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);
    }

    public void onCreateCardClicked(View view) {
        editString = editName.getText().toString();
        if (!editString.equals(Constants.EMPTY_STRING)) {
            titleView.setText(editString);
            editLinear.setVisibility(GONE);
            titleView.setVisibility(VISIBLE);
            linearFrameAction.setVisibility(GONE);
            materialDesignFAM.setVisibility(VISIBLE);
            materialDesignFAM.startAnimation(animScaleUp);

            dbHelper.edit(ModelDiscountCards.class,
                    ModelDiscountCards.TITLE,
                    editString,
                    ModelDiscountCards.ID,
                    String.valueOf(id),
                    new OnResultCallback<Void, Void>() {
                        @Override
                        public void onSuccess(Void pVoid) {

                        }

                        @Override
                        public void onError(Exception pE) {

                        }

                        @Override
                        public void onProgressChanged(Void pVoid) {

                        }
                    });
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, R.string.empty_card_name, Toast.LENGTH_LONG).show();
        }
    }

    public void onToolbarBackClicked(View view) {
    }
}