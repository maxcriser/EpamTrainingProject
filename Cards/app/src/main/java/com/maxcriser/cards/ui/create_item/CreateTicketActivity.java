package com.maxcriser.cards.ui.create_item;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
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
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.RemovePhoto;
import com.maxcriser.cards.constant.constants;
import com.maxcriser.cards.constant.Extras;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelNFCItems;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.fragment.FragmentPagerAdapterTemplate;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.model.PreviewColor;
import com.maxcriser.cards.ui.activities.PhotoEditorActivity;
import com.maxcriser.cards.listener.OnTemplatePageChangeListener;
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
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_CALENDAR;
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_FRONT_CAMERA;
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_WRITE_STORAGE_BACK;
import static com.maxcriser.cards.constant.constants.Requests.REQUEST_WRITE_STORAGE_FRONT;
import static com.maxcriser.cards.ui.activities.LaunchScreenActivity.previewColors;
import static com.maxcriser.cards.utils.Storage.isExternalStorageAvailable;

public class CreateTicketActivity extends AppCompatActivity {

    public static final String TICKET = "CreateTicketActivity";
    public String photoFileNameFront;
    public String photoFileNameBack;
    private DatabaseHelperImpl db;
    private TextView date;
    private boolean statusSave;
    private TextView time;
    private ImageView frontPhoto;
    private ImageView backPhoto;
    private OwnAsyncTask sync;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private final Calendar calendar = Calendar.getInstance();
    private Uri editBackUri;
    private Uri editFrontUri;
    private ViewPager pager;
    private ScrollView mScrollView;
    private CheckBox checkBox;
    private String myColorName;
    private String myColorCode;
    private EditText ticketTitle;
    private EditText ticketCardholder;
    private FrameLayout removeFront;
    private FrameLayout removeBack;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        sync = new OwnAsyncTask();
        initViews();
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
                    intent.putExtra(constants.STATUS_PHOTOEDITOR, constants.STATUS_PHOTOEEDITOR_TICKET);
                    startActivityForResult(intent, EDIT_IMAGE_FRONT);
                } else {
                    final Uri takenPhotoUri = getUri(photoFileNameBack);
                    final Intent intent = new Intent(this, PhotoEditorActivity.class);
                    intent.putExtra(Extras.EXTRA_URI, takenPhotoUri.toString());
                    intent.putExtra(constants.STATUS_PHOTOEDITOR, constants.STATUS_PHOTOEEDITOR_TICKET);
                    startActivityForResult(intent, EDIT_IMAGE_BACK);
                }
            } else {
                Toast.makeText(this, R.string.picture_wasnt_taken, Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == EDIT_IMAGE_FRONT) {
                editFrontUri = Uri.parse(data.getStringExtra(Extras.EXTRA_URI));
                //TODO create simple OnResultCallback with empty methods
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

    public Uri getUri(final String fileName) {
        if (isExternalStorageAvailable()) {
            final File mediaStorageDir = new File(
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), constants.APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(constants.APP_TAG, getResources().getString(R.string.filed_to_create_directory));
            }
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
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
        super.onDestroy();
    }


    private void initViews() {
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        ticketCardholder = (EditText) findViewById(R.id.cardholder);
        frontPhoto = (ImageView) findViewById(R.id.front_photo);
        backPhoto = (ImageView) findViewById(R.id.back_photo);
        checkBox = (CheckBox) findViewById(R.id.add_to_calendar);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View pView) {
                getPermission(REQUEST_CALENDAR, Manifest.permission.WRITE_CALENDAR);
            }
        });
        ticketTitle = (EditText) findViewById(R.id.title_name_ticket);
        final RobotoRegular title = (RobotoRegular) findViewById(R.id.title_toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
        dateFormat = new SimpleDateFormat("d MMM yyyy", Locale.US);
        timeFormat = new SimpleDateFormat("h:mm a", Locale.US);

        final String uniqueString = UniqueStringGenerator.getUniqueString();
        photoFileNameFront = constants.BEG_FILE_NAME_TICKET + uniqueString + "front_photo.jpg";
        photoFileNameBack = constants.BEG_FILE_NAME_TICKET + uniqueString + "back_photo.jpg";
        setDateOnView();
        setTimeOnView();
        db = DatabaseHelperImpl.getInstance(this);
        title.setText(getResources().getString(R.string.new_ticket_title));

        final PreviewColor listPreviewColor = previewColors.get(0);
        myColorName = listPreviewColor.getNameColorCards();
        myColorCode = listPreviewColor.getCodeColorCards();
        Log.d(TICKET, myColorName + " " + myColorCode);
        final int PAGE_COUNT = previewColors.size();

        pager.setPageMargin(constants.PAGER_MARGIN_PREVIEW);
        final PagerAdapter pagerAdapter = new FragmentPagerAdapterTemplate(getSupportFragmentManager(),
                constants.PagerIDs.ID_TICKET_ITEM,
                PAGE_COUNT);

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new OnTemplatePageChangeListener(new OnTemplatePageChangeListener.OnPageChangeListener() {
            @Override
            public void onResult(final int position, final String codeColor, final String nameColor) {
                myColorCode = codeColor;
                myColorName = nameColor;
                Log.d("COLOR", position + myColorName + myColorCode);
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

    void setDateOnView() {
        date.setText(dateFormat.format(calendar.getTime()));
    }

    void setTimeOnView() {
        time.setText(timeFormat.format(calendar.getTime()));
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    public void onToolbarBackClicked(final View view) {
    }

    DatePickerDialog.OnDateSetListener dateCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(final DatePicker view, final int year, final int month, final int day) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            setDateOnView();
        }
    };

    TimePickerDialog.OnTimeSetListener timeCallBack = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(final TimePicker pTimePicker, final int hour, final int min) {
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, min);
            setTimeOnView();
        }
    };

    public void onDateClicked(final View view) {
        final DatePickerDialog tpd = new DatePickerDialog(this, dateCallBack,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        tpd.show();
    }

    public void onTimeClicked(final View view) {
        final TimePickerDialog tpd = new TimePickerDialog(this, timeCallBack,
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.MINUTE),
                true);
        tpd.show();
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            if (requestCode == REQUEST_CALENDAR) {
                checkBox.setChecked(false);
            }
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

    public void onFrontPhotoClicked(final View view) {
        getPermission(REQUEST_FRONT_CAMERA, Manifest.permission.CAMERA);
    }

    public void onBackPhotoClicked(final View view) {
        getPermission(REQUEST_BACK_CAMERA, Manifest.permission.CAMERA);
    }

    private void createCard() {
        final String cardholderStr = ticketCardholder.getText().toString();
        final String titleStr = ticketTitle.getText().toString();
        final String timeStr = timeFormat.format(calendar.getTime());
        final String dateStr = dateFormat.format(calendar.getTime());
        if (!titleStr.isEmpty()
                && !cardholderStr.isEmpty()
                && !timeStr.isEmpty()
                && !dateStr.isEmpty()) {
            if (checkBox.isChecked()) {
                addCalendarEvent(calendar.getTimeInMillis(), ticketTitle.getText().toString());
            }

            final ContentValues cvNewTicket = new ContentValues();
            cvNewTicket.put(ModelTickets.TITLE, titleStr);
            if (removeFront.getVisibility() == View.VISIBLE) {
                cvNewTicket.put(ModelTickets.PHOTO_FIRST, editFrontUri.toString());
            } else {
                cvNewTicket.put(ModelTickets.PHOTO_FIRST, constants.EMPTY_STRING);
            }
            if (removeBack.getVisibility() == View.VISIBLE) {
                cvNewTicket.put(ModelTickets.PHOTO_SECOND, editBackUri.toString());
            } else {
                cvNewTicket.put(ModelTickets.PHOTO_SECOND, constants.EMPTY_STRING);
            }
            cvNewTicket.put(ModelTickets.CARDHOLDER, cardholderStr);
            cvNewTicket.put(ModelTickets.DATE, dateStr);
            cvNewTicket.put(ModelTickets.TIME, timeStr);
            cvNewTicket.put(ModelTickets.BACKGROUND_COLOR, myColorCode);
            cvNewTicket.put(ModelTickets.ID, (Integer) null);

            // TODO DELETE DOWN

            final ContentValues cv = new ContentValues();
            cv.put(ModelNFCItems.TITLE, titleStr);
            cv.put(ModelNFCItems.ID, (Integer) null);
            cv.put(ModelNFCItems.TAG, "Checking TAG");
            cv.put(ModelNFCItems.BACKGROUND_COLOR, myColorCode);

            db.insert(ModelNFCItems.class, cv, null);
            // TODO DELETE UP

            db.insert(ModelTickets.class, cvNewTicket, new OnResultCallback<Long, Void>() {
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
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, getResources().getString(R.string.fill_all_fields), Toast.LENGTH_LONG).show();
        }
    }

    private void addCalendarEvent(final Long timeInMillis, final String title) {
        final Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", timeInMillis);
        intent.putExtra("allDay", false);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", timeInMillis + 60 * 60 * 1000);
        intent.putExtra("title", title);
        startActivity(intent);
    }

    public void onCreateCardClicked(final View view) {
//        getPermission(REQUEST_WRITE_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        createCard();
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

    public void onCancelClicked(final View view) {
        super.onBackPressed();
    }

    public void onPrevColorPagerClicked(final View view) {
        pager.setCurrentItem(pager.getCurrentItem() - 1);
    }

    public void onNextColorPagerClicked(final View view) {
        pager.setCurrentItem(pager.getCurrentItem() + 1);
    }
}