package com.maxcriser.cards.ui.display_item;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.LoadImage;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;

import java.io.File;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BACK_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BANK;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_CARDHOLDER;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_FRONT_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_NUMBER;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_PIN;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_TYPE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_VALID;
import static com.maxcriser.cards.constant.Extras.EXTRA_VERIFICATION_NUMBER_BANK;

public class BankCardActivity extends Activity {

    private FloatingActionMenu materialDesignFAM;
    private FloatingActionButton floatingActionButtonDelete, floatingActionButtonEdit;
    private LinearLayout editLinear;
    private ScrollView mScrollView;
    private Boolean showPin = false;
    private TextView editBank;
    private EditText editName;
    private String editString;
    private LinearLayout linearFrameAction;
    private String id;
    private EditText editCardholder;
    private EditText editNumber;
    private EditText editPin;
    private EditText verificationNumber;
    private EditText editValid;
    private ImageView editType;
    private ImageView eye;
    private DatabaseHelperImpl dbHelper;
    private Handler mHandler;
    private Animation animScaleDown;
    private Animation animScaleUp;
    private ImageView ivFrontPhoto;
    private ImageView ivBackPhoto;

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

        dbHelper = DatabaseHelperImpl.getInstance(this);

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
        String bank = creditIntent.getStringExtra(EXTRA_BANK_BANK);
        String verNumber = creditIntent.getStringExtra(EXTRA_VERIFICATION_NUMBER_BANK);
        String cardholder = creditIntent.getStringExtra(EXTRA_BANK_CARDHOLDER);
        String number = creditIntent.getStringExtra(EXTRA_BANK_NUMBER);
        String pin = creditIntent.getStringExtra(EXTRA_BANK_PIN);
        String valid = creditIntent.getStringExtra(EXTRA_BANK_VALID);
        String type = creditIntent.getStringExtra(EXTRA_BANK_TYPE);
        String color = creditIntent.getStringExtra(EXTRA_BANK_COLOR);
        String firstPhoto = creditIntent.getStringExtra(EXTRA_BANK_FRONT_PHOTO);
        String secondPhoto = creditIntent.getStringExtra(EXTRA_BANK_BACK_PHOTO);

        Uri takenPhotoUriFirst = getPhotoFileUri(firstPhoto);
        if (takenPhotoUriFirst != null) {
            ivFrontPhoto.setImageURI(takenPhotoUriFirst);
        }
        Uri takenPhotoUriSecond = getPhotoFileUri(secondPhoto);
        if (takenPhotoUriSecond != null) {
            ivBackPhoto.setImageURI(takenPhotoUriSecond);
        }

        editBank.setText(bank);
        editCardholder.setText(cardholder);
        editNumber.setText(number);
        editPin.setText(pin);
        verificationNumber.setText(verNumber);
        editValid.setText(valid);
        Integer typeID;
        if (type.equals(Constants.CARDS.VISA)) {
            typeID = R.drawable.type_visa;
        } else if (type.equals(Constants.CARDS.MASTERCARD)) {
            typeID = R.drawable.type_mastercard;
        } else if (type.equals(Constants.CARDS.AMEX)) {
            typeID = R.drawable.type_amex;
        } else if (type.equals(Constants.CARDS.MAESTRO)) {
            typeID = R.drawable.type_maestro;
        } else if (type.equals(Constants.CARDS.WESTERN_UNION)) {
            typeID = R.drawable.type_western_union;
        } else if (type.equals(Constants.CARDS.JCB)) {
            typeID = R.drawable.type_jcb;
        } else if (type.equals(Constants.CARDS.DINERS_CLUB)) {
            typeID = R.drawable.type_diners_club;
        } else {
            typeID = R.drawable.type_belcard;
        }
        editType.setBackgroundResource(typeID);
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public Uri getPhotoFileUri(String fileName) {
        if (isExternalStorageAvailable()) {
            File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), Constants.APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(Constants.APP_TAG, getString(R.string.filed_to_create_directory));
            }
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    private void initViews() {
        ivFrontPhoto = (ImageView) findViewById(R.id.front_photo);
        ivBackPhoto = (ImageView) findViewById(R.id.back_photo);
        verificationNumber = (EditText) findViewById(R.id.ver_number);
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
        if (!editString.equals(Constants.EMPTY_STRING)) {
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