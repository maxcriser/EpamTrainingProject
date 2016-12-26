package com.wakdev.libs.p015a;

import com.wakdev.libs.commons.C0489d;
import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: com.wakdev.libs.a.l */
public class C0480l {
    private static HashMap<String, String> f1058a;
    private static HashMap<String, String> f1059b;
    private static HashMap<String, String> f1060c;
    private static HashMap<String, String> f1061d;
    private static HashMap<String, String> f1062e;

    static {
        f1058a = new HashMap();
        f1058a.put("tag_tech_unknown", "Unknown");
        f1058a.put("tag_tech_unknown_mf_classic", "Unknown Mifare Classic");
        f1058a.put("tag_tech_0344207577810280", "NXP MIFARE DESFire / NXP MIFARE DESFire EV1");
        f1058a.put("tag_tech_0304283877B14A434f503331", "NXP IBM JCOP31");
        f1058a.put("tag_tech_0048207877B1024A434F5076323431", "NXP IBM JCOP31 v2.4.1");
        f1058a.put("tag_tech_0048203833B14A434F503431563232", "NXP IBM JCOP41 v2.2");
        f1058a.put("tag_tech_0004283833B14A434F50343156323331", "NXP IBM JCOP41 v2.3.1");
        f1058a.put("tag_tech_044420C10531200F8429", "NXP MIFARE DESFire / NXP MIFARE DESFire EV1");
        f1058a.put("tag_tech_000409", "NXP MIFARE Mini");
        f1058a.put("tag_tech_000408", "NXP MIFARE Classic 1k");
        f1058a.put("tag_tech_004408", "NXP MIFARE Classic 1k");
        f1058a.put("tag_tech_000218", "NXP MIFARE Classic 4k");
        f1058a.put("tag_tech_004218", "NXP MIFARE Classic 4k");
        f1058a.put("tag_tech_004400", "NXP MIFARE Ultralight");
        f1058a.put("tag_tech_000488", "Infineon MIFARE Classic 1k");
        f1058a.put("tag_tech_000298", "Gemplus MPCOS");
        f1058a.put("tag_tech_000238", "Nokia MIFARE Classic 4k - emulated (6212 Classic)");
        f1058a.put("tag_tech_000838", "Nokia MIFARE Classic 4k - emulated (6131 NFC)");
        f1058a.put("tag_tech_034420", "NXP MIFARE DESFire / NXP MIFARE DESFire EV1");
        f1058a.put("tag_tech_030428", "NXP IBM JCOP31");
        f1058a.put("tag_tech_004820", "NXP IBM JCOP");
        f1058a.put("tag_tech_000428", "NXP IBM JCOP41");
        f1058a.put("tag_tech_0C00", "Innovision R&T Jewel");
        f1058a.put("tag_tech_000220", "NXP MIFARE Plus");
        f1058a.put("tag_tech_004420", "NXP MIFARE Plus");
        f1058a.put("tag_tech_000420", "NXP MIFARE Plus");
        f1058a.put("tag_tech_000220C1052F2F01BCD6", "NXP MIFARE Plus X 4k");
        f1058a.put("tag_tech_004420C1052F2F01BCD6", "NXP MIFARE Plus X 2k");
        f1058a.put("tag_tech_000420C1052F2F0035C7", "NXP MIFARE Plus S 2k");
        f1058a.put("tag_tech_004420C1052F2F0035C7", "NXP MIFARE Plus S 1k / 2k");
        f1058a.put("tag_tech_000220C1052F2F0035C7", "NXP MIFARE Plus S 4k");
        f1059b = new HashMap();
        f1059b.put("tag_tech_37_004400", "Kovio 2k");
        f1060c = new HashMap();
        f1060c.put("tag_tech_unknown", "Unknown");
        f1060c.put("tag_tech_unknown_felica", "Unknown FeliCa");
        f1060c.put("tag_tech_01", "Sony FeliCa RC-S915");
        f1060c.put("tag_tech_08", "Sony FeliCa RC-S952");
        f1060c.put("tag_tech_09", "Sony FeliCa RC-S953");
        f1060c.put("tag_tech_0C", "Sony FeliCa RC-S954");
        f1060c.put("tag_tech_0D", "Sony FeliCa RC-S960");
        f1060c.put("tag_tech_20", "Sony FeliCa RC-S962");
        f1060c.put("tag_tech_32", "Sony FeliCa RC-SA00/1");
        f1060c.put("tag_tech_35", "Sony FeliCa RC-SA01/2");
        f1060c.put("tag_tech_F0", "Sony FeliCa Lite RC-S965");
        f1060c.put("tag_tech_F1", "Sony FeliCa Lite-S RC-S966");
        f1060c.put("tag_tech_F2", "Sony FeliCa RC-S967 - Lite-S Mode");
        f1060c.put("tag_tech_E1", "Sony FeliCa RC-S967 - Plug Mode");
        f1060c.put("tag_tech_FF", "Sony FeliCa RC-S967 - NFC-DEP Mode");
        f1060c.put("tag_tech_E0", "Sony FeliCa RC-S926");
        f1061d = new HashMap();
        f1061d.put("tag_tech_unknown", "Unknown");
        f1061d.put("tag_tech_unknown_vicinity", "Unknown Vicinity");
        f1061d.put("tag_tech_162416E000", "EM Microelectronic - EM4x3x");
        f1061d.put("tag_tech_040104E000", "NXP - ICODE SLI");
        f1061d.put("tag_tech_040104E010", "NXP - ICODE SLIX");
        f1061d.put("tag_tech_040104E001", "NXP - ICODE SLIX2");
        f1062e = new HashMap();
        f1062e.put("tag_tech_01", "Motorola");
        f1062e.put("tag_tech_02", "STMicroelectronics");
        f1062e.put("tag_tech_03", "Hitachi");
        f1062e.put("tag_tech_04", "NXP Semiconductors");
        f1062e.put("tag_tech_05", "Infineon Technologies");
        f1062e.put("tag_tech_06", "Cylink");
        f1062e.put("tag_tech_07", "Texas Instrument");
        f1062e.put("tag_tech_08", "Fujitsu");
        f1062e.put("tag_tech_09", "Matsushita Electronics");
        f1062e.put("tag_tech_0A", "NEC");
        f1062e.put("tag_tech_0B", "Oki Electric");
        f1062e.put("tag_tech_0C", "Toshiba");
        f1062e.put("tag_tech_0D", "Mitsubishi Electric");
        f1062e.put("tag_tech_0E", "Samsung Electronics");
        f1062e.put("tag_tech_0F", "Hynix");
        f1062e.put("tag_tech_10", "LG Semiconductors");
        f1062e.put("tag_tech_11", "Emosyn-EM Microelectronics");
        f1062e.put("tag_tech_12", "Inside Technology");
        f1062e.put("tag_tech_13", "ORGA Kartensysteme");
        f1062e.put("tag_tech_14", "SHARP");
        f1062e.put("tag_tech_15", "ATMEL");
        f1062e.put("tag_tech_16", "EM Microelectronic");
        f1062e.put("tag_tech_17", "KSW Microtec");
        f1062e.put("tag_tech_18", "ZMD AG");
        f1062e.put("tag_tech_19", "XICOR");
        f1062e.put("tag_tech_1A", "Sony Corporation");
        f1062e.put("tag_tech_1B", "Malaysia Microelectronic");
        f1062e.put("tag_tech_1C", "Emosyn");
        f1062e.put("tag_tech_1D", "Shanghai Fudan Microelectronics");
        f1062e.put("tag_tech_1E", "Magellan Technology");
        f1062e.put("tag_tech_1F", "Melexis NV BO");
        f1062e.put("tag_tech_20", "Renesas Technology");
        f1062e.put("tag_tech_21", "TAGSYS");
        f1062e.put("tag_tech_22", "Transcore");
        f1062e.put("tag_tech_23", "Shanghai belling");
        f1062e.put("tag_tech_24", "Masktech Germany");
        f1062e.put("tag_tech_25", "Innovision R&T");
        f1062e.put("tag_tech_26", "Hitachi ULSI Systems");
        f1062e.put("tag_tech_27", "Yubico");
        f1062e.put("tag_tech_28", "Ricoh");
        f1062e.put("tag_tech_29", "ASK");
        f1062e.put("tag_tech_2A", "Unicore Microsystems");
        f1062e.put("tag_tech_2B", "Dallas Semiconductor");
        f1062e.put("tag_tech_2C", "Impinj");
        f1062e.put("tag_tech_2D", "RightPlug Alliance");
        f1062e.put("tag_tech_2E", "Broadcom");
        f1062e.put("tag_tech_2F", "MStar Semiconductor");
        f1062e.put("tag_tech_30", "BeeDar Technology");
        f1062e.put("tag_tech_31", "RFIDsec");
        f1062e.put("tag_tech_32", "Schweizer Electronic");
        f1062e.put("tag_tech_33", "AMIC Technology");
        f1062e.put("tag_tech_34", "Mikron JSC");
        f1062e.put("tag_tech_35", "Fraunhofer Institute");
        f1062e.put("tag_tech_36", "IDS Microchip");
        f1062e.put("tag_tech_37", "Kovio");
        f1062e.put("tag_tech_38", "HMT Microelectronic");
        f1062e.put("tag_tech_39", "Silicon Craft Technology");
        f1062e.put("tag_tech_3A", "Advanced Film Device");
        f1062e.put("tag_tech_3B", "Nitecrest");
        f1062e.put("tag_tech_3C", "Verayo");
        f1062e.put("tag_tech_3D", "HID Global");
        f1062e.put("tag_tech_3E", "Productivity Engineering");
        f1062e.put("tag_tech_3F", "Austriamicrosystems");
        f1062e.put("tag_tech_40", "Gemalto");
        f1062e.put("tag_tech_41", "Renesas Electronics");
        f1062e.put("tag_tech_42", "3Alogics");
        f1062e.put("tag_tech_43", "Top TroniQ Asia");
        f1062e.put("tag_tech_44", "Gentag");
        f1062e.put("tag_tech_45", "Invengo Information Technology");
        f1062e.put("tag_tech_46", "Guangzhou Sysur Microelectronics");
        f1062e.put("tag_tech_47", "CEITEC");
        f1062e.put("tag_tech_48", "Shanghai Quanray Electronics");
        f1062e.put("tag_tech_49", "MediaTek");
        f1062e.put("tag_tech_4A", "Angstrem PJSC");
        f1062e.put("tag_tech_4B", "Celisic Semiconductor");
        f1062e.put("tag_tech_4C", "LEGIC Identsystems");
        f1062e.put("tag_tech_4D", "Balluff");
        f1062e.put("tag_tech_4E", "Oberthur Technologies");
        f1062e.put("tag_tech_4F", "Silterra Malaysia");
        f1062e.put("tag_tech_50", "DELTA Danish Electronics");
        f1062e.put("tag_tech_51", "Giesecke & Devrient");
        f1062e.put("tag_tech_52", "Shenzhen China Vision Microelectronics");
        f1062e.put("tag_tech_53", "Shanghai Feiju Microelectronics");
        f1062e.put("tag_tech_54", "Intel Corporation");
        f1062e.put("tag_tech_55", "Microsensys");
        f1062e.put("tag_tech_56", "Sonix Technology");
        f1062e.put("tag_tech_57", "Qualcomm Technologies");
        f1062e.put("tag_tech_58", "Realtek Semiconductor");
        f1062e.put("tag_tech_59", "Freevision Technologies");
        f1062e.put("tag_tech_5A", "Giantec Semiconductor");
        f1062e.put("tag_tech_5B", "JSC Angstrem-T");
        f1062e.put("tag_tech_5C", "STARCHIP");
        f1062e.put("tag_tech_5D", "SPIRTECH");
        f1062e.put("tag_tech_5E", "GANTNER Electronic");
        f1062e.put("tag_tech_5F", "Nordic Semiconductor");
        f1062e.put("tag_tech_60", "Verisiti");
        f1062e.put("tag_tech_61", "Wearlinks Technology");
        f1062e.put("tag_tech_62", "Userstar Information Systems");
        f1062e.put("tag_tech_63", "Pragmatic Printing");
        f1062e.put("tag_tech_64", "LSI-TEC");
        f1062e.put("tag_tech_65", "Tendyron Corporation");
        f1062e.put("tag_tech_66", "MUTO Smart Co.");
        f1062e.put("tag_tech_67", "ON Semiconductor");
        f1062e.put("tag_tech_68", "T\u00dcB\u0130TAK B\u0130LGEM");
    }

