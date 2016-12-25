package com.maxcriser.cards.barcode;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.maxcriser.cards.R;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private final SurfaceHolder mHolder;
    @SuppressWarnings("deprecation")
    private final Camera mCamera;
    @SuppressWarnings("deprecation")
    private final Camera.PreviewCallback previewCallback;
    @SuppressWarnings("deprecation")
    private final Camera.AutoFocusCallback autoFocusCallback;

    @SuppressWarnings("deprecation")
    public CameraPreview(final Context context, final Camera camera,
                         final Camera.PreviewCallback pPreviewCallback,
                         final Camera.AutoFocusCallback pAutoFocusCallback) {
        super(context);
        mCamera = camera;
        previewCallback = pPreviewCallback;
        autoFocusCallback = pAutoFocusCallback;

        mHolder = getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
        } catch (final IOException e) {
            Log.d("DBG", "Error setting camera preview: " + e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(final SurfaceHolder holder, final int format, final int width, final int height) {
        if (mHolder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (final Exception e) {
            Log.d("DBG", e.toString());
        }
        try {
            final int displayOrientation = 90;
            mCamera.setDisplayOrientation(displayOrientation);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setPreviewCallback(previewCallback);
            mCamera.startPreview();
            mCamera.autoFocus(autoFocusCallback);
        } catch (final Exception e) {
            Log.d("DBG", getContext().getString(R.string.error_starting_camera_preview) + e.getMessage());
        }
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder) {

    }
}