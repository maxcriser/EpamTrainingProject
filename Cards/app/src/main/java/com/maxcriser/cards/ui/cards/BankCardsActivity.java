package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.StaticPageNames;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.reader.TypesCardsReader;
import com.maxcriser.cards.ui.adapter.ItemsRecyclerAdapter;
import com.maxcriser.cards.ui.create.Bank;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import java.util.List;

public class BankCardsActivity extends AppCompatActivity {

    RecyclerView viewBankCards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_cards);

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(StaticPageNames.NEW_BANK_TITLE);

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setBankCards();

        final List<String> myBankCards = tcReader.getBankCards();

        viewBankCards = (RecyclerView) findViewById(R.id.types_bank_cards_recycler_view);

        ItemsRecyclerAdapter adapter = new ItemsRecyclerAdapter(this, myBankCards, R.layout.item_list_bank);
        viewBankCards.setAdapter(adapter);
        viewBankCards.setHasFixedSize(true);
        viewBankCards.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                myBankCards.remove(viewHolder.getAdapterPosition());
                viewBankCards.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
                //TODO remove to database myTickets (viewHolder.getAdapterPosition)
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(viewBankCards);

        viewBankCards.addOnItemTouchListener(new RecyclerItemClickListener(this, viewBankCards, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                TODO GO TO my card
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));

    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onAddNewClicked(View view) {
        // TODO start camera
        startActivity(new Intent(BankCardsActivity.this, Bank.class));
//        Intent intent = new Intent(BankCardsActivity.this, Bank.class);
//        startActivity(intent);
    }
}