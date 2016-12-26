package android.support.v4.widget;

import android.os.Build.VERSION;
import android.view.View;
import android.widget.PopupWindow;

/* renamed from: android.support.v4.widget.h */
public class C0279h {
    static final C0276c f546a;

    /* renamed from: android.support.v4.widget.h.c */
    interface C0276c {
        void m1315a(PopupWindow popupWindow, View view, int i, int i2, int i3);
    }

    /* renamed from: android.support.v4.widget.h.a */
    static class C0277a implements C0276c {
        C0277a() {
        }

        public void m1316a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
            popupWindow.showAsDropDown(view, i, i2);
        }
    }

    /* renamed from: android.support.v4.widget.h.b */
    static class C0278b extends C0277a {
        C0278b() {
        }

        public void m1317a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
            C0280i.m1319a(popupWindow, view, i, i2, i3);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            f546a = new C0278b();
        } else {
            f546a = new C0277a();
        }
    }

    public static void m1318a(PopupWindow popupWindow, View view, int i, int i2, int i3) {
        f546a.m1315a(popupWindow, view, i, i2, i3);
    }
}
