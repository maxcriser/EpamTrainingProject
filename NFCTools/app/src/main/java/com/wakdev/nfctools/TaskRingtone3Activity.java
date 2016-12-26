package com.wakdev.nfctools;

import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
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

public class TaskRingtone3Activity extends C0316b {
    private static final int f1698n;
    private boolean f1699o;
    private String f1700p;
    private EditText f1701q;

    static {
        f1698n = C0481m.TASK_SOUND_RINGTONE3.cM;
    }

    public TaskRingtone3Activity() {
        this.f1699o = false;
        this.f1700p = null;
    }

    private void m2544j() {
        Intent intent = getIntent();
        this.f1699o = intent.getBooleanExtra("itemUpdate", false);
        this.f1700p = intent.getStringExtra("itemHash");
        if (this.f1699o && this.f1700p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1701q, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2545k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1701q.getText().toString());
        return hashMap;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            try {
                Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.ringtone.PICKED_URI");
                if (uri != null) {
                    Object title = RingtoneManager.getRingtone(this, uri).getTitle(this);
                    this.f1701q.setText(title);
                    this.f1701q.setSelection(title.length());
                }
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
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
        setContentView(C0622e.task_ringtone3);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1701q = (EditText) findViewById(C0621d.myRecord);
        m2544j();
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

    public void onSelectToneButtonClick(View view) {
        try {
            Intent intent = new Intent("android.intent.action.RINGTONE_PICKER");
            intent.putExtra("android.intent.extra.ringtone.TITLE", getString(C0625h.task_ringtone_3_description));
            intent.putExtra("android.intent.extra.ringtone.SHOW_SILENT", false);
            intent.putExtra("android.intent.extra.ringtone.SHOW_DEFAULT", true);
            intent.putExtra("android.intent.extra.ringtone.TYPE", 4);
            startActivityForResult(intent, 999);
        } catch (Exception e) {
            C0493f.m2081a(this, getString(C0625h.error));
        }
    }

    public void onValidateButtonClick(View view) {
        String obj = this.f1701q.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("requestMode", 2);
        intent.putExtra("requestType", f1698n);
        intent.putExtra("itemTask", obj);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1700p);
        intent.putExtra("itemUpdate", this.f1699o);
        intent.putExtra("itemFields", m2545k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
