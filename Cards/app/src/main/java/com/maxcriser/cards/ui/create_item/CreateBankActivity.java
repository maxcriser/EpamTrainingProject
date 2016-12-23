//TODO create activities
package com.maxcriser.cards.ui.create_item;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.RemovePhoto;
import com.maxcriser.cards.async.task.ScanCreditCard;
import com.maxcriser.cards.constant.Extras;
import com.maxcriser.cards.constant.constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.fragment.FragmentPreviewCards;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.model.CreditCard;
import com.maxcriser.cards.model.PreviewColor;
import com.maxcriser.cards.ui.activities.PhotoEditorActivity;
import com.maxcriser.cards.utils.OnTemplatePageChangeListener;
import com.maxcriser.cards.utils.OnTypePageChangeListener;
import com.maxcriser.cards.utils.UniqueStringGenerator;
import com.maxcriser.cards.view.labels.RobotoRegular;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.Requests.CAPTURE_IMAGE_BACK;
import static com.maxcriser.cards.constant.constants.Requests.CAPTURE_IMAGE_FRONT;
import static com.maxcriser.cards.constant.constants.Requests.EDIT_IMAGE_BACK;
import static com.maxcriser.cards.constant.constants.Requests.EDIT_IMAGE_FRONT;
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_BACK_CAMERA;
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_FRONT_CAMERA;
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_WRITE_STORAGE_BACK;
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_WRITE_STORAGE_FRONT;
import static com.maxcriser.cards.ui.activities.LaunchScreenActivity.previewColors;
import static com.maxcriser.cards.ui.activities.LaunchScreenActivity.previewTypes;
import static com.maxcriser.cards.utils.Storage.isExternalStorageAvailable;

public class CreateBankActivity extends AppCompatActivity {

