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

public class ChooseRecordActivity extends C0316b implements C0447b {
    private ListView f1177n;
    private C0454c f1178o;
    private ArrayList<C0446a> f1179p;

    private C0446a m2242a(int i, int i2, int i3, int i4) {
        C0446a c0446a = new C0446a();
        c0446a.m1811c(i);
        c0446a.m1802a(i2);
        c0446a.m1807b(C0620c.item_next);
        c0446a.m1804a(getString(i3));
        c0446a.m1808b(getString(i4));
        return c0446a;
    }

    public void m2243a(C0446a c0446a) {
        Intent intent = null;
        switch (c0446a.m1819h()) {
            case C0627j.View_paddingStart /*1*/:
                intent = new Intent(this, RecordTextActivity.class);
                break;
            case C0627j.View_paddingEnd /*2*/:
                intent = new Intent(this, RecordURIActivity.class);
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                intent = new Intent(this, RecordApplicationActivity.class);
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                intent = new Intent(this, RecordMailActivity.class);
                break;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                intent = new Intent(this, RecordContactActivity.class);
                break;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                intent = new Intent(this, RecordTelActivity.class);
                break;
            case C0627j.Toolbar_contentInsetRight /*7*/:
                intent = new Intent(this, RecordSMSActivity.class);
                break;
            case C0627j.Toolbar_popupTheme /*8*/:
                intent = new Intent(this, RecordCustomActivity.class);
                break;
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                intent = new Intent(this, RecordGeocodeActivity.class);
                break;
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                intent = new Intent(this, RecordAddressActivity.class);
                break;
            case C0627j.Toolbar_titleMargins /*11*/:
                intent = new Intent(this, RecordBluetoothActivity.class);
                break;
            case C0627j.Toolbar_titleMarginStart /*12*/:
                intent = new Intent(this, RecordCustomURIActivity.class);
                break;
            case C0627j.Toolbar_titleMarginEnd /*13*/:
                intent = new Intent(this, RecordWifiActivity.class);
                break;
            case C0627j.Toolbar_titleMarginTop /*14*/:
                intent = new Intent(this, ChooseRecordSocialActivity.class);
                break;
            case C0627j.Theme_actionModeCloseButtonStyle /*24*/:
                intent = new Intent(this, RecordFileActivity.class);
                break;
            case C0627j.Theme_actionModeBackground /*25*/:
                intent = new Intent(this, ChooseRecordVideoActivity.class);
                break;
            case C0627j.Theme_actionModeCopyDrawable /*29*/:
                intent = new Intent(this, RecordBitcoinActivity.class);
                break;
            case C0627j.Theme_actionDropDownStyle /*38*/:
                intent = new Intent(this, RecordSearchActivity.class);
                break;
            case C0627j.Theme_dropdownListPreferredItemHeight /*39*/:
                intent = new Intent(this, RecordDestinationActivity.class);
                break;
            case C0627j.Theme_spinnerStyle /*40*/:
                intent = new Intent(this, RecordProximitySearchActivity.class);
                break;
            case C0627j.Theme_spinnerDropDownItemStyle /*41*/:
                intent = new Intent(this, RecordStreetViewActivity.class);
                break;
        }
        if (intent != null) {
            startActivityForResult(intent, 1);
            overridePendingTransition(C0618a.slide_left_in, C0618a.slide_left_out);
        }
    }

    public void m2244b(C0446a c0446a) {
        m2243a(c0446a);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            setResult(-1, intent);
            finish();
            overridePendingTransition(C0618a.slide_right_in, C0618a.slide_right_out);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C0622e.choose_record);
        setRequestedOrientation(C0507a.m2175a().m2178a(getApplicationContext()));
        Toolbar toolbar = (Toolbar) findViewById(C0621d.my_awesome_toolbar);
        toolbar.setNavigationIcon(C0620c.abc_ic_ab_back_mtrl_am_alpha);
        m1533a(toolbar);
        this.f1179p = new ArrayList();
        this.f1179p.add(m2242a(1, C0620c.nfc_type_text, C0625h.text, C0625h.add_text_record));
        this.f1179p.add(m2242a(2, C0620c.nfc_type_uri, C0625h.url_uri, C0625h.add_url_record));
        this.f1179p.add(m2242a(12, C0620c.nfc_type_uri_custom, C0625h.url_uri_custom, C0625h.add_uri_record));
        this.f1179p.add(m2242a(38, C0620c.record_search, C0625h.record_search, C0625h.record_search_description));
        this.f1179p.add(m2242a(14, C0620c.record_social, C0625h.record_social, C0625h.record_social_description));
        this.f1179p.add(m2242a(25, C0620c.record_video, C0625h.record_video, C0625h.record_video_description));
        this.f1179p.add(m2242a(24, C0620c.nfc_type_file, C0625h.record_file, C0625h.record_file_description));
        this.f1179p.add(m2242a(3, C0620c.nfc_type_app, C0625h.app, C0625h.add_app_record));
        this.f1179p.add(m2242a(4, C0620c.nfc_type_mail, C0625h.mail, C0625h.add_mail_record));
        this.f1179p.add(m2242a(5, C0620c.nfc_type_contact, C0625h.contact, C0625h.add_contact_record));
        this.f1179p.add(m2242a(6, C0620c.nfc_type_tel, C0625h.tel, C0625h.add_tel_record));
        this.f1179p.add(m2242a(7, C0620c.nfc_type_sms, C0625h.sms, C0625h.add_sms_record));
        this.f1179p.add(m2242a(9, C0620c.nfc_type_geo, C0625h.location, C0625h.add_geo_record));
        this.f1179p.add(m2242a(10, C0620c.nfc_type_address, C0625h.address, C0625h.add_address_record));
        this.f1179p.add(m2242a(39, C0620c.record_destination, C0625h.record_destination, C0625h.record_destination_description));
        this.f1179p.add(m2242a(40, C0620c.record_poi, C0625h.record_proximity_search, C0625h.record_proximity_search_description));
        this.f1179p.add(m2242a(41, C0620c.record_streetview, C0625h.record_streetview, C0625h.record_streetview_description));
        this.f1179p.add(m2242a(29, C0620c.record_bitcoin, C0625h.record_bitcoin, C0625h.record_bitcoin_description));
        this.f1179p.add(m2242a(11, C0620c.nfc_type_bluetooth, C0625h.bluetooth, C0625h.add_bluetooth_record));
        this.f1179p.add(m2242a(13, C0620c.task_wifi_network, C0625h.record_wifi, C0625h.task_wifi_network_description));
        this.f1179p.add(m2242a(8, C0620c.nfc_type_data, C0625h.data_listitem, C0625h.add_custom_record));
        this.f1177n = (ListView) findViewById(C0621d.mylistview_choose_record);
        this.f1178o = new C0454c(getApplicationContext(), this.f1179p);
        this.f1178o.m1842a((C0447b) this);
        this.f1177n.setAdapter(this.f1178o);
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
