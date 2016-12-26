package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

/* renamed from: android.support.v4.widget.g */
public class C0275g extends C0262a {
    private final ListView f545a;

    public C0275g(ListView listView) {
        super(listView);
        this.f545a = listView;
    }

    public void m1312a(int i, int i2) {
        ListView listView = this.f545a;
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        if (firstVisiblePosition != -1) {
            View childAt = listView.getChildAt(0);
            if (childAt != null) {
                listView.setSelectionFromTop(firstVisiblePosition, childAt.getTop() - i2);
            }
        }
    }

    public boolean m1313e(int i) {
        return false;
    }

    public boolean m1314f(int i) {
        ListView listView = this.f545a;
        int count = listView.getCount();
        if (count == 0) {
            return false;
        }
        int childCount = listView.getChildCount();
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int i2 = firstVisiblePosition + childCount;
        if (i > 0) {
            if (i2 >= count && listView.getChildAt(childCount - 1).getBottom() <= listView.getHeight()) {
                return false;
            }
        } else if (i >= 0) {
            return false;
        } else {
            if (firstVisiblePosition <= 0 && listView.getChildAt(0).getTop() >= 0) {
                return false;
            }
        }
        return true;
    }
}
