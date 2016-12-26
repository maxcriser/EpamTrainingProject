package com.wakdev.libs.p015a.p016a;

import com.wakdev.libs.p015a.p016a.C0460a.C0461a;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.wakdev.libs.a.a.c */
public class C0468c extends C0461a {
    public static final short[] f1009b;
    public static final short[] f1010c;
    private static final byte[] f1011d;
    private byte f1012e;
    private C0465a f1013f;
    private C0466b f1014g;
    private ArrayList<C0467c> f1015h;
    private int f1016i;

    /* renamed from: com.wakdev.libs.a.a.c.a */
    public static class C0465a {
        private byte f989a;
        private byte[] f990b;
        private short f991c;
        private boolean f992d;
        private short f993e;
        private boolean f994f;
        private byte[] f995g;
        private byte[] f996h;
        private int f997i;
        private ArrayList<C0467c> f998j;
        private int f999k;

        public C0465a() {
            this.f989a = (byte) 1;
            this.f991c = (short) 32;
            this.f993e = (short) 8;
            this.f997i = 5;
        }

        private int m1871a() {
            return (this.f999k + this.f997i) + 4;
        }

        private void m1874a(ByteBuffer byteBuffer) {
            byteBuffer.putShort((short) 4110);
            byteBuffer.putShort((short) (this.f997i + this.f999k));
            byteBuffer.putShort((short) 4134);
            byteBuffer.putShort((short) 1);
            byteBuffer.put(this.f989a);
            if (this.f990b != null) {
                byteBuffer.putShort((short) 4165);
                byteBuffer.putShort((short) this.f990b.length);
                byteBuffer.put(this.f990b);
            }
            if (this.f992d) {
                byteBuffer.putShort((short) 4099);
                byteBuffer.putShort((short) 2);
                byteBuffer.putShort(this.f991c);
            }
            if (this.f994f) {
                byteBuffer.putShort((short) 4111);
                byteBuffer.putShort((short) 2);
                byteBuffer.putShort(this.f993e);
            }
            if (this.f995g != null) {
                byteBuffer.putShort((short) 4135);
                byteBuffer.putShort((short) this.f995g.length);
                byteBuffer.put(this.f995g);
            }
            if (this.f996h != null) {
                byteBuffer.putShort((short) 4128);
                byteBuffer.putShort((short) this.f996h.length);
                byteBuffer.put(this.f996h);
            }
            m1875b(byteBuffer);
        }

        private void m1875b(ByteBuffer byteBuffer) {
            if (this.f998j != null) {
                Iterator it = this.f998j.iterator();
                while (it.hasNext()) {
                    C0467c c0467c = (C0467c) it.next();
                    byteBuffer.putShort(c0467c.f1005a);
                    byteBuffer.putShort((short) c0467c.f1006b.length);
                    byteBuffer.put(c0467c.f1006b, c0467c.f1007c, c0467c.f1008d);
                }
            }
        }

        public void m1876a(String str) {
            byte[] bytes;
            int i = 0;
            try {
                bytes = str.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException e) {
                bytes = null;
            }
            int length = this.f995g == null ? 0 : this.f995g.length + 4;
            if (bytes != null) {
                i = bytes.length + 4;
            }
            this.f997i -= length;
            if (i == 0) {
                this.f995g = null;
                return;
            }
            this.f995g = bytes;
            this.f997i += i;
        }

        public void m1877a(short s) {
            if (!this.f992d) {
                this.f992d = true;
                this.f997i += 6;
            }
            this.f991c = s;
        }

        public void m1878a(byte[] bArr) {
            int i = 0;
            int length = this.f996h == null ? 0 : this.f996h.length + 4;
            if (bArr != null) {
                i = bArr.length + 4;
            }
            this.f997i -= length;
            if (i == 0) {
                this.f996h = null;
                return;
            }
            this.f996h = bArr;
            this.f997i = i + this.f997i;
        }

        public void m1879b(String str) {
            byte[] bytes;
            int i = 0;
            try {
                bytes = str.getBytes("US-ASCII");
            } catch (UnsupportedEncodingException e) {
                bytes = null;
            }
            int length = this.f990b == null ? 0 : this.f990b.length + 4;
            if (bytes != null) {
                i = bytes.length + 4;
            }
            this.f997i -= length;
            if (i == 0) {
                this.f990b = null;
                return;
            }
            this.f990b = bytes;
            this.f997i += i;
        }

        public void m1880b(short s) {
            if (!this.f994f) {
                this.f994f = true;
                this.f997i += 6;
            }
            this.f993e = s;
        }
    }

    /* renamed from: com.wakdev.libs.a.a.c.b */
    public static class C0466b {
        private static final byte[] f1000a;
        private byte[] f1001b;
        private int f1002c;
        private byte[] f1003d;
        private int f1004e;

        static {
            f1000a = new byte[]{(byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48, (byte) 48};
        }

        private int m1881a() {
            return this.f1004e + 4;
        }

        private void m1884a(ByteBuffer byteBuffer) {
            byteBuffer.putShort((short) 4140);
            byteBuffer.putShort((short) this.f1004e);
            byteBuffer.put(this.f1001b);
            byteBuffer.putShort((short) this.f1002c);
            byteBuffer.put(this.f1003d);
        }
    }

    /* renamed from: com.wakdev.libs.a.a.c.c */
    public static class C0467c {
        short f1005a;
        byte[] f1006b;
        int f1007c;
        int f1008d;
    }

    static {
        f1009b = new short[]{(short) 1, (short) 2, (short) 4, (short) 8, (short) 16, (short) 32, (short) 34};
        f1010c = new short[]{(short) 1, (short) 2, (short) 4, (short) 8, (short) 12};
        f1011d = new byte[0];
    }

    private C0468c() {
        this.f1012e = (byte) 16;
        this.a = "application/vnd.wfa.wsc";
    }

    public C0468c(C0465a c0465a) {
        this();
        this.f1013f = c0465a;
    }

    private void m1885a(ByteBuffer byteBuffer) {
        if (this.f1015h != null) {
            Iterator it = this.f1015h.iterator();
            while (it.hasNext()) {
                C0467c c0467c = (C0467c) it.next();
                byteBuffer.putShort(c0467c.f1005a);
                byteBuffer.putShort((short) c0467c.f1006b.length);
                byteBuffer.put(c0467c.f1006b, c0467c.f1007c, c0467c.f1008d);
            }
        }
    }

    public byte[] m1886a() {
        int i = this.f1016i + 5;
        if (this.f1013f != null) {
            i += this.f1013f.m1871a();
        } else if (this.f1014g != null) {
            i += this.f1014g.m1881a();
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.putShort((short) 4170);
        allocate.putShort((short) 1);
        allocate.put(this.f1012e);
        if (this.f1013f != null) {
            this.f1013f.m1874a(allocate);
        } else if (this.f1014g != null) {
            this.f1014g.m1884a(allocate);
        }
        m1885a(allocate);
        return allocate.array();
    }

    public byte[] m1887c() {
        return this.a.getBytes();
    }
}
