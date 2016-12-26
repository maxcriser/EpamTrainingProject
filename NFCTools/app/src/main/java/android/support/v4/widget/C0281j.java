package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: android.support.v4.widget.j */
public abstract class C0281j extends C0267b {
    private int f547j;
    private int f548k;
    private LayoutInflater f549l;

    public C0281j(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.f548k = i;
        this.f547j = i;
        this.f549l = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View m1320a(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f549l.inflate(this.f547j, viewGroup, false);
    }

    public View m1321b(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f549l.inflate(this.f548k, viewGroup, false);
    }
}
