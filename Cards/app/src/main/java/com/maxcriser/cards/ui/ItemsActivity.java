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
import android.util.Log;
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
import com.maxcriser.cards.constant.constants;
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
import com.maxcriser.cards.utils.AlertNfcInput;
import com.maxcriser.cards.utils.AlertNfcOutput;
import com.maxcriser.cards.utils.RecyclerItemClickListener;
import com.maxcriser.cards.view.text_view.RobotoRegular;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BACK_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_BANK;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_CARDHOLDER;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_COLOR;
import static com.maxcriser.cards.constant.Extras.EXTRA_BANK_FRONT_PHOTO;
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
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_FIRST_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_ID;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_SECOND_PHOTO;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TIME;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKET_TITLE;
import static com.maxcriser.cards.constant.Extras.EXTRA_VERIFICATION_NUMBER_BANK;

public class ItemsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 1;
    private String typeItems;
    private FrameLayout progressBar;
    private FloatingActionButton newCard;
    private TextView noResultFor;
    private DatabaseHelperImpl dbHelper;
    private CursorAdapter adapter;
    private RecyclerView recyclerItems;
    private CardView toolbarBack;
    private CardView toolbarSearch;
    private LinearLayout linearEmpty;
    private ImageView clearSearch;
    private EditText searchEdit;
    private String searchText = constants.EMPTY_STRING;
    private Class ModelClass;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        typeItems = MenuActivity.selectItem;
        initViews();
    }

    private void showNfc(final Cursor pCursor) {
        final String id = pCursor.getString(pCursor.getColumnIndex(ModelNFCItems.ID));
        final String nameNfc = pCursor.getString(pCursor.getColumnIndex(ModelNFCItems.TITLE));
        final String tagNfc = pCursor.getString(pCursor.getColumnIndex(ModelNFCItems.TAG));
        final String color = pCursor.getString(pCursor.getColumnIndex(ModelNFCItems.BACKGROUND_COLOR));

        Log.d("showNfc", id + "\n" + nameNfc + "\n" + tagNfc + "\n" + color);

        final AlertNfcOutput alertNfcOutput = new AlertNfcOutput(this);
        alertNfcOutput.startDialog();
    }

    private void showTicket(final Cursor pCursor) {
        final String id = pCursor.getString(pCursor.getColumnIndex(ModelTickets.ID));
        final String nameTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.TITLE));
        final String cardholderTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.CARDHOLDER));
        final String dateTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.DATE));
        final String timeTicket = pCursor.getString(pCursor.getColumnIndex(ModelTickets.TIME));
        final String color = pCursor.getString(pCursor.getColumnIndex(ModelTickets.BACKGROUND_COLOR));
        final String firstPhoto = pCursor.getString(pCursor.getColumnIndex(ModelTickets.PHOTO_FIRST));
        final String secondPhoto = pCursor.getString(pCursor.getColumnIndex(ModelTickets.PHOTO_SECOND));

        final Intent intent = new Intent(ItemsActivity.this, TicketActivity.class);
        intent.putExtra(EXTRA_TICKET_ID, id);
        intent.putExtra(EXTRA_TICKET_TITLE, nameTicket);
        intent.putExtra(EXTRA_TICKET_CARDHOLDER, cardholderTicket);
        intent.putExtra(EXTRA_TICKET_DATE, dateTicket);
        intent.putExtra(EXTRA_TICKET_TIME, timeTicket);
        intent.putExtra(EXTRA_TICKET_COLOR, color);
        intent.putExtra(EXTRA_TICKET_FIRST_PHOTO, firstPhoto);
        intent.putExtra(EXTRA_TICKET_SECOND_PHOTO, secondPhoto);
        startActivity(intent);

    }

    private void showDiscount(final Cursor pCursor) {
        final String cardID = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.ID));
        final String cardTitle = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.TITLE));
        final String cardBarcode = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BARCODE));
        final String cardColor = pCursor.getString(pCursor.getColumnIndex(ModelDiscountCards.BACKGROUND_COLOR));

        final Intent intent = new Intent(ItemsActivity.this, DiscountCardActivity.class);
        intent.putExtra(EXTRA_DISCOUNT_ID, cardID);
        intent.putExtra(EXTRA_DISCOUNT_TITLE, cardTitle);
        intent.putExtra(EXTRA_DISCOUNT_BARCODE, cardBarcode);
        intent.putExtra(EXTRA_DISCOUNT_COLOR, cardColor);
        startActivity(intent);
    }

    private void showBank(final Cursor pCursor) {
        final String id = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.ID));
        final String verNumber = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.VERIFICATION_NUMBER));
        final String bank = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.TITLE));
        final String cardholder = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.CARDHOLDER));
        final String number = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.NUMBER));
        final String pin = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.PIN));
        final String valid = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.VALID));
        final String type = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.TYPE));
        final String color = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.BACKGROUND_COLOR));
        final String frontPhoto = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.PHOTO_FRONT));
        final String backPhoto = pCursor.getString(pCursor.getColumnIndex(ModelBankCards.PHOTO_BACK));

        final Intent intent = new Intent(this, BankCardActivity.class);
        intent.putExtra(EXTRA_BANK_ID, id);
        intent.putExtra(EXTRA_BANK_BANK, bank);
        intent.putExtra(EXTRA_BANK_CARDHOLDER, cardholder);
        intent.putExtra(EXTRA_VERIFICATION_NUMBER_BANK, verNumber);
        intent.putExtra(EXTRA_BANK_NUMBER, number);
        intent.putExtra(EXTRA_BANK_PIN, pin);
        intent.putExtra(EXTRA_BANK_VALID, valid);
        intent.putExtra(EXTRA_BANK_TYPE, type);
        intent.putExtra(EXTRA_BANK_COLOR, color);
        intent.putExtra(EXTRA_BANK_FRONT_PHOTO, frontPhoto);
        intent.putExtra(EXTRA_BANK_BACK_PHOTO, backPhoto);
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
        final RobotoRegular title = (RobotoRegular) findViewById(R.id.title_toolbar);
        recyclerItems = (RecyclerView) findViewById(R.id.recycler_view_items);
        if (typeItems.equals(getResources().getString(R.string.bank_title))) {
            ModelClass = ModelBankCards.class;
        } else if (typeItems.equals(getResources().getString(R.string.discount_title))) {
            ModelClass = ModelDiscountCards.class;
        } else if (typeItems.equals(getResources().getString(R.string.tickets_title))) {
            ModelClass = ModelTickets.class;
        } else {
            ModelClass = ModelNFCItems.class;
        }
        getSupportLoaderManager().restartLoader(LOADER_ID, null, this);
        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence pCharSequence, final int pI, final int pI1, final int pI2) {
            }

            @Override
            public void onTextChanged(final CharSequence pCharSequence, final int pI, final int pI1, final int pI2) {
            }

            @Override
            public void afterTextChanged(final Editable pEditable) {
                if (!pEditable.toString().isEmpty()) {
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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerItems.setLayoutManager(layoutManager);

        final ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, final int swipeDir) {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ItemsActivity.this);
                alertDialogBuilder.setTitle(R.string.remove);
                alertDialogBuilder
                        .setMessage(R.string.are_you_sure_to_delete)
                        .setCancelable(false)
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
                                dialog.cancel();
                            }
                        })
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                final TextView cardTitle = (TextView) viewHolder.itemView.findViewById(R.id.title_main_cards);
                                final Integer idDelete = (Integer) cardTitle.getTag();
                                dbHelper.delete(ModelClass, null, ModelBankCards.ID + " = ?", String.valueOf(idDelete));
                                getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
                                dialog.cancel();
                            }
                        });
                final AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        };

        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerItems);

        recyclerItems.addOnItemTouchListener(new RecyclerItemClickListener(this, recyclerItems, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {
                final TextView mTitle = (TextView) view.findViewById(R.id.title_main_cards);
                final int id = (Integer) mTitle.getTag();
                dbHelper.query(new OnResultCallback<Cursor, Void>() {
                    @Override
                    public void onSuccess(final Cursor pCursor) {
                        if (pCursor.moveToFirst()) {
                            if (typeItems.equals(getResources().getString(R.string.bank_title))) {
                                showBank(pCursor);
                            } else if (typeItems.equals(getResources().getString(R.string.discount_title))) {
                                showDiscount(pCursor);
                            } else if (typeItems.equals(getResources().getString(R.string.tickets_title))) {
                                showTicket(pCursor);
                            } else {
                                showNfc(pCursor);
                            }
                        }
                    }

                    @Override
                    public void onError(final Exception pE) {
                        Toast.makeText(ItemsActivity.this, R.string.connot_find_card, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onProgressChanged(final Void pVoid) {
                    }
                }, "*", ModelClass, "WHERE "
                        + ModelBankCards.ID + " = ?", String.valueOf(id));
            }

            @Override
            public void onItemLongClick(final View view, final int position) {

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
        //TODO find answer why here loaders but in another place tasks and in one more place Executors
        final LoaderManager supportLoaderManager = getSupportLoaderManager();
        if (supportLoaderManager.getLoader(LOADER_ID) != null) {
            supportLoaderManager.getLoader(LOADER_ID).forceLoad();
        }
        if (searchEdit != null) {
            if (toolbarSearch.getVisibility() == View.VISIBLE) {
                onBackSearchClicked(null);
            }
        }
    }

    public void onBackClicked(final View view) {
        super.onBackPressed();
    }

    @TargetApi(23)
    private void getPermission(final byte CODE, final String PERMISSION, final int INTENT) {
        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
        } else {
            if (INTENT == constants.Requests.REQUEST_CAMERA) {
                startBarcodeReader();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        if (grantResults.length == 0) {
            return;
        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, R.string.permission_has_not_been_granted, Toast.LENGTH_SHORT).show();
        } else if (requestCode == constants.Requests.REQUEST_CAMERA) {
            startBarcodeReader();
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void startBarcodeReader() {
        final Intent intent = new Intent(this, BarcodeScannerActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    public void onAddNewClicked(final View view) {
        if (typeItems.equals(getResources().getString(R.string.bank_title))) {
            startActivity(new Intent(ItemsActivity.this, CreateBankActivity.class));
        } else if (typeItems.equals(getResources().getString(R.string.discount_title))) {
            getPermission(constants.Requests.REQUEST_CAMERA, Manifest.permission.CAMERA, constants.Requests.REQUEST_CAMERA);
        } else if (typeItems.equals(getResources().getString(R.string.tickets_title))) {
            startActivity(new Intent(ItemsActivity.this, CreateTicketActivity.class));
        } else {
            final AlertNfcInput alertNfcInput = new AlertNfcInput(this);
            alertNfcInput.startDialog();
        }
    }

    public void onBackSearchClicked(final View view) {
        toolbarBack.setVisibility(View.VISIBLE);
        toolbarSearch.setVisibility(GONE);
        searchEdit.setText(constants.EMPTY_STRING);
        searchText = searchEdit.getText().toString();
        getSupportLoaderManager().restartLoader(LOADER_ID, null, ItemsActivity.this);
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(newCard.getWindowToken(), 0);
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int id, final Bundle args) {
        if (typeItems.equals(getResources().getString(R.string.bank_title))) {
            return new CardsCursorLoader(this, searchText, ModelBankCards.class);
        } else if (typeItems.equals(getResources().getString(R.string.discount_title))) {
            return new CardsCursorLoader(this, searchText, ModelDiscountCards.class);
        } else if (typeItems.equals(getResources().getString(R.string.tickets_title))) {
            return new CardsCursorLoader(this, searchText, ModelTickets.class);
        } else {
            // TODO: 12.12.2016 CreateNfcActivity
            return new CardsCursorLoader(this, searchText, ModelNFCItems.class);
        }
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> loader, final Cursor data) {
        progressBar.setVisibility(GONE);
        if (data.getCount() == 0) {
            if (!searchText.isEmpty()) {
                final Spannable text = new SpannableString(getString(R.string.no_result_for) + " '" + searchText + "'");
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

        if (typeItems.equals(getResources().getString(R.string.bank_title))) {
            adapter = new CursorAdapter(data, ItemsActivity.this, R.layout.item_bank);
        } else if (typeItems.equals(getResources().getString(R.string.discount_title))) {
            adapter = new CursorAdapter(data, ItemsActivity.this, R.layout.item_discount);
        } else if (typeItems.equals(getResources().getString(R.string.tickets_title))) {
            adapter = new CursorAdapter(data, ItemsActivity.this, R.layout.item_ticket);
        } else if (typeItems.equals(getResources().getString(R.string.nfc_title))) {
            adapter = new CursorAdapter(data, ItemsActivity.this, R.layout.item_nfc);
        }
//        recyclerItems.swapAdapter(adapter, true);
        recyclerItems.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> loader) {
        //        recyclerItems.swapAdapter(null, true);
        recyclerItems.setAdapter(null);
    }

    public void onToolbarBackClicked(final View view) {
        recyclerItems.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    public void onSearchClicked(final View view) {
        toolbarSearch.setVisibility(View.VISIBLE);
        toolbarBack.setVisibility(GONE);
        searchEdit.setText(constants.EMPTY_STRING);
        searchEdit.clearFocus();
        searchEdit.requestFocus();
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchEdit, InputMethodManager.SHOW_IMPLICIT);
    }

    public void onClearSearchClicked(final View view) {
        searchEdit.setText(constants.EMPTY_STRING);
    }
}