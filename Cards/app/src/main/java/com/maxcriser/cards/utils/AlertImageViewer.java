package com.maxcriser.cards.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.Constants;

public class AlertImageViewer extends AlertDialog {

    private Context context;
    private Bitmap mBitmap;
    private int width;
    private int height;
    private int bitmapHeight;
    private int bitmapWidth;
//    private Bitmap resizedBitmap;


    public AlertImageViewer(Context context, Bitmap pBitmap, int width, int height) {
        super(context);
        this.context = context;
        this.mBitmap = pBitmap;
        this.width = width;
        this.height = height;
//        bitmapHeight = mBitmap.getHeight();
//        bitmapWidth = mBitmap.getHeight();
//        while (bitmapHeight / 2 > height || bitmapWidth / 2 > width) {
//            bitmapHeight /= 2;
//            bitmapWidth /= 2;
//        }
//        resizedBitmap = Bitmap.createScaledBitmap(pBitmap, bitmapWidth, bitmapHeight, true);
    }

    public void startDialog() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.fragment_alertdialog_imageviewer, null);
        Builder builder = new Builder(context);
        builder.setView(layout);
        builder.setPositiveButton("Ok", new OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                dismiss();
            }
        });
        AlertDialog aDialog = builder.create();
        aDialog.show();
//        aDialog.getWindow().setLayout(bitmapWidth, bitmapHeight);
        ImageView imageView = (ImageView) layout.findViewById(R.id.image_alert_dialog);
        imageView.setImageBitmap(mBitmap);
    }
}