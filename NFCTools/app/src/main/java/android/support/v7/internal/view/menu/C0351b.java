package android.support.v7.internal.view.menu;

/* renamed from: android.support.v7.internal.view.menu.b */
class C0351b<T> {
    final T mWrappedObject;

    C0351b(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        }
        this.mWrappedObject = t;
    }

    public T getWrappedObject() {
        return this.mWrappedObject;
    }
}
