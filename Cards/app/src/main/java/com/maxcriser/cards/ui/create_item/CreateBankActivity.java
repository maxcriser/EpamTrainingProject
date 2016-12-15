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
import android.os.Handler;
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

import com.googlecode.tesseract.android.TessBaseAPI;
import com.maxcriser.cards.MainActivity;
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
import com.maxcriser.cards.view.text_view.RobotoRegular;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

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

    private static final String TAG = MainActivity.class.getSimpleName();
    private TessBaseAPI tessBaseApi;
    private static final String lang = "eng";
    String result = "";
    private static final String DATA_PATH = Environment.getExternalStorageDirectory().toString() + "/TesseractSample/";
    private static final String TESSDATA = "tessdata";
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

    public Handler h = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Toast.makeText(CreateBankActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

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
        RobotoRegular title = (RobotoRegular) findViewById(R.id.title_toolbar);
        pagerTemplate = (ViewPager) findViewById(R.id.pager);
        pagerTypes = (ViewPager) findViewById(R.id.type_card);
        frontPhoto = (ImageView) findViewById(R.id.front_photo);
        backPhoto = (ImageView) findViewById(R.id.back_photo);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
        db = DatabaseHelperImpl.getInstance(this);
        setDateOnView();
        currentPositionColors = 0;
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
            if (requestCode == EDIT_IMAGE_FRONT) {
                Uri editFrontUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
//                Bitmap editBitmap = BitmapFactory.decodeFile(editFrontUri.getPath());
                frontPhoto.setImageURI(editFrontUri);
                OwnAsyncTask scan = new OwnAsyncTask();
                scan.execute(new ScanCreditCard(), editFrontUri, new OnResultCallback<String, String>() {
                    @Override
                    public void onSuccess(String pS) {
                        Toast.makeText(CreateBankActivity.this, pS, Toast.LENGTH_LONG).show();
                        cardholder.setText(pS);
//                        CreditCard.setValidCreditCard(pS);
                        Log.d("RESULT", result);
                    }

                    @Override
                    public void onError(Exception pE) {
                        Log.d("RESULT", pE.toString());
                    }

                    @Override
                    public void onProgressChanged(String pS) {

                    }
                });
//                OwnAsyncTask scan = new OwnAsyncTask();
//                scan.execute(new ScanCreditCard(), editFrontUri, new OnResultCallback<String, String>() {
//                    @Override
//                    public void onSuccess(String pS) {
//                        Toast.makeText(CreateBankActivity.this, pS + "-HANDLED", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onError(Exception pE) {
//                        Toast.makeText(CreateBankActivity.this, "ERROR-HANDLED", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onProgressChanged(String pS) {
//                    }
//                });
                removeFront.setVisibility(View.VISIBLE);
                frontPhoto.setClickable(false);
            } else if (requestCode == EDIT_IMAGE_BACK) {
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
        if (bankStr.equals(Constants.EMPTY_STRING)
                || cardholderStr.equals(Constants.EMPTY_STRING)
                || verNumber.equals(Constants.EMPTY_STRING)
                || numberStr.equals(Constants.EMPTY_STRING)
                || validThru.equals(Constants.EMPTY_STRING)
                || type.equals(Constants.EMPTY_STRING)
                || color.equals(Constants.EMPTY_STRING)) {
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

    public void onScanBackFront(View view) {

    }

    public void onScanBackBack(View view) {
    }


//    private void doOCR(Uri uri) {
//        prepareTesseract();
//        startOCR(uri);
//    }
//
//    private void prepareDirectory(String path) {
//
//        File dir = new File(path);
//        if (!dir.exists()) {
//            if (!dir.mkdirs()) {
//                Log.e(TAG, "ERROR: Creation of directory " + path + " failed, check does Android Manifest have permission to write to external storage.");
//            }
//        } else {
//            Log.i(TAG, "Created directory " + path);
//        }
//    }
//
//
//    private void prepareTesseract() {
//        try {
//            prepareDirectory(DATA_PATH + TESSDATA);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        copyTessDataFiles(TESSDATA);
//    }
//
//    private void copyTessDataFiles(String path) {
//        try {
//            String fileList[] = getAssets().list(path);
//
//            for (String fileName : fileList) {
//
// //                open file within the assets folder
//                 if it is not already there copy it to the sdcard
//                String pathToDataFile = DATA_PATH + path + "/" + fileName;
//                if (!(new File(pathToDataFile)).exists()) {
//
//                    InputStream in = getAssets().open(path + "/" + fileName);
//
//                    OutputStream out = new FileOutputStream(pathToDataFile);
//
    // //                   Transfer bytes from in to out
//                    byte[] buf = new byte[1024];
//                    int len;
//
//                    while ((len = in.read(buf)) > 0) {
//                        out.write(buf, 0, len);
//                    }
//                    in.close();
//                    out.close();
//
//                    Log.e(TAG, "Copied " + fileName + "to tessdata");
//                }
//            }
//        } catch (IOException e) {
//            Log.e(TAG, "Unable to copy files to tessdata " + e.toString());
//        }
//    }
//
//    private void startOCR(Uri imgUri) {
//        try {
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inSampleSize = 4; // 1 - means max size. 4 - means maxsize/4 size. Don't use value <4, because you need more memory in the heap to store your data.
//            Bitmap bitmap = BitmapFactory.decodeFile(imgUri.getPath(), options);
//
//            result = extractText(bitmap);
//            Message msg = h.obtainMessage(0, result);
//            h.sendMessage(msg);
//            Log.d("result", result);
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//        }
//    }
//
//    private String extractText(Bitmap bitmap) {
//        try {
//            tessBaseApi = new TessBaseAPI();
//        } catch (Exception e) {
//            Log.e(TAG, e.getMessage());
//            if (tessBaseApi == null) {
//                Log.e(TAG, "TessBaseAPI is null. TessFactory not returning tess object.");
//            }
//        }
//
//        tessBaseApi.init(DATA_PATH, lang);
//
//  //      For example if we only want to detect numbers
//        tessBaseApi.setVariable(TessBaseAPI.VAR_CHAR_WHITELIST, "0123456789" + "AaBbCcDdEeFfGgHhIiJiKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz/,.-");
//
//        Log.d(TAG, "Training file loaded");
//        tessBaseApi.setImage(bitmap);
//        String extractedText = "empty result";
//        try {
//            extractedText = tessBaseApi.getUTF8Text();
//        } catch (Exception e) {
//            Log.e(TAG, "Error in recognizing text.");
//        }
//        tessBaseApi.end();
//        return extractedText;
//    }
}