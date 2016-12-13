package com.maxcriser.cards.ui.create_item;

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

import com.github.pinball83.maskededittext.MaskedEditText;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.constant.Extras;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.fragment.FragmentPreviewCards;
import com.maxcriser.cards.setter.PreviewColorsSetter;
import com.maxcriser.cards.ui.PhotoEditorActivity;
import com.maxcriser.cards.view.text_view.RobotoRegular;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.view.View.GONE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;
import static com.maxcriser.cards.ui.LaunchScreenActivity.sPreviewColorSetters;

public class CreateBankActivity extends AppCompatActivity {

    public static final String BANK_ID = "BANK ";
    public static final String BANK = "CreateBankActivity";
    private static final int pagerMargin = 16;
    private static int PAGE_COUNT_TEMPLATE;
    private static int PAGE_COUNT;
    public final String APP_TAG = "thecrisertakephoto";
    private int currentPositionColors;
    public String photoFileNameFront;
    public String photoFileNameBack;
    private ImageView frontPhoto;
    private ImageView backPhoto;
    private FrameLayout removeFront;
    private FrameLayout removeBack;
    private DatabaseHelperImpl db;
    private ScrollView mScrollView;
    private ViewPager pagerTypes;
    private ViewPager pagerTemplate;
    private PagerAdapter pagerAdapterTypes;
    private PagerAdapter pagerAdapterTemplate;
    private PreviewColorsSetter mListPreviewColorsSetter;
    private RobotoRegular title;
    private Calendar calendar = Calendar.getInstance();
    private EditText bank;
    private EditText cardholder;
    private MaskedEditText number;
    private EditText pin;
    private TextView validDate;
    private String myTypeCard;
    private String myColorName;
    private String myColorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
        //todo refactor that method. move to initView for example
        db = DatabaseHelperImpl.getInstance(this);
        setDateOnView();
        currentPositionColors = 0;
        title.setText(Constants.NEW_TITLES.NEW_BANK_TITLE);

        mListPreviewColorsSetter = sPreviewColorSetters.get(0);
        myColorName = mListPreviewColorsSetter.getNameColorCards();
        myColorCode = mListPreviewColorsSetter.getCodeColorCards();
        Log.d(BANK, myColorName + " " + myColorCode);

        PAGE_COUNT_TEMPLATE = sPreviewColorSetters.size();

