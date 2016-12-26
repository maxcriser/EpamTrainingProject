package android.support.v7.internal.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.v4.p003c.p004a.C0078a;
import android.support.v4.view.C0187d;
import android.support.v4.view.C0210l;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuItemWrapperICS;
import android.support.v7.p009a.C0301a.C0300k;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import java.lang.reflect.Method;

/* renamed from: android.support.v7.internal.view.d */
public class C0343d extends MenuInflater {
    private static final Class<?>[] f741a;
    private static final Class<?>[] f742b;
    private final Object[] f743c;
    private final Object[] f744d;
    private Context f745e;
    private Object f746f;

    /* renamed from: android.support.v7.internal.view.d.a */
    private static class C0341a implements OnMenuItemClickListener {
        private static final Class<?>[] f712a;
        private Object f713b;
        private Method f714c;

        static {
            f712a = new Class[]{MenuItem.class};
        }

        public C0341a(Object obj, String str) {
            this.f713b = obj;
            Class cls = obj.getClass();
            try {
                this.f714c = cls.getMethod(str, f712a);
            } catch (Throwable e) {
                InflateException inflateException = new InflateException("Couldn't resolve menu item onClick handler " + str + " in class " + cls.getName());
                inflateException.initCause(e);
                throw inflateException;
            }
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            try {
                if (this.f714c.getReturnType() == Boolean.TYPE) {
                    return ((Boolean) this.f714c.invoke(this.f713b, new Object[]{menuItem})).booleanValue();
                }
                this.f714c.invoke(this.f713b, new Object[]{menuItem});
                return true;
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* renamed from: android.support.v7.internal.view.d.b */
    private class C0342b {
        final /* synthetic */ C0343d f715a;
        private Menu f716b;
        private int f717c;
        private int f718d;
        private int f719e;
        private int f720f;
        private boolean f721g;
        private boolean f722h;
        private boolean f723i;
        private int f724j;
        private int f725k;
        private CharSequence f726l;
        private CharSequence f727m;
        private int f728n;
        private char f729o;
        private char f730p;
        private int f731q;
        private boolean f732r;
        private boolean f733s;
        private boolean f734t;
        private int f735u;
        private int f736v;
        private String f737w;
        private String f738x;
        private String f739y;
        private C0187d f740z;

        public C0342b(C0343d c0343d, Menu menu) {
            this.f715a = c0343d;
            this.f716b = menu;
            m1682a();
        }

        private char m1678a(String str) {
            return str == null ? '\u0000' : str.charAt(0);
        }

        private <T> T m1680a(String str, Class<?>[] clsArr, Object[] objArr) {
            try {
                return this.f715a.f745e.getClassLoader().loadClass(str).getConstructor(clsArr).newInstance(objArr);
            } catch (Throwable e) {
                Log.w("SupportMenuInflater", "Cannot instantiate class: " + str, e);
                return null;
            }
        }

        private void m1681a(MenuItem menuItem) {
            boolean z = true;
            menuItem.setChecked(this.f732r).setVisible(this.f733s).setEnabled(this.f734t).setCheckable(this.f731q >= 1).setTitleCondensed(this.f727m).setIcon(this.f728n).setAlphabeticShortcut(this.f729o).setNumericShortcut(this.f730p);
            if (this.f735u >= 0) {
                C0210l.m929a(menuItem, this.f735u);
            }
            if (this.f739y != null) {
                if (this.f715a.f745e.isRestricted()) {
                    throw new IllegalStateException("The android:onClick attribute cannot be used within a restricted context");
                }
                menuItem.setOnMenuItemClickListener(new C0341a(this.f715a.m1694c(), this.f739y));
            }
            if (menuItem instanceof MenuItemImpl) {
                MenuItemImpl menuItemImpl = (MenuItemImpl) menuItem;
            }
            if (this.f731q >= 2) {
                if (menuItem instanceof MenuItemImpl) {
                    ((MenuItemImpl) menuItem).setExclusiveCheckable(true);
                } else if (menuItem instanceof MenuItemWrapperICS) {
                    ((MenuItemWrapperICS) menuItem).setExclusiveCheckable(true);
                }
            }
            if (this.f737w != null) {
                C0210l.m927a(menuItem, (View) m1680a(this.f737w, C0343d.f741a, this.f715a.f743c));
            } else {
                z = false;
            }
            if (this.f736v > 0) {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
                } else {
                    C0210l.m930b(menuItem, this.f736v);
                }
            }
            if (this.f740z != null) {
                C0210l.m926a(menuItem, this.f740z);
            }
        }

        public void m1682a() {
            this.f717c = 0;
            this.f718d = 0;
            this.f719e = 0;
            this.f720f = 0;
            this.f721g = true;
            this.f722h = true;
        }

        public void m1683a(AttributeSet attributeSet) {
            TypedArray obtainStyledAttributes = this.f715a.f745e.obtainStyledAttributes(attributeSet, C0300k.MenuGroup);
            this.f717c = obtainStyledAttributes.getResourceId(C0300k.MenuGroup_android_id, 0);
            this.f718d = obtainStyledAttributes.getInt(C0300k.MenuGroup_android_menuCategory, 0);
            this.f719e = obtainStyledAttributes.getInt(C0300k.MenuGroup_android_orderInCategory, 0);
            this.f720f = obtainStyledAttributes.getInt(C0300k.MenuGroup_android_checkableBehavior, 0);
            this.f721g = obtainStyledAttributes.getBoolean(C0300k.MenuGroup_android_visible, true);
            this.f722h = obtainStyledAttributes.getBoolean(C0300k.MenuGroup_android_enabled, true);
            obtainStyledAttributes.recycle();
        }

        public void m1684b() {
            this.f723i = true;
            m1681a(this.f716b.add(this.f717c, this.f724j, this.f725k, this.f726l));
        }

        public void m1685b(AttributeSet attributeSet) {
            boolean z = true;
            TypedArray obtainStyledAttributes = this.f715a.f745e.obtainStyledAttributes(attributeSet, C0300k.MenuItem);
            this.f724j = obtainStyledAttributes.getResourceId(C0300k.MenuItem_android_id, 0);
            this.f725k = (obtainStyledAttributes.getInt(C0300k.MenuItem_android_menuCategory, this.f718d) & -65536) | (obtainStyledAttributes.getInt(C0300k.MenuItem_android_orderInCategory, this.f719e) & 65535);
            this.f726l = obtainStyledAttributes.getText(C0300k.MenuItem_android_title);
            this.f727m = obtainStyledAttributes.getText(C0300k.MenuItem_android_titleCondensed);
            this.f728n = obtainStyledAttributes.getResourceId(C0300k.MenuItem_android_icon, 0);
            this.f729o = m1678a(obtainStyledAttributes.getString(C0300k.MenuItem_android_alphabeticShortcut));
            this.f730p = m1678a(obtainStyledAttributes.getString(C0300k.MenuItem_android_numericShortcut));
            if (obtainStyledAttributes.hasValue(C0300k.MenuItem_android_checkable)) {
                this.f731q = obtainStyledAttributes.getBoolean(C0300k.MenuItem_android_checkable, false) ? 1 : 0;
            } else {
                this.f731q = this.f720f;
            }
            this.f732r = obtainStyledAttributes.getBoolean(C0300k.MenuItem_android_checked, false);
            this.f733s = obtainStyledAttributes.getBoolean(C0300k.MenuItem_android_visible, this.f721g);
            this.f734t = obtainStyledAttributes.getBoolean(C0300k.MenuItem_android_enabled, this.f722h);
            this.f735u = obtainStyledAttributes.getInt(C0300k.MenuItem_showAsAction, -1);
            this.f739y = obtainStyledAttributes.getString(C0300k.MenuItem_android_onClick);
            this.f736v = obtainStyledAttributes.getResourceId(C0300k.MenuItem_actionLayout, 0);
            this.f737w = obtainStyledAttributes.getString(C0300k.MenuItem_actionViewClass);
            this.f738x = obtainStyledAttributes.getString(C0300k.MenuItem_actionProviderClass);
            if (this.f738x == null) {
                z = false;
            }
            if (z && this.f736v == 0 && this.f737w == null) {
                this.f740z = (C0187d) m1680a(this.f738x, C0343d.f742b, this.f715a.f744d);
            } else {
                if (z) {
                    Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
                }
                this.f740z = null;
            }
            obtainStyledAttributes.recycle();
            this.f723i = false;
        }

        public SubMenu m1686c() {
            this.f723i = true;
            SubMenu addSubMenu = this.f716b.addSubMenu(this.f717c, this.f724j, this.f725k, this.f726l);
            m1681a(addSubMenu.getItem());
            return addSubMenu;
        }

        public boolean m1687d() {
            return this.f723i;
        }
    }

    static {
        f741a = new Class[]{Context.class};
        f742b = f741a;
    }

    public C0343d(Context context) {
        super(context);
        this.f745e = context;
        this.f743c = new Object[]{context};
        this.f744d = this.f743c;
    }

    private Object m1689a(Object obj) {
        return (!(obj instanceof Activity) && (obj instanceof ContextWrapper)) ? m1689a(((ContextWrapper) obj).getBaseContext()) : obj;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1690a(org.xmlpull.v1.XmlPullParser r11, AttributeSet r12, Menu r13) {
        /*
        r10 = this;
        r4 = 0;
        r1 = 1;
        r6 = 0;
        r7 = new android.support.v7.internal.view.d$b;
        r7.<init>(r10, r13);
        r0 = r11.getEventType();
    L_0x000c:
        r2 = 2;
        if (r0 != r2) goto L_0x004a;
    L_0x000f:
        r0 = r11.getName();
        r2 = "menu";
        r2 = r0.equals(r2);
        if (r2 == 0) goto L_0x0031;
    L_0x001b:
        r0 = r11.next();
    L_0x001f:
        r2 = r4;
        r5 = r6;
        r3 = r0;
        r0 = r6;
    L_0x0023:
        if (r0 != 0) goto L_0x00e1;
    L_0x0025:
        switch(r3) {
            case 1: goto L_0x00d9;
            case 2: goto L_0x0051;
            case 3: goto L_0x0087;
            default: goto L_0x0028;
        };
    L_0x0028:
        r3 = r5;
    L_0x0029:
        r5 = r11.next();
        r9 = r3;
        r3 = r5;
        r5 = r9;
        goto L_0x0023;
    L_0x0031:
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Expecting menu, got ";
        r2 = r2.append(r3);
        r0 = r2.append(r0);
        r0 = r0.toString();
        r1.<init>(r0);
        throw r1;
    L_0x004a:
        r0 = r11.next();
        if (r0 != r1) goto L_0x000c;
    L_0x0050:
        goto L_0x001f;
    L_0x0051:
        if (r5 == 0) goto L_0x0055;
    L_0x0053:
        r3 = r5;
        goto L_0x0029;
    L_0x0055:
        r3 = r11.getName();
        r8 = "group";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0066;
    L_0x0061:
        r7.m1683a(r12);
        r3 = r5;
        goto L_0x0029;
    L_0x0066:
        r8 = "item";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0073;
    L_0x006e:
        r7.m1685b(r12);
        r3 = r5;
        goto L_0x0029;
    L_0x0073:
        r8 = "menu";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x0084;
    L_0x007b:
        r3 = r7.m1686c();
        r10.m1690a(r11, r12, r3);
        r3 = r5;
        goto L_0x0029;
    L_0x0084:
        r2 = r3;
        r3 = r1;
        goto L_0x0029;
    L_0x0087:
        r3 = r11.getName();
        if (r5 == 0) goto L_0x0096;
    L_0x008d:
        r8 = r3.equals(r2);
        if (r8 == 0) goto L_0x0096;
    L_0x0093:
        r2 = r4;
        r3 = r6;
        goto L_0x0029;
    L_0x0096:
        r8 = "group";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x00a3;
    L_0x009e:
        r7.m1682a();
        r3 = r5;
        goto L_0x0029;
    L_0x00a3:
        r8 = "item";
        r8 = r3.equals(r8);
        if (r8 == 0) goto L_0x00cd;
    L_0x00ab:
        r3 = r7.m1687d();
        if (r3 != 0) goto L_0x0028;
    L_0x00b1:
        r3 = r7.f740z;
        if (r3 == 0) goto L_0x00c7;
    L_0x00b7:
        r3 = r7.f740z;
        r3 = r3.hasSubMenu();
        if (r3 == 0) goto L_0x00c7;
    L_0x00c1:
        r7.m1686c();
        r3 = r5;
        goto L_0x0029;
    L_0x00c7:
        r7.m1684b();
        r3 = r5;
        goto L_0x0029;
    L_0x00cd:
        r8 = "menu";
        r3 = r3.equals(r8);
        if (r3 == 0) goto L_0x0028;
    L_0x00d5:
        r0 = r1;
        r3 = r5;
        goto L_0x0029;
    L_0x00d9:
        r0 = new java.lang.RuntimeException;
        r1 = "Unexpected end of document";
        r0.<init>(r1);
        throw r0;
    L_0x00e1:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.internal.view.d.a(org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.view.Menu):void");
    }

    private Object m1694c() {
        if (this.f746f == null) {
            this.f746f = m1689a(this.f745e);
        }
        return this.f746f;
    }

    public void inflate(int i, Menu menu) {
        if (menu instanceof C0078a) {
            XmlResourceParser xmlResourceParser = null;
            try {
                xmlResourceParser = this.f745e.getResources().getLayout(i);
                m1690a(xmlResourceParser, Xml.asAttributeSet(xmlResourceParser), menu);
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            } catch (Throwable e) {
                throw new InflateException("Error inflating menu XML", e);
            } catch (Throwable e2) {
                throw new InflateException("Error inflating menu XML", e2);
            } catch (Throwable th) {
                if (xmlResourceParser != null) {
                    xmlResourceParser.close();
                }
            }
        } else {
            super.inflate(i, menu);
        }
    }
}
