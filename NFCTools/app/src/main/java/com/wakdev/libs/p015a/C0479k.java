package com.wakdev.libs.p015a;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.nfc.NdefRecord;
import com.wakdev.libs.commons.C0489d;
import com.wakdev.libs.p015a.p016a.C0464b;
import com.wakdev.libs.p015a.p016a.C0468c;
import com.wakdev.libs.p015a.p016a.C0468c.C0465a;
import com.wakdev.nfctools.C0628m.C0627j;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;

@SuppressLint({"NewApi"})
/* renamed from: com.wakdev.libs.a.k */
public class C0479k {
    private byte[] f1052a;
    private String f1053b;
    private short f1054c;
    private byte[] f1055d;
    private byte[] f1056e;
    private NdefRecord f1057f;

    public C0479k() {
        this.f1055d = new byte[0];
    }

    public static final String m1997a(byte b) {
        return b == null ? "" : b == 1 ? "http://www." : b == 2 ? "https://www." : b == 3 ? "http://" : b == 4 ? "https://" : b == 5 ? "tel:" : b == 6 ? "mailto:" : b == 7 ? "ftp://anonymous:anonymous@" : b == 8 ? "ftp://ftp." : b == 9 ? "ftps://" : b == 10 ? "sftp://" : b == 11 ? "smb://" : b == 12 ? "nfs://" : b == 13 ? "ftp://" : b == 14 ? "dav://" : b == 15 ? "news:" : b == 16 ? "telnet://" : b == 17 ? "imap:" : b == 18 ? "rtsp://" : b == 19 ? "urn:" : b == 20 ? "pop:" : b == 21 ? "sip:" : b == 22 ? "sips:" : b == 23 ? "tftp:" : b == 24 ? "btspp://" : b == 25 ? "btl2cap://" : b == 26 ? "btgoep://" : b == 27 ? "tcpobex://" : b == 28 ? "irdaobex://" : b == 29 ? "file://" : b == 30 ? "urn:epc:id:" : b == 31 ? "urn:epc:tag:" : b == 32 ? "urn:epc:pat:" : b == 33 ? "urn:epc:raw:" : b == 34 ? "urn:epc:" : b == 35 ? "urn:nfc:" : "";
    }

    private byte[] m1998a(byte[]... bArr) {
        int i = 0;
        for (byte[] length : bArr) {
            i += length.length;
        }
        Object obj = new byte[i];
        i = 0;
        for (Object obj2 : bArr) {
            System.arraycopy(obj2, 0, obj, i, obj2.length);
            i += obj2.length;
        }
        return obj;
    }

    public static NdefRecord m1999b(String str, byte[] bArr) {
        if (str == null) {
            throw new NullPointerException("mimeType is null");
        } else if (str.length() == 0) {
            throw new IllegalArgumentException("mimeType is empty");
        } else {
            int indexOf = str.indexOf(47);
            if (indexOf == 0) {
                throw new IllegalArgumentException("mimeType must have major type");
            } else if (indexOf == str.length() - 1) {
                throw new IllegalArgumentException("mimeType must have minor type");
            } else {
                return new NdefRecord((short) 2, str.getBytes(), new byte[0], bArr);
            }
        }
    }

    public NdefRecord m2000a() {
        return this.f1057f;
    }

    public void m2001a(NdefRecord ndefRecord) {
        this.f1057f = ndefRecord;
        this.f1052a = this.f1057f.getPayload();
        this.f1054c = this.f1057f.getTnf();
        try {
            this.f1053b = this.f1057f.toMimeType();
        } catch (NoSuchMethodError e) {
            this.f1053b = "";
        }
        this.f1055d = this.f1057f.getId();
        this.f1056e = this.f1057f.getType();
    }

    public void m2002a(String str) {
        m2001a(NdefRecord.createApplicationRecord(str));
    }

    public void m2003a(String str, String str2, byte[] bArr, short s, short s2) {
        C0465a c0465a = new C0465a();
        c0465a.m1876a(str2);
        c0465a.m1879b(str);
        c0465a.m1878a(bArr);
        c0465a.m1877a(s);
        c0465a.m1880b(s2);
        C0464b c0464b = new C0464b();
        c0464b.m1869a((byte) 1, new C0468c(c0465a));
        NdefRecord[] records = c0464b.m1868a().getRecords();
        if (records.length > 1) {
            byte[] payload = records[1].getPayload();
            m2001a(new NdefRecord(records[1].getTnf(), records[1].getType(), records[1].getId(), C0489d.m2064a(payload, 5, payload.length - 5)));
        }
    }

