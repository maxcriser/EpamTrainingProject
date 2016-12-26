package com.wakdev.nfctools;

import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.C0037f;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.C0122f;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.Toolbar.OnMenuItemClickListener;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.wakdev.libs.commons.C0486a;
import com.wakdev.libs.commons.C0488c;
import com.wakdev.libs.commons.C0489d;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0494g;
import com.wakdev.libs.commons.C0495h;
import com.wakdev.libs.commons.C0499j;
import com.wakdev.libs.commons.C0500k;
import com.wakdev.libs.commons.C0503m;
import com.wakdev.libs.commons.C0505n;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.WDCore;
import com.wakdev.libs.p015a.C0469a;
import com.wakdev.libs.p015a.C0471c;
import com.wakdev.libs.p015a.C0472d;
import com.wakdev.libs.p015a.C0476h;
import com.wakdev.libs.p015a.C0478j;
import com.wakdev.libs.p015a.C0479k;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.libs.p015a.p016a.C0468c;
import com.wakdev.nfctools.C0570b.C0525a;
import com.wakdev.nfctools.C0600e.C0599a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0623f;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0626i;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.p014a.C0446a;
import com.wakdev.wdtabs.SlidingTabStrip;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.wakdev.nfctools.l */
public class C0604l extends C0037f implements C0122f, OnMenuItemClickListener, C0471c, C0525a, C0599a {
    private static final String f2373p;
    private C0597d f2374A;
    private C0597d f2375B;
    private C0638r f2376C;
    private ViewPager f2377D;
    private String f2378E;
    private Handler f2379F;
    private Runnable f2380G;
    private final int f2381H;
    private SlidingTabStrip f2382I;
    public C0469a f2383n;
    private C0507a f2384o;
    private C0630o f2385q;
    private C0637q f2386r;
    private C0634p f2387s;
    private Button f2388t;
    private Button f2389u;
    private View f2390v;
    private View f2391w;
    private C0478j f2392x;
    private C0600e f2393y;
    private C0570b f2394z;

    /* renamed from: com.wakdev.nfctools.l.1 */
    class C06101 implements OnClickListener {
        final /* synthetic */ C0604l f2397a;

