package com.maxcriser.cards.barcode;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.create.Discount;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import static android.view.View.GONE;

public class BarcodeScanner extends AppCompatActivity {

    private Button mCancel;
    private Button mOk;
    private TextView mSolution;
    private FrameLayout mFrameSolution;
    private TextView mBottomText;

    private Camera mCamera;
    private CameraPreview mPreview;
    private Handler autoFocusHandler;

    private ImageScanner scanner;

    private boolean barcodeScanned = false;
    private boolean previewing = true;

    public static final String TAG_BARCODE = "barcode";
    private String scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);

        setContentView(R.layout.barcode_scanner);

        mOk = (Button) findViewById(R.id.button_ok);
        mCancel = (Button) findViewById(R.id.button_cancel);
        mSolution = (TextView) findViewById(R.id.solution_of_scan);
        mFrameSolution = (FrameLayout) findViewById(R.id.frame_solution_of_scan);
        mBottomText = (TextView) findViewById(R.id.bottom_text_barcode_scan);

        initControls();
    }

    private void initControls() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        autoFocusHandler = new Handler();
        mCamera = getCameraInstance();

        scanner = new ImageScanner();
        scanner.setConfig(0, Config.X_DENSITY, 3);
        scanner.setConfig(0, Config.Y_DENSITY, 3);

        mPreview = new CameraPreview(BarcodeScanner.this,
                mCamera, previewCb, autoFocusCB);

        FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
        preview.addView(mPreview);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            releaseCamera();
        }
        return super.onKeyDown(keyCode, event);
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open();
        } catch (Exception e) {
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

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (previewing)
                mCamera.autoFocus(autoFocusCB);
        }
    };

    Camera.PreviewCallback previewCb = new Camera.PreviewCallback() {
        public void onPreviewFrame(byte[] data, Camera camera) {
            Camera.Parameters parameters = camera.getParameters();
            Camera.Size size = parameters.getPreviewSize();

            Image barcode = new Image(size.width, size.height, "Y800");
            barcode.setData(data);

            int result = scanner.scanImage(barcode);

            if (result != 0) {
                previewing = false;
                mCamera.setPreviewCallback(null);
                mCamera.stopPreview();

                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {
                    scanResult = sym.getData().trim();
                    showAlertDialog(scanResult);
                    barcodeScanned = true;
                    break;
                }
            }
        }
    };

    Camera.AutoFocusCallback autoFocusCB = new Camera.AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };


    private void showAlertDialog(String message) {

        mBottomText.setVisibility(GONE);
        mFrameSolution.setVisibility(View.VISIBLE);
        mSolution.setText(message);

        mCancel.setOnClickListener(new onCancelClickListener());

        mOk.setOnClickListener(new onOkClickListener());
    }

    private class onCancelClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
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
        public void onClick(View v) {
            releaseCamera();
            Intent intent = new Intent(BarcodeScanner.this, Discount.class);
            intent.putExtra(TAG_BARCODE, scanResult);
            startActivity(intent);
        }
    }
}