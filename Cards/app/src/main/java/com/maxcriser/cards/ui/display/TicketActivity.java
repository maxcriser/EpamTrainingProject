package com.maxcriser.cards.ui.display;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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
import com.maxcriser.cards.CoreApplication;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.RemovePhoto;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.dialog.ImageViewerDialogBuilder;
import com.maxcriser.cards.view.labels.RobotoThin;
import com.squareup.picasso.Picasso;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_CARDHOLDER;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_DATE;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_FIRST_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_SECOND_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TIME;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TITLE;

public class TicketActivity extends Activity {

    private final int DELAY_MILLIS = 300;
    private FloatingActionMenu materialDesignFAM;
    private LinearLayout editLinear;
    private ScrollView mScrollView;
    private TextView editTitle;
    private EditText editName;
    private String editTitleStr;
    private LinearLayout linearFrameAction;
    private String id;
    private DatabaseHelper dbHelper;
    private Animation animScaleDown;
    private Animation animScaleUp;
    private Bitmap firstBitmap;
    private Bitmap secondBitmap;
    private OwnAsyncTask sync;
    private EditText editCardholder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_ticket);
        findViewById(R.id.search_image_toolbar).setVisibility(GONE);
        sync = new OwnAsyncTask();
        initViews();
    }

    private void initViews() {
        final ImageView ivFrontPhoto = (ImageView) findViewById(R.id.front_photo);
        final ImageView ivBackPhoto = (ImageView) findViewById(R.id.back_photo);
        final RobotoThin date = (RobotoThin) findViewById(R.id.date);
        final RobotoThin time = (RobotoThin) findViewById(R.id.time);
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        editTitle = (TextView) findViewById(R.id.title_show_discount);
        editCardholder = (EditText) findViewById(R.id.cardholder);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        final FloatingActionButton floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        final FloatingActionButton floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);

        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down_floating);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up_floating);

        registerForContextMenu(materialDesignFAM);

        dbHelper = ((CoreApplication) getApplication()).getDatabaseHelper(this);

        final Intent creditIntent = getIntent();
        id = creditIntent.getStringExtra(EXTRA_TICKET_ID);
        final String titleStr = creditIntent.getStringExtra(EXTRA_TICKET_TITLE);
        final String cardholderStr = creditIntent.getStringExtra(EXTRA_TICKET_CARDHOLDER);
        final String dateStr = creditIntent.getStringExtra(EXTRA_TICKET_DATE);
        final String timeStr = creditIntent.getStringExtra(EXTRA_TICKET_TIME);
        final String firstPhoto = creditIntent.getStringExtra(EXTRA_TICKET_FIRST_PHOTO);
        final String secondPhoto = creditIntent.getStringExtra(EXTRA_TICKET_SECOND_PHOTO);

        floatingActionButtonDelete.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {
                dbHelper.delete(ModelTickets.class, null, ModelTickets.ID + " = ?", String.valueOf(id));
                sync.execute(new RemovePhoto(), Uri.parse(firstPhoto), null);
                sync.execute(new RemovePhoto(), Uri.parse(secondPhoto), null);
                onBackClicked(null);

            }
        });
        floatingActionButtonEdit.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
                editLinear.setVisibility(VISIBLE);
                editTitle.setVisibility(GONE);
                linearFrameAction.setVisibility(VISIBLE);
                editTitleStr = editTitle.getText().toString();
                editName.setText(editTitleStr);
                materialDesignFAM.close(true);
                editCardholder.setEnabled(true);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        materialDesignFAM.startAnimation(animScaleDown);
                        materialDesignFAM.setVisibility(GONE);
                    }
                }, DELAY_MILLIS);
            }
        });

        Picasso.with(this).load(Uri.parse(firstPhoto)).placeholder(R.drawable.camera_card_size_light).into(ivFrontPhoto);
        Picasso.with(this).load(Uri.parse(secondPhoto)).placeholder(R.drawable.camera_card_size_light).into(ivBackPhoto);

        /*
        Picasso.with(this).load(Uri.parse(firstPhoto)).into(new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
                ivFrontPhoto.setImageBitmap(bitmap);
                firstBitmap = bitmap;

            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {

            }
        });

        Picasso.with(this).load(Uri.parse(secondPhoto)).into(new Target() {

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, final Picasso.LoadedFrom from) {
                ivBackPhoto.setImageBitmap(bitmap);
                secondBitmap = bitmap;
            }

            @Override
            public void onBitmapFailed(final Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(final Drawable placeHolderDrawable) {

            }
        });
        */

        editTitle.setText(titleStr);
        editCardholder.setText(cardholderStr);
        date.setText(dateStr);
        time.setText(timeStr);
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    public void onCancelClicked(final View view) {
        editLinear.setVisibility(GONE);
        editTitle.setVisibility(VISIBLE);
        linearFrameAction.setVisibility(GONE);
        materialDesignFAM.setVisibility(VISIBLE);
        materialDesignFAM.startAnimation(animScaleUp);
    }

    public void onCreateCardClicked(final View view) {
        editTitleStr = editName.getText().toString();
        final String editCardholderStr = editCardholder.getText().toString();
        if (!editTitleStr.isEmpty() && !editCardholderStr.isEmpty()) {
            editTitle.setText(editTitleStr);
            editLinear.setVisibility(GONE);
            editTitle.setVisibility(VISIBLE);
            linearFrameAction.setVisibility(GONE);
            materialDesignFAM.setVisibility(VISIBLE);
            materialDesignFAM.startAnimation(animScaleUp);

            dbHelper.edit(ModelTickets.class, ModelTickets.TITLE, editTitleStr,
                    ModelTickets.ID, String.valueOf(id), null);
            dbHelper.edit(ModelTickets.class, ModelTickets.CARDHOLDER, editCardholderStr,
                    ModelTickets.ID, String.valueOf(id), null);
            editCardholder.setEnabled(false);
        } else {
            mScrollView.fullScroll(ScrollView.FOCUS_UP);
            Toast.makeText(this, R.string.empty_card_name, Toast.LENGTH_LONG).show();
        }
    }

    public void onToolbarBackClicked(final View view) {

    }

    void showPhoto(final Bitmap bitmap) {
        final ImageViewerDialogBuilder dialog = new ImageViewerDialogBuilder(this, bitmap);
        dialog.startDialog();
    }

    public void onSecondPhotoClicked(final View view) {
        if (secondBitmap != null) {
            showPhoto(secondBitmap);
        }
    }

    public void onFirstPhotoClicked(final View view) {
        if (firstBitmap != null) {
            showPhoto(firstBitmap);
        }
    }
}