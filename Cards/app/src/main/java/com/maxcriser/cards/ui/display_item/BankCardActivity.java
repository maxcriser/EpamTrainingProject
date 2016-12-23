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
import com.maxcriser.cards.constant.constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.ui.LaunchScreenActivity;
import com.maxcriser.cards.utils.ImageViewerDialogBuilder;

import static android.text.InputType.TYPE_CLASS_TEXT;
import static android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BACK_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BANK;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_CARDHOLDER;
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
        public boolean handleMessage(final Message msg) {
            materialDesignFAM.startAnimation(animScaleDown);
            materialDesignFAM.setVisibility(GONE);
            return false;
        }
    };

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_bank);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        sync = new OwnAsyncTask();
        initViews();
    }

    private void initViews() {
        final ImageView ivFrontPhoto = (ImageView) findViewById(R.id.front_photo);
        final ImageView ivBackPhoto = (ImageView) findViewById(R.id.back_photo);
        final EditText verificationNumber = (EditText) findViewById(R.id.ver_number);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        eye = (ImageView) findViewById(R.id.eye);
        editBank = (TextView) findViewById(R.id.title_show_discount);
        final EditText editCardholder = (EditText) findViewById(R.id.cardholder);
        final EditText editNumber = (EditText) findViewById(R.id.number);
        editPin = (EditText) findViewById(R.id.pin);
        final EditText editValid = (EditText) findViewById(R.id.valid);
        final ImageView editType = (ImageView) findViewById(R.id.type_card);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        final FloatingActionButton floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        final FloatingActionButton floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
        mHandler = new Handler(hc);

        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down_floating);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up_floating);

        registerForContextMenu(materialDesignFAM);

        dbHelper = DatabaseHelperImpl.getInstance(this);

        final Intent creditIntent = getIntent();
        id = creditIntent.getStringExtra(EXTRA_BANK_ID);
        final String bank = creditIntent.getStringExtra(EXTRA_BANK_BANK);
        final String verNumber = creditIntent.getStringExtra(EXTRA_VERIFICATION_NUMBER_BANK);
        final String cardholder = creditIntent.getStringExtra(EXTRA_BANK_CARDHOLDER);
        final String number = creditIntent.getStringExtra(EXTRA_BANK_NUMBER);
        final String pin = creditIntent.getStringExtra(EXTRA_BANK_PIN);
        final String valid = creditIntent.getStringExtra(EXTRA_BANK_VALID);
        final String type = creditIntent.getStringExtra(EXTRA_BANK_TYPE);
        final String firstPhoto = creditIntent.getStringExtra(EXTRA_BANK_FRONT_PHOTO);
        final String secondPhoto = creditIntent.getStringExtra(EXTRA_BANK_BACK_PHOTO);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                dbHelper.delete(ModelBankCards.class, null, ModelBankCards.ID + " = ?", String.valueOf(id));
                sync.execute(new RemovePhoto(), Uri.parse(firstPhoto), null);
                sync.execute(new RemovePhoto(), Uri.parse(secondPhoto), null);
                onBackClicked(null);

            }
        });
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
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

        ImageLoader.getInstance().downloadToView(firstPhoto, ivFrontPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(final Bitmap pBitmap) {
                if (pBitmap != null) {
                    firstBitmap = pBitmap;
                }
            }

            @Override
            public void onError(final Exception pE) {

            }

            @Override
            public void onProgressChanged(final Void pVoid) {

            }
        });

        ImageLoader.getInstance().downloadToView(secondPhoto, ivBackPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(final Bitmap pBitmap) {
                if (pBitmap != null) {
                    secondBitmap = pBitmap;
                }
            }

            @Override
            public void onError(final Exception pE) {

            }

            @Override
            public void onProgressChanged(final Void pVoid) {

            }
        });

        editBank.setText(bank);
        editCardholder.setText(cardholder);
        editNumber.setText(number);
        editPin.setText(pin);
        verificationNumber.setText(verNumber);
        editValid.setText(valid);
        final Integer typeID;
        switch (type) {
            case constants.Cards.VISA:
                typeID = R.drawable.type_visa;
                break;
            case constants.Cards.MASTERCARD:
                typeID = R.drawable.type_mastercard;
                break;
            case constants.Cards.AMEX:
                typeID = R.drawable.type_amex;
                break;
            case constants.Cards.MAESTRO:
                typeID = R.drawable.type_maestro;
                break;
            case constants.Cards.WESTERN_UNION:
                typeID = R.drawable.type_western_union;
                break;
            case constants.Cards.JCB:
                typeID = R.drawable.type_jcb;
                break;
            case constants.Cards.DINERS_CLUB:
                typeID = R.drawable.type_diners_club;
                break;
            default:
                typeID = R.drawable.type_belcard;
                break;
        }
        editType.setBackgroundResource(typeID);
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    public void onCancelClicked(final View view) {
        editLinear.setVisibility(GONE);
        editBank.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);
    }

    public void onCreateCardClicked(final View view) {
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
                        public void onSuccess(final Void pVoid) {

                        }

                        @Override
                        public void onError(final Exception pE) {

                        }

                        @Override
                        public void onProgressChanged(final Void pVoid) {

                        }
                    });
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, R.string.empty_card_name, Toast.LENGTH_LONG).show();
        }
    }

    public void onToolbarBackClicked(final View view) {
    }

    public void onShowPinClicked(final View view) {
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
        final ImageViewerDialogBuilder dialog = new ImageViewerDialogBuilder(this, bitmap,
                LaunchScreenActivity.SCREEN_WIDTH, LaunchScreenActivity.SCREEN_HEIGHT);
        dialog.startDialog();
    }

    public void onSecondPhotoClicked(final View view) {
        if (secondBitmap != null) {
            showPhoto(secondBitmap);
        }
    }

    public void onFirstPhotoClicked(final View view) {
        if (firstBitmap != null) {
            showPhoto(firstBitmap);
        }
    }
}