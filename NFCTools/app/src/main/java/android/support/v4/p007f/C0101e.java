package android.support.v4.p007f;

import java.util.LinkedHashMap;

/* renamed from: android.support.v4.f.e */
public class C0101e<K, V> {
    private final LinkedHashMap<K, V> f279a;
    private int f280b;
    private int f281c;
    private int f282d;
    private int f283e;
    private int f284f;
    private int f285g;
    private int f286h;

    public C0101e(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.f281c = i;
        this.f279a = new LinkedHashMap(0, 0.75f, true);
    }

    private int m447c(K k, V v) {
        int b = m452b(k, v);
        if (b >= 0) {
            return b;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    public final V m448a(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.f279a.get(k);
            if (v != null) {
                this.f285g++;
                return v;
            }
            this.f286h++;
            V b = m453b(k);
            if (b == null) {
                return null;
            }
            synchronized (this) {
                this.f283e++;
                v = this.f279a.put(k, b);
                if (v != null) {
                    this.f279a.put(k, v);
                } else {
                    this.f280b += m447c(k, b);
                }
            }
            if (v != null) {
                m451a(false, k, b, v);
                return v;
            }
            m450a(this.f281c);
            return b;
        }
    }

    public final V m449a(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        V put;
        synchronized (this) {
            this.f282d++;
            this.f280b += m447c(k, v);
            put = this.f279a.put(k, v);
            if (put != null) {
                this.f280b -= m447c(k, put);
            }
        }
        if (put != null) {
            m451a(false, k, put, v);
        }
        m450a(this.f281c);
        return put;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m450a(int r5) {
        /*
        r4 = this;
    L_0x0000:
        monitor-enter(r4);
        r0 = r4.f280b;	 Catch:{ all -> 0x0032 }
        if (r0 < 0) goto L_0x0011;
    L_0x0005:
        r0 = r4.f279a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x000d:
        r0 = r4.f280b;	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0035;
    L_0x0011:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0032 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0032 }
        r1.<init>();	 Catch:{ all -> 0x0032 }
        r2 = r4.getClass();	 Catch:{ all -> 0x0032 }
        r2 = r2.getName();	 Catch:{ all -> 0x0032 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r2 = ".sizeOf() is reporting inconsistent results!";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0032 }
        r1 = r1.toString();	 Catch:{ all -> 0x0032 }
        r0.<init>(r1);	 Catch:{ all -> 0x0032 }
        throw r0;	 Catch:{ all -> 0x0032 }
    L_0x0032:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        throw r0;
    L_0x0035:
        r0 = r4.f280b;	 Catch:{ all -> 0x0032 }
        if (r0 <= r5) goto L_0x0041;
    L_0x0039:
        r0 = r4.f279a;	 Catch:{ all -> 0x0032 }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0032 }
        if (r0 == 0) goto L_0x0043;
    L_0x0041:
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        return;
    L_0x0043:
        r0 = r4.f279a;	 Catch:{ all -> 0x0032 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0032 }
        r0 = r0.iterator();	 Catch:{ all -> 0x0032 }
        r0 = r0.next();	 Catch:{ all -> 0x0032 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0032 }
        r1 = r0.getKey();	 Catch:{ all -> 0x0032 }
        r0 = r0.getValue();	 Catch:{ all -> 0x0032 }
        r2 = r4.f279a;	 Catch:{ all -> 0x0032 }
        r2.remove(r1);	 Catch:{ all -> 0x0032 }
        r2 = r4.f280b;	 Catch:{ all -> 0x0032 }
        r3 = r4.m447c(r1, r0);	 Catch:{ all -> 0x0032 }
        r2 = r2 - r3;
        r4.f280b = r2;	 Catch:{ all -> 0x0032 }
        r2 = r4.f284f;	 Catch:{ all -> 0x0032 }
        r2 = r2 + 1;
        r4.f284f = r2;	 Catch:{ all -> 0x0032 }
        monitor-exit(r4);	 Catch:{ all -> 0x0032 }
        r2 = 1;
        r3 = 0;
        r4.m451a(r2, r1, r0, r3);
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.f.e.a(int):void");
    }

    protected void m451a(boolean z, K k, V v, V v2) {
    }

    protected int m452b(K k, V v) {
        return 1;
    }

    protected V m453b(K k) {
        return null;
    }

    public final synchronized String toString() {
        String format;
        int i = 0;
        synchronized (this) {
            int i2 = this.f285g + this.f286h;
            if (i2 != 0) {
                i = (this.f285g * 100) / i2;
            }
            format = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[]{Integer.valueOf(this.f281c), Integer.valueOf(this.f285g), Integer.valueOf(this.f286h), Integer.valueOf(i)});
        }
        return format;
    }
}
