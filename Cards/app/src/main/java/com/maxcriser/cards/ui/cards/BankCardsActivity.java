package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.adapter.RecyclerAdapterTypes;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.reader.TypesCardsReader;
import com.maxcriser.cards.ui.PinProtectedActivity;

import java.util.List;

public class BankCardsActivity extends AppCompatActivity {

    RecyclerView viewTypesBankCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_cards);

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setTypesBankCards();

        final List<String> types = tcReader.getTypesBankCards();

        viewTypesBankCards = (RecyclerView) findViewById(R.id.types_bank_cards_recycler_view);

        RecyclerAdapterTypes adapter = new RecyclerAdapterTypes(this, types);
        viewTypesBankCards.setAdapter(adapter);
        viewTypesBankCards.setHasFixedSize(true);
        viewTypesBankCards.setLayoutManager(new LinearLayoutManager(this));
        viewTypesBankCards.addOnItemTouchListener(new RecyclerItemClickListener(this,
                viewTypesBankCards,
                new RecyclerItemClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        if (position == 0) {
                            startActivity(new Intent(BankCardsActivity.this, PinProtectedActivity.class).
                                    addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                        } else if (position == 1) {
                            startActivity(new Intent(BankCardsActivity.this, OpenBankCards.class));
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Error: Directory does not exist",
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {

                    }
                }));

    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }
}