package com.wakdev.libs.p015a.p016a;

import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import com.wakdev.libs.commons.C0489d;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.wakdev.libs.a.a.b */
public class C0464b {
    public static final byte[] f982a;
    public static final byte[] f983b;
    public static final byte[] f984c;
    public static final byte[] f985d;
    private static final byte[] f986e;
    private ArrayList<C0463a> f987f;
    private int f988g;

    /* renamed from: com.wakdev.libs.a.a.b.a */
    private static class C0463a {
        byte f976a;
        C0460a f977b;
        byte[] f978c;
        byte[][] f979d;
        byte[][] f980e;
        int f981f;

        private C0463a() {
        }
    }

    static {
        f982a = new byte[]{(byte) 99, (byte) 114};
        f983b = new byte[]{(byte) 101, (byte) 114, (byte) 114};
        f984c = new byte[]{(byte) 83, (byte) 112, (byte) 101, (byte) 99, (byte) 105, (byte) 102, (byte) 105, (byte) 99};
        f985d = "application/octet-stream".getBytes();
        f986e = new byte[0];
    }

    public C0464b() {
        this.f987f = new ArrayList();
        this.f988g = 1;
    }

    private NdefRecord m1863a(C0463a c0463a) {
        C0460a c0460a = c0463a.f977b;
        return new NdefRecord(c0460a.m1861b(), c0460a.m1862c(), c0463a.f978c, c0460a.m1860a());
    }

    private NdefRecord m1864a(byte[] bArr, byte[] bArr2) {
        return new NdefRecord((short) 2, f985d, bArr, bArr2);
    }

    private NdefRecord m1865b(C0463a c0463a) {
        Object obj = new byte[((c0463a.f978c.length + 3) + c0463a.f981f)];
        obj[0] = (byte) (c0463a.f976a & 3);
        obj[1] = (byte) c0463a.f978c.length;
        System.arraycopy(c0463a.f978c, 0, obj, 2, c0463a.f978c.length);
        if (c0463a.f980e != null) {
            int length = c0463a.f978c.length + 2;
            int i = length + 1;
            obj[length] = (byte) c0463a.f980e.length;
            length = i;
            for (Object obj2 : c0463a.f980e) {
                int i2 = length + 1;
                obj[length] = (byte) obj2.length;
                System.arraycopy(obj2, 0, obj, i2, obj2.length);
                length = obj2.length + i2;
            }
        }
        return new NdefRecord((short) 1, NdefRecord.RTD_ALTERNATIVE_CARRIER, f986e, obj);
    }

    private NdefRecord[] m1866b() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f987f.iterator();
        while (it.hasNext()) {
            C0463a c0463a = (C0463a) it.next();
            arrayList.add(m1863a(c0463a));
            if (c0463a.f980e != null) {
                for (int i = 0; i < c0463a.f980e.length; i++) {
                    arrayList.add(m1864a(c0463a.f980e[i], c0463a.f979d[i]));
                }
            }
        }
        return (NdefRecord[]) arrayList.toArray(new NdefRecord[arrayList.size()]);
    }

    private NdefRecord[] m1867c() {
        NdefRecord[] ndefRecordArr = new NdefRecord[this.f987f.size()];
        Iterator it = this.f987f.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            ndefRecordArr[i] = m1865b((C0463a) it.next());
            i = i2;
        }
        return ndefRecordArr;
    }

    public NdefMessage m1868a() {
        Object toByteArray = new NdefMessage(m1867c()).toByteArray();
        Object obj = new byte[(toByteArray.length + 1)];
        obj[0] = (byte) 18;
        System.arraycopy(toByteArray, 0, obj, 1, toByteArray.length);
        return new NdefMessage((NdefRecord[]) C0489d.m2066a(new NdefRecord[]{new NdefRecord((short) 1, NdefRecord.RTD_HANDOVER_SELECT, f986e, obj)}, m1866b()));
    }

    public void m1869a(byte b, C0460a c0460a) {
        m1870a(b, c0460a, (byte[][]) null);
    }

    public void m1870a(byte b, C0460a c0460a, byte[][] bArr) {
        int i = 0;
        C0463a c0463a = new C0463a();
        c0463a.f976a = b;
        c0463a.f977b = c0460a;
        int i2 = this.f988g;
        this.f988g = i2 + 1;
        c0463a.f978c = Integer.toString(i2).getBytes();
        if (!(bArr == null || bArr.length == 0)) {
            byte[][] bArr2 = new byte[bArr.length][];
            i2 = 0;
            while (i < bArr.length) {
                int i3 = this.f988g;
                this.f988g = i3 + 1;
                bArr2[i] = Integer.toString(i3).getBytes();
                i2 += bArr2[i].length + 1;
                i++;
            }
            c0463a.f979d = bArr;
            c0463a.f980e = bArr2;
            c0463a.f981f = i2;
        }
        this.f987f.add(c0463a);
    }
}
