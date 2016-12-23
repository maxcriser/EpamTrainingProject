package com.maxcriser.cards.ui.display_item;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.maxcriser.cards.async.task.RemovePhoto;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.loader.image.ImageLoader;
import com.maxcriser.cards.dialogs.ImageViewerDialogBuilder;
import com.maxcriser.cards.view.labels.RobotoThin;

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
        public boolean handleMessage(final Message msg) {
            materialDesignFAM.startAnimation(animScaleDown);
            materialDesignFAM.setVisibility(GONE);
            return false;
        }
    };

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
        final EditText editCardholder = (EditText) findViewById(R.id.cardholder);
        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        editLinear = (LinearLayout) findViewById(R.id.linear_edit_frame_title_discount);
        editName = (EditText) findViewById(R.id.rename_discount_title);
        final FloatingActionButton floatingActionButtonDelete = (FloatingActionButton) findViewById(R.id.floating_delete_button);
        final FloatingActionButton floatingActionButtonEdit = (FloatingActionButton) findViewById(R.id.floating_edit_button);
        linearFrameAction = (LinearLayout) findViewById(R.id.linear_frame_actions_discount);
        mHandler = new Handler(hc);

        animScaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down_floating);
        animScaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up_floating);

        registerForContextMenu(materialDesignFAM);

        dbHelper = DatabaseHelperImpl.getInstance(this);

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
                mHandler.sendEmptyMessageDelayed(1, 300);
            }
        });

        ImageLoader.getInstance().downloadToView(firstPhoto, ivFrontPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(final Bitmap pBitmap) {
                firstBitmap = pBitmap;
            }

            @Override
            public void onError(final Exception pE) {

            }

            @Override
            public void onProgressChanged(final Void pVoid) {

            }
        });

        ImageLoader.getInstance().downloadToView(secondPhoto, ivBackPhoto, new OnResultCallback<Bitmap, Void>() {
            @Override
            public void onSuccess(final Bitmap pBitmap) {
                secondBitmap = pBitmap;
            }

            @Override
            public void onError(final Exception pE) {

            }

            @Override
            public void onProgressChanged(final Void pVoid) {

            }
        });

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
                        public void onSuccess(final Void pVoid) {

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