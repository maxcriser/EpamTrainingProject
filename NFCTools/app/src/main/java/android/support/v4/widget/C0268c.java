package android.support.v4.widget;

import android.database.Cursor;
import android.widget.Filter;
import android.widget.Filter.FilterResults;

/* renamed from: android.support.v4.widget.c */
class C0268c extends Filter {
    C0266a f542a;

    /* renamed from: android.support.v4.widget.c.a */
    interface C0266a {
        Cursor m1261a();

        Cursor m1262a(CharSequence charSequence);

        void m1263a(Cursor cursor);

        CharSequence m1264c(Cursor cursor);
    }

    C0268c(C0266a c0266a) {
        this.f542a = c0266a;
    }

    public CharSequence convertResultToString(Object obj) {
        return this.f542a.m1264c((Cursor) obj);
    }

    protected FilterResults performFiltering(CharSequence charSequence) {
        Cursor a = this.f542a.m1262a(charSequence);
        FilterResults filterResults = new FilterResults();
        if (a != null) {
            filterResults.count = a.getCount();
            filterResults.values = a;
        } else {
            filterResults.count = 0;
            filterResults.values = null;
        }
        return filterResults;
    }

    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        Cursor a = this.f542a.m1261a();
        if (filterResults.values != null && filterResults.values != a) {
            this.f542a.m1263a((Cursor) filterResults.values);
        }
    }
}
