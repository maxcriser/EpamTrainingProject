package com.maxcriser.cards.ui.show;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.constant.constants;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelBankCards;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BANK;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_CARDHOLDER;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_NUMBER;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_PIN;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_TYPE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_VALID;

public class ShowBankCard extends Activity {

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButtonDelete, floatingActionButtonEdit;
    LinearLayout editLinear;
    ScrollView mScrollView;
    Boolean showPin = false;
    TextView editBank;
    EditText editName;
    String editString;
    LinearLayout linearFrameAction;
    String id;
    String bank;
    String cardholder;
    String number;
    String pin;
    String valid;
    String type;
    String color;
    EditText editCardholder;
    EditText editNumber;
    EditText editPin;
    EditText editValid;
    ImageView editType;
    ImageView eye;

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
        setContentView(R.layout.activity_show_bank);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
        mHandler = new Handler(hc);

        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        registerForContextMenu(materialDesignFAM);

        dbHelper = DatabaseHelper.getInstance(this, 1);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbHelper.delete(ModelBankCards.class, null, ModelBankCards.ID + " = ?", String.valueOf(id));
                onBackClicked(null);

            }
        });
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                editLinear.setVisibility(VISIBLE);
                editBank.setVisibility(GONE);
                linearFrameAction.setVisibility(VISIBLE);
                editString = editBank.getText().toString();
                editName.setText(editString);
                materialDesignFAM.close(true);
                mHandler.sendEmptyMessageDelayed(1, 300);
            }
        });

        Intent creditIntent = getIntent();
        id = creditIntent.getStringExtra(EXTRA_BANK_ID);
        bank = creditIntent.getStringExtra(EXTRA_BANK_BANK);
        cardholder = creditIntent.getStringExtra(EXTRA_BANK_CARDHOLDER);
        number = creditIntent.getStringExtra(EXTRA_BANK_NUMBER);
        pin = creditIntent.getStringExtra(EXTRA_BANK_PIN);
        valid = creditIntent.getStringExtra(EXTRA_BANK_VALID);
        type = creditIntent.getStringExtra(EXTRA_BANK_TYPE);
        color = creditIntent.getStringExtra(EXTRA_BANK_COLOR);

        editBank.setText(bank);
        editCardholder.setText(cardholder);
        editNumber.setText(number);
        editPin.setText(pin);
        editValid.setText(valid);
        Integer typeID;
        if (type.equals(constants.VISA)) {
            typeID = R.drawable.type_visa;
        } else if (type.equals(constants.MASTERCARD)) {
            typeID = R.drawable.type_mastercard;
        } else if (type.equals(constants.AMEX)) {
            typeID = R.drawable.type_amex;
        } else if (type.equals(constants.MAESTRO)) {
            typeID = R.drawable.type_maestro;
        } else if (type.equals(constants.WESTERN_UNION)) {
            typeID = R.drawable.type_western_union;
        } else if (type.equals(constants.JCB)) {
            typeID = R.drawable.type_jcb;
        } else if (type.equals(constants.DINERS_CLUB)) {
            typeID = R.drawable.type_diners_club;
        } else {
            typeID = R.drawable.type_belcard;
        }
        editType.setBackgroundResource(typeID);
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        eye = (ImageView) findViewById(R.id.eye);
        editBank = (TextView) findViewById(R.id.title_show_discount);
        editCardholder = (EditText) findViewById(R.id.cardholder);
        editNumber = (EditText) findViewById(R.id.number);
        editPin = (EditText) findViewById(R.id.pin);
        editValid = (EditText) findViewById(R.id.valid);
        editType = (ImageView) findViewById(R.id.type_card);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
    }

    public void onBackClicked(View view) {
        // TODO: 07.12.2016   handler close materialFloating
        super.onBackPressed();
    }

    public void onCancelClicked(View view) {
        editLinear.setVisibility(GONE);
        editBank.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);
    }

    public void onCreateCardClicked(View view) {
        editString = editName.getText().toString();
        if (!editString.equals("")) {
            editBank.setText(editString);
            editLinear.setVisibility(GONE);
            editBank.setVisibility(VISIBLE);
            linearFrameAction.setVisibility(GONE);
            materialDesignFAM.setVisibility(VISIBLE);
            materialDesignFAM.startAnimation(animScaleUp);

            dbHelper.edit(ModelBankCards.class,
                    ModelBankCards.TITLE,
                    editString,
                    ModelBankCards.ID,
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

    public void onShowPinClicked(View view) {
        showPin = !showPin;
        if (showPin) {
            editPin.setInputType(TYPE_TEXT_VARIATION_PASSWORD);
            eye.setImageResource(R.drawable.eye_off);
        } else {
            editPin.setInputType(TYPE_CLASS_TEXT | TYPE_TEXT_VARIATION_PASSWORD);
            eye.setImageResource(R.drawable.eye_on);
        }
    }
}