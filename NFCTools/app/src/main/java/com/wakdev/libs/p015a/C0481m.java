package com.wakdev.libs.p015a;

/* renamed from: com.wakdev.libs.a.m */
public enum C0481m {
    TASK_WIFI_STATE(11, "w1/1"),
    TASK_HOTSPOT_STATE(12, "w1/2"),
    TASK_WIFI_NETWORK(13, "w1/30"),
    TASK_WIFI_NETWORK_OPEN(13, "w1/31"),
    TASK_WIFI_NETWORK_WEP(13, "w1/32"),
    TASK_WIFI_NETWORK_WPA(13, "w1/33"),
    TASK_NETWORK_WOL(14, "w1/4"),
    TASK_NETWORK_PING(15, "w1/5"),
    TASK_NETWORK_HTTP_GET(16, "w1/6"),
    TASK_WIFI_FORGOT_NETWORK(17, "w1/7"),
    TASK_OPENVPN(18, "w1/8"),
    TASK_NETWORK_HTTP_GET_TO_VAR(19, "w1/9"),
    TASK_NETWORK_HTTP_POST(110, "w1/10"),
    TASK_NETWORK_HTTP_POST_TO_VAR(111, "w1/11"),
    TASK_DOWNLOAD_FILE(112, "w1/12"),
    TASK_NETWORK_HTTP_AUTH(113, "w1/13"),
    TASK_BLUETOOTH_STATE(21, "w2/1"),
    TASK_BLUETOOTH_DEVICE_CONNECT(22, "w2/2"),
    TASK_BLUETOOTH_DISCOVERABLE(23, "w2/3"),
    TASK_BLUETOOTH_DEVICE_UNPAIR(24, "w2/4"),
    TASK_BLUETOOTH_DEVICE_DISCONNECT(25, "w2/5"),
    TASK_MOBILE_DATA_STATE(31, "w3/1"),
    TASK_MOBILE_CALL_LOG(32, "w3/2"),
    TASK_END_CALL(33, "w3/3"),
    TASK_SPEAKER_PHONE(34, "w3/4"),
    TASK_ALARM_SET(41, "w4/1"),
    TASK_TIMER_SET(42, "w4/2"),
    TASK_ALARM_DISMISS_ALL(43, "w4/3"),
    TASK_ALARM_IN(44, "w4/4"),
    TASK_SOUND_MODE(51, "w5/1"),
    TASK_SOUND_LEVEL_1(52, "w5/2"),
    TASK_SOUND_LEVEL_2(53, "w5/3"),
    TASK_SOUND_LEVEL_3(54, "w5/4"),
    TASK_SOUND_MEDIA_CONTROL(55, "w5/5"),
    TASK_SOUND_LEVEL_4(56, "w5/6"),
    TASK_SOUND_RINGTONE1(57, "w5/7"),
    TASK_SOUND_RINGTONE2(58, "w5/8"),
    TASK_SOUND_RINGTONE3(59, "w5/9"),
    TASK_SOUND_LEVEL_5(510, "w5/10"),
    TASK_SOUND_LEVEL_6(511, "w5/11"),
    TASK_SOUND_LEVEL_7(512, "w5/12"),
    TASK_SOUND_PLAY_FILE(513, "w5/13"),
    TASK_SOUND_BEEP(514, "w5/14"),
    TASK_SOUND_MEDIA_CONTROL_GG_MUSIC(515, "w5/15"),
    TASK_SOUND_DO_NOT_DISTURB(516, "w5/16"),
    TASK_SOUND_STOP_MEDIA(517, "w5/17"),
    TASK_SOUND_DO_NOT_DISTURB_PLUS(518, "w5/18"),
    TASK_SCREEN_BRIGHTNESS(61, "w6/1"),
    TASK_SCREEN_BRIGHTNESS_MODE(62, "w6/2"),
    TASK_SCREEN_AUTO_ROTATE(63, "w6/3"),
    TASK_SCREEN_NOTIFICATION_LIGHT(64, "w6/4"),
    TASK_SCREEN_DISPLAY_SLEEP(65, "w6/5"),
    TASK_SCREEN_CHANGE_WALLPAPER(66, "w6/6"),
    TASK_SCREEN_SHOW_IMAGE(67, "w6/7"),
    TASK_SCREEN_ADAPTIVE_BRIGHTNESS(68, "w6/8"),
    TASK_SCREEN_START_SCREENSAVER(69, "w6/9"),
    TASK_CONFIG_CAR_MODE(71, "w7/1"),
    TASK_CONFIG_OPEN_SETTINGS(72, "w7/2"),
    TASK_CONFIG_SYNC_STATE(73, "w7/3"),
    TASK_CONFIG_INPUT_METHOD(74, "w7/4"),
    TASK_CONFIG_HAPTIC_FEEDBACK(75, "w7/5"),
    TASK_CONFIG_SAMSUNG(76, "w7/6"),
    TASK_CONFIG_DRIVING_MODE(77, "w7/7"),
    TASK_CONFIG_POWER_SAVING_MODE(78, "w7/8"),
    TASK_CONFIG_BLOCKING_MODE(79, "w7/9"),
    TASK_CONFIG_MULTI_WINDOW(710, "w7/10"),
    TASK_CONFIG_TOOLBOX(711, "w7/11"),
    TASK_CONFIG_AIR_VIEW(712, "w7/12"),
    TASK_CONFIG_SVOICE(713, "w7/13"),
    TASK_CONFIG_SPLANNER(714, "w7/14"),
    TASK_CONFIG_SECRET_CODE(715, "w7/15"),
    TASK_CONFIG_TIMEZONE(716, "w7/16"),
    TASK_LAUNCH_APP(81, "w8/1"),
    TASK_LAUNCH_URL(82, "w8/2"),
    TASK_RUN_TOOL(83, "w8/3"),
    TASK_RUN_TASKER(84, "w8/4"),
    TASK_SPEAK_TTS(85, "w8/5"),
    TASK_DIALOG(86, "w8/6"),
    TASK_ROLL_DICE(87, "w8/7"),
    TASK_MISC_OK_GOOGLE(88, "w8/8"),
    TASK_MISC_NOTIFICATION_ALERT(89, "w8/9"),
    TASK_MISC_DIAL(810, "w8/10"),
    TASK_MISC_MAIL(811, "w8/11"),
    TASK_MISC_SMS(812, "w8/12"),
    TASK_MISC_GEO(813, "w8/13"),
    TASK_MISC_ADDRESS(814, "w8/14"),
    TASK_MISC_EVENT(815, "w8/15"),
    TASK_MISC_INSERT_EVENT(816, "w8/16"),
    TASK_MISC_SPEAK_TIME(817, "w8/17"),
    TASK_MISC_COPYINTO_CLIPBOARD(818, "w8/18"),
    TASK_MISC_SLEEP_TIMER(819, "w8/19"),
    TASK_MISC_TIMESTAMPING(820, "w8/20"),
    TASK_MISC_GO_HOME(821, "w8/21"),
    TASK_MISC_VIBRATE(822, "w8/22"),
    TASK_MISC_EXPAND_NOTIFICATIONS(823, "w8/23"),
    TASK_MISC_MORSE_CODE(824, "w8/24"),
    TASK_MISC_UNINSTALL_APP(825, "w8/25"),
    TASK_MISC_KILL_APP(826, "w8/26"),
    TASK_MISC_PHONE_CALL(827, "w8/27"),
    TASK_MISC_SEND_SMS(828, "w8/28"),
    TASK_MISC_SEND_EMAIL(829, "w8/29"),
    TASK_MISC_OPEN_FILE(830, "w8/30"),
    TASK_MISC_SEND_INTENT(831, "w8/31"),
    TASK_MISC_TWITTER(832, "w8/32"),
    TASK_MISC_WRITE_FILE(833, "w8/33"),
    TASK_MISC_WEAR_NOTIFICATION(834, "w8/34"),
    TASK_MISC_SHOW_APP_DETAILS(835, "w8/35"),
    TASK_MISC_FILE2TTS(836, "w8/36"),
    TASK_MISC_EDITVAR(837, "w8/37"),
    TASK_MISC_DELVAR(838, "w8/38"),
    TASK_MISC_RUN_PROFILE(839, "w8/39"),
    TASK_MISC_RUN_SHORTCUT(840, "w8/40"),
    TASK_MISC_INPUT_FIELD(841, "w8/41"),
    TASK_MISC_DESTINATION(842, "w8/42"),
    TASK_MISC_GEO_SEARCH(843, "w8/43"),
    TASK_MISC_STREETVIEW(844, "w8/44"),
    TASK_MISC_SEARCH(845, "w8/45"),
    TASK_COND_END(90, "w9/0"),
    TASK_COND_TIME(91, "w9/1"),
    TASK_COND_DAY(92, "w9/2"),
    TASK_COND_WIFI(93, "w9/3"),
    TASK_COND_WIFI_NETWORK(94, "w9/4"),
    TASK_COND_BLUETOOTH(95, "w9/5"),
    TASK_COND_YES_NO_DIALOG(96, "w9/6"),
    TASK_COND_CLIPBOARD(97, "w9/7"),
    TASK_COND_HTTP_GET(98, "w9/8"),
    TASK_COND_INTERNET_AVAILABILITY(99, "w9/9"),
    TASK_COND_IMEI(910, "w9/10"),
    TASK_COND_IS_PLUGGED_IN(911, "w9/11"),
    TASK_COND_IS_APP_INSTALLED(912, "w9/12"),
    TASK_DEV_EXIT(913, "w9/13"),
    TASK_COND_IS_DEVICE_PAIRED(914, "w9/14"),
    TASK_COND_IS_SCAN_NUMBER(915, "w9/15"),
    TASK_COND_IS_APP_RUNNING(916, "w9/16"),
    TASK_COND_IS_HOTSPOT_WIFI(917, "w9/17"),
    TASK_COND_IS_MOBILE_DATA(918, "w9/18"),
    TASK_COND_IS_CAR_MODE(919, "w9/19"),
    TASK_COND_IS_BRIGHTNESS_MODE(920, "w9/20"),
    TASK_COND_IS_AUTO_ROTATE(921, "w9/21"),
    TASK_COND_IS_NOTIFICATION_LIGHT(922, "w9/22"),
    TASK_COND_IS_SYNC(923, "w9/23"),
    TASK_COND_IS_HAPTIC_FEEDBACK(924, "w9/24"),
    TASK_COND_IS_AIRPLANE(925, "w9/25"),
    TASK_COND_IS_GPS(926, "w9/26"),
    TASK_COND_IS_WIRED_HEADSET(927, "w9/27"),
    TASK_COND_IS_ZEN_MODE(928, "w9/28"),
    TASK_COND_IS_FILE_EXIST(929, "w9/29"),
    TASK_COND_IS_VAR_EQUAL(930, "w9/30"),
    TASK_COND_IS_ROOT(931, "w9/31"),
    TASK_COND_IS_FILE_CONTENT(932, "w9/32"),
    TASK_COND_IS_BATTERY_LEVEL(933, "w9/33"),
    TASK_COND_IS_BLUETOOTH_DEVICE_CONNECTED(934, "w9/34"),
    TASK_COND_IS_BRIGHTNESS_LEVEL(935, "w9/35"),
    TASK_COND_IS_SOUND_LEVEL_1(936, "w9/36"),
    TASK_COND_IS_SOUND_LEVEL_2(937, "w9/37"),
    TASK_COND_IS_SOUND_LEVEL_3(938, "w9/38"),
    TASK_COND_IS_SOUND_LEVEL_4(939, "w9/39"),
    TASK_COND_IS_SOUND_LEVEL_5(940, "w9/40"),
    TASK_COND_IS_SOUND_LEVEL_6(941, "w9/41"),
    TASK_COND_IS_SOUND_LEVEL_7(942, "w9/42"),
    TASK_COND_IS_DIRECTORY_EXIST(943, "w9/43"),
    TASK_COND_IS_VAR_EXIST(944, "w9/44"),
    TASK_COND_IS_SOUND_PROFILE(945, "w9/45"),
    TASK_COND_IS_MUSIC(946, "w9/46"),
    TASK_COND_IS_BATTERY_SAVER(947, "w9/47"),
    TASK_COND_IS_BATTERY_TEMP(948, "w9/48"),
    TASK_COND_IS_NFC(949, "w9/49"),
    TASK_COND_IS_NFC_BEAM(950, "w9/50"),
    TASK_COND_IS_VAR_RANGE(951, "w9/51"),
    TASK_COND_IS_DAYOFMONTH(952, "w9/52"),
    TASK_COND_IS_MONTH(953, "w9/53"),
    TASK_COND_IS_YEAR(954, "w9/54"),
    TASK_COND_IS_DATE(955, "w9/55"),
    TASK_COND_IS_WEBSITE_REACHABLE(956, "w9/56"),
    TASK_COND_IS_HTTP_STATUS_CODE(957, "w9/57"),
    TASK_COND_ELSE(990, "w9/90"),
    TASK_PLANE_MODE(6661, "w666/1"),
    TASK_GPS_MODE(6662, "w666/2"),
    TASK_MISC_KILL_APP_ROOT(6663, "w666/3"),
    TASK_REBOOT_DEVICE(6664, "w666/4"),
    TASK_SHUTDOWN_DEVICE(6665, "w666/5"),
    TASK_EXE_CMD(6666, "w666/6"),
    TASK_DISABLE_APP(6667, "w666/7"),
    TASK_ENABLE_APP(6668, "w666/8"),
    TASK_ZEN_MODE(6669, "w666/9"),
    TASK_SCREENSHOT(66610, "w666/10"),
    TASK_BUTTON(66611, "w666/11"),
    TASK_KEYBOARD(66612, "w666/12"),
    TASK_DPAD(66613, "w666/13"),
    TASK_NUMPAD(66614, "w666/14"),
    TASK_GAMEPAD(66615, "w666/15"),
    TASK_LOCKSCREEN(66616, "w666/16"),
    TASK_NFC_MODE(66617, "w666/17"),
    TASK_BATTERY_SAVER(66618, "w666/18");
    
