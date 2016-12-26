package com.wakdev.nfctools;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import java.util.HashMap;

/* renamed from: com.wakdev.nfctools.b */
public class C0570b extends DialogFragment {
    private static int f2015b;
    private static HashMap<String, String> f2016c;
    public C0525a f2017a;
    private TextView f2018d;
    private Button f2019e;
    private Button f2020f;
    private Button f2021g;

    /* renamed from: com.wakdev.nfctools.b.a */
    public interface C0525a {
        void m2299a(HashMap<String, String> hashMap);

        void m2300b(HashMap<String, String> hashMap);

        void m2301c();

        void m2302d();
    }

    /* renamed from: com.wakdev.nfctools.b.1 */
    class C05671 implements OnClickListener {
        final /* synthetic */ C0570b f2012a;

        C05671(C0570b c0570b) {
            this.f2012a = c0570b;
        }

        public void onClick(View view) {
            this.f2012a.f2017a.m2302d();
        }
    }

    /* renamed from: com.wakdev.nfctools.b.2 */
    class C05682 implements OnClickListener {
        final /* synthetic */ C0570b f2013a;

        C05682(C0570b c0570b) {
            this.f2013a = c0570b;
        }

        public void onClick(View view) {
            this.f2013a.f2017a.m2299a(C0570b.f2016c);
        }
    }

    /* renamed from: com.wakdev.nfctools.b.3 */
    class C05693 implements OnClickListener {
        final /* synthetic */ C0570b f2014a;

        C05693(C0570b c0570b) {
            this.f2014a = c0570b;
        }

        public void onClick(View view) {
            this.f2014a.f2017a.m2300b(C0570b.f2016c);
        }
    }

    static {
        f2015b = 0;
    }

    public static C0570b m2741a(int i, HashMap<String, String> hashMap) {
        C0570b c0570b = new C0570b();
        f2016c = hashMap;
        f2015b = i;
        return c0570b;
    }

    public void m2743a(C0525a c0525a) {
        this.f2017a = c0525a;
    }

    public void m2744a(String str) {
        getDialog().setTitle(str);
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f2017a != null) {
            this.f2017a.m2301c();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (f2015b == 0) {
            f2015b = C0622e.dialog_action;
        }
        View inflate = layoutInflater.inflate(f2015b, viewGroup, false);
        this.f2020f = (Button) inflate.findViewById(C0621d.open_button);
        this.f2021g = (Button) inflate.findViewById(C0621d.copy_to_clipboard_button);
        this.f2019e = (Button) inflate.findViewById(C0621d.dialog_cancel_button);
        this.f2018d = (TextView) inflate.findViewById(C0621d.action_description);
        this.f2018d.setMovementMethod(new ScrollingMovementMethod());
        this.f2019e.setOnClickListener(new C05671(this));
        this.f2020f.setOnClickListener(new C05682(this));
        this.f2021g.setOnClickListener(new C05693(this));
        if (f2016c.get("dialog_action") == null) {
            this.f2020f.setVisibility(8);
        }
        if (!(f2016c.get("dialog_action") == null || f2016c.get("dialog_title_open") == null)) {
            this.f2020f.setText((CharSequence) f2016c.get("dialog_title_open"));
        }
        String str = (String) f2016c.get("dialog_title");
        String str2 = (String) f2016c.get("dialog_description");
        if (str != null) {
            m2744a(str);
        }
        if (str2 != null) {
            this.f2018d.setText(str2);
        }
        return inflate;
    }
}
