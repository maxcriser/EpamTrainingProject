package com.maxcriser.cards.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.reader.TypesCardsReader;
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
        typesCards.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, types));
        typesCards.setOnItemClickListener(new OnItemClickListener());

        String[] drawer = getResources().getStringArray(R.array.drawer_bar);

        ListView drawerListView = (ListView) findViewById(R.id.list_drawer);
        drawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawer));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void onMenuClicked(View view) {
        // open drawer
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position == 0) {
                startActivity(new Intent(MainActivity.this, BankCardsActivity.class));
            } else if (position == 1) {
                startActivity(new Intent(MainActivity.this, DiscountCardsActivity.class));
            } else {
                startActivity(new Intent(MainActivity.this, TicketsActivity.class));
            }
        }
    }


    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),
                    "Выбран пункт " + position, Toast.LENGTH_SHORT).show();
        }
    }
}