package com.maxcriser.cards.ui.activities;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.maxcriser.cards.BuildConfig;
import com.maxcriser.cards.R;
import com.maxcriser.cards.async.OnResultCallback;
import com.maxcriser.cards.async.OwnAsyncTask;
import com.maxcriser.cards.async.task.JsonParser;
import com.maxcriser.cards.dialog.NotificationDialogBuilder;
import com.maxcriser.cards.model.SettingsJson;

import org.json.JSONException;
import org.json.JSONObject;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.Extras.EXTRA_CHECK_ITEMS;
import static com.maxcriser.cards.constant.Extras.EXTRA_DISCOUNT_TITLE_TO_ITEMS;
import static com.maxcriser.cards.constant.Extras.EXTRA_NFC_TITLE_TO_ITEMS;
import static com.maxcriser.cards.constant.Extras.EXTRA_TICKETS_TITLE_TO_ITEMS;
import static com.maxcriser.cards.constant.ListConstants.CONFIG;
import static com.maxcriser.cards.constant.ListConstants.CREDIT_CARD;
import static com.maxcriser.cards.constant.ListConstants.SETUP_PIN;
import static com.maxcriser.cards.constant.ListConstants.TEXT_PLAIN;
import static com.maxcriser.cards.constant.ListConstants.TYPE_LOCKED_SCREEN;
import static com.maxcriser.cards.constant.ListConstants.URL_JSON_LOCATION;
import static com.maxcriser.cards.constant.ListConstants.URL_JSON_SETTINGS;
import static com.maxcriser.cards.manager.NetworkManager.isConnected;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String pCountry = "#country";
    private String pCountryCode = "#country code";
    private String pIsp = "#isp";
    private String pQuery = "#query";
    private String pTimezone = "#timezone";
    private DrawerLayout drawer;
    private OwnAsyncTask sync;
    private SettingsJson mSettingsJson;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sync = new OwnAsyncTask();
        initViews();
    }

    public void initViews() {
        sync.execute(new JsonParser(), URL_JSON_LOCATION, new OnResultCallback<String, Void>() {

            @Override
            public void onSuccess(final String pS) {
                final JSONObject dataJsonObj;
                try {
                    dataJsonObj = new JSONObject(pS);

                    final String COUNTRY_ID = "country";
                    pCountry = dataJsonObj.getString(COUNTRY_ID);
                    final String COUNTRY_CODE_ID = "countryCode";
                    pCountryCode = dataJsonObj.getString(COUNTRY_CODE_ID);
                    final String ISP_ID = "isp";
                    pIsp = dataJsonObj.getString(ISP_ID);
                    final String QUERY_ID = "query";
                    pQuery = dataJsonObj.getString(QUERY_ID);
                    final String TIMEZONE_ID = "timezone";
                    pTimezone = dataJsonObj.getString(TIMEZONE_ID);

                } catch (final JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(final Exception pE) {
                Toast.makeText(MenuActivity.this, getString(R.string.cannot_parese_location), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChanged(final Void pVoid) {
            }
        });

        sync.execute(new JsonParser(), URL_JSON_SETTINGS + CONFIG, new OnResultCallback<String, Void>() {

            @Override
            public void onSuccess(final String pS) {
                mSettingsJson = new SettingsJson(pS);
                if (mSettingsJson.isFlagMessage()) {
                    if (!mSettingsJson.getAppVersion().equals(BuildConfig.VERSION_NAME)) {

                        final NotificationDialogBuilder notificationDialogBuilder = new NotificationDialogBuilder(
                                MenuActivity.this, getString(R.string.notification),
                                getString(R.string.notification_update_first) + mSettingsJson.getAppVersion() + ")",
                                mSettingsJson.getGooglePlayUrl(), true, false, getString(R.string.update), null);
                        notificationDialogBuilder.startDialog();
                    }
                }
            }

            @Override
            public void onError(final Exception pE) {
                Toast.makeText(MenuActivity.this, R.string.cannot_parse_settings, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChanged(final Void pVoid) {

            }
        });
        final CardView credit = (CardView) findViewById(R.id.main_credit_card);
        final CardView discount = (CardView) findViewById(R.id.main_discount_card);
        final CardView tickets = (CardView) findViewById(R.id.main_tickets_card);
        final CardView nfc = (CardView) findViewById(R.id.main_nfc_card);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        final NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            nfc.setVisibility(GONE);
        }

        credit.setOnClickListener(new onClickListener());
        discount.setOnClickListener(new onClickListener());
        tickets.setOnClickListener(new onClickListener());
        nfc.setOnClickListener(new onClickListener());

        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
        final int id = item.getItemId();
        if (id == R.id.nav_pin) {
            final Intent intent = new Intent(this, LockerActivity.class);
            intent.putExtra(TYPE_LOCKED_SCREEN, SETUP_PIN);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));

        } else if (id == R.id.nav_about) {
            if (mSettingsJson != null) {
                final NotificationDialogBuilder notificationDialogBuilder = new NotificationDialogBuilder(this,
                        getString(R.string.about), mSettingsJson.getAbout(), null, false, false, null, null);
                notificationDialogBuilder.startDialog();
            }
        } else if (id == R.id.nav_share) {
            if (mSettingsJson != null) {
                final Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType(TEXT_PLAIN);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, mSettingsJson.getTitleShare());
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, mSettingsJson.getBodyShare());
                final String SHARE_USING = "share_using";
                startActivity(Intent.createChooser(sharingIntent, SHARE_USING));
            }
        } else if (id == R.id.nav_location) {
            final String title;
            final String message;
            if (isConnected(this)) {
                title = getString(R.string.location);
                message = getString(R.string.country_) + pCountry + ", " + pCountryCode + "\n" +
                        getString(R.string.timezone_) + pTimezone + "\n" +
                        getString(R.string.query_) + pQuery + "\n\n" +
                        pIsp;
            } else {
                title = getString(R.string.no_internet_connection);
                message = getString(R.string.looks_like_iconnection);
            }

            final NotificationDialogBuilder notificationDialogBuilder =
                    new NotificationDialogBuilder(this, title, message, null, true, false, null, null);
            notificationDialogBuilder.startDialog();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onMenuClicked(final View view) {
        drawer.openDrawer(GravityCompat.START);
    }

    public void onToolbarClicked(final View view) {
    }

    private class onClickListener implements View.OnClickListener {

        Intent intent;

        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.main_credit_card:
                    intent = new Intent(MenuActivity.this, LockerActivity.class);
                    intent.putExtra(TYPE_LOCKED_SCREEN, CREDIT_CARD);
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));
                    break;
                case R.id.main_discount_card:
                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
                    intent.putExtra(EXTRA_CHECK_ITEMS, EXTRA_DISCOUNT_TITLE_TO_ITEMS);
                    startActivity(intent);
                    break;
                case R.id.main_nfc_card:
                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
                    intent.putExtra(EXTRA_CHECK_ITEMS, EXTRA_NFC_TITLE_TO_ITEMS);
                    startActivity(intent);
                    break;
                case R.id.main_tickets_card:
                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
                    intent.putExtra(EXTRA_CHECK_ITEMS, EXTRA_TICKETS_TITLE_TO_ITEMS);
                    startActivity(intent);
                    break;
            }
        }
    }
}