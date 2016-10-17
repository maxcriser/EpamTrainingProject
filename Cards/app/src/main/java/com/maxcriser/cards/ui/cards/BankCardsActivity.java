package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.PinProtectedActivity;

public class BankCardsActivity extends AppCompatActivity {

    ListView tcBank;
    String[] typesBankCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_cards);

        typesBankCards = getResources().getStringArray(R.array.type_of_bank_cards);
        tcBank = (ListView) findViewById(R.id.types_bank_cards);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, typesBankCards);

        tcBank.setAdapter(adapter);

        tcBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                if (position == 0) {
                    startActivity(new Intent(BankCardsActivity.this, PinProtectedActivity.class));
                } else {
                    // start open
                }
            }
        });
    }
}
