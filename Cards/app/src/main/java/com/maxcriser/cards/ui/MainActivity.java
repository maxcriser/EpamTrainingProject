package com.maxcriser.cards.ui;

import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int IDD_CHECK_CATS = 3;
    AlertDialog.Builder builder;

    CardView credit;
    CardView discount;
    CardView tickets;
    CardView nfc;
    String pCountry = "_country";
    String pCountryCode = "_country code";
    String pIsp = "_isp";
    String pQuery = "_query";
    String pTimezone = "_timezone";

    public static String LOG_TAG = "my_log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ParseLocation().execute();

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

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_location) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.setTitle(R.string.location);
            alertDialogBuilder
                    .setMessage("Country: " + pCountry + ", " + pCountryCode + "\n" +
                            "Timezone: " + pTimezone + "\n" +
                            "Query: " + pQuery + "\n\n" +
                            pIsp)
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
                URL url = new URL("http://ip-api.com/json");

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