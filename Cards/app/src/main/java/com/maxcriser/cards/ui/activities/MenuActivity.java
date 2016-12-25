package com.maxcriser.cards.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.NfcAdapter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.ListConstants;
import com.maxcriser.cards.utils.SettingsParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.ListConstants.SETUP_PIN;
import static com.maxcriser.cards.constant.ListConstants.TEXT_PLAIN;
import static com.maxcriser.cards.constant.ListConstants.URL_JSON_LOCATION;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //TODO remove static
    public static String selectItem;
    public static final String TYPE_LOCKED_SCREEN = "type_locked_screen";
    public static final String CREDIT_CARD = "credit_card";

    private String pCountry = "#country";
    private String pCountryCode = "#country code";
    private String pIsp = "#isp";
    private String pQuery = "#query";
    private String pTimezone = "#timezone";
    private DrawerLayout drawer;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        new SettingsParser().execute();
    }

    public void initViews() {
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

    //TODO ConnectivityManager
    private NetworkInfo getNetworkInfo(final Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    private boolean isConnected(final Context context) {
        final NetworkInfo info = this.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ParseLocation().execute();
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

        } else if (id == R.id.nav_share) {
            final String shareBody = getString(R.string.share_body);
            final String shareSub = getString(R.string.share_title);

            final Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType(TEXT_PLAIN);
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            final String SHARE_USING = "share_using";
            startActivity(Intent.createChooser(sharingIntent, SHARE_USING));

        } else if (id == R.id.nav_location) {
            final String title;
            final String message;
            if (isConnected(this)) {
                title = getString(R.string.location);
                message = "Country: " + pCountry + ", " + pCountryCode + "\n" +
                        "Timezone: " + pTimezone + "\n" +
                        "Query: " + pQuery + "\n\n" +
                        pIsp;
            } else {
                title = getString(R.string.no_internet_connection);
                message = getString(R.string.looks_like_iconnection);
            }

            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder
                    .setMessage(message)
                    .setCancelable(false)
                    .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {

                        public void onClick(final DialogInterface dialog, final int id) {
                            dialog.cancel();
                        }
                    });
            final AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ParseLocation extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection;
        BufferedReader reader;
        String resultJson = ListConstants.EMPTY_STRING;

        @Override
        protected String doInBackground(final Void... params) {
            try {
                final URL url = new URL(URL_JSON_LOCATION);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                final InputStream inputStream = urlConnection.getInputStream();
                final StringBuilder buffer = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();
                inputStream.close();

            } catch (final Exception e) {
                Log.d("Connection time out", e.toString());
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(final String strJson) {
            super.onPostExecute(strJson);

            final JSONObject dataJsonObj;
            try {
                dataJsonObj = new JSONObject(strJson);

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
                    //TODO magic
                    selectItem = getResources().getString(R.string.discount_title);
                    startActivity(intent);
                    break;
                case R.id.main_nfc_card:
                    // TODO: 24.12.2016 NFCREADERACTIVITY
                    startActivity(new Intent(MenuActivity.this, NFCReaderActivity.class));
//                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
//                    selectItem = getResources().getString(R.string.nfc_title);
//                    startActivity(intent);
                    break;
                case R.id.main_tickets_card:
                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
                    //TODO magic
                    selectItem = getResources().getString(R.string.tickets_title);
                    startActivity(intent);
                    break;
            }
        }
    }
}