        C06101(C0604l c0604l) {
            this.f2397a = c0604l;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    /* renamed from: com.wakdev.nfctools.l.2 */
    class C06112 implements Runnable {
        final /* synthetic */ C0604l f2398a;

        C06112(C0604l c0604l) {
            this.f2398a = c0604l;
        }

        public void run() {
            this.f2398a.f2385q.m3034a(this.f2398a.f2384o.m2217q());
        }
    }

    /* renamed from: com.wakdev.nfctools.l.3 */
    class C06123 implements Runnable {
        final /* synthetic */ C0604l f2399a;

        C06123(C0604l c0604l) {
            this.f2399a = c0604l;
        }

        public void run() {
            this.f2399a.f2386r.m3052a(this.f2399a.f2384o.m2215o());
            this.f2399a.m2978m();
        }
    }

    /* renamed from: com.wakdev.nfctools.l.4 */
    class C06134 implements Runnable {
        final /* synthetic */ C0604l f2400a;

        C06134(C0604l c0604l) {
            this.f2400a = c0604l;
        }

        public void run() {
            this.f2400a.f2387s.m3041a(this.f2400a.f2384o.m2222v());
            this.f2400a.m2977l();
        }
    }

    /* renamed from: com.wakdev.nfctools.l.5 */
    class C06145 implements Runnable {
        final /* synthetic */ C0478j f2401a;
        final /* synthetic */ C0604l f2402b;

        C06145(C0604l c0604l, C0478j c0478j) {
            this.f2402b = c0604l;
            this.f2401a = c0478j;
        }

        public void run() {
            this.f2402b.f2385q = (C0630o) this.f2402b.f2376C.m3063e(0);
            if (this.f2402b.f2385q != null) {
                this.f2402b.m2989a(this.f2401a);
                this.f2402b.f2379F.removeCallbacks(this.f2402b.f2380G);
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.l.6 */
    class C06156 implements OnClickListener {
        final /* synthetic */ C0604l f2403a;

        C06156(C0604l c0604l) {
            this.f2403a = c0604l;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    this.f2403a.f2384o.m2200d();
                    this.f2403a.f2384o.m2180a(4);
                    this.f2403a.m2986a(C0622e.dialog_lock, C0600e.m2936a(C0620c.anim_approach, this.f2403a.getString(C0625h.lock_tag), this.f2403a.getString(C0625h.approach_nfc_tag), true));
                default:
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.l.7 */
    class C06167 implements OnClickListener {
        final /* synthetic */ EditText f2404a;
        final /* synthetic */ C0604l f2405b;

        C06167(C0604l c0604l, EditText editText) {
            this.f2405b = c0604l;
            this.f2404a = editText;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    this.f2405b.f2384o.m2200d();
                    this.f2405b.f2384o.m2180a(11);
                    this.f2405b.f2378E = this.f2404a.getText().toString();
                    if (this.f2405b.f2378E.isEmpty()) {
                        C0493f.m2082a(this.f2405b.getString(C0625h.err_password_empty));
                    } else {
                        this.f2405b.m2986a(C0622e.dialog_info, C0600e.m2936a(C0620c.anim_approach, this.f2405b.getString(C0625h.set_password), this.f2405b.getString(C0625h.approach_nfc_tag), true));
                    }
                default:
            }
        }
    }

    /* renamed from: com.wakdev.nfctools.l.8 */
    class C06178 implements OnClickListener {
        final /* synthetic */ EditText f2406a;
        final /* synthetic */ C0604l f2407b;

        C06178(C0604l c0604l, EditText editText) {
            this.f2407b = c0604l;
            this.f2406a = editText;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    this.f2407b.f2384o.m2200d();
                    this.f2407b.f2384o.m2180a(12);
                    this.f2407b.f2378E = this.f2406a.getText().toString();
                    if (this.f2407b.f2378E.isEmpty()) {
                        C0493f.m2082a(this.f2407b.getString(C0625h.err_password_empty));
                    } else {
                        this.f2407b.m2986a(C0622e.dialog_info, C0600e.m2936a(C0620c.anim_approach, this.f2407b.getString(C0625h.unset_password), this.f2407b.getString(C0625h.approach_nfc_tag), true));
                    }
                default:
            }
        }
    }

    static {
        f2373p = null;
    }

    public C0604l() {
        this.f2381H = 100;
    }

    private int m2951a(C0479k c0479k) {
        int i = C0620c.nfc_record;
        if (c0479k == null) {
            return i;
        }
        switch (c0479k.m2017j()) {
            case C0627j.View_paddingEnd /*2*/:
                return C0620c.nfc_type_text;
            case C0627j.Toolbar_subtitle /*3*/:
                return C0620c.nfc_type_uri;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                return C0620c.nfc_type_app;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                return C0620c.nfc_type_mail;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                return C0620c.nfc_type_contact;
            case C0627j.Toolbar_contentInsetRight /*7*/:
                return C0620c.nfc_type_tel;
            case C0627j.Toolbar_popupTheme /*8*/:
                return C0620c.nfc_type_sms;
            case C0627j.Toolbar_titleMarginStart /*12*/:
                return C0620c.nfc_type_geo;
            case C0627j.Toolbar_titleMarginEnd /*13*/:
                return C0620c.nfc_type_address;
            case C0627j.Toolbar_titleMarginTop /*14*/:
                return C0620c.nfc_type_bluetooth;
            case C0627j.Toolbar_titleMarginBottom /*15*/:
                return C0620c.task_wifi_network;
            case C0627j.Toolbar_maxButtonHeight /*16*/:
                return C0620c.record_social_facebook;
            case C0627j.Toolbar_theme /*17*/:
                return C0620c.record_social_twitter;
            case C0627j.Toolbar_collapseIcon /*18*/:
                return C0620c.record_social_googleplus;
            case C0627j.Toolbar_collapseContentDescription /*19*/:
                return C0620c.record_social_linkedin;
            case C0627j.Toolbar_navigationIcon /*20*/:
                return C0620c.record_social_pinterest;
            case C0627j.Toolbar_navigationContentDescription /*21*/:
                return C0620c.record_social_instagram;
            case C0627j.Theme_actionMenuTextColor /*22*/:
                return C0620c.record_social_tumblr;
            case C0627j.Theme_actionModeStyle /*23*/:
                return C0620c.record_social_github;
            case C0627j.Theme_actionModeCloseButtonStyle /*24*/:
                return C0620c.record_social_skype;
            case C0627j.Theme_actionModeBackground /*25*/:
                return C0486a.m2046b(C0481m.m2027a(c0479k.m2011e()));
            case C0627j.Theme_actionModeSplitBackground /*26*/:
                return C0620c.record_youtube;
            case C0627j.Theme_actionModeCloseDrawable /*27*/:
                return C0620c.record_vimeo;
            case C0627j.Theme_actionModeCutDrawable /*28*/:
                return C0620c.record_dailymotion;
            case C0627j.Theme_actionModeCopyDrawable /*29*/:
                return C0620c.record_bitcoin;
            case C0627j.Theme_actionModePasteDrawable /*30*/:
                return C0620c.record_social_dribbble;
            case C0627j.Theme_actionModeSelectAllDrawable /*31*/:
                return C0620c.record_social_flickr;
            case C0627j.Theme_actionModeShareDrawable /*32*/:
                return C0620c.record_social_reddit;
            case C0627j.Theme_actionModeFindDrawable /*33*/:
                return C0620c.record_social_slack;
            case C0627j.Theme_actionModeWebSearchDrawable /*34*/:
                return C0620c.record_social_snapchat;
            case C0627j.Theme_actionModePopupWindowStyle /*35*/:
                return C0620c.record_social_soundcloud;
            case C0627j.Theme_textAppearanceLargePopupMenu /*36*/:
                return C0620c.record_social_steam;
            case C0627j.Theme_textAppearanceSmallPopupMenu /*37*/:
                return C0620c.record_social_twitch;
            case C0627j.Theme_actionDropDownStyle /*38*/:
                return C0620c.record_poi;
            case C0627j.Theme_dropdownListPreferredItemHeight /*39*/:
                return C0620c.record_destination;
            case C0627j.Theme_spinnerStyle /*40*/:
                return C0620c.record_streetview;
            default:
                return i;
        }
    }

    private void m2955a(int i, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        this.f2386r = (C0637q) this.f2376C.m3063e(1);
        if (C0476h.m1923a(i) && this.f2386r != null) {
            C0479k c0479k = new C0479k();
            String str = (String) hashMap.get("itemRecord");
            String str2 = (String) hashMap.get("itemDescription");
            String str3 = (String) hashMap.get("itemHash");
            boolean parseBoolean = Boolean.parseBoolean((String) hashMap.get("itemUpdate"));
            C0446a c0446a = new C0446a();
            c0446a.m1811c(i);
            c0446a.m1814d(i);
            c0446a.m1805a((HashMap) hashMap2);
            c0446a.m1809b((HashMap) hashMap);
            c0446a.m1807b(C0620c.action_menu_vertical_black);
            switch (i) {
                case C0627j.View_paddingStart /*1*/:
                    c0479k.m2010d(str);
                    c0446a.m1802a(C0620c.nfc_type_text);
                    c0446a.m1804a(getString(C0625h.text) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.View_paddingEnd /*2*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_uri);
                    c0446a.m1804a(getString(C0625h.url) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    c0479k.m2002a(str);
                    c0446a.m1802a(C0620c.nfc_type_app);
                    c0446a.m1804a(getString(C0625h.app) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_contentInsetStart /*4*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_mail);
                    c0446a.m1804a(getString(C0625h.mail) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_contentInsetEnd /*5*/:
                    c0479k.m2004a("text/vcard", str.getBytes());
                    c0446a.m1802a(C0620c.nfc_type_contact);
                    c0446a.m1804a(getString(C0625h.contact) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_contentInsetLeft /*6*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_tel);
                    c0446a.m1804a(getString(C0625h.tel) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_contentInsetRight /*7*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_sms);
                    c0446a.m1804a(getString(C0625h.sms) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_popupTheme /*8*/:
                    String str4 = (String) hashMap.get("itemRecordExtra");
                    c0479k.m2004a(str4, str.getBytes());
                    c0446a.m1802a(C0620c.nfc_type_data);
                    c0446a.m1804a(getString(C0625h.data) + " " + str4 + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_titleTextAppearance /*9*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_geo);
                    c0446a.m1804a(getString(C0625h.location) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_address);
                    c0446a.m1804a(getString(C0625h.address) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_titleMargins /*11*/:
                    c0479k.m2006b(str);
                    c0446a.m1802a(C0620c.nfc_type_bluetooth);
                    c0446a.m1804a(getString(C0625h.bluetooth) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_titleMarginStart /*12*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_uri_custom);
                    c0446a.m1804a(getString(C0625h.url_uri) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_titleMarginEnd /*13*/:
                    try {
                        JSONArray jSONArray = new JSONArray(str);
                        c0479k.m2003a(jSONArray.getString(2), jSONArray.getString(3), new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1, (byte) -1}, C0468c.f1009b[Integer.valueOf(jSONArray.getString(0)).intValue()], C0468c.f1010c[Integer.valueOf(jSONArray.getString(1)).intValue()]);
                        c0446a.m1802a(C0620c.task_wifi_network);
                        c0446a.m1804a(getString(C0625h.record_wifi) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                        break;
                    } catch (Exception e) {
                        C0493f.m2081a(this, getString(C0625h.unknown_error));
                        return;
                    }
                case C0627j.Toolbar_titleMarginBottom /*15*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_facebook);
                    c0446a.m1804a(getString(C0625h.record_social_facebook) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_maxButtonHeight /*16*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_twitter);
                    c0446a.m1804a(getString(C0625h.record_social_twitter) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_theme /*17*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_googleplus);
                    c0446a.m1804a(getString(C0625h.record_social_googleplus) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_collapseIcon /*18*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_linkedin);
                    c0446a.m1804a(getString(C0625h.record_social_linkedin) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_collapseContentDescription /*19*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_pinterest);
                    c0446a.m1804a(getString(C0625h.record_social_pinterest) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_navigationIcon /*20*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_instagram);
                    c0446a.m1804a(getString(C0625h.record_social_instagram) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Toolbar_navigationContentDescription /*21*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_tumblr);
                    c0446a.m1804a(getString(C0625h.record_social_tumblr) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionMenuTextColor /*22*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_github);
                    c0446a.m1804a(getString(C0625h.record_social_github) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeStyle /*23*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_skype);
                    c0446a.m1804a(getString(C0625h.record_social_skype) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeCloseButtonStyle /*24*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.nfc_type_file);
                    c0446a.m1804a(getString(C0625h.record_file) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeSplitBackground /*26*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_youtube);
                    c0446a.m1804a(getString(C0625h.record_video_youtube) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeCloseDrawable /*27*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_vimeo);
                    c0446a.m1804a(getString(C0625h.record_video_vimeo) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeCutDrawable /*28*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_dailymotion);
                    c0446a.m1804a(getString(C0625h.record_video_dailymotion) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeCopyDrawable /*29*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_bitcoin);
                    c0446a.m1804a(getString(C0625h.record_bitcoin) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModePasteDrawable /*30*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_dribbble);
                    c0446a.m1804a(getString(C0625h.record_social_dribbble) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeSelectAllDrawable /*31*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_flickr);
                    c0446a.m1804a(getString(C0625h.record_social_flickr) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeShareDrawable /*32*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_reddit);
                    c0446a.m1804a(getString(C0625h.record_social_reddit) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeFindDrawable /*33*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_slack);
                    c0446a.m1804a(getString(C0625h.record_social_slack) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModeWebSearchDrawable /*34*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_snapchat);
                    c0446a.m1804a(getString(C0625h.record_social_snapchat) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionModePopupWindowStyle /*35*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_soundcloud);
                    c0446a.m1804a(getString(C0625h.record_social_soundcloud) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_textAppearanceLargePopupMenu /*36*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_steam);
                    c0446a.m1804a(getString(C0625h.record_social_steam) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_textAppearanceSmallPopupMenu /*37*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_social_twitch);
                    c0446a.m1804a(getString(C0625h.record_social_twitch) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_actionDropDownStyle /*38*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_search);
                    c0446a.m1804a(getString(C0625h.record_search) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_dropdownListPreferredItemHeight /*39*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_destination);
                    c0446a.m1804a(getString(C0625h.record_destination) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_spinnerStyle /*40*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_poi);
                    c0446a.m1804a(getString(C0625h.record_proximity_search) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                case C0627j.Theme_spinnerDropDownItemStyle /*41*/:
                    c0479k.m2007c(str);
                    c0446a.m1802a(C0620c.record_streetview);
                    c0446a.m1804a(getString(C0625h.record_streetview) + " : " + c0479k.m2013f() + " " + getString(C0625h.bytes));
                    break;
                default:
                    C0493f.m2081a(this, getString(C0625h.unknown_error));
                    return;
            }
            c0446a.m1808b(str2);
            if (!parseBoolean || str3 == null) {
                this.f2384o.m2191b(c0446a, c0479k);
            } else {
                this.f2384o.m2183a(str3, c0446a, c0479k);
            }
            this.f2386r.m3052a(this.f2384o.m2215o());
            m2978m();
        }
    }

    private boolean m2956a(String str, boolean z) {
        if (!z) {
            this.f2386r = (C0637q) this.f2376C.m3063e(1);
            if (this.f2386r == null) {
                return false;
            }
            this.f2384o.m2224x();
            this.f2386r.m3052a(this.f2384o.m2215o());
            m2978m();
        }
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("tag.profile.data");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("tag.profile.fields");
                jSONObject = (JSONObject) jSONObject.get("tag.profile.config");
                HashMap a = C0494g.m2083a(jSONObject2);
                HashMap a2 = C0494g.m2083a(jSONObject);
                a2.put("itemUpdate", String.valueOf(false));
                a2.put("itemHash", C0488c.m2054a());
                m2955a(Integer.valueOf((String) a2.get("requestType")).intValue(), a2, a);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void m2958b(int i, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) {
        this.f2387s = (C0634p) this.f2376C.m3063e(3);
        if (C0481m.m2029b(i) && this.f2387s != null) {
            C0479k c0479k = new C0479k();
            String str = (String) hashMap.get("itemTask");
            String str2 = (String) hashMap.get("itemDescription");
            String str3 = (String) hashMap.get("itemHash");
            boolean parseBoolean = Boolean.parseBoolean((String) hashMap.get("itemUpdate"));
            C0446a c0446a = new C0446a();
            c0446a.m1811c(i);
            c0446a.m1814d(i);
            c0446a.m1805a((HashMap) hashMap2);
            c0446a.m1809b((HashMap) hashMap);
            c0446a.m1807b(C0620c.action_menu_vertical_black);
            C0481m a = C0481m.m2026a(i);
            if (a != null) {
                if (a == C0481m.TASK_WIFI_NETWORK) {
                    int intValue;
                    try {
                        intValue = Integer.valueOf((String) hashMap.get("itemTaskExtra")).intValue();
                    } catch (Exception e) {
                        intValue = -1;
                    }
                    if (intValue >= 0) {
                        String str4;
                        String str5 = "";
                        String str6;
                        switch (intValue) {
                            case C0627j.View_android_focusable /*0*/:
                                str6 = C0481m.TASK_WIFI_NETWORK_OPEN.cN;
                                str4 = "OPEN : " + str2;
                                str2 = str6;
                                break;
                            case C0627j.View_paddingStart /*1*/:
                                str6 = C0481m.TASK_WIFI_NETWORK_WEP.cN;
                                str4 = "WEP : " + str2;
                                str2 = str6;
                                break;
                            case C0627j.View_paddingEnd /*2*/:
                                str6 = C0481m.TASK_WIFI_NETWORK_WPA.cN;
                                str4 = "WPA : " + str2;
                                str2 = str6;
                                break;
                            default:
                                C0493f.m2081a(this, getString(C0625h.unknown_error));
                                return;
                        }
                        c0479k.m2004a(str2, str.getBytes());
                        c0446a.m1802a(C0486a.m2046b(a));
                        c0446a.m1804a(C0486a.m2044a(a));
                        c0446a.m1808b(str4);
                    } else {
                        C0493f.m2081a(this, getString(C0625h.unknown_error));
                        return;
                    }
                }
                c0479k.m2004a(a.cN, str.getBytes());
                c0446a.m1802a(C0486a.m2046b(a));
                c0446a.m1804a(C0486a.m2044a(a));
                c0446a.m1808b(str2);
                if (!parseBoolean || str3 == null) {
                    this.f2384o.m2199c(c0446a, c0479k);
                } else {
                    this.f2384o.m2192b(str3, c0446a, c0479k);
                }
                this.f2387s.m3041a(this.f2384o.m2222v());
                m2977l();
                return;
            }
            C0493f.m2081a(this, getString(C0625h.unknown_error));
        }
    }

    private boolean m2959b(String str) {
        this.f2387s = (C0634p) this.f2376C.m3063e(3);
        if (this.f2387s == null) {
            return false;
        }
        this.f2384o.m2225y();
        this.f2387s.m3041a(this.f2384o.m2222v());
        m2977l();
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("tasks.profile.data");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONObject jSONObject2 = (JSONObject) jSONObject.get("tasks.profile.fields");
                jSONObject = (JSONObject) jSONObject.get("tasks.profile.config");
                HashMap a = C0494g.m2083a(jSONObject2);
                HashMap a2 = C0494g.m2083a(jSONObject);
                int intValue = Integer.valueOf((String) a2.get("requestType")).intValue();
                a2.put("itemUpdate", String.valueOf(false));
                a2.put("itemHash", C0488c.m2054a());
                m2958b(intValue, a2, a);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void m2961d(C0478j c0478j) {
        if (this.f2385q != null) {
            ArrayList g = c0478j.m1977g();
            C0446a c0446a = new C0446a();
            c0446a.m1802a(C0620c.nfc_size);
            c0446a.m1804a(getString(C0625h.tag_size));
            c0446a.m1808b(c0478j.m1950G() + " / " + c0478j.m1982l() + " " + getString(C0625h.bytes));
            this.f2384o.m2181a(c0446a);
            c0446a = new C0446a();
            c0446a.m1802a(C0620c.nfc_writable);
            c0446a.m1804a(getString(C0625h.tag_writable));
            if (c0478j.m1979i()) {
                c0446a.m1808b(getString(C0625h.yes));
            } else {
                c0446a.m1808b(getString(C0625h.no));
            }
            this.f2384o.m2181a(c0446a);
            c0446a = new C0446a();
            c0446a.m1802a(C0620c.nfc_canmakereadonly);
            c0446a.m1804a(getString(C0625h.tag_canmakereadonly));
            if (c0478j.m1981k()) {
                c0446a.m1808b(getString(C0625h.yes));
            } else {
                c0446a.m1808b(getString(C0625h.no));
            }
            this.f2384o.m2181a(c0446a);
            Iterator it = g.iterator();
            int i = 0;
            while (it.hasNext()) {
                String str;
                C0479k c0479k = (C0479k) it.next();
                C0446a c0446a2 = new C0446a();
                String str2 = "";
                str2 = "";
                String str3 = C0488c.m2054a() + i;
                String e = c0479k.m2011e();
                String k = c0479k.m2018k();
                int a = m2951a(c0479k);
                int j = c0479k.m2017j();
                if ("text/plain".equals(e)) {
                    try {
                        str2 = c0479k.m2016i();
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                }
                if (str2.isEmpty()) {
                    str2 = c0479k.toString();
                }
                if (e == null || j == 25) {
                    str = str2;
                } else {
                    String str4 = k + " : " + c0479k.m2011e();
                    k = c0479k.m2011e().equals("application/vnd.bluetooth.ep.oob") ? c0479k.m2020m() : str2;
                    if (c0479k.m2011e().equals("application/vnd.wfa.wsc")) {
                        HashMap n = c0479k.m2021n();
                        str2 = (String) n.get("SSID");
                        str = (String) n.get("NETWORKPWD");
                        if (!(str2 == null || str2.isEmpty())) {
                            if (str == null || str.isEmpty()) {
                                str = str2;
                                k = str4;
                            } else {
                                str = str2 + " / " + str;
                                k = str4;
                            }
                        }
                    }
                    str = k;
                    k = str4;
                }
                if (e != null && j == 25) {
                    try {
                        C0481m a2 = C0481m.m2027a(e);
                        JSONObject a3 = C0505n.m2138a(c0479k);
                        if (!(a2 == null || a3 == null)) {
                            a3 = (JSONObject) a3.get("tasks.profile.config");
                            if (a3 != null) {
                                str2 = "[ " + C0486a.m2044a(a2) + " ]\n" + ((String) C0494g.m2083a(a3).get("itemDescription"));
                                str = str2;
                            }
                        }
                        str2 = str;
                        str = str2;
                    } catch (Exception e3) {
                    }
                }
                str2 = !k.isEmpty() ? " - " + k : k;
                c0446a2.m1811c(110);
                c0446a2.m1814d(j);
                c0446a2.m1815d(str3);
                c0446a2.m1802a(a);
                c0446a2.m1804a(getString(C0625h.tag_record) + " " + i + str2);
                c0446a2.m1808b(str);
                if (j == 4 || j == 3 || j == 16 || j == 17 || j == 18 || j == 19 || j == 20 || j == 21 || j == 22 || j == 23 || j == 30 || j == 31 || j == 32 || j == 33 || j == 34 || j == 35 || j == 36 || j == 37 || j == 24 || j == 26 || j == 27 || j == 28 || j == 8 || j == 5 || j == 13 || j == 12 || j == 7 || j == 2 || j == 10 || j == 29 || j == 39 || j == 40 || j == 38 || j == 25) {
                    c0446a2.m1807b(C0620c.action_menu_vertical_black);
                }
                this.f2384o.m2182a(c0446a2, c0479k);
                i++;
            }
            if (i == 0) {
                c0446a = new C0446a();
                c0446a.m1802a(C0620c.nfc_record);
                c0446a.m1804a(getString(C0625h.tag_empty));
                this.f2384o.m2181a(c0446a);
            }
            this.f2385q.m3034a(this.f2384o.m2217q());
        }
    }

    private void m2964e(C0478j c0478j) {
        this.f2385q = (C0630o) this.f2376C.m3063e(0);
        if (this.f2385q == null) {
            m2973i(c0478j);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.tag_detected));
        this.f2384o.m2218r();
        this.f2383n.f1017a = false;
        this.f2383n.m1894b(c0478j);
    }

    private void m2965f(C0478j c0478j) {
        if (this.f2384o.m2204e()) {
            this.f2383n.f1017a = true;
            C0478j c0478j2 = new C0478j(c0478j.m1969b(), true);
            Iterator it = this.f2384o.m2215o().iterator();
            while (it.hasNext()) {
                c0478j2.addRecord(this.f2384o.m2195c(((C0446a) it.next()).m1820i()));
            }
            this.f2383n.m1901e(c0478j2);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.write_button_before));
    }

    private void m2968g(C0478j c0478j) {
        if (this.f2384o.m2204e()) {
            this.f2383n.f1017a = true;
            C0478j c0478j2 = new C0478j(c0478j.m1969b(), true);
            Iterator it = this.f2384o.m2222v().iterator();
            while (it.hasNext()) {
                c0478j2.addRecord(this.f2384o.m2195c(((C0446a) it.next()).m1820i()));
            }
            c0478j2.m1966a("com.wakdev.nfctasks");
            this.f2383n.m1901e(c0478j2);
            return;
        }
        C0493f.m2081a(this, getString(C0625h.write_button_before));
    }

    private void m2970h(C0478j c0478j) {
        byte[] bArr = null;
        int i = -1;
        switch (this.f2384o.m2205f()) {
            case C0627j.Toolbar_subtitle /*3*/:
                this.f2383n.f1017a = true;
                C0478j c0478j2 = new C0478j(c0478j.m1969b(), true);
                c0478j2.eraseTag();
                this.f2383n.m1901e(c0478j2);
            case C0627j.Toolbar_contentInsetStart /*4*/:
                this.f2383n.f1017a = false;
                this.f2383n.m1891a(c0478j);
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                this.f2383n.f1017a = false;
                this.f2383n.m1891a(c0478j);
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                this.f2383n.f1017a = true;
                this.f2383n.m1892a(this.f2392x, true);
            case C0627j.Toolbar_contentInsetRight /*7*/:
                this.f2383n.f1017a = false;
                this.f2383n.m1891a(c0478j);
            case C0627j.Toolbar_popupTheme /*8*/:
                this.f2383n.f1017a = true;
                this.f2383n.m1892a(this.f2392x, true);
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                this.f2383n.f1017a = false;
                this.f2383n.m1896c(c0478j);
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                this.f2383n.f1017a = true;
                this.f2383n.m1899d(c0478j);
            case C0627j.Toolbar_titleMargins /*11*/:
                this.f2383n.f1017a = false;
                if (!(this.f2378E == null || this.f2378E.isEmpty())) {
                    bArr = C0489d.m2064a(C0488c.m2055a(this.f2378E), 0, 4);
                }
                int b = (c0478j.m1969b() == null || bArr == null || bArr.length != 4) ? -1 : c0478j.m1968b(bArr);
                if (this.f2393y != null) {
                    this.f2393y.dismiss();
                }
                m2979n();
                switch (b) {
                    case -6:
                        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.error, getString(C0625h.write_error), getString(C0625h.write_error), getString(C0625h.valid_button)));
                    case C0627j.View_paddingStart /*1*/:
                        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.success, getString(C0625h.set_password), getString(C0625h.set_password_complete), getString(C0625h.valid_button)));
                    default:
                        C0493f.m2081a(this, getString(C0625h.set_password_error));
                }
            case C0627j.Toolbar_titleMarginStart /*12*/:
                this.f2383n.f1017a = false;
                if (!(this.f2378E == null || this.f2378E.isEmpty())) {
                    bArr = C0489d.m2064a(C0488c.m2055a(this.f2378E), 0, 4);
                }
                if (!(c0478j.m1969b() == null || bArr == null || bArr.length != 4)) {
                    i = c0478j.m1959a(bArr);
                }
                if (this.f2393y != null) {
                    this.f2393y.dismiss();
                }
                m2979n();
                switch (i) {
                    case -13:
                        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.error, getString(C0625h.write_error), getString(C0625h.unset_password_pwd_error), getString(C0625h.valid_button)));
                    case -6:
                        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.error, getString(C0625h.write_error), getString(C0625h.write_error), getString(C0625h.valid_button)));
                    case C0627j.View_paddingStart /*1*/:
                        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.success, getString(C0625h.unset_password), getString(C0625h.unset_password_complete), getString(C0625h.valid_button)));
                    default:
                        C0493f.m2081a(this, getString(C0625h.unset_password_error));
                }
            default:
        }
    }

