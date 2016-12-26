package com.wakdev.nfctools;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import com.wakdev.libs.commons.C0489d;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0503m;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.WDCore;
import com.wakdev.libs.p015a.C0470b;
import com.wakdev.nfctools.C0570b.C0525a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0623f;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;
import java.util.HashMap;

public class DisplayTagMemoryActivity extends C0316b implements C0447b, C0525a {
    private ListView f1262n;
    private C0454c f1263o;
    private C0570b f1264p;
    private Toolbar f1265q;
    private byte[] f1266r;
    private int f1267s;
    private int f1268t;
    private int f1269u;
    private HashMap<Integer, String> f1270v;

    /* renamed from: com.wakdev.nfctools.DisplayTagMemoryActivity.1 */
    class C05221 implements OnClickListener {
        final /* synthetic */ DisplayTagMemoryActivity f1257a;

        C05221(DisplayTagMemoryActivity displayTagMemoryActivity) {
            this.f1257a = displayTagMemoryActivity;
        }

        public void onClick(View view) {
            this.f1257a.onBackPressed();
        }
    }

    /* renamed from: com.wakdev.nfctools.DisplayTagMemoryActivity.2 */
    class C05232 implements OnMenuItemClickListener {
        final /* synthetic */ DisplayTagMemoryActivity f1258a;

