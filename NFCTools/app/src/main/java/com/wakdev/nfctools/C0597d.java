package com.wakdev.nfctools;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import java.util.HashMap;

/* renamed from: com.wakdev.nfctools.d */
public class C0597d extends DialogFragment {
    private static int f2349b;
    private static HashMap<String, String> f2350c;
    public C0596a f2351a;
    private Button f2352d;
    private Button f2353e;
    private Button f2354f;
    private Button f2355g;
    private Button f2356h;
    private Button f2357i;

    /* renamed from: com.wakdev.nfctools.d.1 */
    class C05901 implements OnClickListener {
        final /* synthetic */ C0597d f2343a;

        C05901(C0597d c0597d) {
            this.f2343a = c0597d;
        }

        public void onClick(View view) {
            if (this.f2343a.f2351a != null) {
                this.f2343a.f2351a.m2924a(C0597d.f2350c);
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.d.2 */
    class C05912 implements OnClickListener {
        final /* synthetic */ C0597d f2344a;

        C05912(C0597d c0597d) {
            this.f2344a = c0597d;
        }

        public void onClick(View view) {
            if (this.f2344a.f2351a != null) {
                this.f2344a.f2351a.m2926c(C0597d.f2350c);
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.d.3 */
    class C05923 implements OnClickListener {
        final /* synthetic */ C0597d f2345a;

        C05923(C0597d c0597d) {
            this.f2345a = c0597d;
        }

        public void onClick(View view) {
            if (this.f2345a.f2351a != null) {
                this.f2345a.f2351a.m2925b(C0597d.f2350c);
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.d.4 */
    class C05934 implements OnClickListener {
        final /* synthetic */ C0597d f2346a;

        C05934(C0597d c0597d) {
            this.f2346a = c0597d;
        }

        public void onClick(View view) {
            if (this.f2346a.f2351a != null) {
                this.f2346a.f2351a.m2927d(C0597d.f2350c);
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.d.5 */
    class C05945 implements OnClickListener {
        final /* synthetic */ C0597d f2347a;

        C05945(C0597d c0597d) {
            this.f2347a = c0597d;
        }

        public void onClick(View view) {
            if (this.f2347a.f2351a != null) {
                this.f2347a.f2351a.m2928e(C0597d.f2350c);
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.d.6 */
    class C05956 implements OnClickListener {
        final /* synthetic */ C0597d f2348a;

        C05956(C0597d c0597d) {
            this.f2348a = c0597d;
        }

        public void onClick(View view) {
            if (this.f2348a.f2351a != null) {
                this.f2348a.f2351a.h_();
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.d.a */
    public interface C0596a {
        void m2924a(HashMap<String, String> hashMap);

        void m2925b(HashMap<String, String> hashMap);

        void m2926c(HashMap<String, String> hashMap);

        void m2927d(HashMap<String, String> hashMap);

        void m2928e(HashMap<String, String> hashMap);

        void g_();

        void h_();
    }

    static {
        f2349b = 0;
    }

    public static C0597d m2929a(int i, HashMap<String, String> hashMap) {
        C0597d c0597d = new C0597d();
        f2350c = hashMap;
        f2349b = i;
        return c0597d;
    }

    public void m2931a(C0596a c0596a) {
        this.f2351a = c0596a;
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f2351a != null) {
            this.f2351a.g_();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (f2349b == 0) {
            f2349b = C0622e.dialog_record;
        }
        View inflate = layoutInflater.inflate(f2349b, viewGroup, false);
        this.f2353e = (Button) inflate.findViewById(C0621d.dialog_edit_button);
        this.f2354f = (Button) inflate.findViewById(C0621d.dialog_duplicate_button);
        this.f2355g = (Button) inflate.findViewById(C0621d.dialog_up_button);
        this.f2356h = (Button) inflate.findViewById(C0621d.dialog_down_button);
        this.f2357i = (Button) inflate.findViewById(C0621d.dialog_delete_button);
        this.f2352d = (Button) inflate.findViewById(C0621d.dialog_cancel_button);
        this.f2353e.setOnClickListener(new C05901(this));
        this.f2354f.setOnClickListener(new C05912(this));
        this.f2355g.setOnClickListener(new C05923(this));
        this.f2356h.setOnClickListener(new C05934(this));
        this.f2357i.setOnClickListener(new C05945(this));
        this.f2352d.setOnClickListener(new C05956(this));
        if (Boolean.valueOf((String) f2350c.get("dialog_hide_up")).booleanValue()) {
            this.f2355g.setVisibility(8);
        }
        if (Boolean.valueOf((String) f2350c.get("dialog_hide_down")).booleanValue()) {
            this.f2356h.setVisibility(8);
        }
        if (Boolean.valueOf((String) f2350c.get("dialog_hide_edit")).booleanValue()) {
            this.f2353e.setVisibility(8);
        }
        if (Boolean.valueOf((String) f2350c.get("dialog_hide_duplicate")).booleanValue()) {
            this.f2354f.setVisibility(8);
        }
        getDialog().getWindow().requestFeature(1);
        return inflate;
    }
}
