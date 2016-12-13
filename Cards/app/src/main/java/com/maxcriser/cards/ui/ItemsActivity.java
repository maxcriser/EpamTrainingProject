package com.maxcriser.cards.ui;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.constant.Constants;
import com.maxcriser.cards.database.DatabaseHelperImpl;
import com.maxcriser.cards.database.models.ModelBankCards;
import com.maxcriser.cards.database.models.ModelDiscountCards;
import com.maxcriser.cards.database.models.ModelNFCItems;
import com.maxcriser.cards.database.models.ModelTickets;
import com.maxcriser.cards.loader.CardsCursorLoader;
import com.maxcriser.cards.ui.adapter.CursorAdapter;
import com.maxcriser.cards.ui.create_item.CreateBankActivity;
import com.maxcriser.cards.ui.create_item.CreateTicketActivity;
import com.maxcriser.cards.ui.display_item.BankCardActivity;
import com.maxcriser.cards.ui.display_item.DiscountCardActivity;
import com.maxcriser.cards.ui.display_item.TicketActivity;
import com.maxcriser.cards.util.RecyclerItemClickListener;
import com.maxcriser.cards.view.text_view.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BANK;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_CARDHOLDER;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_NUMBER;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_PIN;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_TYPE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_VALID;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_BARCODE;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_TITLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_CARDHOLDER;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_DATE;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TIME;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TITLE;

