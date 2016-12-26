package com.wakdev.nfctools;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import java.util.HashMap;

/* renamed from: com.wakdev.nfctools.e */
public class C0600e extends DialogFragment {
    private static int f2359b;
    private static HashMap<String, String> f2360c;
    public C0599a f2361a;
    private TextView f2362d;
    private ImageView f2363e;
    private Button f2364f;

    /* renamed from: com.wakdev.nfctools.e.1 */
    class C05981 implements OnClickListener {
        final /* synthetic */ C0600e f2358a;

        C05981(C0600e c0600e) {
            this.f2358a = c0600e;
        }

        public void onClick(View view) {
            if (this.f2358a.f2361a != null) {
                this.f2358a.f2361a.d_();
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.e.a */
    public interface C0599a {
        void d_();

        void m2932e();
    }

    static {
        f2359b = 0;
    }

    public static C0600e m2933a(int i, HashMap<String, String> hashMap) {
        C0600e c0600e = new C0600e();
        f2360c = hashMap;
        f2359b = i;
        return c0600e;
    }

    public static HashMap<String, String> m2934a(int i, String str, String str2) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("dialog_title", str);
        hashMap.put("dialog_text", str2);
        hashMap.put("dialog_image", String.valueOf(i));
        return hashMap;
    }

    public static HashMap<String, String> m2935a(int i, String str, String str2, String str3) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("dialog_title", str);
        hashMap.put("dialog_text", str2);
        hashMap.put("dialog_image", String.valueOf(i));
        hashMap.put("dialog_button", str3);
        return hashMap;
    }

    public static HashMap<String, String> m2936a(int i, String str, String str2, boolean z) {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("dialog_title", str);
        hashMap.put("dialog_text", str2);
        hashMap.put("dialog_image", String.valueOf(i));
        hashMap.put("dialog_animation", String.valueOf(z));
        return hashMap;
    }

    public void m2937a(C0599a c0599a) {
        this.f2361a = c0599a;
    }

    public void m2938a(String str) {
        if (str != null) {
            getDialog().setTitle(str);
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
        if (this.f2361a != null) {
            this.f2361a.m2932e();
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int parseInt;
        if (f2359b == 0) {
            f2359b = C0622e.dialog_info;
        }
        View inflate = layoutInflater.inflate(f2359b, viewGroup, false);
        this.f2364f = (Button) inflate.findViewById(C0621d.dialog_cancel_button);
        this.f2362d = (TextView) inflate.findViewById(C0621d.dialog_text);
        this.f2363e = (ImageView) inflate.findViewById(C0621d.dialog_image);
        this.f2364f.setOnClickListener(new C05981(this));
        String str = (String) f2360c.get("dialog_title");
        String str2 = (String) f2360c.get("dialog_text");
        String str3 = (String) f2360c.get("dialog_button");
        boolean booleanValue = Boolean.valueOf((String) f2360c.get("dialog_animation")).booleanValue();
        try {
            parseInt = Integer.parseInt((String) f2360c.get("dialog_image"));
        } catch (NumberFormatException e) {
            parseInt = 0;
        }
        if (str != null) {
            m2938a(str);
        }
        if (!(str2 == null || this.f2362d == null)) {
            this.f2362d.setText(str2);
        }
        if (!(parseInt == 0 || this.f2363e == null)) {
            if (booleanValue) {
                this.f2363e.setBackgroundResource(parseInt);
                ((AnimationDrawable) this.f2363e.getBackground()).start();
            } else {
                this.f2363e.setImageResource(parseInt);
            }
        }
        if (!(str3 == null || this.f2364f == null)) {
            this.f2364f.setText(str3);
        }
        return inflate;
    }
}
