package com.maxcriser.cards.ui.create;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.ui.PhotoEditor;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.ui.pager.ViewPagerPreviewCard;
import com.maxcriser.cards.view.TextViews.RobotoRegular;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.AMEX;
import static com.maxcriser.cards.constant.constants.BELCARD;
import static com.maxcriser.cards.constant.constants.DINERS_CLUB;
import static com.maxcriser.cards.constant.constants.ID_BANK_CARD_ITEM;
import static com.maxcriser.cards.constant.constants.ID_BANK_CARD_ITEM_TYPE;
import static com.maxcriser.cards.constant.constants.JCB;
import static com.maxcriser.cards.constant.constants.MAESTRO;
import static com.maxcriser.cards.constant.constants.MASTERCARD;
import static com.maxcriser.cards.constant.constants.NEW_BANK_TITLE;
import static com.maxcriser.cards.constant.constants.VISA;
import static com.maxcriser.cards.constant.constants.WESTERN_UNION;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewTypes;

public class Bank extends AppCompatActivity {

    public final String APP_TAG = "thecrisertakephoto";
    public String photoFileNameFront;
    public String photoFileNameBack;
    public final static int CAPTURE_IMAGE_FRONT = 1001;
    public final static int CAPTURE_IMAGE_BACK = 1010;
    public final static int EDIT_IMAGE_FRONT = 1011;
    public final static int EDIT_IMAGE_BACK = 1100;
    ImageView frontPhoto;
    ImageView backPhoto;
    FrameLayout removeFront;
    FrameLayout removeBack;
    ContentValues cvNewCredit;
    DatabaseHelper db;
    ScrollView mScrollView;

    public static final String BANK = "Bank";
    int currentPositionColors;
    static int PAGE_COUNT;
    static int PAGE_COUNT_TEMPLATE;
    static final int pagerMargin = 16;
    ViewPager pagerTypes;
    ViewPager pagerTemplate;
    PagerAdapter pagerAdapterTypes;
    PagerAdapter pagerAdapterTemplate;
    Colors listColors;
    RobotoRegular title;
    Calendar calendar = Calendar.getInstance();

    EditText bank;
    EditText cardholder;
    MaskedEditText number;
    EditText pin;
    TextView validDate;
    String myTypeCard;
    String myColorName;
    String myColorCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_card);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
        db = DatabaseHelper.getInstance(this, 1);

        setDateOnView();
        currentPositionColors = 0;
        title.setText(NEW_BANK_TITLE);

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorCards();
        myColorCode = listColors.getCodeColorCards();
        Log.d(BANK, myColorName + " " + myColorCode);

        PAGE_COUNT_TEMPLATE = previewColors.size();

        pagerTemplate.setPageMargin(pagerMargin);
        pagerTemplate.setMinimumHeight(156);
        pagerAdapterTemplate = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_BANK_CARD_ITEM,
                PAGE_COUNT_TEMPLATE);
        pagerTemplate.setAdapter(pagerAdapterTemplate);
        pagerTemplate.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                listColors = previewColors.get(position);
                myColorName = listColors.getNameColorCards();
                myColorCode = listColors.getCodeColorCards();
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
        pagerAdapterTypes = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_BANK_CARD_ITEM_TYPE,
                PAGE_COUNT);
        pagerTypes.setAdapter(pagerAdapterTypes);
        ViewPagerPreviewCard.icon = R.drawable.type_visa;
        pagerTypes.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                myTypeCard = previewTypes.get(position);
                Log.d(BANK, myTypeCard);
                switch (myTypeCard) {
                    case VISA:
                        ViewPagerPreviewCard.icon = R.drawable.type_visa;
                        break;
                    case MAESTRO:
                        ViewPagerPreviewCard.icon = R.drawable.type_maestro;
                        break;
                    case MASTERCARD:
                        ViewPagerPreviewCard.icon = R.drawable.type_mastercard;
                        break;
                    case AMEX:
                        ViewPagerPreviewCard.icon = R.drawable.type_amex;
                        break;
                    case WESTERN_UNION:
                        ViewPagerPreviewCard.icon = R.drawable.type_western_union;
                        break;
                    case JCB:
                        ViewPagerPreviewCard.icon = R.drawable.type_jcb;
                        break;
                    case DINERS_CLUB:
                        ViewPagerPreviewCard.icon = R.drawable.type_diners_club;
                        break;
                    case BELCARD:
                        ViewPagerPreviewCard.icon = R.drawable.type_belcard;
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
        if (requestCode == CAPTURE_IMAGE_FRONT ||
                requestCode == CAPTURE_IMAGE_BACK) {
            if (resultCode == RESULT_OK) {
//                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
                if (requestCode == CAPTURE_IMAGE_FRONT) {
                    Uri takenPhotoUri = getPhotoFileUri(photoFileNameFront);
                    Intent intent = new Intent(this, PhotoEditor.class);
                    intent.putExtra("uri", takenPhotoUri.toString());
                    startActivityForResult(intent, EDIT_IMAGE_FRONT);
                    removeFront.setVisibility(View.VISIBLE);
                    frontPhoto.setClickable(false);
//                    frontPhoto.setImageURI(takenPhotoUri);
                } else {
                    Uri takenPhotoUri = getPhotoFileUri(photoFileNameBack);
                    Intent intent = new Intent(this, PhotoEditor.class);
                    intent.putExtra("uri", takenPhotoUri.toString());
                    startActivityForResult(intent, EDIT_IMAGE_BACK);
                    removeBack.setVisibility(View.VISIBLE);
                    backPhoto.setClickable(false);
//                    backPhoto.setImageURI(takenPhotoUri);
                }
            } else {
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == EDIT_IMAGE_FRONT) {
            Uri editFrontUri = Uri.parse(data.getStringExtra("uri"));
            frontPhoto.setImageURI(editFrontUri);
        } else if (requestCode == EDIT_IMAGE_BACK) {
            Uri editBackUri = Uri.parse(data.getStringExtra("uri"));
            backPhoto.setImageURI(editBackUri);
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
                Log.d(APP_TAG, "failed to create directory");
            }
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }


    public void onFrontPhotoClicked(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileNameFront)); // set the image file name

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_FRONT);
        }
    }

    public void onBackPhotoClicked(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileNameBack)); // set the image file name

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_BACK);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onCreateCardClicked(View view) {
        String bankStr = bank.getText().toString();
        String cardholderStr = cardholder.getText().toString();
        String numberStr = number.getText().toString();
        String pinStr = pin.getText().toString();
        String validThru = validDate.getText().toString();
        String type = myTypeCard;
        String color = myColorCode;
        Log.d("fill", bankStr + "\n" + cardholderStr + "\n" + numberStr + "\n" + pinStr + "\n" + validThru + "\n" + type + "\n" + color);
        if (bankStr.equals("") || cardholderStr.equals("") || numberStr.equals("") || validThru.equals("") || type.equals("") || color.equals("")) {
            Toast.makeText(this, "Please fill all fields and try again", Toast.LENGTH_LONG).show();
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
        } else {
            cvNewCredit = new ContentValues();
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
                    Log.d("BANK " + " ID", pLong.toString());
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
}