    private void m2972i() {
        this.f2385q = (C0630o) this.f2376C.m3063e(0);
        if (this.f2385q != null && !this.f2384o.m2220t() && !this.f2385q.m3031I()) {
            runOnUiThread(new C06112(this));
        }
    }

    private void m2973i(C0478j c0478j) {
        this.f2379F = new Handler();
        this.f2380G = new C06145(this, c0478j);
        this.f2379F.postAtTime(this.f2380G, System.currentTimeMillis() + 100);
        this.f2379F.postDelayed(this.f2380G, 100);
    }

    private void m2975j() {
        this.f2386r = (C0637q) this.f2376C.m3063e(1);
        if (this.f2386r != null && !this.f2384o.m2219s() && !this.f2386r.m3050I()) {
            runOnUiThread(new C06123(this));
        }
    }

    private void m2976k() {
        this.f2387s = (C0634p) this.f2376C.m3063e(3);
        if (this.f2387s != null && !this.f2384o.m2223w() && !this.f2387s.m3039I()) {
            runOnUiThread(new C06134(this));
        }
    }

    private void m2977l() {
        this.f2389u = (Button) findViewById(C0621d.write_tasks_button);
        this.f2391w = findViewById(C0621d.mylistview_section3_separator);
        if (this.f2384o.m2223w()) {
            this.f2389u.setVisibility(4);
            this.f2391w.setVisibility(4);
            return;
        }
        this.f2389u.setText(getString(C0625h.write_button) + " / " + this.f2384o.m2221u() + " " + getString(C0625h.bytes));
        this.f2389u.setVisibility(0);
        this.f2391w.setVisibility(0);
    }

