package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.p014a.C0446a;
import com.wakdev.p014a.C0447b;
import com.wakdev.p014a.C0454c;
import java.util.ArrayList;

public class ChooseRecordVideoActivity extends C0316b implements C0447b {
    private ListView f1183n;
    private C0454c f1184o;
    private ArrayList<C0446a> f1185p;

    private C0446a m2248a(int i, int i2, int i3, int i4) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        c0446a.m1807b(C0620c.item_next);
        c0446a.m1804a(getString(i3));
        c0446a.m1808b(getString(i4));
        return c0446a;
    }

    public void m2249a(C0446a c0446a) {
        int h = c0446a.m1819h();
        Intent intent = new Intent(this, RecordVideoActivity.class);
        switch (h) {
            case C0627j.Theme_actionModeSplitBackground /*26*/:
            case C0627j.Theme_actionModeCloseDrawable /*27*/:
            case C0627j.Theme_actionModeCutDrawable /*28*/:
                intent.putExtra("VIDEO_ID", h);
                break;
            default:
                intent = null;
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2250b(C0446a c0446a) {
        m2249a(c0446a);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.choose_task);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1185p = new ArrayList();
        this.f1185p.add(m2248a(26, C0620c.record_youtube, C0625h.record_video_youtube, C0625h.record_video_youtube_description));
        this.f1185p.add(m2248a(27, C0620c.record_vimeo, C0625h.record_video_vimeo, C0625h.record_video_vimeo_description));
        this.f1185p.add(m2248a(28, C0620c.record_dailymotion, C0625h.record_video_dailymotion, C0625h.record_video_dailymotion_description));
        this.f1183n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1184o = new C0454c(getApplicationContext(), this.f1185p);
        this.f1184o.m1842a((C0447b) this);
        this.f1183n.setAdapter(this.f1184o);
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
