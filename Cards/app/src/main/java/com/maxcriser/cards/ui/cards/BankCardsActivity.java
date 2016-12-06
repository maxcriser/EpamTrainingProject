package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.maxcriser.cards.R;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.reader.deleteTypesCardsReader;
import com.maxcriser.cards.ui.adapter.ItemsRecyclerAdapter;
import com.maxcriser.cards.ui.create.Bank;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import java.util.List;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.BANK_TITLE;

public class BankCardsActivity extends AppCompatActivity {

    RecyclerView viewBankCards;
    CardView toolbarBack;
    CardView toolbarSearch;
    EditText searchEdit;
    RobotoRegularTextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_cards);
        initViews();
        searchEdit.addTextChangedListener(new SearchTextListener());
        title.setText(BANK_TITLE);

        final deleteTypesCardsReader tcReader = deleteTypesCardsReader.getInstance();
        tcReader.setBankCards();

        final List<String> myBankCards = tcReader.getBankCards();

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

    private void initViews(){
        searchEdit = (EditText) findViewById(R.id.search_edit);
        toolbarBack = (CardView) findViewById(R.id.card_view_toolbar_back);
        toolbarSearch = (CardView) findViewById(R.id.card_view_toolbar_search);
        title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        viewBankCards = (RecyclerView) findViewById(R.id.types_bank_cards_recycler_view);
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

    public void onToolbarBackClicked(View view) {
        viewBankCards.smoothScrollToPosition(0);
    }

    public void onSearchClicked(View view) {
        toolbarBack.setVisibility(GONE);
        toolbarSearch.setVisibility(View.VISIBLE);
    }

    public void onBackSearchClicked(View view) {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(GONE);
    }

    private class SearchTextListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}