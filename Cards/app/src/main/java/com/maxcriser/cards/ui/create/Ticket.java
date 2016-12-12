package com.maxcriser.cards.ui.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.ui.PhotoEditor;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.util.UniqueStringGenerator;
import com.maxcriser.cards.view.TextViews.RobotoRegular;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.ID_TICKET_ITEM;
import static com.maxcriser.cards.constant.constants.NEW_TICKET_TITLE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class Ticket extends AppCompatActivity {

    public static final String TICKET = "Ticket";
    public final String APP_TAG = "thecrisertakephoto";
    public String photoFileNameFront;
    public String photoFileNameBack;
    public final static int CAPTURE_IMAGE_FRONT = 1001;
    public final static int CAPTURE_IMAGE_BACK = 1010;
    public final static int EDIT_IMAGE_FRONT = 1011;
    public final static int EDIT_IMAGE_BACK = 1100;
    DatabaseHelper db;
    TextView date;
    TextView time;
    ImageView frontPhoto;
    ImageView backPhoto;
    SimpleDateFormat dateFormat;
    SimpleDateFormat timeFormat;

    Calendar calendar = Calendar.getInstance();
    static int PAGE_COUNT;
    static final int pagerMargin = 16;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    ContentValues cvNewTicket;
    ScrollView mScrollView;

    Colors listColors;
    CheckBox checkBox;
    String myColorName;
    String myColorCode;
    EditText ticketTitle;
    EditText ticketCardholder;
    RobotoRegular title;
    FrameLayout removeFront;
    FrameLayout removeBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
        dateFormat = new SimpleDateFormat("d MMM yyyy", Locale.US);
        timeFormat = new SimpleDateFormat("h:mm a", Locale.US);
        photoFileNameFront = "ticket-" + UniqueStringGenerator.getUniqueString() + "front_photo.jpg";
        photoFileNameBack = "ticket-" + UniqueStringGenerator.getUniqueString() + "back_photo.jpg";
        setDateOnView();
        setTimeOnView();

        db = DatabaseHelper.getInstance(this, 1);
        title.setText(NEW_TICKET_TITLE);

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorCards();
        myColorCode = listColors.getCodeColorCards();
        Log.d(TICKET, myColorName + " " + myColorCode);
        PAGE_COUNT = previewColors.size();

        pager.setPageMargin(pagerMargin);
        pagerAdapter = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_TICKET_ITEM,
                PAGE_COUNT);

        pager.setAdapter(pagerAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                listColors = previewColors.get(position);
                myColorName = listColors.getNameColorCards();
                myColorCode = listColors.getCodeColorCards();
                Log.d(TICKET, myColorName + " " + myColorCode);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
                    getExternalFilesDir(Environment.DIRECTORY_PICTURES), APP_TAG);
            if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                Log.d(APP_TAG, "failed to create directory");
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
        ticketTitle = (EditText) findViewById(R.id.title_name_ticket);
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
        removeBack = (FrameLayout) findViewById(R.id.remove_back);
        removeFront = (FrameLayout) findViewById(R.id.remove_front);
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

    public void onCreateCardClicked(View view) {
        String cardholderStr = ticketCardholder.getText().toString();
        String titleStr = ticketTitle.getText().toString();
        String timeStr = timeFormat.format(calendar.getTime());
        String dateStr = dateFormat.format(calendar.getTime());
        if (!titleStr.equals("") && !cardholderStr.equals("") && !timeStr.equals("") && !dateStr.equals("")) {
            if (checkBox.isChecked()) {
//                Intent intent = new Intent(Intent.ACTION_EDIT);
//                intent.setType("vnd.android.cursor.item/event");
//                intent.putExtra("beginTime", calendar.getTimeInMillis());
//                intent.putExtra("allDay", false);
//                intent.putExtra("rrule", "FREQ=YEARLY");
//                intent.putExtra("endTime", calendar.getTimeInMillis() + 60 * 60 * 1000);
//                intent.putExtra("title", ticketTitle.getText().toString());
//                startActivity(intent);
            }

            cvNewTicket = new ContentValues();
            cvNewTicket.put(ModelTickets.TITLE, titleStr);
            cvNewTicket.put(ModelTickets.CARDHOLDER, cardholderStr);
            cvNewTicket.put(ModelTickets.DATE, dateStr);
            cvNewTicket.put(ModelTickets.TIME, timeStr);
            cvNewTicket.put(ModelTickets.BACKGROUND_COLOR, myColorCode);
            cvNewTicket.put(ModelTickets.ID, (Integer) null);

            db.insert(ModelTickets.class, cvNewTicket, new OnResultCallback<Long, Void>() {
                @Override
                public void onSuccess(Long pLong) {
                    Log.d("TICKET" + " ID", pLong.toString());
                }

                @Override
                public void onError(Exception pE) {
                }

                @Override
                public void onProgressChanged(Void pVoid) {
                }
            });
            onBackClicked(null);


        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, "Please fill all fields and try again", Toast.LENGTH_LONG).show();
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

    public void onCancelClicked(View view) {
        super.onBackPressed();
    }
}