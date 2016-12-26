package android.support.v4.p007f;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: android.support.v4.f.f */
abstract class C0094f<K, V> {
    C0103b f262b;
    C0104c f263c;
    C0106e f264d;

    /* renamed from: android.support.v4.f.f.a */
    final class C0102a<T> implements Iterator<T> {
        final int f287a;
        int f288b;
        int f289c;
        boolean f290d;
        final /* synthetic */ C0094f f291e;

        C0102a(C0094f c0094f, int i) {
            this.f291e = c0094f;
            this.f290d = false;
            this.f287a = i;
            this.f288b = c0094f.m405a();
        }

        public boolean hasNext() {
            return this.f289c < this.f288b;
        }

        public T next() {
            T a = this.f291e.m407a(this.f289c, this.f287a);
            this.f289c++;
            this.f290d = true;
            return a;
        }

        public void remove() {
            if (this.f290d) {
                this.f289c--;
                this.f288b--;
                this.f290d = false;
                this.f291e.m409a(this.f289c);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: android.support.v4.f.f.b */
    final class C0103b implements Set<Entry<K, V>> {
        final /* synthetic */ C0094f f292a;

        C0103b(C0094f c0094f) {
            this.f292a = c0094f;
        }

        public boolean m454a(Entry<K, V> entry) {
            throw new UnsupportedOperationException();
        }

        public /* synthetic */ boolean add(Object obj) {
            return m454a((Entry) obj);
        }

        public boolean addAll(Collection<? extends Entry<K, V>> collection) {
            int a = this.f292a.m405a();
            for (Entry entry : collection) {
                this.f292a.m410a(entry.getKey(), entry.getValue());
            }
            return a != this.f292a.m405a();
        }

        public void clear() {
            this.f292a.m415c();
        }

        public boolean contains(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            int a = this.f292a.m406a(entry.getKey());
            return a >= 0 ? C0098b.m443a(this.f292a.m407a(a, 1), entry.getValue()) : false;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object obj) {
            return C0094f.m402a((Set) this, obj);
        }

        public int hashCode() {
            int a = this.f292a.m405a() - 1;
            int i = 0;
            while (a >= 0) {
                Object a2 = this.f292a.m407a(a, 0);
                Object a3 = this.f292a.m407a(a, 1);
                a--;
                i += (a3 == null ? 0 : a3.hashCode()) ^ (a2 == null ? 0 : a2.hashCode());
            }
            return i;
        }

        public boolean isEmpty() {
            return this.f292a.m405a() == 0;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new C0105d(this.f292a);
        }

        public boolean remove(Object obj) {
            throw new UnsupportedOperationException();
        }

        public boolean removeAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public boolean retainAll(Collection<?> collection) {
            throw new UnsupportedOperationException();
        }

        public int size() {
            return this.f292a.m405a();
        }

        public Object[] toArray() {
            throw new UnsupportedOperationException();
        }

        public <T> T[] toArray(T[] tArr) {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: android.support.v4.f.f.c */
    final class C0104c implements Set<K> {
        final /* synthetic */ C0094f f293a;

        C0104c(C0094f c0094f) {
            this.f293a = c0094f;
        }

        public boolean add(K k) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends K> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f293a.m415c();
        }

        public boolean contains(Object obj) {
            return this.f293a.m406a(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            return C0094f.m401a(this.f293a.m413b(), (Collection) collection);
        }

        public boolean equals(Object obj) {
            return C0094f.m402a((Set) this, obj);
        }

        public int hashCode() {
            int i = 0;
            for (int a = this.f293a.m405a() - 1; a >= 0; a--) {
                Object a2 = this.f293a.m407a(a, 0);
                i += a2 == null ? 0 : a2.hashCode();
            }
            return i;
        }

        public boolean isEmpty() {
            return this.f293a.m405a() == 0;
        }

        public Iterator<K> iterator() {
            return new C0102a(this.f293a, 0);
        }

        public boolean remove(Object obj) {
            int a = this.f293a.m406a(obj);
            if (a < 0) {
                return false;
            }
            this.f293a.m409a(a);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            return C0094f.m403b(this.f293a.m413b(), collection);
        }

        public boolean retainAll(Collection<?> collection) {
            return C0094f.m404c(this.f293a.m413b(), collection);
        }

        public int size() {
            return this.f293a.m405a();
        }

        public Object[] toArray() {
            return this.f293a.m414b(0);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f293a.m411a((Object[]) tArr, 0);
        }
    }

    /* renamed from: android.support.v4.f.f.d */
    final class C0105d implements Iterator<Entry<K, V>>, Entry<K, V> {
        int f294a;
        int f295b;
        boolean f296c;
        final /* synthetic */ C0094f f297d;

        C0105d(C0094f c0094f) {
            this.f297d = c0094f;
            this.f296c = false;
            this.f294a = c0094f.m405a() - 1;
            this.f295b = -1;
        }

        public Entry<K, V> m455a() {
            this.f295b++;
            this.f296c = true;
            return this;
        }

        public final boolean equals(Object obj) {
            boolean z = true;
            if (!this.f296c) {
                throw new IllegalStateException("This container does not support retaining Map.Entry objects");
            } else if (!(obj instanceof Entry)) {
                return false;
            } else {
                Entry entry = (Entry) obj;
                if (!(C0098b.m443a(entry.getKey(), this.f297d.m407a(this.f295b, 0)) && C0098b.m443a(entry.getValue(), this.f297d.m407a(this.f295b, 1)))) {
                    z = false;
                }
                return z;
            }
        }

        public K getKey() {
            if (this.f296c) {
                return this.f297d.m407a(this.f295b, 0);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public V getValue() {
            if (this.f296c) {
                return this.f297d.m407a(this.f295b, 1);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public boolean hasNext() {
            return this.f295b < this.f294a;
        }

        public final int hashCode() {
            int i = 0;
            if (this.f296c) {
                Object a = this.f297d.m407a(this.f295b, 0);
                Object a2 = this.f297d.m407a(this.f295b, 1);
                int hashCode = a == null ? 0 : a.hashCode();
                if (a2 != null) {
                    i = a2.hashCode();
                }
                return i ^ hashCode;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public /* synthetic */ Object next() {
            return m455a();
        }

        public void remove() {
            if (this.f296c) {
                this.f297d.m409a(this.f295b);
                this.f295b--;
                this.f294a--;
                this.f296c = false;
                return;
            }
            throw new IllegalStateException();
        }

        public V setValue(V v) {
            if (this.f296c) {
                return this.f297d.m408a(this.f295b, (Object) v);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* renamed from: android.support.v4.f.f.e */
    final class C0106e implements Collection<V> {
        final /* synthetic */ C0094f f298a;

        C0106e(C0094f c0094f) {
            this.f298a = c0094f;
        }

        public boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        public boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            this.f298a.m415c();
        }

        public boolean contains(Object obj) {
            return this.f298a.m412b(obj) >= 0;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        public boolean isEmpty() {
            return this.f298a.m405a() == 0;
        }

        public Iterator<V> iterator() {
            return new C0102a(this.f298a, 1);
        }

        public boolean remove(Object obj) {
            int b = this.f298a.m412b(obj);
            if (b < 0) {
                return false;
            }
            this.f298a.m409a(b);
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            int i = 0;
            int a = this.f298a.m405a();
            boolean z = false;
            while (i < a) {
                if (collection.contains(this.f298a.m407a(i, 1))) {
                    this.f298a.m409a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            int i = 0;
            int a = this.f298a.m405a();
            boolean z = false;
            while (i < a) {
                if (!collection.contains(this.f298a.m407a(i, 1))) {
                    this.f298a.m409a(i);
                    i--;
                    a--;
                    z = true;
                }
                i++;
            }
            return z;
        }

        public int size() {
            return this.f298a.m405a();
        }

        public Object[] toArray() {
            return this.f298a.m414b(1);
        }

        public <T> T[] toArray(T[] tArr) {
            return this.f298a.m411a((Object[]) tArr, 1);
        }
    }

    C0094f() {
    }

    public static <K, V> boolean m401a(Map<K, V> map, Collection<?> collection) {
        for (Object containsKey : collection) {
            if (!map.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean m402a(Set<T> set, Object obj) {
        boolean z = true;
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (!(set.size() == set2.size() && set.containsAll(set2))) {
                z = false;
            }
            return z;
        } catch (NullPointerException e) {
            return false;
        } catch (ClassCastException e2) {
            return false;
        }
    }

    public static <K, V> boolean m403b(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        for (Object remove : collection) {
            map.remove(remove);
        }
        return size != map.size();
    }

    public static <K, V> boolean m404c(Map<K, V> map, Collection<?> collection) {
        int size = map.size();
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            if (!collection.contains(it.next())) {
                it.remove();
            }
        }
        return size != map.size();
    }

    protected abstract int m405a();

    protected abstract int m406a(Object obj);

    protected abstract Object m407a(int i, int i2);

    protected abstract V m408a(int i, V v);

    protected abstract void m409a(int i);

    protected abstract void m410a(K k, V v);

    public <T> T[] m411a(T[] tArr, int i) {
        int a = m405a();
        T[] tArr2 = tArr.length < a ? (Object[]) Array.newInstance(tArr.getClass().getComponentType(), a) : tArr;
        for (int i2 = 0; i2 < a; i2++) {
            tArr2[i2] = m407a(i2, i);
        }
        if (tArr2.length > a) {
            tArr2[a] = null;
        }
        return tArr2;
    }

    protected abstract int m412b(Object obj);

    protected abstract Map<K, V> m413b();

    public Object[] m414b(int i) {
        int a = m405a();
        Object[] objArr = new Object[a];
        for (int i2 = 0; i2 < a; i2++) {
            objArr[i2] = m407a(i2, i);
        }
        return objArr;
    }

    protected abstract void m415c();

    public Set<Entry<K, V>> m416d() {
        if (this.f262b == null) {
            this.f262b = new C0103b(this);
        }
        return this.f262b;
    }

    public Set<K> m417e() {
        if (this.f263c == null) {
            this.f263c = new C0104c(this);
        }
        return this.f263c;
    }

    public Collection<V> m418f() {
        if (this.f264d == null) {
            this.f264d = new C0106e(this);
        }
        return this.f264d;
    }
}
