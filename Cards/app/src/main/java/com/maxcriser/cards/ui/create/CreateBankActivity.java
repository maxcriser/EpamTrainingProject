package com.maxcriser.cards.ui.create;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;
import com.maxcriser.cards.CoreApplication;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.RemovePhoto;
import com.maxcriser.cards.async.task.ScanCreditCard;
import com.maxcriser.cards.constant.Extras;
import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.constant.ListPreview;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.date.DateToView;
import com.maxcriser.cards.dialog.MatchesFountDialogBuilder;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.listener.OnTypePageChangeListener;
import com.maxcriser.cards.model.CreditCard;
import com.maxcriser.cards.ui.activities.PhotoEditorActivity;
import com.maxcriser.cards.utils.UniqueStringGenerator;
import com.maxcriser.cards.view.labels.RobotoRegular;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Calendar;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.ListConstants.Requests.CAPTURE_IMAGE_BACK;
import static com.maxcriser.cards.constant.ListConstants.Requests.CAPTURE_IMAGE_FRONT;
import static com.maxcriser.cards.constant.ListConstants.Requests.EDIT_IMAGE_BACK;
import static com.maxcriser.cards.constant.ListConstants.Requests.EDIT_IMAGE_FRONT;
import static com.maxcriser.cards.constant.ListConstants.Requests.REQUEST_BACK_CAMERA;
import static com.maxcriser.cards.constant.ListConstants.Requests.REQUEST_FRONT_CAMERA;
import static com.maxcriser.cards.constant.ListConstants.Requests.REQUEST_WRITE_STORAGE_BACK;
import static com.maxcriser.cards.constant.ListConstants.Requests.REQUEST_WRITE_STORAGE_FRONT;
import static com.maxcriser.cards.manager.StorageManager.isExternalStorageAvailable;

public class CreateBankActivity extends AppCompatActivity {