public class ItemsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    String typeItems;
    FrameLayout progressBar;
    FloatingActionButton newCard;
    TextView noResultFor;
    DatabaseHelperImpl dbHelper;
    LinearLayoutManager mLayoutManager;
    RecyclerView recyclerItems;
    CursorAdapter adapter;
    // TODO: 12.12.2016  CreateNfcActivity adapter
    CardView toolbarBack;
    CardView toolbarSearch;
    LinearLayout linearEmpty;
    ImageView clearSearch;
    EditText searchEdit;
    RobotoRegular title;
    private String searchText = Constants.EMPTY_STRING;
    public static Boolean flagToRemove = false;
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
                if (!pEditable.toString().equals(Constants.EMPTY_STRING)) {
                    clearSearch.setVisibility(View.VISIBLE);
                } else {
                    clearSearch.setVisibility(View.GONE);
                }
                searchText = pEditable.toString();
                getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
            }
        });
        title.setText(typeItems);

        dbHelper = DatabaseHelperImpl.getInstance(this);

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
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ItemsActivity.this);
                alertDialogBuilder.setTitle(R.string.remove);
                alertDialogBuilder
                        .setMessage(R.string.are_you_sure_to_delete)
                        .setCancelable(false)
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                TextView cardTitle = (TextView) viewHolder.itemView.findViewById(R.id.title_main_cards);
                                Integer idDelete = (Integer) cardTitle.getTag();
                                dbHelper.delete(ModelClass, null, ModelBankCards.ID + " = ?", String.valueOf(idDelete));
                                getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
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
                            if (typeItems.equals(Constants.TITLES.BANK_TITLE)) {
                                showBank(pCursor);
                            } else if (typeItems.equals(Constants.TITLES.DISCOUNT_TITLE)) {
                                showDiscount(pCursor);
                            } else if (typeItems.equals(Constants.TITLES.TICKETS_TITLE)) {
                                showTicket(pCursor);
                            } else {
                                // TODO: 12.12.2016 showNFC(pCursor)
                            }
                        }
                    }

                    @Override
                    public void onError(Exception pE) {
                        Toast.makeText(ItemsActivity.this, R.string.connot_find_card, Toast.LENGTH_SHORT).show();
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

    private void showTicket(Cursor pCursor) {
        String id = pCursor.getString(pCursor.getColumnIndex(ModelTickets.ID));
        String nameTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.TITLE));
        String cardholderTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.CARDHOLDER));
        String dateTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.DATE));
        String timeTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.TIME));
        String color = pCursor.getString(pCursor.getColumnIndex(ModelTickets.BACKGROUND_COLOR));

        Intent intent = new Intent(ItemsActivity.this, TicketActivity.class);
        intent.putExtra(EXTRA_TICKET_ID, id);
        intent.putExtra(EXTRA_TICKET_TITLE, nameTicket);
        intent.putExtra(EXTRA_TICKET_CARDHOLDER, cardholderTicket);
        intent.putExtra(EXTRA_TICKET_DATE, dateTicket);
        intent.putExtra(EXTRA_TICKET_TIME, timeTicket);
        intent.putExtra(EXTRA_TICKET_COLOR, color);
        startActivity(intent);
    }

    private void showDiscount(Cursor pCursor) {
        String cardID = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.ID));
        String cardTitle = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.TITLE));
        String cardBarcode = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BARCODE));
        String cardColor = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BACKGROUND_COLOR));

        Intent intent = new Intent(ItemsActivity.this, DiscountCardActivity.class);
        intent.putExtra(EXTRA_DISCOUNT_ID, cardID);
        intent.putExtra(EXTRA_DISCOUNT_TITLE, cardTitle);
        intent.putExtra(EXTRA_DISCOUNT_BARCODE, cardBarcode);
        intent.putExtra(EXTRA_DISCOUNT_COLOR, cardColor);
        startActivity(intent);
    }

    private void showBank(Cursor pCursor) {
        String id = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.ID));
        String bank = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.TITLE));
        String cardholder = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.CARDHOLDER));
        String number = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.NUMBER));
        String pin = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.PIN));
        String valid = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.VALID));
        String type = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.TYPE));
        String color = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.BACKGROUND_COLOR));

        Intent intent = new Intent(ItemsActivity.this, BankCardActivity.class);
        intent.putExtra(EXTRA_BANK_ID, id);
        intent.putExtra(EXTRA_BANK_BANK, bank);
        intent.putExtra(EXTRA_BANK_CARDHOLDER, cardholder);
        intent.putExtra(EXTRA_BANK_NUMBER, number);
        intent.putExtra(EXTRA_BANK_PIN, pin);
        intent.putExtra(EXTRA_BANK_VALID, valid);
        intent.putExtra(EXTRA_BANK_TYPE, type);
        intent.putExtra(EXTRA_BANK_COLOR, color);
        startActivity(intent);
    }

    private void initViews() {
        progressBar = (FrameLayout) findViewById(R.id.frame_progressbar);
        newCard = (FloatingActionButton) findViewById(R.id.new_card);
        linearEmpty = (LinearLayout) findViewById(R.id.empty_page_id_fragment);
        noResultFor = (TextView) findViewById(R.id.frame_no_results_for);
        clearSearch = (ImageView) findViewById(R.id.clearSearch);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        toolbarBack = (CardView) findViewById(R.id.card_view_toolbar_back);
        toolbarSearch = (CardView) findViewById(R.id.card_view_toolbar_search);
        title = (RobotoRegular) findViewById(R.id.title_toolbar);
        recyclerItems = (RecyclerView) findViewById(R.id.recycler_view_items);
        if (typeItems.equals(Constants.TITLES.BANK_TITLE)) {
            ModelClass = ModelBankCards.class;
        } else if (typeItems.equals(Constants.TITLES.DISCOUNT_TITLE)) {
            ModelClass = ModelDiscountCards.class;
        } else if (typeItems.equals(Constants.TITLES.TICKETS_TITLE)) {
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
        if (searchEdit != null) {
            if (toolbarSearch.getVisibility() == View.VISIBLE) {
                onBackSearchClicked(null);
            }
        }
    }

    public void onBackClicked(View view) {
        super.onBackPressed();
    }

    @TargetApi(23)
    private void getPermission(final byte CODE, final String PERMISSION, int INTENT) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
        } else {
            if (INTENT == Constants.REQUESTS.REQUEST_CAMERA) {
                startBarcodeReader();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.permission_has_not_been_granted, Toast.LENGTH_SHORT).show();
        } else if (requestCode == Constants.REQUESTS.REQUEST_CAMERA) {
            startBarcodeReader();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startBarcodeReader() {
        Intent intent = new Intent(this, BarcodeScannerActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void onAddNewClicked(View view) {
        if (typeItems.equals(Constants.TITLES.BANK_TITLE)) {
            startActivity(new Intent(ItemsActivity.this, CreateBankActivity.class));
        } else if (typeItems.equals(Constants.TITLES.DISCOUNT_TITLE)) {
            getPermission(Constants.REQUESTS.REQUEST_CAMERA, Manifest.permission.CAMERA, Constants.REQUESTS.REQUEST_CAMERA);
        } else if (typeItems.equals(Constants.TITLES.TICKETS_TITLE)) {
            startActivity(new Intent(ItemsActivity.this, CreateTicketActivity.class));
        } else {
            startActivity(new Intent(ItemsActivity.this, NfcReaderActivity.class));
        }
    }

    public void onBackSearchClicked(View view) {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(GONE);
        searchEdit.setText(Constants.EMPTY_STRING);
        searchText = searchEdit.getText().toString();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(newCard.getWindowToken(), 0);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if (typeItems.equals(Constants.TITLES.BANK_TITLE)) {
            return new CardsCursorLoader(this, searchText, ModelBankCards.class);
        } else if (typeItems.equals(Constants.TITLES.DISCOUNT_TITLE)) {
            return new CardsCursorLoader(this, searchText, ModelDiscountCards.class);
        } else if (typeItems.equals(Constants.TITLES.TICKETS_TITLE)) {
            return new CardsCursorLoader(this, searchText, ModelTickets.class);
        } else {
            // TODO: 12.12.2016 CreateNfcActivity
            return new CardsCursorLoader(this, searchText, ModelBankCards.class);
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        progressBar.setVisibility(GONE);
        if (data.getCount() == 0) {
            if (!searchText.equals(Constants.EMPTY_STRING)) {
                Spannable text = new SpannableString(getString(R.string.no_result_for) + " '" + searchText + "'");
                text.setSpan(new StyleSpan(Typeface.BOLD), text.length() - searchText.length() - 1, text.length() - 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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

        if (typeItems.equals(Constants.TITLES.BANK_TITLE)) {
            adapter = new CursorAdapter(data, ItemsActivity.this, R.layout.item_list_bank);
        } else if (typeItems.equals(Constants.TITLES.DISCOUNT_TITLE)) {
            adapter = new CursorAdapter(data, ItemsActivity.this, R.layout.item_discount);
        } else if (typeItems.equals(Constants.TITLES.TICKETS_TITLE)) {
            adapter = new CursorAdapter(data, ItemsActivity.this, R.layout.item_ticket);
        } else {
            // TODO: 12.12.2016 CNFCAdapter
        }
        recyclerItems.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        recyclerItems.swapAdapter(null, true);
    }

    public void onToolbarBackClicked(View view) {
        recyclerItems.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    public void onSearchClicked(View view) {
        toolbarSearch.setVisibility(View.VISIBLE);
        toolbarBack.setVisibility(GONE);
        searchEdit.setText(Constants.EMPTY_STRING);
        searchEdit.clearFocus();
        searchEdit.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEdit, InputMethodManager.SHOW_IMPLICIT);
    }

    public void onClearSearchClicked(View view) {
        searchEdit.setText(Constants.EMPTY_STRING);
    }
}