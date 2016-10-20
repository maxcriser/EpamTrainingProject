package com.maxcriser.cards.ui.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maxcriser.cards.R;


public class ProtectedBankCards extends AppCompatActivity {

//    Camera camera;
//    SurfaceView preview;

//    View btnAddNewCard;


    //TODO if this page is empty - fragment_empty_page.xml visibility VISIBLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected_bank_cards);

//        btnAddNewCard = findViewById(R.id.add_new_card);
//        btnAddNewCard.setOnClickListener(new onAddNewCardListener());

//        SurfaceHolder surfaceHolder;
//        surfaceHolder = preview.getHolder();
//        try {
//            camera.setPreviewDisplay(surfaceHolder);
//        } catch (IOException pE) {
//            pE.printStackTrace();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

//        camera = Camera.open();
    }

    @Override
    protected void onPause() {
        super.onPause();

//        if (camera != null) {
//            camera.setPreviewCallback(null);
//            camera.stopPreview();
//            camera.release();
//            camera = null;
//        }
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    private class onAddNewCardListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
//            camera.startPreview();
            //           void takePicture(Camera.ShutterCalback shutter, Camera.PictureCallback raw, Camera.PictureCallback postview, Camera.PictureCallback jpg);
        }
    }
}