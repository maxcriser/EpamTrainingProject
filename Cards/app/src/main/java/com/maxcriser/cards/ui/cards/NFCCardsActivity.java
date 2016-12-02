package com.maxcriser.cards.ui.cards;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.reader.deleteTypesCardsReader;
import com.maxcriser.cards.ui.adapter.ItemsRecyclerAdapter;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import java.util.List;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.NFC_TITLE;

public class NFCCardsActivity extends AppCompatActivity {

    RecyclerView viewNFCItems;
    CardView toolbarBack;
    CardView toolbarSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc_cards);

        toolbarBack = (CardView) findViewById(R.id.card_view_toolbar_back);
        toolbarSearch = (CardView) findViewById(R.id.card_view_toolbar_search);

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(NFC_TITLE);

        final deleteTypesCardsReader tcReader = deleteTypesCardsReader.getInstance();
        tcReader.setNfcCards();

        final List<String> myNFCItems = tcReader.getNfcCards();

        viewNFCItems = (RecyclerView) findViewById(R.id.nfc_items_recycler_view);

        ItemsRecyclerAdapter adapter = new ItemsRecyclerAdapter(this, myNFCItems, R.layout.item_list);
        viewNFCItems.setAdapter(adapter);
        viewNFCItems.setHasFixedSize(true);
        viewNFCItems.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                myNFCItems.remove(viewHolder.getAdapterPosition());
                viewNFCItems.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
                //TODO remove to database myTickets (viewHolder.getAdapterPosition)
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(viewNFCItems);

        viewNFCItems.addOnItemTouchListener(new RecyclerItemClickListener(this, viewNFCItems, new RecyclerItemClickListener.OnItemClickListener() {
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

    public void onToolbarBackClicked(View view) {
        viewNFCItems.smoothScrollToPosition(0);
    }

    public void onSearchClicked(View view) {
        toolbarBack.setVisibility(GONE);
        toolbarSearch.setVisibility(View.VISIBLE);
    }

    public void onBackSearchClicked(View view) {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(GONE);
    }
}