    private void m2978m() {
        this.f2388t = (Button) findViewById(C0621d.write_button);
        this.f2390v = findViewById(C0621d.mylistview_section1_separator);
        if (this.f2384o.m2219s()) {
            this.f2388t.setVisibility(4);
            this.f2390v.setVisibility(4);
            return;
        }
        this.f2388t.setText(getString(C0625h.write_button) + " / " + this.f2384o.m2210i() + " " + getString(C0625h.bytes));
        this.f2388t.setVisibility(0);
        this.f2390v.setVisibility(0);
    }

    private void m2979n() {
        this.f2384o.m2196c();
        this.f2384o.m2180a(0);
        this.f2378E = null;
    }

    private void m2980o() {
        startActivityForResult(new Intent(this, HelpFirstUseActivity.class), 1);
    }

    private void m2981p() {
        this.f2386r = (C0637q) this.f2376C.m3063e(1);
        if (this.f2386r == null) {
            C0493f.m2081a(this, getString(C0625h.error));
            return;
        }
        this.f2384o.m2224x();
        this.f2386r.m3052a(this.f2384o.m2215o());
        m2978m();
    }

    private void m2982q() {
        this.f2387s = (C0634p) this.f2376C.m3063e(3);
        if (this.f2387s == null) {
            C0493f.m2081a(this, getString(C0625h.error));
            return;
        }
        this.f2384o.m2225y();
        this.f2387s.m3041a(this.f2384o.m2222v());
        m2977l();
    }

