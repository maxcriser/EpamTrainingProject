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

public class ChooseRecordSocialActivity extends C0316b implements C0447b {
    private ListView f1180n;
    private C0454c f1181o;
    private ArrayList<C0446a> f1182p;

    private C0446a m2245a(int i, int i2, int i3, int i4) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        c0446a.m1807b(C0620c.item_next);
        c0446a.m1804a(getString(i3));
        c0446a.m1808b(getString(i4));
        return c0446a;
    }

    public void m2246a(C0446a c0446a) {
        int h = c0446a.m1819h();
        Intent intent = new Intent(this, RecordSocialActivity.class);
        switch (h) {
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
                intent.putExtra("SOCIAL_ID", h);
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

    public void m2247b(C0446a c0446a) {
        m2246a(c0446a);
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
        this.f1182p = new ArrayList();
        this.f1182p.add(m2245a(15, C0620c.record_social_facebook, C0625h.record_social_facebook, C0625h.record_social_facebook_description));
        this.f1182p.add(m2245a(16, C0620c.record_social_twitter, C0625h.record_social_twitter, C0625h.record_social_twitter_description));
        this.f1182p.add(m2245a(17, C0620c.record_social_googleplus, C0625h.record_social_googleplus, C0625h.record_social_googleplus_description));
        this.f1182p.add(m2245a(18, C0620c.record_social_linkedin, C0625h.record_social_linkedin, C0625h.record_social_linkedin_description));
        this.f1182p.add(m2245a(19, C0620c.record_social_pinterest, C0625h.record_social_pinterest, C0625h.record_social_pinterest_description));
        this.f1182p.add(m2245a(20, C0620c.record_social_instagram, C0625h.record_social_instagram, C0625h.record_social_instagram_description));
        this.f1182p.add(m2245a(30, C0620c.record_social_dribbble, C0625h.record_social_dribbble, C0625h.record_social_dribbble_description));
        this.f1182p.add(m2245a(31, C0620c.record_social_flickr, C0625h.record_social_flickr, C0625h.record_social_flickr_description));
        this.f1182p.add(m2245a(21, C0620c.record_social_tumblr, C0625h.record_social_tumblr, C0625h.record_social_tumblr_description));
        this.f1182p.add(m2245a(22, C0620c.record_social_github, C0625h.record_social_github, C0625h.record_social_github_description));
        this.f1182p.add(m2245a(33, C0620c.record_social_slack, C0625h.record_social_slack, C0625h.record_social_slack_description));
        this.f1182p.add(m2245a(23, C0620c.record_social_skype, C0625h.record_social_skype, C0625h.record_social_skype_description));
        this.f1182p.add(m2245a(34, C0620c.record_social_snapchat, C0625h.record_social_snapchat, C0625h.record_social_snapchat_description));
        this.f1182p.add(m2245a(32, C0620c.record_social_reddit, C0625h.record_social_reddit, C0625h.record_social_reddit_description));
        this.f1182p.add(m2245a(35, C0620c.record_social_soundcloud, C0625h.record_social_soundcloud, C0625h.record_social_soundcloud_description));
        this.f1182p.add(m2245a(36, C0620c.record_social_steam, C0625h.record_social_steam, C0625h.record_social_steam_description));
        this.f1182p.add(m2245a(37, C0620c.record_social_twitch, C0625h.record_social_twitch, C0625h.record_social_twitch_description));
        this.f1180n = (ListView) findViewById(C0621d.mylistview_choose_task);
        this.f1181o = new C0454c(getApplicationContext(), this.f1182p);
        this.f1181o.m1842a((C0447b) this);
        this.f1180n.setAdapter(this.f1181o);
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
