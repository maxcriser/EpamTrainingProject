package com.maxcriser.cards.ui.create;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.adapter.RecyclerAdapterTypes;
import com.maxcriser.cards.barcode.EAN13CodeBuilder;
import com.maxcriser.cards.reader.PreviewColorReader;
import com.maxcriser.cards.view.EANP72TextView;
import android.widget.Button;

import java.util.List;

public class Discount extends AppCompatActivity {

    EAN13CodeBuilder mEAN13CodeBuilder;
    EANP72TextView mEANP72TextView;
    RecyclerView colorCards;
    String mBarcode = "90014582910436014";

    Button createCard; // database
    String title; // database
    String generateBarcode; // database
    String myColor;
    // Color mColor - putExtra

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_discount);

        mEAN13CodeBuilder = new EAN13CodeBuilder(mBarcode);
        generateBarcode = mEAN13CodeBuilder.getCode();


//        createCard = (Button)findViewById(R.id.new_discount_card);
//        createCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TODO write to database
//            }
//        });

        mEANP72TextView = (EANP72TextView) findViewById(R.id.generate_barcode);
        mEANP72TextView.setText(generateBarcode);
        //getExtra.... mBarcode

        final PreviewColorReader tcReader = PreviewColorReader.getInstance();
        tcReader.setPreviewColors();

        final List<String> previewColors = tcReader.getPreviewColors();

        colorCards = (RecyclerView) findViewById(R.id.preview_discount_cards);

        RecyclerAdapterTypes adapter = new RecyclerAdapterTypes(this, previewColors, R.layout.discount_item);
        colorCards.setAdapter(adapter);
        colorCards.setHasFixedSize(true);
        colorCards.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }
}
