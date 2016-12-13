package com.maxcriser.cards.ui;

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
import android.view.MenuItem;
import android.view.View;

import com.maxcriser.cards.R;
import com.maxcriser.cards.constant.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.view.View.GONE;
import static com.maxcriser.cards.constant.Constants.URL_JSON_LOCATION;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TYPE_LOCKED_SCREEN = "type_locked_screen";
    public static final String CREDIT_CARD = "credit_card";
    public static final String SETUP_PIN = "setup_pin";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String SHARE_BODY = "Donwloads my application on playmarket: cards_application.com";
    public static final String SHARE_TITLE = "Cards application";
    public static final String SHARE_USING = "Share using";
    static final String COUNTRY_ID = "country";
    static final String COUNTRY_CODE_ID = "countryCode";
    static final String ISP_ID = "isp";
    static final String QUERY_ID = "query";
    static final String TIMEZONE_ID = "timezone";
    public static String selectItem;
    CardView credit;
    CardView discount;
    CardView tickets;
    CardView nfc;
    String pCountry = "#country";
    String pCountryCode = "#country code";
    String pIsp = "#isp";
    String pQuery = "#query";
    String pTimezone = "#timezone";
    DrawerLayout drawer;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            nfc.setVisibility(GONE);
        }

        credit.setOnClickListener(new onClickListener());
        discount.setOnClickListener(new onClickListener());
        tickets.setOnClickListener(new onClickListener());
        nfc.setOnClickListener(new onClickListener());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, null,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void initViews() {

        credit = (CardView) findViewById(R.id.main_credit_card);
        discount = (CardView) findViewById(R.id.main_discount_card);
        tickets = (CardView) findViewById(R.id.main_tickets_card);
        nfc = (CardView) findViewById(R.id.main_nfc_card);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

//    @TargetApi(23)
//    private void getPermission(final byte CODE, final String PERMISSION) {
//        if (ContextCompat.checkSelfPermission(this, PERMISSION) != PackageManager.PERMISSION_GRANTED) {
//            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                Toast.makeText(this, R.string.application_need_access, Toast.LENGTH_SHORT).show();
//            } else if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
//                Toast.makeText(this, R.string.application_need_access, Toast.LENGTH_SHORT).show();
//            }
//            ActivityCompat.requestPermissions(this, new String[]{PERMISSION}, CODE);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (grantResults.length == 0) {
//            return;
//        } else if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(this, R.string.permission_has_not_been_granted, Toast.LENGTH_SHORT).show();
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

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
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_pin) {
            Intent intent = new Intent(MenuActivity.this, LockerActivity.class);
            intent.putExtra(TYPE_LOCKED_SCREEN, SETUP_PIN);
            startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));

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

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MenuActivity.this);
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

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private class ParseLocation extends AsyncTask<Void, Void, String> {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJson = Constants.EMPTY_STRING;

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

                pCountry = dataJsonObj.getString(COUNTRY_ID);
                pCountryCode = dataJsonObj.getString(COUNTRY_CODE_ID);
                pIsp = dataJsonObj.getString(ISP_ID);
                pQuery = dataJsonObj.getString(QUERY_ID);
                pTimezone = dataJsonObj.getString(TIMEZONE_ID);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void onMenuClicked(View view) {
        drawer.openDrawer(GravityCompat.START);
    }

    public void onToolbarClicked(View view) {
    }

    private class onClickListener implements View.OnClickListener {
        Intent intent;

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.main_credit_card:
                    intent = new Intent(MenuActivity.this, LockerActivity.class);
                    intent.putExtra(TYPE_LOCKED_SCREEN, CREDIT_CARD);
                    startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY));
                    break;
                case R.id.main_discount_card:
                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
                    selectItem = Constants.TITLES.DISCOUNT_TITLE;
                    startActivity(intent);
                    break;
                case R.id.main_nfc_card:
                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
                    selectItem = Constants.TITLES.NFC_TITLE;
                    startActivity(intent);
                    break;
                case R.id.main_tickets_card:
                    intent = new Intent(MenuActivity.this, ItemsActivity.class);
                    selectItem = Constants.TITLES.TICKETS_TITLE;
                    startActivity(intent);
                    break;
            }
        }
    }
}