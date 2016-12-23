package com.maxcriser.cards.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.maxcriser.cards.R;

public class ImageViewerDialogBuilder extends AlertDialog {

    private final Context context;
    private final Bitmap mBitmap;

    public ImageViewerDialogBuilder(final Context context, final Bitmap pBitmap) {
        super(context);
        this.context = context;
        this.mBitmap = pBitmap;
    }

    public void startDialog() {
        final LayoutInflater inflater = getLayoutInflater();
        final View layout = inflater.inflate(R.layout.fragment_alertdialog_imageviewer, null);
        final Builder builder = new Builder(context);
        builder.setView(layout);
        builder.setPositiveButton(R.string.ok, new OnClickListener() {

            @Override
            public void onClick(final DialogInterface arg0, final int arg1) {
                dismiss();
            }
        });
        final AlertDialog aDialog = builder.create();
        aDialog.show();
        final ImageView imageView = (ImageView) layout.findViewById(R.id.image_alert_dialog);
        imageView.setImageBitmap(mBitmap);
    }
}