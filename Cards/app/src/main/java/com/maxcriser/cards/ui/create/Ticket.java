package com.maxcriser.cards.ui.create;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.maxcriser.cards.R;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.reader.Colors;
import com.maxcriser.cards.ui.adapter.MyFragmentPagerAdapterTemplate;
import com.maxcriser.cards.view.TextViews.RobotoRegular;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.ID_TICKET_ITEM;
import static com.maxcriser.cards.constant.constants.NEW_TICKET_TITLE;
import static com.maxcriser.cards.ui.LaunchScreenActivity.previewColors;

public class Ticket extends AppCompatActivity {

    public static final String DISCOUNT = "Ticket";
    DatabaseHelper db;
    TextView date;
    TextView time;
    Calendar calendar = Calendar.getInstance();

    static int PAGE_COUNT;
    static final int pagerMargin = 16;
    ViewPager pager;
    PagerAdapter pagerAdapter;

    Colors listColors;
    CheckBox checkBox;
    String myColorName;
    String myColorCode;
    EditText ticketTitle;
    RobotoRegular title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        initViews();
        setDateOnView();
        setTimeOnView();

        db = DatabaseHelper.getInstance(this, 1);
        title.setText(NEW_TICKET_TITLE);

        listColors = previewColors.get(0);
        myColorName = listColors.getNameColorCards();
        myColorCode = listColors.getCodeColorCards();
        Log.d(DISCOUNT, myColorName + " " + myColorCode);
        PAGE_COUNT = previewColors.size();

        pager.setPageMargin(pagerMargin);
        pagerAdapter = new MyFragmentPagerAdapterTemplate(getSupportFragmentManager(),
                ID_TICKET_ITEM,
                PAGE_COUNT);

        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                listColors = previewColors.get(position);
                myColorName = listColors.getNameColorCards();
                myColorCode = listColors.getCodeColorCards();
                Log.d(DISCOUNT, myColorName + " " + myColorCode);
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
        checkBox = (CheckBox) findViewById(R.id.add_to_calendar);
        ticketTitle = (EditText) findViewById(R.id.title_name_ticket);
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        pager = (ViewPager) findViewById(R.id.pager);
        date = (TextView) findViewById(R.id.date);
        time = (TextView) findViewById(R.id.time);
    }

    void setDateOnView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy", Locale.US);
        date.setText(dateFormat.format(calendar.getTime()));
    }

    void setTimeOnView() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h:mm a", Locale.US);
        time.setText(dateFormat.format(calendar.getTime()));
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

    }

    public void onBackPhotoClicked(View view) {

    }

    public void onCreateCardClicked(View view) {
        if (!ticketTitle.getText().toString().equals("")) {
            if (checkBox.isChecked()) {
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", calendar.getTimeInMillis());
                intent.putExtra("allDay", false);
                intent.putExtra("rrule", "FREQ=YEARLY");
                intent.putExtra("endTime", calendar.getTimeInMillis() + 60 * 60 * 1000);
                intent.putExtra("title", ticketTitle.getText().toString());
                startActivity(intent);
            }
        }
    }
}