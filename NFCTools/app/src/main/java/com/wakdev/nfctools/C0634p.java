package com.wakdev.nfctools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0597d.C0596a;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0459d;
import com.wakdev.wdsortablelist.DragSortListView;
import com.wakdev.wdsortablelist.DragSortListView.C0631h;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.wakdev.nfctools.p */
public class C0634p extends Fragment implements C0596a {
    private DragSortListView aa;
    private C0459d ab;
    private View ac;
    private C0507a ad;
    private boolean ae;
    private C0631h af;
    private OnItemClickListener ag;

    /* renamed from: com.wakdev.nfctools.p.1 */
    class C06321 implements C0631h {
        final /* synthetic */ C0634p f2408a;

        C06321(C0634p c0634p) {
            this.f2408a = c0634p;
        }

        public void a_(int i, int i2) {
            C0446a c0446a = (C0446a) this.f2408a.ab.getItem(i);
            this.f2408a.ab.m1857a(c0446a);
            this.f2408a.ab.m1858a(c0446a, i2);
            this.f2408a.ab.notifyDataSetChanged();
        }
    }

    /* renamed from: com.wakdev.nfctools.p.2 */
    class C06332 implements OnItemClickListener {
        final /* synthetic */ C0634p f2409a;

        C06332(C0634p c0634p) {
            this.f2409a = c0634p;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f2409a.m3037a((C0446a) this.f2409a.ab.getItem(i));
        }
    }

    public C0634p() {
        this.ae = false;
        this.af = new C06321(this);
        this.ag = new C06332(this);
    }

    private void m3037a(C0446a c0446a) {
        C0604l c0604l = (C0604l) m56b();
        String i = c0446a.m1820i();
        HashMap hashMap = new HashMap();
        C0481m a = C0481m.m2026a(c0446a.m1819h());
        hashMap.put("dialog_hash_record", i);
        if (a == C0481m.TASK_RUN_TOOL || a == C0481m.TASK_MISC_OK_GOOGLE || a == C0481m.TASK_CONFIG_OPEN_SETTINGS || a == C0481m.TASK_COND_END || a == C0481m.TASK_MISC_GO_HOME || a == C0481m.TASK_CONFIG_INPUT_METHOD || a == C0481m.TASK_DEV_EXIT || a == C0481m.TASK_REBOOT_DEVICE || a == C0481m.TASK_SHUTDOWN_DEVICE || a == C0481m.TASK_LOCKSCREEN || a == C0481m.TASK_CONFIG_SVOICE || a == C0481m.TASK_CONFIG_SPLANNER || a == C0481m.TASK_END_CALL || a == C0481m.TASK_ALARM_DISMISS_ALL || a == C0481m.TASK_SCREEN_START_SCREENSAVER || a == C0481m.TASK_SOUND_STOP_MEDIA || a == C0481m.TASK_COND_ELSE) {
            hashMap.put("dialog_hide_edit", String.valueOf(true));
        }
        hashMap.put("dialog_hide_up", String.valueOf(true));
        hashMap.put("dialog_hide_down", String.valueOf(true));
        c0604l.m3004d(C0622e.dialog_record, hashMap);
    }

    public boolean m3039I() {
        return this.ae;
    }

    public View m3040a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ac = layoutInflater.inflate(C0622e.section3, viewGroup, false);
        Button button = (Button) this.ac.findViewById(C0621d.download_tasks_button);
        this.ad = C0507a.m2175a();
        if (C0499j.m2096a("com.wakdev.nfctasks")) {
            button.setVisibility(8);
        }
        this.ae = false;
        return this.ac;
    }

    public void m3041a(ArrayList<C0446a> arrayList) {
        this.aa = (DragSortListView) this.ac.findViewById(C0621d.mylistview_section3);
        this.aa.setDropListener(this.af);
        this.aa.setOnItemClickListener(this.ag);
        this.ab = new C0459d(this.ac.getContext(), arrayList);
        this.aa.setAdapter(this.ab);
        this.ae = true;
    }

    public void m3042a(HashMap<String, String> hashMap) {
        C0446a b = this.ad.m2188b((String) hashMap.get("dialog_hash_record"));
        if (b != null) {
            ((C0604l) m56b()).m3010f(b);
        }
        h_();
    }

    public void m3043b(HashMap<String, String> hashMap) {
        C0446a b = this.ad.m2188b((String) hashMap.get("dialog_hash_record"));
        if (b != null) {
            ((C0604l) m56b()).m3013g(b);
        }
        h_();
    }

    public void m3044c(HashMap<String, String> hashMap) {
        C0446a b = this.ad.m2188b((String) hashMap.get("dialog_hash_record"));
        if (b != null) {
            ((C0604l) m56b()).m3016h(b);
        }
        h_();
    }

    public void m3045d(HashMap<String, String> hashMap) {
        C0446a b = this.ad.m2188b((String) hashMap.get("dialog_hash_record"));
        if (b != null) {
            ((C0604l) m56b()).m3017i(b);
        }
        h_();
    }

    public void m3046e(HashMap<String, String> hashMap) {
        C0446a b = this.ad.m2188b((String) hashMap.get("dialog_hash_record"));
        if (b != null) {
            ((C0604l) m56b()).m3018j(b);
        }
        h_();
    }

    public void g_() {
    }

    public void h_() {
        ((C0604l) m56b()).m3014h();
    }
}
