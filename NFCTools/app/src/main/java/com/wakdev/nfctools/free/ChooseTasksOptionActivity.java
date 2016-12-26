package com.wakdev.nfctools.free;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.commons.C0499j;
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

public class ChooseTasksOptionActivity extends C0316b implements C0447b {
    private ListView f2366n;
    private C0454c f2367o;
    private ArrayList<C0446a> f2368p;

    /* renamed from: com.wakdev.nfctools.free.ChooseTasksOptionActivity.1 */
    class C06021 implements OnClickListener {
        final /* synthetic */ ChooseTasksOptionActivity f2365a;

        C06021(ChooseTasksOptionActivity chooseTasksOptionActivity) {
            this.f2365a = chooseTasksOptionActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case ListPopupWindow.MATCH_PARENT /*-1*/:
                    Intent intent = new Intent();
                    intent.putExtra("requestMode", 3);
                    intent.putExtra("requestType", 18);
                    this.f2365a.setResult(-1, intent);
                    this.f2365a.finish();
                    this.f2365a.overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
                default:
            }
        }
    }

    private C0446a m2941a(int i, int i2, int i3, int i4) {
        return m2942a(i, i2, i3, i4, 0);
    }

    private C0446a m2942a(int i, int i2, int i3, int i4, int i5) {
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

    private void m2943j() {
        OnClickListener c06021 = new C06021(this);
        new Builder(this).setMessage(getString(C0625h.clear_tasks_list_are_you_sure)).setPositiveButton(getString(C0625h.yes), c06021).setNegativeButton(getString(C0625h.no), c06021).setIcon(C0620c.clear_records_list).setTitle(getString(C0625h.clear_tasks_list)).show();
    }

    public void m2944a(C0446a c0446a) {
        Intent intent = null;
        switch (c0446a.m1819h()) {
            case C0627j.Toolbar_collapseIcon /*18*/:
                m2943j();
                break;
            case C0627j.Theme_actionModeStyle /*23*/:
                if (!C0499j.m2096a("com.wakdev.nfctasks")) {
                    C0493f.m2081a(this, getString(C0625h.need_nfctasks));
                    break;
                }
                intent = new Intent();
                intent.setAction("com.wakdev.nfctasks.OPEN_SETTINGS");
                try {
                    startActivityForResult(intent, 1);
                    overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
                    return;
                } catch (Exception e) {
                    C0493f.m2081a(this, getString(C0625h.need_update_nfctasks));
                    return;
                }
            default:
                intent = new Intent(this, ProEditionActivity.class);
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2945b(C0446a c0446a) {
        m2944a(c0446a);
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
        this.f2368p = new ArrayList();
        this.f2368p.add(m2941a(18, C0620c.clear_records_list, C0625h.clear_tasks_list, C0625h.clear_tasks_list_description));
        this.f2368p.add(m2942a(23, C0620c.option_execution_settings, C0625h.configure_tasks_execution, C0625h.configure_tasks_execution_description, C0620c.item_next));
        this.f2368p.add(m2942a(9, C0620c.tasks_profiles_add, C0625h.save_tasks_profile, C0625h.save_tasks_profile_description, C0620c.item_pro));
        this.f2368p.add(m2942a(10, C0620c.tasks_profiles_load, C0625h.load_tasks_profile, C0625h.load_tasks_profile_description, C0620c.item_pro));
        this.f2368p.add(m2942a(11, C0620c.tasks_profiles_manage, C0625h.manage_tasks_profile, C0625h.manage_tasks_profile_description, C0620c.item_pro));
        this.f2368p.add(m2942a(16, C0620c.tasks_profiles_export_all, C0625h.export_all_tasks_profile, C0625h.export_all_tasks_profile_description, C0620c.item_pro));
        this.f2368p.add(m2942a(13, C0620c.tasks_profiles_import, C0625h.import_tasks_profile, C0625h.import_tasks_profile_description, C0620c.item_pro));
        this.f2368p.add(m2942a(20, C0620c.tasks_profiles_reuse, C0625h.reuse_tasks_profiles, C0625h.reuse_tasks_profiles_description, C0620c.item_pro));
        this.f2368p.add(m2942a(14, C0620c.import_from_tag, C0625h.import_tasks_from_tag, C0625h.import_tasks_from_tag_description, C0620c.item_pro));
        this.f2368p.add(m2942a(19, C0620c.tasks_profiles_run, C0625h.run_tasks_profile, C0625h.run_tasks_profile_description, C0620c.item_pro));
        this.f2366n = (ListView) findViewById(2131624011);
        this.f2367o = new C0454c(getApplicationContext(), this.f2368p);
        this.f2367o.m1842a((C0447b) this);
        this.f2366n.setAdapter(this.f2367o);
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
