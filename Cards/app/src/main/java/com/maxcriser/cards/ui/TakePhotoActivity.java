package com.maxcriser.cards.ui;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.maxcriser.cards.R;
import com.maxcriser.cards.util.ViewSetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TakePhotoActivity extends Activity implements SurfaceHolder.Callback,
        Camera.PictureCallback {

    private Camera camera;
    private SurfaceView preview;
    private ViewSetter mViewSetter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_takephoto);

        FrameLayout frameEditor = (FrameLayout) findViewById(R.id.frame_take_photo);
        preview = (SurfaceView) findViewById(R.id.surface_take_photo);
        SurfaceHolder surfaceHolder = preview.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        mViewSetter = new ViewSetter(this);
        mViewSetter.setBackgroundColor(Color.TRANSPARENT);
        mViewSetter.setOnTouchListener(mViewSetter);
        frameEditor.addView(mViewSetter, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);

        ImageButton shotBtn = (ImageButton) findViewById(R.id.btn_take_photo);
        shotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        camera.takePicture(null, null, null, TakePhotoActivity.this);
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        camera = Camera.open();
        camera.setDisplayOrientation(90);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (camera != null) {
            camera.setPreviewCallback(null);
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Size previewSize = camera.getParameters().getPreviewSize();
        float aspect = (float) previewSize.width / previewSize.height;

        int previewSurfaceWidth = preview.getWidth();

        LayoutParams lp = preview.getLayoutParams();

        // здесь корректируем размер отображаемого preview, чтобы не было
        // искажений

//        camera.setDisplayOrientation(0);
        lp.width = previewSurfaceWidth;
        lp.height = (int) (previewSurfaceWidth / aspect);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        lp.height = height;
        lp.width = width;

        preview.setLayoutParams(lp);
        camera.startPreview();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void onPictureTaken(byte[] paramArrayOfByte, Camera camera) {
        try {
            String base = Environment.getExternalStorageDirectory() + "/img/";
            File saveDir = new File(base);

            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }

            String tmpFile = base + "tmp.jpg";
            FileOutputStream tos = new FileOutputStream(tmpFile);
            tos.write(paramArrayOfByte);
            tos.flush();
            tos.close();

            cropFile(new File(tmpFile),
                    new File(base + String.valueOf(System.currentTimeMillis()) + ".jpg"),
                    mViewSetter.getFramingRect());

        } catch (Exception e) {
            e.printStackTrace();
        }

        camera.startPreview();
    }

    private void cropFile(File in, File out, Rect rect) {
            try {
            BitmapFactory.Options o = new BitmapFactory.Options();

            Bitmap inb = BitmapFactory.decodeStream(new FileInputStream(in), null, o);

            Matrix matrix = new Matrix();
            matrix.postRotate(90);

            inb = Bitmap.createBitmap(inb, 0, 0, inb.getWidth(), inb.getHeight(), matrix, true);
//            Bitmap outb = Bitmap.createBitmap(inb, rect.bottom, rect.top, rect.width(), rect.height()   );
                Bitmap outb = inb;
            FileOutputStream os = new FileOutputStream(out);
//            outb.compress(Bitmap.CompressFormat.JPEG, 85, os);
            inb.compress(Bitmap.CompressFormat.JPEG, 85, os);
            os.flush();
            os.close();
            in.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}