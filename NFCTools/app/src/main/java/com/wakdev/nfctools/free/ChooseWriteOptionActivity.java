package com.wakdev.nfctools.free;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0500k;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.nfctools.ProEditionActivity;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;

public class ChooseWriteOptionActivity extends C0316b implements C0447b {
    private ListView f2370n;
    private C0454c f2371o;
    private ArrayList<C0446a> f2372p;

    /* renamed from: com.wakdev.nfctools.free.ChooseWriteOptionActivity.1 */
    class C06031 implements OnClickListener {
        final /* synthetic */ ChooseWriteOptionActivity f2369a;

        C06031(ChooseWriteOptionActivity chooseWriteOptionActivity) {
            this.f2369a = chooseWriteOptionActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    Intent intent = new Intent();
                    intent.putExtra("requestMode", 3);
                    intent.putExtra("requestType", 17);
                    this.f2369a.setResult(-1, intent);
                    this.f2369a.finish();
                    this.f2369a.overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                default:
            }
        }
    }

    private C0446a m2946a(int i, int i2, int i3, int i4) {
        return m2947a(i, i2, i3, i4, 0);
    }

    private C0446a m2947a(int i, int i2, int i3, int i4, int i5) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        if (i5 != 0) {
            c0446a.m1807b(i5);
        }
        c0446a.m1804a(getString(i3));
        c0446a.m1808b(getString(i4));
        return c0446a;
    }

    private void m2948j() {
        OnClickListener c06031 = new C06031(this);
        new Builder(this).setMessage(getString(C0625h.clear_record_list_are_you_sure)).setPositiveButton(getString(C0625h.yes), c06031).setNegativeButton(getString(C0625h.no), c06031).setIcon(C0620c.clear_records_list).setTitle(getString(C0625h.clear_record_list)).show();
    }

    public void m2949a(C0446a c0446a) {
        Intent intent = null;
        switch (c0446a.m1819h()) {
            case C0627j.Toolbar_theme /*17*/:
                m2948j();
                break;
            default:
                intent = new Intent(this, ProEditionActivity.class);
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2950b(C0446a c0446a) {
        m2949a(c0446a);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && intent.getIntExtra("requestMode", 0) == 3) {
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130903070);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon((int) C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f2372p = new ArrayList();
        this.f2372p.add(m2946a(17, C0620c.clear_records_list, C0625h.clear_record_list, C0625h.clear_record_list_description));
        this.f2372p.add(m2947a(1, C0620c.tags_profiles_add, C0625h.save_tag_profile, C0625h.save_tag_profile_description, C0620c.item_pro));
        this.f2372p.add(m2947a(2, C0620c.tags_profiles_load, C0625h.load_tag_profile, C0625h.load_tag_profile_description, C0620c.item_pro));
        this.f2372p.add(m2947a(3, C0620c.tags_profiles_manage, C0625h.manage_tag_profile, C0625h.manage_tag_profile_description, C0620c.item_pro));
        this.f2372p.add(m2947a(8, C0620c.tags_profiles_export_all, C0625h.export_all_tag_profile, C0625h.export_all_tag_profile_description, C0620c.item_pro));
        this.f2372p.add(m2947a(5, C0620c.tags_profiles_import, C0625h.import_tag_profile, C0625h.import_tag_profile_description, C0620c.item_pro));
        this.f2372p.add(m2947a(6, C0620c.import_from_tag, C0625h.import_records_from_tag, C0625h.import_records_from_tag_description, C0620c.item_pro));
        if (VERSION.SDK_INT < 19 || !C0500k.m2102d()) {
            this.f2372p.add(m2947a(22, C0620c.emulate1, C0625h.option_emulate_nfc_tag, C0625h.option_emulate_nfc_tag_not_available, C0620c.lock_warning_icon));
        } else {
            this.f2372p.add(m2947a(22, C0620c.emulate1, C0625h.option_emulate_nfc_tag, C0625h.option_emulate_nfc_tag_description, C0620c.item_pro));
        }
        this.f2370n = (ListView) findViewById(2131624011);
        this.f2371o = new C0454c(getApplicationContext(), this.f2372p);
        this.f2371o.m1842a((C0447b) this);
        this.f2370n.setAdapter(this.f2371o);
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
}
