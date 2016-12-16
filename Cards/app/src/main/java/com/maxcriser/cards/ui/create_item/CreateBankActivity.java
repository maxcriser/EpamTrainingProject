package com.maxcriser.cards.ui.create_item;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.ScanCreditCard;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.constant.Extras;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.fragment.FragmentPreviewCards;
import com.maxcriser.cards.model.CreditCard;
import com.maxcriser.cards.model.PreviewColor;
import com.maxcriser.cards.ui.PhotoEditorActivity;
import com.maxcriser.cards.util.OnTemplatePageChangeListener;
import com.maxcriser.cards.util.OnTypePageChangeListener;
import com.maxcriser.cards.util.UniqueStringGenerator;
import com.maxcriser.cards.view.custom_view.RobotoRegular;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.Constants.Requests.CAPTURE_IMAGE_BACK;
import static com.maxcriser.cards.constant.Constants.Requests.CAPTURE_IMAGE_FRONT;
import static com.maxcriser.cards.constant.Constants.Requests.EDIT_IMAGE_BACK;
import static com.maxcriser.cards.constant.Constants.Requests.EDIT_IMAGE_FRONT;
import static com.maxcriser.cards.constant.Constants.Requests.REQUEST_BACK_CAMERA;
import static com.maxcriser.cards.constant.Constants.Requests.REQUEST_FRONT_CAMERA;
import static com.maxcriser.cards.constant.Constants.Requests.REQUEST_WRITE_STORAGE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

public class CreateBankActivity extends AppCompatActivity {

