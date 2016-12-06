package com.maxcriser.cards.ui.cards;

import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.ui.BarcodeScanner;
import com.maxcriser.cards.ui.adapter.CursorDiscountAdapter;
import com.maxcriser.cards.ui.adapter.DiscountCursorLoader;
import com.maxcriser.cards.ui.show.ShowDiscountCard;
import com.maxcriser.cards.view.TextViews.RobotoRegularTextView;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.DISCOUNT_TITLE;

public class DiscountCardsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    DatabaseHelper dbHelper;

    RecyclerView discountCards;
    LinearLayoutManager mLayoutManager;
    CursorDiscountAdapter adapter;
    LinearLayout linearEmpty;
    CardView toolbarBack;
    CardView toolbarSearch;
    EditText searchEdit;
    TextView noResultFor;
    ImageView clearSearch;
    RobotoRegularTextView title;
    private String searchText = "";
    public static final String EXTRA_DISCOUNT_ID = "discount_id_extra";
    public static final int LOADER_DISCOUNT_ID = 1;
    public static final String EXTRA_DISCOUNT_TITLE = "discount_title_extra";
    public static final String EXTRA_DISCOUNT_BARCODE = "discount_barcode_extra";
    public static final String EXTRA_DISCOUNT_COLOR = "discount_color_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discount_cards);

        getSupportLoaderManager().restartLoader(LOADER_DISCOUNT_ID, null, this);
        noResultFor = (TextView) findViewById(R.id.frame_no_results_for);
        toolbarBack = (CardView) findViewById(R.id.card_view_toolbar_back);
        discountCards = (RecyclerView) findViewById(R.id.discount_cards_recycler_view);
        toolbarSearch = (CardView) findViewById(R.id.card_view_toolbar_search);
        clearSearch = (ImageView) findViewById(R.id.clearSearch);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        title = (RobotoRegularTextView) findViewById(R.id.title_toolbar);
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

            }

            @Override
            public void onTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

            }

            @Override
            public void afterTextChanged(Editable pEditable) {
                if (!pEditable.toString().equals("")) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else {
                    clearSearch.setVisibility(View.GONE);
                }
                searchText = pEditable.toString();
                getSupportLoaderManager().restartLoader(LOADER_DISCOUNT_ID, null, DiscountCardsActivity.this);
            }
        });


        title.setText(DISCOUNT_TITLE);

        dbHelper = DatabaseHelper.getInstance(this, 1);

        discountCards.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        discountCards.setLayoutManager(mLayoutManager);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                discountCards.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
                TextView cardTitle = (TextView) viewHolder.itemView.findViewById(R.id.title_main_cards);
                Integer id = (Integer) cardTitle.getTag();
                dbHelper.delete(ModelDiscountCards.class, null, ModelDiscountCards.DISCOUNT_ID + " = ?", String.valueOf(id));
                // TODO FIX incorrect animation delete
//                onResume();
                getSupportLoaderManager().restartLoader(LOADER_DISCOUNT_ID, null, DiscountCardsActivity.this);
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
    }

    @Override
    public void onBackPressed() {
        if (toolbarSearch.getVisibility() == View.VISIBLE) {
            onBackSearchClicked(null);
        } else {
            super.onBackPressed();
        }
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
        Intent intent = new Intent(this,
                BarcodeScanner.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new DiscountCursorLoader(this, searchText);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, final Cursor data) {
        linearEmpty = (LinearLayout) findViewById(R.id.empty_page_id_fragment);
        if (data.getCount() == 0) {
            if (!searchText.equals("")) {
                noResultFor.setText("Say what?! No result for '" + searchText + "'");
                noResultFor.setVisibility(View.VISIBLE);
            } else {
                linearEmpty.setVisibility(View.VISIBLE);
            }
            discountCards.setVisibility(GONE);
        } else {
            noResultFor.setVisibility(GONE);
            linearEmpty.setVisibility(GONE);
            discountCards.setVisibility(View.VISIBLE);
        }

        adapter = new CursorDiscountAdapter(data, DiscountCardsActivity.this, R.layout.item_discount);
        discountCards.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        discountCards.swapAdapter(null, true);
    }

    public void onToolbarBackClicked(View view) {
        Toast.makeText(this, "" + adapter.getItemCount(), Toast.LENGTH_LONG).show();
        discountCards.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    public void onSearchClicked(View view) {
        toolbarSearch.setVisibility(View.VISIBLE);
        toolbarBack.setVisibility(GONE);
        searchEdit.setText("");
        searchEdit.clearFocus();
        searchEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEdit, InputMethodManager.SHOW_IMPLICIT);
    }

    public void onBackSearchClicked(View view) {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(GONE);
        searchEdit.setText("");
        searchText = searchEdit.getText().toString();
        getSupportLoaderManager().restartLoader(LOADER_DISCOUNT_ID, null, DiscountCardsActivity.this);
    }

    public void onClearSearchClicked(View view) {
        searchEdit.setText("");
    }
}
