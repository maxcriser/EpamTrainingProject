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
import android.widget.FrameLayout;
import android.widget.Toast;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.maxcriser.cards.R;

import java.io.File;

public class PhotoEditor extends AppCompatActivity {

    public final String APP_TAG = "thecrisertakephoto";
    public String photoFileName = "photo.jpg";
    public final static int CAPTURE_IMAGE_FRONT = 1001;
    public final static int CAPTURE_IMAGE_BACK = 1010;
    CropImageView image;
    FrameLayout mProgressBar;
    Uri photoUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_editor);
        initViews();
        image.setCropMode(CropImageView.CropMode.FREE);
        image.setInitialFrameScale(0.75f);
//        setCustomRatio(int ratioX, int ratioY);
        //FIT_IMAGE, RATIO_4_3, RATIO_3_4, SQUARE(default), RATIO_16_9, RATIO_9_16, FREE, CUSTOM, CIRCLE, CIRCLE_SQUARE
        image.setHandleSizeInDp(0);
        image.setFrameStrokeWeightInDp(2);
        image.setGuideStrokeWeightInDp(1);
        image.setTouchPaddingInDp(24);
        image.setMinFrameSizeInDp(150);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String uri_Str = bundle.getString("uri");
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
                Uri takenPhotoUri = getPhotoFileUri(photoFileName);
                photoUri = takenPhotoUri;
//                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
                startCrop(photoUri);

            } else {
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(PhotoEditor.this, "Can not load image, please try again", Toast.LENGTH_LONG).show();
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
                Log.d(APP_TAG, "failed to create directory");
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
                        photoUri = outputUri;
                        mProgressBar.setVisibility(View.GONE);
                        Intent intent = new Intent();
                        intent.putExtra("uri", photoUri.toString());
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    @Override
                    public void onError() {
                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(PhotoEditor.this, "Can not load image to crop, please try again", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}