package com.maxcriser.cards.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.adapter.RecyclerAdapterTypes;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.reader.TypesCardsReader;
import com.maxcriser.cards.ui.cards.BankCardsActivity;
import com.maxcriser.cards.ui.cards.DiscountCardsActivity;
import com.maxcriser.cards.ui.cards.TicketsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView typesCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setTypesCards();

        final List<String> types = tcReader.getTypesCards();

        typesCards = (RecyclerView) findViewById(R.id.types_cards_recycler_view);

        RecyclerAdapterTypes adapter = new RecyclerAdapterTypes(this, types, R.layout.item_list);
        typesCards.setAdapter(adapter);
        typesCards.setHasFixedSize(true);
        typesCards.setLayoutManager(new LinearLayoutManager(this));
        typesCards.addOnItemTouchListener(new RecyclerItemClickListener(this, typesCards, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (position == 0) {
                    startActivity(new Intent(MainActivity.this, BankCardsActivity.class));
                } else if (position == 1) {
                    startActivity(new Intent(MainActivity.this, DiscountCardsActivity.class));
                } else if (position == 2) {
                    startActivity(new Intent(MainActivity.this, TicketsActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "Error: Directory does not exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
            }
        }));

        String[] drawer = getResources().getStringArray(R.array.drawer_bar);

        ListView drawerListView = (ListView) findViewById(R.id.list_drawer);
        drawerListView.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, drawer));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void onMenuClicked(View view) {
        // open drawer
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),
                    "Выбран пункт " + position, Toast.LENGTH_SHORT).show();
        }
    }
}