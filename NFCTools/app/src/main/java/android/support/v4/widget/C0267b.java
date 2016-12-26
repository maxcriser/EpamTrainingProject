package android.support.v4.widget;

import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.widget.C0268c.C0266a;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.FilterQueryProvider;
import android.widget.Filterable;

/* renamed from: android.support.v4.widget.b */
public abstract class C0267b extends BaseAdapter implements C0266a, Filterable {
    protected boolean f533a;
    protected boolean f534b;
    protected Cursor f535c;
    protected Context f536d;
    protected int f537e;
    protected C0264a f538f;
    protected DataSetObserver f539g;
    protected C0268c f540h;
    protected FilterQueryProvider f541i;

    /* renamed from: android.support.v4.widget.b.a */
    private class C0264a extends ContentObserver {
        final /* synthetic */ C0267b f531a;

        public C0264a(C0267b c0267b) {
            this.f531a = c0267b;
            super(new Handler());
        }

        public boolean deliverSelfNotifications() {
            return true;
        }

        public void onChange(boolean z) {
            this.f531a.m1273b();
        }
    }

    /* renamed from: android.support.v4.widget.b.b */
    private class C0265b extends DataSetObserver {
        final /* synthetic */ C0267b f532a;

        private C0265b(C0267b c0267b) {
            this.f532a = c0267b;
        }

        public void onChanged() {
            this.f532a.f533a = true;
            this.f532a.notifyDataSetChanged();
        }

        public void onInvalidated() {
            this.f532a.f533a = false;
            this.f532a.notifyDataSetInvalidated();
        }
    }

    public C0267b(Context context, Cursor cursor, boolean z) {
        m1268a(context, cursor, z ? 1 : 2);
    }

    public Cursor m1265a() {
        return this.f535c;
    }

    public Cursor m1266a(CharSequence charSequence) {
        return this.f541i != null ? this.f541i.runQuery(charSequence) : this.f535c;
    }

    public abstract View m1267a(Context context, Cursor cursor, ViewGroup viewGroup);

    void m1268a(Context context, Cursor cursor, int i) {
        boolean z = true;
        if ((i & 1) == 1) {
            i |= 2;
            this.f534b = true;
        } else {
            this.f534b = false;
        }
        if (cursor == null) {
            z = false;
        }
        this.f535c = cursor;
        this.f533a = z;
        this.f536d = context;
        this.f537e = z ? cursor.getColumnIndexOrThrow("_id") : -1;
        if ((i & 2) == 2) {
            this.f538f = new C0264a(this);
            this.f539g = new C0265b();
        } else {
            this.f538f = null;
            this.f539g = null;
        }
        if (z) {
            if (this.f538f != null) {
                cursor.registerContentObserver(this.f538f);
            }
            if (this.f539g != null) {
                cursor.registerDataSetObserver(this.f539g);
            }
        }
    }

    public void m1269a(Cursor cursor) {
        Cursor b = m1271b(cursor);
        if (b != null) {
            b.close();
        }
    }

    public abstract void m1270a(View view, Context context, Cursor cursor);

    public Cursor m1271b(Cursor cursor) {
        if (cursor == this.f535c) {
            return null;
        }
        Cursor cursor2 = this.f535c;
        if (cursor2 != null) {
            if (this.f538f != null) {
                cursor2.unregisterContentObserver(this.f538f);
            }
            if (this.f539g != null) {
                cursor2.unregisterDataSetObserver(this.f539g);
            }
        }
        this.f535c = cursor;
        if (cursor != null) {
            if (this.f538f != null) {
                cursor.registerContentObserver(this.f538f);
            }
            if (this.f539g != null) {
                cursor.registerDataSetObserver(this.f539g);
            }
            this.f537e = cursor.getColumnIndexOrThrow("_id");
            this.f533a = true;
            notifyDataSetChanged();
            return cursor2;
        }
        this.f537e = -1;
        this.f533a = false;
        notifyDataSetInvalidated();
        return cursor2;
    }

    public View m1272b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return m1267a(context, cursor, viewGroup);
    }

    protected void m1273b() {
        if (this.f534b && this.f535c != null && !this.f535c.isClosed()) {
            this.f533a = this.f535c.requery();
        }
    }

    public CharSequence m1274c(Cursor cursor) {
        return cursor == null ? "" : cursor.toString();
    }

    public int getCount() {
        return (!this.f533a || this.f535c == null) ? 0 : this.f535c.getCount();
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (!this.f533a) {
            return null;
        }
        this.f535c.moveToPosition(i);
        if (view == null) {
            view = m1272b(this.f536d, this.f535c, viewGroup);
        }
        m1270a(view, this.f536d, this.f535c);
        return view;
    }

    public Filter getFilter() {
        if (this.f540h == null) {
            this.f540h = new C0268c(this);
        }
        return this.f540h;
    }

    public Object getItem(int i) {
        if (!this.f533a || this.f535c == null) {
            return null;
        }
        this.f535c.moveToPosition(i);
        return this.f535c;
    }

    public long getItemId(int i) {
        return (this.f533a && this.f535c != null && this.f535c.moveToPosition(i)) ? this.f535c.getLong(this.f537e) : 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (!this.f533a) {
            throw new IllegalStateException("this should only be called when the cursor is valid");
        } else if (this.f535c.moveToPosition(i)) {
            if (view == null) {
                view = m1267a(this.f536d, this.f535c, viewGroup);
            }
            m1270a(view, this.f536d, this.f535c);
            return view;
        } else {
            throw new IllegalStateException("couldn't move cursor to position " + i);
        }
    }

    public boolean hasStableIds() {
        return true;
    }
}
