package com.maxcriser.cards.ui.create_item;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.constant.Extras;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.model.PreviewColor;
import com.maxcriser.cards.ui.PhotoEditorActivity;
import com.maxcriser.cards.util.OnTemplatePageChangeListener;
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
import static com.maxcriser.cards.constant.Constants.Requests.REQUEST_CALENDAR;
import static com.maxcriser.cards.constant.Constants.Requests.REQUEST_FRONT_CAMERA;
import static com.maxcriser.cards.constant.Constants.Requests.REQUEST_WRITE_STORAGE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class CreateTicketActivity extends AppCompatActivity {

    public static final String TICKET = "CreateTicketActivity";
    public String photoFileNameFront;
    public String photoFileNameBack;
    private DatabaseHelperImpl db;
    private TextView date;
    private TextView time;
    private ImageView frontPhoto;
    private ImageView backPhoto;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private Calendar calendar = Calendar.getInstance();
    private ViewPager pager;
    private ScrollView mScrollView;
    private PreviewColor mListPreviewColor;
    private CheckBox checkBox;
    private String myColorName;
    private String myColorCode;
    private EditText ticketTitle;
    private EditText ticketCardholder;
    private RobotoRegular title;
    private FrameLayout removeFront;
    private FrameLayout removeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_FRONT ||
                requestCode == CAPTURE_IMAGE_BACK) {
            if (resultCode == RESULT_OK) {
                // TODO load in view
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
                frontPhoto.setImageURI(editFrontUri);
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

//    public static File savebitmap(Bitmap bmp, String fileName) throws IOException {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 60, bytes);
//        File f = new File(getApplicationContext().getFilesDir() + fileName);
//        f.createNewFile();
//        FileOutputStream fo = new FileOutputStream(f);
//        fo.write(bytes.toByteArray());
//        fo.close();
//        return f;
//    }

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public Uri getPhotoFileUri(String fileName) {
        if (isExternalStorageAvailable()) {
            File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), Constants.APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(Constants.APP_TAG, getResources().getString(R.string.filed_to_create_directory));
            }
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        ticketCardholder = (EditText) findViewById(R.id.cardholder);
        frontPhoto = (ImageView) findViewById(R.id.front_photo);
        backPhoto = (ImageView) findViewById(R.id.back_photo);
        checkBox = (CheckBox) findViewById(R.id.add_to_calendar);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                getPermission(REQUEST_CALENDAR, Manifest.permission.WRITE_CALENDAR, REQUEST_CALENDAR);
            }
        });
        ticketTitle = (EditText) findViewById(R.id.title_name_ticket);
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
        dateFormat = new SimpleDateFormat("d MMM yyyy", Locale.US);
        timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        photoFileNameFront = Constants.BEG_FILE_NAME_TICKET + UniqueStringGenerator.getUniqueString() + "front_photo.jpg";
        photoFileNameBack = Constants.BEG_FILE_NAME_TICKET + UniqueStringGenerator.getUniqueString() + "back_photo.jpg";
        setDateOnView();
        setTimeOnView();
        db = DatabaseHelperImpl.getInstance(this);
        title.setText(Constants.TitlesNew.NEW_TICKET_TITLE);

        mListPreviewColor = previewColors.get(0);
        myColorName = mListPreviewColor.getNameColorCards();
        myColorCode = mListPreviewColor.getCodeColorCards();
        Log.d(TICKET, myColorName + " " + myColorCode);
        int PAGE_COUNT = previewColors.size();

        pager.setPageMargin(Constants.PAGER_MARGIN_PREVIEW);
        PagerAdapter pagerAdapter = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                Constants.PagerIDs.ID_TICKET_ITEM,
                PAGE_COUNT);

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new OnTemplatePageChangeListener(new OnTemplatePageChangeListener.OnPageChangeListener() {
            @Override
            public void onResult(int position, String codeColor, String nameColor) {
                myColorCode = codeColor;
                myColorName = nameColor;
                Log.d("COLOR", position + myColorName + myColorCode);
            }
        }));
    }

    void setDateOnView() {
        date.setText(dateFormat.format(calendar.getTime()));
    }

    void setTimeOnView() {
        time.setText(timeFormat.format(calendar.getTime()));
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onToolbarBackClicked(View view) {
    }

    DatePickerDialog.OnDateSetListener dateCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            setDateOnView();
        }
    };

    TimePickerDialog.OnTimeSetListener timeCallBack = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker pTimePicker, int hour, int min) {
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, min);
            setTimeOnView();
        }
    };

    public void onDateClicked(View view) {
        DatePickerDialog tpd = new DatePickerDialog(this, dateCallBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        tpd.show();
    }

    public void onTimeClicked(View view) {
        TimePickerDialog tpd = new TimePickerDialog(this, timeCallBack,
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                true);
        tpd.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_CALENDAR) {
                checkBox.setChecked(false);
            }
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

    @TargetApi(23)
    private void getPermission(final byte CODE, final String PERMISSION, int INTENT) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
        } else {
            if (INTENT == CAPTURE_IMAGE_FRONT) {
                startCameraForPhoto(CAPTURE_IMAGE_FRONT, photoFileNameFront);
            } else if (INTENT == CAPTURE_IMAGE_BACK) {
                startCameraForPhoto(CAPTURE_IMAGE_BACK, photoFileNameBack);
            } else if (INTENT == REQUEST_WRITE_STORAGE) {
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

    public void onFrontPhotoClicked(View view) {
        getPermission(REQUEST_FRONT_CAMERA, Manifest.permission.CAMERA, CAPTURE_IMAGE_FRONT);
    }

    public void onBackPhotoClicked(View view) {
        getPermission(REQUEST_BACK_CAMERA, Manifest.permission.CAMERA, CAPTURE_IMAGE_BACK);
    }

    private void createCard() {
        String cardholderStr = ticketCardholder.getText().toString();
        String titleStr = ticketTitle.getText().toString();
        String timeStr = timeFormat.format(calendar.getTime());
        String dateStr = dateFormat.format(calendar.getTime());
        if (!titleStr.equals(Constants.EMPTY_STRING)
                && !cardholderStr.equals(Constants.EMPTY_STRING)
                && !timeStr.equals(Constants.EMPTY_STRING)
                && !dateStr.equals(Constants.EMPTY_STRING)) {
            if (checkBox.isChecked()) {
                addCalendarEvent(calendar.getTimeInMillis(), ticketTitle.getText().toString());
            }

            ContentValues cvNewTicket = new ContentValues();
            cvNewTicket.put(ModelTickets.TITLE, titleStr);
            cvNewTicket.put(ModelTickets.PHOTO_FIRST, photoFileNameFront);
            cvNewTicket.put(ModelTickets.PHOTO_SECOND, photoFileNameBack);
            cvNewTicket.put(ModelTickets.CARDHOLDER, cardholderStr);
            cvNewTicket.put(ModelTickets.DATE, dateStr);
            cvNewTicket.put(ModelTickets.TIME, timeStr);
            cvNewTicket.put(ModelTickets.BACKGROUND_COLOR, myColorCode);
            cvNewTicket.put(ModelTickets.ID, (Integer) null);

            db.insert(ModelTickets.class, cvNewTicket, new OnResultCallback<Long, Void>() {
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
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show();
        }
    }

    private void addCalendarEvent(Long timeInMillis, String title) {
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", timeInMillis);
        intent.putExtra("allDay", false);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", timeInMillis + 60 * 60 * 1000);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void onCreateCardClicked(View view) {
        // // TODO: 12.12.2016 filesDir
        getPermission(REQUEST_WRITE_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, REQUEST_WRITE_STORAGE);
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

    public void onCancelClicked(View view) {
        super.onBackPressed();
    }

    public void onPrevColorPagerClicked(View view) {
        pager.setCurrentItem(pager.getCurrentItem() - 1);
    }

    public void onNextColorPagerClicked(View view) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }
}