package com.wakdev.nfctools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0597d.C0596a;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0459d;
import com.wakdev.wdsortablelist.DragSortListView;
import com.wakdev.wdsortablelist.DragSortListView.C0631h;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.wakdev.nfctools.q */
public class C0637q extends Fragment implements C0596a {
    private DragSortListView aa;
    private C0459d ab;
    private View ac;
    private boolean ad;
    private C0507a ae;
    private C0631h af;
    private OnItemClickListener ag;

    /* renamed from: com.wakdev.nfctools.q.1 */
    class C06351 implements C0631h {
        final /* synthetic */ C0637q f2410a;

        C06351(C0637q c0637q) {
            this.f2410a = c0637q;
        }

        public void a_(int i, int i2) {
            C0446a c0446a = (C0446a) this.f2410a.ab.getItem(i);
            this.f2410a.ab.m1857a(c0446a);
            this.f2410a.ab.m1858a(c0446a, i2);
            this.f2410a.ab.notifyDataSetChanged();
        }
    }

    /* renamed from: com.wakdev.nfctools.q.2 */
    class C06362 implements OnItemClickListener {
        final /* synthetic */ C0637q f2411a;

        C06362(C0637q c0637q) {
            this.f2411a = c0637q;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f2411a.m3048a((C0446a) this.f2411a.ab.getItem(i));
        }
    }

    public C0637q() {
        this.ad = false;
        this.af = new C06351(this);
        this.ag = new C06362(this);
    }

    private void m3048a(C0446a c0446a) {
        C0604l c0604l = (C0604l) m56b();
        String i = c0446a.m1820i();
        HashMap hashMap = new HashMap();
        this.ae.m2203e(c0446a);
        this.ae.m2216p();
        hashMap.put("dialog_hash_record", i);
        hashMap.put("dialog_hide_up", String.valueOf(true));
        hashMap.put("dialog_hide_down", String.valueOf(true));
        c0604l.m2999c(C0622e.dialog_record, hashMap);
    }

    public boolean m3050I() {
        return this.ad;
    }

    public View m3051a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ac = layoutInflater.inflate(C0622e.section1, viewGroup, false);
        this.ad = false;
        this.ae = C0507a.m2175a();
        return this.ac;
    }

    public void m3052a(ArrayList<C0446a> arrayList) {
        this.aa = (DragSortListView) this.ac.findViewById(C0621d.mylistview_section1);
        this.aa.setDropListener(this.af);
        this.aa.setOnItemClickListener(this.ag);
        this.ab = new C0459d(this.ac.getContext(), arrayList);
        this.aa.setAdapter(this.ab);
        this.ad = true;
    }

    public void m3053a(HashMap<String, String> hashMap) {
        C0446a a = this.ae.m2179a((String) hashMap.get("dialog_hash_record"));
        if (a != null) {
            ((C0604l) m56b()).m3008e(a);
        }
        h_();
    }

    public void m3054b(HashMap<String, String> hashMap) {
        C0446a a = this.ae.m2179a((String) hashMap.get("dialog_hash_record"));
        if (a != null) {
            ((C0604l) m56b()).m3000c(a);
        }
        h_();
    }

    public void m3055c(HashMap<String, String> hashMap) {
        C0446a a = this.ae.m2179a((String) hashMap.get("dialog_hash_record"));
        if (a != null) {
            ((C0604l) m56b()).m2994b(a);
        }
        h_();
    }

    public void m3056d(HashMap<String, String> hashMap) {
        C0446a a = this.ae.m2179a((String) hashMap.get("dialog_hash_record"));
        if (a != null) {
            ((C0604l) m56b()).m3005d(a);
        }
        h_();
    }

    public void m3057e(HashMap<String, String> hashMap) {
        C0446a a = this.ae.m2179a((String) hashMap.get("dialog_hash_record"));
        if (a != null) {
            ((C0604l) m56b()).m2987a(a);
        }
        h_();
    }

    public void g_() {
    }

    public void h_() {
        ((C0604l) m56b()).m3011g();
    }
}
