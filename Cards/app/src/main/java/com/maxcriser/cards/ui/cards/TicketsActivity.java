package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.RecyclerAdapterTypes;
import com.maxcriser.cards.reader.TypesCardsReader;
import com.maxcriser.cards.ui.TakePhotoActivity;

import java.util.List;

import static android.view.View.GONE;

public class TicketsActivity extends AppCompatActivity {


    RecyclerView tickets;
    View buttonNewTicket;

    //TODO if this page is empty - fragment_empty_page.xml visibility VISIBLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        buttonNewTicket = findViewById(R.id.new_ticket);
        buttonNewTicket.setOnClickListener(new onButtonNewTickedClickListener());

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setTickets();

        tickets = (RecyclerView) findViewById(R.id.tickets_recycler_view);

        final List<String> myTickets = tcReader.getTickets();

        if (myTickets.isEmpty()) {
            tickets.setVisibility(GONE);
        } else {
            RecyclerAdapterTypes adapter = new RecyclerAdapterTypes(this, myTickets, R.layout.item_list);
            tickets.setAdapter(adapter);
            tickets.setHasFixedSize(true);
            tickets.setLayoutManager(new LinearLayoutManager(this));

            ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    myTickets.remove(viewHolder.getAdapterPosition());
                    tickets.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
                    //TODO remove to database myTickets (viewHolder.getAdapterPosition)
                }
            };

            ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
            itemTouchHelper.attachToRecyclerView(tickets);
        }
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    private class onButtonNewTickedClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(TicketsActivity.this, TakePhotoActivity.class));
        }
    }
}