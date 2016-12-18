package com.maxcriser.cards.ui.display_item;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
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
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.RemovePhoto;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.ui.LaunchScreenActivity;
import com.maxcriser.cards.util.AlertImageViewer;

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
    private LinearLayout editLinear;
    private ScrollView mScrollView;
    private Boolean showPin = false;
    private OwnAsyncTask sync;
    private TextView editBank;
    private EditText editName;
    private String editString;
    private LinearLayout linearFrameAction;
    private String id;
    private EditText editPin;
    private ImageView eye;
    private DatabaseHelperImpl dbHelper;
    private Handler mHandler;
    private Animation animScaleDown;
    private Animation animScaleUp;
    private Bitmap firstBitmap;
    private Bitmap secondBitmap;

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
        sync = new OwnAsyncTask();
        initViews();
    }

    private void initViews() {
        final ImageView ivFrontPhoto = (ImageView) findViewById(R.id.front_photo);
        final ImageView ivBackPhoto = (ImageView) findViewById(R.id.back_photo);
        EditText verificationNumber = (EditText) findViewById(R.id.ver_number);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        eye = (ImageView) findViewById(R.id.eye);
        editBank = (TextView) findViewById(R.id.title_show_discount);
        EditText editCardholder = (EditText) findViewById(R.id.cardholder);
        EditText editNumber = (EditText) findViewById(R.id.number);
        editPin = (EditText) findViewById(R.id.pin);
        EditText editValid = (EditText) findViewById(R.id.valid);
        ImageView editType = (ImageView) findViewById(R.id.type_card);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        FloatingActionButton floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
        mHandler = new Handler(hc);

        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        registerForContextMenu(materialDesignFAM);

        dbHelper = DatabaseHelperImpl.getInstance(this);

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
        final String firstPhoto = creditIntent.getStringExtra(EXTRA_BANK_FRONT_PHOTO);
        final String secondPhoto = creditIntent.getStringExtra(EXTRA_BANK_BACK_PHOTO);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbHelper.delete(ModelBankCards.class, null, ModelBankCards.ID + " = ?", String.valueOf(id));
                sync.execute(new RemovePhoto(), Uri.parse(firstPhoto), null);
                sync.execute(new RemovePhoto(), Uri.parse(secondPhoto), null);
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

        ImageLoader.getInstance().downloadAndDraw(firstPhoto, ivFrontPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(Bitmap pBitmap) {
                if (pBitmap != null)
                    firstBitmap = pBitmap;
            }

            @Override
            public void onError(Exception pE) {

            }

            @Override
            public void onProgressChanged(Void pVoid) {

            }
        });

        ImageLoader.getInstance().downloadAndDraw(secondPhoto, ivBackPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(Bitmap pBitmap) {
                if (pBitmap != null)
                    secondBitmap = pBitmap;
            }

            @Override
            public void onError(Exception pE) {

            }

            @Override
            public void onProgressChanged(Void pVoid) {

            }
        });

        editBank.setText(bank);
        editCardholder.setText(cardholder);
        editNumber.setText(number);
        editPin.setText(pin);
        verificationNumber.setText(verNumber);
        editValid.setText(valid);
        Integer typeID;
        if (type.equals(Constants.Cards.VISA)) {
            typeID = R.drawable.type_visa;
        } else if (type.equals(Constants.Cards.MASTERCARD)) {
            typeID = R.drawable.type_mastercard;
        } else if (type.equals(Constants.Cards.AMEX)) {
            typeID = R.drawable.type_amex;
        } else if (type.equals(Constants.Cards.MAESTRO)) {
            typeID = R.drawable.type_maestro;
        } else if (type.equals(Constants.Cards.WESTERN_UNION)) {
            typeID = R.drawable.type_western_union;
        } else if (type.equals(Constants.Cards.JCB)) {
            typeID = R.drawable.type_jcb;
        } else if (type.equals(Constants.Cards.DINERS_CLUB)) {
            typeID = R.drawable.type_diners_club;
        } else {
            typeID = R.drawable.type_belcard;
        }
        editType.setBackgroundResource(typeID);
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
        if (!editString.isEmpty()) {
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

    void showPhoto(final Bitmap bitmap) {
        AlertImageViewer dialog = new AlertImageViewer(BankCardActivity.this, bitmap,
                LaunchScreenActivity.SCREEN_WIDTH, LaunchScreenActivity.SCREEN_HEIGHT);
        dialog.startDialog();
    }

    public void onSecondPhotoClicked(View view) {
        if (secondBitmap != null)
            showPhoto(secondBitmap);
    }

    public void onFirstPhotoClicked(View view) {
        if (firstBitmap != null)
            showPhoto(firstBitmap);
    }

    // TODO copy tessdata to external storage for recognize
    // TODO cannot edit name card which has photos ;/
}