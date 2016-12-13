package com.maxcriser.cards.barcode;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.maxcriser.cards.R;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    public static final String DBG = "DBG";
    private SurfaceHolder mHolder;
    @SuppressWarnings("deprecation")
    private Camera mCamera;
    @SuppressWarnings("deprecation")
    private Camera.PreviewCallback previewCallback;
    @SuppressWarnings("deprecation")
    private Camera.AutoFocusCallback autoFocusCallback;

    @SuppressWarnings("deprecation")
    public CameraPreview(Context context, Camera camera,
                         Camera.PreviewCallback pPreviewCallback,
                         Camera.AutoFocusCallback pAutoFocusCallback) {
        super(context);
        mCamera = camera;
        previewCallback = pPreviewCallback;
        autoFocusCallback = pAutoFocusCallback;

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (IOException e) {
            Log.d("DBG", "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mHolder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
            Log.d("DBG", e.toString());
        }
        try {
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setPreviewCallback(previewCallback);
            mCamera.startPreview();
            mCamera.autoFocus(autoFocusCallback);
        } catch (Exception e) {
            Log.d(DBG, getContext().getString(R.string.error_starting_camera_preview) + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}