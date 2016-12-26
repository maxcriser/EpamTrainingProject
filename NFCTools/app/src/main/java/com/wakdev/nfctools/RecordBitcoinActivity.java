package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class RecordBitcoinActivity extends C0316b {
    private EditText f1285n;
    private EditText f1286o;
    private EditText f1287p;
    private boolean f1288q;
    private String f1289r;

    public RecordBitcoinActivity() {
        this.f1288q = false;
        this.f1289r = null;
    }

    private void m2332j() {
        Intent intent = getIntent();
        this.f1288q = intent.getBooleanExtra("itemUpdate", false);
        this.f1289r = intent.getStringExtra("itemHash");
        if (this.f1288q && this.f1289r != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1285n, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1286o, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f1287p, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2333k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1285n.getText().toString());
        hashMap.put("field2", this.f1286o.getText().toString());
        hashMap.put("field3", this.f1287p.getText().toString());
        return hashMap;
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCancelButtonClick(View view) {
        setResult(0, null);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.record_bitcoin);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1285n = (EditText) findViewById(C0621d.myAddress);
        this.f1286o = (EditText) findViewById(C0621d.myAmount);
        this.f1287p = (EditText) findViewById(C0621d.myMessage);
        m2332j();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1285n.getText().toString();
        String obj2 = this.f1286o.getText().toString();
        String obj3 = this.f1287p.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_incorrect));
            return;
        }
        String str = "bitcoin:" + obj;
        String str2 = "?";
        if (!obj2.isEmpty()) {
            obj = obj + "\n" + getString(C0625h.record_bitcoin_amount_title) + " " + obj2 + " " + getString(C0625h.record_bitcoin_amount_btc);
            str = str + str2 + "amount=" + obj2;
            str2 = "&";
        }
        if (!obj3.isEmpty()) {
            obj = obj + "\n" + getString(C0625h.record_bitcoin_message_title) + " " + obj3;
            str = str + str2 + "message=" + obj3;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 1);
        intent.putExtra("requestType", 29);
        intent.putExtra("itemRecord", str);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1289r);
        intent.putExtra("itemUpdate", this.f1288q);
        intent.putExtra("itemFields", m2333k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
