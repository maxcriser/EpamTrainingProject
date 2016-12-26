package android.support.v4.p007f;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* renamed from: android.support.v4.f.a */
public class C0097a<K, V> extends C0096g<K, V> implements Map<K, V> {
    C0094f<K, V> f273a;

    /* renamed from: android.support.v4.f.a.1 */
    class C00951 extends C0094f<K, V> {
        final /* synthetic */ C0097a f265a;

        C00951(C0097a c0097a) {
            this.f265a = c0097a;
        }

        protected int m419a() {
            return this.f265a.h;
        }

        protected int m420a(Object obj) {
            return this.f265a.m431a(obj);
        }

        protected Object m421a(int i, int i2) {
            return this.f265a.g[(i << 1) + i2];
        }

        protected V m422a(int i, V v) {
            return this.f265a.m433a(i, (Object) v);
        }

        protected void m423a(int i) {
            this.f265a.m438d(i);
        }

        protected void m424a(K k, V v) {
            this.f265a.put(k, v);
        }

        protected int m425b(Object obj) {
            return this.f265a.m435b(obj);
        }

        protected Map<K, V> m426b() {
            return this.f265a;
        }

        protected void m427c() {
            this.f265a.clear();
        }
    }

    private C0094f<K, V> m439b() {
        if (this.f273a == null) {
            this.f273a = new C00951(this);
        }
        return this.f273a;
    }

    public boolean m440a(Collection<?> collection) {
        return C0094f.m404c(this, collection);
    }

    public Set<Entry<K, V>> entrySet() {
        return m439b().m416d();
    }

    public Set<K> keySet() {
        return m439b().m417e();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        m434a(this.h + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Collection<V> values() {
        return m439b().m418f();
    }
}
