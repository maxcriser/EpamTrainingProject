package com.wakdev.nfctools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.C0316b;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.wakdev.libs.commons.C0490e;
import com.wakdev.libs.commons.C0493f;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0628m.C0618a;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0621d;
import com.wakdev.nfctools.C0628m.C0622e;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.HashMap;

public class RecordSocialActivity extends C0316b {
    private int f1342n;
    private boolean f1343o;
    private String f1344p;
    private String f1345q;
    private TextView f1346r;
    private TextView f1347s;
    private EditText f1348t;

    /* renamed from: com.wakdev.nfctools.RecordSocialActivity.1 */
    class C05311 implements TextWatcher {
        final /* synthetic */ RecordSocialActivity f1341a;

        C05311(RecordSocialActivity recordSocialActivity) {
            this.f1341a = recordSocialActivity;
        }

        public void afterTextChanged(Editable editable) {
            this.f1341a.f1347s.setText(this.f1341a.f1345q.replace("#USERNAME#", this.f1341a.f1348t.getText()));
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public RecordSocialActivity() {
        this.f1342n = -1;
        this.f1343o = false;
        this.f1344p = null;
    }

    private void m2359j() {
        Intent intent = getIntent();
        this.f1343o = intent.getBooleanExtra("itemUpdate", false);
        this.f1344p = intent.getStringExtra("itemHash");
        if (this.f1343o && this.f1344p != null) {
            HashMap hashMap = (HashMap) intent.getSerializableExtra("itemFields");
            if (hashMap != null) {
                C0490e.m2075a(this.f1348t, (String) hashMap.get("field1"));
            }
        }
    }

    private HashMap<String, String> m2360k() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("field1", this.f1348t.getText().toString());
        return hashMap;
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCancelButtonClick(View view) {
        setResult(0, null);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.record_social);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1348t = (EditText) findViewById(C0621d.myUserNameRecord);
        this.f1346r = (TextView) findViewById(C0621d.myTitleRecord);
        this.f1347s = (TextView) findViewById(C0621d.myURLRecord);
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

    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected void onStart() {
        int i;
        int i2;
        int i3;
        int i4;
        super.onStart();
        this.f1342n = getIntent().getIntExtra("SOCIAL_ID", -1);
        switch (this.f1342n) {
            case C0627j.Toolbar_titleMarginBottom /*15*/:
                i = C0625h.record_social_facebook;
                i2 = C0620c.record_social_facebook;
                i3 = C0625h.record_social_facebook_hint;
                i4 = C0625h.record_social_facebook_url;
                break;
            case C0627j.Toolbar_maxButtonHeight /*16*/:
                i = C0625h.record_social_twitter;
                i2 = C0620c.record_social_twitter;
                i3 = C0625h.record_social_twitter_hint;
                i4 = C0625h.record_social_twitter_url;
                break;
            case C0627j.Toolbar_theme /*17*/:
                i = C0625h.record_social_googleplus;
                i2 = C0620c.record_social_googleplus;
                i3 = C0625h.record_social_googleplus_hint;
                i4 = C0625h.record_social_googleplus_url;
                break;
            case C0627j.Toolbar_collapseIcon /*18*/:
                i = C0625h.record_social_linkedin;
                i2 = C0620c.record_social_linkedin;
                i3 = C0625h.record_social_linkedin_hint;
                i4 = C0625h.record_social_linkedin_url;
                break;
            case C0627j.Toolbar_collapseContentDescription /*19*/:
                i = C0625h.record_social_pinterest;
                i2 = C0620c.record_social_pinterest;
                i3 = C0625h.record_social_pinterest_hint;
                i4 = C0625h.record_social_pinterest_url;
                break;
            case C0627j.Toolbar_navigationIcon /*20*/:
                i = C0625h.record_social_instagram;
                i2 = C0620c.record_social_instagram;
                i3 = C0625h.record_social_instagram_hint;
                i4 = C0625h.record_social_instagram_url;
                break;
            case C0627j.Toolbar_navigationContentDescription /*21*/:
                i = C0625h.record_social_tumblr;
                i2 = C0620c.record_social_tumblr;
                i3 = C0625h.record_social_tumblr_hint;
                i4 = C0625h.record_social_tumblr_url;
                break;
            case C0627j.Theme_actionMenuTextColor /*22*/:
                i = C0625h.record_social_github;
                i2 = C0620c.record_social_github;
                i3 = C0625h.record_social_github_hint;
                i4 = C0625h.record_social_github_url;
                break;
            case C0627j.Theme_actionModeStyle /*23*/:
                i = C0625h.record_social_skype;
                i2 = C0620c.record_social_skype;
                i3 = C0625h.record_social_skype_hint;
                i4 = C0625h.record_social_skype_url;
                break;
            case C0627j.Theme_actionModePasteDrawable /*30*/:
                i = C0625h.record_social_dribbble;
                i2 = C0620c.record_social_dribbble;
                i3 = C0625h.record_social_dribbble_hint;
                i4 = C0625h.record_social_dribbble_url;
                break;
            case C0627j.Theme_actionModeSelectAllDrawable /*31*/:
                i = C0625h.record_social_flickr;
                i2 = C0620c.record_social_flickr;
                i3 = C0625h.record_social_flickr_hint;
                i4 = C0625h.record_social_flickr_url;
                break;
            case C0627j.Theme_actionModeShareDrawable /*32*/:
                i = C0625h.record_social_reddit;
                i2 = C0620c.record_social_reddit;
                i3 = C0625h.record_social_reddit_hint;
                i4 = C0625h.record_social_reddit_url;
                break;
            case C0627j.Theme_actionModeFindDrawable /*33*/:
                i = C0625h.record_social_slack;
                i2 = C0620c.record_social_slack;
                i3 = C0625h.record_social_slack_hint;
                i4 = C0625h.record_social_slack_url;
                break;
            case C0627j.Theme_actionModeWebSearchDrawable /*34*/:
                i = C0625h.record_social_snapchat;
                i2 = C0620c.record_social_snapchat;
                i3 = C0625h.record_social_snapchat_hint;
                i4 = C0625h.record_social_snapchat_url;
                break;
            case C0627j.Theme_actionModePopupWindowStyle /*35*/:
                i = C0625h.record_social_soundcloud;
                i2 = C0620c.record_social_soundcloud;
                i3 = C0625h.record_social_soundcloud_hint;
                i4 = C0625h.record_social_soundcloud_url;
                break;
            case C0627j.Theme_textAppearanceLargePopupMenu /*36*/:
                i = C0625h.record_social_steam;
                i2 = C0620c.record_social_steam;
                i3 = C0625h.record_social_steam_hint;
                i4 = C0625h.record_social_steam_url;
                break;
            case C0627j.Theme_textAppearanceSmallPopupMenu /*37*/:
                i = C0625h.record_social_twitch;
                i2 = C0620c.record_social_twitch;
                i3 = C0625h.record_social_twitch_hint;
                i4 = C0625h.record_social_twitch_url;
                break;
            default:
                C0493f.m2081a(this, getString(C0625h.error));
                finish();
                i4 = -1;
                i3 = -1;
                i = -1;
                i2 = -1;
                break;
        }
        if (i == -1 || i2 == -1 || i3 == -1 || i4 == -1) {
            C0493f.m2081a(this, getString(C0625h.error));
            finish();
        }
        this.f1346r.setText(i);
        this.f1346r.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(i2), null, null, null);
        this.f1348t.setHint(i3);
        this.f1345q = getString(i4);
        this.f1347s.setText(this.f1345q.replace("#USERNAME#", this.f1348t.getText()));
        this.f1348t.addTextChangedListener(new C05311(this));
        m2359j();
    }

    public void onValidateButtonClick(View view) {
        Object obj = this.f1348t.getText().toString();
        if (obj.isEmpty()) {
            C0493f.m2081a(this, getString(C0625h.err_some_fields_are_empty));
            return;
        }
        String replace = this.f1345q.replace("#USERNAME#", obj);
        Intent intent = new Intent();
        intent.putExtra("requestMode", 1);
        intent.putExtra("requestType", this.f1342n);
        intent.putExtra("itemRecord", replace);
        intent.putExtra("itemDescription", obj);
        intent.putExtra("itemHash", this.f1344p);
        intent.putExtra("itemUpdate", this.f1343o);
        intent.putExtra("itemFields", m2360k());
        setResult(-1, intent);
        finish();
        overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
    }
}
