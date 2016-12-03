package com.maxcriser.cards.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.ui.cards.DiscountCardsActivity;
import com.maxcriser.cards.ui.cards.NFCCardsActivity;
import com.maxcriser.cards.ui.cards.TicketsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.maxcriser.cards.constant.constants.URL_JSON_LOCATION;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TEXT_PLAIN = "text/plain";
    public static final String SHARE_BODY = "Donwloads my application on playmarket: cards_application.com";
    public static final String SHARE_TITLE = "Cards application";
    public static final String SHARE_USING = "Share using";
    CardView credit;
    CardView discount;
    CardView tickets;
    CardView nfc;
    String pCountry = "#country";
    String pCountryCode = "#country code";
    String pIsp = "#isp";
    String pQuery = "#query";
    String pTimezone = "#timezone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        credit = (CardView) findViewById(R.id.main_credit_card);
        discount = (CardView) findViewById(R.id.main_discount_card);
        tickets = (CardView) findViewById(R.id.main_tickets_card);
        nfc = (CardView) findViewById(R.id.main_nfc_card);

        credit.setOnClickListener(new onClickListener());
        discount.setOnClickListener(new onClickListener());
        tickets.setOnClickListener(new onClickListener());
        nfc.setOnClickListener(new onClickListener());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    private boolean isConnected(Context context) {
        NetworkInfo info = this.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    @Override
    protected void onResume() {
        super.onResume();
        new ParseLocation().execute();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            String shareBody = SHARE_BODY;
            String shareSub = SHARE_TITLE;

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType(TEXT_PLAIN);
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, shareSub);
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, SHARE_USING));

        } else if (id == R.id.nav_location) {
            String title;
            String message;
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

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder
                    .setMessage(message)
                    .setCancelable(false)
                    .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ParseLocation extends AsyncTask<Void, Void, String> {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = "";

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(URL_JSON_LOCATION);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                resultJson = buffer.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultJson;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);

            JSONObject dataJsonObj;
            try {
                dataJsonObj = new JSONObject(strJson);

                pCountry = dataJsonObj.getString("country");
                pCountryCode = dataJsonObj.getString("countryCode");
                pIsp = dataJsonObj.getString("isp");
                pQuery = dataJsonObj.getString("query");
                pTimezone = dataJsonObj.getString("timezone");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    public void onMenuClicked(View view) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.openDrawer(GravityCompat.START);
    }

    public void onToolbarClicked(View view) {
    }

    private class onClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_credit_card:
                    startActivity(new Intent(MainActivity.this, LockerActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY));
                    break;
                case R.id.main_discount_card:
                    startActivity(new Intent(MainActivity.this, DiscountCardsActivity.class));
                    break;
                case R.id.main_nfc_card:
                    startActivity(new Intent(MainActivity.this, NFCCardsActivity.class));
                    break;
                case R.id.main_tickets_card:
                    startActivity(new Intent(MainActivity.this, TicketsActivity.class));
                    break;
            }
        }
    }
}