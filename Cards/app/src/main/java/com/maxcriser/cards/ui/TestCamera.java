package com.maxcriser.cards.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;
import com.maxcriser.cards.R;

import java.io.File;

public class TestCamera extends Activity {

    CropImageView mCropView;
    private ImageView mImageView;
    public final String APP_TAG = "MyCustomApp";
    public final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034;
    public String photoFileName = "photo.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testcamera);
    }

    public void onClick(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getPhotoFileUri(photoFileName)); // set the image file name

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri takenPhotoUri = getPhotoFileUri(photoFileName);

                mCropView = (CropImageView) findViewById(R.id.cropImageView);
                mCropView.setCropMode(CropImageView.CropMode.RATIO_4_3);
                mCropView.setHandleSizeInDp(2);

                mCropView.startLoad(

                        takenPhotoUri,

                        new LoadCallback() {
                            @Override
                            public void onSuccess() {}

                            @Override
                            public void onError() {}
                        });

//                mCropView.startCrop(
//
//                        takenPhotoUri,
//
//                        new CropCallback() {
//                            @Override
//                            public void onSuccess(Bitmap cropped) {}
//
//                            @Override
//                            public void onError() {}
//                        },
//
//                        new SaveCallback() {
//                            @Override
//                            public void onSuccess(Uri outputUri) {}
//
//                            @Override
//                            public void onError() {}
//                        }
//                );

//                Bitmap takenImage = BitmapFactory.decodeFile(takenPhotoUri.getPath());
//                ImageView ivPreview = (ImageView) findViewById(R.id.picture);
//                ivPreview.setImageURI(takenPhotoUri);
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
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

    private boolean isExternalStorageAvailable() {
        String state = Environment.getExternalStorageState();
        return state.equals(Environment.MEDIA_MOUNTED);
    }

    public void onRotate(View view) {
        mCropView.rotateImage(CropImageView.RotateDegrees.ROTATE_90D); // rotate clockwise by 90 degrees
    }
}