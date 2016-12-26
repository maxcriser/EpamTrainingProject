package com.wakdev.libs.commons;

import android.content.Context;
import com.wakdev.libs.core.C0507a;
import com.wakdev.libs.core.WDCore;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0620c;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0641s;
import com.wakdev.nfctools.C0642t;
import com.wakdev.nfctools.C0643u;
import com.wakdev.nfctools.C0644v;
import com.wakdev.nfctools.C0645w;
import com.wakdev.nfctools.C0646x;
import com.wakdev.nfctools.C0648y;
import com.wakdev.nfctools.C0649z;
import com.wakdev.nfctools.ChooseTaskSamsungActivity;
import com.wakdev.nfctools.ChooseTaskSettingsActivity;
import com.wakdev.nfctools.ProEditionActivity;
import com.wakdev.nfctools.TaskAdaptiveBrightnessActivity;
import com.wakdev.nfctools.TaskAddressActivity;
import com.wakdev.nfctools.TaskAirViewActivity;
import com.wakdev.nfctools.TaskAlarmInActivity;
import com.wakdev.nfctools.TaskAlarmSetActivity;
import com.wakdev.nfctools.TaskApplicationActivity;
import com.wakdev.nfctools.TaskAutoRotateActivity;
import com.wakdev.nfctools.TaskBatterySaverActivity;
import com.wakdev.nfctools.TaskBlockingModeActivity;
import com.wakdev.nfctools.TaskBluetoothDeviceConnectActivity;
import com.wakdev.nfctools.TaskBluetoothDeviceDisconnectActivity;
import com.wakdev.nfctools.TaskBluetoothDeviceUnpairActivity;
import com.wakdev.nfctools.TaskBluetoothDiscoverableActivity;
import com.wakdev.nfctools.TaskBluetoothStateActivity;
import com.wakdev.nfctools.TaskBrightnessActivity;
import com.wakdev.nfctools.TaskBrightnessModeActivity;
import com.wakdev.nfctools.TaskButtonActivity;
import com.wakdev.nfctools.TaskCarModeActivity;
import com.wakdev.nfctools.TaskCondDateActivity;
import com.wakdev.nfctools.TaskCondDayActivity;
import com.wakdev.nfctools.TaskCondDayOfMonthActivity;
import com.wakdev.nfctools.TaskCondMonthActivity;
import com.wakdev.nfctools.TaskCondScanNumberActivity;
import com.wakdev.nfctools.TaskCondTimeActivity;
import com.wakdev.nfctools.TaskCondYearActivity;
import com.wakdev.nfctools.TaskDPadActivity;
import com.wakdev.nfctools.TaskDelVarActivity;
import com.wakdev.nfctools.TaskDestinationActivity;
import com.wakdev.nfctools.TaskDisableAppActivity;
import com.wakdev.nfctools.TaskDisplayTimeOutActivity;
import com.wakdev.nfctools.TaskDoNotDisturbActivity;
import com.wakdev.nfctools.TaskDoNotDisturbPlusActivity;
import com.wakdev.nfctools.TaskDrivingModeActivity;
import com.wakdev.nfctools.TaskEditVarActivity;
import com.wakdev.nfctools.TaskEnableAppActivity;
import com.wakdev.nfctools.TaskEventActivity;
import com.wakdev.nfctools.TaskExeCMDActivity;
import com.wakdev.nfctools.TaskExpandNotificationsActivity;
import com.wakdev.nfctools.TaskForgotWifiActivity;
import com.wakdev.nfctools.TaskGPSStateActivity;
import com.wakdev.nfctools.TaskGamePadActivity;
import com.wakdev.nfctools.TaskGeocodeActivity;
import com.wakdev.nfctools.TaskHotspotStateActivity;
import com.wakdev.nfctools.TaskKeyboardActivity;
import com.wakdev.nfctools.TaskKillAppActivity;
import com.wakdev.nfctools.TaskKillAppRootActivity;
import com.wakdev.nfctools.TaskMailActivity;
import com.wakdev.nfctools.TaskMediaControlActivity;
import com.wakdev.nfctools.TaskMobileDataStateActivity;
import com.wakdev.nfctools.TaskMultiWindowActivity;
import com.wakdev.nfctools.TaskNFCStateActivity;
import com.wakdev.nfctools.TaskNotificationAlertActivity;
import com.wakdev.nfctools.TaskNotificationLightStateActivity;
import com.wakdev.nfctools.TaskNumPadActivity;
import com.wakdev.nfctools.TaskOpenFileActivity;
import com.wakdev.nfctools.TaskPhoneCallActivity;
import com.wakdev.nfctools.TaskPlaneStateActivity;
import com.wakdev.nfctools.TaskPowerSavingModeActivity;
import com.wakdev.nfctools.TaskProximitySearchActivity;
import com.wakdev.nfctools.TaskRingtone1Activity;
import com.wakdev.nfctools.TaskRingtone2Activity;
import com.wakdev.nfctools.TaskRingtone3Activity;
import com.wakdev.nfctools.TaskRunShortcutActivity;
import com.wakdev.nfctools.TaskRunToolActivity;
import com.wakdev.nfctools.TaskSMSActivity;
import com.wakdev.nfctools.TaskScreenshotActivity;
import com.wakdev.nfctools.TaskSearchActivity;
import com.wakdev.nfctools.TaskSendSMSActivity;
import com.wakdev.nfctools.TaskShowAppDetailsActivity;
import com.wakdev.nfctools.TaskSleepTimerActivity;
import com.wakdev.nfctools.TaskSoundLevel1Activity;
import com.wakdev.nfctools.TaskSoundLevel2Activity;
import com.wakdev.nfctools.TaskSoundLevel3Activity;
import com.wakdev.nfctools.TaskSoundLevel4Activity;
import com.wakdev.nfctools.TaskSoundLevel5Activity;
import com.wakdev.nfctools.TaskSoundLevel6Activity;
import com.wakdev.nfctools.TaskSoundLevel7Activity;
import com.wakdev.nfctools.TaskSoundModeActivity;
import com.wakdev.nfctools.TaskSpeakerStateActivity;
import com.wakdev.nfctools.TaskStreetViewActivity;
import com.wakdev.nfctools.TaskTTSActivity;
import com.wakdev.nfctools.TaskTaskerActivity;
import com.wakdev.nfctools.TaskTelActivity;
import com.wakdev.nfctools.TaskTimerSetActivity;
import com.wakdev.nfctools.TaskToolboxActivity;
import com.wakdev.nfctools.TaskTwitterActivity;
import com.wakdev.nfctools.TaskURLURIActivity;
import com.wakdev.nfctools.TaskUninstallAppActivity;
import com.wakdev.nfctools.TaskWifiNetworkActivity;
import com.wakdev.nfctools.TaskWifiStateActivity;
import com.wakdev.nfctools.TaskZenModeActivity;
import com.wakdev.nfctools.aa;
import com.wakdev.nfctools.ab;
import com.wakdev.nfctools.ac;
import com.wakdev.nfctools.ad;
import com.wakdev.nfctools.ae;
import com.wakdev.nfctools.af;
import com.wakdev.nfctools.ag;
import com.wakdev.nfctools.ah;
import com.wakdev.nfctools.ai;
import com.wakdev.nfctools.aj;
import com.wakdev.nfctools.ak;
import com.wakdev.nfctools.al;
import com.wakdev.nfctools.am;
import com.wakdev.nfctools.an;
import com.wakdev.nfctools.ao;
import com.wakdev.nfctools.ap;
import com.wakdev.nfctools.aq;
import com.wakdev.nfctools.ar;
import com.wakdev.nfctools.as;
import com.wakdev.nfctools.at;
import com.wakdev.nfctools.au;
import com.wakdev.nfctools.av;
import com.wakdev.nfctools.aw;
import com.wakdev.nfctools.ax;
import com.wakdev.nfctools.ay;
import com.wakdev.nfctools.az;
import com.wakdev.nfctools.ba;
import com.wakdev.nfctools.bb;
import com.wakdev.nfctools.bc;
import com.wakdev.nfctools.bd;
import com.wakdev.nfctools.be;
import com.wakdev.nfctools.bf;
import com.wakdev.nfctools.bg;
import com.wakdev.nfctools.bh;
import com.wakdev.nfctools.bi;
import com.wakdev.nfctools.bj;
import com.wakdev.nfctools.bk;
import com.wakdev.nfctools.bl;
import com.wakdev.nfctools.bm;
import com.wakdev.nfctools.bn;
import com.wakdev.nfctools.bo;
import com.wakdev.nfctools.bp;
import com.wakdev.nfctools.bq;
import com.wakdev.nfctools.br;
import com.wakdev.nfctools.bs;
import com.wakdev.nfctools.bt;
import com.wakdev.nfctools.bu;
import com.wakdev.nfctools.bv;
import com.wakdev.nfctools.bw;
import com.wakdev.nfctools.bx;
import com.wakdev.nfctools.by;
import com.wakdev.nfctools.bz;
import com.wakdev.nfctools.ca;
import com.wakdev.nfctools.cb;
import com.wakdev.nfctools.cc;
import com.wakdev.nfctools.cd;
import com.wakdev.nfctools.ce;
import com.wakdev.nfctools.cf;
import com.wakdev.nfctools.cg;
import com.wakdev.nfctools.ch;
import com.wakdev.nfctools.ci;
import com.wakdev.nfctools.cj;
import com.wakdev.nfctools.ck;
import com.wakdev.nfctools.cl;
import com.wakdev.nfctools.cm;
import com.wakdev.nfctools.cn;
import com.wakdev.nfctools.co;
import com.wakdev.nfctools.cp;
import com.wakdev.nfctools.cq;
import com.wakdev.nfctools.cr;
import com.wakdev.nfctools.cs;
import com.wakdev.nfctools.ct;
import com.wakdev.nfctools.cu;
import com.wakdev.nfctools.cv;
import com.wakdev.p014a.C0446a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

