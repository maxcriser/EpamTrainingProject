package com.maxcriser.cards.ui.display_item;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.LoadImage;
import com.maxcriser.cards.async.task.UriToBitmap;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.view.text_view.RobotoThin;

import java.io.File;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_CARDHOLDER;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_DATE;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_FIRST_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_SECOND_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TIME;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TITLE;

public class TicketActivity extends Activity {

    private FloatingActionMenu materialDesignFAM;
    private LinearLayout editLinear;
    private ScrollView mScrollView;
    private TextView editTitle;
    private EditText editName;
    private String editTitleStr;
    private LinearLayout linearFrameAction;
    private String id;
    private DatabaseHelperImpl dbHelper;
    private Handler mHandler;
    private Animation animScaleDown;
    private Animation animScaleUp;
    private Bitmap firstBitmap;
    private Bitmap secondBitmap;
    private OwnAsyncTask sync;

    Handler.Callback hc = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            materialDesignFAM.startAnimation(animScaleDown);
            materialDesignFAM.setVisibility(GONE);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ticket);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        sync = new OwnAsyncTask();
        initViews();
    }

    private void initViews() {
        ImageView ivFrontPhoto = (ImageView) findViewById(R.id.front_photo);
        ImageView ivBackPhoto = (ImageView) findViewById(R.id.back_photo);
        RobotoThin date = (RobotoThin) findViewById(R.id.date);
        RobotoThin time = (RobotoThin) findViewById(R.id.time);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        editTitle = (TextView) findViewById(R.id.title_show_discount);
        EditText editCardholder = (EditText) findViewById(R.id.cardholder);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        FloatingActionButton floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        FloatingActionButton floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
        mHandler = new Handler(hc);

        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);

        registerForContextMenu(materialDesignFAM);

        dbHelper = DatabaseHelperImpl.getInstance(this);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbHelper.delete(ModelTickets.class, null, ModelTickets.ID + " = ?", String.valueOf(id));
                onBackClicked(null);

            }
        });
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                editLinear.setVisibility(VISIBLE);
                editTitle.setVisibility(GONE);
                linearFrameAction.setVisibility(VISIBLE);
                editTitleStr = editTitle.getText().toString();
                editName.setText(editTitleStr);
                materialDesignFAM.close(true);
                mHandler.sendEmptyMessageDelayed(1, 300);
            }
        });

        Intent creditIntent = getIntent();
        id = creditIntent.getStringExtra(EXTRA_TICKET_ID);
        String titleStr = creditIntent.getStringExtra(EXTRA_TICKET_TITLE);
        String cardholderStr = creditIntent.getStringExtra(EXTRA_TICKET_CARDHOLDER);
        String dateStr = creditIntent.getStringExtra(EXTRA_TICKET_DATE);
        String timeStr = creditIntent.getStringExtra(EXTRA_TICKET_TIME);
        String colorStr = creditIntent.getStringExtra(EXTRA_TICKET_COLOR);
        String firstPhoto = creditIntent.getStringExtra(EXTRA_TICKET_FIRST_PHOTO);
        String secondPhoto = creditIntent.getStringExtra(EXTRA_TICKET_SECOND_PHOTO);

        sync.execute(new LoadImage(getExternalFilesDir(Environment.DIRECTORY_PICTURES), ivFrontPhoto),
                firstPhoto, null);
        sync.execute(new LoadImage(getExternalFilesDir(Environment.DIRECTORY_PICTURES), ivBackPhoto),
                secondPhoto, null);

        sync.execute(new UriToBitmap(getExternalFilesDir(Environment.DIRECTORY_PICTURES)), firstPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(Bitmap pBitmap) {
                firstBitmap = pBitmap;
                Toast.makeText(TicketActivity.this, "onsuccess first bitmap", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception pE) {
                Toast.makeText(TicketActivity.this, "error first bitmap", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChanged(Void pVoid) {
            }
        });

        sync.execute(new UriToBitmap(getExternalFilesDir(Environment.DIRECTORY_PICTURES)), secondPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(Bitmap pBitmap) {
                secondBitmap = pBitmap;
                Toast.makeText(TicketActivity.this, "onsuccess second bitmap", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(Exception pE) {
                Toast.makeText(TicketActivity.this, "error second bitmap", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChanged(Void pVoid) {
            }
        });
        editTitle.setText(titleStr);
        editCardholder.setText(cardholderStr);
        date.setText(dateStr);
        time.setText(timeStr);
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

    public void onBackClicked(View view) {
        // TODO: 07.12.2016   handler close materialFloating
        super.onBackPressed();
    }

    public void onCancelClicked(View view) {
        editLinear.setVisibility(GONE);
        editTitle.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);
    }

    public void onCreateCardClicked(View view) {
        editTitleStr = editName.getText().toString();
        if (!editTitleStr.isEmpty()) {
            editTitle.setText(editTitleStr);
            editLinear.setVisibility(GONE);
            editTitle.setVisibility(VISIBLE);
            linearFrameAction.setVisibility(GONE);
            materialDesignFAM.setVisibility(VISIBLE);
            materialDesignFAM.startAnimation(animScaleUp);

            dbHelper.edit(ModelTickets.class,
                    ModelTickets.TITLE,
                    editTitleStr,
                    ModelTickets.ID,
                    String.valueOf(id),
                    new OnResultCallback<Void, Void>() {
                        @Override
                        public void onSuccess(Void pVoid) {

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
            Toast.makeText(this, R.string.empty_card_name, Toast.LENGTH_LONG).show();
        }
    }

    public void onToolbarBackClicked(View view) {

    }

    void showPhoto(final Bitmap bitmap) {
        ImageView image = new ImageView(this);
        image.setImageBitmap(bitmap);

        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).
                        setPositiveButton(R.string.close, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).
                        setView(image);
        builder.create().show();
    }

    public void onSecondPhotoClicked(View view) {
        if (secondBitmap != null)
            showPhoto(secondBitmap);
    }

    public void onFirstPhotoClicked(View view) {
        if (firstBitmap != null)
            showPhoto(firstBitmap);
    }
}