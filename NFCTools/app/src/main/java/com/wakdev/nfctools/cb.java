package com.wakdev.nfctools;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputFilter.AllCaps;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0499j;
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

public class cb extends C0316b {
    private static final int f2214n;
    private boolean f2215o;
    private String f2216p;
    private EditText f2217q;
    private EditText f2218r;
    private EditText f2219s;
    private LinearLayout f2220t;
    private ArrayList<HashMap<String, EditText>> f2221u;

    /* renamed from: com.wakdev.nfctools.cb.1 */
    class C05831 implements InputFilter {
        final /* synthetic */ cb f2213a;

        C05831(cb cbVar) {
            this.f2213a = cbVar;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            while (i < i2) {
                if (!Character.isLetterOrDigit(charSequence.charAt(i))) {
                    return "";
                }
                i++;
            }
            return null;
        }
    }

    static {
        f2214n = C0481m.TASK_NETWORK_HTTP_POST_TO_VAR.cM;
    }

    public cb() {
        this.f2215o = false;
        this.f2216p = null;
    }

    private void m2861j() {
        Intent intent = getIntent();
        this.f2215o = intent.getBooleanExtra("itemUpdate", false);
        this.f2216p = intent.getStringExtra("itemHash");
        if (this.f2215o && this.f2216p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f2217q, (String) hashMap.get("field1"));
                C0490e.m2075a(this.f2219s, (String) hashMap.get("field2"));
                C0490e.m2075a(this.f2218r, (String) hashMap.get("field3"));
            }
        }
    }

    private void m2862k() {
        String obj = this.f2218r.getText().toString();
        if (!obj.isEmpty() && obj.contains(";")) {
            for (String str : obj.split(";")) {
                if (!str.isEmpty() && str.contains("=")) {
                    String[] split = str.split("=");
                    if (split.length == 2) {
                        m2866a(split[0], split[1]);
                    }
                }
            }
        }
    }

    private HashMap<String, String> m2863l() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f2217q.getText().toString());
        hashMap.put("field2", this.f2219s.getText().toString());
        hashMap.put("field3", this.f2218r.getText().toString());
        return hashMap;
    }

    private String m2864m() {
        String obj = this.f2217q.getText().toString();
        String obj2 = this.f2219s.getText().toString();
        if (obj.isEmpty() || obj2.isEmpty()) {
            return null;
        }
        String str = obj + "|" + obj2;
        obj = "";
        if (this.f2221u.isEmpty()) {
            this.f2218r.setText("");
            return str;
        }
        Iterator it = this.f2221u.iterator();
        Object obj3 = obj;
        while (it.hasNext()) {
            HashMap hashMap = (HashMap) it.next();
            obj2 = ((EditText) hashMap.get("PARAM_NAME")).getText().toString();
            obj = ((EditText) hashMap.get("PARAM_VALUE")).getText().toString();
            if (obj2.isEmpty() || obj.isEmpty()) {
                return null;
            }
            obj2 = obj2.replace("|", Uri.encode("|")).replace("=", Uri.encode("=")).replace(";", Uri.encode(";"));
            String str2 = obj3 + obj2 + "=" + obj.replace("|", Uri.encode("|")).replace("=", Uri.encode("=")).replace(";", Uri.encode(";")) + ";";
        }
        this.f2218r.setText(obj3);
        return str + "|" + obj3;
    }

    private String m2865n() {
        String str = (getString(C0625h.task_http_post_request) + " " + this.f2217q.getText().toString() + "\n") + getString(C0625h.task_http_post_to_variable_varname) + " {VAR_" + this.f2219s.getText().toString() + "}";
        if (!this.f2221u.isEmpty()) {
            String str2 = str + "\n" + getString(C0625h.task_http_post_post_parameters);
            Iterator it = this.f2221u.iterator();
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

    public void m2866a(String str, String str2) {
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
        this.f2221u.add(hashMap);
        button.setTag(linearLayout);
        this.f2220t.addView(linearLayout);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.hasExtra("kTargetField") && intent.hasExtra("kResultValue")) {
            CharSequence stringExtra = intent.getStringExtra("kResultValue");
            String stringExtra2 = intent.getStringExtra("kTargetField");
            int intExtra = intent.getIntExtra("kSelectionField", -1);
            if (stringExtra != null && !stringExtra.isEmpty() && stringExtra2 != null && !stringExtra2.isEmpty()) {
                if ("field1".equals(stringExtra2)) {
                    if (intExtra != -1) {
                        this.f2217q.setText(new StringBuffer(this.f2217q.getText().toString()).insert(intExtra, stringExtra).toString());
                        this.f2217q.setSelection(stringExtra.length() + intExtra);
                    } else {
                        this.f2217q.setText(this.f2217q.getText().toString() + stringExtra);
                        this.f2217q.setSelection(this.f2217q.length());
                    }
                }
                if (!"field2".equals(stringExtra2)) {
                    return;
                }
                if (intExtra != -1) {
                    Object replace = stringExtra.replace("{VAR_", "").replace("}", "");
                    this.f2219s.setText(replace);
                    this.f2219s.setSelection(replace.length());
                    return;
                }
                this.f2219s.setText(stringExtra);
                this.f2219s.setSelection(this.f2219s.length());
            }
        }
    }

    public void onAddParamsButtonClick(View view) {
        m2866a(null, null);
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
        setContentView(C0622e.task_httppost2var);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2217q = (EditText) findViewById(C0621d.myRequest);
        this.f2218r = (EditText) findViewById(C0621d.myParameters);
        this.f2220t = (LinearLayout) findViewById(C0621d.myPostParams);
        this.f2221u = new ArrayList();
        this.f2219s = (EditText) findViewById(C0621d.myVar);
        C05831 c05831 = new C05831(this);
        this.f2219s.setFilters(new InputFilter[]{c05831, new AllCaps()});
        m2861j();
        m2862k();
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
        this.f2221u.remove(hashMap);
        viewGroup.removeView(linearLayout);
    }

    public void onSelectVarsButtonClick1(View view) {
        Intent intent = new Intent(this, ChooseVarsActivity.class);
        intent.putExtra("kTargetField", "field1");
        intent.putExtra("kSelectionField", this.f2217q.getSelectionStart());
        startActivityForResult(intent, 1);
        overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
    }

    public void onSelectVarsButtonClick2(View view) {
        Intent intent;
        if (C0507a.m2175a().m2214m()) {
            intent = new Intent();
            intent.setAction("com.wakdev.droidautomation.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field2");
                intent.putExtra("kSelectionField", this.f2219s.getSelectionStart());
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e) {
                C0493f.m2081a(this, getString(C0625h.error));
            }
        } else if (C0499j.m2096a("com.wakdev.nfctasks")) {
            intent = new Intent();
            intent.setAction("com.wakdev.nfctasks.SELECT_USER_VARIABLE");
            try {
                intent.putExtra("kTargetField", "field2");
                intent.putExtra("kSelectionField", this.f2219s.getSelectionStart());
                startActivityForResult(intent, 1);
                overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
            } catch (Exception e2) {
                C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
            }
        } else {
            C0493f.m2081a(this, getString(C0625h.need_nfctasks));
        }
    }

    public void onValidateButtonClick(View view) {
        String m = m2864m();
        if (m != null) {
            String n = m2865n();
            Intent intent = new Intent();
            intent.putExtra("requestMode", 2);
            intent.putExtra("requestType", f2214n);
            intent.putExtra("itemTask", m);
            intent.putExtra("itemDescription", n);
            intent.putExtra("itemHash", this.f2216p);
            intent.putExtra("itemUpdate", this.f2215o);
            intent.putExtra("itemFields", m2863l());
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
    }
}
