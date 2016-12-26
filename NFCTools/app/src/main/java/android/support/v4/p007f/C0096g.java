package android.support.v4.p007f;

import java.util.Map;

/* renamed from: android.support.v4.f.g */
public class C0096g<K, V> {
    static Object[] f266b;
    static int f267c;
    static Object[] f268d;
    static int f269e;
    int[] f270f;
    Object[] f271g;
    int f272h;

    public C0096g() {
        this.f270f = C0098b.f274a;
        this.f271g = C0098b.f276c;
        this.f272h = 0;
    }

    private static void m428a(int[] iArr, Object[] objArr, int i) {
        int i2;
        if (iArr.length == 8) {
            synchronized (C0097a.class) {
                if (f269e < 10) {
                    objArr[0] = f268d;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f268d = objArr;
                    f269e++;
                }
            }
        } else if (iArr.length == 4) {
            synchronized (C0097a.class) {
                if (f267c < 10) {
                    objArr[0] = f266b;
                    objArr[1] = iArr;
                    for (i2 = (i << 1) - 1; i2 >= 2; i2--) {
                        objArr[i2] = null;
                    }
                    f266b = objArr;
                    f267c++;
                }
            }
        }
    }

    private void m429e(int i) {
        Object[] objArr;
        if (i == 8) {
            synchronized (C0097a.class) {
                if (f268d != null) {
                    objArr = f268d;
                    this.f271g = objArr;
                    f268d = (Object[]) objArr[0];
                    this.f270f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f269e--;
                    return;
                }
            }
        } else if (i == 4) {
            synchronized (C0097a.class) {
                if (f266b != null) {
                    objArr = f266b;
                    this.f271g = objArr;
                    f266b = (Object[]) objArr[0];
                    this.f270f = (int[]) objArr[1];
                    objArr[1] = null;
                    objArr[0] = null;
                    f267c--;
                    return;
                }
            }
        }
        this.f270f = new int[i];
        this.f271g = new Object[(i << 1)];
    }

    int m430a() {
        int i = this.f272h;
        if (i == 0) {
            return -1;
        }
        int a = C0098b.m442a(this.f270f, i, 0);
        if (a < 0 || this.f271g[a << 1] == null) {
            return a;
        }
        int i2 = a + 1;
        while (i2 < i && this.f270f[i2] == 0) {
            if (this.f271g[i2 << 1] == null) {
                return i2;
            }
            i2++;
        }
        a--;
        while (a >= 0 && this.f270f[a] == 0) {
            if (this.f271g[a << 1] == null) {
                return a;
            }
            a--;
        }
        return i2 ^ -1;
    }

    public int m431a(Object obj) {
        return obj == null ? m430a() : m432a(obj, obj.hashCode());
    }

    int m432a(Object obj, int i) {
        int i2 = this.f272h;
        if (i2 == 0) {
            return -1;
        }
        int a = C0098b.m442a(this.f270f, i2, i);
        if (a < 0 || obj.equals(this.f271g[a << 1])) {
            return a;
        }
        int i3 = a + 1;
        while (i3 < i2 && this.f270f[i3] == i) {
            if (obj.equals(this.f271g[i3 << 1])) {
                return i3;
            }
            i3++;
        }
        a--;
        while (a >= 0 && this.f270f[a] == i) {
            if (obj.equals(this.f271g[a << 1])) {
                return a;
            }
            a--;
        }
        return i3 ^ -1;
    }

    public V m433a(int i, V v) {
        int i2 = (i << 1) + 1;
        V v2 = this.f271g[i2];
        this.f271g[i2] = v;
        return v2;
    }

    public void m434a(int i) {
        if (this.f270f.length < i) {
            Object obj = this.f270f;
            Object obj2 = this.f271g;
            m429e(i);
            if (this.f272h > 0) {
                System.arraycopy(obj, 0, this.f270f, 0, this.f272h);
                System.arraycopy(obj2, 0, this.f271g, 0, this.f272h << 1);
            }
            C0096g.m428a(obj, obj2, this.f272h);
        }
    }

    int m435b(Object obj) {
        int i = 1;
        int i2 = this.f272h * 2;
        Object[] objArr = this.f271g;
        if (obj == null) {
            while (i < i2) {
                if (objArr[i] == null) {
                    return i >> 1;
                }
                i += 2;
            }
        } else {
            while (i < i2) {
                if (obj.equals(objArr[i])) {
                    return i >> 1;
                }
                i += 2;
            }
        }
        return -1;
    }

    public K m436b(int i) {
        return this.f271g[i << 1];
    }

    public V m437c(int i) {
        return this.f271g[(i << 1) + 1];
    }