    private String result = Constants.EMPTY_STRING;
    public static final String BANK = "CreateBankActivity"; // TODO delete
    public String photoFileNameFront;
    public String photoFileNameBack;
    private int currentPositionColors;
    private ImageView frontPhoto;
    private ImageView backPhoto;
    private FrameLayout removeFront;
    private FrameLayout removeBack;
    private DatabaseHelperImpl db;
    private ScrollView mScrollView;
    private ViewPager pagerTypes;
    private ViewPager pagerTemplate;
    private PagerAdapter pagerAdapterTemplate;
    private Calendar calendar = Calendar.getInstance();
    private EditText bank;
    private EditText cardholder;
    private EditText number;
    private EditText pin;
    private TextView validDate;
    private EditText verificationNumber;
    private String myTypeCard;
    private String myColorName;
    private String myColorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
    }

    private void initViews() {
        photoFileNameFront = Constants.BEG_FILE_NAME_BANK + UniqueStringGenerator.getUniqueString() + "front_photo.jpg";
        photoFileNameBack = Constants.BEG_FILE_NAME_BANK + UniqueStringGenerator.getUniqueString() + "back_photo.jpg";
        verificationNumber = (EditText) findViewById(R.id.ver_number);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        bank = (EditText) findViewById(R.id.bank);
        cardholder = (EditText) findViewById(R.id.cardholder);
        number = (EditText) findViewById(R.id.number);
        pin = (EditText) findViewById(R.id.pin);
        validDate = (TextView) findViewById(R.id.date);
        pagerTemplate = (ViewPager) findViewById(R.id.pager);
        pagerTypes = (ViewPager) findViewById(R.id.type_card);
        frontPhoto = (ImageView) findViewById(R.id.front_photo);
        backPhoto = (ImageView) findViewById(R.id.back_photo);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
        db = DatabaseHelperImpl.getInstance(this);
        setDateOnView();
        currentPositionColors = 0;
        RobotoRegular title = (RobotoRegular) findViewById(R.id.title_toolbar);
        title.setText(Constants.TitlesNew.NEW_BANK_TITLE);

        PreviewColor listPreviewColor = previewColors.get(0);
        myColorName = listPreviewColor.getNameColorCards();
        myColorCode = listPreviewColor.getCodeColorCards();
        Log.d(BANK, myColorName + " " + myColorCode);

        int PAGE_COUNT_TEMPLATE = previewColors.size();

        pagerTemplate.setPageMargin(Constants.PAGER_MARGIN_PREVIEW);
        pagerTemplate.setMinimumHeight(156);
        pagerAdapterTemplate = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                Constants.PagerIDs.ID_BANK_CARD_ITEM,
                PAGE_COUNT_TEMPLATE);
        pagerTemplate.setAdapter(pagerAdapterTemplate);

        pagerTemplate.addOnPageChangeListener(new OnTemplatePageChangeListener(new OnTemplatePageChangeListener.OnPageChangeListener() {
            @Override
            public void onResult(int position, String codeColor, String nameColor) {
                currentPositionColors = position;
                myColorCode = codeColor;
                myColorName = nameColor;
                Log.d("COLOR", position + myColorName + myColorCode);
            }
        }));

        myTypeCard = previewTypes.get(0);
        Log.d(BANK, myTypeCard);
        int PAGE_COUNT = previewTypes.size();
        PagerAdapter pagerAdapterTypes = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                Constants.PagerIDs.ID_BANK_CARD_ITEM_TYPE,
                PAGE_COUNT);
        pagerTypes.setAdapter(pagerAdapterTypes);
        FragmentPreviewCards.icon = R.drawable.type_visa;
        pagerTypes.addOnPageChangeListener(new OnTypePageChangeListener(new OnTypePageChangeListener.OnPageChangeListener() {
            @Override
            public void onResult(int position, Integer icon, String type) {
                myTypeCard = type;
                FragmentPreviewCards.icon = icon;
                pagerTemplate.setAdapter(pagerAdapterTemplate);
                pagerTemplate.setCurrentItem(currentPositionColors);
            }
        }));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_FRONT ||
                requestCode == CAPTURE_IMAGE_BACK) {
            if (resultCode == RESULT_OK) {
//                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
                if (requestCode == CAPTURE_IMAGE_FRONT) {
                    Uri takenPhotoUri = getPhotoFileUri(photoFileNameFront);
                    Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(Extras.EXTRA_URI, takenPhotoUri.toString());
                    startActivityForResult(intent, EDIT_IMAGE_FRONT);
//                    frontPhoto.setImageURI(takenPhotoUri);
                } else {
                    Uri takenPhotoUri = getPhotoFileUri(photoFileNameBack);
                    Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(Extras.EXTRA_URI, takenPhotoUri.toString());
                    startActivityForResult(intent, EDIT_IMAGE_BACK);
//                    backPhoto.setImageURI(takenPhotoUri);
                }
            } else {
                Toast.makeText(this, R.string.picture_wasnt_taken, Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_OK) {
            OwnAsyncTask scan = new OwnAsyncTask();
            if (requestCode == EDIT_IMAGE_FRONT) {
                Uri editFrontUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
//                frontPhoto.setImageURI(editFrontUri);
                scan.execute(new ScanCreditCard(), editFrontUri, new OnResultCallback<CreditCard, String>() {
                    @Override
                    public void onSuccess(CreditCard pCredit) {
//                        if (pCredit != null) {
//                            String creditNumber = pCredit.getNumberCreditCard();
//                            String creditCardholder = pCredit.getCardholderCreditCard();
//                            String creditName = pCredit.getNameCreditCard();
//                            String creditType = pCredit.getTypeCreditCard();
//                            String creditValid = pCredit.getValidCreditCard();
//
//                            if (!creditNumber.isEmpty() || !creditCardholder.isEmpty()
//                                    || !creditName.isEmpty() || !creditType.isEmpty()
//                                    || !creditValid.isEmpty()) {
//                                showAlertDialogRecognize(creditNumber, creditCardholder, creditName,
//                                        creditType, creditValid);
//                            } else {
//                                Toast.makeText(CreateBankActivity.this, "Not found matches", Toast.LENGTH_LONG).show();
                        Log.d("TAG", "SFDSDFS");
//                            }
//                        }
                    }

                    @Override
                    public void onError(Exception pE) {
                        Log.d("RESULT", pE.toString());
                    }

                    @Override
                    public void onProgressChanged(String pS) {

                    }
                });
                removeFront.setVisibility(View.VISIBLE);
                frontPhoto.setClickable(false);
            } else if (requestCode == EDIT_IMAGE_BACK) {
                Uri editBackUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
                // TODO: 16.12.2016  recognize
                scan.execute(new ScanCreditCard(), editBackUri, null);
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
            message += "Valid through: " + creditValid;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateBankActivity.this);
        builder.setTitle(R.string.matches_found)
                .setMessage(getString(R.string.fount_matches_on_fields) + message +
                        getString(R.string.click_apply_if_matches))
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(getString(R.string.apply), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int pI) {
                        pasteRecognizeTextToViews(creditNumber, creditCardholder, creditName,
                                creditType, creditValid);
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void pasteRecognizeTextToViews(String creditNumber, String creditCardholder,
                                           String creditName, String creditType, String creditValid) {
        number.setText(creditNumber);
        cardholder.setText(creditCardholder);
        bank.setText(creditName);
        validDate.setText(creditValid);
        if (!creditType.isEmpty()) {
            int numberType;
            switch (creditType) {
                case Constants.Cards.VISA:
                    numberType = Constants.PagerTypesID.VISA;
                    break;
                case Constants.Cards.MASTERCARD:
                    numberType = Constants.PagerTypesID.MASTERCAD;
                    break;
                case Constants.Cards.AMEX:
                    numberType = Constants.PagerTypesID.AMEX;
                    break;
                case Constants.Cards.MAESTRO:
                    numberType = Constants.PagerTypesID.MAESTRO;
                    break;
                case Constants.Cards.WESTERN_UNION:
                    numberType = Constants.PagerTypesID.WESTERN_UNION;
                    break;
                case Constants.Cards.JCB:
                    numberType = Constants.PagerTypesID.JCB;
                    break;
                case Constants.Cards.DINERS_CLUB:
                    numberType = Constants.PagerTypesID.DINERS_CLUB;
                    break;
                default:
                    numberType = Constants.PagerTypesID.BELCARD;
                    break;
            }
            pagerTypes.setCurrentItem(numberType);
        }
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

    public void onBackPhotoClicked(View view) {
        getPermission(REQUEST_BACK_CAMERA, Manifest.permission.CAMERA);
    }

    public void onFrontPhotoClicked(View view) {
        getPermission(REQUEST_FRONT_CAMERA, Manifest.permission.CAMERA);
    }

    @TargetApi(23)
    private void getPermission(final byte CODE, final String PERMISSION) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
        } else {
            if (CODE == REQUEST_FRONT_CAMERA) {
                startCameraForPhoto(CAPTURE_IMAGE_FRONT, photoFileNameFront);
            } else if (CODE == REQUEST_BACK_CAMERA) {
                startCameraForPhoto(CAPTURE_IMAGE_BACK, photoFileNameBack);
            } else if (CODE == REQUEST_WRITE_STORAGE) {
                createCard();
            }
        }
    }

    private void startCameraForPhoto(int code, String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(fileName)); // set the image file name
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, code);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.permission_has_not_been_granted, Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_FRONT_CAMERA) {
            startCameraForPhoto(CAPTURE_IMAGE_FRONT, photoFileNameFront);
        } else if (requestCode == REQUEST_BACK_CAMERA) {
            startCameraForPhoto(CAPTURE_IMAGE_BACK, photoFileNameBack);
        } else if (requestCode == REQUEST_WRITE_STORAGE) {
            createCard();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onRemoveBackClicked(View view) {
        backPhoto.setImageURI(null);
        backPhoto.setClickable(true);
        removeBack.setVisibility(GONE);
    }

    public void onRemoveFrontClicked(View view) {
        frontPhoto.setImageURI(null);
        frontPhoto.setClickable(true);
        removeFront.setVisibility(GONE);
    }


    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    private void createCard() {
        String bankStr = bank.getText().toString();
        String cardholderStr = cardholder.getText().toString();
        String numberStr = number.getText().toString();
        String pinStr = pin.getText().toString();
        String validThru = validDate.getText().toString();
        String type = myTypeCard;
        String color = myColorCode;
        String verNumber = verificationNumber.getText().toString();
        if (bankStr.isEmpty() || cardholderStr.isEmpty() || verNumber.isEmpty() || numberStr.isEmpty()
                || validThru.isEmpty() || type.isEmpty() || color.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
        } else {
            ContentValues cvNewCredit = new ContentValues();
            cvNewCredit.put(ModelBankCards.TITLE, bankStr);
            cvNewCredit.put(ModelBankCards.PHOTO_FRONT, photoFileNameFront);
            cvNewCredit.put(ModelBankCards.PHOTO_BACK, photoFileNameBack);
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
                public void onSuccess(Long pLong) {
                    onBackClicked(null);
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

    public void onCreateCardClicked(View view) {
        // // TODO: 12.12.2016 filesDir
        getPermission(REQUEST_WRITE_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    public void onToolbarBackClicked(View view) {
    }

    void setDateOnView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yy", Locale.US);
        validDate.setText(dateFormat.format(calendar.getTime()));
    }

    DatePickerDialog.OnDateSetListener dateCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            setDateOnView();
        }
    };

    public void onDateClicked(View view) {
        DatePickerDialog tpd = new DatePickerDialog(this, dateCallBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        tpd.show();
    }

    public void onCancelClicked(View view) {
        onBackClicked(null);
    }

    public void onPrevTypePagerClicked(View view) {
        pagerTypes.setCurrentItem(pagerTypes.getCurrentItem() - 1);
    }

    public void onNextTypePagerClicked(View view) {
        pagerTypes.setCurrentItem(pagerTypes.getCurrentItem() + 1);
    }

    public void onPrevColorPagerClicked(View view) {
        pagerTemplate.setCurrentItem(pagerTemplate.getCurrentItem() - 1);
    }

    public void onNextColorPagerClicked(View view) {
        pagerTemplate.setCurrentItem(pagerTemplate.getCurrentItem() + 1);
    }
}