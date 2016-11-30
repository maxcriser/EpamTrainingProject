package com.maxcriser.cards.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.cards.DiscountCardsActivity;
import com.maxcriser.cards.ui.cards.NFCCardsActivity;
import com.maxcriser.cards.ui.cards.TicketsActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayout lv;
    DrawerLayout drawerLayout;
    CardView credit;
    CardView discount;
    CardView tickets;
    CardView nfc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        credit = (CardView) findViewById(R.id.main_credit_card);
        discount = (CardView) findViewById(R.id.main_discount_card);
        tickets = (CardView) findViewById(R.id.main_tickets_card);
        nfc = (CardView) findViewById(R.id.main_nfc_card);

        credit.setOnClickListener(new onClickListener());
        discount.setOnClickListener(new onClickListener());
        tickets.setOnClickListener(new onClickListener());
        nfc.setOnClickListener(new onClickListener());

        String[] drawer = getResources().getStringArray(R.array.drawer_bar);
        lv = (LinearLayout) findViewById(R.id.left_drawer);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ListView drawerListView = (ListView) findViewById(R.id.list_drawer);
        drawerListView.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, drawer));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
    }

    public void onMenuClicked(View view) {
        drawerLayout.openDrawer(lv);
    }

    public void onToolbarClicked(View view) {
    }

    private class DrawerItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),
                    "Выбран пункт " + position, Toast.LENGTH_SHORT).show();
        }
    }

    private class onClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_credit_card:
                    startActivity(new Intent(MainActivity.this, LockerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                    break;
                case R.id.main_discount_card:
                    startActivity(new Intent(MainActivity.this, DiscountCardsActivity.class));
                    break;
                case R.id.main_nfc_card:
                    startActivity(new Intent(MainActivity.this, NFCCardsActivity.class));
                    break;
                case R.id.main_tickets_card:
                    startActivity(new Intent(MainActivity.this, TicketsActivity.class));
                    break;
            }
        }
    }
}