package android.support.v4.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.view.r */
public abstract class C0044r {
    private DataSetObservable f202a;

    public C0044r() {
        this.f202a = new DataSetObservable();
    }

    public int m240a(Object obj) {
        return -1;
    }

    public Parcelable m241a() {
        return null;
    }

    public Object m242a(View view, int i) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object m243a(ViewGroup viewGroup, int i) {
        return m242a((View) viewGroup, i);
    }

    public void m244a(DataSetObserver dataSetObserver) {
        this.f202a.registerObserver(dataSetObserver);
    }

    public void m245a(Parcelable parcelable, ClassLoader classLoader) {
    }

    public void m246a(View view) {
    }

    public void m247a(View view, int i, Object obj) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void m248a(ViewGroup viewGroup) {
        m246a((View) viewGroup);
    }

    public void m249a(ViewGroup viewGroup, int i, Object obj) {
        m247a((View) viewGroup, i, obj);
    }

    public abstract boolean m250a(View view, Object obj);

    public abstract int m251b();

    public void m252b(DataSetObserver dataSetObserver) {
        this.f202a.unregisterObserver(dataSetObserver);
    }

    public void m253b(View view) {
    }

    public void m254b(View view, int i, Object obj) {
    }

    public void m255b(ViewGroup viewGroup) {
        m253b((View) viewGroup);
    }

    public void m256b(ViewGroup viewGroup, int i, Object obj) {
        m254b((View) viewGroup, i, obj);
    }

    public CharSequence m257c(int i) {
        return null;
    }

    public float m258d(int i) {
        return 1.0f;
    }
}