    public void m2004a(String str, byte[] bArr) {
        try {
            m2001a(NdefRecord.createMime(str, bArr));
        } catch (NoSuchMethodError e) {
            try {
                m2001a(C0479k.m1999b(str, bArr));
            } catch (Exception e2) {
            }
        }
    }

    public void m2005b() {
        byte[] bArr = new byte[0];
        m2001a(new NdefRecord((short) 0, bArr, bArr, bArr));
    }

    public void m2006b(String str) {
        String str2 = "application/vnd.bluetooth.ep.oob";
        String[] split = str.split(":");
        byte[] bArr = new byte[8];
        bArr[0] = (byte) 8;
        bArr[1] = (byte) 0;
        int i = 2;
        for (int i2 = 5; i2 >= 0; i2--) {
            bArr[i] = Integer.valueOf(Integer.parseInt(split[i2], 16)).byteValue();
            i++;
        }
        try {
            m2001a(NdefRecord.createMime(str2, bArr));
        } catch (NoSuchMethodError e) {
            try {
                m2001a(C0479k.m1999b(str2, bArr));
            } catch (Exception e2) {
            }
        }
    }

    public void m2007c(String str) {
        m2001a(NdefRecord.createUri(str));
    }

    public byte[] m2008c() {
        return this.f1056e;
    }

    public int m2009d() {
        return this.f1052a.length;
    }

