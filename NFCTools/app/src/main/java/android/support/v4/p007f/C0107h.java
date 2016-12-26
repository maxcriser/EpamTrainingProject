package android.support.v4.p007f;

/* renamed from: android.support.v4.f.h */
public class C0107h<E> implements Cloneable {
    private static final Object f299a;
    private boolean f300b;
    private int[] f301c;
    private Object[] f302d;
    private int f303e;

    static {
        f299a = new Object();
    }

    public C0107h() {
        this(10);
    }

    public C0107h(int i) {
        this.f300b = false;
        if (i == 0) {
            this.f301c = C0098b.f274a;
            this.f302d = C0098b.f276c;
        } else {
            int a = C0098b.m441a(i);
            this.f301c = new int[a];
            this.f302d = new Object[a];
        }
        this.f303e = 0;
    }

    private void m456d() {
        int i = this.f303e;
        int[] iArr = this.f301c;
        Object[] objArr = this.f302d;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (obj != f299a) {
                if (i3 != i2) {
                    iArr[i2] = iArr[i3];
                    objArr[i2] = obj;
                    objArr[i3] = null;
                }
                i2++;
            }
        }
        this.f300b = false;
        this.f303e = i2;
    }

    public int m457a(int i) {
        if (this.f300b) {
            m456d();
        }
        return this.f301c[i];
    }

    public C0107h<E> m458a() {
        try {
            C0107h<E> c0107h = (C0107h) super.clone();
            try {
                c0107h.f301c = (int[]) this.f301c.clone();
                c0107h.f302d = (Object[]) this.f302d.clone();
                return c0107h;
            } catch (CloneNotSupportedException e) {
                return c0107h;
            }
        } catch (CloneNotSupportedException e2) {
            return null;
        }
    }

    public int m459b() {
        if (this.f300b) {
            m456d();
        }
        return this.f303e;
    }

    public E m460b(int i) {
        if (this.f300b) {
            m456d();
        }
        return this.f302d[i];
    }

    public void m461c() {
        int i = this.f303e;
        Object[] objArr = this.f302d;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        this.f303e = 0;
        this.f300b = false;
    }

    public /* synthetic */ Object clone() {
        return m458a();
    }

    public String toString() {
        if (m459b() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.f303e * 28);
        stringBuilder.append('{');
        for (int i = 0; i < this.f303e; i++) {
            if (i > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(m457a(i));
            stringBuilder.append('=');
            C0107h b = m460b(i);
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