    public String photoFileNameFront;
    public String photoFileNameBack;
    private int currentPositionColors;
    private OwnAsyncTask sync;
    private ImageView frontPhoto;
    private ImageView backPhoto;
    private FrameLayout removeFront;
    private FrameLayout removeBack;
    private DatabaseHelperImpl db;
    private ScrollView mScrollView;
    private ViewPager pagerTypes;
    private ViewPager pagerTemplate;
    private PagerAdapter pagerAdapterTemplate;
    private final Calendar calendar = Calendar.getInstance();
    private EditText bank;
    private EditText cardholder;
    private MaskedEditText number;
    private EditText pin;
    private TextView validDate;
    private EditText verificationNumber;
    private String myTypeCard;
    private String myColorName;
    private String myColorCode;
    private Uri editFrontUri;
    private Uri editBackUri;
    private boolean statusScan;
    private boolean statusSave = false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        sync = new OwnAsyncTask();
        initViews();
    }

    private void initViews() {
        final String uniqueString = UniqueStringGenerator.getUniqueString();
        photoFileNameFront = constants.BEG_FILE_NAME_BANK + uniqueString + "front_photo.jpg";
        photoFileNameBack = constants.BEG_FILE_NAME_BANK + uniqueString + "back_photo.jpg";
        verificationNumber = (EditText) findViewById(R.id.ver_number);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        bank = (EditText) findViewById(R.id.bank);
        cardholder = (EditText) findViewById(R.id.cardholder);
        number = (MaskedEditText) findViewById(R.id.number);
        pin = (EditText) findViewById(R.id.pin);
        validDate = (TextView) findViewById(R.id.date);
        pagerTemplate = (ViewPager) findViewById(R.id.pager);
        pagerTypes = (ViewPager) findViewById(R.id.type_card);
        frontPhoto = (ImageView) findViewById(R.id.front_photo);
        backPhoto = (ImageView) findViewById(R.id.back_photo);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
        statusScan = true;
        db = DatabaseHelperImpl.getInstance(this);
        setDateOnView();
        currentPositionColors = 0;
        final RobotoRegular title = (RobotoRegular) findViewById(R.id.title_toolbar);
        title.setText(getResources().getString(R.string.bank_title));

        final PreviewColor listPreviewColor = previewColors.get(0);
        myColorName = listPreviewColor.getNameColorCards();
        myColorCode = listPreviewColor.getCodeColorCards();
        Log.d("BANK", myColorName + " " + myColorCode);

        //TODO name convention
        final int PAGE_COUNT_TEMPLATE = previewColors.size();

        pagerTemplate.setPageMargin(constants.PAGER_MARGIN_PREVIEW);
        //TODO magic number, move to dimens
        pagerTemplate.setMinimumHeight(156);
        pagerAdapterTemplate = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                constants.PagerIDs.ID_BANK_CARD_ITEM,
                PAGE_COUNT_TEMPLATE);
        pagerTemplate.setAdapter(pagerAdapterTemplate);

        pagerTemplate.addOnPageChangeListener(new OnTemplatePageChangeListener(new OnTemplatePageChangeListener.OnPageChangeListener() {

            @Override
            public void onResult(final int position, final String codeColor, final String nameColor) {
                currentPositionColors = position;
                myColorCode = codeColor;
                myColorName = nameColor;
                Log.d("COLOR", position + myColorName + myColorCode);
            }
        }));

        myTypeCard = previewTypes.get(0);
        Log.d("BANK", myTypeCard);
        final int PAGE_COUNT = previewTypes.size();
        final PagerAdapter pagerAdapterTypes = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                constants.PagerIDs.ID_BANK_CARD_ITEM_TYPE,
                PAGE_COUNT);
        pagerTypes.setAdapter(pagerAdapterTypes);
        FragmentPreviewCards.icon = R.drawable.type_visa;
        pagerTypes.addOnPageChangeListener(new OnTypePageChangeListener(new OnTypePageChangeListener.OnPageChangeListener() {

            @Override
            public void onResult(final int position, final Integer icon, final String type) {
                myTypeCard = type;
                //TODO remove all statics
                FragmentPreviewCards.icon = icon;
                pagerTemplate.setAdapter(pagerAdapterTemplate);
                pagerTemplate.setCurrentItem(currentPositionColors);
            }
        }));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {

                @Override
                public void onScrollChange(final View pView, final int pI, final int pI1, final int pI2, final int pI3) {
                    final InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    final View view = getCurrentFocus();
                    assert view != null;
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            });
        }
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
                    intent.putExtra(constants.STATUS_PHOTOEDITOR, constants.STATUS_PHOTOEEDITOR_CREDIT_CARD);
                    startActivityForResult(intent, EDIT_IMAGE_FRONT);
                } else {
                    final Uri takenPhotoUri = getUri(photoFileNameBack);
                    final Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(constants.STATUS_PHOTOEDITOR, constants.STATUS_PHOTOEEDITOR_CREDIT_CARD);
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
                ImageLoader.getInstance().downloadToView(editFrontUri.toString(), frontPhoto, new OnResultCallback<Bitmap, Void>() {

                    @Override
                    public void onSuccess(final Bitmap pBitmap) {
                        removeFront.setVisibility(View.VISIBLE);
                        frontPhoto.setClickable(false);
                    }

                    @Override
                    public void onError(final Exception pE) {

                    }

                    @Override
                    public void onProgressChanged(final Void pVoid) {

                    }
                });
                sync.execute(new ScanCreditCard(getAssets()), editFrontUri, new OnResultCallback<CreditCard, String>() {

                    @Override
                    public void onSuccess(final CreditCard pCredit) {
                        if (pCredit != null && statusScan) {
                            final String creditNumber = pCredit.getNumberCreditCard();
                            final String creditCardholder = pCredit.getCardholderCreditCard();
                            final String creditName = pCredit.getNameCreditCard();
                            final String creditType = pCredit.getTypeCreditCard();
                            final String creditValid = pCredit.getValidCreditCard();

                            if (!creditNumber.isEmpty() || !creditCardholder.isEmpty()
                                    || !creditName.isEmpty() || !creditType.isEmpty()
                                    || !creditValid.isEmpty()) {
                                showAlertDialogRecognize(creditNumber, creditCardholder, creditName,
                                        creditType, creditValid);
                            } else {
                                Toast.makeText(CreateBankActivity.this, "Not found matches", Toast.LENGTH_LONG).show();
                                Log.d("TAG", "SFDSDFS");
                            }
                        }
                    }

                    @Override
                    public void onError(final Exception pE) {
                        Log.d("RESULT", pE.toString());
                    }

                    @Override
                    public void onProgressChanged(final String pS) {

                    }
                });
                removeFront.setVisibility(View.VISIBLE);
                frontPhoto.setClickable(false);
            } else if (requestCode == EDIT_IMAGE_BACK) {
                editBackUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
                ImageLoader.getInstance().downloadToView(editBackUri.toString(), backPhoto, new OnResultCallback<Bitmap, Void>() {

                    @Override
                    public void onSuccess(final Bitmap pBitmap) {
                        removeBack.setVisibility(View.VISIBLE);
                        backPhoto.setClickable(false);
                    }

                    @Override
                    public void onError(final Exception pE) {

                    }

                    @Override
                    public void onProgressChanged(final Void pVoid) {

                    }
                });
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
        //TODO move to builders
        final AlertDialog.Builder builder = new AlertDialog.Builder(CreateBankActivity.this);
        builder.setTitle(R.string.matches_found)
                .setMessage(message)
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {

                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(getString(R.string.apply), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(final DialogInterface dialog, final int pI) {
                        pasteRecognizeTextToViews(creditNumber, creditCardholder, creditName,
                                creditType, creditValid);
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void pasteRecognizeTextToViews(final CharSequence creditNumber, final CharSequence creditCardholder,
                                           final CharSequence creditName, final String creditType, final CharSequence creditValid) {
        if (!creditNumber.equals(constants.EMPTY_STRING)) {
            number.setText(creditNumber);
        }
        cardholder.setText(creditCardholder);
        bank.setText(creditName);
        validDate.setText(creditValid);
        if (!creditType.isEmpty()) {
            final int numberType;
            switch (creditType) {
                case constants.Cards.VISA:
                    numberType = constants.PagerTypesID.VISA;
                    break;
                case constants.Cards.MASTERCARD:
                    numberType = constants.PagerTypesID.MASTERCAD;
                    break;
                case constants.Cards.AMEX:
                    numberType = constants.PagerTypesID.AMEX;
                    break;
                case constants.Cards.MAESTRO:
                    numberType = constants.PagerTypesID.MAESTRO;
                    break;
                case constants.Cards.WESTERN_UNION:
                    numberType = constants.PagerTypesID.WESTERN_UNION;
                    break;
                case constants.Cards.JCB:
                    numberType = constants.PagerTypesID.JCB;
                    break;
                case constants.Cards.DINERS_CLUB:
                    numberType = constants.PagerTypesID.DINERS_CLUB;
                    break;
                default:
                    numberType = constants.PagerTypesID.BELCARD;
                    break;
            }
            pagerTypes.setCurrentItem(numberType);
        }
    }

    public Uri getUri(final String fileName) {
        if (isExternalStorageAvailable()) {
            final File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), constants.APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(constants.APP_TAG, getString(R.string.filed_to_create_directory));
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
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getUri(fileName)); // set the image file name
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
        backPhoto.setImageBitmap(null);
        backPhoto.setClickable(true);
        removeBack.setVisibility(GONE);
    }

    public void onRemoveFrontClicked(final View view) {
        frontPhoto.setImageBitmap(null);
        frontPhoto.setClickable(true);
        removeFront.setVisibility(GONE);
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    private void createCard() {
        final String bankStr = bank.getText().toString();
        final String cardholderStr = cardholder.getText().toString();
        final String numberStr = number.getText().toString();
        final String pinStr = pin.getText().toString();
        final String validThru = validDate.getText().toString();
        final String type = myTypeCard;
        final String color = myColorCode;
        final String verNumber = verificationNumber.getText().toString();
        if (bankStr.isEmpty() || cardholderStr.isEmpty() || verNumber.isEmpty() || numberStr.isEmpty()
                || validThru.isEmpty() || type.isEmpty() || color.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
        } else {
            final ContentValues cvNewCredit = new ContentValues();
            cvNewCredit.put(ModelBankCards.TITLE, bankStr);
            if (removeFront.getVisibility() == View.VISIBLE) {
                cvNewCredit.put(ModelBankCards.PHOTO_FRONT, editFrontUri.toString());
            } else {
                cvNewCredit.put(ModelBankCards.PHOTO_FRONT, constants.EMPTY_STRING);
            }
            if (removeBack.getVisibility() == View.VISIBLE) {
                cvNewCredit.put(ModelBankCards.PHOTO_BACK, editBackUri.toString());
            } else {
                cvNewCredit.put(ModelBankCards.PHOTO_BACK, constants.EMPTY_STRING);
            }
            cvNewCredit.put(ModelBankCards.VERIFICATION_NUMBER, verNumber);
            cvNewCredit.put(ModelBankCards.CARDHOLDER, cardholderStr);
            cvNewCredit.put(ModelBankCards.NUMBER, numberStr);
            cvNewCredit.put(ModelBankCards.PIN, pinStr);
            cvNewCredit.put(ModelBankCards.VALID, validThru);
            cvNewCredit.put(ModelBankCards.TYPE, type);
            cvNewCredit.put(ModelBankCards.BACKGROUND_COLOR, color);
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

    void setDateOnView() {
        //TODO move to some DateUtils class
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy", Locale.US);
        validDate.setText(dateFormat.format(calendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener dateCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            setDateOnView();
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

    public void onPrevColorPagerClicked(final View view) {
        pagerTemplate.setCurrentItem(pagerTemplate.getCurrentItem() - 1);
    }

    public void onNextColorPagerClicked(final View view) {
        pagerTemplate.setCurrentItem(pagerTemplate.getCurrentItem() + 1);
    }
}