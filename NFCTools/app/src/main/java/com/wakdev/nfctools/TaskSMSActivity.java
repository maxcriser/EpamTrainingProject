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

public class TaskSMSActivity extends C0316b {
    private static final int f1712n;
    private EditText f1713o;
    private EditText f1714p;
    private boolean f1715q;
    private String f1716r;

    static {
        f1712n = C0481m.TASK_MISC_SMS.cM;
    }

    public TaskSMSActivity() {
        this.f1715q = false;
        this.f1716r = null;
    }

    private void m2558j() {
        Intent intent = getIntent();
        this.f1715q = intent.getBooleanExtra("itemUpdate", false);
        this.f1716r = intent.getStringExtra("itemHash");
        if (this.f1715q && this.f1716r != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1713o, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f1714p, (String) hashMap.get("field2"));
            }
        }
    }

    private HashMap<String, String> m2559k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1713o.getText().toString());
        hashMap.put("field2", this.f1714p.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Cursor query;
        Throwable th;
        Cursor cursor = null;
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            Uri data = intent.getData();
            if (data != null) {
                try {
                    query = getContentResolver().query(data, new String[]{"data1", "data2"}, null, null, null);
                    if (query != null) {
                        try {
                            if (query.moveToFirst()) {
                                Object string = query.getString(0);
                                this.f1713o.setText(string);
                                this.f1713o.setSelection(string.length());
                            }
                        } catch (Exception e) {
                            try {
                                C0493f.m2081a(this, getString(C0625h.err_device_requires_additional_permissions));
                                if (query != null) {
                                    query.close();
                                }
                                if (i == 2) {
                                    return;
                                }
                                return;
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
                    if (i == 2) {
                        return;
                    }
                    return;
                } catch (Throwable th3) {
                    th = th3;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
        }
        if (i == 2 && i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field2".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f1714p.setText(new StringBuffer(this.f1714p.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f1714p.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f1714p.setText(this.f1714p.getText().toString() + stringExtra);
                this.f1714p.setSelection(this.f1714p.length());
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
        setContentView(C0622e.task_sms);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1713o = (EditText) findViewById(C0621d.mySMSTo);
        this.f1714p = (EditText) findViewById(C0621d.mySMSMessage);
        m2558j();
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

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field2");
        intent.putExtra("kSelectionField", this.f1714p.getSelectionStart());
        startActivityForResult(intent, 2);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1713o.getText().toString();
        String obj2 = this.f1714p.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_incorrect_sms));
            return;
        }
        String str = "sms:" + obj;
        String str2 = "?";
        if (!obj2.isEmpty()) {
            obj = obj + "\n" + obj2;
            str = str + str2 + "body=" + obj2;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1712n);
        intent.putExtra("itemTask", str);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1716r);
        intent.putExtra("itemUpdate", this.f1715q);
        intent.putExtra("itemFields", m2559k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
