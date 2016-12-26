package com.wakdev.libs.commons;

import android.content.Context;
import android.net.MailTo;
import android.net.Uri;
import android.view.KeyEvent;
import com.wakdev.libs.core.WDCore;
import com.wakdev.libs.p015a.C0479k;
import com.wakdev.libs.p015a.C0481m;
import com.wakdev.nfctools.C0628m.C0619b;
import com.wakdev.nfctools.C0628m.C0624g;
import com.wakdev.nfctools.C0628m.C0625h;
import com.wakdev.nfctools.C0628m.C0627j;
import com.wakdev.nfctools.au;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.wakdev.libs.commons.n */
public class C0505n {
    private static HashMap<String, HashMap<String, String>> m2111A(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String str = "";
            String str2 = "";
            String[] split = c0479k2.split("/");
            if (!(split[0] == null || split[1] == null)) {
                str = split[0];
                str2 = split[1];
            }
            String str3 = str + " / " + str2;
            hashMap2.put("field1", "1");
            hashMap2.put("field2", str);
            hashMap2.put("field3", str2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemTaskExtra", "1");
            hashMap3.put("itemDescription", str3);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2112B(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String str = "";
            String str2 = "";
            String[] split = c0479k2.split("/");
            if (!(split[0] == null || split[1] == null)) {
                str = split[0];
                str2 = split[1];
            }
            String str3 = str + " / " + str2;
            hashMap2.put("field1", "2");
            hashMap2.put("field2", str);
            hashMap2.put("field3", str2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemTaskExtra", "2");
            hashMap3.put("itemDescription", str3);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2113C(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            String str = split[0] + " - " + split[1];
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2114D(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(((long) Integer.valueOf(c0479k2).intValue()) * 1000);
            String valueOf = String.valueOf(instance.get(11) - 1);
            String valueOf2 = String.valueOf(instance.get(12));
            String valueOf3 = String.valueOf(instance.get(13));
            hashMap2.put("field1", valueOf);
            hashMap2.put("field2", valueOf2);
            hashMap2.put("field3", valueOf3);
            if (valueOf.length() == 1) {
                valueOf = "0" + valueOf;
            }
            if (valueOf2.length() == 1) {
                valueOf2 = "0" + valueOf2;
            }
            if (valueOf3.length() == 1) {
                valueOf3 = "0" + valueOf3;
            }
            valueOf3 = valueOf + ":" + valueOf2 + ":" + valueOf3;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", valueOf3);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2115E(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String str = "";
            String str2 = "";
            String str3 = "";
            if (c0479k2.contains(":")) {
                String[] split;
                if (c0479k2.contains(";")) {
                    str3 = c0479k2.substring(c0479k2.indexOf(";") + 1);
                    split = c0479k2.substring(0, c0479k2.indexOf(";")).split(":");
                    if (!(split[0] == null || split[1] == null)) {
                        str = split[0];
                        str2 = split[1];
                    }
                } else {
                    split = c0479k2.split(":");
                    if (!(split[0] == null || split[1] == null)) {
                        str = split[0];
                        str2 = split[1];
                    }
                }
                hashMap2.put("field1", str3);
                hashMap2.put("field2", str);
                hashMap2.put("field3", str2);
                if (str.length() == 1) {
                    str = "0" + str;
                }
                if (str2.length() == 1) {
                    str2 = "0" + str2;
                }
                Object obj = str + ":" + str2;
                if (!str3.isEmpty()) {
                    obj = str3 + " - " + obj;
                }
                hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
                hashMap3.put("itemTask", c0479k2);
                hashMap3.put("itemDescription", obj);
                hashMap3.put("itemHash", null);
                hashMap3.put("itemUpdate", String.valueOf(false));
                hashMap.put("tasks.profile.fields", hashMap2);
                hashMap.put("tasks.profile.config", hashMap3);
                return hashMap;
            }
            throw new Exception();
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2116F(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String str = "";
            String str2 = "";
            if (c0479k2.contains(";")) {
                String[] split = c0479k2.split(";");
                if (!(split[0] == null || split[1] == null)) {
                    str = split[0];
                    str2 = split[1];
                }
                int parseInt = Integer.parseInt(str2);
                String str3 = str + applicationContext.getString(C0625h.task_beep_hz) + " / " + applicationContext.getResources().getQuantityString(C0624g.task_beep_seconds, parseInt, new Object[]{Integer.valueOf(parseInt)});
                hashMap2.put("field1", str);
                hashMap2.put("field2", str2);
                hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
                hashMap3.put("itemTask", c0479k2);
                hashMap3.put("itemDescription", str3);
                hashMap3.put("itemHash", null);
                hashMap3.put("itemUpdate", String.valueOf(false));
                hashMap.put("tasks.profile.fields", hashMap2);
                hashMap.put("tasks.profile.config", hashMap3);
                return hashMap;
            }
            throw new Exception();
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2117G(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object obj;
            Object obj2;
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.display_sleep_arrays);
            switch (Integer.valueOf(c0479k2).intValue()) {
                case 15000:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
                case 30000:
                    obj = stringArray[1];
                    obj2 = "1";
                    break;
                case 60000:
                    obj = stringArray[2];
                    obj2 = "2";
                    break;
                case 120000:
                    obj = stringArray[3];
                    obj2 = "3";
                    break;
                case 300000:
                    obj = stringArray[4];
                    obj2 = "4";
                    break;
                case 600000:
                    obj = stringArray[5];
                    obj2 = "5";
                    break;
                case 1800000:
                    obj = stringArray[6];
                    obj2 = "6";
                    break;
                default:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
            }
            hashMap2.put("field1", obj2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2118H(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object obj;
            Object obj2;
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_bluetooth_discoverable_arrays);
            switch (Integer.valueOf(c0479k2).intValue()) {
                case C0627j.Theme_actionModePasteDrawable /*30*/:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
                case C0627j.Theme_textColorSearchUrl /*60*/:
                    obj = stringArray[1];
                    obj2 = "1";
                    break;
                case 120:
                    obj = stringArray[2];
                    obj2 = "2";
                    break;
                case 300:
                    obj = stringArray[3];
                    obj2 = "3";
                    break;
                case 600:
                    obj = stringArray[4];
                    obj2 = "4";
                    break;
                case 1800:
                    obj = stringArray[5];
                    obj2 = "5";
                    break;
                case 3600:
                    obj = stringArray[6];
                    obj2 = "6";
                    break;
                default:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
            }
            hashMap2.put("field1", obj2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2119I(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object obj;
            Object obj2;
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_sleep_timer_arrays);
            switch (Integer.valueOf(c0479k2).intValue()) {
                case C0627j.View_paddingStart /*1*/:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
                case C0627j.View_paddingEnd /*2*/:
                    obj = stringArray[1];
                    obj2 = "1";
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    obj = stringArray[2];
                    obj2 = "2";
                    break;
                case C0627j.Toolbar_contentInsetStart /*4*/:
                    obj = stringArray[3];
                    obj2 = "3";
                    break;
                case C0627j.Toolbar_contentInsetEnd /*5*/:
                    obj = stringArray[4];
                    obj2 = "4";
                    break;
                case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                    obj = stringArray[5];
                    obj2 = "5";
                    break;
                case C0627j.Theme_actionModePasteDrawable /*30*/:
                    obj = stringArray[6];
                    obj2 = "6";
                    break;
                case C0627j.Theme_textColorSearchUrl /*60*/:
                    obj = stringArray[7];
                    obj2 = "7";
                    break;
                case 120:
                    obj = stringArray[8];
                    obj2 = "8";
                    break;
                case 300:
                    obj = stringArray[9];
                    obj2 = "9";
                    break;
                default:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
            }
            hashMap2.put("field1", obj2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2120J(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object obj;
            Object obj2;
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_vibrate_during_arrays);
            switch (Integer.valueOf(c0479k2).intValue()) {
                case C0627j.View_paddingStart /*1*/:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
                case C0627j.View_paddingEnd /*2*/:
                    obj = stringArray[1];
                    obj2 = "1";
                    break;
                case C0627j.Toolbar_subtitle /*3*/:
                    obj = stringArray[2];
                    obj2 = "2";
                    break;
                case C0627j.Toolbar_contentInsetStart /*4*/:
                    obj = stringArray[3];
                    obj2 = "3";
                    break;
                case C0627j.Toolbar_contentInsetEnd /*5*/:
                    obj = stringArray[4];
                    obj2 = "4";
                    break;
                case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                    obj = stringArray[5];
                    obj2 = "5";
                    break;
                default:
                    obj = stringArray[0];
                    obj2 = "0";
                    break;
            }
            hashMap2.put("field1", obj2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2121K(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", C0505n.m2147f(c0479k2));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2122L(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object substring;
            Object substring2;
            Object obj;
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            if (c0479k2.startsWith("[") && c0479k2.contains("]")) {
                substring = c0479k2.substring(1, c0479k2.indexOf("]"));
                substring2 = c0479k2.substring(c0479k2.indexOf("]") + 1);
                obj = substring + "\n" + substring2;
            } else {
                String str = c0479k2;
                String str2 = "";
                String str3 = c0479k2;
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2123M(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_http_auth_login) + " " + split[0] + "\n" + applicationContext.getString(C0625h.task_http_auth_password) + " " + split[1];
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2124N(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            if (!c0479k2.startsWith("[") || !c0479k2.contains("]")) {
                return null;
            }
            String substring = c0479k2.substring(1, c0479k2.indexOf("]"));
            String substring2 = c0479k2.substring(c0479k2.indexOf("]") + 1);
            substring = substring.replace("{VAR_", "").replace("}", "");
            String str = substring + "\n" + substring2;
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            hashMap = null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2125O(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            if (!c0479k2.startsWith("[") || !c0479k2.contains("]")) {
                return null;
            }
            String substring = c0479k2.substring(1, c0479k2.indexOf("]"));
            String substring2 = c0479k2.substring(c0479k2.indexOf("]") + 1);
            substring = substring.replace("{VAR_", "").replace("}", "");
            String str = (applicationContext.getString(C0625h.task_http_get_to_variable_request) + " " + substring2 + "\n") + applicationContext.getString(C0625h.task_http_get_to_variable_varname) + " " + substring;
            hashMap2.put("field1", substring2);
            hashMap2.put("field2", substring);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            hashMap = null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2126P(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object obj;
            Object obj2;
            Object obj3;
            Object obj4;
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] split = c0479k2.split("\\|");
            if (split.length == 3) {
                obj = split[0];
                obj2 = split[1];
                obj3 = split[2];
                obj4 = (applicationContext.getString(C0625h.task_http_post_request) + " " + obj + "\n") + applicationContext.getString(C0625h.task_http_post_to_variable_varname) + " {VAR_" + obj2 + "}";
                if (!obj3.isEmpty() && obj3.contains(";")) {
                    String str = obj4 + "\n" + applicationContext.getString(C0625h.task_http_post_post_parameters);
                    obj4 = str;
                    for (String str2 : obj3.split(";")) {
                        obj4 = obj4 + "\n";
                        if (!str2.isEmpty() && str2.contains("=")) {
                            String[] split2 = str2.split("=");
                            if (split2.length == 2) {
                                obj4 = (obj4 + applicationContext.getString(C0625h.param_name) + " " + split2[0]) + " / " + applicationContext.getString(C0625h.param_value) + " " + split2[1];
                            }
                        }
                    }
                }
            } else if (split.length != 2) {
                return null;
            } else {
                obj = split[0];
                obj2 = split[1];
                obj3 = "";
                obj4 = (applicationContext.getString(C0625h.task_http_post_request) + " " + obj + "\n") + applicationContext.getString(C0625h.task_http_post_to_variable_varname) + " {VAR_" + obj2 + "}";
            }
            hashMap2.put("field1", obj);
            hashMap2.put("field2", obj2);
            hashMap2.put("field3", obj3);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj4);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2127Q(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object obj;
            Object obj2;
            Object obj3;
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] split = c0479k2.split("\\|");
            if (split.length == 2) {
                obj = split[0];
                obj2 = split[1];
                obj3 = applicationContext.getString(C0625h.task_http_post_request) + " " + obj;
                if (!obj2.isEmpty() && obj2.contains(";")) {
                    String str = obj3 + "\n" + applicationContext.getString(C0625h.task_http_post_post_parameters);
                    obj3 = str;
                    for (String str2 : obj2.split(";")) {
                        obj3 = obj3 + "\n";
                        if (!str2.isEmpty() && str2.contains("=")) {
                            String[] split2 = str2.split("=");
                            if (split2.length == 2) {
                                obj3 = (obj3 + applicationContext.getString(C0625h.param_name) + " " + split2[0]) + " / " + applicationContext.getString(C0625h.param_value) + " " + split2[1];
                            }
                        }
                    }
                }
            } else {
                obj2 = "";
                obj3 = applicationContext.getString(C0625h.task_http_post_request) + " " + c0479k2;
                obj = c0479k2;
            }
            hashMap2.put("field1", obj);
            hashMap2.put("field2", obj2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj3);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2128R(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            if (c0479k2.substring(1, 2).equals("d")) {
                String[] split = c0479k2.split("d");
                int intValue = Integer.valueOf(split[0]).intValue();
                int intValue2 = Integer.valueOf(split[1]).intValue();
                if (intValue > 6 || intValue < 1) {
                    throw new Exception();
                } else if (intValue2 == 2 || intValue2 == 4 || intValue2 == 6 || intValue2 == 8 || intValue2 == 10 || intValue2 == 12 || intValue2 == 20 || intValue2 == 30) {
                    String str = split[0] + "d" + split[1];
                    String valueOf = String.valueOf(intValue - 1);
                    Object obj = "2";
                    switch (intValue2) {
                        case C0627j.View_paddingEnd /*2*/:
                            obj = "0";
                            break;
                        case C0627j.Toolbar_contentInsetStart /*4*/:
                            obj = "1";
                            break;
                        case C0627j.Toolbar_contentInsetLeft /*6*/:
                            obj = "2";
                            break;
                        case C0627j.Toolbar_popupTheme /*8*/:
                            obj = "3";
                            break;
                        case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                            obj = "4";
                            break;
                        case C0627j.Toolbar_titleMarginStart /*12*/:
                            obj = "5";
                            break;
                        case C0627j.Toolbar_navigationIcon /*20*/:
                            obj = "6";
                            break;
                        case C0627j.Theme_actionModePasteDrawable /*30*/:
                            obj = "7";
                            break;
                    }
                    hashMap2.put("field1", valueOf);
                    hashMap2.put("field2", obj);
                    hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
                    hashMap3.put("itemTask", c0479k2);
                    hashMap3.put("itemDescription", str);
                    hashMap3.put("itemHash", null);
                    hashMap3.put("itemUpdate", String.valueOf(false));
                    hashMap.put("tasks.profile.fields", hashMap2);
                    hashMap.put("tasks.profile.config", hashMap3);
                    return hashMap;
                } else {
                    throw new Exception();
                }
            }
            throw new Exception();
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2129S(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.settings_title_arrays);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", stringArray[Integer.valueOf(c0479k2).intValue()]);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2130T(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Uri parse = Uri.parse(c0479k.toString());
            String e = c0479k.m2011e();
            if (parse == null) {
                throw new Exception();
            }
            Object obj;
            String uri = parse.toString();
            String query = parse.getQuery();
            String replace = uri.replace(parse.getScheme() + ":", "");
            String str = "";
            if (query != null) {
                replace = replace.replace("?" + query, "");
                str = parse.getQuery().replace("body=", "");
                hashMap2.put("field2", str);
            }
            if (str.isEmpty()) {
                str = replace;
            } else {
                obj = replace + "\n" + str;
            }
            hashMap2.put("field1", replace);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", uri);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2131U(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            Uri parse = Uri.parse(c0479k.toString());
            String e = c0479k.m2011e();
            if (parse == null) {
                throw new Exception();
            }
            Object valueOf;
            String str;
            String uri = parse.toString();
            int indexOf = uri.indexOf("?body=");
            if (uri.indexOf("dlr=1") != -1) {
                uri = uri.replace("?dlr=1", "").replace("&dlr=1", "");
                valueOf = String.valueOf(true);
                str = applicationContext.getString(C0625h.task_send_sms_delivery) + " : " + applicationContext.getString(C0625h.yes);
            } else {
                valueOf = String.valueOf(false);
                str = applicationContext.getString(C0625h.task_send_sms_delivery) + " : " + applicationContext.getString(C0625h.no);
            }
            if (indexOf != -1) {
                String substring = uri.substring(4, indexOf);
                String substring2 = uri.substring(indexOf + 6);
                str = substring + "\n" + substring2 + "\n" + str;
                hashMap2.put("field1", substring);
                hashMap2.put("field2", substring2);
                hashMap2.put("field3", valueOf);
                hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
                hashMap3.put("itemTask", uri);
                hashMap3.put("itemDescription", str);
                hashMap3.put("itemHash", null);
                hashMap3.put("itemUpdate", String.valueOf(false));
                hashMap.put("tasks.profile.fields", hashMap2);
                hashMap.put("tasks.profile.config", hashMap3);
                return hashMap;
            }
            throw new Exception();
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2132V(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            if (MailTo.isMailTo(c0479k2)) {
                MailTo parse = MailTo.parse(c0479k2);
                String to = parse.getTo();
                String subject = parse.getSubject();
                String body = parse.getBody();
                Object obj = "";
                if (to != null) {
                    obj = obj + to;
                    hashMap2.put("field1", to);
                }
                if (subject != null) {
                    obj = obj + "\n" + subject;
                    hashMap2.put("field2", subject);
                }
                if (body != null) {
                    obj = obj + "\n" + body;
                    hashMap2.put("field3", body);
                }
                hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
                hashMap3.put("itemTask", c0479k2);
                hashMap3.put("itemDescription", obj);
                hashMap3.put("itemHash", null);
                hashMap3.put("itemUpdate", String.valueOf(false));
                hashMap.put("tasks.profile.fields", hashMap2);
                hashMap.put("tasks.profile.config", hashMap3);
                return hashMap;
            }
            throw new Exception();
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2133W(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Uri parse = Uri.parse(c0479k.toString());
            String e = c0479k.m2011e();
            if (parse == null) {
                throw new Exception();
            }
            String uri = parse.toString();
            String[] split = uri.replace(parse.getScheme() + ":", "").split(",");
            String str = "";
            String str2 = "";
            if (split[0] != null) {
                str = split[0];
            }
            if (split[1] != null) {
                str2 = split[1];
            }
            String str3 = str + "," + str2;
            hashMap2.put("field1", str);
            hashMap2.put("field2", str2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", uri);
            hashMap3.put("itemDescription", str3);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2134X(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Object c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            if (c0479k2.contains("geo:0,0?q=")) {
                Uri parse = Uri.parse(c0479k.toString());
                if (parse == null) {
                    throw new Exception();
                }
                c0479k2 = URLDecoder.decode(parse.getQuery().replace("q=", ""), "utf-8");
            }
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", c0479k2);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2135Y(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String str = "";
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            if (c0479k2.startsWith("[") && c0479k2.endsWith("]")) {
                String str2 = "";
                String str3 = "";
                int indexOf = c0479k2.indexOf("][", 1);
                if (indexOf == -1) {
                    throw new Exception();
                }
                int indexOf2 = c0479k2.indexOf("][", indexOf + 1);
                if (indexOf2 == -1) {
                    throw new Exception();
                }
                String str4;
                Object obj;
                String string;
                String substring = c0479k2.substring(1, indexOf);
                String substring2 = c0479k2.substring(indexOf + 2, indexOf2);
                String substring3 = c0479k2.substring(indexOf2 + 2, c0479k2.length() - 1);
                String[] split = substring.split("\\|");
                String[] split2 = substring2.split("\\|");
                String str5 = split[0];
                switch (split.length) {
                    case C0627j.View_paddingEnd /*2*/:
                        str3 = split[1];
                        str4 = str2;
                        break;
                    case C0627j.Toolbar_subtitle /*3*/:
                        str2 = split[1];
                        str3 = split[2];
                        str4 = str2;
                        break;
                    default:
                        str4 = str2;
                        break;
                }
                substring2 = str3.equals("#") ? "" : str3;
                Calendar a = C0487b.m2052a(split2[0], "yyyy-MM-dd HH:mm");
                Calendar a2 = C0487b.m2052a(split2[1], "yyyy-MM-dd HH:mm");
                String str6 = String.valueOf(a.get(1)) + "-" + String.valueOf(a.get(2) + 1) + "-" + String.valueOf(a.get(5));
                String str7 = String.valueOf(a2.get(1)) + "-" + String.valueOf(a2.get(2) + 1) + "-" + String.valueOf(a2.get(5));
                String str8 = String.valueOf(a.get(11)) + ":" + String.valueOf(a.get(12));
                String str9 = String.valueOf(a2.get(11)) + ":" + String.valueOf(a2.get(12));
                str3 = applicationContext.getString(C0625h.no);
                if (substring3.equals("1")) {
                    obj = 1;
                    string = applicationContext.getString(C0625h.yes);
                } else {
                    obj = null;
                    string = str3;
                }
                hashMap2.put("field1", str5);
                hashMap2.put("field2", str4);
                hashMap2.put("field3", substring2);
                hashMap2.put("field4", str6);
                hashMap2.put("field5", str7);
                hashMap2.put("field6", str8);
                hashMap2.put("field7", str9);
                hashMap2.put("field8", substring3);
                str3 = (str + C0487b.m2051a(a)) + " - " + C0487b.m2051a(a2) + "\n";
                if (obj == null) {
                    str3 = str3 + C0487b.m2053b(a) + " - " + C0487b.m2053b(a2) + "\n";
                }
                str3 = str3 + str5 + "\n";
                if (!str4.isEmpty()) {
                    str3 = str3 + str4 + "\n";
                }
                if (!substring2.isEmpty()) {
                    str3 = str3 + substring2 + "\n";
                }
                str3 = str3 + applicationContext.getString(C0625h.task_event_all_day) + string + "\n";
                hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
                hashMap3.put("itemTask", c0479k2);
                hashMap3.put("itemDescription", str3);
                hashMap3.put("itemHash", null);
                hashMap3.put("itemUpdate", String.valueOf(false));
                hashMap.put("tasks.profile.fields", hashMap2);
                hashMap.put("tasks.profile.config", hashMap3);
                return hashMap;
            }
            throw new Exception();
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2136Z(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Object obj = applicationContext.getResources().getStringArray(C0619b.task_speak_time_lang_arrays)[Integer.valueOf(c0479k2).intValue()];
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static String m2137a(String str) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        Object obj = -1;
        switch (str.hashCode()) {
            case C0627j.Theme_dividerVertical /*48*/:
                if (str.equals("0")) {
                    obj = null;
                    break;
                }
                break;
            case C0627j.Theme_dividerHorizontal /*49*/:
                if (str.equals("1")) {
                    obj = 1;
                    break;
                }
                break;
            case C0627j.Theme_activityChooserViewStyle /*50*/:
                if (str.equals("2")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case C0627j.View_android_focusable /*0*/:
                return applicationContext.getString(C0625h.state_disable);
            case C0627j.View_paddingStart /*1*/:
                return applicationContext.getString(C0625h.state_enable);
            case C0627j.View_paddingEnd /*2*/:
                return applicationContext.getString(C0625h.state_toggle);
            default:
                return "";
        }
    }

    public static JSONObject m2138a(C0479k c0479k) {
        if (c0479k == null) {
            return null;
        }
        HashMap c;
        switch (C05041.f1143a[C0481m.m2027a(c0479k.m2011e()).ordinal()]) {
            case C0627j.View_paddingStart /*1*/:
            case C0627j.View_paddingEnd /*2*/:
            case C0627j.Toolbar_subtitle /*3*/:
            case C0627j.Toolbar_contentInsetStart /*4*/:
            case C0627j.Toolbar_contentInsetEnd /*5*/:
            case C0627j.Toolbar_contentInsetLeft /*6*/:
            case C0627j.Toolbar_contentInsetRight /*7*/:
            case C0627j.Toolbar_popupTheme /*8*/:
            case C0627j.Toolbar_titleTextAppearance /*9*/:
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
            case C0627j.Toolbar_titleMargins /*11*/:
            case C0627j.Toolbar_titleMarginStart /*12*/:
            case C0627j.Toolbar_titleMarginEnd /*13*/:
            case C0627j.Toolbar_titleMarginTop /*14*/:
            case C0627j.Toolbar_titleMarginBottom /*15*/:
            case C0627j.Toolbar_maxButtonHeight /*16*/:
            case C0627j.Toolbar_theme /*17*/:
            case C0627j.Toolbar_collapseIcon /*18*/:
            case C0627j.Toolbar_collapseContentDescription /*19*/:
            case C0627j.Toolbar_navigationIcon /*20*/:
            case C0627j.Toolbar_navigationContentDescription /*21*/:
                c = C0505n.m2142c(c0479k);
                break;
            case C0627j.Theme_actionMenuTextColor /*22*/:
            case C0627j.Theme_actionModeStyle /*23*/:
            case C0627j.Theme_actionModeCloseButtonStyle /*24*/:
            case C0627j.Theme_actionModeBackground /*25*/:
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
            case C0627j.Theme_spinnerDropDownItemStyle /*41*/:
            case C0627j.Theme_homeAsUpIndicator /*42*/:
            case C0627j.Theme_actionButtonStyle /*43*/:
            case C0627j.Theme_buttonBarStyle /*44*/:
            case C0627j.Theme_buttonBarButtonStyle /*45*/:
            case C0627j.Theme_selectableItemBackground /*46*/:
            case C0627j.Theme_selectableItemBackgroundBorderless /*47*/:
            case C0627j.Theme_dividerVertical /*48*/:
            case C0627j.Theme_dividerHorizontal /*49*/:
            case C0627j.Theme_activityChooserViewStyle /*50*/:
            case C0627j.Theme_toolbarStyle /*51*/:
            case C0627j.Theme_toolbarNavigationButtonStyle /*52*/:
            case C0627j.Theme_popupMenuStyle /*53*/:
            case C0627j.Theme_popupWindowStyle /*54*/:
            case C0627j.Theme_editTextColor /*55*/:
            case C0627j.Theme_editTextBackground /*56*/:
            case C0627j.Theme_switchStyle /*57*/:
            case C0627j.Theme_textAppearanceSearchResultTitle /*58*/:
            case C0627j.Theme_textAppearanceSearchResultSubtitle /*59*/:
            case C0627j.Theme_textColorSearchUrl /*60*/:
            case C0627j.Theme_searchViewStyle /*61*/:
            case C0627j.Theme_listPreferredItemHeight /*62*/:
            case C0627j.Theme_listPreferredItemHeightSmall /*63*/:
            case C0627j.Theme_listPreferredItemHeightLarge /*64*/:
                c = C0505n.m2165w(c0479k);
                break;
            case C0627j.Theme_listPreferredItemPaddingLeft /*65*/:
                c = C0505n.m2144d(c0479k);
                break;
            case C0627j.Theme_listPreferredItemPaddingRight /*66*/:
                c = C0505n.m2146e(c0479k);
                break;
            case C0627j.Theme_dropDownListViewStyle /*67*/:
                c = C0505n.m2148f(c0479k);
                break;
            case C0627j.Theme_listPopupWindowStyle /*68*/:
                c = C0505n.m2149g(c0479k);
                break;
            case C0627j.Theme_textAppearanceListItem /*69*/:
                c = C0505n.m2150h(c0479k);
                break;
            case C0627j.Theme_textAppearanceListItemSmall /*70*/:
                c = C0505n.m2152j(c0479k);
                break;
            case C0627j.Theme_panelBackground /*71*/:
                c = C0505n.m2159q(c0479k);
                break;
            case C0627j.Theme_panelMenuListWidth /*72*/:
                c = C0505n.m2158p(c0479k);
                break;
            case C0627j.Theme_panelMenuListTheme /*73*/:
                c = C0505n.m2160r(c0479k);
                break;
            case C0627j.Theme_listChoiceBackgroundIndicator /*74*/:
                c = C0505n.m2161s(c0479k);
                break;
            case C0627j.Theme_colorPrimary /*75*/:
                c = C0505n.m2162t(c0479k);
                break;
            case C0627j.Theme_colorPrimaryDark /*76*/:
            case C0627j.Theme_colorAccent /*77*/:
                c = C0505n.m2163u(c0479k);
                break;
            case C0627j.Theme_colorControlNormal /*78*/:
                c = C0505n.m2168z(c0479k);
                break;
            case C0627j.Theme_colorControlActivated /*79*/:
                c = C0505n.m2111A(c0479k);
                break;
            case C0627j.Theme_colorControlHighlight /*80*/:
                c = C0505n.m2112B(c0479k);
                break;
            case C0627j.Theme_colorButtonNormal /*81*/:
                c = C0505n.m2113C(c0479k);
                break;
            case C0627j.Theme_colorSwitchThumbNormal /*82*/:
                c = C0505n.m2164v(c0479k);
                break;
            case 83:
                c = C0505n.m2114D(c0479k);
                break;
            case 84:
                c = C0505n.m2117G(c0479k);
                break;
            case 85:
                c = C0505n.m2118H(c0479k);
                break;
            case 86:
                c = C0505n.m2119I(c0479k);
                break;
            case 87:
                c = C0505n.m2121K(c0479k);
                break;
            case 88:
            case 89:
                c = C0505n.m2115E(c0479k);
                break;
            case 90:
            case 91:
            case 92:
                c = C0505n.m2122L(c0479k);
                break;
            case 93:
                c = C0505n.m2123M(c0479k);
                break;
            case 94:
                c = C0505n.m2128R(c0479k);
                break;
            case 95:
                c = C0505n.m2129S(c0479k);
                break;
            case 96:
                c = C0505n.m2130T(c0479k);
                break;
            case 97:
                c = C0505n.m2131U(c0479k);
                break;
            case 98:
                c = C0505n.m2132V(c0479k);
                break;
            case 99:
            case 100:
                c = C0505n.m2133W(c0479k);
                break;
            case 101:
                c = C0505n.m2134X(c0479k);
                break;
            case 102:
            case 103:
                c = C0505n.m2135Y(c0479k);
                break;
            case 104:
                c = C0505n.m2136Z(c0479k);
                break;
            case 105:
                c = C0505n.m2120J(c0479k);
                break;
            case 106:
                c = C0505n.m2140b(c0479k);
                break;
            case 107:
                c = C0505n.aa(c0479k);
                break;
            case 108:
                c = C0505n.ad(c0479k);
                break;
            case 109:
                c = C0505n.ab(c0479k);
                break;
            case 110:
                c = C0505n.ae(c0479k);
                break;
            case 111:
                c = C0505n.af(c0479k);
                break;
            case 112:
                c = C0505n.m2116F(c0479k);
                break;
            case 113:
                c = C0505n.ar(c0479k);
                break;
            case 114:
                c = C0505n.aR(c0479k);
                break;
            case 115:
                c = C0505n.aS(c0479k);
                break;
            case 116:
                c = C0505n.ag(c0479k);
                break;
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
                c = C0505n.am(c0479k);
                break;
            case 131:
                c = C0505n.an(c0479k);
                break;
            case 132:
                c = C0505n.ao(c0479k);
                break;
            case 133:
                c = C0505n.ap(c0479k);
                break;
            case 134:
                c = C0505n.aq(c0479k);
                break;
            case 135:
                c = C0505n.ai(c0479k);
                break;
            case 136:
                c = C0505n.aj(c0479k);
                break;
            case 137:
                c = C0505n.ah(c0479k);
                break;
            case 138:
                c = C0505n.ak(c0479k);
                break;
            case 139:
                c = C0505n.al(c0479k);
                break;
            case 140:
                c = C0505n.at(c0479k);
                break;
            case 141:
                c = C0505n.aC(c0479k);
                break;
            case 142:
                c = C0505n.aH(c0479k);
                break;
            case 143:
                c = C0505n.aE(c0479k);
                break;
            case 144:
                c = C0505n.aF(c0479k);
                break;
            case 145:
                c = C0505n.aI(c0479k);
                break;
            case 146:
                c = C0505n.aP(c0479k);
                break;
            case 147:
                c = C0505n.au(c0479k);
                break;
            case 148:
                c = C0505n.aB(c0479k);
                break;
            case 149:
                c = C0505n.aQ(c0479k);
                break;
            case 150:
                c = C0505n.aN(c0479k);
                break;
            case 151:
                c = C0505n.aO(c0479k);
                break;
            case 152:
                c = C0505n.m2154l(c0479k);
                break;
            case 153:
                c = C0505n.m2155m(c0479k);
                break;
            case 154:
                c = C0505n.m2156n(c0479k);
                break;
            case 155:
                c = C0505n.m2157o(c0479k);
                break;
            case 156:
                c = C0505n.av(c0479k);
                break;
            case 157:
                c = C0505n.aw(c0479k);
                break;
            case 158:
                c = C0505n.ay(c0479k);
                break;
            case 159:
                c = C0505n.az(c0479k);
                break;
            case 160:
                c = C0505n.aD(c0479k);
                break;
            case 161:
                c = C0505n.as(c0479k);
                break;
            case 162:
                c = C0505n.aA(c0479k);
                break;
            case 163:
                c = C0505n.aG(c0479k);
                break;
            case 164:
                c = C0505n.aJ(c0479k);
                break;
            case 165:
                c = C0505n.aK(c0479k);
                break;
            case 166:
                c = C0505n.aL(c0479k);
                break;
            case 167:
                c = C0505n.aM(c0479k);
                break;
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 175:
                c = C0505n.ax(c0479k);
                break;
            case 176:
                c = C0505n.aT(c0479k);
                break;
            case 177:
                c = C0505n.aU(c0479k);
                break;
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
                c = C0505n.aV(c0479k);
                break;
            case 183:
                c = C0505n.m2124N(c0479k);
                break;
            case 184:
                c = C0505n.m2125O(c0479k);
                break;
            case 185:
                c = C0505n.m2167y(c0479k);
                break;
            case 186:
                c = C0505n.m2166x(c0479k);
                break;
            case 187:
                c = C0505n.m2153k(c0479k);
                break;
            case 188:
                c = C0505n.m2127Q(c0479k);
                break;
            case 189:
                c = C0505n.m2126P(c0479k);
                break;
            case 190:
                c = C0505n.m2151i(c0479k);
                break;
            case 191:
                c = C0505n.ac(c0479k);
                break;
            default:
                c = null;
                break;
        }
        if (c == null) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("tasks.profile.fields", new JSONObject((Map) c.get("tasks.profile.fields")));
                jSONObject.put("tasks.profile.config", new JSONObject((Map) c.get("tasks.profile.config")));
                return jSONObject;
            } catch (Exception e) {
                return jSONObject;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aA(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 4) {
                throw new Exception();
            }
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            String str = split[0] + "\n" + applicationContext.getString(C0625h.task_cond_if_file_content_contains) + " " + split[1];
            String str2 = "1".equals(split[2]) ? str + "\n" + applicationContext.getString(C0625h.task_cond_if_file_content_match) + " : " + applicationContext.getString(C0625h.yes) : str + "\n" + applicationContext.getString(C0625h.task_cond_if_file_content_match) + " : " + applicationContext.getString(C0625h.no);
            str = "1".equals(split[3]) ? applicationContext.getString(C0625h.cond_desc_include) : string;
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2].equals("1") ? "true" : "false");
            hashMap2.put("field4", split[3]);
            string = str2 + "\n" + str;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aB(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_running_app_title) + " " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aC(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String str = split[0] + "\n" + applicationContext.getString(C0625h.task_cond_http_get_return_value) + " " + split[1];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[2].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aD(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_if_var_equal_equal) + "\n" + split[0] + "\n" + split[1];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[2].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aE(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String str = split[0] + "\n";
            Object obj = split[1];
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_cond_is_website_reachable_array);
            String str2 = "1".equals(obj) ? str + stringArray[1] : str + stringArray[0];
            str = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[2].equals("1")) {
                str = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            str = str2 + " : " + str;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aF(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String str = split[0] + "\n";
            int indexOf = Arrays.asList(au.f1979n).indexOf(Integer.valueOf(split[1]));
            String str2 = str + applicationContext.getString(C0625h.task_cond_is_http_status_code_http_code_title) + " " + applicationContext.getResources().getStringArray(C0619b.task_cond_is_http_status_code_array)[indexOf];
            str = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[2].equals("1")) {
                str = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", String.valueOf(indexOf));
            hashMap2.put("field3", split[2]);
            str = str2 + "\n" + str;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aG(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 4) {
                throw new Exception();
            }
            int intValue = Integer.valueOf(split[2]).intValue();
            if (intValue < 0 || intValue > 3) {
                throw new Exception();
            }
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_cond_if_var_range_array);
            String str = String.format(applicationContext.getString(C0625h.task_cond_if_var_range_if), new Object[]{split[0]}) + " " + stringArray[intValue].toLowerCase() + " " + split[1];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[3].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            hashMap2.put("field4", split[3]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aH(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.task_cond_internet_availability_state)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aI(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_clipboard_contains) + " " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aJ(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String valueOf = String.valueOf(Integer.valueOf(split[0]).intValue() + 1);
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = valueOf + " : " + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aK(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_cond_month_array);
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = stringArray[Integer.valueOf(split[0]).intValue()] + " : " + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aL(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = split[0] + " : " + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aM(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            Calendar a = C0487b.m2052a(split[0], "yyyy-MM-dd");
            Calendar a2 = C0487b.m2052a(split[1], "yyyy-MM-dd");
            String str = (applicationContext.getString(C0625h.task_cond_date_title) + " " + C0487b.m2051a(a)) + " - " + C0487b.m2051a(a2);
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[2].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aN(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = split[0];
            String str2 = split[1];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (str2.equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", str);
            hashMap2.put("field2", str2);
            string = applicationContext.getResources().getStringArray(C0619b.zen_mode_arrays)[Integer.valueOf(str).intValue()] + " : " + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aO(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = split[0];
            String str2 = split[1];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (str2.equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", str);
            hashMap2.put("field2", str2);
            string = applicationContext.getResources().getStringArray(C0619b.sound_profile_cond_arrays)[Integer.valueOf(str).intValue()] + " : " + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aP(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.task_cond_is_plugged_in_state)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aQ(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.scan_number_arrays)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aR(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_cond_end_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aS(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_cond_else_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aT(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_lockscreen_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aU(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            Object d = C0503m.m2110d(c0479k2);
            String c = C0503m.m2109c(c0479k2);
            if (d != null) {
                d = d.replace(".png", "");
            }
            hashMap2.put("field1", d);
            hashMap2.put("field2", c);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(c0479k.m2011e())));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", c0479k2);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aV(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            int i;
            int i2;
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            int i3 = -1;
            Object obj = "";
            C0481m a = C0481m.m2027a(e);
            int intValue = Integer.valueOf(c0479k2).intValue();
            switch (a) {
                case TASK_BUTTON:
                    i = C0619b.keycodes_button_names;
                    i2 = C0619b.keycodes_button_values;
                    break;
                case TASK_KEYBOARD:
                    i = C0619b.keycodes_keyboard_names;
                    i2 = C0619b.keycodes_keyboard_values;
                    break;
                case TASK_DPAD:
                    i = C0619b.keycodes_dpad_names;
                    i2 = C0619b.keycodes_dpad_values;
                    break;
                case TASK_NUMPAD:
                    i = C0619b.keycodes_numpad_names;
                    i2 = C0619b.keycodes_numpad_values;
                    break;
                case TASK_GAMEPAD:
                    i = C0619b.keycodes_gamepad_names;
                    i2 = C0619b.keycodes_gamepad_values;
                    break;
                default:
                    throw new Exception();
            }
            String[] stringArray = applicationContext.getResources().getStringArray(i);
            String[] stringArray2 = applicationContext.getResources().getStringArray(i2);
            for (int i4 = 0; i4 < stringArray2.length; i4++) {
                if (KeyEvent.keyCodeFromString(stringArray2[i4]) == intValue) {
                    obj = stringArray[i4];
                    i3 = i4;
                }
            }
            hashMap2.put("field1", String.valueOf(i3));
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aa(C0479k c0479k) {
        int i = 3;
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String[] split = c0479k2.split("\\|");
            if (split.length < 3) {
                throw new Exception();
            }
            String str = split[0];
            String d = C0503m.m2110d(str);
            String c = C0503m.m2109c(str);
            int intValue = Integer.valueOf(split[1]).intValue();
            if (intValue < 0 || intValue > 2) {
                throw new Exception();
            }
            String str2 = split[2];
            if (split.length > 3) {
                while (i < split.length) {
                    String str3 = str2 + "|" + split[i];
                    i++;
                    str2 = str3;
                }
            }
            String str4 = applicationContext.getResources().getStringArray(C0619b.task_write_file_mode_arrays)[intValue] + " : " + str + "\n" + str2;
            hashMap2.put("field1", d);
            hashMap2.put("field2", c);
            hashMap2.put("field3", split[1]);
            hashMap2.put("field4", str2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(c0479k.m2011e())));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str4);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ab(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String str = split[0];
            String str2 = split[1];
            String str3 = split[2];
            String str4 = ((applicationContext.getString(C0625h.task_input_field_title) + " " + str + "\n") + applicationContext.getString(C0625h.task_input_field_message) + " " + str2 + "\n") + applicationContext.getString(C0625h.task_input_field_variable) + " " + str3;
            hashMap2.put("field1", str);
            hashMap2.put("field2", str2);
            hashMap2.put("field3", str3);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(c0479k.m2011e())));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str4);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ac(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = split[0];
            String str2 = split[1];
            String str3 = (applicationContext.getString(C0625h.task_download_file_address_title) + " " + str + "\n") + applicationContext.getString(C0625h.task_download_file_folder_title) + " " + str2;
            hashMap2.put("field1", str);
            hashMap2.put("field2", str2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(c0479k.m2011e())));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str3);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ad(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            if (c0479k2.contains("|")) {
                int indexOf = c0479k2.indexOf("|");
                String substring = c0479k2.substring(0, indexOf);
                String substring2 = c0479k2.substring(indexOf + 1, c0479k2.length());
                hashMap2.put("field1", substring);
                hashMap2.put("field2", substring2);
                hashMap3.put("requestType", String.valueOf(C0481m.m2028b(c0479k.m2011e())));
                hashMap3.put("itemTask", c0479k2);
                hashMap3.put("itemDescription", substring);
                hashMap3.put("itemHash", null);
                hashMap3.put("itemUpdate", String.valueOf(false));
                hashMap.put("tasks.profile.fields", hashMap2);
                hashMap.put("tasks.profile.config", hashMap3);
                return hashMap;
            }
            throw new Exception();
        } catch (Exception e) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ae(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String str = split[0];
            String str2 = split[1];
            String str3 = split[2];
            String str4 = str3 + "\n" + str + "," + str2;
            hashMap2.put("field1", str3);
            hashMap2.put("field2", str);
            hashMap2.put("field3", str2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(c0479k.m2011e())));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str4);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> af(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String c0479k2 = c0479k.toString();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = split[0];
            String str2 = split[1];
            String str3 = applicationContext.getResources().getStringArray(C0619b.record_search_engine_arrays)[Integer.valueOf(str).intValue()] + " : " + str2;
            hashMap2.put("field1", str);
            hashMap2.put("field2", str2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(c0479k.m2011e())));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str3);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ag(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance.set(11, C0487b.m2052a(split[0], "HH:mm").get(11));
            instance.set(12, C0487b.m2052a(split[0], "HH:mm").get(12));
            instance2.set(11, C0487b.m2052a(split[1], "HH:mm").get(11));
            instance2.set(12, C0487b.m2052a(split[1], "HH:mm").get(12));
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[2].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            string = (C0487b.m2053b(instance) + " - " + C0487b.m2053b(instance2) + "\n") + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ah(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_wifi_network_ssid) + " " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ai(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_device_paired_title) + " " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aj(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_device_connected_title) + " " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ak(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 8);
            if (b.length() != 8) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            String substring3 = b.substring(2, 3);
            String substring4 = b.substring(3, 4);
            String substring5 = b.substring(4, 5);
            String substring6 = b.substring(5, 6);
            String substring7 = b.substring(6, 7);
            String substring8 = b.substring(7, 8);
            String string = substring8.equals("1") ? applicationContext.getString(C0625h.cond_desc_include) : applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring.equals("1")) {
                hashMap2.put("field1", "true");
            } else {
                hashMap2.put("field1", "false");
            }
            if (substring2.equals("1")) {
                hashMap2.put("field2", "true");
            } else {
                hashMap2.put("field2", "false");
            }
            if (substring3.equals("1")) {
                hashMap2.put("field3", "true");
            } else {
                hashMap2.put("field3", "false");
            }
            if (substring4.equals("1")) {
                hashMap2.put("field4", "true");
            } else {
                hashMap2.put("field4", "false");
            }
            if (substring5.equals("1")) {
                hashMap2.put("field5", "true");
            } else {
                hashMap2.put("field5", "false");
            }
            if (substring6.equals("1")) {
                hashMap2.put("field6", "true");
            } else {
                hashMap2.put("field6", "false");
            }
            if (substring7.equals("1")) {
                hashMap2.put("field7", "true");
            } else {
                hashMap2.put("field7", "false");
            }
            hashMap2.put("field8", substring8);
            b = "";
            if (substring.equals("1")) {
                b = b + applicationContext.getString(C0625h.toggle_monday) + ",";
            }
            if (substring2.equals("1")) {
                b = b + applicationContext.getString(C0625h.toggle_tuesday) + ",";
            }
            if (substring3.equals("1")) {
                b = b + applicationContext.getString(C0625h.toggle_wednesday) + ",";
            }
            if (substring4.equals("1")) {
                b = b + applicationContext.getString(C0625h.toggle_thursday) + ",";
            }
            if (substring5.equals("1")) {
                b = b + applicationContext.getString(C0625h.toggle_friday) + ",";
            }
            if (substring6.equals("1")) {
                b = b + applicationContext.getString(C0625h.toggle_saturday) + ",";
            }
            if (substring7.equals("1")) {
                b = b + applicationContext.getString(C0625h.toggle_sunday) + ",";
            }
            if (!b.isEmpty()) {
                b = b.substring(0, b.length() - 1);
            }
            b = b + "\n" + string + "\n";
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> al(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + applicationContext.getString(C0625h.task_cond_yes_no_dialog_title) + " " + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> am(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.states_cond_arrays)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> an(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.music_cond_arrays)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ao(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.root_state_arrays)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ap(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.cond_brightness_mode_arrays)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aq(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String b = C0489d.m2069b(C0489d.m2068b(c0479k2), 2);
            if (b.length() != 2) {
                throw new Exception();
            }
            String substring = b.substring(0, 1);
            String substring2 = b.substring(1, 2);
            b = applicationContext.getString(C0625h.cond_desc_exclude);
            if (substring2.equals("1")) {
                b = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", substring);
            hashMap2.put("field2", substring2);
            b = applicationContext.getResources().getStringArray(C0619b.cond_wired_headset_arrays)[Integer.valueOf(substring).intValue()] + " : " + b;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", b);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ar(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.openvpn_action_arrays);
            String str = stringArray[0];
            if (split[1].equals("1")) {
                str = stringArray[1];
            }
            str = str + " : " + split[0];
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> as(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_if_var_exist_title) + " : " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> at(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_clipboard_contains) + " " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> au(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_if_app_title) + " " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> av(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_if_file_exist_title) + " : " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> aw(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 2) {
                throw new Exception();
            }
            String str = applicationContext.getString(C0625h.task_cond_if_directory_exist_title) + " : " + split[0];
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            if (split[1].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            string = str + "\n" + string;
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ax(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.level_state_arrays);
            if (split[2].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            string = stringArray[Integer.valueOf(split[0]).intValue()] + " " + String.valueOf(split[1]) + "\n" + string;
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> ay(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.battery_level_state_arrays);
            if (split[2].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            string = stringArray[Integer.valueOf(split[0]).intValue()] + " " + split[1] + "%\n" + string;
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> az(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            Context applicationContext = WDCore.m2174a().getApplicationContext();
            String[] split = c0479k2.split("\\|");
            if (split.length != 3) {
                throw new Exception();
            }
            String string = applicationContext.getString(C0625h.cond_desc_exclude);
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.battery_temp_state_arrays);
            if (split[2].equals("1")) {
                string = applicationContext.getString(C0625h.cond_desc_include);
            }
            int intValue = Integer.valueOf(split[1]).intValue();
            string = stringArray[Integer.valueOf(split[0]).intValue()] + " " + String.valueOf(intValue) + " \u00b0C / " + String.valueOf(C0489d.m2067b(C0489d.m2056a((float) intValue))) + " \u00b0F \n" + string;
            hashMap2.put("field1", split[0]);
            hashMap2.put("field2", split[1]);
            hashMap2.put("field3", split[2]);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", string);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static String m2139b(String str) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        Object obj = -1;
        switch (str.hashCode()) {
            case C0627j.Theme_dividerVertical /*48*/:
                if (str.equals("0")) {
                    obj = null;
                    break;
                }
                break;
            case C0627j.Theme_dividerHorizontal /*49*/:
                if (str.equals("1")) {
                    obj = 1;
                    break;
                }
                break;
            case C0627j.Theme_activityChooserViewStyle /*50*/:
                if (str.equals("2")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case C0627j.View_android_focusable /*0*/:
                return applicationContext.getString(C0625h.manual);
            case C0627j.View_paddingStart /*1*/:
                return applicationContext.getString(C0625h.automatic);
            case C0627j.View_paddingEnd /*2*/:
                return applicationContext.getString(C0625h.state_toggle);
            default:
                return "";
        }
    }

    private static HashMap<String, HashMap<String, String>> m2140b(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String[] split = c0479k2.split("\\|");
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_send_intent_target_arrays);
            if (split == null || split.length < 1) {
                return null;
            }
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            String str10 = stringArray[Integer.valueOf(split[0]).intValue()];
            hashMap2.put("field1", split[0]);
            String str11 = null;
            String str12 = null;
            if (split.length > 1) {
                str = split[1];
                str2 = str.equals("null") ? null : str;
            } else {
                str2 = null;
            }
            if (split.length > 2) {
                str3 = split[2];
                str = str3.equals("null") ? null : str3;
            } else {
                str = null;
            }
            if (split.length > 3) {
                str4 = split[3];
                str3 = str4.equals("null") ? null : str4;
            } else {
                str3 = null;
            }
            if (split.length > 4) {
                str5 = split[4];
                str4 = str5.equals("null") ? null : str5;
            } else {
                str4 = null;
            }
            if (split.length > 5) {
                str6 = split[5];
                str5 = str6.equals("null") ? null : str6;
            } else {
                str5 = null;
            }
            if (split.length > 6) {
                str7 = split[6];
                str6 = str7.equals("null") ? null : str7;
            } else {
                str6 = null;
            }
            if (split.length > 7) {
                str8 = split[7];
                str7 = str8.equals("null") ? null : str8;
            } else {
                str7 = null;
            }
            if (split.length > 8) {
                str11 = split[8];
                if (str11.equals("null")) {
                    str11 = null;
                }
            }
            if (split.length > 9) {
                str9 = split[9];
                str8 = str9.equals("null") ? null : str9;
            } else {
                str8 = null;
            }
            if (split.length > 10) {
                str12 = split[10];
                if (str12.equals("null")) {
                    str12 = null;
                }
            }
            Object obj = applicationContext.getString(C0625h.task_send_intent_title_target) + " " + str10 + "\n";
            if (str2 != null) {
                hashMap2.put("field2", str2);
                obj = obj + applicationContext.getString(C0625h.task_send_intent_title_action) + " " + str2 + "\n";
            }
            if (str != null) {
                hashMap2.put("field3", str);
                obj = obj + applicationContext.getString(C0625h.task_send_intent_title_category) + " " + str + "\n";
            }
            if (str3 != null) {
                hashMap2.put("field4", str3);
                obj = obj + applicationContext.getString(C0625h.task_send_intent_title_flag) + " " + str3 + "\n";
            }
            if (str4 != null) {
                hashMap2.put("field5", str4);
                obj = obj + applicationContext.getString(C0625h.task_send_intent_title_package) + " " + str4 + "\n";
            }
            if (str5 != null) {
                hashMap2.put("field6", str5);
                obj = obj + applicationContext.getString(C0625h.task_send_intent_title_class) + " " + str5 + "\n";
            }
            if (str6 != null) {
                hashMap2.put("field7", str6);
                obj = obj + applicationContext.getString(C0625h.task_send_intent_title_type) + " " + str6 + "\n";
            }
            if (str7 != null) {
                hashMap2.put("field8", str7);
                str7 = obj + " - " + applicationContext.getString(C0625h.task_send_intent_title_extra1) + " -\n" + applicationContext.getString(C0625h.task_send_intent_title_extrakey) + " " + str7 + "\n";
                str9 = str11 == null ? "null" : str11;
                hashMap2.put("field9", str9);
                obj = str7 + applicationContext.getString(C0625h.task_send_intent_title_extravalue) + " " + str9 + "\n";
            }
            if (str8 != null) {
                hashMap2.put("field10", str8);
                str11 = obj + " - " + applicationContext.getString(C0625h.task_send_intent_title_extra2) + " -\n" + applicationContext.getString(C0625h.task_send_intent_title_extrakey) + " " + str8 + "\n";
                str9 = str12 == null ? "null" : str12;
                hashMap2.put("field11", str9);
                obj = str11 + applicationContext.getString(C0625h.task_send_intent_title_extravalue) + " " + str9 + "\n";
            }
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static String m2141c(String str) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        Object obj = -1;
        switch (str.hashCode()) {
            case C0627j.Theme_dividerVertical /*48*/:
                if (str.equals("0")) {
                    obj = null;
                    break;
                }
                break;
            case C0627j.Theme_dividerHorizontal /*49*/:
                if (str.equals("1")) {
                    obj = 1;
                    break;
                }
                break;
            case C0627j.Theme_activityChooserViewStyle /*50*/:
                if (str.equals("2")) {
                    obj = 2;
                    break;
                }
                break;
            case C0627j.Theme_toolbarStyle /*51*/:
                if (str.equals("3")) {
                    obj = 3;
                    break;
                }
                break;
            case C0627j.Theme_toolbarNavigationButtonStyle /*52*/:
                if (str.equals("4")) {
                    obj = 4;
                    break;
                }
                break;
            case C0627j.Theme_popupMenuStyle /*53*/:
                if (str.equals("5")) {
                    obj = 5;
                    break;
                }
                break;
        }
        switch (obj) {
            case C0627j.View_android_focusable /*0*/:
                return applicationContext.getString(C0625h.profile_mute);
            case C0627j.View_paddingStart /*1*/:
                return applicationContext.getString(C0625h.profile_vibrate);
            case C0627j.View_paddingEnd /*2*/:
                return applicationContext.getString(C0625h.profile_normal);
            case C0627j.Toolbar_subtitle /*3*/:
                return applicationContext.getString(C0625h.profile_toggle_normal_mute);
            case C0627j.Toolbar_contentInsetStart /*4*/:
                return applicationContext.getString(C0625h.profile_toggle_normal_vibrate);
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                return applicationContext.getString(C0625h.profile_toggle_vibrate_mute);
            default:
                return "";
        }
    }

    private static HashMap<String, HashMap<String, String>> m2142c(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", C0505n.m2137a(c0479k2));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static String m2143d(String str) {
        String[] stringArray = WDCore.m2174a().getApplicationContext().getResources().getStringArray(C0619b.media_controls_arrays);
        int i = -1;
        switch (str.hashCode()) {
            case C0627j.Theme_dividerVertical /*48*/:
                if (str.equals("0")) {
                    i = 0;
                    break;
                }
                break;
            case C0627j.Theme_dividerHorizontal /*49*/:
                if (str.equals("1")) {
                    i = 1;
                    break;
                }
                break;
            case C0627j.Theme_activityChooserViewStyle /*50*/:
                if (str.equals("2")) {
                    i = 2;
                    break;
                }
                break;
            case C0627j.Theme_toolbarStyle /*51*/:
                if (str.equals("3")) {
                    i = 3;
                    break;
                }
                break;
            case C0627j.Theme_toolbarNavigationButtonStyle /*52*/:
                if (str.equals("4")) {
                    i = 4;
                    break;
                }
                break;
            case C0627j.Theme_popupMenuStyle /*53*/:
                if (str.equals("5")) {
                    i = 5;
                    break;
                }
                break;
        }
        switch (i) {
            case C0627j.View_android_focusable /*0*/:
                return stringArray[0];
            case C0627j.View_paddingStart /*1*/:
                return stringArray[1];
            case C0627j.View_paddingEnd /*2*/:
                return stringArray[2];
            case C0627j.Toolbar_subtitle /*3*/:
                return stringArray[3];
            case C0627j.Toolbar_contentInsetStart /*4*/:
                return stringArray[4];
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                return stringArray[5];
            default:
                return "";
        }
    }

    private static HashMap<String, HashMap<String, String>> m2144d(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", C0505n.m2141c(c0479k2));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static String m2145e(String str) {
        String[] stringArray = WDCore.m2174a().getApplicationContext().getResources().getStringArray(C0619b.task_call_log_action_arrays);
        int i = -1;
        switch (str.hashCode()) {
            case C0627j.Theme_dividerVertical /*48*/:
                if (str.equals("0")) {
                    i = 0;
                    break;
                }
                break;
            case C0627j.Theme_dividerHorizontal /*49*/:
                if (str.equals("1")) {
                    i = 1;
                    break;
                }
                break;
        }
        switch (i) {
            case C0627j.View_android_focusable /*0*/:
                return stringArray[0];
            case C0627j.View_paddingStart /*1*/:
                return stringArray[1];
            default:
                return "";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static HashMap<String, HashMap<String, String>> m2146e(C0479k r13) {
        /*
        r1 = 0;
        r6 = 3;
        r5 = 2;
        r4 = 1;
        r2 = 0;
        r0 = com.wakdev.libs.core.WDCore.m2174a();
        r3 = r0.getApplicationContext();
        r0 = new java.util.HashMap;
        r0.<init>();
        r7 = new java.util.HashMap;
        r7.<init>();
        r8 = new java.util.HashMap;
        r8.<init>();
        r9 = r13.toString();	 Catch:{ Exception -> 0x00aa }
        r10 = r13.m2011e();	 Catch:{ Exception -> 0x00aa }
        r3 = r3.getResources();	 Catch:{ Exception -> 0x00aa }
        r11 = com.wakdev.nfctools.C0628m.C0619b.task_donotdisturb_plus_array;	 Catch:{ Exception -> 0x00aa }
        r11 = r3.getStringArray(r11);	 Catch:{ Exception -> 0x00aa }
        r3 = -1;
        r12 = r9.hashCode();	 Catch:{ Exception -> 0x00aa }
        switch(r12) {
            case 48: goto L_0x0073;
            case 49: goto L_0x007c;
            case 50: goto L_0x0086;
            case 51: goto L_0x0090;
            default: goto L_0x0036;
        };	 Catch:{ Exception -> 0x00aa }
    L_0x0036:
        r2 = r3;
    L_0x0037:
        switch(r2) {
            case 0: goto L_0x009a;
            case 1: goto L_0x009e;
            case 2: goto L_0x00a2;
            case 3: goto L_0x00a6;
            default: goto L_0x003a;
        };	 Catch:{ Exception -> 0x00aa }
    L_0x003a:
        r2 = "";
    L_0x003c:
        r3 = "field1";
        r7.put(r3, r9);	 Catch:{ Exception -> 0x00aa }
        r3 = "requestType";
        r4 = com.wakdev.libs.p015a.C0481m.m2028b(r10);	 Catch:{ Exception -> 0x00aa }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x00aa }
        r8.put(r3, r4);	 Catch:{ Exception -> 0x00aa }
        r3 = "itemTask";
        r8.put(r3, r9);	 Catch:{ Exception -> 0x00aa }
        r3 = "itemDescription";
        r8.put(r3, r2);	 Catch:{ Exception -> 0x00aa }
        r2 = "itemHash";
        r3 = 0;
        r8.put(r2, r3);	 Catch:{ Exception -> 0x00aa }
        r2 = "itemUpdate";
        r3 = 0;
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x00aa }
        r8.put(r2, r3);	 Catch:{ Exception -> 0x00aa }
        r2 = "tasks.profile.fields";
        r0.put(r2, r7);	 Catch:{ Exception -> 0x00aa }
        r2 = "tasks.profile.config";
        r0.put(r2, r8);	 Catch:{ Exception -> 0x00aa }
    L_0x0072:
        return r0;
    L_0x0073:
        r4 = "0";
        r4 = r9.equals(r4);	 Catch:{ Exception -> 0x00aa }
        if (r4 == 0) goto L_0x0036;
    L_0x007b:
        goto L_0x0037;
    L_0x007c:
        r2 = "1";
        r2 = r9.equals(r2);	 Catch:{ Exception -> 0x00aa }
        if (r2 == 0) goto L_0x0036;
    L_0x0084:
        r2 = r4;
        goto L_0x0037;
    L_0x0086:
        r2 = "2";
        r2 = r9.equals(r2);	 Catch:{ Exception -> 0x00aa }
        if (r2 == 0) goto L_0x0036;
    L_0x008e:
        r2 = r5;
        goto L_0x0037;
    L_0x0090:
        r2 = "3";
        r2 = r9.equals(r2);	 Catch:{ Exception -> 0x00aa }
        if (r2 == 0) goto L_0x0036;
    L_0x0098:
        r2 = r6;
        goto L_0x0037;
    L_0x009a:
        r2 = 0;
        r2 = r11[r2];	 Catch:{ Exception -> 0x00aa }
        goto L_0x003c;
    L_0x009e:
        r2 = 1;
        r2 = r11[r2];	 Catch:{ Exception -> 0x00aa }
        goto L_0x003c;
    L_0x00a2:
        r2 = 2;
        r2 = r11[r2];	 Catch:{ Exception -> 0x00aa }
        goto L_0x003c;
    L_0x00a6:
        r2 = 3;
        r2 = r11[r2];	 Catch:{ Exception -> 0x00aa }
        goto L_0x003c;
    L_0x00aa:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wakdev.libs.commons.n.e(com.wakdev.libs.a.k):java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.String>>");
    }

    private static String m2147f(String str) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        Object obj = -1;
        switch (str.hashCode()) {
            case C0627j.Theme_dividerHorizontal /*49*/:
                if (str.equals("1")) {
                    obj = null;
                    break;
                }
                break;
            case C0627j.Theme_activityChooserViewStyle /*50*/:
                if (str.equals("2")) {
                    obj = 1;
                    break;
                }
                break;
            case C0627j.Theme_toolbarStyle /*51*/:
                if (str.equals("3")) {
                    obj = 2;
                    break;
                }
                break;
            case C0627j.Theme_toolbarNavigationButtonStyle /*52*/:
                if (str.equals("4")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case C0627j.View_android_focusable /*0*/:
                return applicationContext.getString(C0625h.task_run_tool_1);
            case C0627j.View_paddingStart /*1*/:
                return applicationContext.getString(C0625h.task_run_tool_2);
            case C0627j.View_paddingEnd /*2*/:
                return applicationContext.getString(C0625h.task_run_tool_3);
            case C0627j.Toolbar_subtitle /*3*/:
                return applicationContext.getString(C0625h.task_run_tool_4);
            default:
                return "";
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static HashMap<String, HashMap<String, String>> m2148f(C0479k r12) {
        /*
        r1 = 0;
        r5 = 2;
        r4 = 1;
        r2 = 0;
        r0 = com.wakdev.libs.core.WDCore.m2174a();
        r3 = r0.getApplicationContext();
        r0 = new java.util.HashMap;
        r0.<init>();
        r6 = new java.util.HashMap;
        r6.<init>();
        r7 = new java.util.HashMap;
        r7.<init>();
        r8 = r12.toString();	 Catch:{ Exception -> 0x009b }
        r9 = r12.m2011e();	 Catch:{ Exception -> 0x009b }
        r3 = r3.getResources();	 Catch:{ Exception -> 0x009b }
        r10 = com.wakdev.nfctools.C0628m.C0619b.zen_mode_arrays;	 Catch:{ Exception -> 0x009b }
        r10 = r3.getStringArray(r10);	 Catch:{ Exception -> 0x009b }
        r3 = -1;
        r11 = r8.hashCode();	 Catch:{ Exception -> 0x009b }
        switch(r11) {
            case 48: goto L_0x0072;
            case 49: goto L_0x007b;
            case 50: goto L_0x0085;
            default: goto L_0x0035;
        };	 Catch:{ Exception -> 0x009b }
    L_0x0035:
        r2 = r3;
    L_0x0036:
        switch(r2) {
            case 0: goto L_0x008f;
            case 1: goto L_0x0093;
            case 2: goto L_0x0097;
            default: goto L_0x0039;
        };	 Catch:{ Exception -> 0x009b }
    L_0x0039:
        r2 = "";
    L_0x003b:
        r3 = "field1";
        r6.put(r3, r8);	 Catch:{ Exception -> 0x009b }
        r3 = "requestType";
        r4 = com.wakdev.libs.p015a.C0481m.m2028b(r9);	 Catch:{ Exception -> 0x009b }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x009b }
        r7.put(r3, r4);	 Catch:{ Exception -> 0x009b }
        r3 = "itemTask";
        r7.put(r3, r8);	 Catch:{ Exception -> 0x009b }
        r3 = "itemDescription";
        r7.put(r3, r2);	 Catch:{ Exception -> 0x009b }
        r2 = "itemHash";
        r3 = 0;
        r7.put(r2, r3);	 Catch:{ Exception -> 0x009b }
        r2 = "itemUpdate";
        r3 = 0;
        r3 = java.lang.String.valueOf(r3);	 Catch:{ Exception -> 0x009b }
        r7.put(r2, r3);	 Catch:{ Exception -> 0x009b }
        r2 = "tasks.profile.fields";
        r0.put(r2, r6);	 Catch:{ Exception -> 0x009b }
        r2 = "tasks.profile.config";
        r0.put(r2, r7);	 Catch:{ Exception -> 0x009b }
    L_0x0071:
        return r0;
    L_0x0072:
        r4 = "0";
        r4 = r8.equals(r4);	 Catch:{ Exception -> 0x009b }
        if (r4 == 0) goto L_0x0035;
    L_0x007a:
        goto L_0x0036;
    L_0x007b:
        r2 = "1";
        r2 = r8.equals(r2);	 Catch:{ Exception -> 0x009b }
        if (r2 == 0) goto L_0x0035;
    L_0x0083:
        r2 = r4;
        goto L_0x0036;
    L_0x0085:
        r2 = "2";
        r2 = r8.equals(r2);	 Catch:{ Exception -> 0x009b }
        if (r2 == 0) goto L_0x0035;
    L_0x008d:
        r2 = r5;
        goto L_0x0036;
    L_0x008f:
        r2 = 0;
        r2 = r10[r2];	 Catch:{ Exception -> 0x009b }
        goto L_0x003b;
    L_0x0093:
        r2 = 1;
        r2 = r10[r2];	 Catch:{ Exception -> 0x009b }
        goto L_0x003b;
    L_0x0097:
        r2 = 2;
        r2 = r10[r2];	 Catch:{ Exception -> 0x009b }
        goto L_0x003b;
    L_0x009b:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0071;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wakdev.libs.commons.n.f(com.wakdev.libs.a.k):java.util.HashMap<java.lang.String, java.util.HashMap<java.lang.String, java.lang.String>>");
    }

    private static HashMap<String, HashMap<String, String>> m2149g(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", C0505n.m2145e(c0479k2));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2150h(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_ok_google_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2151i(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_screensaver_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2152j(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_go_home_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2153k(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_dismiss_alarms_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2154l(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_reboot_device_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2155m(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_shutdown_device_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2156n(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_svoice_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2157o(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_splanner_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2158p(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_exit_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2159q(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_stop_sound_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2160r(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_input_method_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2161s(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            String[] stringArray = applicationContext.getResources().getStringArray(C0619b.task_expand_hide_notifications_array);
            Object obj = stringArray[0];
            if ("1".equals(c0479k2)) {
                obj = stringArray[1];
            }
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", obj);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2162t(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", applicationContext.getString(C0625h.task_end_call_description));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2163u(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", C0505n.m2143d(c0479k2));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2164v(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", C0505n.m2139b(c0479k2));
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2165w(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", c0479k2);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2166x(C0479k c0479k) {
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String str = applicationContext.getString(C0625h.task_secret_code_start) + c0479k2 + applicationContext.getString(C0625h.task_secret_code_end);
            hashMap2.put("field1", c0479k2);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", str);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2167y(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            String replace = c0479k2.replace("{VAR_", "").replace("}", "");
            hashMap2.put("field1", replace);
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemDescription", replace);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }

    private static HashMap<String, HashMap<String, String>> m2168z(C0479k c0479k) {
        HashMap<String, HashMap<String, String>> hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        try {
            String c0479k2 = c0479k.toString();
            String e = c0479k.m2011e();
            hashMap2.put("field1", "0");
            hashMap2.put("field2", c0479k2);
            hashMap2.put("field3", "");
            hashMap3.put("requestType", String.valueOf(C0481m.m2028b(e)));
            hashMap3.put("itemTask", c0479k2);
            hashMap3.put("itemTaskExtra", "0");
            hashMap3.put("itemDescription", c0479k2);
            hashMap3.put("itemHash", null);
            hashMap3.put("itemUpdate", String.valueOf(false));
            hashMap.put("tasks.profile.fields", hashMap2);
            hashMap.put("tasks.profile.config", hashMap3);
            return hashMap;
        } catch (Exception e2) {
            return null;
        }
    }
}
