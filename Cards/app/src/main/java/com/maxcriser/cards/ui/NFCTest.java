package com.maxcriser.cards.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.display_item.BankCardActivity;
import com.maxcriser.cards.utils.AlertImageViewer;
import com.maxcriser.cards.utils.AlertNfcExample;

public class NFCTest extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_on_nfc_page);
        AlertNfcExample dialog = new AlertNfcExample(this);
        dialog.startDialog();
    }
}