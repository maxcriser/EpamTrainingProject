package com.maxcriser.cards.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.Extras;
import com.maxcriser.cards.constant.ListConstants;

public class PhotoEditorActivity extends AppCompatActivity {

    private CropImageView image;
    private FrameLayout mProgressBar;
    private Uri photoUri;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        setContentView(R.layout.activity_photo_editor);
        initViews();
    }

    void initViews() {
        final Intent intent = getIntent();
        final String statusEditor = intent.getStringExtra(ListConstants.STATUS_PHOTOEDITOR);
        final LinearLayout menuBar = (LinearLayout) findViewById(R.id.menu_bar);
        image = (CropImageView) findViewById(R.id.cropImageView);
        mProgressBar = (FrameLayout) findViewById(R.id.frame_progressbar);
        final float initialScane = 0.75f;
        image.setInitialFrameScale(initialScane);
        if (statusEditor.equals(ListConstants.STATUS_PHOTOEEDITOR_CREDIT_CARD)) {
            final int ratioY = 55;
            final int ratioX = 87;
            image.setCustomRatio(ratioX, ratioY);
            menuBar.setVisibility(View.GONE);
        } else {
            image.setCropMode(CropImageView.CropMode.FREE);
            menuBar.setVisibility(View.VISIBLE);
        }
        final int handleDp = 0;
        image.setHandleSizeInDp(handleDp);
        final int weightDp = 1;
        image.setFrameStrokeWeightInDp(weightDp);
        final int weightDpFirst = 1;
        image.setGuideStrokeWeightInDp(weightDpFirst);
        final int paddingDp = 24;
        image.setTouchPaddingInDp(paddingDp);
        final int minDp = 85;
        image.setMinFrameSizeInDp(minDp);

        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final String uri_Str = bundle.getString(Extras.EXTRA_URI);
            photoUri = Uri.parse(uri_Str);
            startCrop(photoUri);
        } else {
            finish();
        }
    }

    void startCrop(final Uri uri) {
        image.startLoad(

                uri,

                new LoadCallback() {

                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(PhotoEditorActivity.this, R.string.cannot_load_image, Toast.LENGTH_LONG).show();
                    }
                });
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    public void onLeftRotate(final View view) {
        image.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
    }

    public void onRightRotate(final View view) {
        image.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
    }

    public void onDoneClicked(final View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        image.startCrop(

                photoUri,

                new CropCallback() {

                    @Override
                    public void onSuccess(final Bitmap cropped) {

                    }

                    @Override
                    public void onError() {

                    }
                },

                new SaveCallback() {

                    @Override
                    public void onSuccess(final Uri outputUri) {
                        final Intent intent = new Intent();
                        intent.putExtra(Extras.EXTRA_URI, outputUri.toString());
                        setResult(RESULT_OK, intent);
                        mProgressBar.setVisibility(View.GONE);
                        finish();
                    }

                    @Override
                    public void onError() {
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(PhotoEditorActivity.this, R.string.cannot_load_image_to_crop, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void onFreeClicked(final View view) {
        image.setCropMode(CropImageView.CropMode.FREE);
    }

    public void onSquareClicked(final View view) {
        image.setCropMode(CropImageView.CropMode.SQUARE);
    }

    public void on43Clicked(final View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_4_3);
    }

    public void on34Clicked(final View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_3_4);
    }

    public void on169Clicked(final View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_16_9);
    }

    public void on916Clicked(final View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_9_16);
    }
}