        C05232(DisplayTagMemoryActivity displayTagMemoryActivity) {
            this.f1258a = displayTagMemoryActivity;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            int itemId = menuItem.getItemId();
            if (itemId == C0621d.menu_show_hex) {
                this.f1258a.f1269u = 0;
                this.f1258a.f1265q.setTitle(this.f1258a.getString(C0625h.read_memory) + " : HEX");
                new C0524a(null).execute(new Void[0]);
                return true;
            } else if (itemId == C0621d.menu_show_utf8) {
                this.f1258a.f1269u = 1;
                this.f1258a.f1265q.setTitle(this.f1258a.getString(C0625h.read_memory) + " : UTF8");
                new C0524a(null).execute(new Void[0]);
                return true;
            } else if (itemId == C0621d.menu_show_ascii) {
                this.f1258a.f1269u = 2;
                this.f1258a.f1265q.setTitle(this.f1258a.getString(C0625h.read_memory) + " : US-ASCII");
                new C0524a(null).execute(new Void[0]);
                return true;
            } else if (itemId != C0621d.menu_export) {
                return false;
            } else {
                this.f1258a.m2317j();
                return true;
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.DisplayTagMemoryActivity.a */
    private class C0524a extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ DisplayTagMemoryActivity f1259a;
        private ProgressDialog f1260b;
        private C0454c f1261c;

        private C0524a(DisplayTagMemoryActivity displayTagMemoryActivity) {
            this.f1259a = displayTagMemoryActivity;
            this.f1260b = null;
            this.f1261c = null;
        }

        private C0446a m2295a(int i, int i2, String str, String str2) {
            C0446a c0446a = new C0446a();
            c0446a.m1811c(i);
            c0446a.m1802a(i2);
            c0446a.m1807b(C0620c.action_menu_vertical_black);
            c0446a.m1804a(str);
            c0446a.m1808b(str2);
            return c0446a;
        }

        protected Void m2296a(Void... voidArr) {
            if (this.f1259a.f1266r != null) {
                Context applicationContext = WDCore.m2174a().getApplicationContext();
                String a = C0489d.m2063a(this.f1259a.f1266r);
                ArrayList arrayList = new ArrayList();
                int length = a.length();
                int i = 0;
                int i2 = 0;
                while (i2 < length) {
                    String str;
                    int c = (this.f1259a.f1267s * 2) + i2;
                    int i3 = c > length ? length : c;
                    String num = Integer.toString(i, 16);
                    if (num.length() == 1) {
                        num = "0" + num;
                    }
                    num = this.f1259a.getString(C0625h.memory_sector_title) + " " + num.toUpperCase();
                    int i4 = C0620c.memory_icon_black;
                    if (this.f1259a.f1270v.get(Integer.valueOf(i)) != null) {
                        int i5 = i4;
                        str = num + " : " + ((String) this.f1259a.f1270v.get(Integer.valueOf(i)));
                        c = i5;
                    } else {
                        str = num + " : DATA";
                        c = C0620c.memory_icon;
                    }
                    String substring = a.substring(i2, i3);
                    switch (this.f1259a.f1269u) {
                        case C0627j.View_paddingStart /*1*/:
                            substring = C0489d.m2071d(substring);
                            break;
                        case C0627j.View_paddingEnd /*2*/:
                            substring = C0489d.m2072e(substring);
                            break;
                        default:
                            substring = C0489d.m2062a(substring, ":", 2, false);
                            break;
                    }
                    arrayList.add(m2295a(i, c, str, substring));
                    i++;
                    i2 += this.f1259a.f1267s * 2;
                }
                if (applicationContext != null) {
                    this.f1261c = new C0454c(applicationContext, arrayList);
                }
            }
            return null;
        }

        protected void m2297a(Void voidR) {
            this.f1259a.m2311a(this.f1261c);
            try {
                this.f1260b.dismiss();
            } catch (Exception e) {
            }
            super.onPostExecute(voidR);
        }

        protected void m2298b(Void... voidArr) {
            super.onProgressUpdate(voidArr);
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m2296a((Void[]) objArr);
        }

        protected void onCancelled() {
            super.onCancelled();
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m2297a((Void) obj);
        }

        protected void onPreExecute() {
            this.f1260b = ProgressDialog.show(this.f1259a, null, "Loading ...");
            super.onPreExecute();
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            m2298b((Void[]) objArr);
        }
    }

    public DisplayTagMemoryActivity() {
        this.f1266r = null;
        this.f1267s = 4;
        this.f1268t = -1;
        this.f1269u = 0;
        this.f1270v = null;
    }

    public void m2309a(int i, HashMap<String, String> hashMap) {
        if (this.f1264p != null) {
            this.f1264p.dismiss();
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("actionDialog");
        if (findFragmentByTag != null) {
            beginTransaction.remove(findFragmentByTag);
        }
        if (hashMap.get("dialog_title") == null) {
            hashMap.put("dialog_title", getString(C0625h.dialog_action_title_default));
        }
        if (i == 0) {
            i = C0622e.dialog_action;
        }
        this.f1264p = null;
        this.f1264p = C0570b.m2741a(i, hashMap);
        this.f1264p.m2743a((C0525a) this);
        this.f1264p.show(beginTransaction, "actionDialog");
    }

    public void m2310a(C0446a c0446a) {
        HashMap hashMap = new HashMap();
        hashMap.put("dialog_title", c0446a.m1821j());
        int i = C0622e.dialog_action;
        hashMap.put("dialog_description", c0446a.m1822k());
        m2309a(i, hashMap);
    }

    public void m2311a(C0454c c0454c) {
        if (c0454c != null) {
            this.f1262n = (ListView) findViewById(C0621d.mylistview_memory);
            this.f1263o = c0454c;
            this.f1262n.setAdapter(this.f1263o);
            this.f1263o.m1842a((C0447b) this);
            return;
        }
        C0493f.m2081a(this, "Error when retrieving the list!");
    }

    public void m2312a(HashMap<String, String> hashMap) {
    }

    public void m2313b(C0446a c0446a) {
        m2310a(c0446a);
    }

    public void m2314b(HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("dialog_description");
        if (str != null) {
            this.f1264p.dismiss();
            C0503m.m2107a(str);
            C0493f.m2081a(this, getString(C0625h.copied_to_clipboard));
        }
    }

    public void m2315c() {
    }

    public void m2316d() {
        this.f1264p.dismiss();
    }

    public void m2317j() {
        try {
            String str = "";
            for (int i = 0; i < this.f1262n.getCount(); i++) {
                C0446a c0446a = (C0446a) this.f1262n.getItemAtPosition(i);
                str = str + "[ " + c0446a.m1822k() + " ] " + c0446a.m1821j() + "\n";
            }
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", str);
            intent.setType("text/plain");
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            C0493f.m2081a(this, "Error while exporting!");
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.display_tag_memory);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        this.f1265q = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        this.f1265q.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        this.f1265q.setNavigationOnClickListener(new C05221(this));
        try {
            if (!C0507a.m2177n()) {
                this.f1265q.inflateMenu(C0623f.memory_menu);
            }
        } catch (Exception e) {
        }
        this.f1265q.setOnMenuItemClickListener(new C05232(this));
        this.f1265q.setTitle(getString(C0625h.read_memory) + " : HEX");
        this.f1266r = getIntent().getByteArrayExtra("memory_bytes");
        this.f1267s = getIntent().getIntExtra("sector_size", 4);
        this.f1268t = getIntent().getIntExtra("tag_tech", -1);
        this.f1270v = C0470b.m1904a(this.f1268t);
        new C0524a().execute(new Void[0]);
    }
}
