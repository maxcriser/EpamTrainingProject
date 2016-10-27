package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;

import com.maxcriser.cards.R;
import com.maxcriser.cards.adapter.RecyclerAdapterTypes;
import com.maxcriser.cards.barcode.BarcodeScanner;
import com.maxcriser.cards.reader.TypesCardsReader;
import com.maxcriser.cards.ui.create.Discount;

import java.util.List;

public class DiscountCardsActivity extends AppCompatActivity {

    RecyclerView discountCards;
    Button newDiscountCard;

    //TODO if this page is empty - fragment_empty_page.xml visibility VISIBLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_cards);

        newDiscountCard = (Button) findViewById(R.id.new_discount_card);
        newDiscountCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Discount.class)
                        .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setDiscountCards();

        final List<String> myDiscountCards = tcReader.getDiscountCards();

        discountCards = (RecyclerView) findViewById(R.id.discount_cards_recycler_view);

        RecyclerAdapterTypes adapter = new RecyclerAdapterTypes(this, myDiscountCards,R.layout.discount_item);

        discountCards.setAdapter(adapter);
        discountCards.setHasFixedSize(true);
        discountCards.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                myDiscountCards.remove(viewHolder.getAdapterPosition());
                discountCards.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
                //TODO remove to database myTickets (viewHolder.getAdapterPosition)
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(discountCards);
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }
}
