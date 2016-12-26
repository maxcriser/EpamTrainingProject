package com.wakdev.libs.p015a;

import android.content.Context;
import android.nfc.NdefRecord;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Build.VERSION;
import android.util.Log;
import com.wakdev.libs.commons.C0489d;
import com.wakdev.libs.core.WDCore;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* renamed from: com.wakdev.libs.a.j */
public class C0478j {
    private Ndef f1041a;
    private NdefFormatable f1042b;
    private Tag f1043c;
    private ArrayList<C0479k> f1044d;
    private int f1045e;
    private int f1046f;
    private boolean f1047g;
    private boolean f1048h;
    private boolean f1049i;
    private NfcA f1050j;
    private HashMap<String, String> f1051k;

    public C0478j(Tag tag, boolean z) {
        this.f1048h = false;
        this.f1049i = false;
        this.f1051k = new HashMap();
        m1965a(tag);
        this.f1046f = 0;
        this.f1045e = 0;
        this.f1047g = z;
        this.f1048h = false;
        this.f1044d = new ArrayList();
    }

    private boolean m1930N() {
        return m1937d(50);
    }

    private void m1931O() {
        if (this.f1050j != null && this.f1050j.isConnected()) {
            try {
                this.f1050j.close();
            } catch (Exception e) {
            }
        }
    }

