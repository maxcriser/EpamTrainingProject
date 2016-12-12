package com.maxcriser.cards.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.constant.constants;
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.database.models.ModelNFCItems;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.ui.adapter.BankCursorLoader;
import com.maxcriser.cards.ui.adapter.CursorBankAdapter;
import com.maxcriser.cards.ui.adapter.CursorDiscountAdapter;
import com.maxcriser.cards.ui.adapter.DiscountCursorLoader;
import com.maxcriser.cards.ui.create.Bank;
import com.maxcriser.cards.ui.create.Ticket;
import com.maxcriser.cards.ui.show.ShowDiscountCard;
import com.maxcriser.cards.view.TextViews.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.BANK_TITLE;
import static com.maxcriser.cards.constant.constants.DISCOUNT_TITLE;
import static com.maxcriser.cards.constant.constants.TICKETS_TITLE;
import static com.maxcriser.cards.ui.cards.DiscountCardsActivity.EXTRA_DISCOUNT_BARCODE;
import static com.maxcriser.cards.ui.cards.DiscountCardsActivity.EXTRA_DISCOUNT_COLOR;
import static com.maxcriser.cards.ui.cards.DiscountCardsActivity.EXTRA_DISCOUNT_ID;
import static com.maxcriser.cards.ui.cards.DiscountCardsActivity.EXTRA_DISCOUNT_TITLE;

