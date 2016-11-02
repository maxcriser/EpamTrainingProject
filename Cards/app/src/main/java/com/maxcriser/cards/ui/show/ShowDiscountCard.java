package com.maxcriser.cards.ui.show;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.view.TextViews.EANP72TextView;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

public class ShowDiscountCard extends Activity {

    RobotoRegularTextView titleView;
    EANP72TextView barcodeView; // с базы придёт уже готовый geeratebarcode();
    String id;
    String title;
    String barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_discount);

//        TODO putExtra from DCA
//        Intent barcodeIntent = getIntent();
//        id = barcodeIntent.getStringExtra("idCard");

        titleView = (RobotoRegularTextView)findViewById(R.id.title_show_discount);
        titleView.setText(title);
        barcodeView = (EANP72TextView) findViewById(R.id.show_barcode);
        barcodeView.setText(barcode);

        WindowManager.LayoutParams layoutParam = getWindow().getAttributes();
        layoutParam.screenBrightness = 1.0f;
        getWindow().setAttributes(layoutParam);
        Toast.makeText(this, layoutParam.toString(), Toast.LENGTH_LONG).show();
    }
}