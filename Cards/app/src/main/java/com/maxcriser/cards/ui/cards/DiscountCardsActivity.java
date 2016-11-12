package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.barcode.BarcodeScanner;
import com.maxcriser.cards.constant.StaticPageNames;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.reader.TypesCardsReader;
import com.maxcriser.cards.ui.adapter.CursorDiscountAdapter;
import com.maxcriser.cards.ui.adapter.DiscountCursorLoader;
import com.maxcriser.cards.ui.show.ShowDiscountCard;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import java.util.List;

public class DiscountCardsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int LOADER_DISCOUNT_ID = 1;

    RecyclerView discountCards;
    CursorDiscountAdapter adapter;

    int pixels;

    //TODO if this page is empty - fragment_empty_page.xml visibility VISIBLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_cards);

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(StaticPageNames.DISCOUNT_TITLE);

        final TypesCardsReader tcReader = TypesCardsReader.getInstance();
        tcReader.setDiscountCards();
        final List<String> myDiscountCards = tcReader.getDiscountCards();

        discountCards = (RecyclerView) findViewById(R.id.discount_cards_recycler_view);
        discountCards.setHasFixedSize(true);
        discountCards.setLayoutManager(new LinearLayoutManager(this));
        getSupportLoaderManager().restartLoader(LOADER_DISCOUNT_ID, null, this);

//        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
//                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                myDiscountCards.remove(viewHolder.getAdapterPosition());
//                discountCards.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
////                TODO remove to database myTickets (viewHolder.getAdapterPosition)
//            }
//        };

//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
//        itemTouchHelper.attachToRecyclerView(discountCards);

        discountCards.addOnItemTouchListener(new RecyclerItemClickListener(this, discountCards, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(DiscountCardsActivity.this, ShowDiscountCard.class));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));




//        discountCards.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    float height = recyclerView.getLayoutManager().getHeight();
//                    int posit = (int) Math.floor(pixels / height);
//                    if ((pixels % height) > (height / 2)) {
//                        posit += 1;
//                    }
//                    recyclerView.smoothScrollBy(0, (int) Math.floor(posit * height - pixels));
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                pixels += dy;
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getSupportLoaderManager().getLoader(LOADER_DISCOUNT_ID) != null) {
            getSupportLoaderManager().getLoader(LOADER_DISCOUNT_ID).forceLoad();
        }
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onAddNewClicked(View view) {
        Intent intent = new Intent(this, BarcodeScanner.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DiscountCursorLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter = new CursorDiscountAdapter(data, this, R.layout.discount_item);
        discountCards.swapAdapter(adapter, true);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        discountCards.swapAdapter(null, true);
    }
}