/* renamed from: com.wakdev.libs.commons.a */
public class C0486a {
    private static HashMap<C0481m, C0485a> f1125a;

    /* renamed from: com.wakdev.libs.commons.a.1 */
    static class C04841 implements Comparator<C0446a> {
        C04841() {
        }

        public int m2042a(C0446a c0446a, C0446a c0446a2) {
            return c0446a.m1821j().compareTo(c0446a2.m1821j());
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m2042a((C0446a) obj, (C0446a) obj2);
        }
    }

    /* renamed from: com.wakdev.libs.commons.a.a */
    private static class C0485a {
        public int f1120a;
        public int f1121b;
        public int f1122c;
        public Class<?> f1123d;
        public boolean f1124e;

        public C0485a(int i, int i2, int i3, Class<?> cls, boolean z) {
            this.f1120a = -1;
            this.f1121b = -1;
            this.f1122c = -1;
            this.f1123d = null;
            this.f1124e = false;
            this.f1120a = i;
            this.f1121b = i2;
            this.f1122c = i3;
            this.f1123d = cls;
            this.f1124e = z;
        }
    }

    static {
        f1125a = new HashMap();
        f1125a.put(C0481m.TASK_WIFI_STATE, new C0485a(C0620c.task_wifi, C0625h.task_wifi_state, C0625h.task_wifi_state_description, TaskWifiStateActivity.class, false));
        f1125a.put(C0481m.TASK_HOTSPOT_STATE, new C0485a(C0620c.task_hotspot, C0625h.task_wifi_hotspot, C0625h.task_wifi_hotspot_description, TaskHotspotStateActivity.class, false));
        f1125a.put(C0481m.TASK_WIFI_NETWORK, new C0485a(C0620c.task_wifi_network, C0625h.task_wifi_network, C0625h.task_wifi_network_description, TaskWifiNetworkActivity.class, false));
        f1125a.put(C0481m.TASK_WIFI_NETWORK_OPEN, new C0485a(C0620c.task_wifi_network, C0625h.task_wifi_network, C0625h.task_wifi_network_description, TaskWifiNetworkActivity.class, false));
        f1125a.put(C0481m.TASK_WIFI_NETWORK_WEP, new C0485a(C0620c.task_wifi_network, C0625h.task_wifi_network, C0625h.task_wifi_network_description, TaskWifiNetworkActivity.class, false));
        f1125a.put(C0481m.TASK_WIFI_NETWORK_WPA, new C0485a(C0620c.task_wifi_network, C0625h.task_wifi_network, C0625h.task_wifi_network_description, TaskWifiNetworkActivity.class, false));
        f1125a.put(C0481m.TASK_WIFI_FORGOT_NETWORK, new C0485a(C0620c.task_forgot_wifi, C0625h.task_forgot_wifi, C0625h.task_forgot_wifi_description, TaskForgotWifiActivity.class, false));
        f1125a.put(C0481m.TASK_NETWORK_WOL, new C0485a(C0620c.task_wol, C0625h.task_wol, C0625h.task_wol_description, ct.class, true));
        f1125a.put(C0481m.TASK_NETWORK_PING, new C0485a(C0620c.task_ping, C0625h.task_ping, C0625h.task_ping_description, ch.class, true));
        f1125a.put(C0481m.TASK_NETWORK_HTTP_GET, new C0485a(C0620c.task_http_get, C0625h.task_http_get, C0625h.task_http_get_description, bx.class, true));
        f1125a.put(C0481m.TASK_OPENVPN, new C0485a(C0620c.task_openvpn, C0625h.task_openvpn, C0625h.task_openvpn_description, cg.class, true));
        f1125a.put(C0481m.TASK_BLUETOOTH_STATE, new C0485a(C0620c.task_bluetooth, C0625h.task_bluetooth_state, C0625h.task_bluetooth_state_description, TaskBluetoothStateActivity.class, false));
        f1125a.put(C0481m.TASK_BLUETOOTH_DEVICE_CONNECT, new C0485a(C0620c.task_bluetooth_device_connect, C0625h.task_bluetooth_device_connect, C0625h.task_bluetooth_device_connect_description, TaskBluetoothDeviceConnectActivity.class, false));
        f1125a.put(C0481m.TASK_BLUETOOTH_DISCOVERABLE, new C0485a(C0620c.task_bluetooth_discoverable, C0625h.task_bluetooth_discoverable, C0625h.task_bluetooth_discoverable_description, TaskBluetoothDiscoverableActivity.class, false));
        f1125a.put(C0481m.TASK_BLUETOOTH_DEVICE_UNPAIR, new C0485a(C0620c.task_remove_bluetooth, C0625h.task_remove_bluetooth, C0625h.task_remove_bluetooth_description, TaskBluetoothDeviceUnpairActivity.class, false));
        f1125a.put(C0481m.TASK_BLUETOOTH_DEVICE_DISCONNECT, new C0485a(C0620c.task_bluetooth_device_disconnect, C0625h.task_bluetooth_device_disconnect, C0625h.task_bluetooth_device_disconnect_description, TaskBluetoothDeviceDisconnectActivity.class, false));
        f1125a.put(C0481m.TASK_MOBILE_DATA_STATE, new C0485a(C0620c.task_mobile_data, C0625h.task_mobile_data_state, C0625h.task_mobile_data_state_description, TaskMobileDataStateActivity.class, false));
        f1125a.put(C0481m.TASK_MOBILE_CALL_LOG, new C0485a(C0620c.task_calllog, C0625h.task_call_log, C0625h.task_call_log_description, C0642t.class, true));
        f1125a.put(C0481m.TASK_END_CALL, new C0485a(C0620c.task_end_call, C0625h.task_end_call, C0625h.task_end_call_description, null, false));
        f1125a.put(C0481m.TASK_ALARM_SET, new C0485a(C0620c.task_alarm, C0625h.task_alarm_set, C0625h.task_alarm_set_description, TaskAlarmSetActivity.class, false));
        f1125a.put(C0481m.TASK_ALARM_IN, new C0485a(C0620c.task_alarm_in, C0625h.task_alarm_in, C0625h.task_alarm_in_description, TaskAlarmInActivity.class, false));
        f1125a.put(C0481m.TASK_TIMER_SET, new C0485a(C0620c.task_timer, C0625h.task_timer_set, C0625h.task_timer_set_description, TaskTimerSetActivity.class, false));
        f1125a.put(C0481m.TASK_SOUND_MODE, new C0485a(C0620c.task_sound, C0625h.task_sound_mode, C0625h.task_sound_mode_description, TaskSoundModeActivity.class, false));
        f1125a.put(C0481m.TASK_SOUND_LEVEL_1, new C0485a(C0620c.task_sound_level_one, C0625h.task_sound_level_1, C0625h.task_sound_level_1_description, TaskSoundLevel1Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_LEVEL_2, new C0485a(C0620c.task_sound_level_two, C0625h.task_sound_level_2, C0625h.task_sound_level_2_description, TaskSoundLevel2Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_LEVEL_3, new C0485a(C0620c.task_sound_level_three, C0625h.task_sound_level_3, C0625h.task_sound_level_3_description, TaskSoundLevel3Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_LEVEL_4, new C0485a(C0620c.task_sound_level_four, C0625h.task_sound_level_4, C0625h.task_sound_level_4_description, TaskSoundLevel4Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_LEVEL_5, new C0485a(C0620c.task_sound_level_five, C0625h.task_sound_level_5, C0625h.task_sound_level_5_description, TaskSoundLevel5Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_LEVEL_6, new C0485a(C0620c.task_sound_level_six, C0625h.task_sound_level_6, C0625h.task_sound_level_6_description, TaskSoundLevel6Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_LEVEL_7, new C0485a(C0620c.task_sound_level_seven, C0625h.task_sound_level_7, C0625h.task_sound_level_7_description, TaskSoundLevel7Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_PLAY_FILE, new C0485a(C0620c.task_play_sound, C0625h.task_play_sound, C0625h.task_play_sound_description, ci.class, true));
        f1125a.put(C0481m.TASK_SOUND_BEEP, new C0485a(C0620c.task_beep, C0625h.task_beep, C0625h.task_beep_description, C0641s.class, true));
        f1125a.put(C0481m.TASK_SOUND_RINGTONE1, new C0485a(C0620c.task_ringtone_one, C0625h.task_ringtone_1, C0625h.task_ringtone_1_description, TaskRingtone1Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_RINGTONE2, new C0485a(C0620c.task_ringtone_two, C0625h.task_ringtone_2, C0625h.task_ringtone_2_description, TaskRingtone2Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_RINGTONE3, new C0485a(C0620c.task_ringtone_three, C0625h.task_ringtone_3, C0625h.task_ringtone_3_description, TaskRingtone3Activity.class, false));
        f1125a.put(C0481m.TASK_SOUND_MEDIA_CONTROL, new C0485a(C0620c.task_mediacontrol, C0625h.task_sound_media_control, C0625h.task_sound_media_control_description, TaskMediaControlActivity.class, true));
        f1125a.put(C0481m.TASK_SOUND_MEDIA_CONTROL_GG_MUSIC, new C0485a(C0620c.task_google_music, C0625h.task_sound_media_control_gg_music, C0625h.task_sound_media_control_gg_music_description, ce.class, true));
        f1125a.put(C0481m.TASK_SCREEN_BRIGHTNESS, new C0485a(C0620c.task_brightness, C0625h.task_brightness, C0625h.task_brightness_description, TaskBrightnessActivity.class, false));
        f1125a.put(C0481m.TASK_SCREEN_BRIGHTNESS_MODE, new C0485a(C0620c.task_brightness_mode, C0625h.task_brightness_mode, C0625h.task_brightness_mode_description, TaskBrightnessModeActivity.class, false));
        f1125a.put(C0481m.TASK_SCREEN_AUTO_ROTATE, new C0485a(C0620c.task_auto_rotate, C0625h.task_auto_rotate, C0625h.task_auto_rotate_description, TaskAutoRotateActivity.class, false));
        f1125a.put(C0481m.TASK_SCREEN_NOTIFICATION_LIGHT, new C0485a(C0620c.task_notification_light, C0625h.task_notification_light, C0625h.task_notification_light_description, TaskNotificationLightStateActivity.class, false));
        f1125a.put(C0481m.TASK_SCREEN_DISPLAY_SLEEP, new C0485a(C0620c.task_display_timeout, C0625h.task_screen_display_sleep, C0625h.task_screen_display_sleep_description, TaskDisplayTimeOutActivity.class, false));
        f1125a.put(C0481m.TASK_SCREEN_CHANGE_WALLPAPER, new C0485a(C0620c.task_change_wallpaper, C0625h.task_change_wallpaper, C0625h.task_change_wallpaper_description, C0643u.class, true));
        f1125a.put(C0481m.TASK_SCREEN_SHOW_IMAGE, new C0485a(C0620c.task_show_image, C0625h.task_show_image, C0625h.task_show_image_description, cn.class, true));
        f1125a.put(C0481m.TASK_CONFIG_CAR_MODE, new C0485a(C0620c.task_car_mode, C0625h.task_car_mode, C0625h.task_car_mode_description, TaskCarModeActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_OPEN_SETTINGS, new C0485a(C0620c.task_open_settings, C0625h.task_open_settings, C0625h.task_open_settings_description, ChooseTaskSettingsActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_SYNC_STATE, new C0485a(C0620c.task_sync, C0625h.task_sync_state, C0625h.task_sync_state_description, cp.class, true));
        f1125a.put(C0481m.TASK_CONFIG_INPUT_METHOD, new C0485a(C0620c.task_input_method, C0625h.task_input_method, C0625h.task_input_method_description, null, false));
        f1125a.put(C0481m.TASK_CONFIG_HAPTIC_FEEDBACK, new C0485a(C0620c.task_haptic_feedback, C0625h.task_haptic_feedback, C0625h.task_haptic_feedback_description, bz.class, true));
        f1125a.put(C0481m.TASK_CONFIG_SAMSUNG, new C0485a(C0620c.task_samsung, C0625h.task_samsung, C0625h.task_samsung_description, ChooseTaskSamsungActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_DRIVING_MODE, new C0485a(C0620c.task_driving_mode, C0625h.task_driving_mode, C0625h.task_driving_mode_description, TaskDrivingModeActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_POWER_SAVING_MODE, new C0485a(C0620c.task_power_saving, C0625h.task_power_saving_mode, C0625h.task_power_saving_mode_description, TaskPowerSavingModeActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_BLOCKING_MODE, new C0485a(C0620c.task_blocking_mode, C0625h.task_blocking_mode, C0625h.task_blocking_mode_description, TaskBlockingModeActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_MULTI_WINDOW, new C0485a(C0620c.task_multiwindow, C0625h.task_multi_window, C0625h.task_multi_window_description, TaskMultiWindowActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_TOOLBOX, new C0485a(C0620c.task_toolbox, C0625h.task_toolbox, C0625h.task_toolbox_description, TaskToolboxActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_AIR_VIEW, new C0485a(C0620c.task_airview, C0625h.task_airview, C0625h.task_airview_description, TaskAirViewActivity.class, false));
        f1125a.put(C0481m.TASK_CONFIG_SVOICE, new C0485a(C0620c.task_svoice, C0625h.task_svoice, C0625h.task_svoice_description, null, false));
        f1125a.put(C0481m.TASK_CONFIG_SPLANNER, new C0485a(C0620c.task_splanner, C0625h.task_splanner, C0625h.task_splanner_description, null, false));
        f1125a.put(C0481m.TASK_CONFIG_TIMEZONE, new C0485a(C0620c.task_timezone, C0625h.task_timezone, C0625h.task_timezone_description, cr.class, true));
        f1125a.put(C0481m.TASK_LAUNCH_APP, new C0485a(C0620c.nfc_type_app, C0625h.task_launch_app, C0625h.task_launch_app_description, TaskApplicationActivity.class, false));
        f1125a.put(C0481m.TASK_LAUNCH_URL, new C0485a(C0620c.nfc_type_uri_custom, C0625h.task_launch_uri, C0625h.task_launch_uri_description, TaskURLURIActivity.class, false));
        f1125a.put(C0481m.TASK_RUN_TOOL, new C0485a(C0620c.task_runtool, C0625h.task_run_tool, C0625h.task_run_tool_description, TaskRunToolActivity.class, false));
        f1125a.put(C0481m.TASK_RUN_TASKER, new C0485a(C0620c.task_tasker, C0625h.task_run_tasker, C0625h.task_run_tasker_description, TaskTaskerActivity.class, false));
        f1125a.put(C0481m.TASK_SPEAK_TTS, new C0485a(C0620c.task_tts, C0625h.task_tts, C0625h.task_tts_description, TaskTTSActivity.class, false));
        f1125a.put(C0481m.TASK_DIALOG, new C0485a(C0620c.task_dialog, C0625h.task_dialog, C0625h.task_dialog_description, bt.class, true));
        f1125a.put(C0481m.TASK_ROLL_DICE, new C0485a(C0620c.task_roll_dice, C0625h.task_roll_dice, C0625h.task_roll_dice_description, cj.class, true));
        f1125a.put(C0481m.TASK_MISC_OK_GOOGLE, new C0485a(C0620c.task_ok_google, C0625h.task_ok_google, C0625h.task_ok_google_description, null, true));
        f1125a.put(C0481m.TASK_MISC_NOTIFICATION_ALERT, new C0485a(C0620c.task_notification_alert, C0625h.task_notification_alert, C0625h.task_notification_alert_description, TaskNotificationAlertActivity.class, true));
        f1125a.put(C0481m.TASK_MISC_DIAL, new C0485a(C0620c.nfc_type_tel, C0625h.task_dial, C0625h.task_dial_description, TaskTelActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_MAIL, new C0485a(C0620c.nfc_type_mail, C0625h.task_mail, C0625h.task_mail_description, TaskMailActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_SMS, new C0485a(C0620c.nfc_type_sms, C0625h.task_sms, C0625h.task_sms_description, TaskSMSActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_GEO, new C0485a(C0620c.nfc_type_geo, C0625h.task_geo, C0625h.task_geo_description, TaskGeocodeActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_ADDRESS, new C0485a(C0620c.nfc_type_address, C0625h.task_address, C0625h.task_address_description, TaskAddressActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_EVENT, new C0485a(C0620c.task_event, C0625h.task_suggest_event, C0625h.task_suggest_event_description, TaskEventActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_INSERT_EVENT, new C0485a(C0620c.task_insert_event, C0625h.task_insert_event, C0625h.task_insert_event_description, cd.class, true));
        f1125a.put(C0481m.TASK_MISC_TIMESTAMPING, new C0485a(C0620c.task_timestamping, C0625h.task_timestamping, C0625h.task_timestamping_description, cq.class, true));
        f1125a.put(C0481m.TASK_MISC_SPEAK_TIME, new C0485a(C0620c.task_speak_time, C0625h.task_speak_time, C0625h.task_speak_time_description, co.class, true));
        f1125a.put(C0481m.TASK_MISC_COPYINTO_CLIPBOARD, new C0485a(C0620c.task_clipboard, C0625h.task_clipboard, C0625h.task_clipboard_description, C0644v.class, true));
        f1125a.put(C0481m.TASK_MISC_SLEEP_TIMER, new C0485a(C0620c.task_sleep_timer, C0625h.task_sleep_timer, C0625h.task_sleep_timer_description, TaskSleepTimerActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_GO_HOME, new C0485a(C0620c.task_go_home, C0625h.task_go_home, C0625h.task_go_home_description, null, false));
        f1125a.put(C0481m.TASK_MISC_VIBRATE, new C0485a(C0620c.task_vibrate, C0625h.task_vibrate, C0625h.task_vibrate_description, cs.class, true));
        f1125a.put(C0481m.TASK_MISC_EXPAND_NOTIFICATIONS, new C0485a(C0620c.task_expand_notifications, C0625h.task_expand_hide_notifications, C0625h.task_expand_hide_notifications_description, TaskExpandNotificationsActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_MORSE_CODE, new C0485a(C0620c.task_morse_code, C0625h.task_morse_code, C0625h.task_morse_code_description, cf.class, true));
        f1125a.put(C0481m.TASK_MISC_UNINSTALL_APP, new C0485a(C0620c.task_uninstall_app, C0625h.task_uninstall_app, C0625h.task_uninstall_app_description, TaskUninstallAppActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_KILL_APP, new C0485a(C0620c.task_kill_app, C0625h.task_kill_app, C0625h.task_kill_app_description, TaskKillAppActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_KILL_APP_ROOT, new C0485a(C0620c.task_kill_app_root, C0625h.task_kill_app, C0625h.task_kill_app_description, TaskKillAppRootActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_PHONE_CALL, new C0485a(C0620c.task_phone_call, C0625h.task_phone_call, C0625h.task_phone_call_description, TaskPhoneCallActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_SEND_SMS, new C0485a(C0620c.task_send_sms, C0625h.task_send_sms, C0625h.task_send_sms_description, TaskSendSMSActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_OPEN_FILE, new C0485a(C0620c.task_open_file, C0625h.task_open_file, C0625h.task_open_file_description, TaskOpenFileActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_SEND_INTENT, new C0485a(C0620c.task_intent, C0625h.task_send_intent, C0625h.task_send_intent_description, cm.class, true));
        f1125a.put(C0481m.TASK_MISC_TWITTER, new C0485a(C0620c.task_twitter, C0625h.task_twitter, C0625h.task_twitter_description, TaskTwitterActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_WRITE_FILE, new C0485a(C0620c.task_write_file, C0625h.task_write_file, C0625h.task_write_file_description, cv.class, true));
        f1125a.put(C0481m.TASK_MISC_WEAR_NOTIFICATION, new C0485a(C0620c.task_notif_wear, C0625h.task_wear_notification, C0625h.task_wear_notification_description, cu.class, true));
        f1125a.put(C0481m.TASK_MISC_SHOW_APP_DETAILS, new C0485a(C0620c.task_app_details, C0625h.task_show_app_details, C0625h.task_show_app_details_description, TaskShowAppDetailsActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_FILE2TTS, new C0485a(C0620c.task_file2tts, C0625h.task_file2tts, C0625h.task_file2tts_description, bv.class, true));
        f1125a.put(C0481m.TASK_MISC_EDITVAR, new C0485a(C0620c.task_editvar, C0625h.task_editvar, C0625h.task_editvar_description, TaskEditVarActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_DELVAR, new C0485a(C0620c.task_delvar, C0625h.task_delvar, C0625h.task_delvar_description, TaskDelVarActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_RUN_PROFILE, new C0485a(C0620c.tasks_profiles_item, C0625h.task_run_profile, C0625h.task_run_profile_description, ck.class, true));
        f1125a.put(C0481m.TASK_COND_END, new C0485a(C0620c.task_end_if, C0625h.task_cond_end, C0625h.task_cond_end_description, null, false));
        f1125a.put(C0481m.TASK_COND_TIME, new C0485a(C0620c.task_if_time, C0625h.task_cond_time, C0625h.task_cond_time_description, TaskCondTimeActivity.class, false));
        f1125a.put(C0481m.TASK_COND_DAY, new C0485a(C0620c.task_if_day, C0625h.task_cond_day, C0625h.task_cond_day_description, TaskCondDayActivity.class, false));
        f1125a.put(C0481m.TASK_COND_WIFI, new C0485a(C0620c.task_if_wifi, C0625h.task_cond_wifi, C0625h.task_cond_wifi_description, bo.class, true));
        f1125a.put(C0481m.TASK_COND_WIFI_NETWORK, new C0485a(C0620c.task_if_wifi_network, C0625h.task_cond_wifi_network, C0625h.task_cond_wifi_network_description, bp.class, true));
        f1125a.put(C0481m.TASK_COND_BLUETOOTH, new C0485a(C0620c.task_if_bluetooth, C0625h.task_cond_bluetooth, C0625h.task_cond_bluetooth_description, ae.class, true));
        f1125a.put(C0481m.TASK_COND_YES_NO_DIALOG, new C0485a(C0620c.task_if_yes_no, C0625h.task_cond_yes_no_dialog, C0625h.task_cond_yes_no_dialog_description, br.class, true));
        f1125a.put(C0481m.TASK_COND_CLIPBOARD, new C0485a(C0620c.task_if_clipboard, C0625h.task_cond_clipboard, C0625h.task_cond_clipboard_description, ai.class, true));
        f1125a.put(C0481m.TASK_COND_HTTP_GET, new C0485a(C0620c.task_if_httpget, C0625h.task_cond_http_get, C0625h.task_cond_http_get_description, ap.class, true));
        f1125a.put(C0481m.TASK_COND_INTERNET_AVAILABILITY, new C0485a(C0620c.task_if_internet_availabilty, C0625h.task_cond_internet_availability, C0625h.task_cond_internet_availability_description, at.class, true));
        f1125a.put(C0481m.TASK_COND_IMEI, new C0485a(C0620c.task_if_imei, C0625h.task_cond_imei, C0625h.task_cond_imei_description, as.class, true));
        f1125a.put(C0481m.TASK_COND_IS_PLUGGED_IN, new C0485a(C0620c.task_if_is_plugged, C0625h.task_cond_is_plugged_in, C0625h.task_cond_is_plugged_in_description, av.class, true));
        f1125a.put(C0481m.TASK_COND_IS_APP_INSTALLED, new C0485a(C0620c.task_if_app, C0625h.task_if_app, C0625h.task_if_app_description, C0646x.class, true));
        f1125a.put(C0481m.TASK_COND_IS_DEVICE_PAIRED, new C0485a(C0620c.task_if_device_paired, C0625h.task_cond_device_paired, C0625h.task_cond_device_paired_description, ak.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SCAN_NUMBER, new C0485a(C0620c.task_if_count, C0625h.task_cond_scan_number, C0625h.task_cond_scan_number_description, TaskCondScanNumberActivity.class, false));
        f1125a.put(C0481m.TASK_COND_IS_APP_RUNNING, new C0485a(C0620c.task_if_running, C0625h.task_cond_running_app, C0625h.task_cond_running_app_description, C0648y.class, true));
        f1125a.put(C0481m.TASK_COND_IS_HOTSPOT_WIFI, new C0485a(C0620c.task_if_hotspot, C0625h.task_cond_hotspot_wifi, C0625h.task_cond_hotspot_wifi_description, ar.class, true));
        f1125a.put(C0481m.TASK_COND_IS_MOBILE_DATA, new C0485a(C0620c.task_if_mobile_data, C0625h.task_cond_mobile_data, C0625h.task_cond_mobile_data_description, ax.class, true));
        f1125a.put(C0481m.TASK_COND_IS_CAR_MODE, new C0485a(C0620c.task_if_car_mode, C0625h.task_cond_car_mode, C0625h.task_cond_car_mode_description, ah.class, true));
        f1125a.put(C0481m.TASK_COND_IS_BRIGHTNESS_MODE, new C0485a(C0620c.task_if_brightness_mode, C0625h.task_cond_brightness_mode, C0625h.task_cond_brightness_mode_description, ag.class, true));
        f1125a.put(C0481m.TASK_COND_IS_AUTO_ROTATE, new C0485a(C0620c.task_if_auto_rotate, C0625h.task_cond_auto_rotate, C0625h.task_cond_auto_rotate_description, C0649z.class, true));
        f1125a.put(C0481m.TASK_COND_IS_NOTIFICATION_LIGHT, new C0485a(C0620c.task_if_notification_light, C0625h.task_cond_notification_light, C0625h.task_cond_notification_light_description, ba.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SYNC, new C0485a(C0620c.task_if_sync, C0625h.task_cond_sync, C0625h.task_cond_sync_description, bk.class, true));
        f1125a.put(C0481m.TASK_COND_IS_HAPTIC_FEEDBACK, new C0485a(C0620c.task_if_haptic_feedback, C0625h.task_cond_haptic_feedback, C0625h.task_cond_haptic_feedback_description, aq.class, true));
        f1125a.put(C0481m.TASK_COND_IS_AIRPLANE, new C0485a(C0620c.task_if_plane, C0625h.task_cond_airplane, C0625h.task_cond_airplane_description, C0645w.class, true));
        f1125a.put(C0481m.TASK_COND_IS_GPS, new C0485a(C0620c.task_if_gps, C0625h.task_cond_gps, C0625h.task_cond_gps_description, ao.class, true));
        f1125a.put(C0481m.TASK_COND_IS_WIRED_HEADSET, new C0485a(C0620c.task_if_wired_headset, C0625h.task_cond_wired_headset, C0625h.task_cond_wired_headset_description, bq.class, true));
        f1125a.put(C0481m.TASK_COND_IS_ZEN_MODE, new C0485a(C0620c.task_if_zen_mode, C0625h.task_cond_zen_mode, C0625h.task_cond_zen_mode_description, bs.class, true));
        f1125a.put(C0481m.TASK_COND_IS_FILE_EXIST, new C0485a(C0620c.task_if_file, C0625h.task_cond_if_file_exist, C0625h.task_cond_if_file_exist_description, an.class, true));
        f1125a.put(C0481m.TASK_COND_IS_VAR_EQUAL, new C0485a(C0620c.task_if_vars, C0625h.task_cond_if_var_equal, C0625h.task_cond_if_var_equal_description, bl.class, true));
        f1125a.put(C0481m.TASK_COND_IS_ROOT, new C0485a(C0620c.task_if_root, C0625h.task_cond_if_root, C0625h.task_cond_if_root_description, bb.class, true));
        f1125a.put(C0481m.TASK_COND_IS_FILE_CONTENT, new C0485a(C0620c.task_if_file_content, C0625h.task_cond_if_file_content, C0625h.task_cond_if_file_content_description, am.class, true));
        f1125a.put(C0481m.TASK_COND_IS_BATTERY_LEVEL, new C0485a(C0620c.task_if_battery_level, C0625h.task_cond_if_battery_level, C0625h.task_cond_if_battery_level_description, aa.class, true));
        f1125a.put(C0481m.TASK_COND_IS_BLUETOOTH_DEVICE_CONNECTED, new C0485a(C0620c.task_if_device_connected, C0625h.task_cond_device_connected, C0625h.task_cond_device_connected_description, aj.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_PROFILE, new C0485a(C0620c.task_if_sound, C0625h.task_cond_if_sound_profile, C0625h.task_cond_if_sound_profile_description, bj.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_LEVEL_1, new C0485a(C0620c.task_if_sound_level_one, C0625h.task_cond_if_sound_level_1, C0625h.task_cond_if_sound_level_1_description, bc.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_LEVEL_2, new C0485a(C0620c.task_if_sound_level_two, C0625h.task_cond_if_sound_level_2, C0625h.task_cond_if_sound_level_2_description, bd.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_LEVEL_3, new C0485a(C0620c.task_if_sound_level_three, C0625h.task_cond_if_sound_level_3, C0625h.task_cond_if_sound_level_3_description, be.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_LEVEL_4, new C0485a(C0620c.task_if_sound_level_four, C0625h.task_cond_if_sound_level_4, C0625h.task_cond_if_sound_level_4_description, bf.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_LEVEL_5, new C0485a(C0620c.task_if_sound_level_five, C0625h.task_cond_if_sound_level_5, C0625h.task_cond_if_sound_level_5_description, bg.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_LEVEL_6, new C0485a(C0620c.task_if_sound_level_six, C0625h.task_cond_if_sound_level_6, C0625h.task_cond_if_sound_level_6_description, bh.class, true));
        f1125a.put(C0481m.TASK_COND_IS_SOUND_LEVEL_7, new C0485a(C0620c.task_if_sound_level_seven, C0625h.task_cond_if_sound_level_7, C0625h.task_cond_if_sound_level_7_description, bi.class, true));
        f1125a.put(C0481m.TASK_COND_IS_DIRECTORY_EXIST, new C0485a(C0620c.task_if_dir_exist, C0625h.task_cond_if_directory_exist, C0625h.task_cond_if_directory_exist_description, al.class, true));
        f1125a.put(C0481m.TASK_COND_IS_VAR_EXIST, new C0485a(C0620c.task_if_var_exist, C0625h.task_cond_if_var_exist, C0625h.task_cond_if_var_exist_description, bm.class, true));
        f1125a.put(C0481m.TASK_NETWORK_HTTP_GET_TO_VAR, new C0485a(C0620c.task_httpget2var, C0625h.task_http_get_to_variable, C0625h.task_http_get_to_variable_description, ca.class, true));
        f1125a.put(C0481m.TASK_COND_IS_BRIGHTNESS_LEVEL, new C0485a(C0620c.task_if_brightness, C0625h.task_cond_if_brightness_level, C0625h.task_cond_if_brightness_level_description, af.class, true));
        f1125a.put(C0481m.TASK_SCREEN_ADAPTIVE_BRIGHTNESS, new C0485a(C0620c.task_adaptive_brightness, C0625h.task_adaptive_brightness, C0625h.task_adaptive_brightness_description, TaskAdaptiveBrightnessActivity.class, false));
        f1125a.put(C0481m.TASK_PLANE_MODE, new C0485a(C0620c.task_plane, C0625h.task_plane_state, C0625h.task_plane_state_description, TaskPlaneStateActivity.class, false));
        f1125a.put(C0481m.TASK_GPS_MODE, new C0485a(C0620c.task_gps, C0625h.task_gps_state, C0625h.task_gps_state_description, TaskGPSStateActivity.class, false));
        f1125a.put(C0481m.TASK_REBOOT_DEVICE, new C0485a(C0620c.task_reboot_device, C0625h.task_reboot_device, C0625h.task_reboot_device_description, null, false));
        f1125a.put(C0481m.TASK_SHUTDOWN_DEVICE, new C0485a(C0620c.task_shutdown_device, C0625h.task_shutdown_device, C0625h.task_shutdown_device_description, null, false));
        f1125a.put(C0481m.TASK_EXE_CMD, new C0485a(C0620c.task_exe_cmd, C0625h.task_exe_cmd, C0625h.task_exe_cmd_description, TaskExeCMDActivity.class, false));
        f1125a.put(C0481m.TASK_DISABLE_APP, new C0485a(C0620c.task_disable_app, C0625h.task_disable_app, C0625h.task_disable_app_description, TaskDisableAppActivity.class, false));
        f1125a.put(C0481m.TASK_ENABLE_APP, new C0485a(C0620c.task_enable_app, C0625h.task_enable_app, C0625h.task_enable_app_description, TaskEnableAppActivity.class, false));
        f1125a.put(C0481m.TASK_ZEN_MODE, new C0485a(C0620c.task_zen_mode, C0625h.task_zen_mode, C0625h.task_zen_mode_description, TaskZenModeActivity.class, false));
        f1125a.put(C0481m.TASK_SCREENSHOT, new C0485a(C0620c.task_screenshot, C0625h.task_screenshot, C0625h.task_screenshot_description, TaskScreenshotActivity.class, false));
        f1125a.put(C0481m.TASK_BUTTON, new C0485a(C0620c.task_button, C0625h.task_button, C0625h.task_button_description, TaskButtonActivity.class, false));
        f1125a.put(C0481m.TASK_KEYBOARD, new C0485a(C0620c.task_keyboard, C0625h.task_keyboard, C0625h.task_keyboard_description, TaskKeyboardActivity.class, false));
        f1125a.put(C0481m.TASK_DPAD, new C0485a(C0620c.task_dpad, C0625h.task_dpad, C0625h.task_dpad_description, TaskDPadActivity.class, false));
        f1125a.put(C0481m.TASK_NUMPAD, new C0485a(C0620c.task_numpad, C0625h.task_numpad, C0625h.task_numpad_description, TaskNumPadActivity.class, false));
        f1125a.put(C0481m.TASK_GAMEPAD, new C0485a(C0620c.task_gamepad, C0625h.task_gamepad, C0625h.task_gamepad_description, TaskGamePadActivity.class, false));
        f1125a.put(C0481m.TASK_LOCKSCREEN, new C0485a(C0620c.task_lockscreen, C0625h.task_lockscreen, C0625h.task_lockscreen_description, null, false));
        f1125a.put(C0481m.TASK_DEV_EXIT, new C0485a(C0620c.task_exit, C0625h.task_exit, C0625h.task_exit_description, null, false));
        f1125a.put(C0481m.TASK_NETWORK_HTTP_POST, new C0485a(C0620c.task_http_post, C0625h.task_http_post, C0625h.task_http_post_description, by.class, true));
        f1125a.put(C0481m.TASK_NETWORK_HTTP_POST_TO_VAR, new C0485a(C0620c.task_httppost2var, C0625h.task_http_post_to_variable, C0625h.task_http_post_to_variable_description, cb.class, true));
        f1125a.put(C0481m.TASK_CONFIG_SECRET_CODE, new C0485a(C0620c.task_secret_code, C0625h.task_secret_code, C0625h.task_secret_code_description, cl.class, true));
        f1125a.put(C0481m.TASK_ALARM_DISMISS_ALL, new C0485a(C0620c.task_dismiss_alarms, C0625h.task_dismiss_alarms, C0625h.task_dismiss_alarms_description, null, false));
        f1125a.put(C0481m.TASK_SPEAKER_PHONE, new C0485a(C0620c.task_speaker, C0625h.task_speaker_state, C0625h.task_speaker_state_description, TaskSpeakerStateActivity.class, false));
        f1125a.put(C0481m.TASK_SCREEN_START_SCREENSAVER, new C0485a(C0620c.task_screensaver, C0625h.task_screensaver, C0625h.task_screensaver_description, null, false));
        f1125a.put(C0481m.TASK_NFC_MODE, new C0485a(C0620c.task_nfc_mode, C0625h.task_nfc_state, C0625h.task_nfc_state_description, TaskNFCStateActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_RUN_SHORTCUT, new C0485a(C0620c.task_run_shortcut, C0625h.task_run_shortcut, C0625h.task_run_shortcut_description, TaskRunShortcutActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_INPUT_FIELD, new C0485a(C0620c.task_input_field, C0625h.task_input_field, C0625h.task_input_field_description, cc.class, true));
        f1125a.put(C0481m.TASK_DOWNLOAD_FILE, new C0485a(C0620c.task_download_file, C0625h.task_download_file, C0625h.task_download_file_description, bu.class, true));
        f1125a.put(C0481m.TASK_COND_IS_MUSIC, new C0485a(C0620c.task_if_music, C0625h.task_cond_if_music, C0625h.task_cond_if_music_description, ay.class, true));
        f1125a.put(C0481m.TASK_COND_IS_BATTERY_SAVER, new C0485a(C0620c.task_if_battery_saver, C0625h.task_cond_if_battery_saver, C0625h.task_cond_if_battery_saver_description, ab.class, true));
        f1125a.put(C0481m.TASK_COND_IS_BATTERY_TEMP, new C0485a(C0620c.task_if_battery_temp, C0625h.task_cond_if_battery_temp, C0625h.task_cond_if_battery_temp_description, ac.class, true));
        f1125a.put(C0481m.TASK_COND_IS_NFC, new C0485a(C0620c.task_if_nfc, C0625h.task_cond_if_nfc, C0625h.task_cond_if_nfc_description, az.class, true));
        f1125a.put(C0481m.TASK_COND_IS_NFC_BEAM, new C0485a(C0620c.task_if_beam, C0625h.task_cond_if_beam, C0625h.task_cond_if_beam_description, ad.class, true));
        f1125a.put(C0481m.TASK_BATTERY_SAVER, new C0485a(C0620c.task_battery_saver, C0625h.task_battery_saver, C0625h.task_battery_saver_description, TaskBatterySaverActivity.class, false));
        f1125a.put(C0481m.TASK_COND_ELSE, new C0485a(C0620c.task_if_else, C0625h.task_cond_else, C0625h.task_cond_else_description, null, false));
        f1125a.put(C0481m.TASK_COND_IS_VAR_RANGE, new C0485a(C0620c.task_if_var_range, C0625h.task_cond_if_var_range, C0625h.task_cond_if_var_range_description, bn.class, true));
        f1125a.put(C0481m.TASK_SOUND_DO_NOT_DISTURB, new C0485a(C0620c.task_donotdisturb, C0625h.task_donotdisturb, C0625h.task_donotdisturb_description, TaskDoNotDisturbActivity.class, false));
        f1125a.put(C0481m.TASK_COND_IS_DAYOFMONTH, new C0485a(C0620c.task_if_dayofmonth, C0625h.task_cond_dayofmonth, C0625h.task_cond_dayofmonth_description, TaskCondDayOfMonthActivity.class, false));
        f1125a.put(C0481m.TASK_COND_IS_MONTH, new C0485a(C0620c.task_if_month, C0625h.task_cond_month, C0625h.task_cond_month_description, TaskCondMonthActivity.class, false));
        f1125a.put(C0481m.TASK_COND_IS_YEAR, new C0485a(C0620c.task_if_year, C0625h.task_cond_year, C0625h.task_cond_year_description, TaskCondYearActivity.class, false));
        f1125a.put(C0481m.TASK_COND_IS_DATE, new C0485a(C0620c.task_if_date, C0625h.task_cond_date, C0625h.task_cond_date_description, TaskCondDateActivity.class, false));
        f1125a.put(C0481m.TASK_NETWORK_HTTP_AUTH, new C0485a(C0620c.task_http_auth, C0625h.task_http_auth, C0625h.task_http_auth_description, bw.class, true));
        f1125a.put(C0481m.TASK_COND_IS_WEBSITE_REACHABLE, new C0485a(C0620c.task_if_website_reachable, C0625h.task_cond_is_website_reachable, C0625h.task_cond_is_website_reachable_description, aw.class, true));
        f1125a.put(C0481m.TASK_COND_IS_HTTP_STATUS_CODE, new C0485a(C0620c.task_if_http_code, C0625h.task_cond_is_http_status_code, C0625h.task_cond_is_http_status_code_description, au.class, true));
        f1125a.put(C0481m.TASK_SOUND_STOP_MEDIA, new C0485a(C0620c.task_stop_sound, C0625h.task_stop_sound, C0625h.task_stop_sound_description, null, true));
        f1125a.put(C0481m.TASK_SOUND_DO_NOT_DISTURB_PLUS, new C0485a(C0620c.task_donotdisturb_plus, C0625h.task_donotdisturb_plus, C0625h.task_donotdisturb_plus_description, TaskDoNotDisturbPlusActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_SEARCH, new C0485a(C0620c.record_search, C0625h.task_search, C0625h.task_search_description, TaskSearchActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_DESTINATION, new C0485a(C0620c.record_destination, C0625h.task_destination, C0625h.task_destination_description, TaskDestinationActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_GEO_SEARCH, new C0485a(C0620c.record_poi, C0625h.task_proximity_search, C0625h.task_proximity_search_description, TaskProximitySearchActivity.class, false));
        f1125a.put(C0481m.TASK_MISC_STREETVIEW, new C0485a(C0620c.record_streetview, C0625h.task_streetview, C0625h.task_streetview_description, TaskStreetViewActivity.class, false));
    }

    public static C0446a m2043a(C0481m c0481m, int i) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        if (f1125a.get(c0481m) == null) {
            return null;
        }
        C0485a c0485a = (C0485a) f1125a.get(c0481m);
        C0446a c0446a = new C0446a();
        c0446a.m1811c(c0481m.cM);
        c0446a.m1802a(c0485a.f1120a);
        c0446a.m1804a(applicationContext.getString(c0485a.f1121b));
        c0446a.m1808b(applicationContext.getString(c0485a.f1122c));
        if (i != 0) {
            c0446a.m1807b(i);
        }
        return c0446a;
    }

    public static String m2044a(C0481m c0481m) {
        return f1125a.get(c0481m) != null ? WDCore.m2174a().getApplicationContext().getString(((C0485a) f1125a.get(c0481m)).f1121b) : null;
    }

    public static ArrayList<C0446a> m2045a() {
        boolean b = C0507a.m2175a().m2194b();
        Object arrayList = new ArrayList();
        for (Entry entry : f1125a.entrySet()) {
            C0481m c0481m = (C0481m) entry.getKey();
            if (!(c0481m == C0481m.TASK_WIFI_NETWORK_OPEN || c0481m == C0481m.TASK_WIFI_NETWORK_WEP || c0481m == C0481m.TASK_WIFI_NETWORK_WPA)) {
                C0485a c0485a = (C0485a) entry.getValue();
                int i = C0620c.item_next;
                if (c0485a.f1123d == null) {
                    i = 0;
                }
                if (c0485a.f1124e && !b) {
                    i = C0620c.item_pro;
                }
                arrayList.add(C0486a.m2043a(c0481m, i));
            }
        }
        Collections.sort(arrayList, new C04841());
        return arrayList;
    }

    public static int m2046b(C0481m c0481m) {
        return f1125a.get(c0481m) != null ? ((C0485a) f1125a.get(c0481m)).f1120a : 0;
    }

    public static C0446a m2047c(C0481m c0481m) {
        return C0486a.m2043a(c0481m, C0620c.item_next);
    }

    public static Class<?> m2048d(C0481m c0481m) {
        if (f1125a.get(c0481m) == null) {
            return null;
        }
        C0485a c0485a = (C0485a) f1125a.get(c0481m);
        return c0485a.f1124e ? C0507a.m2175a().m2194b() ? c0485a.f1123d : ProEditionActivity.class : c0485a.f1123d;
    }
}