    public void m2983a() {
        String string = getString(C0625h.write_complete);
        switch (this.f2384o.m2205f()) {
            case C0627j.Toolbar_subtitle /*3*/:
                string = getString(C0625h.erase_complete);
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                string = getString(C0625h.lock_complete);
                break;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                string = getString(C0625h.copy_complete);
                break;
            case C0627j.Toolbar_popupTheme /*8*/:
                string = getString(C0625h.copy_complete);
                break;
        }
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        if (this.f2384o.m2205f() == 8) {
            this.f2384o.m2212k();
            m2986a(C0622e.dialog_info, C0600e.m2934a(C0620c.dialog_copy_dest, getString(C0625h.infinite_copy_tag) + " : " + this.f2384o.m2213l(), getString(C0625h.approach_nfc_tag_dest)));
            return;
        }
        m2979n();
        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.success, string, string, getString(C0625h.valid_button)));
    }

    public void m2984a(int i) {
        this.f2384o.m2189b(i);
        this.f2377D.setCurrentItem(this.f2384o.m2208h());
        switch (this.f2384o.m2208h()) {
            case C0627j.View_android_focusable /*0*/:
                m2972i();
            case C0627j.View_paddingStart /*1*/:
                m2975j();
            case C0627j.Toolbar_subtitle /*3*/:
                m2976k();
            default:
        }
    }

    public void m2985a(int i, float f, int i2) {
    }

    public void m2986a(int i, HashMap<String, String> hashMap) {
        HashMap hashMap2;
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("tagDialog");
        if (findFragmentByTag != null) {
            beginTransaction.remove(findFragmentByTag);
        }
        HashMap hashMap3 = new HashMap();
        if (hashMap == null) {
            hashMap2 = hashMap3;
        }
        if (i == 0) {
            i = C0622e.dialog_info;
        }
        if (hashMap2.get("dialog_title") == null) {
            hashMap2.put("dialog_title", getString(C0625h.write_dialog_title));
        }
        this.f2393y = null;
        this.f2393y = C0600e.m2933a(i, hashMap2);
        this.f2393y.m2937a((C0599a) this);
        this.f2393y.show(beginTransaction, "tagDialog");
    }

    public void m2987a(C0446a c0446a) {
        this.f2384o.m2190b(c0446a);
        this.f2386r.m3052a(this.f2384o.m2215o());
        m2978m();
    }

    public void m2988a(C0472d c0472d) {
        if (!(c0472d == null || c0472d.f1031a == null || c0472d.f1032b <= 0)) {
            Intent intent = new Intent(this, DisplayTagMemoryActivity.class);
            intent.putExtra("memory_bytes", c0472d.f1031a);
            intent.putExtra("sector_size", c0472d.f1032b);
            intent.putExtra("tag_tech", c0472d.f1033c);
            startActivityForResult(intent, 1);
        }
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        m2979n();
    }

    public void m2989a(C0478j c0478j) {
        if (c0478j != null) {
            switch (this.f2384o.m2208h()) {
                case C0627j.View_android_focusable /*0*/:
                    m2964e(c0478j);
                case C0627j.View_paddingStart /*1*/:
                    m2965f(c0478j);
                case C0627j.View_paddingEnd /*2*/:
                    m2970h(c0478j);
                case C0627j.Toolbar_subtitle /*3*/:
                    m2968g(c0478j);
                default:
            }
        }
    }

    public void m2990a(HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("dialog_action");
        try {
            int parseInt = Integer.parseInt((String) hashMap.get("dialog_id_action"));
            if (str != null && parseInt != 0) {
                this.f2394z.dismiss();
                switch (parseInt) {
                    case C0627j.Toolbar_subtitle /*3*/:
                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                    case C0627j.Toolbar_contentInsetRight /*7*/:
                    case C0627j.Toolbar_popupTheme /*8*/:
                    case C0627j.Toolbar_titleMarginStart /*12*/:
                    case C0627j.Toolbar_titleMarginEnd /*13*/:
                    case C0627j.Toolbar_maxButtonHeight /*16*/:
                    case C0627j.Toolbar_theme /*17*/:
                    case C0627j.Toolbar_collapseIcon /*18*/:
                    case C0627j.Toolbar_collapseContentDescription /*19*/:
                    case C0627j.Toolbar_navigationIcon /*20*/:
                    case C0627j.Toolbar_navigationContentDescription /*21*/:
                    case C0627j.Theme_actionMenuTextColor /*22*/:
                    case C0627j.Theme_actionModeStyle /*23*/:
                    case C0627j.Theme_actionModeCloseButtonStyle /*24*/:
                    case C0627j.Theme_actionModeCopyDrawable /*29*/:
                    case C0627j.Theme_actionModePasteDrawable /*30*/:
                    case C0627j.Theme_actionModeSelectAllDrawable /*31*/:
                    case C0627j.Theme_actionModeShareDrawable /*32*/:
                    case C0627j.Theme_actionModeFindDrawable /*33*/:
                    case C0627j.Theme_actionModeWebSearchDrawable /*34*/:
                    case C0627j.Theme_actionModePopupWindowStyle /*35*/:
                    case C0627j.Theme_textAppearanceLargePopupMenu /*36*/:
                    case C0627j.Theme_textAppearanceSmallPopupMenu /*37*/:
                    case C0627j.Theme_actionDropDownStyle /*38*/:
                    case C0627j.Theme_dropdownListPreferredItemHeight /*39*/:
                    case C0627j.Theme_spinnerStyle /*40*/:
                        C0495h.m2084a(str);
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        C0495h.m2088b(str);
                    case C0627j.Theme_actionModeBackground /*25*/:
                        if (C0499j.m2096a("com.wakdev.nfctasks")) {
                            Serializable arrayList = new ArrayList();
                            HashMap hashMap2 = new HashMap();
                            try {
                                HashMap a = C0494g.m2083a((JSONObject) new JSONObject(str).get("tasks.profile.config"));
                                hashMap2.put("requestType", a.get("requestType"));
                                hashMap2.put("itemTask", a.get("itemTask"));
                                hashMap2.put("itemTaskExtra", a.get("itemTaskExtra"));
                                arrayList.add(hashMap2);
                                Intent intent = new Intent();
                                intent.putExtra("TasksProfile", arrayList);
                                intent.setAction("com.wakdev.nfctasks.LAUNCH_PROFILE");
                                startActivity(intent);
                                return;
                            } catch (Exception e) {
                                C0493f.m2081a(this, getString(C0625h.load_error));
                                return;
                            }
                        }
                        C0493f.m2081a(this, getString(C0625h.need_nfctasks));
                    default:
                }
            }
        } catch (Exception e2) {
        }
    }

    public void addRecord(View view) {
        startActivityForResult(new Intent(this, ChooseRecordActivity.class), 1);
    }

    public void addTask(View view) {
        startActivityForResult(new Intent(this, ChooseTaskActivity.class), 1);
    }

    public void m2991b() {
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        m2979n();
        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.success, getString(C0625h.format_memory), getString(C0625h.format_complete), getString(C0625h.valid_button)));
    }

    public void m2992b(int i) {
    }

    public void m2993b(int i, HashMap<String, String> hashMap) {
        if (this.f2394z != null) {
            this.f2394z.dismiss();
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
        this.f2394z = null;
        this.f2394z = C0570b.m2741a(i, hashMap);
        this.f2394z.m2743a((C0525a) this);
        this.f2394z.show(beginTransaction, "actionDialog");
    }

    public void m2994b(C0446a c0446a) {
        try {
            HashMap hashMap = (HashMap) c0446a.m1824m().clone();
            HashMap hashMap2 = (HashMap) c0446a.m1825n().clone();
            hashMap2.put("itemHash", null);
            hashMap2.put("itemUpdate", String.valueOf(false));
            m2955a(c0446a.m1819h(), hashMap2, hashMap);
        } catch (Exception e) {
            C0493f.m2081a(this, getString(C0625h.error));
        }
    }

    public void m2995b(C0478j c0478j) {
        switch (this.f2384o.m2205f()) {
            case C0627j.View_android_focusable /*0*/:
                m2961d(c0478j);
            case C0627j.Toolbar_contentInsetStart /*4*/:
                this.f2392x = c0478j;
                this.f2383n.f1017a = true;
                this.f2383n.f1018b = true;
                this.f2392x.lockTag();
                this.f2383n.m1901e(this.f2392x);
                Log.d("NFCTools", "lock");
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                this.f2392x = c0478j;
                this.f2384o.m2180a(6);
                m2986a(C0622e.dialog_info, C0600e.m2934a(C0620c.dialog_copy_dest, getString(C0625h.copy_tag) + " 2/2", getString(C0625h.approach_nfc_tag_dest)));
            case C0627j.Toolbar_contentInsetRight /*7*/:
                this.f2392x = c0478j;
                this.f2384o.m2211j();
                this.f2384o.m2180a(8);
                m2986a(C0622e.dialog_info, C0600e.m2934a(C0620c.dialog_copy_dest, getString(C0625h.infinite_copy_tag) + " : " + this.f2384o.m2213l(), getString(C0625h.approach_nfc_tag_dest)));
            default:
        }
    }

    public void m2996b(HashMap<String, String> hashMap) {
        String str = (String) hashMap.get("dialog_description");
        if (str != null) {
            this.f2394z.dismiss();
            C0503m.m2107a(str);
            C0493f.m2081a(this, getString(C0625h.copied_to_clipboard));
        }
    }

    public void m2997c() {
    }

    public void m2998c(int i) {
        String str = "";
        switch (i) {
            case -3:
                str = getString(C0625h.err_adapter_disable);
                break;
            default:
                str = getString(C0625h.err_adapter_unknow);
                break;
        }
        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.error, getString(C0625h.error), str, getString(C0625h.valid_button)));
    }

    public void m2999c(int i, HashMap<String, String> hashMap) {
        if (this.f2374A != null) {
            this.f2374A.dismiss();
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("recordDialog");
        if (findFragmentByTag != null) {
            beginTransaction.remove(findFragmentByTag);
        }
        if (i == 0) {
            i = C0622e.dialog_record;
        }
        this.f2374A = null;
        this.f2374A = C0597d.m2929a(i, hashMap);
        this.f2374A.m2931a(this.f2386r);
        this.f2374A.show(beginTransaction, "recordDialog");
    }

    public void m3000c(C0446a c0446a) {
        this.f2384o.m2198c(c0446a);
        this.f2386r.m3052a(this.f2384o.m2215o());
    }

    public void m3001c(C0478j c0478j) {
        int intValue;
        int intValue2;
        int intValue3;
        try {
            intValue = Integer.valueOf(c0478j.m1962a("kTechDataTagTypeID", String.valueOf(-1))).intValue();
        } catch (Exception e) {
            intValue = -1;
        }
        try {
            intValue2 = Integer.valueOf(c0478j.m1962a("kTechDataNTAGSignatureStatus", String.valueOf(-1))).intValue();
        } catch (Exception e2) {
            intValue2 = -1;
        }
        try {
            intValue3 = Integer.valueOf(c0478j.m1962a("kTechDataIsProtectedByPassword", String.valueOf(-1))).intValue();
        } catch (Exception e3) {
            intValue3 = -1;
        }
        String a = c0478j.m1962a("kTechDataIso", "");
        String a2 = c0478j.m1962a("kTechDataType", "");
        String a3 = c0478j.m1962a("kTechDataList", "");
        String a4 = c0478j.m1962a("kTechDataTagID", "");
        String a5 = c0478j.m1962a("kTechDataATQA", "");
        String a6 = c0478j.m1962a("kTechDataSAK", "");
        String a7 = c0478j.m1962a("kTechDataHeaderRom", "");
        String a8 = c0478j.m1962a("kTechDataATR", "");
        String a9 = c0478j.m1962a("kTechDataPMm", "");
        String a10 = c0478j.m1962a("kTechDataSystemCode", "");
        String a11 = c0478j.m1962a("kTechDataDSFID", "");
        String a12 = c0478j.m1962a("kTechDataNTAGSignature", "");
        String a13 = c0478j.m1962a("kTechDataNDEFFormat", "");
        if (this.f2394z != null) {
            this.f2394z.dismiss();
        }
        C0446a c0446a = new C0446a();
        c0446a.m1811c(101);
        c0446a.m1802a(C0620c.nfc_type);
        c0446a.m1804a(getString(C0625h.tag_type) + " : " + a);
        c0446a.m1807b(C0620c.action_menu_vertical_black);
        c0446a.m1808b(a2);
        this.f2384o.m2181a(c0446a);
        c0478j.m1951H();
        C0446a c0446a2 = new C0446a();
        c0446a2.m1811c(102);
        c0446a2.m1802a(C0620c.nfc_techlist);
        c0446a2.m1807b(C0620c.action_menu_vertical_black);
        c0446a2.m1804a(getString(C0625h.tag_techlist));
        if (a3.isEmpty()) {
            c0446a2.m1808b(getString(C0625h.unknow));
        } else {
            c0446a2.m1808b(a3);
        }
        this.f2384o.m2181a(c0446a2);
        c0446a2 = new C0446a();
        c0446a2.m1811c(103);
        c0446a2.m1802a(C0620c.nfc_serial);
        c0446a2.m1807b(C0620c.action_menu_vertical_black);
        c0446a2.m1804a(getString(C0625h.tag_serial_number));
        c0446a2.m1808b(a4);
        this.f2384o.m2181a(c0446a2);
        if (!a5.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(104);
            c0446a2.m1802a(C0620c.nfc_atqa);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.tag_atqa));
            c0446a2.m1808b(a5);
            this.f2384o.m2181a(c0446a2);
        }
        if (!a6.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(105);
            c0446a2.m1802a(C0620c.nfc_sak);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.tag_sak));
            c0446a2.m1808b(a6);
            this.f2384o.m2181a(c0446a2);
        }
        if (!a7.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(107);
            c0446a2.m1802a(C0620c.nfc_hr);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.tag_hr));
            c0446a2.m1808b(a7);
            this.f2384o.m2181a(c0446a2);
        }
        if (!a8.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(106);
            c0446a2.m1802a(C0620c.nfc_ats);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.tag_ats));
            c0446a2.m1808b(a8);
            this.f2384o.m2181a(c0446a2);
        }
        if (!a9.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(113);
            c0446a2.m1802a(C0620c.nfc_manufacturer);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.nfc_manufacture_parameter));
            c0446a2.m1808b(a9);
            this.f2384o.m2181a(c0446a2);
        }
        if (!a10.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(114);
            c0446a2.m1802a(C0620c.nfc_code);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.nfc_system_code));
            c0446a2.m1808b(a10);
            this.f2384o.m2181a(c0446a2);
        }
        if (!a11.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(116);
            c0446a2.m1802a(C0620c.nfc_dsfid);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.nfc_dsfid));
            c0446a2.m1808b(a11);
            this.f2384o.m2181a(c0446a2);
        }
        if (!a12.isEmpty()) {
            c0446a2 = new C0446a();
            c0446a2.m1811c(115);
            c0446a2.m1802a(C0620c.nfc_signature);
            c0446a2.m1807b(C0620c.action_menu_vertical_black);
            c0446a2.m1804a(getString(C0625h.nfc_signature));
            switch (intValue2) {
                case C0627j.View_android_focusable /*0*/:
                    c0446a2.m1808b(getString(C0625h.nfc_signature_not_valid));
                    break;
                case C0627j.View_paddingStart /*1*/:
                    c0446a2.m1808b(getString(C0625h.nfc_signature_valid));
                    break;
                default:
                    c0446a2.m1808b(getString(C0625h.nfc_signature_not_checked));
                    break;
            }
            c0446a2.m1812c(a12);
            this.f2384o.m2181a(c0446a2);
        }
        if (intValue3 == 2) {
            C0446a c0446a3 = new C0446a();
            c0446a3.m1811c(111);
            c0446a3.m1802a(C0620c.set_password);
            c0446a3.m1804a(getString(C0625h.protected_by_password));
            c0446a3.m1808b(getString(C0625h.yes));
            this.f2384o.m2181a(c0446a3);
        }
        if (intValue3 == 3) {
            C0446a c0446a4 = new C0446a();
            c0446a4.m1811c(111);
            c0446a4.m1802a(C0620c.set_password);
            c0446a4.m1804a(getString(C0625h.protected_by_password));
            c0446a4.m1808b(getString(C0625h.no));
            this.f2384o.m2181a(c0446a4);
        }
        String str = null;
        switch (intValue) {
            case C0627j.View_paddingStart /*1*/:
                str = getString(C0625h.tag_memory_information_NTAG203);
                break;
            case C0627j.View_paddingEnd /*2*/:
                str = getString(C0625h.tag_memory_information_NTAG210);
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                str = getString(C0625h.tag_memory_information_NTAG212);
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                str = getString(C0625h.tag_memory_information_NTAG213);
                break;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                str = getString(C0625h.tag_memory_information_NTAG215);
                break;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                str = getString(C0625h.tag_memory_information_NTAG216);
                break;
            case C0627j.Toolbar_contentInsetRight /*7*/:
                str = getString(C0625h.tag_memory_information_TOPAZ512);
                break;
            case C0627j.Toolbar_popupTheme /*8*/:
                str = getString(C0625h.tag_memory_information_ULTRALIGHT_C);
                break;
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                str = getString(C0625h.tag_memory_information_ULTRALIGHT);
                break;
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                str = getString(C0625h.tag_memory_information_MIFARE_CLASSIC_1K);
                break;
            case C0627j.Toolbar_titleMargins /*11*/:
                str = getString(C0625h.tag_memory_information_MIFARE_CLASSIC_4K);
                break;
            case C0627j.Toolbar_titleMarginEnd /*13*/:
                str = getString(C0625h.tag_memory_information_FELICA_RC_915);
                break;
            case C0627j.Toolbar_theme /*17*/:
                str = getString(C0625h.tag_memory_information_FELICA_RC_S960);
                break;
            case C0627j.Toolbar_collapseIcon /*18*/:
                str = getString(C0625h.tag_memory_information_FELICA_RC_S962);
                break;
            case C0627j.Toolbar_collapseContentDescription /*19*/:
                str = getString(C0625h.tag_memory_information_FELICA_RC_SA00);
                break;
            case C0627j.Toolbar_navigationIcon /*20*/:
                str = getString(C0625h.tag_memory_information_FELICA_RC_SA01);
                break;
            case C0627j.Toolbar_navigationContentDescription /*21*/:
                str = getString(C0625h.tag_memory_information_FELICA_LITE_RC_S965);
                break;
            case C0627j.Theme_actionMenuTextColor /*22*/:
                str = getString(C0625h.tag_memory_information_FELICA_LITE_S_RC_S966);
                break;
            case C0627j.Theme_actionModeCloseDrawable /*27*/:
            case C0627j.Theme_actionModeCopyDrawable /*29*/:
                str = getString(C0625h.tag_memory_information_MIFARE_PLUS_X_S_4K);
                break;
            case C0627j.Theme_actionModeCutDrawable /*28*/:
            case C0627j.Theme_actionModePasteDrawable /*30*/:
                str = getString(C0625h.tag_memory_information_MIFARE_PLUS_X_S_2K);
                break;
        }
        if (!(str == null || str.isEmpty())) {
            c0446a3 = new C0446a();
            c0446a3.m1811c(112);
            c0446a3.m1802a(C0620c.nfc_memory_information);
            c0446a3.m1804a(getString(C0625h.tag_memory_information));
            c0446a3.m1808b(str);
            this.f2384o.m2181a(c0446a3);
        }
        if (c0478j.m1971c() != null) {
            c0446a3 = new C0446a();
            c0446a3.m1811c(108);
            c0446a3.m1802a(C0620c.nfc_ndef);
            c0446a3.m1804a(getString(C0625h.tag_ndef));
            c0446a3.m1808b(a13.isEmpty() ? getString(C0625h.unknow) : a13);
            this.f2384o.m2181a(c0446a3);
            this.f2383n.m1891a(c0478j);
            return;
        }
        this.f2385q.m3034a(this.f2384o.m2217q());
    }

    public void copyTag(View view) {
        this.f2384o.m2200d();
        this.f2384o.m2180a(5);
        m2986a(C0622e.dialog_info, C0600e.m2934a(C0620c.dialog_copy_src, getString(C0625h.copy_tag) + " 1/2", getString(C0625h.approach_nfc_tag_src)));
    }

    public void m3002d() {
        this.f2394z.dismiss();
    }

    public void m3003d(int i) {
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        if (this.f2394z != null) {
            this.f2394z.dismiss();
        }
        m2979n();
        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.error, getString(C0625h.error), getString(C0625h.err_nfc_reading), getString(C0625h.valid_button)));
    }

    public void m3004d(int i, HashMap<String, String> hashMap) {
        if (this.f2375B != null) {
            this.f2375B.dismiss();
        }
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("taskDialog");
        if (findFragmentByTag != null) {
            beginTransaction.remove(findFragmentByTag);
        }
        if (i == 0) {
            i = C0622e.dialog_record;
        }
        this.f2375B = null;
        this.f2375B = C0597d.m2929a(i, hashMap);
        this.f2375B.m2931a(this.f2387s);
        this.f2375B.show(beginTransaction, "taskDialog");
    }

    public void m3005d(C0446a c0446a) {
        this.f2384o.m2202d(c0446a);
        this.f2386r.m3052a(this.f2384o.m2215o());
    }

    public void d_() {
        m2979n();
        this.f2393y.dismiss();
    }

    public void downloadNFCTasks(View view) {
        C0495h.m2085a("com.wakdev.nfctasks", 1);
    }

    public void m3006e() {
        m2979n();
    }

    public void m3007e(int i) {
        C0493f.m2081a(this, getString(C0625h.read_memory_error));
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        if (this.f2394z != null) {
            this.f2394z.dismiss();
        }
        m2979n();
    }

    public void m3008e(C0446a c0446a) {
        int h = c0446a.m1819h();
        Intent intent = null;
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        switch (h) {
            case C0627j.View_paddingStart /*1*/:
                intent = new Intent(applicationContext, RecordTextActivity.class);
                break;
            case C0627j.View_paddingEnd /*2*/:
                intent = new Intent(applicationContext, RecordURIActivity.class);
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                intent = new Intent(applicationContext, RecordApplicationActivity.class);
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                intent = new Intent(applicationContext, RecordMailActivity.class);
                break;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                intent = new Intent(applicationContext, RecordContactActivity.class);
                break;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                intent = new Intent(applicationContext, RecordTelActivity.class);
                break;
            case C0627j.Toolbar_contentInsetRight /*7*/:
                intent = new Intent(applicationContext, RecordSMSActivity.class);
                break;
            case C0627j.Toolbar_popupTheme /*8*/:
                intent = new Intent(applicationContext, RecordCustomActivity.class);
                break;
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                intent = new Intent(applicationContext, RecordGeocodeActivity.class);
                break;
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                intent = new Intent(applicationContext, RecordAddressActivity.class);
                break;
            case C0627j.Toolbar_titleMargins /*11*/:
                intent = new Intent(applicationContext, RecordBluetoothActivity.class);
                break;
            case C0627j.Toolbar_titleMarginStart /*12*/:
                intent = new Intent(applicationContext, RecordCustomURIActivity.class);
                break;
            case C0627j.Toolbar_titleMarginEnd /*13*/:
                intent = new Intent(applicationContext, RecordWifiActivity.class);
                break;
            case C0627j.Toolbar_titleMarginBottom /*15*/:
            case C0627j.Toolbar_maxButtonHeight /*16*/:
            case C0627j.Toolbar_theme /*17*/:
            case C0627j.Toolbar_collapseIcon /*18*/:
            case C0627j.Toolbar_collapseContentDescription /*19*/:
            case C0627j.Toolbar_navigationIcon /*20*/:
            case C0627j.Toolbar_navigationContentDescription /*21*/:
            case C0627j.Theme_actionMenuTextColor /*22*/:
            case C0627j.Theme_actionModeStyle /*23*/:
            case C0627j.Theme_actionModePasteDrawable /*30*/:
            case C0627j.Theme_actionModeSelectAllDrawable /*31*/:
            case C0627j.Theme_actionModeShareDrawable /*32*/:
            case C0627j.Theme_actionModeFindDrawable /*33*/:
            case C0627j.Theme_actionModeWebSearchDrawable /*34*/:
            case C0627j.Theme_actionModePopupWindowStyle /*35*/:
            case C0627j.Theme_textAppearanceLargePopupMenu /*36*/:
            case C0627j.Theme_textAppearanceSmallPopupMenu /*37*/:
                intent = new Intent(applicationContext, RecordSocialActivity.class);
                intent.putExtra("SOCIAL_ID", h);
                break;
            case C0627j.Theme_actionModeCloseButtonStyle /*24*/:
                intent = new Intent(applicationContext, RecordFileActivity.class);
                break;
            case C0627j.Theme_actionModeSplitBackground /*26*/:
            case C0627j.Theme_actionModeCloseDrawable /*27*/:
            case C0627j.Theme_actionModeCutDrawable /*28*/:
                intent = new Intent(applicationContext, RecordVideoActivity.class);
                intent.putExtra("VIDEO_ID", h);
                break;
            case C0627j.Theme_actionModeCopyDrawable /*29*/:
                intent = new Intent(applicationContext, RecordBitcoinActivity.class);
                break;
            case C0627j.Theme_actionDropDownStyle /*38*/:
                intent = new Intent(applicationContext, RecordSearchActivity.class);
                break;
            case C0627j.Theme_dropdownListPreferredItemHeight /*39*/:
                intent = new Intent(applicationContext, RecordDestinationActivity.class);
                break;
            case C0627j.Theme_spinnerStyle /*40*/:
                intent = new Intent(applicationContext, RecordProximitySearchActivity.class);
                break;
            case C0627j.Theme_spinnerDropDownItemStyle /*41*/:
                intent = new Intent(applicationContext, RecordStreetViewActivity.class);
                break;
        }
        if (intent != null) {
            intent.putExtra("itemHash", c0446a.m1820i());
            intent.putExtra("itemFields", c0446a.m1824m());
            intent.putExtra("itemUpdate", true);
            startActivityForResult(intent, 1);
        }
    }

    public void eraseTag(View view) {
        this.f2384o.m2200d();
        this.f2384o.m2180a(3);
        m2986a(C0622e.dialog_info, C0600e.m2936a(C0620c.anim_approach, getString(C0625h.erase_tag), getString(C0625h.approach_nfc_tag), true));
    }

    public void m3009f(int i) {
        m3003d(i);
    }

    public void m3010f(C0446a c0446a) {
        Intent intent = new Intent(this, C0486a.m2048d(C0481m.m2026a(c0446a.m1819h())));
        if (intent != null) {
            intent.putExtra("itemHash", c0446a.m1820i());
            intent.putExtra("itemFields", c0446a.m1824m());
            intent.putExtra("itemUpdate", true);
            startActivityForResult(intent, 1);
        }
    }

    public void formatTagMemory(View view) {
        this.f2384o.m2200d();
        this.f2384o.m2180a(10);
        m2986a(C0622e.dialog_info, C0600e.m2936a(C0620c.anim_approach, getString(C0625h.format_memory), getString(C0625h.approach_nfc_tag), true));
    }

    public void m3011g() {
        this.f2374A.dismiss();
    }

    public void m3012g(int i) {
        String string;
        switch (i) {
            case -14:
                string = getString(C0625h.write_error_ndef_need_to_be_fixed);
                break;
            case -10:
                string = getString(C0625h.write_error_format);
                break;
            case -9:
                string = getString(C0625h.write_error_size);
                break;
            default:
                string = getString(C0625h.write_error);
                break;
        }
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        m2979n();
        m2986a(C0622e.dialog_info, C0600e.m2935a(C0620c.error, getString(C0625h.write_error), string, getString(C0625h.valid_button)));
    }

    public void m3013g(C0446a c0446a) {
        this.f2384o.m2207g(c0446a);
        this.f2387s.m3041a(this.f2384o.m2222v());
    }

    public void m3014h() {
        this.f2375B.dismiss();
    }

    public void m3015h(int i) {
        if (this.f2393y != null) {
            this.f2393y.dismiss();
        }
        m2979n();
        if (i == -6) {
            m3012g(i);
        } else {
            C0493f.m2081a(this, getString(C0625h.format_memory_error));
        }
    }

    public void m3016h(C0446a c0446a) {
        try {
            HashMap hashMap = (HashMap) c0446a.m1824m().clone();
            HashMap hashMap2 = (HashMap) c0446a.m1825n().clone();
            hashMap2.put("itemHash", null);
            hashMap2.put("itemUpdate", String.valueOf(false));
            m2958b(c0446a.m1819h(), hashMap2, hashMap);
        } catch (Exception e) {
            C0493f.m2081a(this, getString(C0625h.error));
        }
    }

    public void m3017i(C0446a c0446a) {
        this.f2384o.m2209h(c0446a);
        this.f2387s.m3041a(this.f2384o.m2222v());
    }

    public void infiniteCopyTag(View view) {
        this.f2384o.m2200d();
        this.f2384o.m2180a(7);
        m2986a(C0622e.dialog_info, C0600e.m2934a(C0620c.dialog_copy_src, getString(C0625h.infinite_copy_tag), getString(C0625h.approach_nfc_tag_src)));
    }

    public void m3018j(C0446a c0446a) {
        this.f2384o.m2206f(c0446a);
        this.f2387s.m3041a(this.f2384o.m2222v());
        m2977l();
    }

    public void lockTag(View view) {
        OnClickListener c06156 = new C06156(this);
        new Builder(this).setMessage(getString(C0625h.lock_warning_sure)).setPositiveButton(getString(C0625h.yes), c06156).setNegativeButton(getString(C0625h.no), c06156).setIcon(C0620c.lock_warning_confirm).setTitle(getString(C0625h.lock_tag)).show();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            int h = this.f2384o.m2208h();
            int intExtra = intent.getIntExtra("requestMode", 0);
            int intExtra2 = intent.getIntExtra("requestType", 0);
            HashMap hashMap;
            HashMap hashMap2;
            if (intExtra == 1 && h == 1) {
                hashMap = (HashMap) intent.getSerializableExtra("itemFields");
                hashMap2 = new HashMap();
                hashMap2.put("requestType", String.valueOf(intExtra2));
                hashMap2.put("itemRecord", intent.getStringExtra("itemRecord"));
                hashMap2.put("itemRecordExtra", intent.getStringExtra("itemRecordExtra"));
                hashMap2.put("itemDescription", intent.getStringExtra("itemDescription"));
                hashMap2.put("itemHash", intent.getStringExtra("itemHash"));
                hashMap2.put("itemUpdate", String.valueOf(intent.getBooleanExtra("itemUpdate", false)));
                m2955a(intExtra2, hashMap2, hashMap);
            } else if (intExtra == 2 && h == 3) {
                hashMap = (HashMap) intent.getSerializableExtra("itemFields");
                hashMap2 = new HashMap();
                hashMap2.put("requestType", String.valueOf(intExtra2));
                hashMap2.put("itemTask", intent.getStringExtra("itemTask"));
                hashMap2.put("itemTaskExtra", intent.getStringExtra("itemTaskExtra"));
                hashMap2.put("itemDescription", intent.getStringExtra("itemDescription"));
                hashMap2.put("itemHash", intent.getStringExtra("itemHash"));
                hashMap2.put("itemUpdate", String.valueOf(intent.getBooleanExtra("itemUpdate", false)));
                m2958b(intExtra2, hashMap2, hashMap);
            } else if (intExtra == 3) {
                switch (intExtra2) {
                    case C0627j.View_paddingStart /*1*/:
                    case C0627j.Toolbar_titleTextAppearance /*9*/:
                        C0493f.m2081a(this, getString(C0625h.save_success));
                    case C0627j.View_paddingEnd /*2*/:
                        if (m2956a(intent.getStringExtra("jsonData"), intent.getBooleanExtra("isAppend", false))) {
                            C0493f.m2081a(this, getString(C0625h.load_success));
                        } else {
                            C0493f.m2081a(this, getString(C0625h.load_error));
                        }
                    case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                        if (m2959b(intent.getStringExtra("jsonData"))) {
                            C0493f.m2081a(this, getString(C0625h.load_success));
                        } else {
                            C0493f.m2081a(this, getString(C0625h.load_error));
                        }
                    case C0627j.Toolbar_theme /*17*/:
                        m2981p();
                        C0493f.m2081a(this, getString(C0625h.clear_record_list_success));
                    case C0627j.Toolbar_collapseIcon /*18*/:
                        m2982q();
                        C0493f.m2081a(this, getString(C0625h.clear_tasks_list_success));
                    default:
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.main);
        Intent intent = getIntent();
        this.f2384o = C0507a.m2175a();
        setRequestedOrientation(this.f2384o.m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        try {
            if (!C0507a.m2177n()) {
                if (this.f2384o.m2194b()) {
                    toolbar.inflateMenu(C0623f.mainpro);
                } else {
                    toolbar.inflateMenu(C0623f.main);
                }
            }
        } catch (Exception e) {
        }
        toolbar.setTitle(C0625h.app_name);
        toolbar.setOnMenuItemClickListener(this);
        this.f2377D = (ViewPager) findViewById(C0621d.pager);
        this.f2376C = new C0638r(m174f());
        this.f2377D.setAdapter(this.f2376C);
        this.f2382I = (SlidingTabStrip) findViewById(C0621d.sliding_tabs);
        this.f2382I.setViewPager(this.f2377D);
        this.f2382I.setOnPageChangeListener(this);
        this.f2384o.m2196c();
        this.f2383n = new C0469a(this);
        this.f2383n.m1890a((C0471c) this);
        this.f2383n.m1893a(f2373p);
        this.f2383n.m1888a();
        this.f2383n.m1889a(intent);
        int intExtra = intent.getIntExtra("NFC_TOOLS_PRE_OPENED_TAB", 0);
        if (intExtra < 0 || intExtra > 3) {
            intExtra = 0;
        }
        this.f2384o.m2189b(intExtra);
        this.f2377D.setCurrentItem(intExtra);
        if (C0500k.m2100b()) {
            new Builder(this).setTitle(getString(C0625h.error_always_finish_activities_title)).setMessage(getString(C0625h.error_always_finish_activities_message)).setPositiveButton(getString(C0625h.error_always_finish_activities_valid), new C06101(this)).setIcon(C0620c.error).show();
        }
        if (this.f2384o.m2187b(getApplicationContext()) == 0) {
            m2980o();
            this.f2384o.m2201d(1);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        if (this.f2384o.m2194b()) {
            menuInflater.inflate(C0623f.mainpro, menu);
        } else {
            menuInflater.inflate(C0623f.main, menu);
        }
        return true;
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        return onOptionsItemSelected(menuItem);
    }

    protected void onNewIntent(Intent intent) {
        this.f2383n.m1889a(intent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == C0621d.menu_about) {
            startActivityForResult(new Intent(this, AboutActivity.class), 1);
            return true;
        } else if (itemId == C0621d.menu_partner) {
            C0495h.m2084a("http://whiztags.wakdev.com/nfctools/2/");
            return true;
        } else if (itemId == C0621d.menu_donate) {
            if (this.f2384o.m2194b()) {
                C0495h.m2085a("com.wakdev.donate", 1);
                return true;
            }
            C0495h.m2085a("com.wakdev.nfctools.pro", 1);
            return true;
        } else if (itemId == C0621d.menu_rate) {
            if (this.f2384o.m2194b()) {
                C0495h.m2085a("com.wakdev.nfctools.pro", 1);
                return true;
            }
            C0495h.m2085a("com.wakdev.wdnfc", 1);
            return true;
        } else if (itemId == C0621d.menu_orientation) {
            switch (this.f2384o.m2178a(getApplicationContext())) {
                case C0627j.View_android_focusable /*0*/:
                    setRequestedOrientation(1);
                    this.f2384o.m2197c(1);
                    return true;
                case C0627j.View_paddingStart /*1*/:
                    setRequestedOrientation(0);
                    this.f2384o.m2197c(0);
                    return true;
                default:
                    return true;
            }
        } else if (itemId == C0621d.menu_api) {
            if (getResources().getConfiguration().locale.getLanguage().equals("fr")) {
                C0495h.m2084a("http://fr.api.nfc.systems/");
                return true;
            }
            C0495h.m2084a("http://en.api.nfc.systems/");
            return true;
        } else if (itemId == C0621d.menu_exit) {
            finish();
            return true;
        } else if (itemId != C0621d.menu_help) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            m2980o();
            return true;
        }
    }

    protected void onPause() {
        this.f2383n.m1903g();
        super.onPause();
    }

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    protected void onResume() {
        super.onResume();
        this.f2383n.m1902f();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void partnerLink1Click(View view) {
        C0495h.m2084a("http://whiztags.wakdev.com/nfctools/1/");
    }

    public void pwdProtectTag(View view) {
        View editText = new EditText(this);
        View linearLayout = new LinearLayout(new ContextThemeWrapper(this, C0626i.myPaddingDialogStyle));
        linearLayout.setOrientation(1);
        linearLayout.addView(editText);
        OnClickListener c06167 = new C06167(this, editText);
        new Builder(this).setMessage(getString(C0625h.set_password_dialog_msg)).setPositiveButton(getString(C0625h.valid_button), c06167).setNegativeButton(getString(C0625h.cancel_button), c06167).setIcon(C0620c.lock_warning_confirm).setTitle(getString(C0625h.set_password)).setView(linearLayout).setCancelable(false).show();
    }

    public void pwdUnprotectTag(View view) {
        View editText = new EditText(this);
        View linearLayout = new LinearLayout(new ContextThemeWrapper(this, C0626i.myPaddingDialogStyle));
        linearLayout.setOrientation(1);
        linearLayout.addView(editText);
        OnClickListener c06178 = new C06178(this, editText);
        new Builder(this).setMessage(getString(C0625h.unset_password_dialog_msg)).setPositiveButton(getString(C0625h.valid_button), c06178).setNegativeButton(getString(C0625h.cancel_button), c06178).setIcon(C0620c.unset_password).setTitle(getString(C0625h.unset_password)).setView(linearLayout).show();
    }

    public void readMemoryTag(View view) {
        this.f2384o.m2196c();
        this.f2384o.m2180a(9);
        m2986a(C0622e.dialog_info, C0600e.m2936a(C0620c.anim_approach, getString(C0625h.read_memory), getString(C0625h.approach_nfc_tag), true));
    }

    public void writeRecords(View view) {
        this.f2384o.m2200d();
        this.f2384o.m2180a(1);
        m2986a(C0622e.dialog_info, C0600e.m2936a(C0620c.anim_approach, getString(C0625h.write_dialog_title), getString(C0625h.approach_nfc_tag), true));
    }

    public void writeTasks(View view) {
        this.f2384o.m2200d();
        this.f2384o.m2180a(2);
        m2986a(C0622e.dialog_info, C0600e.m2936a(C0620c.anim_approach, getString(C0625h.write_dialog_title), getString(C0625h.approach_nfc_tag), true));
    }
}
