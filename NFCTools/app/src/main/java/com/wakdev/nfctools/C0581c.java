package com.wakdev.nfctools;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import java.util.HashMap;

/* renamed from: com.wakdev.nfctools.c */
public class C0581c extends DialogFragment {
    private static int f2200b;
    private static HashMap<String, String> f2201c;
    public C0551a f2202a;
    private TextView f2203d;
    private Button f2204e;
    private Button f2205f;
    private Button f2206g;

    /* renamed from: com.wakdev.nfctools.c.a */
    public interface C0551a {
        void m2548a(HashMap<String, String> hashMap);

        void m2549b();

        void m2550b(HashMap<String, String> hashMap);

        void i_();
    }

    /* renamed from: com.wakdev.nfctools.c.1 */
    class C05781 implements OnClickListener {
        final /* synthetic */ C0581c f2197a;

        C05781(C0581c c0581c) {
            this.f2197a = c0581c;
        }

        public void onClick(View view) {
            if (this.f2197a.f2202a != null) {
                this.f2197a.f2202a.m2549b();
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.c.2 */
    class C05792 implements OnClickListener {
        final /* synthetic */ C0581c f2198a;

        C05792(C0581c c0581c) {
            this.f2198a = c0581c;
        }

        public void onClick(View view) {
            if (this.f2198a.f2202a != null) {
                this.f2198a.f2202a.m2548a(C0581c.f2201c);
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.c.3 */
    class C05803 implements OnClickListener {
        final /* synthetic */ C0581c f2199a;

        C05803(C0581c c0581c) {
            this.f2199a = c0581c;
        }

        public void onClick(View view) {
            if (this.f2199a.f2202a != null) {
                this.f2199a.f2202a.m2550b(C0581c.f2201c);
            }
        }
    }

    static {
        f2200b = 0;
    }

    public static C0581c m2855a(int i, HashMap<String, String> hashMap) {
        C0581c c0581c = new C0581c();
        f2201c = hashMap;
        f2200b = i;
        return c0581c;
    }

    public void m2857a(C0551a c0551a) {
        this.f2202a = c0551a;
    }

    public void m2858a(String str) {
        getDialog().setTitle(str);
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f2202a != null) {
            this.f2202a.i_();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int intValue;
        if (f2200b == 0) {
            f2200b = C0622e.dialog_app;
        }
        View inflate = layoutInflater.inflate(f2200b, viewGroup, false);
        this.f2205f = (Button) inflate.findViewById(C0621d.download_button);
        if (C0507a.m2176g() == 2) {
            this.f2205f.setText(getString(C0625h.download_app_button_amazon));
        }
        this.f2206g = (Button) inflate.findViewById(C0621d.select_button);
        this.f2204e = (Button) inflate.findViewById(C0621d.dialog_cancel_button);
        this.f2203d = (TextView) inflate.findViewById(C0621d.app_description);
        this.f2204e.setOnClickListener(new C05781(this));
        this.f2205f.setOnClickListener(new C05792(this));
        this.f2206g.setOnClickListener(new C05803(this));
        String str = (String) f2201c.get("dialog_title");
        String str2 = (String) f2201c.get("dialog_description");
        String str3 = (String) f2201c.get("dialog_show_button_download");
        String str4 = (String) f2201c.get("dialog_show_button_select");
        try {
            intValue = Integer.valueOf((String) f2201c.get("dialog_image")).intValue();
        } catch (NumberFormatException e) {
            intValue = -1;
        }
        if (str != null) {
            m2858a(str);
        }
        if (str2 != null) {
            this.f2203d.setText(str2);
        }
        if (intValue != -1) {
            this.f2203d.setCompoundDrawablesWithIntrinsicBounds(intValue, 0, 0, 0);
        }
        if (!Boolean.parseBoolean(str3)) {
            this.f2205f.setVisibility(8);
        }
        if (!Boolean.parseBoolean(str4)) {
            this.f2206g.setVisibility(8);
        }
        return inflate;
    }
}