        pagerTemplate.setPageMargin(pagerMargin);
        pagerTemplate.setMinimumHeight(156);
        pagerAdapterTemplate = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                Constants.ID_PAGERS.ID_BANK_CARD_ITEM,
                PAGE_COUNT_TEMPLATE);
        pagerTemplate.setAdapter(pagerAdapterTemplate);
        pagerTemplate.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mListPreviewColorsSetter = sPreviewColorSetters.get(position);
                myColorName = mListPreviewColorsSetter.getNameColorCards();
                myColorCode = mListPreviewColorsSetter.getCodeColorCards();
                Log.d(BANK, myColorName + " " + myColorCode);
                currentPositionColors = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        myTypeCard = previewTypes.get(0);
        Log.d(BANK, myTypeCard);
        PAGE_COUNT = previewTypes.size();
        pagerAdapterTypes = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                Constants.ID_PAGERS.ID_BANK_CARD_ITEM_TYPE,
                PAGE_COUNT);
        pagerTypes.setAdapter(pagerAdapterTypes);
        FragmentPreviewCards.icon = R.drawable.type_visa;
        pagerTypes.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                myTypeCard = previewTypes.get(position);
                Log.d(BANK, myTypeCard);
                switch (myTypeCard) {
                    case Constants.CARDS.VISA:
                        FragmentPreviewCards.icon = R.drawable.type_visa;
                        break;
                    case Constants.CARDS.MAESTRO:
                        FragmentPreviewCards.icon = R.drawable.type_maestro;
                        break;
                    case Constants.CARDS.MASTERCARD:
                        FragmentPreviewCards.icon = R.drawable.type_mastercard;
                        break;
                    case Constants.CARDS.AMEX:
                        FragmentPreviewCards.icon = R.drawable.type_amex;
                        break;
                    case Constants.CARDS.WESTERN_UNION:
                        FragmentPreviewCards.icon = R.drawable.type_western_union;
                        break;
                    case Constants.CARDS.JCB:
                        FragmentPreviewCards.icon = R.drawable.type_jcb;
                        break;
                    case Constants.CARDS.DINERS_CLUB:
                        FragmentPreviewCards.icon = R.drawable.type_diners_club;
                        break;
                    case Constants.CARDS.BELCARD:
                        FragmentPreviewCards.icon = R.drawable.type_belcard;
                        break;
                }
                pagerTemplate.setAdapter(pagerAdapterTemplate);
                pagerTemplate.setCurrentItem(currentPositionColors);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }

        });
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        bank = (EditText) findViewById(R.id.bank);
        cardholder = (EditText) findViewById(R.id.cardholder);
        number = (MaskedEditText) findViewById(R.id.number);
        pin = (EditText) findViewById(R.id.pin);
        validDate = (TextView) findViewById(R.id.date);
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        pagerTemplate = (ViewPager) findViewById(R.id.pager);
        pagerTypes = (ViewPager) findViewById(R.id.type_card);
        frontPhoto = (ImageView) findViewById(R.id.front_photo);
        backPhoto = (ImageView) findViewById(R.id.back_photo);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUESTS.CAPTURE_IMAGE_FRONT ||
                requestCode == Constants.REQUESTS.CAPTURE_IMAGE_BACK) {
            if (resultCode == RESULT_OK) {
//                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
                if (requestCode == Constants.REQUESTS.CAPTURE_IMAGE_FRONT) {
                    Uri takenPhotoUri = getPhotoFileUri(photoFileNameFront);
                    Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(Extras.EXTRA_URI, takenPhotoUri.toString());
                    startActivityForResult(intent, Constants.REQUESTS.EDIT_IMAGE_FRONT);
//                    frontPhoto.setImageURI(takenPhotoUri);
                } else {
                    Uri takenPhotoUri = getPhotoFileUri(photoFileNameBack);
                    Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(Extras.EXTRA_URI, takenPhotoUri.toString());
                    startActivityForResult(intent, Constants.REQUESTS.EDIT_IMAGE_BACK);
//                    backPhoto.setImageURI(takenPhotoUri);
                }
            } else {
                Toast.makeText(this, R.string.picture_wasnt_taken, Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == Constants.REQUESTS.EDIT_IMAGE_FRONT) {
                Uri editFrontUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
                frontPhoto.setImageURI(editFrontUri);
                removeFront.setVisibility(View.VISIBLE);
                frontPhoto.setClickable(false);
            } else if (requestCode == Constants.REQUESTS.EDIT_IMAGE_BACK) {
                Uri editBackUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
                backPhoto.setImageURI(editBackUri);
                removeBack.setVisibility(View.VISIBLE);
                backPhoto.setClickable(false);
            }
        } else {
            Toast.makeText(this, R.string.picture_wasnt_edited, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public Uri getPhotoFileUri(String fileName) {
        if (isExternalStorageAvailable()) {
            File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(APP_TAG, getString(R.string.filed_to_create_directory));
            }
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    public void onBackPhotoClicked(View view) {
        getPermission(Constants.REQUESTS.REQUEST_BACK_CAMERA, Manifest.permission.CAMERA, Constants.REQUESTS.CAPTURE_IMAGE_BACK);
    }

    public void onFrontPhotoClicked(View view) {
        getPermission(Constants.REQUESTS.REQUEST_FRONT_CAMERA, Manifest.permission.CAMERA, Constants.REQUESTS.CAPTURE_IMAGE_FRONT);
    }

    @TargetApi(23)
    private void getPermission(final byte CODE, final String PERMISSION, int INTENT) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
        } else {
            if (INTENT == Constants.REQUESTS.CAPTURE_IMAGE_FRONT) {
                startCameraForPhoto(Constants.REQUESTS.CAPTURE_IMAGE_FRONT, photoFileNameFront);
            } else if (INTENT == Constants.REQUESTS.CAPTURE_IMAGE_BACK) {
                startCameraForPhoto(Constants.REQUESTS.CAPTURE_IMAGE_BACK, photoFileNameBack);
            } else if (INTENT == Constants.REQUESTS.REQUEST_WRITE_STORAGE) {
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
        } else if (requestCode == Constants.REQUESTS.CAPTURE_IMAGE_FRONT) {
            startCameraForPhoto(Constants.REQUESTS.CAPTURE_IMAGE_FRONT, photoFileNameFront);
        } else if (requestCode == Constants.REQUESTS.CAPTURE_IMAGE_BACK) {
            startCameraForPhoto(Constants.REQUESTS.CAPTURE_IMAGE_BACK, photoFileNameBack);
        } else if (requestCode == Constants.REQUESTS.REQUEST_WRITE_STORAGE) {
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
        if (bankStr.equals(Constants.EMPTY_STRING)
                || cardholderStr.equals(Constants.EMPTY_STRING)
                || numberStr.equals(Constants.EMPTY_STRING)
                || validThru.equals(Constants.EMPTY_STRING)
                || type.equals(Constants.EMPTY_STRING)
                || color.equals(Constants.EMPTY_STRING)) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_LONG).show();
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
        } else {
            ContentValues cvNewCredit = new ContentValues();
            cvNewCredit.put(ModelBankCards.TITLE, bankStr);
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
                    Log.d(BANK_ID + " ID", pLong.toString());
                }

                @Override
                public void onError(Exception pE) {
                }

                @Override
                public void onProgressChanged(Void pVoid) {
                }
            });
            onBackClicked(null);
        }
    }

    public void onCreateCardClicked(View view) {
        // // TODO: 12.12.2016 filesDir
        getPermission(Constants.REQUESTS.REQUEST_WRITE_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Constants.REQUESTS.REQUEST_WRITE_STORAGE);
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