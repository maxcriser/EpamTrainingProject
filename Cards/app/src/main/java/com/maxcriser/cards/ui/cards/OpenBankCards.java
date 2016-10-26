package com.maxcriser.cards.ui.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.adapter.RecyclerAdapterTypes;
import com.maxcriser.cards.reader.TypesCardsReader;

import java.util.List;

import static android.view.View.GONE;

public class OpenBankCards extends AppCompatActivity {

    RecyclerView openBankCards;

    //TODO if this page is empty - fragment_empty_page.xml visibility VISIBLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_bank_cards);

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setOpenBankCards();

        final List<String> myOpenBankCards = tcReader.getOpenBankCards();

        openBankCards = (RecyclerView) findViewById(R.id.open_bank_cards_recycler_view);

        if (myOpenBankCards.isEmpty()) {
            openBankCards.setVisibility(GONE);
        } else {
            RecyclerAdapterTypes adapter = new RecyclerAdapterTypes(this, myOpenBankCards, R.layout.item_list);
            openBankCards.setAdapter(adapter);
            openBankCards.setHasFixedSize(true);
            openBankCards.setLayoutManager(new LinearLayoutManager(this));

            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    myOpenBankCards.remove(viewHolder.getAdapterPosition());
                    openBankCards.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
                    //TODO remove to database myTickets (viewHolder.getAdapterPosition)
                }
            };

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(openBankCards);
        }
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }
}
