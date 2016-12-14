package com.maxcriser.cards.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.Extras;

import java.io.File;

import static com.maxcriser.cards.constant.Constants.Requests.CAPTURE_IMAGE_BACK;
import static com.maxcriser.cards.constant.Constants.Requests.CAPTURE_IMAGE_FRONT;

public class PhotoEditorActivity extends AppCompatActivity {

    private final String APP_TAG = "thecrisertakephoto";
    private String photoFileName = "photo.jpg";
    private CropImageView image;
    private FrameLayout mProgressBar;
    private Uri photoUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        setContentView(R.layout.activity_photo_editor);
        initViews();
        image.setInitialFrameScale(0.75f);
        image.setCropMode(CropImageView.CropMode.FREE);
//        setCustomRatio(int ratioX, int ratioY);
        //FIT_IMAGE, RATIO_4_3, RATIO_3_4, SQUARE(default), RATIO_16_9, RATIO_9_16, FREE, CUSTOM, CIRCLE, CIRCLE_SQUARE
        image.setHandleSizeInDp(0);
        image.setFrameStrokeWeightInDp(1);
        image.setGuideStrokeWeightInDp(1);
        image.setTouchPaddingInDp(24);
        image.setMinFrameSizeInDp(150);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String uri_Str = bundle.getString(Extras.EXTRA_URI);
            photoUri = Uri.parse(uri_Str);
            startCrop(photoUri);
        } else {
            finish();
        }
    }

    void initViews() {
        image = (CropImageView) findViewById(R.id.cropImageView);
        mProgressBar = (FrameLayout) findViewById(R.id.frame_progressbar);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_FRONT || requestCode == CAPTURE_IMAGE_BACK) {
            if (resultCode == RESULT_OK) {
                photoUri = getPhotoFileUri(photoFileName);
//                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
                startCrop(photoUri);
            } else {
                Toast.makeText(this, R.string.picture_wasnt_taken, Toast.LENGTH_SHORT).show();
            }
        }
    }

    void startCrop(Uri uri) {
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

    public void onPhotoAgainClicked(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_FRONT);
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
                Log.d(APP_TAG, getResources().getString(R.string.filed_to_create_directory));
            }
            return Uri.fromFile(new File(mediaStorageDir.getPath() + File.separator + fileName));
        }
        return null;
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onLeftRotate(View view) {
        image.rotateImage(CropImageView.RotateDegrees.ROTATE_M90D);
    }

    public void onRightRotate(View view) {
        image.rotateImage(CropImageView.RotateDegrees.ROTATE_90D);
    }

    public void onDoneClicked(View view) {
        mProgressBar.setVisibility(View.VISIBLE);
        image.startCrop(

                photoUri,

                new CropCallback() {
                    @Override
                    public void onSuccess(Bitmap cropped) {

                    }

                    @Override
                    public void onError() {

                    }
                },

                new SaveCallback() {
                    @Override
                    public void onSuccess(Uri outputUri) {
                        Intent intent = new Intent();
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

    public void onFreeClicked(View view) {
        image.setCropMode(CropImageView.CropMode.FREE);
    }

    public void onSquareClicked(View view) {
        image.setCropMode(CropImageView.CropMode.SQUARE);
    }

    public void on43Clicked(View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_4_3);
    }

    public void on34Clicked(View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_3_4);
    }

    public void on169Clicked(View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_16_9);
    }

    public void on916Clicked(View view) {
        image.setCropMode(CropImageView.CropMode.RATIO_9_16);
    }
}