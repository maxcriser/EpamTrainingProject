package com.maxcriser.cards.ui.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.maxcriser.cards.R;

public class TicketsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }
}