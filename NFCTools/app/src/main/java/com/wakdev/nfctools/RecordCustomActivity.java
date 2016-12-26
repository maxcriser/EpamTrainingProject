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

public class RecordCustomActivity extends C0316b {
    private boolean f1302n;
    private String f1303o;
    private EditText f1304p;
    private EditText f1305q;
    private EditText f1306r;

    public RecordCustomActivity() {
        this.f1302n = false;
        this.f1303o = null;
    }

    private void m2338j() {
        Intent intent = getIntent();
        this.f1302n = intent.getBooleanExtra("itemUpdate", false);
        this.f1303o = intent.getStringExtra("itemHash");
        if (this.f1302n && this.f1303o != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1304p, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1305q, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f1306r, (String) hashMap.get("field3"));
            }
        }
    }

    private HashMap<String, String> m2339k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1304p.getText().toString());
        hashMap.put("field2", this.f1305q.getText().toString());
        hashMap.put("field3", this.f1306r.getText().toString());
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
        setContentView(C0622e.record_custom);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1304p = (EditText) findViewById(C0621d.myCustomMimeRecord1);
        this.f1305q = (EditText) findViewById(C0621d.myCustomMimeRecord2);
        this.f1306r = (EditText) findViewById(C0621d.myCustomDataRecord);
        m2338j();
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
        String obj = this.f1304p.getText().toString();
        String obj2 = this.f1305q.getText().toString();
        String obj3 = this.f1306r.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_content_type_is_empty));
        } else if (obj.equals(" ") || obj2.equals(" ")) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_content_type));
        } else if (obj3.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_data_is_empty));
        } else {
            obj = obj + "/" + obj2;
            Intent intent = new Intent();
            intent.putExtra("requestMode", 1);
            intent.putExtra("requestType", 8);
            intent.putExtra("itemRecord", obj3);
            intent.putExtra("itemRecordExtra", obj);
            intent.putExtra("itemDescription", obj3);
            intent.putExtra("itemHash", this.f1303o);
            intent.putExtra("itemUpdate", this.f1302n);
            intent.putExtra("itemFields", m2339k());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }
}