    public void m2010d(String str) {
        String str2 = "en";
        Object bytes = str.getBytes();
        Object obj = new byte[0];
        try {
            obj = str2.getBytes("US-ASCII");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        int length = obj.length;
        int length2 = bytes.length;
        Object obj2 = new byte[((length + 1) + length2)];
        obj2[0] = (byte) length;
        System.arraycopy(obj, 0, obj2, 1, length);
        System.arraycopy(bytes, 0, obj2, length + 1, length2);
        m2001a(new NdefRecord((short) 1, NdefRecord.RTD_TEXT, new byte[0], obj2));
    }

    public String m2011e() {
        return this.f1053b;
    }

    public String m2012e(String str) {
        String str2 = "";
        try {
            return new String(this.f1052a, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str2;
        }
    }

    public int m2013f() {
        int i = 0;
        if (this.f1057f == null) {
            return i;
        }
        try {
            return this.f1057f.getType().length + (3 + this.f1057f.getPayload().length);
        } catch (Exception e) {
            return i;
        }
    }

    public String m2014g() {
        try {
            return (this.f1052a[0] & 128) == 0 ? "UTF-8" : "UTF-16";
        } catch (Exception e) {
            return "";
        }
    }

    public String m2015h() {
        return new String(this.f1052a, 1, this.f1052a[0] & 51);
    }

    public String m2016i() {
        try {
            int i = this.f1052a[0] & 51;
            return new String(this.f1052a, i + 1, (this.f1052a.length - i) - 1, (this.f1052a[0] & 128) == 0 ? "UTF-8" : "UTF-16");
        } catch (Exception e) {
            return "";
        }
    }

    public int m2017j() {
        int i;
        UnsupportedEncodingException e;
        int i2 = (short) 4 == this.f1054c ? 4 : 1;
        if ((short) 2 == this.f1054c) {
            String str = this.f1053b;
            i2 = -1;
            switch (str.hashCode()) {
                case -2130718966:
                    if (str.equals("application/vnd.wfa.wsc")) {
                        i2 = 1;
                        break;
                    }
                    break;
                case 822609188:
                    if (str.equals("text/vcard")) {
                        i2 = 2;
                        break;
                    }
                    break;
                case 941490784:
                    if (str.equals("application/vnd.bluetooth.ep.oob")) {
                        i2 = 0;
                        break;
                    }
                    break;
            }
            switch (i2) {
                case C0627j.View_android_focusable /*0*/:
                    i2 = 14;
                    break;
                case C0627j.View_paddingStart /*1*/:
                    i2 = 15;
                    break;
                case C0627j.View_paddingEnd /*2*/:
                    i2 = 6;
                    break;
                default:
                    if (C0481m.m2027a(this.f1053b) == null) {
                        i2 = 10;
                        break;
                    }
                    i2 = 25;
                    break;
            }
        }
        if (this.f1054c == (short) 0) {
            i2 = 11;
        }
        if ((short) 5 == this.f1054c) {
            i2 = 1;
        }
        if ((short) 1 != this.f1054c) {
            return i2;
        }
        if ("text/plain".equals(this.f1053b)) {
            i2 = 2;
        }
        if (!Arrays.equals(m2008c(), NdefRecord.RTD_URI)) {
            return i2;
        }
        String a = C0479k.m1997a(this.f1052a[0]);
        if (a != null) {
            if (a.equals("mailto:")) {
                i2 = 5;
            }
            if (a.equals("tel:")) {
                i2 = 7;
            }
            if (a.equals("http://") || a.equals("https://") || a.equals("http://www.") || a.equals("https://www.")) {
                String c0479k = toString();
                i2 = c0479k.contains("facebook.com") ? 16 : c0479k.contains("twitter.com") ? 17 : c0479k.contains("plus.google.com") ? 18 : c0479k.contains("linkedin.com") ? 19 : c0479k.contains("pinterest.com") ? 20 : c0479k.contains("instagram.com") ? 21 : c0479k.contains("tumblr.com") ? 22 : c0479k.contains("github.com") ? 23 : c0479k.contains("dribbble.com") ? 30 : c0479k.contains("flickr.com") ? 31 : c0479k.contains("reddit.com") ? 32 : c0479k.contains("slack.com") ? 33 : c0479k.contains("snapchat.com") ? 34 : c0479k.contains("soundcloud.com") ? 35 : c0479k.contains("steamcommunity.com") ? 36 : c0479k.contains("twitch.tv") ? 37 : c0479k.contains("youtu.be") ? 26 : c0479k.contains("vimeo.com") ? 27 : c0479k.contains("dai.ly") ? 28 : 3;
            }
            if (a.equals("")) {
                try {
                    a = m2016i();
                    if (a.length() > 4) {
                        i = a.substring(0, 4).equals("sms:") ? 8 : i2;
                        try {
                            if (a.substring(0, 4).equals("geo:")) {
                                i = 12;
                            }
                            if (a.length() > 10 && r1 == 12 && a.contains("?q=")) {
                                i = 38;
                            }
                        } catch (UnsupportedEncodingException e2) {
                            e = e2;
                            e.printStackTrace();
                            i2 = i;
                            return i2 == 1 ? 3 : i2;
                        }
                    } else {
                        i = i2;
                    }
                    if (a.length() > 6 && a.substring(0, 6).equals("skype:")) {
                        i = 24;
                    }
                    if (a.length() > 8 && a.substring(0, 8).equals("bitcoin:")) {
                        i = 29;
                    }
                    if (a.length() > 10 && a.substring(0, 10).equals("geo:0,0?q=")) {
                        i = 13;
                    }
                    i2 = (a.length() <= 20 || !a.substring(0, 20).equals("google.navigation:q=")) ? i : 39;
                    if (a.length() > 23 && a.substring(0, 23).equals("google.streetview:cbll=")) {
                        i2 = 40;
                    }
                } catch (UnsupportedEncodingException e3) {
                    UnsupportedEncodingException unsupportedEncodingException = e3;
                    i = i2;
                    e = unsupportedEncodingException;
                    e.printStackTrace();
                    i2 = i;
                    if (i2 == 1) {
                    }
                }
            }
        }
        if (i2 == 1) {
        }
    }

    public String m2018k() {
        String str;
        UnsupportedEncodingException unsupportedEncodingException;
        String str2 = "";
        if ((short) 3 == this.f1054c) {
            str2 = "URI";
        }
        if ((short) 4 == this.f1054c) {
            str2 = "External";
        }
        if ((short) 2 == this.f1054c) {
            str2 = C0481m.m2027a(this.f1053b) != null ? "Task" : "Media";
        }
        if (this.f1054c == (short) 0) {
            str2 = "Empty";
        }
        if ((short) 5 == this.f1054c) {
            str2 = "Unknow";
        }
        if ((short) 1 != this.f1054c) {
            return str2;
        }
        if ("text/plain".equals(this.f1053b)) {
            str2 = m2014g() + " (" + m2015h() + ")";
        }
        if (!Arrays.equals(m2008c(), NdefRecord.RTD_URI)) {
            return str2;
        }
        str2 = C0479k.m1997a(this.f1052a[0]);
        if (!str2.equals("")) {
            return str2;
        }
        try {
            String i = m2016i();
            if (i.length() > 4) {
                str = i.substring(0, 4).equals("sms:") ? "SMS" : str2;
                try {
                    if (i.substring(0, 4).equals("geo:")) {
                        str = "GEO";
                    }
                    if (i.length() > 10 && i.substring(0, 4).equals("geo:") && i.contains("?q=")) {
                        str = "GEO SEARCH";
                    }
                } catch (UnsupportedEncodingException e) {
                    UnsupportedEncodingException unsupportedEncodingException2 = e;
                    str2 = str;
                    unsupportedEncodingException = unsupportedEncodingException2;
                    unsupportedEncodingException.printStackTrace();
                    return str2;
                }
            }
            str = str2;
            if (i.length() > 8 && i.substring(0, 8).equals("bitcoin:")) {
                str = "Bitcoin";
            }
            if (i.length() > 10 && i.substring(0, 10).equals("geo:0,0?q=")) {
                str = "GEO";
            }
            str2 = (i.length() <= 20 || !i.substring(0, 20).equals("google.navigation:q=")) ? str : "Google Navigation";
            return (i.length() <= 23 || !i.substring(0, 23).equals("google.streetview:cbll=")) ? str2 : "Google Street View";
        } catch (UnsupportedEncodingException e2) {
            unsupportedEncodingException = e2;
            unsupportedEncodingException.printStackTrace();
            return str2;
        }
    }

    public Uri m2019l() {
        if ((short) 1 != this.f1054c) {
            return (short) 3 == this.f1054c ? Uri.parse(m2012e("UTF-8")) : null;
        } else {
            String str;
            String str2 = "";
            try {
                str = new String(m1998a(C0479k.m1997a(this.f1052a[0]).getBytes(Charset.forName("UTF-8")), Arrays.copyOfRange(this.f1052a, 1, this.f1052a.length)), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                str = str2;
            }
            return Uri.parse(str);
        }
    }

    public String m2020m() {
        if (this.f1052a.length < 8) {
            return "";
        }
        char[] toCharArray = "0123456789ABCDEF".toCharArray();
        String str = "";
        for (int length = this.f1052a.length - 1; length >= 2; length--) {
            int i = this.f1052a[length] & 255;
            str = str + new String(new char[]{toCharArray[i >>> 4], toCharArray[i & 15]});
            if (length > 2) {
                str = str + ":";
            }
        }
        return str;
    }

    public HashMap<String, String> m2021n() {
        HashMap<String, String> hashMap = new HashMap();
        String str = "1045";
        str = "1027";
        String a = C0489d.m2063a(this.f1052a);
        int indexOf = a.indexOf("1045");
        if (indexOf > 0) {
            int indexOf2 = a.indexOf("1027");
            if (indexOf2 > 0) {
                indexOf += "1045".length();
                indexOf2 += "1027".length();
                try {
                    int f = C0489d.m2073f(a.substring(indexOf, indexOf + 4));
                    if (f > 32) {
                        return null;
                    }
                    try {
                        int f2 = C0489d.m2073f(a.substring(indexOf2, indexOf2 + 4));
                        if (f2 > 64) {
                            return null;
                        }
                        int i = 4 + indexOf2;
                        try {
                            int i2 = (indexOf + 4) / 2;
                            a = new String(Arrays.copyOfRange(this.f1052a, i2, i2 + f));
                            i /= 2;
                            String str2 = new String(Arrays.copyOfRange(this.f1052a, i, f2 + i));
                            hashMap.put("SSID", a);
                            hashMap.put("NETWORKPWD", str2);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            return null;
                        }
                    } catch (IndexOutOfBoundsException e2) {
                        return null;
                    }
                } catch (IndexOutOfBoundsException e3) {
                    return null;
                }
            }
        }
        return hashMap;
    }

    public String toString() {
        return m2012e("UTF-8");
    }
}
