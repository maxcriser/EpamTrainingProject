package com.wakdev.nfctools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.wakdev.libs.commons.C0505n;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.p015a.C0479k;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONObject;

/* renamed from: com.wakdev.nfctools.o */
public class C0630o extends Fragment implements C0447b {
    private TextView aa;
    private ListView ab;
    private C0454c ac;
    private View ad;
    private boolean ae;

    public C0630o() {
        this.ae = false;
    }

    private void m3030c(C0446a c0446a) {
        C0604l c0604l = (C0604l) m56b();
        if (c0446a.m1819h() == 110) {
            C0479k c = C0507a.m2175a().m2195c(c0446a.m1820i());
            if (c != null) {
                HashMap hashMap = new HashMap();
                hashMap.put("dialog_title", m42a(C0625h.dialog_action_title_record));
                int i = C0622e.dialog_action;
                int o = c0446a.m1826o();
                switch (o) {
                    case C0627j.View_paddingEnd /*2*/:
                    case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                        hashMap.put("dialog_description", c0446a.m1822k());
                        c0604l.m2993b(i, hashMap);
                        return;
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
                    case C0627j.Theme_actionModeSplitBackground /*26*/:
                    case C0627j.Theme_actionModeCloseDrawable /*27*/:
                    case C0627j.Theme_actionModeCutDrawable /*28*/:
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
                        hashMap.put("dialog_description", c.m2019l().toString());
                        hashMap.put("dialog_action", c.m2019l().toString());
                        hashMap.put("dialog_id_action", String.valueOf(o));
                        c0604l.m2993b(i, hashMap);
                        return;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        hashMap.put("dialog_description", c.toString());
                        hashMap.put("dialog_action", c.toString());
                        hashMap.put("dialog_id_action", String.valueOf(o));
                        c0604l.m2993b(i, hashMap);
                        return;
                    case C0627j.Theme_actionModeBackground /*25*/:
                        JSONObject a = C0505n.m2138a(c);
                        if (a != null) {
                            hashMap.put("dialog_title", m42a(C0625h.dialog_action_title_task));
                            hashMap.put("dialog_title_open", m42a(C0625h.dialog_action_title_execute));
                            hashMap.put("dialog_description", c0446a.m1822k());
                            hashMap.put("dialog_action", a.toString());
                            hashMap.put("dialog_id_action", String.valueOf(o));
                            c0604l.m2993b(i, hashMap);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
            return;
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("dialog_title", m42a(C0625h.dialog_action_title_default));
        int i2 = C0622e.dialog_action;
        switch (c0446a.m1819h()) {
            case 101:
                hashMap2.put("dialog_title", m42a(C0625h.tag_type));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 102:
                hashMap2.put("dialog_title", m42a(C0625h.tag_techlist));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 103:
                hashMap2.put("dialog_title", m42a(C0625h.tag_serial_number));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 104:
                hashMap2.put("dialog_title", m42a(C0625h.tag_atqa_def));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 105:
                hashMap2.put("dialog_title", m42a(C0625h.tag_sak_def));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 106:
                hashMap2.put("dialog_title", m42a(C0625h.tag_ats_def));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 107:
                hashMap2.put("dialog_title", m42a(C0625h.tag_hr));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 113:
                hashMap2.put("dialog_title", m42a(C0625h.nfc_manufacture_parameter));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 114:
                hashMap2.put("dialog_title", m42a(C0625h.nfc_system_code));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            case 115:
                hashMap2.put("dialog_title", m42a(C0625h.nfc_signature));
                hashMap2.put("dialog_description", c0446a.m1823l());
                c0604l.m2993b(i2, hashMap2);
            case 116:
                hashMap2.put("dialog_title", m42a(C0625h.nfc_dsfid));
                hashMap2.put("dialog_description", c0446a.m1822k());
                c0604l.m2993b(i2, hashMap2);
            default:
        }
    }

    public boolean m3031I() {
        return this.ae;
    }

    public View m3032a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.ad = layoutInflater.inflate(C0622e.section0, viewGroup, false);
        this.aa = (TextView) this.ad.findViewById(C0621d.mainlayout_section0_text1);
        this.aa.setText(m42a(C0625h.approach_nfc_tag));
        this.ae = false;
        return this.ad;
    }

    public void m3033a(C0446a c0446a) {
        m3030c(c0446a);
    }

    public void m3034a(ArrayList<C0446a> arrayList) {
        this.ad.findViewById(C0621d.mainlayout_section0).setVisibility(8);
        this.ab = (ListView) this.ad.findViewById(C0621d.mylistview_section0);
        this.ac = new C0454c(this.ad.getContext(), arrayList);
        this.ac.m1842a((C0447b) this);
        this.ab.setAdapter(this.ac);
        this.ae = true;
    }

    public void m3035b(C0446a c0446a) {
        m3030c(c0446a);
    }
}
