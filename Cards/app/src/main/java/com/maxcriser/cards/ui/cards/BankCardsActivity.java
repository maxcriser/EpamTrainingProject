package com.maxcriser.cards.ui.cards;

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
import com.maxcriser.cards.database.DatabaseHelper;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.handler.RecyclerItemClickListener;
import com.maxcriser.cards.ui.adapter.BankCursorLoader;
import com.maxcriser.cards.ui.adapter.CursorBankAdapter;
import com.maxcriser.cards.ui.create.Bank;
import com.maxcriser.cards.view.TextViews.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.constants.BANK_TITLE;

public class BankCardsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    DatabaseHelper dbHelper;
    TextView noResultFor;
    LinearLayoutManager mLayoutManager;
    RecyclerView viewBankCards;
    CursorBankAdapter adapter;
    CardView toolbarBack;
    CardView toolbarSearch;
    LinearLayout linearEmpty;
    ImageView clearSearch;
    EditText searchEdit;
    RobotoRegular title;
    private String searchText = "";
    public static final int LOADER_BANK_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_cards);
        initViews();
        getSupportLoaderManager().restartLoader(LOADER_BANK_ID, null, this);
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
                getSupportLoaderManager().restartLoader(LOADER_BANK_ID, null, BankCardsActivity.this);
            }
        });
        title.setText(BANK_TITLE);

        dbHelper = DatabaseHelper.getInstance(this, 1);

        viewBankCards.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        viewBankCards.setLayoutManager(mLayoutManager);

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
                dbHelper.delete(ModelBankCards.class, null, ModelBankCards.ID + " = ?", String.valueOf(id));
                // TODO FIX incorrect animation delete
//                onResume();
                getSupportLoaderManager().restartLoader(LOADER_BANK_ID, null, BankCardsActivity.this);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(viewBankCards);

        viewBankCards.addOnItemTouchListener(new RecyclerItemClickListener(this, viewBankCards, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView mTitle = (TextView) view.findViewById(R.id.title_main_cards);
                int id = (Integer) mTitle.getTag();

                dbHelper.query(new OnResultCallback<Cursor, Void>() {
                    @Override
                    public void onSuccess(Cursor pCursor) {
                        if (pCursor.moveToFirst()) {
//                            String cardID = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.ID));
//                            String cardTitle = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.TITLE));
//                            String cardBarcode = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BARCODE));
//                            String cardColor = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BACKGROUND_COLOR));
//
//                            Intent intent = new Intent(DiscountCardsActivity.this, ShowDiscountCard.class);
//                            intent.putExtra(EXTRA_DISCOUNT_ID, cardID);
//                            intent.putExtra(EXTRA_DISCOUNT_TITLE, cardTitle);
//                            intent.putExtra(EXTRA_DISCOUNT_BARCODE, cardBarcode);
//                            intent.putExtra(EXTRA_DISCOUNT_COLOR, cardColor);
//                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onError(Exception pE) {
//                        Toast.makeText(DiscountCardsActivity.this, "Cannot find card", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgressChanged(Void pVoid) {
                    }
                }, "*", ModelBankCards.class, "WHERE "
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
        viewBankCards = (RecyclerView) findViewById(R.id.types_bank_cards_recycler_view);
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
        if (supportLoaderManager.getLoader(LOADER_BANK_ID) != null) {
            supportLoaderManager.getLoader(LOADER_BANK_ID).forceLoad();
        }
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    public void onAddNewClicked(View view) {
        startActivity(new Intent(BankCardsActivity.this, Bank.class));
    }

    public void onBackSearchClicked(View view) {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(GONE);
        searchEdit.setText("");
        searchText = searchEdit.getText().toString();
        getSupportLoaderManager().restartLoader(LOADER_BANK_ID, null, BankCardsActivity.this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new BankCursorLoader(this, searchText);
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
            viewBankCards.setVisibility(GONE);
        } else {
            noResultFor.setVisibility(GONE);
            linearEmpty.setVisibility(GONE);
            viewBankCards.setVisibility(View.VISIBLE);
        }

        adapter = new CursorBankAdapter(data, BankCardsActivity.this, R.layout.item_list_bank);
        viewBankCards.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        viewBankCards.swapAdapter(null, true);
    }

    public void onToolbarBackClicked(View view) {
        Toast.makeText(this, "" + adapter.getItemCount(), Toast.LENGTH_LONG).show();
        viewBankCards.smoothScrollToPosition(adapter.getItemCount() - 1);
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