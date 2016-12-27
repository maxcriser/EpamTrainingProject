package com.maxcriser.cards.barcode;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.maxcriser.cards.R;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private final Context mContext;
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
        mContext = context;
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
            Toast.makeText(mContext, R.string.error_setting_camera_preview, Toast.LENGTH_LONG).show();
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
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        try {
            final int displayOrientation = 90;
            mCamera.setDisplayOrientation(displayOrientation);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setPreviewCallback(previewCallback);
            mCamera.startPreview();
            mCamera.autoFocus(autoFocusCallback);
        } catch (final Exception e) {
            Toast.makeText(mContext, R.string.error_starting_camera_preview, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void surfaceDestroyed(final SurfaceHolder holder) {

    }
}