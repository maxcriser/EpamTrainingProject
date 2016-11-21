package com.maxcriser.cards.ui.cards;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.barcode.BarcodeScanner;
import com.maxcriser.cards.constant.StaticPageNames;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.ui.adapter.CursorDiscountAdapter;
import com.maxcriser.cards.ui.adapter.DiscountCursorLoader;
import com.maxcriser.cards.ui.show.ShowDiscountCard;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import static android.view.View.GONE;

public class DiscountCardsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int LOADER_DISCOUNT_ID = 1;

    ProgressBar mProgressBarDiscount;
    DatabaseHelper dbHelper;
    RecyclerView discountCards;
    LinearLayoutManager mLayoutManager;
    CursorDiscountAdapter adapter;
    CardView toolbarBack;
    CardView toolbarSearch;
    EditText searchEdit;

    public static final String EXTRA_DISCOUNT_ID = "discount_id_extra";
    public static final String EXTRA_DISCOUNT_TITLE = "discount_title_extra";
    public static final String EXTRA_DISCOUNT_BARCODE = "discount_barcode_extra";
    public static final String EXTRA_DISCOUNT_COLOR = "discount_color_extra";

    int pixels;

    //TODO if this page is empty - fragment_empty_page.xml visibility VISIBLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_cards);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        searchEdit.addTextChangedListener(new SearchTextListener());

        toolbarBack = (CardView) findViewById(R.id.card_view_toolbar_back);
        toolbarSearch = (CardView) findViewById(R.id.card_view_toolbar_search);

        RobotoRegularTextView title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        title.setText(StaticPageNames.DISCOUNT_TITLE);

        dbHelper = DatabaseHelper.getInstance(this, 1);

        discountCards = (RecyclerView) findViewById(R.id.discount_cards_recycler_view);
        discountCards.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        discountCards.setLayoutManager(mLayoutManager);
        getSupportLoaderManager().restartLoader(LOADER_DISCOUNT_ID, null, this);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                TextView cardTitle = (TextView) viewHolder.itemView.findViewById(R.id.title_main_cards);
                Integer id = (Integer) cardTitle.getTag();
                dbHelper.delete(ModelDiscountCards.class, null, ModelDiscountCards.DISCOUNT_ID + " = ?", String.valueOf(id));
//                TODO FIX incorrect animation delete
//                discountCards.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
                onResume();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(discountCards);

        discountCards.addOnItemTouchListener(new RecyclerItemClickListener(this, discountCards, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView mTitle = (TextView) view.findViewById(R.id.title_main_cards);
                int id = (Integer) mTitle.getTag();

                dbHelper.query(new OnResultCallback<Cursor, Void>() {
                    @Override
                    public void onSuccess(Cursor pCursor) {
                        if (pCursor.moveToFirst()) {
                            String cardID = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_ID));
                            String cardTitle = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_TITLE));
                            String cardBarcode = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_BARCODE));
                            String cardColor = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.DISCOUNT_BACKGROUND_COLOR));

                            Intent intent = new Intent(DiscountCardsActivity.this, ShowDiscountCard.class);
                            intent.putExtra(EXTRA_DISCOUNT_ID, cardID);
                            intent.putExtra(EXTRA_DISCOUNT_TITLE, cardTitle);
                            intent.putExtra(EXTRA_DISCOUNT_BARCODE, cardBarcode);
                            intent.putExtra(EXTRA_DISCOUNT_COLOR, cardColor);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Exception pE) {
                        Toast.makeText(DiscountCardsActivity.this, "Cannot find card", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgressChanged(Void pVoid) {
                    }
                }, "*", ModelDiscountCards.class, "WHERE "
                        + ModelDiscountCards.DISCOUNT_ID + " = ?", String.valueOf(id));
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
        LoaderManager supportLoaderManager = getSupportLoaderManager();
        if (supportLoaderManager.getLoader(LOADER_DISCOUNT_ID) != null) {
            supportLoaderManager.getLoader(LOADER_DISCOUNT_ID).forceLoad();
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
        LinearLayout linearEmpty = (LinearLayout) findViewById(R.id.empty_page_id_fragment);
        if (data.getCount() == 0) {
            linearEmpty.setVisibility(View.VISIBLE);
            discountCards.setVisibility(GONE);
        } else {
            linearEmpty.setVisibility(GONE);
            discountCards.setVisibility(View.VISIBLE);
        }
        adapter = new CursorDiscountAdapter(data, this, R.layout.item_discount);
        discountCards.swapAdapter(adapter, true);

//        mProgressBarDiscount = (ProgressBar) findViewById(R.id.progressbar_discount);
//        mProgressBarDiscount.setVisibility(GONE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        discountCards.swapAdapter(null, true);
    }

    public void onToolbarBackClicked(View view) {
        discountCards.smoothScrollToPosition(0);
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
