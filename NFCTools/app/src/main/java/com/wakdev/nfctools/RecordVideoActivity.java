package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.HashMap;

public class RecordVideoActivity extends C0316b {
    private int f1365n;
    private boolean f1366o;
    private String f1367p;
    private String f1368q;
    private TextView f1369r;
    private TextView f1370s;
    private EditText f1371t;

    /* renamed from: com.wakdev.nfctools.RecordVideoActivity.1 */
    class C05331 implements TextWatcher {
        final /* synthetic */ RecordVideoActivity f1364a;

        C05331(RecordVideoActivity recordVideoActivity) {
            this.f1364a = recordVideoActivity;
        }

        public void afterTextChanged(Editable editable) {
            this.f1364a.f1370s.setText(this.f1364a.f1368q.replace("#CODE#", this.f1364a.f1371t.getText()));
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public RecordVideoActivity() {
        this.f1365n = -1;
        this.f1366o = false;
        this.f1367p = null;
    }

    private void m2372j() {
        Intent intent = getIntent();
        this.f1366o = intent.getBooleanExtra("itemUpdate", false);
        this.f1367p = intent.getStringExtra("itemHash");
        if (this.f1366o && this.f1367p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1371t, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2373k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1371t.getText().toString());
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
        setContentView(C0622e.record_video);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1371t = (EditText) findViewById(C0621d.myCodeRecord);
        this.f1369r = (TextView) findViewById(C0621d.myTitleRecord);
        this.f1370s = (TextView) findViewById(C0621d.myURLRecord);
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

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        int i;
        int i2;
        int i3;
        int i4;
        super.onStart();
        this.f1365n = getIntent().getIntExtra("VIDEO_ID", -1);
        switch (this.f1365n) {
            case C0627j.Theme_actionModeSplitBackground /*26*/:
                i = C0625h.record_video_youtube;
                i2 = C0620c.record_youtube;
                i3 = C0625h.record_video_youtube_hint;
                i4 = C0625h.record_video_youtube_url;
                break;
            case C0627j.Theme_actionModeCloseDrawable /*27*/:
                i = C0625h.record_video_vimeo;
                i2 = C0620c.record_vimeo;
                i3 = C0625h.record_video_vimeo_hint;
                i4 = C0625h.record_video_vimeo_url;
                break;
            case C0627j.Theme_actionModeCutDrawable /*28*/:
                i = C0625h.record_video_dailymotion;
                i2 = C0620c.record_dailymotion;
                i3 = C0625h.record_video_dailymotion_hint;
                i4 = C0625h.record_video_dailymotion_url;
                break;
            default:
                C0493f.m2081a(this, getString(C0625h.error));
                finish();
                i4 = -1;
                i3 = -1;
                i = -1;
                i2 = -1;
                break;
        }
        if (i == -1 || i2 == -1 || i3 == -1 || i4 == -1) {
            C0493f.m2081a(this, getString(C0625h.error));
            finish();
        }
        this.f1369r.setText(i);
        this.f1369r.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(i2), null, null, null);
        this.f1371t.setHint(i3);
        this.f1368q = getString(i4);
        this.f1370s.setText(this.f1368q.replace("#CODE#", this.f1371t.getText()));
        this.f1371t.addTextChangedListener(new C05331(this));
        m2372j();
    }

    public void onValidateButtonClick(View view) {
        Object obj = this.f1371t.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        String replace = this.f1368q.replace("#CODE#", obj);
        Intent intent = new Intent();
        intent.putExtra("requestMode", 1);
        intent.putExtra("requestType", this.f1365n);
        intent.putExtra("itemRecord", replace);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1367p);
        intent.putExtra("itemUpdate", this.f1366o);
        intent.putExtra("itemFields", m2373k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