    public void clear() {
        if (this.f272h != 0) {
            C0096g.m428a(this.f270f, this.f271g, this.f272h);
            this.f270f = C0098b.f274a;
            this.f271g = C0098b.f276c;
            this.f272h = 0;
        }
    }

    public boolean containsKey(Object obj) {
        return m431a(obj) >= 0;
    }

    public boolean containsValue(Object obj) {
        return m435b(obj) >= 0;
    }

    public V m438d(int i) {
        int i2 = 8;
        V v = this.f271g[(i << 1) + 1];
        if (this.f272h <= 1) {
            C0096g.m428a(this.f270f, this.f271g, this.f272h);
            this.f270f = C0098b.f274a;
            this.f271g = C0098b.f276c;
            this.f272h = 0;
        } else if (this.f270f.length <= 8 || this.f272h >= this.f270f.length / 3) {
            this.f272h--;
            if (i < this.f272h) {
                System.arraycopy(this.f270f, i + 1, this.f270f, i, this.f272h - i);
                System.arraycopy(this.f271g, (i + 1) << 1, this.f271g, i << 1, (this.f272h - i) << 1);
            }
            this.f271g[this.f272h << 1] = null;
            this.f271g[(this.f272h << 1) + 1] = null;
        } else {
            if (this.f272h > 8) {
                i2 = this.f272h + (this.f272h >> 1);
            }
            Object obj = this.f270f;
            Object obj2 = this.f271g;
            m429e(i2);
            this.f272h--;
            if (i > 0) {
                System.arraycopy(obj, 0, this.f270f, 0, i);
                System.arraycopy(obj2, 0, this.f271g, 0, i << 1);
            }
            if (i < this.f272h) {
                System.arraycopy(obj, i + 1, this.f270f, i, this.f272h - i);
                System.arraycopy(obj2, (i + 1) << 1, this.f271g, i << 1, (this.f272h - i) << 1);
            }
        }
        return v;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (size() != map.size()) {
            return false;
        }
        int i = 0;
        while (i < this.f272h) {
            try {
                Object b = m436b(i);
                Object c = m437c(i);
                Object obj2 = map.get(b);
                if (c == null) {
                    if (obj2 != null || !map.containsKey(b)) {
                        return false;
                    }
                } else if (!c.equals(obj2)) {
                    return false;
                }
                i++;
            } catch (NullPointerException e) {
                return false;
            } catch (ClassCastException e2) {
                return false;
            }
        }
        return true;
    }

    public V get(Object obj) {
        int a = m431a(obj);
        return a >= 0 ? this.f271g[(a << 1) + 1] : null;
    }

    public int hashCode() {
        int[] iArr = this.f270f;
        Object[] objArr = this.f271g;
        int i = this.f272h;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            Object obj = objArr[i2];
            i4 += (obj == null ? 0 : obj.hashCode()) ^ iArr[i3];
            i3++;
            i2 += 2;
        }
        return i4;
    }

    public boolean isEmpty() {
        return this.f272h <= 0;
    }

    public V put(K k, V v) {
        int a;
        int i;
        int i2 = 8;
        if (k == null) {
            a = m430a();
            i = 0;
        } else {
            i = k.hashCode();
            a = m432a((Object) k, i);
        }
        if (a >= 0) {
            int i3 = (a << 1) + 1;
            V v2 = this.f271g[i3];
            this.f271g[i3] = v;
            return v2;
        }
        a ^= -1;
        if (this.f272h >= this.f270f.length) {
            if (this.f272h >= 8) {
                i2 = this.f272h + (this.f272h >> 1);
            } else if (this.f272h < 4) {
                i2 = 4;
            }
            Object obj = this.f270f;
            Object obj2 = this.f271g;
            m429e(i2);
            if (this.f270f.length > 0) {
                System.arraycopy(obj, 0, this.f270f, 0, obj.length);
                System.arraycopy(obj2, 0, this.f271g, 0, obj2.length);
            }
            C0096g.m428a(obj, obj2, this.f272h);
        }
        if (a < this.f272h) {
            System.arraycopy(this.f270f, a, this.f270f, a + 1, this.f272h - a);
            System.arraycopy(this.f271g, a << 1, this.f271g, (a + 1) << 1, (this.f272h - a) << 1);
        }
        this.f270f[a] = i;
        this.f271g[a << 1] = k;
        this.f271g[(a << 1) + 1] = v;
        this.f272h++;
        return null;
    }

    public V remove(Object obj) {
        int a = m431a(obj);
        return a >= 0 ? m438d(a) : null;
    }

    public int size() {
        return this.f272h;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f272h * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f272h; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            C0096g b = m436b(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
            stringBuilder.append('=');
            b = m437c(i);
            if (b != this) {
                stringBuilder.append(b);
            } else {
                stringBuilder.append("(this Map)");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
