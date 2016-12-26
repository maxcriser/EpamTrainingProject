package com.wakdev.wdsortablelist;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import com.wakdev.wdsortablelist.DragSortListView.C0662i;

/* renamed from: com.wakdev.wdsortablelist.d */
public class C0667d implements C0662i {
    private Bitmap f2560a;
    private ImageView f2561b;
    private int f2562c;
    private ListView f2563d;

    public C0667d(ListView listView) {
        this.f2562c = -16777216;
        this.f2563d = listView;
    }

    public void m3200a(View view) {
        ((ImageView) view).setImageDrawable(null);
        this.f2560a.recycle();
        this.f2560a = null;
    }

    public void m3201a(View view, Point point, Point point2) {
    }

    public View m3202c(int i) {
        View childAt = this.f2563d.getChildAt((this.f2563d.getHeaderViewsCount() + i) - this.f2563d.getFirstVisiblePosition());
        if (childAt == null) {
            return null;
        }
        childAt.setPressed(false);
        childAt.setDrawingCacheEnabled(true);
        this.f2560a = Bitmap.createBitmap(childAt.getDrawingCache());
        childAt.setDrawingCacheEnabled(false);
        if (this.f2561b == null) {
            this.f2561b = new ImageView(this.f2563d.getContext());
        }
        this.f2561b.setBackgroundColor(this.f2562c);
        this.f2561b.setPadding(0, 0, 0, 0);
        this.f2561b.setImageBitmap(this.f2560a);
        this.f2561b.setLayoutParams(new LayoutParams(childAt.getWidth(), childAt.getHeight()));
        return this.f2561b;
    }

    public void m3203d(int i) {
        this.f2562c = i;
    }
}