    public static String m2022a(String str) {
        return f1060c.containsKey(new StringBuilder().append("tag_tech_").append(str).toString()) ? (String) f1060c.get("tag_tech_" + str) : (String) f1060c.get("tag_tech_unknown_felica");
    }

    public static String m2023a(String str, String str2, String str3, String str4) {
        String replace = str3.replace("-", "");
        String str5 = null;
        if (!(str4 == null || str4.isEmpty())) {
            str5 = str4.substring(0, 2);
        }
        return (str5 == null || !f1059b.containsKey("tag_tech_" + str5 + "_" + str + str2 + replace)) ? (str5 == null || !f1059b.containsKey("tag_tech_" + str5 + "_" + str + str2)) ? f1058a.containsKey(new StringBuilder().append("tag_tech_").append(str).append(str2).append(replace).toString()) ? (String) f1058a.get("tag_tech_" + str + str2 + replace) : f1058a.containsKey(new StringBuilder().append("tag_tech_").append(str).append(str2).toString()) ? (String) f1058a.get("tag_tech_" + str + str2) : f1058a.containsKey(new StringBuilder().append("tag_tech_").append(str).toString()) ? (String) f1058a.get("tag_tech_" + str) : (str5 == null || !f1062e.containsKey("tag_tech_" + str5)) ? (String) f1058a.get("tag_tech_unknown_mf_classic") : (String) f1062e.get("tag_tech_" + str5) : (String) f1059b.get("tag_tech_" + str5 + "_" + str + str2) : (String) f1059b.get("tag_tech_" + str5 + "_" + str + str2 + replace);
    }

    public static ArrayList<String> m2024a() {
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(f1058a.get("tag_tech_000408"));
        arrayList.add(f1058a.get("tag_tech_000218"));
        arrayList.add(f1058a.get("tag_tech_004218"));
        arrayList.add(f1058a.get("tag_tech_004408"));
        arrayList.add(f1058a.get("tag_tech_000488"));
        return arrayList;
    }

    public static String m2025b(String str) {
        try {
            String toUpperCase = str.replace(":", "").toUpperCase();
            if (toUpperCase.length() != 16) {
                throw new Exception();
            }
            String substring = C0489d.m2060a(toUpperCase.substring(8, 10), 8).substring(3, 5);
            toUpperCase = toUpperCase.substring(10, 16);
            String substring2 = toUpperCase.substring(2, 4);
            if (f1061d.containsKey("tag_tech_" + substring2 + toUpperCase + substring)) {
                return (String) f1061d.get("tag_tech_" + substring2 + toUpperCase + substring);
            }
            if (f1062e.containsKey("tag_tech_" + substring2)) {
                return (String) f1062e.get("tag_tech_" + substring2);
            }
            return (String) f1061d.get("tag_tech_unknown_vicinity");
        } catch (Exception e) {
        }
    }
}
