package com.wakdev.nfctools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class by extends C0316b {
    private static final int f2186n;
    private boolean f2187o;
    private String f2188p;
    private EditText f2189q;
    private EditText f2190r;
    private LinearLayout f2191s;
    private ArrayList<HashMap<String, EditText>> f2192t;

    static {
        f2186n = C0481m.TASK_NETWORK_HTTP_POST.cM;
    }

    public by() {
        this.f2187o = false;
        this.f2188p = null;
    }

    private void m2847j() {
        Intent intent = getIntent();
        this.f2187o = intent.getBooleanExtra("itemUpdate", false);
        this.f2188p = intent.getStringExtra("itemHash");
        if (this.f2187o && this.f2188p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2189q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2190r, (String) hashMap.get("field2"));
            }
        }
    }

    private void m2848k() {
        String obj = this.f2190r.getText().toString();
        if (!obj.isEmpty() && obj.contains(";")) {
            for (String str : obj.split(";")) {
                if (!str.isEmpty() && str.contains("=")) {
                    String[] split = str.split("=");
                    if (split.length == 2) {
                        m2852a(split[0], split[1]);
                    }
                }
            }
        }
    }

    private HashMap<String, String> m2849l() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2189q.getText().toString());
        hashMap.put("field2", this.f2190r.getText().toString());
        return hashMap;
    }

    private String m2850m() {
        String obj = this.f2189q.getText().toString();
        if (obj.isEmpty()) {
            return null;
        }
        String str = "";
        if (this.f2192t.isEmpty()) {
            this.f2190r.setText("");
            return obj;
        }
        Iterator it = this.f2192t.iterator();
        Object obj2 = str;
        while (it.hasNext()) {
            HashMap hashMap = (HashMap) it.next();
            String obj3 = ((EditText) hashMap.get("PARAM_NAME")).getText().toString();
            str = ((EditText) hashMap.get("PARAM_VALUE")).getText().toString();
            if (obj3.isEmpty() || str.isEmpty()) {
                return null;
            }
            obj3 = obj3.replace("|", Uri.encode("|")).replace("=", Uri.encode("=")).replace(";", Uri.encode(";"));
            String str2 = obj2 + obj3 + "=" + str.replace("|", Uri.encode("|")).replace("=", Uri.encode("=")).replace(";", Uri.encode(";")) + ";";
        }
        this.f2190r.setText(obj2);
        return obj + "|" + obj2;
    }

    private String m2851n() {
        String str = getString(C0625h.task_http_post_request) + " " + this.f2189q.getText().toString();
        if (!this.f2192t.isEmpty()) {
            String str2 = str + "\n" + getString(C0625h.task_http_post_post_parameters);
            Iterator it = this.f2192t.iterator();
            str = str2;
            while (it.hasNext()) {
                HashMap hashMap = (HashMap) it.next();
                String str3 = str + "\n";
                str = ((EditText) hashMap.get("PARAM_NAME")).getText().toString();
                str = (str3 + getString(C0625h.param_name) + " " + str) + " / " + getString(C0625h.param_value) + " " + ((EditText) hashMap.get("PARAM_VALUE")).getText().toString();
            }
        }
        return str;
    }

    public void m2852a(String str, String str2) {
        LinearLayout linearLayout = (LinearLayout) View.inflate(this, C0622e.param_item, null);
        EditText editText = (EditText) linearLayout.findViewById(C0621d.myName);
        EditText editText2 = (EditText) linearLayout.findViewById(C0621d.myValue);
        Button button = (Button) linearLayout.findViewById(C0621d.myParamRemoveButton);
        if (str != null) {
            editText.setText(str);
        }
        if (str2 != null) {
            editText2.setText(str2);
        }
        HashMap hashMap = new HashMap();
        hashMap.put("PARAM_NAME", editText);
        hashMap.put("PARAM_VALUE", editText2);
        this.f2192t.add(hashMap);
        button.setTag(linearLayout);
        this.f2191s.addView(linearLayout);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            String stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty() && "field1".equals(stringExtra2)) {
                if (intExtra != -1) {
                    this.f2189q.setText(new StringBuffer(this.f2189q.getText().toString()).insert(intExtra, stringExtra).toString());
                    this.f2189q.setSelection(stringExtra.length() + intExtra);
                    return;
                }
                this.f2189q.setText(this.f2189q.getText().toString() + stringExtra);
                this.f2189q.setSelection(this.f2189q.length());
            }
        }
    }

    public void onAddParamsButtonClick(View view) {
        m2852a(null, null);
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
        setContentView(C0622e.task_http_post);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2189q = (EditText) findViewById(C0621d.myRequest);
        this.f2190r = (EditText) findViewById(C0621d.myParameters);
        this.f2191s = (LinearLayout) findViewById(C0621d.myPostParams);
        this.f2192t = new ArrayList();
        m2847j();
        m2848k();
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

    public void onRemoveButtonClick(View view) {
        LinearLayout linearLayout = (LinearLayout) view.getTag();
        ViewGroup viewGroup = (ViewGroup) linearLayout.getParent();
        EditText editText = (EditText) linearLayout.findViewById(C0621d.myName);
        EditText editText2 = (EditText) linearLayout.findViewById(C0621d.myValue);
        HashMap hashMap = new HashMap();
        hashMap.put("PARAM_NAME", editText);
        hashMap.put("PARAM_VALUE", editText2);
        this.f2192t.remove(hashMap);
        viewGroup.removeView(linearLayout);
    }

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field1");
        intent.putExtra("kSelectionField", this.f2189q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onValidateButtonClick(View view) {
        String m = m2850m();
        if (m != null) {
            String n = m2851n();
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2186n);
            intent.putExtra("itemTask", m);
            intent.putExtra("itemDescription", n);
            intent.putExtra("itemHash", this.f2188p);
            intent.putExtra("itemUpdate", this.f2187o);
            intent.putExtra("itemFields", m2849l());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
    }
}