public class ItemsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    String typeItems;
    TextView noResultFor;
    DatabaseHelper dbHelper;
    LinearLayoutManager mLayoutManager;
    RecyclerView recyclerItems;
    CursorBankAdapter bankAdapter;
    CursorDiscountAdapter discountAdapter;
    // TODO: 12.12.2016  Ticket and NFC adapters
    CardView toolbarBack;
    CardView toolbarSearch;
    LinearLayout linearEmpty;
    ImageView clearSearch;
    EditText searchEdit;
    RobotoRegular title;
    private String searchText = "";
    public static final int LOADER_ID = 1;
    Class ModelClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        typeItems = MenuActivity.selectItem;
        initViews();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
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
                getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
            }
        });
        title.setText(typeItems);

        dbHelper = DatabaseHelper.getInstance(this, 1);

        recyclerItems.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        recyclerItems.setLayoutManager(mLayoutManager);

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
                dbHelper.delete(ModelClass, null, ModelBankCards.ID + " = ?", String.valueOf(id));
                getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerItems);

        recyclerItems.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerItems, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView mTitle = (TextView) view.findViewById(R.id.title_main_cards);
                int id = (Integer) mTitle.getTag();
                dbHelper.query(new OnResultCallback<Cursor, Void>() {
                    @Override
                    public void onSuccess(Cursor pCursor) {
                        if (pCursor.moveToFirst()) {
                            if (typeItems.equals(BANK_TITLE)) {

                            } else if (typeItems.equals(DISCOUNT_TITLE)) {
                                String cardID = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.ID));
                                String cardTitle = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.TITLE));
                                String cardBarcode = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BARCODE));
                                String cardColor = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BACKGROUND_COLOR));

                                Intent intent = new Intent(ItemsActivity.this, ShowDiscountCard.class);
                                intent.putExtra(EXTRA_DISCOUNT_ID, cardID);
                                intent.putExtra(EXTRA_DISCOUNT_TITLE, cardTitle);
                                intent.putExtra(EXTRA_DISCOUNT_BARCODE, cardBarcode);
                                intent.putExtra(EXTRA_DISCOUNT_COLOR, cardColor);
                                startActivity(intent);

                            } else if (typeItems.equals(TICKETS_TITLE)) {

                            } else {

                            }
                        }
                    }

                    @Override
                    public void onError(Exception pE) {
                        Toast.makeText(ItemsActivity.this, "Cannot find card", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgressChanged(Void pVoid) {
                    }
                }, "*", ModelClass, "WHERE "
                        + ModelBankCards.ID + " = ?", String.valueOf(id));
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }

    private void initViews() {
        linearEmpty = (LinearLayout) findViewById(R.id.empty_page_id_fragment);
        noResultFor = (TextView) findViewById(R.id.frame_no_results_for);
        clearSearch = (ImageView) findViewById(R.id.clearSearch);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        toolbarBack = (CardView) findViewById(R.id.card_view_toolbar_back);
        toolbarSearch = (CardView) findViewById(R.id.card_view_toolbar_search);
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        recyclerItems = (RecyclerView) findViewById(R.id.recycler_view_items);
        if (typeItems.equals(constants.BANK_TITLE)) {
            ModelClass = ModelBankCards.class;
        } else if (typeItems.equals(constants.DISCOUNT_TITLE)) {
            ModelClass = ModelDiscountCards.class;
        } else if (typeItems.equals(constants.TICKETS_TITLE)) {
            ModelClass = ModelTickets.class;
        } else {
            ModelClass = ModelNFCItems.class;
        }
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
        if (supportLoaderManager.getLoader(LOADER_ID) != null) {
            supportLoaderManager.getLoader(LOADER_ID).forceLoad();
        }
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onAddNewClicked(View view) {
        if (typeItems.equals(BANK_TITLE)) {
            startActivity(new Intent(ItemsActivity.this, Bank.class));
        } else if (typeItems.equals(DISCOUNT_TITLE)) {
            Intent intent = new Intent(this, BarcodeScanner.class)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        } else if (typeItems.equals(TICKETS_TITLE)) {
            startActivity(new Intent(ItemsActivity.this, Ticket.class));
        } else {
            startActivity(new Intent(ItemsActivity.this, NFCReaderActivity.class));
        }
    }

    public void onBackSearchClicked(View view) {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(GONE);
        searchEdit.setText("");
        searchText = searchEdit.getText().toString();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (typeItems.equals(BANK_TITLE)) {
            return new BankCursorLoader(this, searchText);
        } else if (typeItems.equals(DISCOUNT_TITLE)) {
            return new DiscountCursorLoader(this, searchText);
        } else if (typeItems.equals(TICKETS_TITLE)) {
            // TODO: 12.12.2016 TicketCursorLoader
            return new BankCursorLoader(this, searchText);
        } else {
            // TODO: 12.12.2016 NFCCursorLoader
            return new BankCursorLoader(this, searchText);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data.getCount() == 0) {
            if (!searchText.equals("")) {
                // TODO: 06.12.2016 CHECK IF(LANG_ENG) LANG_RUS for spannable
                Spannable text = new SpannableString(getString(R.string.no_result_for) + " '" + searchText + "'");
                text.setSpan(new StyleSpan(Typeface.BOLD), 26, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                noResultFor.setText(text);
                noResultFor.setVisibility(View.VISIBLE);
            } else {
                linearEmpty.setVisibility(View.VISIBLE);
            }
            recyclerItems.setVisibility(GONE);
        } else {
            noResultFor.setVisibility(GONE);
            linearEmpty.setVisibility(GONE);
            recyclerItems.setVisibility(View.VISIBLE);
        }

        if (typeItems.equals(BANK_TITLE)) {
            bankAdapter = new CursorBankAdapter(data, ItemsActivity.this, R.layout.item_list_bank);
            recyclerItems.setAdapter(bankAdapter);
        } else if (typeItems.equals(DISCOUNT_TITLE)) {
            discountAdapter = new CursorDiscountAdapter(data, ItemsActivity.this, R.layout.item_discount);
            recyclerItems.setAdapter(discountAdapter);
        } else if (typeItems.equals(TICKETS_TITLE)) {
            // TODO: 12.12.2016 CTicketAdapter
        } else {
            // TODO: 12.12.2016 CNFCAdapter
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        recyclerItems.swapAdapter(null, true);
    }

    public void onToolbarBackClicked(View view) {
        if (typeItems.equals(BANK_TITLE)) {
            recyclerItems.smoothScrollToPosition(bankAdapter.getItemCount() - 1);
        } else if (typeItems.equals(DISCOUNT_TITLE)) {
            recyclerItems.smoothScrollToPosition(discountAdapter.getItemCount() - 1);
        } else if (typeItems.equals(TICKETS_TITLE)) {
            // TODO: 12.12.2016 CTicketAdapter
        } else {
            // TODO: 12.12.2016 CNFCAdapter
        }
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

    public void onClearSearchClicked(View view) {
        searchEdit.setText("");
    }
}