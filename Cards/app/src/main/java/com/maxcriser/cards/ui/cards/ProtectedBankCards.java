package com.maxcriser.cards.ui.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maxcriser.cards.R;

public class ProtectedBankCards extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected_bank_cards);
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }
}