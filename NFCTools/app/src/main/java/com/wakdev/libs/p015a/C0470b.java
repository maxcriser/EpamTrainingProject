package com.wakdev.libs.p015a;

import android.annotation.SuppressLint;
import com.wakdev.nfctools.C0628m.C0627j;
import java.util.HashMap;

/* renamed from: com.wakdev.libs.a.b */
public class C0470b {
    public static final byte[] f1027a;
    public static final byte[] f1028b;
    public static final byte[] f1029c;
    public static final byte[] f1030d;

    static {
        f1027a = new byte[]{(byte) 66, (byte) 82, (byte) 69, (byte) 65};
        f1028b = new byte[]{(byte) 75, (byte) 77, (byte) 69, (byte) 73};
        f1029c = new byte[]{(byte) 70, (byte) 89, (byte) 79, (byte) 85};
        f1030d = new byte[]{(byte) 67, (byte) 65, (byte) 78, (byte) 33};
    }

    @SuppressLint({"UseSparseArrays"})
    public static HashMap<Integer, String> m1904a(int i) {
        HashMap<Integer, String> hashMap = new HashMap();
        switch (i) {
            case C0627j.View_paddingStart /*1*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                hashMap.put(Integer.valueOf(40), "LOCK2 - LOCK3");
                hashMap.put(Integer.valueOf(41), "COUNT0 - COUNT1");
                break;
            case C0627j.View_paddingEnd /*2*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                hashMap.put(Integer.valueOf(16), "CFG 0 (MIRROR / AUTH0)");
                hashMap.put(Integer.valueOf(17), "CFG 1 (ACCESS)");
                hashMap.put(Integer.valueOf(18), "PWD0 - PWD3");
                hashMap.put(Integer.valueOf(19), "PACK0 - PACK1");
                break;
            case C0627j.Toolbar_subtitle /*3*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                hashMap.put(Integer.valueOf(36), "LOCK2 - LOCK4");
                hashMap.put(Integer.valueOf(37), "CFG 0 (MIRROR / AUTH0)");
                hashMap.put(Integer.valueOf(38), "CFG 1 (ACCESS)");
                hashMap.put(Integer.valueOf(39), "PWD0 - PWD3");
                hashMap.put(Integer.valueOf(40), "PACK0 - PACK1");
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                hashMap.put(Integer.valueOf(40), "LOCK2 - LOCK4");
                hashMap.put(Integer.valueOf(41), "CFG 0 (MIRROR / AUTH0)");
                hashMap.put(Integer.valueOf(42), "CFG 1 (ACCESS)");
                hashMap.put(Integer.valueOf(43), "PWD0 - PWD3");
                hashMap.put(Integer.valueOf(44), "PACK0 - PACK1");
                break;
            case C0627j.Toolbar_contentInsetEnd /*5*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                hashMap.put(Integer.valueOf(130), "LOCK2 - LOCK4");
                hashMap.put(Integer.valueOf(131), "CFG 0 (MIRROR / AUTH0)");
                hashMap.put(Integer.valueOf(132), "CFG 1 (ACCESS)");
                hashMap.put(Integer.valueOf(133), "PWD0 - PWD3");
                hashMap.put(Integer.valueOf(134), "PACK0 - PACK1");
                break;
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                hashMap.put(Integer.valueOf(226), "LOCK2 - LOCK4");
                hashMap.put(Integer.valueOf(227), "CFG 0 (MIRROR / AUTH0)");
                hashMap.put(Integer.valueOf(228), "CFG 1 (ACCESS)");
                hashMap.put(Integer.valueOf(229), "PWD0 - PWD3");
                hashMap.put(Integer.valueOf(230), "PACK0 - PACK1");
                break;
            case C0627j.Toolbar_contentInsetRight /*7*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID5 / RESERVED");
                hashMap.put(Integer.valueOf(13), "RESERVED");
                hashMap.put(Integer.valueOf(14), "LOCK0 - LOCK1 / OTP0 - OTP5");
                hashMap.put(Integer.valueOf(15), "OTP6 - OTP7 / LOCK2 - LOCK7");
                break;
            case C0627j.Toolbar_popupTheme /*8*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                hashMap.put(Integer.valueOf(40), "LOCK2 - LOCK3");
                hashMap.put(Integer.valueOf(41), "COUNT0 - COUNT1");
                hashMap.put(Integer.valueOf(42), "AUTH0");
                hashMap.put(Integer.valueOf(43), "AUTH1");
                break;
            case C0627j.Toolbar_titleTextAppearance /*9*/:
                hashMap.put(Integer.valueOf(0), "UID0 - UID2 / BCC0");
                hashMap.put(Integer.valueOf(1), "UID3 - UDI6");
                hashMap.put(Integer.valueOf(2), "BCC1 / INT. / LOCK0 - LOCK1");
                hashMap.put(Integer.valueOf(3), "OTP0 - OTP3");
                break;
            case C0627j.Toolbar_subtitleTextAppearance /*10*/:
                hashMap.put(Integer.valueOf(0), "UID0-UID3 / MANUFACTURER");
                hashMap.put(Integer.valueOf(3), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(7), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(11), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(15), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(19), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(23), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(27), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(31), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(35), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(39), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(43), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(47), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(51), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(55), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(59), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(63), "KEYA / ACCESS / KEYB");
                break;
            case C0627j.Toolbar_titleMargins /*11*/:
                hashMap.put(Integer.valueOf(0), "UID0-UID3 / MANUFACTURER");
                hashMap.put(Integer.valueOf(3), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(7), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(11), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(15), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(19), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(23), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(27), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(31), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(35), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(39), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(43), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(47), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(51), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(55), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(59), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(63), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(67), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(71), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(75), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(79), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(83), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(87), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(91), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(95), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(99), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(103), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(107), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(111), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(115), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(119), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(123), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(127), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(143), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(159), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(175), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(191), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(207), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(223), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(239), "KEYA / ACCESS / KEYB");
                hashMap.put(Integer.valueOf(255), "KEYA / ACCESS / KEYB");
                break;
        }
        return hashMap;
    }
}
