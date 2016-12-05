package com.maxcriser.cards.ui.show;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.maxcriser.cards.R;

public class ShowBankCard extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_discount);
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }
}