    private boolean m1932P() {
        if (m1930N()) {
            Object obj = -1;
            try {
                String a = C0489d.m2063a(m1938d(new byte[]{(byte) 48, (byte) 3}));
                if (!(a == null || a.isEmpty())) {
                    if (a.startsWith("E1101200")) {
                        if (m1938d(new byte[]{(byte) 96}) != null) {
                            obj = 4;
                        } else {
                            int i = 1;
                        }
                    }
                    if (a.startsWith("E1101000")) {
                        obj = 3;
                    }
                }
                switch (obj) {
                    case C0627j.View_paddingStart /*1*/:
                        if (!m1941e(new byte[]{(byte) -94, (byte) 4, (byte) 1, (byte) 3, (byte) -96, (byte) 16}) || !m1941e(new byte[]{(byte) -94, (byte) 5, (byte) 68, (byte) 3, (byte) 3, (byte) -48})) {
                            return false;
                        }
                        if (!m1941e(new byte[]{(byte) -94, (byte) 6, (byte) 0, (byte) 0, (byte) -2, (byte) 0})) {
                            return false;
                        }
                        break;
                    case C0627j.Toolbar_subtitle /*3*/:
                        if (!m1941e(new byte[]{(byte) -94, (byte) 4, (byte) 1, (byte) 3, (byte) -112, (byte) 10}) || !m1941e(new byte[]{(byte) -94, (byte) 5, (byte) 52, (byte) 3, (byte) 3, (byte) -48})) {
                            return false;
                        }
                        if (!m1941e(new byte[]{(byte) -94, (byte) 6, (byte) 0, (byte) 0, (byte) -2, (byte) 0})) {
                            return false;
                        }
                        break;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        if (!m1941e(new byte[]{(byte) -94, (byte) 4, (byte) 1, (byte) 3, (byte) -96, (byte) 12}) || !m1941e(new byte[]{(byte) -94, (byte) 5, (byte) 52, (byte) 3, (byte) 3, (byte) -48})) {
                            return false;
                        }
                        if (!m1941e(new byte[]{(byte) -94, (byte) 6, (byte) 0, (byte) 0, (byte) -2, (byte) 0})) {
                            return false;
                        }
                        break;
                    default:
                        if (!(m1941e(new byte[]{(byte) -94, (byte) 4, (byte) 3, (byte) 3, (byte) -48, (byte) 0}) && m1941e(new byte[]{(byte) -94, (byte) 5, (byte) 0, (byte) -2, (byte) 0, (byte) 0}))) {
                            return false;
                        }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.e("NFCTools", "Error : can't connect NfcA");
        return false;
    }

    private boolean m1933Q() {
        if (m1930N()) {
            try {
                byte[] bArr = new byte[]{(byte) 84, (byte) 1, (byte) -31, (byte) 17, (byte) 63, (byte) 0, (byte) 1, (byte) 3, (byte) -14, (byte) 48};
                byte[] bArr2 = new byte[]{(byte) 84, (byte) 2, (byte) 51, (byte) 2, (byte) 3, (byte) -16, (byte) 2, (byte) 3, (byte) 3, (byte) 3};
                byte[] bArr3 = new byte[]{(byte) 84, (byte) 3, (byte) -48, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
                byte[] n = m1984n();
                return m1941e(C0489d.m2065a(bArr, n)) && m1941e(C0489d.m2065a(bArr2, n)) && m1941e(C0489d.m2065a(bArr3, n));
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        Log.e("NFCTools", "Error : can't connect NfcA");
        return false;
    }

    private String m1934a(String str, String str2, String str3, String str4) {
        return C0480l.m2023a(str, str2, str3, str4);
    }

    public static byte[] m1935c(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private String m1936d(String str) {
        return C0480l.m2022a(str);
    }

    private boolean m1937d(int i) {
        if (this.f1050j != null && this.f1050j.isConnected()) {
            return true;
        }
        if (this.f1043c == null) {
            return false;
        }
        try {
            this.f1050j = NfcA.get(this.f1043c);
            this.f1050j.connect();
            this.f1050j.setTimeout(i);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private byte[] m1938d(byte[] bArr) {
        byte[] bArr2 = null;
        if (this.f1050j != null && this.f1050j.isConnected()) {
            try {
                bArr2 = this.f1050j.transceive(bArr);
            } catch (Exception e) {
            }
        }
        return bArr2;
    }

    private String m1939e(String str) {
        return C0480l.m2025b(str);
    }

    private HashMap<String, byte[]> m1940e(int i) {
        HashMap<String, byte[]> hashMap;
        switch (i) {
            case C0627j.View_paddingStart /*1*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 1, (byte) 3, (byte) -96, (byte) 16, (byte) 68, (byte) 3, (byte) 0, (byte) -2});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 39});
                hashMap.put("kBytesPerPage", new byte[]{4});
                return hashMap;
            case C0627j.View_paddingEnd /*2*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 3, (byte) 0, (byte) -2, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 15});
                hashMap.put("kBytesPerPage", new byte[]{4});
                hashMap.put("kAuthSector", new byte[]{(byte) 16});
                hashMap.put("kPasswordSector", new byte[]{(byte) 18});
                hashMap.put("kAuthCMD", new byte[]{(byte) 27});
                hashMap.put("kDefaultPassword", new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1});
                hashMap.put("kAuthSet", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kAuthUnset", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) -1});
                hashMap.put("kDefaultAuth", new byte[]{(byte) -1});
                return hashMap;
            case C0627j.Toolbar_subtitle /*3*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 1, (byte) 3, (byte) -112, (byte) 10, (byte) 52, (byte) 3, (byte) 0, (byte) -2});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 35});
                hashMap.put("kBytesPerPage", new byte[]{4});
                hashMap.put("kAuthSector", new byte[]{(byte) 37});
                hashMap.put("kPasswordSector", new byte[]{(byte) 39});
                hashMap.put("kAuthCMD", new byte[]{(byte) 27});
                hashMap.put("kDefaultPassword", new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1});
                hashMap.put("kAuthSet", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kAuthUnset", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) -1});
                hashMap.put("kDefaultAuth", new byte[]{(byte) -1});
                return hashMap;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 1, (byte) 3, (byte) -96, (byte) 12, (byte) 52, (byte) 3, (byte) 0, (byte) -2});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 39});
                hashMap.put("kBytesPerPage", new byte[]{4});
                hashMap.put("kAuthSector", new byte[]{(byte) 41});
                hashMap.put("kPasswordSector", new byte[]{(byte) 43});
                hashMap.put("kAuthCMD", new byte[]{(byte) 27});
                hashMap.put("kDefaultPassword", new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1});
                hashMap.put("kAuthSet", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kAuthUnset", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) -1});
                hashMap.put("kDefaultAuth", new byte[]{(byte) -1});
                return hashMap;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 3, (byte) 0, (byte) -2, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kOTPOldVersion", new byte[]{(byte) -31, (byte) 16, (byte) 63, (byte) 0});
                hashMap.put("kInitialMemoryValuesOldVersion", new byte[]{(byte) 1, (byte) 3, (byte) -120, (byte) 8, (byte) 102, (byte) 3, (byte) 0, (byte) -2});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) -127});
                hashMap.put("kBytesPerPage", new byte[]{4});
                hashMap.put("kAuthSector", new byte[]{(byte) -125});
                hashMap.put("kPasswordSector", new byte[]{(byte) -123});
                hashMap.put("kAuthCMD", new byte[]{(byte) 27});
                hashMap.put("kDefaultPassword", new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1});
                hashMap.put("kAuthSet", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kAuthUnset", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) -1});
                hashMap.put("kDefaultAuth", new byte[]{(byte) -1});
                return hashMap;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 3, (byte) 0, (byte) -2, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kOTPOldVersion", new byte[]{(byte) -31, (byte) 16, (byte) 111, (byte) 0});
                hashMap.put("kInitialMemoryValuesOldVersion", new byte[]{(byte) 1, (byte) 3, (byte) -24, (byte) 14, (byte) 102, (byte) 3, (byte) 0, (byte) -2});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) -31});
                hashMap.put("kBytesPerPage", new byte[]{4});
                hashMap.put("kAuthSector", new byte[]{(byte) -29});
                hashMap.put("kPasswordSector", new byte[]{(byte) -27});
                hashMap.put("kAuthCMD", new byte[]{(byte) 27});
                hashMap.put("kDefaultPassword", new byte[]{(byte) -1, (byte) -1, (byte) -1, (byte) -1});
                hashMap.put("kAuthSet", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kAuthUnset", new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) -1});
                hashMap.put("kDefaultAuth", new byte[]{(byte) -1});
                return hashMap;
            case C0627j.Toolbar_contentInsetRight /*7*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{(byte) 84});
                hashMap.put("kReadSegCMD", new byte[]{(byte) 16});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) -31, (byte) 17, (byte) 63, (byte) 0, (byte) 1, (byte) 3, (byte) -14, (byte) 48, (byte) 51, (byte) 2, (byte) 3, (byte) -16, (byte) 2, (byte) 3, (byte) 3, (byte) -1});
                hashMap.put("kFirstUserMemorySector", new byte[]{1});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 63});
                hashMap.put("kFormatSkipAds", new byte[]{(byte) 13, (byte) 14, (byte) 15});
                hashMap.put("kBytesPerPage", new byte[]{8});
                hashMap.put("kTotalSeg", new byte[]{4});
                hashMap.put("kTotalBlocksPerSeg", new byte[]{(byte) 16});
                hashMap.put("kAppendToCMD", m1984n());
                return hashMap;
            case C0627j.Toolbar_popupTheme /*8*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 3, (byte) 3, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 39});
                hashMap.put("kBytesPerPage", new byte[]{4});
                return hashMap;
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{-94});
                hashMap.put("kInitialMemoryValues", new byte[]{(byte) 3, (byte) 0, (byte) -2, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0});
                hashMap.put("kFirstUserMemorySector", new byte[]{4});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 15});
                hashMap.put("kBytesPerPage", new byte[]{4});
                return hashMap;
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{(byte) -96});
                hashMap.put("kFirstUserMemorySector", new byte[]{1});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) 63});
                hashMap.put("kBytesPerPage", new byte[]{(byte) 16});
                hashMap.put("kFormatSkipAds", new byte[]{(byte) 0, (byte) 3, (byte) 7, (byte) 11, (byte) 15, (byte) 19, (byte) 23, (byte) 27, (byte) 31, (byte) 35, (byte) 39, (byte) 43, (byte) 47, (byte) 51, (byte) 55, (byte) 59, (byte) 63});
                hashMap.put("kAuthSector", new byte[]{null});
                hashMap.put("kPasswordSector", new byte[]{null});
                hashMap.put("kAuthCMD", new byte[]{null});
                hashMap.put("kDefaultPassword", new byte[]{null});
                hashMap.put("kDefaultAuth", new byte[]{null});
                hashMap.put("kAuthSet", new byte[]{(byte) 120, (byte) 119, (byte) -120, (byte) 64});
                hashMap.put("kAuthUnset", new byte[]{Byte.MAX_VALUE, (byte) 7, (byte) -120, (byte) 64});
                return hashMap;
            case C0627j.Toolbar_titleMargins /*11*/:
                hashMap = new HashMap();
                hashMap.put("kWriteCMD", new byte[]{(byte) -96});
                hashMap.put("kFirstUserMemorySector", new byte[]{1});
                hashMap.put("kLastUserMemorySector", new byte[]{(byte) -1});
                hashMap.put("kBytesPerPage", new byte[]{(byte) 16});
                hashMap.put("kFormatSkipAds", new byte[]{(byte) 0, (byte) 3, (byte) 7, (byte) 11, (byte) 15, (byte) 19, (byte) 23, (byte) 27, (byte) 31, (byte) 35, (byte) 39, (byte) 43, (byte) 47, (byte) 51, (byte) 55, (byte) 59, (byte) 63, (byte) 67, (byte) 71, (byte) 75, (byte) 79, (byte) 83, (byte) 87, (byte) 91, (byte) 95, (byte) 99, (byte) 103, (byte) 107, (byte) 111, (byte) 115, (byte) 119, (byte) 123, Byte.MAX_VALUE, (byte) -113, (byte) -97, (byte) -81, (byte) -65, (byte) -49, (byte) -33, (byte) -17, (byte) -1});
                hashMap.put("kAuthSector", new byte[]{null});
                hashMap.put("kPasswordSector", new byte[]{null});
                hashMap.put("kAuthCMD", new byte[]{null});
                hashMap.put("kDefaultPassword", new byte[]{null});
                hashMap.put("kDefaultAuth", new byte[]{null});
                hashMap.put("kAuthSet", new byte[]{(byte) 120, (byte) 119, (byte) -120, (byte) 64});
                hashMap.put("kAuthUnset", new byte[]{Byte.MAX_VALUE, (byte) 7, (byte) -120, (byte) 64});
                return hashMap;
            default:
                return null;
        }
    }

    private boolean m1941e(byte[] bArr) {
        if (this.f1050j == null || !this.f1050j.isConnected()) {
            return false;
        }
        try {
            this.f1050j.transceive(bArr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static String m1942f(byte[] bArr) {
        String str = "";
        if (bArr != null) {
            int length = bArr.length;
            int i = 0;
            while (i < length) {
                Byte valueOf = Byte.valueOf(bArr[i]);
                i++;
                str = str + String.format("%02X", new Object[]{Integer.valueOf(valueOf.intValue() & 255)});
            }
        }
        return str;
    }

    private static byte[] m1943g(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length - 1;
        for (int i = 0; length > i; i++) {
            byte b = bArr[length];
            bArr[length] = bArr[i];
            bArr[i] = b;
            length--;
        }
        return bArr;
    }

    public String m1944A() {
        byte[] z = m1996z();
        if (z != null) {
            String f = C0478j.m1942f(z);
            if (!(f == null || f.isEmpty())) {
                return f;
            }
        }
        return null;
    }

    public String m1945B() {
        byte[] p = m1986p();
        if (p != null) {
            String f = C0478j.m1942f(C0478j.m1943g(p));
            if (!(f == null || f.isEmpty())) {
                return "0x" + f;
            }
        }
        return null;
    }

    public String m1946C() {
        short q = m1987q();
        if (q != (short) -1) {
            String toHexString = Integer.toHexString(q & 65535);
            if (!(toHexString == null || toHexString.isEmpty())) {
                if (toHexString.length() == 1) {
                    toHexString = "0" + toHexString;
                }
                return "0x" + toHexString;
            }
        }
        return null;
    }

    public String m1947D() {
        byte[] r = m1988r();
        if (r != null) {
            String f = C0478j.m1942f(r);
            if (!(f == null || f.isEmpty())) {
                return "0x" + f;
            }
        }
        return null;
    }

    public String m1948E() {
        byte[] y = m1995y();
        if (y != null) {
            String f = C0478j.m1942f(y);
            if (!(f == null || f.isEmpty())) {
                return "0x" + f;
            }
        }
        return null;
    }

    public String m1949F() {
        String m = m1983m();
        String str = "";
        if ("org.nfcforum.ndef.type1".equals(m)) {
            str = "NFC Forum Type 1";
        }
        if ("org.nfcforum.ndef.type2".equals(m)) {
            str = "NFC Forum Type 2";
        }
        if ("org.nfcforum.ndef.type3".equals(m)) {
            str = "NFC Forum Type 3";
        }
        if ("org.nfcforum.ndef.type4".equals(m)) {
            str = "NFC Forum Type 4";
        }
        return "com.nxp.ndef.mifareclassic".equals(m) ? "NXP Mifare Classic" : str;
    }

    public int m1950G() {
        return this.f1045e;
    }

    public String[] m1951H() {
        return this.f1043c.getTechList();
    }

    public String m1952I() {
        String str = "";
        String[] H = m1951H();
        if (Arrays.asList(H).contains(NfcA.class.getName())) {
            str = "ISO 14443-3A";
        }
        if (Arrays.asList(H).contains(NfcB.class.getName())) {
            str = "ISO 14443-3B";
        }
        if (Arrays.asList(H).contains(NfcF.class.getName())) {
            str = "JIS 6319-4";
        }
        if (Arrays.asList(H).contains(NfcV.class.getName())) {
            str = "ISO 15693";
        }
        if (Arrays.asList(H).contains(IsoDep.class.getName())) {
            str = "ISO 14443-4";
        }
        return str.isEmpty() ? "Unknow ISO" : str;
    }

    public int m1953J() {
        Object obj;
        Object obj2;
        Object f;
        MifareClassic mifareClassic;
        int type;
        int size;
        MifareUltralight mifareUltralight;
        int type2;
        Object E;
        byte[] d;
        byte[] d2;
        NfcF nfcF;
        String f2;
        String[] H = m1951H();
        boolean contains = Arrays.asList(H).contains(NfcA.class.getName());
        boolean contains2 = Arrays.asList(H).contains(NfcF.class.getName());
        Arrays.asList(H).contains(NfcV.class.getName());
        boolean contains3 = Arrays.asList(H).contains(MifareClassic.class.getName());
        boolean contains4 = Arrays.asList(H).contains(MifareUltralight.class.getName());
        boolean contains5 = Arrays.asList(H).contains(IsoDep.class.getName());
        boolean contains6 = Arrays.asList(H).contains(Ndef.class.getName());
        Object obj3 = null;
        if (contains) {
            NfcA nfcA = null;
            try {
                nfcA = NfcA.get(this.f1043c);
            } catch (Exception e) {
            }
            if (nfcA != null) {
                byte[] atqa = nfcA.getAtqa();
                String f3 = C0478j.m1942f(new byte[]{atqa[1], atqa[0]});
                String f4 = C0478j.m1942f(new byte[]{(byte) nfcA.getSak()});
                if (contains5) {
                    IsoDep isoDep = IsoDep.get(this.f1043c);
                    if (isoDep != null) {
                        obj = f3;
                        obj2 = f4;
                        f = C0478j.m1942f(isoDep.getHistoricalBytes());
                        if (contains3) {
                            try {
                                mifareClassic = MifareClassic.get(this.f1043c);
                                type = mifareClassic.getType();
                                size = mifareClassic.getSize();
                            } catch (Exception e2) {
                                return -1;
                            }
                        }
                        type = -1;
                        size = -1;
                        if (contains4) {
                            mifareUltralight = null;
                            try {
                                mifareUltralight = MifareUltralight.get(this.f1043c);
                            } catch (Exception e3) {
                            }
                            if (mifareUltralight != null) {
                                type2 = mifareUltralight.getType();
                                E = m1948E();
                                if (contains6) {
                                    obj3 = m1954K();
                                }
                                if (contains4) {
                                    if (type2 != 1 && (("0044".equals(r8) || "4400".equals(r8)) && "00".equals(r7) && "NTAG200".equals(r1))) {
                                        return 9;
                                    }
                                    if (type2 == 2 && (("0044".equals(r8) || "4400".equals(r8)) && "00".equals(r7) && "NTAG203".equals(r1) && contains)) {
                                        try {
                                            if (!m1930N()) {
                                                throw new Exception();
                                            } else if (this.f1050j != null) {
                                                throw new Exception();
                                            } else {
                                                d = m1938d(new byte[]{(byte) 48, (byte) 0});
                                                d2 = m1938d(new byte[]{(byte) 48, (byte) 41});
                                                if (d == null && d2 != null) {
                                                    return Arrays.equals(Arrays.copyOfRange(d, 0, d.length + -5), Arrays.copyOfRange(d2, 4, d2.length + -1)) ? 1 : 8;
                                                } else {
                                                    m1931O();
                                                }
                                            }
                                        } catch (Exception e4) {
                                            return 1;
                                        }
                                    }
                                    if (type2 != 1 && (("0044".equals(r8) || "4400".equals(r8)) && "00".equals(r7) && "NTAG210".equals(r1))) {
                                        return 2;
                                    }
                                    if (type2 != 2 && (("0044".equals(r8) || "4400".equals(r8)) && "00".equals(r7) && "NTAG212".equals(r1))) {
                                        return 3;
                                    }
                                    if (type2 != 2 && (("0044".equals(r8) || "4400".equals(r8)) && "00".equals(r7) && "NTAG213".equals(r1))) {
                                        return 4;
                                    }
                                    if (type2 != 2 && (("0044".equals(r8) || "4400".equals(r8)) && "00".equals(r7) && "NTAG215".equals(r1))) {
                                        return 5;
                                    }
                                    if (type2 == 2 && (("0044".equals(r8) || "4400".equals(r8)) && "00".equals(r7) && "NTAG216".equals(r1))) {
                                        return 6;
                                    }
                                }
                                if (contains3) {
                                    if (type != 0 && r4 == 1024) {
                                        return 10;
                                    }
                                    if (type == 0 && r4 == 4096) {
                                        return 11;
                                    }
                                }
                                if (!contains && "0x124C".equals(r2) && "0C00".equals(r8) && "00".equals(r7)) {
                                    return 7;
                                }
                                if (!contains && "0002".equals(r8) && "20".equals(r7) && "C1052F2F01BCD6".equals(r6)) {
                                    return 27;
                                }
                                if (!contains && "0044".equals(r8) && "20".equals(r7) && "C1052F2F01BCD6".equals(r6)) {
                                    return 28;
                                }
                                if (!contains && "0002".equals(r8) && "20".equals(r7) && "C1052F2F0035C7".equals(r6)) {
                                    return 29;
                                }
                                if (!contains && "0004".equals(r8) && "20".equals(r7) && "C1052F2F0035C7".equals(r6)) {
                                    return 30;
                                }
                                if (!contains && "0004".equals(r8) && "08".equals(r7)) {
                                    return 10;
                                }
                                if (!contains && "0002".equals(r8) && "18".equals(r7)) {
                                    return 11;
                                }
                                if (contains2) {
                                    try {
                                        nfcF = NfcF.get(this.f1043c);
                                        if (nfcF != null) {
                                            f2 = C0478j.m1942f(m1973c(nfcF.getManufacturer()));
                                            obj3 = -1;
                                            switch (f2.hashCode()) {
                                                case 1537:
                                                    if (f2.equals("01")) {
                                                        obj3 = null;
                                                        break;
                                                    }
                                                    break;
                                                case 1544:
                                                    if (f2.equals("08")) {
                                                        obj3 = 1;
                                                        break;
                                                    }
                                                    break;
                                                case 1545:
                                                    if (f2.equals("09")) {
                                                        obj3 = 2;
                                                        break;
                                                    }
                                                    break;
                                                case 1555:
                                                    if (f2.equals("0C")) {
                                                        obj3 = 3;
                                                        break;
                                                    }
                                                    break;
                                                case 1556:
                                                    if (f2.equals("0D")) {
                                                        obj3 = 4;
                                                        break;
                                                    }
                                                    break;
                                                case 1598:
                                                    if (f2.equals("20")) {
                                                        obj3 = 5;
                                                        break;
                                                    }
                                                    break;
                                                case 1631:
                                                    if (f2.equals("32")) {
                                                        obj3 = 6;
                                                        break;
                                                    }
                                                    break;
                                                case 1634:
                                                    if (f2.equals("35")) {
                                                        obj3 = 7;
                                                        break;
                                                    }
                                                    break;
                                                case 2187:
                                                    if (f2.equals("E0")) {
                                                        obj3 = 13;
                                                        break;
                                                    }
                                                    break;
                                                case 2188:
                                                    if (f2.equals("E1")) {
                                                        obj3 = 11;
                                                        break;
                                                    }
                                                    break;
                                                case 2218:
                                                    if (f2.equals("F0")) {
                                                        obj3 = 8;
                                                        break;
                                                    }
                                                    break;
                                                case 2219:
                                                    if (f2.equals("F1")) {
                                                        obj3 = 9;
                                                        break;
                                                    }
                                                    break;
                                                case 2220:
                                                    if (f2.equals("F2")) {
                                                        obj3 = 10;
                                                        break;
                                                    }
                                                    break;
                                                case 2240:
                                                    if (f2.equals("FF")) {
                                                        obj3 = 12;
                                                        break;
                                                    }
                                                    break;
                                            }
                                            switch (obj3) {
                                                case C0627j.View_android_focusable /*0*/:
                                                    return 13;
                                                case C0627j.View_paddingStart /*1*/:
                                                    return 14;
                                                case C0627j.View_paddingEnd /*2*/:
                                                    return 15;
                                                case C0627j.Toolbar_subtitle /*3*/:
                                                    return 16;
                                                case C0627j.Toolbar_contentInsetStart /*4*/:
                                                    return 17;
                                                case C0627j.Toolbar_contentInsetEnd /*5*/:
                                                    return 18;
                                                case C0627j.Toolbar_contentInsetLeft /*6*/:
                                                    return 19;
                                                case C0627j.Toolbar_contentInsetRight /*7*/:
                                                    return 20;
                                                case C0627j.Toolbar_popupTheme /*8*/:
                                                    return 21;
                                                case C0627j.Toolbar_titleTextAppearance /*9*/:
                                                    return 22;
                                                case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                                                    return 23;
                                                case C0627j.Toolbar_titleMargins /*11*/:
                                                    return 24;
                                                case C0627j.Toolbar_titleMarginStart /*12*/:
                                                    return 25;
                                                case C0627j.Toolbar_titleMarginEnd /*13*/:
                                                    return 26;
                                                default:
                                                    return 12;
                                            }
                                        }
                                    } catch (Exception e5) {
                                    }
                                }
                                return -1;
                            }
                        }
                        type2 = -1;
                        E = m1948E();
                        if (contains6) {
                            obj3 = m1954K();
                        }
                        if (contains4) {
                            if (type2 != 1) {
                            }
                            if (!m1930N()) {
                                throw new Exception();
                            } else if (this.f1050j != null) {
                                d = m1938d(new byte[]{(byte) 48, (byte) 0});
                                d2 = m1938d(new byte[]{(byte) 48, (byte) 41});
                                if (d == null) {
                                }
                                m1931O();
                                if (type2 != 1) {
                                }
                                if (type2 != 2) {
                                }
                                if (type2 != 2) {
                                }
                                if (type2 != 2) {
                                }
                                return 6;
                            } else {
                                throw new Exception();
                            }
                        }
                        if (contains3) {
                            if (type != 0) {
                            }
                            return 11;
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (contains2) {
                            nfcF = NfcF.get(this.f1043c);
                            if (nfcF != null) {
                                f2 = C0478j.m1942f(m1973c(nfcF.getManufacturer()));
                                obj3 = -1;
                                switch (f2.hashCode()) {
                                    case 1537:
                                        if (f2.equals("01")) {
                                            obj3 = null;
                                            break;
                                        }
                                        break;
                                    case 1544:
                                        if (f2.equals("08")) {
                                            obj3 = 1;
                                            break;
                                        }
                                        break;
                                    case 1545:
                                        if (f2.equals("09")) {
                                            obj3 = 2;
                                            break;
                                        }
                                        break;
                                    case 1555:
                                        if (f2.equals("0C")) {
                                            obj3 = 3;
                                            break;
                                        }
                                        break;
                                    case 1556:
                                        if (f2.equals("0D")) {
                                            obj3 = 4;
                                            break;
                                        }
                                        break;
                                    case 1598:
                                        if (f2.equals("20")) {
                                            obj3 = 5;
                                            break;
                                        }
                                        break;
                                    case 1631:
                                        if (f2.equals("32")) {
                                            obj3 = 6;
                                            break;
                                        }
                                        break;
                                    case 1634:
                                        if (f2.equals("35")) {
                                            obj3 = 7;
                                            break;
                                        }
                                        break;
                                    case 2187:
                                        if (f2.equals("E0")) {
                                            obj3 = 13;
                                            break;
                                        }
                                        break;
                                    case 2188:
                                        if (f2.equals("E1")) {
                                            obj3 = 11;
                                            break;
                                        }
                                        break;
                                    case 2218:
                                        if (f2.equals("F0")) {
                                            obj3 = 8;
                                            break;
                                        }
                                        break;
                                    case 2219:
                                        if (f2.equals("F1")) {
                                            obj3 = 9;
                                            break;
                                        }
                                        break;
                                    case 2220:
                                        if (f2.equals("F2")) {
                                            obj3 = 10;
                                            break;
                                        }
                                        break;
                                    case 2240:
                                        if (f2.equals("FF")) {
                                            obj3 = 12;
                                            break;
                                        }
                                        break;
                                }
                                switch (obj3) {
                                    case C0627j.View_android_focusable /*0*/:
                                        return 13;
                                    case C0627j.View_paddingStart /*1*/:
                                        return 14;
                                    case C0627j.View_paddingEnd /*2*/:
                                        return 15;
                                    case C0627j.Toolbar_subtitle /*3*/:
                                        return 16;
                                    case C0627j.Toolbar_contentInsetStart /*4*/:
                                        return 17;
                                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                                        return 18;
                                    case C0627j.Toolbar_contentInsetLeft /*6*/:
                                        return 19;
                                    case C0627j.Toolbar_contentInsetRight /*7*/:
                                        return 20;
                                    case C0627j.Toolbar_popupTheme /*8*/:
                                        return 21;
                                    case C0627j.Toolbar_titleTextAppearance /*9*/:
                                        return 22;
                                    case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                                        return 23;
                                    case C0627j.Toolbar_titleMargins /*11*/:
                                        return 24;
                                    case C0627j.Toolbar_titleMarginStart /*12*/:
                                        return 25;
                                    case C0627j.Toolbar_titleMarginEnd /*13*/:
                                        return 26;
                                    default:
                                        return 12;
                                }
                            }
                        }
                        return -1;
                    }
                }
                String str = f3;
                f3 = f4;
                f = null;
                if (contains3) {
                    type = -1;
                    size = -1;
                } else {
                    mifareClassic = MifareClassic.get(this.f1043c);
                    type = mifareClassic.getType();
                    size = mifareClassic.getSize();
                }
                if (contains4) {
                    mifareUltralight = null;
                    mifareUltralight = MifareUltralight.get(this.f1043c);
                    if (mifareUltralight != null) {
                        type2 = mifareUltralight.getType();
                        E = m1948E();
                        if (contains6) {
                            obj3 = m1954K();
                        }
                        if (contains4) {
                            if (type2 != 1) {
                            }
                            if (!m1930N()) {
                                throw new Exception();
                            } else if (this.f1050j != null) {
                                throw new Exception();
                            } else {
                                d = m1938d(new byte[]{(byte) 48, (byte) 0});
                                d2 = m1938d(new byte[]{(byte) 48, (byte) 41});
                                if (d == null) {
                                }
                                m1931O();
                                if (type2 != 1) {
                                }
                                if (type2 != 2) {
                                }
                                if (type2 != 2) {
                                }
                                if (type2 != 2) {
                                }
                                return 6;
                            }
                        }
                        if (contains3) {
                            if (type != 0) {
                            }
                            return 11;
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (!contains) {
                        }
                        if (contains2) {
                            nfcF = NfcF.get(this.f1043c);
                            if (nfcF != null) {
                                f2 = C0478j.m1942f(m1973c(nfcF.getManufacturer()));
                                obj3 = -1;
                                switch (f2.hashCode()) {
                                    case 1537:
                                        if (f2.equals("01")) {
                                            obj3 = null;
                                            break;
                                        }
                                        break;
                                    case 1544:
                                        if (f2.equals("08")) {
                                            obj3 = 1;
                                            break;
                                        }
                                        break;
                                    case 1545:
                                        if (f2.equals("09")) {
                                            obj3 = 2;
                                            break;
                                        }
                                        break;
                                    case 1555:
                                        if (f2.equals("0C")) {
                                            obj3 = 3;
                                            break;
                                        }
                                        break;
                                    case 1556:
                                        if (f2.equals("0D")) {
                                            obj3 = 4;
                                            break;
                                        }
                                        break;
                                    case 1598:
                                        if (f2.equals("20")) {
                                            obj3 = 5;
                                            break;
                                        }
                                        break;
                                    case 1631:
                                        if (f2.equals("32")) {
                                            obj3 = 6;
                                            break;
                                        }
                                        break;
                                    case 1634:
                                        if (f2.equals("35")) {
                                            obj3 = 7;
                                            break;
                                        }
                                        break;
                                    case 2187:
                                        if (f2.equals("E0")) {
                                            obj3 = 13;
                                            break;
                                        }
                                        break;
                                    case 2188:
                                        if (f2.equals("E1")) {
                                            obj3 = 11;
                                            break;
                                        }
                                        break;
                                    case 2218:
                                        if (f2.equals("F0")) {
                                            obj3 = 8;
                                            break;
                                        }
                                        break;
                                    case 2219:
                                        if (f2.equals("F1")) {
                                            obj3 = 9;
                                            break;
                                        }
                                        break;
                                    case 2220:
                                        if (f2.equals("F2")) {
                                            obj3 = 10;
                                            break;
                                        }
                                        break;
                                    case 2240:
                                        if (f2.equals("FF")) {
                                            obj3 = 12;
                                            break;
                                        }
                                        break;
                                }
                                switch (obj3) {
                                    case C0627j.View_android_focusable /*0*/:
                                        return 13;
                                    case C0627j.View_paddingStart /*1*/:
                                        return 14;
                                    case C0627j.View_paddingEnd /*2*/:
                                        return 15;
                                    case C0627j.Toolbar_subtitle /*3*/:
                                        return 16;
                                    case C0627j.Toolbar_contentInsetStart /*4*/:
                                        return 17;
                                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                                        return 18;
                                    case C0627j.Toolbar_contentInsetLeft /*6*/:
                                        return 19;
                                    case C0627j.Toolbar_contentInsetRight /*7*/:
                                        return 20;
                                    case C0627j.Toolbar_popupTheme /*8*/:
                                        return 21;
                                    case C0627j.Toolbar_titleTextAppearance /*9*/:
                                        return 22;
                                    case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                                        return 23;
                                    case C0627j.Toolbar_titleMargins /*11*/:
                                        return 24;
                                    case C0627j.Toolbar_titleMarginStart /*12*/:
                                        return 25;
                                    case C0627j.Toolbar_titleMarginEnd /*13*/:
                                        return 26;
                                    default:
                                        return 12;
                                }
                            }
                        }
                        return -1;
                    }
                }
                type2 = -1;
                E = m1948E();
                if (contains6) {
                    obj3 = m1954K();
                }
                if (contains4) {
                    if (type2 != 1) {
                    }
                    if (!m1930N()) {
                        throw new Exception();
                    } else if (this.f1050j != null) {
                        d = m1938d(new byte[]{(byte) 48, (byte) 0});
                        d2 = m1938d(new byte[]{(byte) 48, (byte) 41});
                        if (d == null) {
                        }
                        m1931O();
                        if (type2 != 1) {
                        }
                        if (type2 != 2) {
                        }
                        if (type2 != 2) {
                        }
                        if (type2 != 2) {
                        }
                        return 6;
                    } else {
                        throw new Exception();
                    }
                }
                if (contains3) {
                    if (type != 0) {
                    }
                    return 11;
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (contains2) {
                    nfcF = NfcF.get(this.f1043c);
                    if (nfcF != null) {
                        f2 = C0478j.m1942f(m1973c(nfcF.getManufacturer()));
                        obj3 = -1;
                        switch (f2.hashCode()) {
                            case 1537:
                                if (f2.equals("01")) {
                                    obj3 = null;
                                    break;
                                }
                                break;
                            case 1544:
                                if (f2.equals("08")) {
                                    obj3 = 1;
                                    break;
                                }
                                break;
                            case 1545:
                                if (f2.equals("09")) {
                                    obj3 = 2;
                                    break;
                                }
                                break;
                            case 1555:
                                if (f2.equals("0C")) {
                                    obj3 = 3;
                                    break;
                                }
                                break;
                            case 1556:
                                if (f2.equals("0D")) {
                                    obj3 = 4;
                                    break;
                                }
                                break;
                            case 1598:
                                if (f2.equals("20")) {
                                    obj3 = 5;
                                    break;
                                }
                                break;
                            case 1631:
                                if (f2.equals("32")) {
                                    obj3 = 6;
                                    break;
                                }
                                break;
                            case 1634:
                                if (f2.equals("35")) {
                                    obj3 = 7;
                                    break;
                                }
                                break;
                            case 2187:
                                if (f2.equals("E0")) {
                                    obj3 = 13;
                                    break;
                                }
                                break;
                            case 2188:
                                if (f2.equals("E1")) {
                                    obj3 = 11;
                                    break;
                                }
                                break;
                            case 2218:
                                if (f2.equals("F0")) {
                                    obj3 = 8;
                                    break;
                                }
                                break;
                            case 2219:
                                if (f2.equals("F1")) {
                                    obj3 = 9;
                                    break;
                                }
                                break;
                            case 2220:
                                if (f2.equals("F2")) {
                                    obj3 = 10;
                                    break;
                                }
                                break;
                            case 2240:
                                if (f2.equals("FF")) {
                                    obj3 = 12;
                                    break;
                                }
                                break;
                        }
                        switch (obj3) {
                            case C0627j.View_android_focusable /*0*/:
                                return 13;
                            case C0627j.View_paddingStart /*1*/:
                                return 14;
                            case C0627j.View_paddingEnd /*2*/:
                                return 15;
                            case C0627j.Toolbar_subtitle /*3*/:
                                return 16;
                            case C0627j.Toolbar_contentInsetStart /*4*/:
                                return 17;
                            case C0627j.Toolbar_contentInsetEnd /*5*/:
                                return 18;
                            case C0627j.Toolbar_contentInsetLeft /*6*/:
                                return 19;
                            case C0627j.Toolbar_contentInsetRight /*7*/:
                                return 20;
                            case C0627j.Toolbar_popupTheme /*8*/:
                                return 21;
                            case C0627j.Toolbar_titleTextAppearance /*9*/:
                                return 22;
                            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                                return 23;
                            case C0627j.Toolbar_titleMargins /*11*/:
                                return 24;
                            case C0627j.Toolbar_titleMarginStart /*12*/:
                                return 25;
                            case C0627j.Toolbar_titleMarginEnd /*13*/:
                                return 26;
                            default:
                                return 12;
                        }
                    }
                }
                return -1;
            }
        }
        obj = null;
        obj2 = null;
        f = null;
        if (contains3) {
            mifareClassic = MifareClassic.get(this.f1043c);
            type = mifareClassic.getType();
            size = mifareClassic.getSize();
        } else {
            type = -1;
            size = -1;
        }
        if (contains4) {
            mifareUltralight = null;
            mifareUltralight = MifareUltralight.get(this.f1043c);
            if (mifareUltralight != null) {
                type2 = mifareUltralight.getType();
                E = m1948E();
                if (contains6) {
                    obj3 = m1954K();
                }
                if (contains4) {
                    if (type2 != 1) {
                    }
                    if (!m1930N()) {
                        throw new Exception();
                    } else if (this.f1050j != null) {
                        throw new Exception();
                    } else {
                        d = m1938d(new byte[]{(byte) 48, (byte) 0});
                        d2 = m1938d(new byte[]{(byte) 48, (byte) 41});
                        if (d == null) {
                        }
                        m1931O();
                        if (type2 != 1) {
                        }
                        if (type2 != 2) {
                        }
                        if (type2 != 2) {
                        }
                        if (type2 != 2) {
                        }
                        return 6;
                    }
                }
                if (contains3) {
                    if (type != 0) {
                    }
                    return 11;
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (!contains) {
                }
                if (contains2) {
                    nfcF = NfcF.get(this.f1043c);
                    if (nfcF != null) {
                        f2 = C0478j.m1942f(m1973c(nfcF.getManufacturer()));
                        obj3 = -1;
                        switch (f2.hashCode()) {
                            case 1537:
                                if (f2.equals("01")) {
                                    obj3 = null;
                                    break;
                                }
                                break;
                            case 1544:
                                if (f2.equals("08")) {
                                    obj3 = 1;
                                    break;
                                }
                                break;
                            case 1545:
                                if (f2.equals("09")) {
                                    obj3 = 2;
                                    break;
                                }
                                break;
                            case 1555:
                                if (f2.equals("0C")) {
                                    obj3 = 3;
                                    break;
                                }
                                break;
                            case 1556:
                                if (f2.equals("0D")) {
                                    obj3 = 4;
                                    break;
                                }
                                break;
                            case 1598:
                                if (f2.equals("20")) {
                                    obj3 = 5;
                                    break;
                                }
                                break;
                            case 1631:
                                if (f2.equals("32")) {
                                    obj3 = 6;
                                    break;
                                }
                                break;
                            case 1634:
                                if (f2.equals("35")) {
                                    obj3 = 7;
                                    break;
                                }
                                break;
                            case 2187:
                                if (f2.equals("E0")) {
                                    obj3 = 13;
                                    break;
                                }
                                break;
                            case 2188:
                                if (f2.equals("E1")) {
                                    obj3 = 11;
                                    break;
                                }
                                break;
                            case 2218:
                                if (f2.equals("F0")) {
                                    obj3 = 8;
                                    break;
                                }
                                break;
                            case 2219:
                                if (f2.equals("F1")) {
                                    obj3 = 9;
                                    break;
                                }
                                break;
                            case 2220:
                                if (f2.equals("F2")) {
                                    obj3 = 10;
                                    break;
                                }
                                break;
                            case 2240:
                                if (f2.equals("FF")) {
                                    obj3 = 12;
                                    break;
                                }
                                break;
                        }
                        switch (obj3) {
                            case C0627j.View_android_focusable /*0*/:
                                return 13;
                            case C0627j.View_paddingStart /*1*/:
                                return 14;
                            case C0627j.View_paddingEnd /*2*/:
                                return 15;
                            case C0627j.Toolbar_subtitle /*3*/:
                                return 16;
                            case C0627j.Toolbar_contentInsetStart /*4*/:
                                return 17;
                            case C0627j.Toolbar_contentInsetEnd /*5*/:
                                return 18;
                            case C0627j.Toolbar_contentInsetLeft /*6*/:
                                return 19;
                            case C0627j.Toolbar_contentInsetRight /*7*/:
                                return 20;
                            case C0627j.Toolbar_popupTheme /*8*/:
                                return 21;
                            case C0627j.Toolbar_titleTextAppearance /*9*/:
                                return 22;
                            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                                return 23;
                            case C0627j.Toolbar_titleMargins /*11*/:
                                return 24;
                            case C0627j.Toolbar_titleMarginStart /*12*/:
                                return 25;
                            case C0627j.Toolbar_titleMarginEnd /*13*/:
                                return 26;
                            default:
                                return 12;
                        }
                    }
                }
                return -1;
            }
        }
        type2 = -1;
        try {
            E = m1948E();
        } catch (Exception e6) {
            E = null;
        }
        if (contains6) {
            obj3 = m1954K();
        }
        if (contains4) {
            if (type2 != 1) {
            }
            if (!m1930N()) {
                throw new Exception();
            } else if (this.f1050j != null) {
                d = m1938d(new byte[]{(byte) 48, (byte) 0});
                d2 = m1938d(new byte[]{(byte) 48, (byte) 41});
                if (d == null) {
                }
                m1931O();
                if (type2 != 1) {
                }
                if (type2 != 2) {
                }
                if (type2 != 2) {
                }
                if (type2 != 2) {
                }
                return 6;
            } else {
                throw new Exception();
            }
        }
        if (contains3) {
            if (type != 0) {
            }
            return 11;
        }
        if (!contains) {
        }
        if (!contains) {
        }
        if (!contains) {
        }
        if (!contains) {
        }
        if (!contains) {
        }
        if (!contains) {
        }
        if (!contains) {
        }
        if (contains2) {
            nfcF = NfcF.get(this.f1043c);
            if (nfcF != null) {
                f2 = C0478j.m1942f(m1973c(nfcF.getManufacturer()));
                obj3 = -1;
                switch (f2.hashCode()) {
                    case 1537:
                        if (f2.equals("01")) {
                            obj3 = null;
                            break;
                        }
                        break;
                    case 1544:
                        if (f2.equals("08")) {
                            obj3 = 1;
                            break;
                        }
                        break;
                    case 1545:
                        if (f2.equals("09")) {
                            obj3 = 2;
                            break;
                        }
                        break;
                    case 1555:
                        if (f2.equals("0C")) {
                            obj3 = 3;
                            break;
                        }
                        break;
                    case 1556:
                        if (f2.equals("0D")) {
                            obj3 = 4;
                            break;
                        }
                        break;
                    case 1598:
                        if (f2.equals("20")) {
                            obj3 = 5;
                            break;
                        }
                        break;
                    case 1631:
                        if (f2.equals("32")) {
                            obj3 = 6;
                            break;
                        }
                        break;
                    case 1634:
                        if (f2.equals("35")) {
                            obj3 = 7;
                            break;
                        }
                        break;
                    case 2187:
                        if (f2.equals("E0")) {
                            obj3 = 13;
                            break;
                        }
                        break;
                    case 2188:
                        if (f2.equals("E1")) {
                            obj3 = 11;
                            break;
                        }
                        break;
                    case 2218:
                        if (f2.equals("F0")) {
                            obj3 = 8;
                            break;
                        }
                        break;
                    case 2219:
                        if (f2.equals("F1")) {
                            obj3 = 9;
                            break;
                        }
                        break;
                    case 2220:
                        if (f2.equals("F2")) {
                            obj3 = 10;
                            break;
                        }
                        break;
                    case 2240:
                        if (f2.equals("FF")) {
                            obj3 = 12;
                            break;
                        }
                        break;
                }
                switch (obj3) {
                    case C0627j.View_android_focusable /*0*/:
                        return 13;
                    case C0627j.View_paddingStart /*1*/:
                        return 14;
                    case C0627j.View_paddingEnd /*2*/:
                        return 15;
                    case C0627j.Toolbar_subtitle /*3*/:
                        return 16;
                    case C0627j.Toolbar_contentInsetStart /*4*/:
                        return 17;
                    case C0627j.Toolbar_contentInsetEnd /*5*/:
                        return 18;
                    case C0627j.Toolbar_contentInsetLeft /*6*/:
                        return 19;
                    case C0627j.Toolbar_contentInsetRight /*7*/:
                        return 20;
                    case C0627j.Toolbar_popupTheme /*8*/:
                        return 21;
                    case C0627j.Toolbar_titleTextAppearance /*9*/:
                        return 22;
                    case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                        return 23;
                    case C0627j.Toolbar_titleMargins /*11*/:
                        return 24;
                    case C0627j.Toolbar_titleMarginStart /*12*/:
                        return 25;
                    case C0627j.Toolbar_titleMarginEnd /*13*/:
                        return 26;
                    default:
                        return 12;
                }
            }
        }
        return -1;
    }

    public String m1954K() {
        int i = 0;
        String str = "";
        str = "";
        if (this.f1041a == null) {
            return str;
        }
        str = "NTAG";
        if (!"org.nfcforum.ndef.type2".equals(m1983m())) {
            return null;
        }
        str = str + "2";
        try {
            byte[] M = m1956M();
            if (M != null) {
                i = M[4];
            }
        } catch (Exception e) {
        }
        String str2 = str + String.valueOf(i);
        int L = m1955L();
        if (L == -1) {
            return null;
        }
        if (L <= 64) {
            str2 = str2 + "0";
        }
        if (L > 64 && L <= 96) {
            str2 = str2 + "1";
        }
        if (L > 96 && L <= 128) {
            str2 = str2 + "2";
        }
        if (L > 128 && L <= 256) {
            str2 = str2 + "3";
        }
        if (L > 256 && L <= 512) {
            str2 = str2 + "5";
        }
        return L > 512 ? str2 + "6" : str2;
    }

    public int m1955L() {
        if (this.f1043c == null) {
            return -1;
        }
        int i;
        if ("org.nfcforum.ndef.type2".equals(m1983m()) && Arrays.asList(this.f1043c.getTechList()).contains(NfcA.class.getName())) {
            try {
                if (!m1930N()) {
                    throw new Exception();
                } else if (this.f1050j == null) {
                    throw new Exception();
                } else {
                    byte[] d = m1938d(new byte[]{(byte) 48, (byte) 3});
                    i = d != null ? d[2] * 8 : -1;
                    m1931O();
                }
            } catch (Exception e) {
                return -1;
            }
        }
        i = -1;
        return i;
    }

    public byte[] m1956M() {
        if (this.f1043c == null) {
            return null;
        }
        byte[] d;
        if ("org.nfcforum.ndef.type2".equals(m1983m()) && Arrays.asList(this.f1043c.getTechList()).contains(NfcA.class.getName())) {
            try {
                if (!m1930N()) {
                    throw new Exception();
                } else if (this.f1050j == null) {
                    throw new Exception();
                } else {
                    d = m1938d(new byte[]{(byte) 96});
                    m1931O();
                }
            } catch (Exception e) {
                return null;
            }
        }
        d = null;
        return d;
    }

    public int m1957a(int i) {
        int i2 = 4;
        if (this.f1043c == null) {
            return -1;
        }
        String m = m1983m();
        int i3 = "org.nfcforum.ndef.type1".equals(m) ? 8 : 4;
        if (!"org.nfcforum.ndef.type2".equals(m)) {
            i2 = i3;
        }
        if ("org.nfcforum.ndef.type3".equals(m)) {
        }
        if ("org.nfcforum.ndef.type4".equals(m)) {
        }
        return ("com.nxp.ndef.mifareclassic".equals(m) || i == 10 || i == 11) ? 16 : i2;
    }

    public int m1958a(boolean z) {
        if (this.f1043c == null) {
            return -1;
        }
        String[] techList = this.f1043c.getTechList();
        if ((z ? m1953J() : -1) != -1 || VERSION.SDK_INT < 25) {
            return -1;
        }
        String f;
        Log.d("NFCTools", "Try to fix the Android 7.1 bug");
        if (Arrays.asList(techList).contains(NfcA.class.getName()) && Arrays.asList(techList).contains(MifareUltralight.class.getName()) && !Arrays.asList(techList).contains(NdefFormatable.class.getName()) && !Arrays.asList(techList).contains(Ndef.class.getName())) {
            try {
                if (!m1930N()) {
                    return -1;
                }
                byte[] atqa = this.f1050j.getAtqa();
                f = C0478j.m1942f(new byte[]{atqa[1], atqa[0]});
                String f2 = C0478j.m1942f(new byte[]{(byte) this.f1050j.getSak()});
                if ("0044".equals(f) && "00".equals(f2)) {
                    return 1;
                }
            } catch (Exception e) {
            }
        }
        if (techList.length != 1 || !Arrays.asList(techList).contains(NfcA.class.getName())) {
            return -1;
        }
        try {
            if (!m1930N()) {
                return -1;
            }
            byte[] atqa2 = this.f1050j.getAtqa();
            f = C0478j.m1942f(new byte[]{atqa2[1], atqa2[0]});
            String f3 = C0478j.m1942f(new byte[]{(byte) this.f1050j.getSak()});
            Object obj = null;
            if (m1938d(new byte[]{(byte) 120, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}).length >= 2) {
                obj = C0478j.m1942f(new byte[]{m1938d(new byte[]{(byte) 120, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0})[0], m1938d(new byte[]{(byte) 120, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0})[1]});
            }
            return ("0C00".equals(f) && "00".equals(f3) && "124C".equals(obj)) ? 2 : -1;
        } catch (Exception e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    public int m1959a(byte[] bArr) {
        return m1960a(null, bArr);
    }

    public int m1960a(byte[] bArr, byte[] bArr2) {
        if (this.f1043c == null) {
            return -5;
        }
        if (bArr == null && bArr2 == null) {
            return -5;
        }
        String[] techList = this.f1043c.getTechList();
        int J = m1953J();
        if (J == -1) {
            return -4;
        }
        HashMap e = m1940e(J);
        if (e == null) {
            return -4;
        }
        if (!e.containsKey("kAuthSector") || !e.containsKey("kPasswordSector") || !e.containsKey("kAuthCMD") || !e.containsKey("kDefaultPassword") || !e.containsKey("kAuthSet") || !e.containsKey("kAuthUnset") || !e.containsKey("kBytesPerPage")) {
            return -4;
        }
        if (!Arrays.asList(techList).contains(NfcA.class.getName())) {
            return -4;
        }
        byte[] bArr3;
        byte[] a;
        if (J == 10) {
            try {
                byte[] bArr4;
                MifareClassic mifareClassic = MifareClassic.get(this.f1043c);
                mifareClassic.connect();
                bArr3 = new byte[]{(byte) 66, (byte) 66};
                byte[] bArr5;
                if (bArr == null) {
                    a = C0489d.m2065a(bArr2, bArr3);
                    bArr3 = (byte[]) e.get("kAuthUnset");
                    bArr5 = a;
                    a = MifareClassic.KEY_DEFAULT;
                    bArr4 = bArr5;
                } else {
                    a = MifareClassic.KEY_DEFAULT;
                    bArr4 = C0489d.m2065a(bArr, bArr3);
                    bArr3 = (byte[]) e.get("kAuthSet");
                    bArr5 = a;
                    a = bArr4;
                    bArr4 = bArr5;
                }
                byte[] a2 = C0489d.m2065a(C0489d.m2065a(MifareClassic.KEY_NFC_FORUM, bArr3), a);
                int sectorCount = mifareClassic.getSectorCount();
                for (int i = 1; i < sectorCount; i++) {
                    boolean authenticateSectorWithKeyA = mifareClassic.authenticateSectorWithKeyA(i, bArr4);
                    if (bArr4 != MifareClassic.KEY_DEFAULT) {
                        authenticateSectorWithKeyA = mifareClassic.authenticateSectorWithKeyB(i, bArr4);
                    } else if (!authenticateSectorWithKeyA) {
                        authenticateSectorWithKeyA = mifareClassic.authenticateSectorWithKeyB(i, bArr4);
                    }
                    if (authenticateSectorWithKeyA) {
                        mifareClassic.writeBlock(mifareClassic.sectorToBlock(i) + 3, a2);
                    }
                }
                return 1;
            } catch (Exception e2) {
                return -6;
            }
        } else if (!m1930N()) {
            return -5;
        } else {
            bArr3 = (byte[]) e.get("kAuthSet");
            if (bArr == null) {
                a = (byte[]) e.get("kAuthUnset");
                bArr = (byte[]) e.get("kDefaultPassword");
            } else {
                a = bArr3;
            }
            if (bArr2 != null && !m1941e(C0489d.m2065a((byte[]) e.get("kAuthCMD"), bArr2))) {
                return -13;
            }
            if (!m1941e(C0489d.m2065a(new byte[]{((byte[]) e.get("kWriteCMD"))[0], ((byte[]) e.get("kAuthSector"))[0]}, a))) {
                return -6;
            }
            return !m1941e(C0489d.m2065a(new byte[]{((byte[]) e.get("kWriteCMD"))[0], ((byte[]) e.get("kPasswordSector"))[0]}, bArr)) ? -6 : 1;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public String m1961a(Context r14) {
        /*
        r13 = this;
        r2 = 0;
        r12 = 1;
        r11 = 0;
        r0 = "";
        r1 = r13.m1951H();
        r3 = java.util.Arrays.asList(r1);
        r4 = android.nfc.tech.NfcA.class;
        r4 = r4.getName();
        r3 = r3.contains(r4);
        r4 = java.util.Arrays.asList(r1);
        r5 = android.nfc.tech.NfcF.class;
        r5 = r5.getName();
        r4 = r4.contains(r5);
        r5 = java.util.Arrays.asList(r1);
        r6 = android.nfc.tech.NfcV.class;
        r6 = r6.getName();
        r5 = r5.contains(r6);
        r6 = java.util.Arrays.asList(r1);
        r7 = android.nfc.tech.MifareClassic.class;
        r7 = r7.getName();
        r6 = r6.contains(r7);
        r7 = java.util.Arrays.asList(r1);
        r8 = android.nfc.tech.MifareUltralight.class;
        r8 = r8.getName();
        r7 = r7.contains(r8);
        r8 = java.util.Arrays.asList(r1);
        r9 = android.nfc.tech.IsoDep.class;
        r9 = r9.getName();
        r8 = r8.contains(r9);
        r1 = java.util.Arrays.asList(r1);
        r9 = android.nfc.tech.Ndef.class;
        r9 = r9.getName();
        r9 = r1.contains(r9);
        if (r3 == 0) goto L_0x00da;
    L_0x006d:
        r1 = r13.f1043c;	 Catch:{ Exception -> 0x0190 }
        r1 = android.nfc.tech.NfcA.get(r1);	 Catch:{ Exception -> 0x0190 }
    L_0x0073:
        if (r1 == 0) goto L_0x00da;
    L_0x0075:
        r0 = r1.getAtqa();
        r3 = 2;
        r3 = new byte[r3];
        r10 = r0[r12];
        r3[r11] = r10;
        r0 = r0[r11];
        r3[r12] = r0;
        r3 = com.wakdev.libs.p015a.C0478j.m1942f(r3);
        r0 = new byte[r12];
        r1 = r1.getSak();
        r1 = (byte) r1;
        r0[r11] = r1;
        r1 = com.wakdev.libs.p015a.C0478j.m1942f(r0);
        r0 = "-";
        if (r8 == 0) goto L_0x00a9;
    L_0x0099:
        r8 = r13.f1043c;
        r8 = android.nfc.tech.IsoDep.get(r8);
        if (r8 == 0) goto L_0x00a9;
    L_0x00a1:
        r0 = r8.getHistoricalBytes();
        r0 = com.wakdev.libs.p015a.C0478j.m1942f(r0);
    L_0x00a9:
        r8 = r13.m1985o();
        r0 = r13.m1934a(r3, r1, r0, r8);
        r1 = com.wakdev.libs.p015a.C0480l.m2024a();
        r1 = r1.contains(r0);
        if (r1 == 0) goto L_0x00da;
    L_0x00bb:
        r1 = r14.getPackageManager();
        r3 = "com.nxp.mifare";
        r1 = r1.hasSystemFeature(r3);
        if (r1 != 0) goto L_0x00da;
    L_0x00c7:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = " - Not Supported";
        r0 = r0.append(r1);
        r0 = r0.toString();
    L_0x00da:
        if (r4 == 0) goto L_0x00f4;
    L_0x00dc:
        r1 = r13.f1043c;	 Catch:{ Exception -> 0x02cd }
        r1 = android.nfc.tech.NfcF.get(r1);	 Catch:{ Exception -> 0x02cd }
        if (r1 == 0) goto L_0x00f4;
    L_0x00e4:
        r1 = r1.getManufacturer();	 Catch:{ Exception -> 0x02cd }
        r1 = r13.m1973c(r1);	 Catch:{ Exception -> 0x02cd }
        r1 = com.wakdev.libs.p015a.C0478j.m1942f(r1);	 Catch:{ Exception -> 0x02cd }
        r0 = r13.m1936d(r1);	 Catch:{ Exception -> 0x02cd }
    L_0x00f4:
        if (r5 == 0) goto L_0x0106;
    L_0x00f6:
        r1 = r13.f1043c;	 Catch:{ Exception -> 0x0194 }
        r1 = android.nfc.tech.NfcV.get(r1);	 Catch:{ Exception -> 0x0194 }
        if (r1 == 0) goto L_0x0106;
    L_0x00fe:
        r1 = r13.m1985o();	 Catch:{ Exception -> 0x0194 }
        r0 = r13.m1939e(r1);	 Catch:{ Exception -> 0x0194 }
    L_0x0106:
        if (r6 == 0) goto L_0x014c;
    L_0x0108:
        r1 = r13.f1043c;	 Catch:{ Exception -> 0x022d }
        r1 = android.nfc.tech.MifareClassic.get(r1);	 Catch:{ Exception -> 0x022d }
        r3 = r1.getSize();	 Catch:{ Exception -> 0x022d }
        r1 = r1.getType();	 Catch:{ Exception -> 0x022d }
        switch(r1) {
            case 0: goto L_0x019a;
            case 1: goto L_0x01af;
            case 2: goto L_0x01c4;
            default: goto L_0x0119;
        };	 Catch:{ Exception -> 0x022d }
    L_0x0119:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r4 = " (Classic compatible)";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
    L_0x012c:
        switch(r3) {
            case 320: goto L_0x01d9;
            case 1024: goto L_0x01ee;
            case 2048: goto L_0x0203;
            case 4096: goto L_0x0218;
            default: goto L_0x012f;
        };	 Catch:{ Exception -> 0x022d }
    L_0x012f:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r4 = " ";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x022d }
        r3 = " bytes";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
    L_0x014c:
        if (r7 == 0) goto L_0x016e;
    L_0x014e:
        r1 = r13.f1043c;	 Catch:{ Exception -> 0x026d }
        r1 = android.nfc.tech.MifareUltralight.get(r1);	 Catch:{ Exception -> 0x026d }
        r1 = r1.getType();	 Catch:{ Exception -> 0x026d }
        switch(r1) {
            case 1: goto L_0x0243;
            case 2: goto L_0x0258;
            default: goto L_0x015b;
        };	 Catch:{ Exception -> 0x026d }
    L_0x015b:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x026d }
        r1.<init>();	 Catch:{ Exception -> 0x026d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x026d }
        r3 = " (Ultralight compatible)";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x026d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x026d }
    L_0x016e:
        r1 = r13.m1948E();	 Catch:{ Exception -> 0x0283 }
    L_0x0172:
        if (r1 == 0) goto L_0x02a4;
    L_0x0174:
        r2 = "0x124C";
        r2 = r1.equals(r2);
        if (r2 == 0) goto L_0x0287;
    L_0x017c:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = " - Topaz 512";
        r0 = r0.append(r1);
        r0 = r0.toString();
    L_0x018f:
        return r0;
    L_0x0190:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0073;
    L_0x0194:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0106;
    L_0x019a:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r4 = " (Classic)";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
        goto L_0x012c;
    L_0x01af:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r4 = " (Plus)";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
        goto L_0x012c;
    L_0x01c4:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r4 = " (Pro)";
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
        goto L_0x012c;
    L_0x01d9:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r3 = " 320 bytes";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
        goto L_0x014c;
    L_0x01ee:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r3 = " 1K";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
        goto L_0x014c;
    L_0x0203:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r3 = " 2K";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
        goto L_0x014c;
    L_0x0218:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022d }
        r1.<init>();	 Catch:{ Exception -> 0x022d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x022d }
        r3 = " 4K";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x022d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x022d }
        goto L_0x014c;
    L_0x022d:
        r1 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = " Unknown";
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x014c;
    L_0x0243:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x026d }
        r1.<init>();	 Catch:{ Exception -> 0x026d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x026d }
        r3 = " (Ultralight)";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x026d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x026d }
        goto L_0x016e;
    L_0x0258:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x026d }
        r1.<init>();	 Catch:{ Exception -> 0x026d }
        r1 = r1.append(r0);	 Catch:{ Exception -> 0x026d }
        r3 = " (Ultralight C)";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x026d }
        r0 = r1.toString();	 Catch:{ Exception -> 0x026d }
        goto L_0x016e;
    L_0x026d:
        r1 = move-exception;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = " Unknown";
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x016e;
    L_0x0283:
        r1 = move-exception;
        r1 = r2;
        goto L_0x0172;
    L_0x0287:
        r2 = "0x1148";
        r1 = r1.equals(r2);
        if (r1 == 0) goto L_0x02a4;
    L_0x028f:
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r0 = r1.append(r0);
        r1 = " - Topaz 96";
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x018f;
    L_0x02a4:
        if (r9 == 0) goto L_0x018f;
    L_0x02a6:
        r1 = r13.m1954K();
        if (r1 == 0) goto L_0x018f;
    L_0x02ac:
        r2 = "NTAG200";
        r2 = r2.equals(r1);
        if (r2 != 0) goto L_0x018f;
    L_0x02b4:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r2.append(r0);
        r2 = " - ";
        r0 = r0.append(r2);
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x018f;
    L_0x02cd:
        r1 = move-exception;
        goto L_0x00f4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wakdev.libs.a.j.a(android.content.Context):java.lang.String");
    }

    public String m1962a(String str, String str2) {
        try {
            String str3 = (String) this.f1051k.get(str);
            return str3 == null ? str2 : str3;
        } catch (Exception e) {
            return str2;
        }
    }

    public void m1963a() {
        int i = -1;
        Object obj = null;
        Context applicationContext = WDCore.m2174a().getApplicationContext();
        this.f1051k = new HashMap();
        this.f1051k.put("kTechDataTagTypeID", String.valueOf(m1953J()));
        this.f1051k.put("kTechDataIso", m1952I());
        this.f1051k.put("kTechDataType", m1961a(applicationContext));
        String[] H = m1951H();
        int length = H.length;
        Object obj2 = "";
        int i2 = 0;
        while (i2 < length) {
            i2++;
            String str = obj2 + H[i2].replaceAll("android.nfc.tech.", "") + ", ";
        }
        if (!obj2.isEmpty()) {
            obj2 = obj2.substring(0, obj2.length() - 2);
        }
        this.f1051k.put("kTechDataList", obj2);
        this.f1051k.put("kTechDataTagID", m1985o().toUpperCase());
        try {
            obj2 = m1945B();
        } catch (Exception e) {
            obj2 = obj;
        }
        try {
            int b;
            this.f1051k.put("kTechDataATQA", obj2);
            try {
                obj2 = m1946C();
            } catch (Exception e2) {
                obj2 = obj;
            }
            this.f1051k.put("kTechDataSAK", obj2);
            try {
                obj2 = m1948E();
            } catch (Exception e3) {
                obj2 = obj;
            }
            this.f1051k.put("kTechDataHeaderRom", obj2);
            try {
                obj2 = m1947D();
            } catch (Exception e4) {
                obj2 = obj;
            }
            this.f1051k.put("kTechDataATR", obj2);
            try {
                obj2 = m1993w();
            } catch (Exception e5) {
                obj2 = obj;
            }
            this.f1051k.put("kTechDataPMm", obj2);
            try {
                obj2 = m1994x();
            } catch (Exception e6) {
                obj2 = obj;
            }
            this.f1051k.put("kTechDataSystemCode", obj2);
            try {
                obj2 = m1992v();
            } catch (Exception e7) {
                obj2 = obj;
            }
            this.f1051k.put("kTechDataDSFID", obj2);
            try {
                obj2 = m1944A();
                b = m1967b((String) obj2);
            } catch (Exception e8) {
                obj2 = obj;
                b = i;
            }
            this.f1051k.put("kTechDataNTAGSignature", obj2);
            this.f1051k.put("kTechDataNTAGSignatureStatus", String.valueOf(b));
            try {
                i = m1980j();
            } catch (Exception e9) {
            }
            this.f1051k.put("kTechDataIsProtectedByPassword", String.valueOf(i));
            this.f1051k.put("kTechDataNDEFFormat", m1949F());
        } catch (Exception e10) {
        }
    }

    public void m1964a(NdefRecord ndefRecord) {
        C0479k c0479k = new C0479k();
        c0479k.m2001a(ndefRecord);
        addRecord(c0479k);
    }

    public void m1965a(Tag tag) {
        this.f1043c = tag;
        String[] techList = this.f1043c.getTechList();
        if (Arrays.asList(techList).contains(Ndef.class.getName())) {
            this.f1041a = Ndef.get(tag);
        }
        if (Arrays.asList(techList).contains(NdefFormatable.class.getName())) {
            this.f1042b = NdefFormatable.get(tag);
            MifareClassic.class.getName();
        }
    }

    public void m1966a(String str) {
        if (this.f1047g) {
            C0479k c0479k = new C0479k();
            c0479k.m2002a(str);
            addRecord(c0479k);
            return;
        }
        Log.e("NFCTools", "You are not in writing mode !");
    }

    public void addRecord(C0479k c0479k) {
        this.f1044d.add(c0479k);
        this.f1046f += c0479k.m2009d();
    }

    public int m1967b(String str) {
        return C0477i.m1924a(m1984n(), C0478j.m1935c(str));
    }

    public int m1968b(byte[] bArr) {
        return m1960a(bArr, null);
    }

    public Tag m1969b() {
        return this.f1043c;
    }

    public byte[] m1970b(int i) {
        if (this.f1043c == null) {
            return null;
        }
        byte[] bArr = null;
        if (i == -1) {
            return null;
        }
        HashMap e = m1940e(i);
        String[] techList = this.f1043c.getTechList();
        String m = m1983m();
        if (!Arrays.asList(techList).contains(NfcA.class.getName())) {
            return null;
        }
        int i2;
        int sectorToBlock;
        byte[] n;
        int i3;
        if ("com.nxp.ndef.mifareclassic".equals(m) || i == 10) {
            m1931O();
            try {
                MifareClassic mifareClassic = MifareClassic.get(this.f1043c);
                mifareClassic.connect();
                int sectorCount = mifareClassic.getSectorCount();
                for (i2 = 0; i2 < sectorCount; i2++) {
                    boolean authenticateSectorWithKeyA = mifareClassic.authenticateSectorWithKeyA(i2, MifareClassic.KEY_DEFAULT);
                    if (!authenticateSectorWithKeyA) {
                        authenticateSectorWithKeyA = mifareClassic.authenticateSectorWithKeyB(i2, MifareClassic.KEY_DEFAULT);
                    }
                    if (authenticateSectorWithKeyA) {
                        int blockCountInSector = mifareClassic.getBlockCountInSector(i2);
                        sectorToBlock = mifareClassic.sectorToBlock(i2);
                        int i4 = 0;
                        byte[] bArr2 = bArr;
                        while (i4 < blockCountInSector) {
                            try {
                                bArr = mifareClassic.readBlock(sectorToBlock);
                                if (bArr2 != null) {
                                    bArr = C0489d.m2065a(bArr2, bArr);
                                }
                                sectorToBlock++;
                                i4++;
                                bArr2 = bArr;
                            } catch (Exception e2) {
                                bArr = bArr2;
                            }
                        }
                        bArr = bArr2;
                    }
                }
            } catch (Exception e3) {
            }
        }
        if ("org.nfcforum.ndef.type1".equals(m)) {
            if (!m1930N()) {
                return null;
            }
            n = m1984n();
            byte b = ((byte[]) e.get("kReadSegCMD"))[0];
            sectorToBlock = C0489d.m2057a(((byte[]) e.get("kTotalSeg"))[0]);
            int a = C0489d.m2057a(((byte[]) e.get("kTotalBlocksPerSeg"))[0]);
            bArr = null;
            for (i3 = 0; i3 < sectorToBlock; i3++) {
                byte[] d = m1938d(C0489d.m2065a(new byte[]{b, (byte) (i3 * a), (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}, n));
                if (d != null) {
                    bArr = bArr == null ? Arrays.copyOfRange(d, 1, d.length) : C0489d.m2065a(bArr, Arrays.copyOfRange(d, 1, d.length));
                }
            }
            m1931O();
        }
        if (!"org.nfcforum.ndef.type2".equals(m)) {
            return bArr;
        }
        if (!m1930N()) {
            return null;
        }
        i2 = 0;
        i3 = 10;
        byte[] bArr3 = bArr;
        while (true) {
            int i5;
            bArr = m1938d(new byte[]{(byte) 48, (byte) i2});
            if (bArr != null) {
                if (bArr.length < 4) {
                    break;
                }
                bArr = Arrays.copyOfRange(bArr, 0, 4);
                if (bArr3 != null) {
                    bArr = C0489d.m2065a(bArr3, bArr);
                }
                int i6 = i3;
                i3 = i2 + 1;
                n = bArr;
                i5 = i6;
            } else {
                i5 = i3 - 1;
                if (i5 <= 0) {
                    break;
                }
                i3 = i2;
                n = bArr3;
            }
            bArr3 = n;
            i2 = i3;
            i3 = i5;
        }
        m1931O();
        return bArr3;
    }

    public Ndef m1971c() {
        return this.f1041a;
    }

    public void m1972c(int i) {
        this.f1045e = i;
    }

    public byte[] m1973c(byte[] bArr) {
        return C0489d.m2064a(bArr, 1, 1);
    }

    public NdefFormatable m1974d() {
        return this.f1042b;
    }

    public boolean m1975e() {
        return this.f1048h;
    }

    public void eraseTag() {
        C0479k c0479k = new C0479k();
        c0479k.m2005b();
        addRecord(c0479k);
    }

    public boolean m1976f() {
        return this.f1049i;
    }

    public int formatTagMemory() {
        if (this.f1043c == null) {
            return -5;
        }
        String[] techList = this.f1043c.getTechList();
        int J = m1953J();
        if (J == -1) {
            if (VERSION.SDK_INT >= 25) {
                int a = m1958a(false);
                if (a > 0) {
                    switch (a) {
                        case C0627j.View_paddingStart /*1*/:
                            if (m1932P()) {
                                return 1;
                            }
                            break;
                        case C0627j.View_paddingEnd /*2*/:
                            if (m1933Q()) {
                                return 1;
                            }
                            break;
                    }
                }
            }
            return -4;
        }
        HashMap e = m1940e(J);
        if (e == null) {
            return -4;
        }
        if (!e.containsKey("kFirstUserMemorySector") || !e.containsKey("kLastUserMemorySector") || !e.containsKey("kWriteCMD") || !e.containsKey("kBytesPerPage")) {
            return -4;
        }
        if (!Arrays.asList(techList).contains(NfcA.class.getName())) {
            return -4;
        }
        int a2;
        byte[] bArr;
        byte[] bArr2;
        int blockCountInSector;
        int i;
        if (J == 10 || J == 11) {
            try {
                MifareClassic mifareClassic = MifareClassic.get(this.f1043c);
                mifareClassic.connect();
                int a3 = C0489d.m2057a(((byte[]) e.get("kBytesPerPage"))[0]);
                a2 = C0489d.m2057a(((byte[]) e.get("kLastUserMemorySector"))[0]);
                bArr = (byte[]) e.get("kFormatSkipAds");
                bArr2 = new byte[a3];
                for (a3 = 0; a3 < bArr2.length; a3++) {
                    bArr2[a3] = (byte) 0;
                }
                int sectorCount = mifareClassic.getSectorCount();
                for (J = 0; J < sectorCount; J++) {
                    boolean authenticateSectorWithKeyA = mifareClassic.authenticateSectorWithKeyA(J, MifareClassic.KEY_DEFAULT);
                    if (!authenticateSectorWithKeyA) {
                        authenticateSectorWithKeyA = mifareClassic.authenticateSectorWithKeyB(J, MifareClassic.KEY_DEFAULT);
                    }
                    if (authenticateSectorWithKeyA) {
                        blockCountInSector = mifareClassic.getBlockCountInSector(J);
                        a3 = mifareClassic.sectorToBlock(J);
                        for (i = 0; i < blockCountInSector; i++) {
                            if (a3 > a2) {
                                return 1;
                            }
                            if (bArr == null || C0489d.m2058a(bArr, (byte) a3) == -1) {
                                mifareClassic.writeBlock(a3, bArr2);
                                a3++;
                            }
                        }
                        continue;
                    }
                }
                return 1;
            } catch (Exception e2) {
                return -6;
            }
        } else if (!m1930N()) {
            return -5;
        } else {
            byte[] d;
            int length;
            int a4 = C0489d.m2057a(((byte[]) e.get("kFirstUserMemorySector"))[0]);
            blockCountInSector = C0489d.m2057a(((byte[]) e.get("kLastUserMemorySector"))[0]);
            int a5 = C0489d.m2057a(((byte[]) e.get("kBytesPerPage"))[0]);
            bArr = (byte[]) e.get("kFormatSkipAds");
            byte[] bArr3 = (byte[]) e.get("kAppendToCMD");
            byte[] bArr4 = new byte[a5];
            for (i = 0; i < bArr4.length; i++) {
                bArr4[i] = (byte) 0;
            }
            byte[] bArr5 = (byte[]) e.get("kInitialMemoryValues");
            if (J == 6 || J == 5) {
                d = m1938d(new byte[]{(byte) 48, (byte) 3});
                if (d != null && Arrays.equals(C0489d.m2064a(d, 0, 4), (byte[]) e.get("kOTPOldVersion"))) {
                    bArr2 = (byte[]) e.get("kInitialMemoryValuesOldVersion");
                    if (bArr2 != null) {
                        length = bArr2.length;
                        a2 = 0;
                        while (a2 < length) {
                            d = Arrays.copyOfRange(bArr2, a2, a2 + a5);
                            if (bArr != null || C0489d.m2058a(bArr, (byte) a4) == -1) {
                                d = C0489d.m2065a(new byte[]{((byte[]) e.get("kWriteCMD"))[0], (byte) a4}, d);
                                bArr5 = bArr3 == null ? C0489d.m2065a(d, (byte[]) e.get("kAppendToCMD")) : d;
                                if (m1941e(bArr5) && !m1941e(bArr5) && !m1941e(bArr5)) {
                                    return -6;
                                }
                                a4++;
                                a2 += a5;
                            }
                        }
                    }
                    J = a4;
                    while (J <= blockCountInSector) {
                        if (bArr != null || C0489d.m2058a(bArr, (byte) J) == -1) {
                            bArr5 = C0489d.m2065a(new byte[]{((byte[]) e.get("kWriteCMD"))[0], (byte) J}, bArr4);
                            bArr3 = e.get("kAppendToCMD") == null ? C0489d.m2065a(bArr5, (byte[]) e.get("kAppendToCMD")) : bArr5;
                            if (!(m1941e(bArr3) || m1941e(bArr3) || m1941e(bArr3))) {
                                return -6;
                            }
                        }
                        J++;
                    }
                    return 1;
                }
            }
            bArr2 = bArr5;
            if (bArr2 != null) {
                length = bArr2.length;
                a2 = 0;
                while (a2 < length) {
                    d = Arrays.copyOfRange(bArr2, a2, a2 + a5);
                    if (bArr != null) {
                    }
                    d = C0489d.m2065a(new byte[]{((byte[]) e.get("kWriteCMD"))[0], (byte) a4}, d);
                    if (bArr3 == null) {
                    }
                    if (m1941e(bArr5)) {
                    }
                    a4++;
                    a2 += a5;
                }
            }
            J = a4;
            while (J <= blockCountInSector) {
                if (bArr != null) {
                }
                bArr5 = C0489d.m2065a(new byte[]{((byte[]) e.get("kWriteCMD"))[0], (byte) J}, bArr4);
                if (e.get("kAppendToCMD") == null) {
                }
                return -6;
            }
            return 1;
        }
    }

    public ArrayList<C0479k> m1977g() {
        return this.f1044d;
    }

    public boolean m1978h() {
        return this.f1042b != null;
    }

    public boolean m1979i() {
        return this.f1041a != null ? this.f1041a.isWritable() : false;
    }

    public int m1980j() {
        if (this.f1043c == null) {
            return -5;
        }
        String[] techList = this.f1043c.getTechList();
        int J = m1953J();
        if (J == -1) {
            return -4;
        }
        HashMap e = m1940e(J);
        if (e == null) {
            return -4;
        }
        if (!e.containsKey("kAuthSector") || !e.containsKey("kPasswordSector") || !e.containsKey("kAuthCMD") || !e.containsKey("kDefaultPassword") || !e.containsKey("kAuthSet") || !e.containsKey("kAuthUnset") || !e.containsKey("kBytesPerPage") || !e.containsKey("kDefaultAuth")) {
            return -4;
        }
        if (!Arrays.asList(techList).contains(NfcA.class.getName())) {
            return -4;
        }
        if (J != 2 && J != 3 && J != 4 && J != 5 && J != 6) {
            return -1;
        }
        try {
            if (!m1930N()) {
                return -5;
            }
            byte[] d = m1938d(new byte[]{(byte) 48, ((byte[]) e.get("kAuthSector"))[0]});
            int i = d != null ? ((byte[]) e.get("kDefaultAuth"))[0] == d[3] ? 3 : 2 : -1;
            return i;
        } catch (Exception e2) {
            return -1;
        }
    }

    public boolean m1981k() {
        boolean z = false;
        if (this.f1041a != null) {
            try {
                z = this.f1041a.canMakeReadOnly();
            } catch (Exception e) {
            }
        }
        return z;
    }

    public int m1982l() {
        return this.f1041a != null ? this.f1041a.getMaxSize() : 0;
    }

    public void lockTag() {
        this.f1048h = true;
    }

    public String m1983m() {
        return this.f1041a != null ? this.f1041a.getType() : "none";
    }

    public byte[] m1984n() {
        return this.f1043c.getId();
    }

    public String m1985o() {
        byte[] id = this.f1043c.getId();
        StringBuilder stringBuilder = new StringBuilder();
        if (id == null || id.length <= 0) {
            return null;
        }
        char[] cArr = new char[2];
        for (int i = 0; i < id.length; i++) {
            cArr[0] = Character.forDigit((id[i] >>> 4) & 15, 16);
            cArr[1] = Character.forDigit(id[i] & 15, 16);
            stringBuilder.append(cArr);
            if (i < id.length - 1) {
                stringBuilder.append(":");
            }
        }
        return stringBuilder.toString();
    }

    public byte[] m1986p() {
        try {
            return NfcA.get(this.f1043c).getAtqa();
        } catch (Exception e) {
            return null;
        }
    }

    public short m1987q() {
        try {
            return NfcA.get(this.f1043c).getSak();
        } catch (Exception e) {
            return (short) -1;
        }
    }

    public byte[] m1988r() {
        byte[] bArr = null;
        try {
            IsoDep isoDep = IsoDep.get(this.f1043c);
            if (!(NfcA.get(this.f1043c) == null || isoDep == null)) {
                bArr = isoDep.getHistoricalBytes();
            }
        } catch (Exception e) {
        }
        return bArr;
    }

    public byte[] m1989s() {
        byte[] bArr = null;
        if (Arrays.asList(this.f1043c.getTechList()).contains(NfcF.class.getName())) {
            try {
                NfcF nfcF = NfcF.get(this.f1043c);
                if (nfcF != null) {
                    bArr = nfcF.getSystemCode();
                }
            } catch (Exception e) {
            }
        }
        return bArr;
    }

    public byte[] m1990t() {
        byte[] bArr = null;
        if (Arrays.asList(this.f1043c.getTechList()).contains(NfcF.class.getName())) {
            try {
                NfcF nfcF = NfcF.get(this.f1043c);
                if (nfcF != null) {
                    bArr = nfcF.getManufacturer();
                }
            } catch (Exception e) {
            }
        }
        return bArr;
    }

    public byte[] m1991u() {
        if (Arrays.asList(this.f1043c.getTechList()).contains(NfcV.class.getName())) {
            try {
                if (NfcV.get(this.f1043c) != null) {
                    return new byte[]{NfcV.get(this.f1043c).getDsfId()};
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public String m1992v() {
        byte[] u = m1991u();
        if (u != null) {
            String f = C0478j.m1942f(u);
            if (!(f == null || f.isEmpty())) {
                return "0x" + f;
            }
        }
        return null;
    }

    public String m1993w() {
        byte[] t = m1990t();
        if (t != null) {
            String f = C0478j.m1942f(t);
            if (!(f == null || f.isEmpty())) {
                return "0x" + f;
            }
        }
        return null;
    }

    public String m1994x() {
        byte[] s = m1989s();
        if (s != null) {
            String f = C0478j.m1942f(s);
            if (!(f == null || f.isEmpty())) {
                return "0x" + f;
            }
        }
        return null;
    }

    public byte[] m1995y() {
        if (this.f1043c == null) {
            return null;
        }
        byte[] bArr;
        if ("org.nfcforum.ndef.type1".equals(m1983m()) && Arrays.asList(this.f1043c.getTechList()).contains(NfcA.class.getName())) {
            try {
                if (!m1930N()) {
                    throw new Exception();
                } else if (this.f1050j == null) {
                    throw new Exception();
                } else {
                    bArr = m1938d(new byte[]{(byte) 120, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0}).length >= 2 ? new byte[]{m1938d(new byte[]{(byte) 120, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0})[0], m1938d(new byte[]{(byte) 120, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0})[1]} : null;
                    m1931O();
                }
            } catch (Exception e) {
                return null;
            }
        }
        bArr = null;
        return bArr;
    }

    public byte[] m1996z() {
        if (this.f1043c == null) {
            return null;
        }
        byte[] d;
        if ("org.nfcforum.ndef.type2".equals(m1983m()) && Arrays.asList(this.f1043c.getTechList()).contains(NfcA.class.getName())) {
            try {
                if (!m1930N()) {
                    throw new Exception();
                } else if (this.f1050j == null) {
                    throw new Exception();
                } else {
                    d = m1938d(new byte[]{(byte) 60, (byte) 0});
                    if (d == null) {
                        d = null;
                    }
                    m1931O();
                }
            } catch (Exception e) {
                return null;
            }
        }
        d = null;
        return d;
    }
}
