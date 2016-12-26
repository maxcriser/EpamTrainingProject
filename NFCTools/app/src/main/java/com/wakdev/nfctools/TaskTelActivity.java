package com.wakdev.nfctools;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

public class TaskTelActivity extends C0316b {
    private static final int f1811n;
    private EditText f1812o;
    private boolean f1813p;
    private String f1814q;

    static {
        f1811n = C0481m.TASK_MISC_DIAL.cM;
    }

    public TaskTelActivity() {
        this.f1813p = false;
        this.f1814q = null;
    }

    private void m2605j() {
        Intent intent = getIntent();
        this.f1813p = intent.getBooleanExtra("itemUpdate", false);
        this.f1814q = intent.getStringExtra("itemHash");
        if (this.f1813p && this.f1814q != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1812o, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2606k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1812o.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Throwable th;
        Cursor cursor = null;
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            Uri data = intent.getData();
            if (data != null) {
                Cursor query;
                try {
                    query = getContentResolver().query(data, new String[]{"data1", "data2"}, null, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                Object string = query.getString(0);
                                this.f1812o.setText(string);
                                this.f1812o.setSelection(string.length());
                            }
                        } catch (Exception e) {
                            try {
                                C0493f.m2081a(this, getString(C0625h.err_device_requires_additional_permissions));
                                if (query != null) {
                                    query.close();
                                }
                            } catch (Throwable th2) {
                                cursor = query;
                                th = th2;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e2) {
                    query = null;
                    C0493f.m2081a(this, getString(C0625h.err_device_requires_additional_permissions));
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
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
        setContentView(C0622e.task_tel);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1812o = (EditText) findViewById(C0621d.myTelRecord);
        m2605j();
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

    public void onPickupContactButtonClick(View view) {
        try {
            Intent intent = new Intent("android.intent.action.PICK", Contacts.CONTENT_URI);
            intent.setType("vnd.android.cursor.dir/phone_v2");
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            C0493f.m2081a(this, getString(C0625h.error));
        }
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1812o.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_tel));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1811n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1814q);
        intent.putExtra("itemUpdate", this.f1813p);
        intent.putExtra("itemFields", m2606k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
