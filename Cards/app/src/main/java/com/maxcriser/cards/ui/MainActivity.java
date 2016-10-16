package com.maxcriser.cards.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.maxcriser.cards.R;
import com.maxcriser.cards.TypesCardsReader;
import com.maxcriser.cards.ui.cards.BankCardsActivity;
import com.maxcriser.cards.ui.cards.DiscountCardsActivity;
import com.maxcriser.cards.ui.cards.TicketsActivity;

public class MainActivity extends AppCompatActivity {

    ListView typesCards;
    String[] types;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setTypes();
        types = tcReader.getTypes();

        typesCards = (ListView) findViewById(R.id.types_of_cards);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, types);

        typesCards.setAdapter(adapter);

        typesCards.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();

                if (strText.equalsIgnoreCase(types[0])) {
                    startActivity(new Intent(MainActivity.this, BankCardsActivity.class));
                } else if (strText.equalsIgnoreCase(types[1])) {
                    startActivity(new Intent(MainActivity.this, DiscountCardsActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, TicketsActivity.class));
                }
            }
        });
    }
}