    public String photoFileNameFront;
    public String photoFileNameBack;
    private OwnAsyncTask sync;
    private ImageView frontPhoto;
    private ImageView backPhoto;
    private FrameLayout removeFront;
    private FrameLayout removeBack;
    private DatabaseHelper db;
    private ScrollView mScrollView;
    private ViewPager pagerTypes;
    private final Calendar calendar = Calendar.getInstance();
    private EditText bank;
    private EditText cardholder;
    private MaskedEditText number;
    private EditText pin;
    private TextView validDate;
    private EditText verificationNumber;
    private String myTypeCard;
    private Uri editFrontUri;
    private Uri editBackUri;
    private boolean statusScan;
    private boolean statusSave;
    private TextInputLayout numberLayout, bankLayout, cardholderLayout, vernumberLayout, pinLayout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        sync = new OwnAsyncTask();
        initViews();
    }

    private void initViews() {
        numberLayout = (TextInputLayout) findViewById(R.id.layout_number);
        bankLayout = (TextInputLayout) findViewById(R.id.layout_bank);
        cardholderLayout = (TextInputLayout) findViewById(R.id.layout_cardholder);
        vernumberLayout = (TextInputLayout) findViewById(R.id.layout_ver);
        pinLayout = (TextInputLayout) findViewById(R.id.layout_pin);
        final String uniqueString = UniqueStringGenerator.getUniqueString();
        photoFileNameFront = ListConstants.BEG_FILE_NAME_BANK + uniqueString + "front_photo.jpg";
        photoFileNameBack = ListConstants.BEG_FILE_NAME_BANK + uniqueString + "back_photo.jpg";
        verificationNumber = (EditText) findViewById(R.id.ver_number);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        bank = (EditText) findViewById(R.id.bank);
        cardholder = (EditText) findViewById(R.id.cardholder);
        number = (MaskedEditText) findViewById(R.id.number);
        pin = (EditText) findViewById(R.id.pin);
        validDate = (TextView) findViewById(R.id.date);
        pagerTypes = (ViewPager) findViewById(R.id.type_card);
        frontPhoto = (ImageView) findViewById(R.id.front_photo);
        backPhoto = (ImageView) findViewById(R.id.back_photo);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
        statusScan = true;
        db = ((CoreApplication) getApplication()).getDatabaseHelper(this);
        DateToView.setDateToCreditView(validDate, calendar);
        final RobotoRegular title = (RobotoRegular) findViewById(R.id.title_toolbar);
        title.setText(getResources().getString(R.string.bank_title));

        myTypeCard = ListPreview.types.get(0);
        final int pageCount = ListPreview.types.size();
        final PagerAdapter pagerAdapterTypes = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ListConstants.PagerIDs.ID_BANK_CARD_ITEM_TYPE,
                pageCount);
        pagerTypes.setAdapter(pagerAdapterTypes);
        pagerTypes.addOnPageChangeListener(new OnTypePageChangeListener(new OnTypePageChangeListener.OnPageChangeListener() {

            @Override
            public void onResult(final int position, final Integer icon, final String type) {
                myTypeCard = type;
            }
        }));
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (requestCode == CAPTURE_IMAGE_FRONT ||
                requestCode == CAPTURE_IMAGE_BACK) {
            if (resultCode == RESULT_OK) {
                if (requestCode == CAPTURE_IMAGE_FRONT) {
                    final Uri takenPhotoUri = getUri(photoFileNameFront);
                    final Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(Extras.EXTRA_URI, takenPhotoUri.toString());
                    intent.putExtra(ListConstants.STATUS_PHOTOEDITOR, ListConstants.STATUS_PHOTOEEDITOR_CREDIT_CARD);
                    startActivityForResult(intent, EDIT_IMAGE_FRONT);
                } else {
                    final Uri takenPhotoUri = getUri(photoFileNameBack);
                    final Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(ListConstants.STATUS_PHOTOEDITOR, ListConstants.STATUS_PHOTOEEDITOR_CREDIT_CARD);
                    intent.putExtra(Extras.EXTRA_URI, takenPhotoUri.toString());
                    startActivityForResult(intent, EDIT_IMAGE_BACK);
                }
            } else {
                Toast.makeText(this, R.string.picture_wasnt_taken, Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == EDIT_IMAGE_FRONT) {
                editFrontUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
                Picasso.with(this).load(editFrontUri).placeholder(R.drawable.background_placeholder_500_316).into(frontPhoto);
                /*
                Picasso.with(this).load(editFrontUri).into(new Target() {

                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
                        frontPhoto.setImageBitmap(bitmap);

                    }

                    @Override
                    public void onBitmapFailed(final Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable) {

                    }
                });
                */
                removeFront.setVisibility(View.VISIBLE);
                frontPhoto.setClickable(false);
                sync.execute(new ScanCreditCard(getAssets(), this), editFrontUri, new OnResultCallback<CreditCard, String>() {

                    @Override
                    public void onSuccess(final CreditCard pCredit) {
                        try {
                            if (pCredit != null && statusScan) {
                                final String creditNumber = pCredit.getNumberCreditCard();
                                final String creditCardholder = pCredit.getCardholderCreditCard();
                                final String creditName = pCredit.getNameCreditCard();
                                final String creditType = pCredit.getTypeCreditCard();
                                final String creditValid = pCredit.getValidCreditCard();

                                if (!creditNumber.isEmpty() || !creditCardholder.isEmpty() || !creditName.isEmpty() || !creditType.isEmpty() || !creditValid.isEmpty()) {
                                    showAlertDialogRecognize(creditNumber, creditCardholder, creditName,
                                            creditType, creditValid);
                                } else {
                                    Toast.makeText(CreateBankActivity.this, R.string.not_fount_matches, Toast.LENGTH_LONG).show();
                                }
                            }
                        } catch (final Exception e) {
                            Toast.makeText(CreateBankActivity.this, R.string.error_to_past_matches, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(final Exception pE) {
                        Toast.makeText(CreateBankActivity.this, R.string.cannot_scanning_card, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onProgressChanged(final String pS) {

                    }
                });
            } else if (requestCode == EDIT_IMAGE_BACK) {
                editBackUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
                Picasso.with(this).load(editBackUri).placeholder(R.drawable.background_placeholder_500_316).into(backPhoto);
                /*
                Picasso.with(this).load(editBackUri).into(new Target() {

                    @Override
                    public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
                        backPhoto.setImageBitmap(bitmap);
                        removeBack.setVisibility(View.VISIBLE);
                        backPhoto.setClickable(false);
                    }

                    @Override
                    public void onBitmapFailed(final Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(final Drawable placeHolderDrawable) {

                    }
                });
                */
                removeBack.setVisibility(View.VISIBLE);
                backPhoto.setClickable(false);
            }
        } else {
            Toast.makeText(this, R.string.picture_wasnt_edited, Toast.LENGTH_SHORT).show();
        }
    }

    private void showAlertDialogRecognize(final String creditNumber, final String creditCardholder,
                                          final String creditName, final String creditType, final String creditValid) {
        String message = "";
        if (!creditName.isEmpty()) {
            message += "Name of the bank: " + creditName + "\n";
        }
        if (!creditCardholder.isEmpty()) {
            message += "Cardholder: " + creditCardholder + "\n";
        }
        if (!creditType.isEmpty()) {
            message += "Card type: " + creditType + "\n";
        }
        if (!creditNumber.isEmpty()) {
            message += "Card number: " + creditNumber + "\n";
        }
        if (!creditValid.isEmpty()) {
            message += "Valid through: " + creditValid + "\n\n";
        }
        final MatchesFountDialogBuilder dialogMatches = new MatchesFountDialogBuilder(this, message,
                creditNumber, creditCardholder, creditName, creditType, creditValid);
        dialogMatches.startDialog();
    }

    public void pasteRecognizeTextToViews(final String creditNumber, final String creditCardholder,
                                          final String creditName, final String creditType, final String creditValid) {
        if (!creditNumber.isEmpty()) {
            number.setText(creditNumber);
        }
        if (!creditCardholder.isEmpty()) {
            cardholder.setText(creditCardholder);
        }
        if (!creditName.isEmpty()) {
            bank.setText(creditName);
        }
        if (!creditValid.isEmpty()) {
            validDate.setText(creditValid);
        }
        if (!creditType.isEmpty()) {
            final int numberType;
            switch (creditType) {
                case ListConstants.Cards.VISA:
                    numberType = ListConstants.PagerTypesID.VISA;
                    break;
                case ListConstants.Cards.MASTERCARD:
                    numberType = ListConstants.PagerTypesID.MASTERCAD;
                    break;
                case ListConstants.Cards.AMEX:
                    numberType = ListConstants.PagerTypesID.AMEX;
                    break;
                case ListConstants.Cards.MAESTRO:
                    numberType = ListConstants.PagerTypesID.MAESTRO;
                    break;
                case ListConstants.Cards.WESTERN_UNION:
                    numberType = ListConstants.PagerTypesID.WESTERN_UNION;
                    break;
                case ListConstants.Cards.JCB:
                    numberType = ListConstants.PagerTypesID.JCB;
                    break;
                case ListConstants.Cards.DINERS_CLUB:
                    numberType = ListConstants.PagerTypesID.DINERS_CLUB;
                    break;
                default:
                    numberType = ListConstants.PagerTypesID.BELCARD;
                    break;
            }
            pagerTypes.setCurrentItem(numberType);
        }
    }

    public Uri getUri(final String fileName) {
        if (isExternalStorageAvailable()) {
            final File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), ListConstants.APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Toast.makeText(this, ListConstants.APP_TAG + getString(R.string.filed_to_create_directory),
                        Toast.LENGTH_LONG).show();
            }
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    public void onBackPhotoClicked(final View view) {
        getPermission(REQUEST_BACK_CAMERA, Manifest.permission.CAMERA);
    }

    public void onFrontPhotoClicked(final View view) {
        getPermission(REQUEST_FRONT_CAMERA, Manifest.permission.CAMERA);
    }

    @TargetApi(23)
    private void getPermission(final byte CODE, final String PERMISSION) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
        } else {
            if (CODE == REQUEST_FRONT_CAMERA) {
                getPermission(REQUEST_WRITE_STORAGE_FRONT, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else if (CODE == REQUEST_BACK_CAMERA) {
                getPermission(REQUEST_WRITE_STORAGE_BACK, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            } else if (CODE == REQUEST_WRITE_STORAGE_FRONT) {
                startCameraForPhoto(CAPTURE_IMAGE_FRONT, photoFileNameFront);
            } else if (CODE == REQUEST_WRITE_STORAGE_BACK) {
                startCameraForPhoto(CAPTURE_IMAGE_BACK, photoFileNameBack);
            }
        }
    }

    private void startCameraForPhoto(final int code, final String fileName) {
        final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUri(fileName));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, code);
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.permission_has_not_been_granted, Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_FRONT_CAMERA) {
            getPermission(REQUEST_WRITE_STORAGE_FRONT, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else if (requestCode == REQUEST_BACK_CAMERA) {
            getPermission(REQUEST_WRITE_STORAGE_BACK, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else if (requestCode == REQUEST_WRITE_STORAGE_FRONT) {
            startCameraForPhoto(CAPTURE_IMAGE_FRONT, photoFileNameFront);
        } else if (requestCode == REQUEST_WRITE_STORAGE_BACK) {
            startCameraForPhoto(CAPTURE_IMAGE_BACK, photoFileNameBack);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onRemoveBackClicked(final View view) {
        backPhoto.setImageResource(R.drawable.camera_card_size);
        backPhoto.setClickable(true);
        removeBack.setVisibility(GONE);
    }

    public void onRemoveFrontClicked(final View view) {
        frontPhoto.setImageResource(R.drawable.camera_card_size);
        frontPhoto.setClickable(true);
        removeFront.setVisibility(GONE);
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    private boolean validateField(final EditText view, final TextInputLayout viewLayout) {
        if (view.getText().toString().isEmpty()) {
            viewLayout.setError("You must fill this field");
            requestFocus(view);
            return false;
        } else {
            viewLayout.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(final View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void createCard() {
        final String bankStr = bank.getText().toString();
        final String cardholderStr = cardholder.getText().toString();
        final String numberStr = number.getText().toString();
        final String pinStr = pin.getText().toString();
        final String validThru = validDate.getText().toString();
        final String type = myTypeCard;
        final String verNumber = verificationNumber.getText().toString();
        if (!validateField(pin, pinLayout) | !validateField(verificationNumber, vernumberLayout) | !validateField(cardholder, cardholderLayout) | !validateField(bank, bankLayout) | !validateField(number, numberLayout) || validThru.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
//            mScrollView.fullScroll(ScrollView.FOCUS_UP);
        } else if (removeFront.getVisibility() == View.GONE || removeBack.getVisibility() == View.GONE) {
            Toast.makeText(this, R.string.must_make_card_images, Toast.LENGTH_LONG).show();
        } else {
            final ContentValues cvNewCredit = new ContentValues();
            cvNewCredit.put(ModelBankCards.TITLE, bankStr);
            cvNewCredit.put(ModelBankCards.PHOTO_FRONT, editFrontUri.toString());
            cvNewCredit.put(ModelBankCards.PHOTO_BACK, editBackUri.toString());
            cvNewCredit.put(ModelBankCards.VERIFICATION_NUMBER, verNumber);
            cvNewCredit.put(ModelBankCards.CARDHOLDER, cardholderStr);
            cvNewCredit.put(ModelBankCards.NUMBER, numberStr);
            cvNewCredit.put(ModelBankCards.PIN, pinStr);
            cvNewCredit.put(ModelBankCards.VALID, validThru);
            cvNewCredit.put(ModelBankCards.TYPE, type);
            cvNewCredit.put(ModelBankCards.ID, (Integer) null);

            db.insert(ModelBankCards.class, cvNewCredit, new OnResultCallback<Long, Void>() {

                @Override
                public void onSuccess(final Long pLong) {
                    statusSave = true;
                    onBackClicked(null);
                }

                @Override
                public void onError(final Exception pE) {
                }

                @Override
                public void onProgressChanged(final Void pVoid) {
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        if (!statusSave) {
            if (editBackUri != null) {
                sync.execute(new RemovePhoto(), editBackUri, null);
            }
            if (editFrontUri != null) {
                sync.execute(new RemovePhoto(), editFrontUri, null);
            }
        }
        statusScan = false;
        super.onDestroy();
    }

    public void onCreateCardClicked(final View view) {
        createCard();
    }

    public void onToolbarBackClicked(final View view) {
    }

    DatePickerDialog.OnDateSetListener dateCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            DateToView.setDateToCreditView(validDate, calendar);
        }
    };

    public void onDateClicked(final View view) {
        final DatePickerDialog tpd = new DatePickerDialog(this, dateCallBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        tpd.show();
    }

    public void onCancelClicked(final View view) {
        onBackClicked(null);
    }

    public void onPrevTypePagerClicked(final View view) {
        pagerTypes.setCurrentItem(pagerTypes.getCurrentItem() - 1);
    }

    public void onNextTypePagerClicked(final View view) {
        pagerTypes.setCurrentItem(pagerTypes.getCurrentItem() + 1);
    }
}