    public int cM;
    public String cN;
    public final String cO;
    public final String cP;
    public final String cQ;
    public final String cR;
    public final String cS;
    public final String cT;
    public final String cU;
    public final String cV;
    public final String cW;
    public final String cX;
    public final String cY;
    public final String cZ;
    public final String da;
    public final String db;
    public final String dc;
    public final int dd;

    private C0481m(int i, String str) {
        this.cM = -1;
        this.cN = null;
        this.cO = "1";
        this.cP = "0";
        this.cQ = "2";
        this.cR = "1";
        this.cS = "0";
        this.cT = "0";
        this.cU = "1";
        this.cV = "2";
        this.cW = "3";
        this.cX = "4";
        this.cY = "5";
        this.cZ = "0";
        this.da = "1";
        this.db = "2";
        this.dc = "3";
        this.dd = 0;
        this.cM = i;
        this.cN = str;
    }

    public static C0481m m2026a(int i) {
        for (C0481m c0481m : C0481m.values()) {
            if (c0481m.m2030a() == i) {
                return c0481m;
            }
        }
        return null;
    }

    public static C0481m m2027a(String str) {
        for (C0481m c0481m : C0481m.values()) {
            if (c0481m.m2031b().equals(str)) {
                return c0481m;
            }
        }
        return null;
    }

    public static int m2028b(String str) {
        for (C0481m c0481m : C0481m.values()) {
            if (c0481m.m2031b().equals(str)) {
                return c0481m.m2030a();
            }
        }
        return -1;
    }

    public static boolean m2029b(int i) {
        for (C0481m a : C0481m.values()) {
            if (a.m2030a() == i) {
                return true;
            }
        }
        return false;
    }

    public int m2030a() {
        return this.cM;
    }

    public String m2031b() {
        return this.cN;
    }
}
