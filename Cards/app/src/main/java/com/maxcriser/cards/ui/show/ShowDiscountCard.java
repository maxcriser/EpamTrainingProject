package com.maxcriser.cards.ui.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.ui.cards.DiscountCardsActivity;
import com.maxcriser.cards.view.EditTextViews.RobotoThinEditText;
import com.maxcriser.cards.view.TextViews.EANP72TextView;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;
import com.maxcriser.cards.view.TextViews.RobotoThinTextView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ShowDiscountCard extends Activity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButtonDelete, floatingActionButtonEdit;
    LinearLayout editLinear;

    RobotoThinTextView titleView;
    RobotoThinEditText editName;
    String editString;
    EANP72TextView barcodeView;
    LinearLayout linearFrameTitle;
    LinearLayout linearFrameAction;
    String id;
    String title;
    String barcode;
    String color;
    DatabaseHelper dbHelper;
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

        mHandler = new Handler(hc);

        animScaleDown = AnimationUtils.loadAnimation(ShowDiscountCard.this, R.anim.scale_down);
        animScaleUp = AnimationUtils.loadAnimation(ShowDiscountCard.this, R.anim.scale_up);

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        registerForContextMenu(materialDesignFAM);

        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (RobotoThinEditText) findViewById(R.id.rename_discount_title);
        // titleView
        RobotoRegularTextView titleToolbar = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        titleToolbar.setText(title);

        dbHelper = DatabaseHelper.getInstance(this, 1);

        floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbHelper.delete(ModelDiscountCards.class, null, ModelDiscountCards.DISCOUNT_ID + " = ?", String.valueOf(id));
                onBackClicked(null);

            }
        });
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

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
        id = barcodeIntent.getStringExtra(DiscountCardsActivity.EXTRA_DISCOUNT_ID);
        title = barcodeIntent.getStringExtra(DiscountCardsActivity.EXTRA_DISCOUNT_TITLE);
        barcode = barcodeIntent.getStringExtra(DiscountCardsActivity.EXTRA_DISCOUNT_BARCODE);
        color = barcodeIntent.getStringExtra(DiscountCardsActivity.EXTRA_DISCOUNT_COLOR);

        titleView = (RobotoThinTextView) findViewById(R.id.title_show_discount);
        titleView.setText(title);

        barcodeView = (EANP72TextView) findViewById(R.id.show_barcode);
        barcodeView.setText(barcode);

        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
//        linearFrameAction.setBackgroundColor(Color.parseColor(color));
//        linearFrameTitle = (LinearLayout) findViewById(R.id.linear_frame_title_discount);
//        linearFrameTitle.setBackgroundColor(Color.parseColor(color));

        WindowManager.LayoutParams layoutParam = getWindow().getAttributes();
        layoutParam.screenBrightness = 1.0f;
        getWindow().setAttributes(layoutParam);
    }

    public void onBackClicked(View view) {
        // handler close materialFloating
        super.onBackPressed();
    }

    public void onCancelDiscountClicked(View view) {
        // отмена изменений titleView
        editLinear.setVisibility(GONE);
        titleView.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);
    }

    public void onOkDiscountClicked(View view) {
        editString = editName.getText().toString();
        titleView.setText(editString);
        editLinear.setVisibility(GONE);
        titleView.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);

        dbHelper.edit(ModelDiscountCards.class,
                ModelDiscountCards.DISCOUNT_TITLE,
                editString,
                ModelDiscountCards.DISCOUNT_ID,
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

    }
}