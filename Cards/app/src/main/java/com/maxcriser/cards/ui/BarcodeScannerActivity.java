package com.maxcriser.cards.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.maxcriser.cards.R;
import com.maxcriser.cards.barcode.CameraPreview;
import com.maxcriser.cards.ui.create_item.CreateDiscountActivity;
import com.maxcriser.cards.view.text_view.RobotoThin;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import static android.view.View.GONE;

public class BarcodeScannerActivity extends AppCompatActivity {

    public static final String TAG_BARCODE = "barcode";
    private static final String Y_800 = "Y800";
    private RobotoThin mCancel;
    private RobotoThin mOk;
    private RobotoThin mSolution;
    private RobotoThin mBottomText;
    private FrameLayout mFrameSolution;
    @SuppressWarnings("deprecation")
    private Camera mCamera;
    private Handler autoFocusHandler;
    private ImageScanner scanner;
    private boolean barcodeScanned;
    private boolean previewing = true;
    private String scanResult;
    private FrameLayout preview;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        setContentView(R.layout.activity_barcode_scanner);

        initViews();
        initControls();
    }

    private void initViews() {
        preview = (FrameLayout) findViewById(R.id.cameraPreview);
        mOk = (RobotoThin) findViewById(R.id.button_ok);
        mCancel = (RobotoThin) findViewById(R.id.button_cancel);
        mSolution = (RobotoThin) findViewById(R.id.solution_of_scan);
        mFrameSolution = (FrameLayout) findViewById(R.id.frame_solution_of_scan);
        mBottomText = (RobotoThin) findViewById(R.id.bottom_text_barcode_scan);
    }

    private void initControls() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();

        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        final CameraPreview preview1 = new CameraPreview(this,
                mCamera, previewCb, autoFocusCB);

        preview.addView(preview1);
    }


    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            releaseCamera();
        }
        return super.onKeyDown(keyCode, event);
    }

    @SuppressWarnings("deprecation")
    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (final Exception e) {
//            Toast.makeText(this, "Camera not available", Toast.LENGTH_SHORT).show();
        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            previewing = false;
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private final Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing) {
                mCamera.autoFocus(autoFocusCB);
            }
        }
    };

    @SuppressWarnings("deprecation")
    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(final byte[] data, final Camera camera) {
            final Camera.Parameters parameters = camera.getParameters();
            final Camera.Size size = parameters.getPreviewSize();

            final Image barcode = new Image(size.width, size.height, Y_800);
            barcode.setData(data);

            final int result = scanner.scanImage(barcode);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                final SymbolSet syms = scanner.getResults();
                for (final Symbol sym : syms) {
                    scanResult = sym.getData().trim();
                    showAlertDialog(scanResult);
                    barcodeScanned = true;
                    break;
                }
            }
        }
    };

    @SuppressWarnings("deprecation")
    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(final boolean success, final Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };


    private void showAlertDialog(final CharSequence message) {

        mBottomText.setVisibility(GONE);
        mFrameSolution.setVisibility(View.VISIBLE);
        mSolution.setText(message);

        mCancel.setOnClickListener(new onCancelClickListener());

        mOk.setOnClickListener(new onOkClickListener());
    }

    private class onCancelClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
            if (barcodeScanned) {
                mBottomText.setVisibility(View.VISIBLE);
                mFrameSolution.setVisibility(View.GONE);

                barcodeScanned = false;
                mCamera.setPreviewCallback(previewCb);
                mCamera.startPreview();
                previewing = true;
                mCamera.autoFocus(autoFocusCB);
            }
        }
    }

    private class onOkClickListener implements View.OnClickListener {
        @Override
        public void onClick(final View v) {
//            releaseCamera();
            final Intent intent = new Intent(BarcodeScannerActivity.this, CreateDiscountActivity.class);
            intent.putExtra(TAG_BARCODE, scanResult);
            startActivity(intent);
        }
    }
}