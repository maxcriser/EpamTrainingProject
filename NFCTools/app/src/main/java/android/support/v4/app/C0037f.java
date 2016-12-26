package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.p007f.C0096g;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import com.wakdev.nfctools.C0628m.C0627j;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;

/* renamed from: android.support.v4.app.f */
public class C0037f extends Activity {
    final Handler f156a;
    final C0043i f157b;
    final C0013g f158c;
    boolean f159d;
    boolean f160e;
    boolean f161f;
    boolean f162g;
    boolean f163h;
    boolean f164i;
    boolean f165j;
    boolean f166k;
    C0096g<String, C0055n> f167l;
    C0055n f168m;

    /* renamed from: android.support.v4.app.f.1 */
    class C00341 extends Handler {
        final /* synthetic */ C0037f f149a;

        C00341(C0037f c0037f) {
            this.f149a = c0037f;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case C0627j.View_paddingStart /*1*/:
                    if (this.f149a.f161f) {
                        this.f149a.m172a(false);
                    }
                case C0627j.View_paddingEnd /*2*/:
                    this.f149a.e_();
                    this.f149a.f157b.m223e();
                default:
                    super.handleMessage(message);
            }
        }
    }

    /* renamed from: android.support.v4.app.f.2 */
    class C00352 implements C0013g {
        final /* synthetic */ C0037f f150a;

        C00352(C0037f c0037f) {
            this.f150a = c0037f;
        }

        public View m165a(int i) {
            return this.f150a.findViewById(i);
        }

        public boolean m166a() {
            Window window = this.f150a.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    /* renamed from: android.support.v4.app.f.a */
    static final class C0036a {
        Object f151a;
        Object f152b;
        C0096g<String, Object> f153c;
        ArrayList<Fragment> f154d;
        C0096g<String, C0055n> f155e;

        C0036a() {
        }
    }

    public C0037f() {
        this.f156a = new C00341(this);
        this.f157b = new C0043i();
        this.f158c = new C00352(this);
    }

    private static String m167a(View view) {
        char c = 'F';
        char c2 = '.';
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(view.getClass().getName());
        stringBuilder.append('{');
        stringBuilder.append(Integer.toHexString(System.identityHashCode(view)));
        stringBuilder.append(' ');
        switch (view.getVisibility()) {
            case C0627j.View_android_focusable /*0*/:
                stringBuilder.append('V');
                break;
            case C0627j.Toolbar_contentInsetStart /*4*/:
                stringBuilder.append('I');
                break;
            case C0627j.Toolbar_popupTheme /*8*/:
                stringBuilder.append('G');
                break;
            default:
                stringBuilder.append('.');
                break;
        }
        stringBuilder.append(view.isFocusable() ? 'F' : '.');
        stringBuilder.append(view.isEnabled() ? 'E' : '.');
        stringBuilder.append(view.willNotDraw() ? '.' : 'D');
        stringBuilder.append(view.isHorizontalScrollBarEnabled() ? 'H' : '.');
        stringBuilder.append(view.isVerticalScrollBarEnabled() ? 'V' : '.');
        stringBuilder.append(view.isClickable() ? 'C' : '.');
        stringBuilder.append(view.isLongClickable() ? 'L' : '.');
        stringBuilder.append(' ');
        if (!view.isFocused()) {
            c = '.';
        }
        stringBuilder.append(c);
        stringBuilder.append(view.isSelected() ? 'S' : '.');
        if (view.isPressed()) {
            c2 = 'P';
        }
        stringBuilder.append(c2);
        stringBuilder.append(' ');
        stringBuilder.append(view.getLeft());
        stringBuilder.append(',');
        stringBuilder.append(view.getTop());
        stringBuilder.append('-');
        stringBuilder.append(view.getRight());
        stringBuilder.append(',');
        stringBuilder.append(view.getBottom());
        int id = view.getId();
        if (id != -1) {
            stringBuilder.append(" #");
            stringBuilder.append(Integer.toHexString(id));
            Resources resources = view.getResources();
            if (!(id == 0 || resources == null)) {
                String str;
                switch (-16777216 & id) {
                    case 16777216:
                        str = "android";
                        break;
                    case 2130706432:
                        str = "app";
                        break;
                    default:
                        try {
                            str = resources.getResourcePackageName(id);
                            break;
                        } catch (NotFoundException e) {
                            break;
                        }
                }
                String resourceTypeName = resources.getResourceTypeName(id);
                String resourceEntryName = resources.getResourceEntryName(id);
                stringBuilder.append(" ");
                stringBuilder.append(str);
                stringBuilder.append(":");
                stringBuilder.append(resourceTypeName);
                stringBuilder.append("/");
                stringBuilder.append(resourceEntryName);
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    private void m168a(String str, PrintWriter printWriter, View view) {
        printWriter.print(str);
        if (view == null) {
            printWriter.println("null");
            return;
        }
        printWriter.println(C0037f.m167a(view));
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            if (childCount > 0) {
                String str2 = str + "  ";
                for (int i = 0; i < childCount; i++) {
                    m168a(str2, printWriter, viewGroup.getChildAt(i));
                }
            }
        }
    }

    C0055n m169a(String str, boolean z, boolean z2) {
        if (this.f167l == null) {
            this.f167l = new C0096g();
        }
        C0055n c0055n = (C0055n) this.f167l.get(str);
        if (c0055n != null) {
            c0055n.m299a(this);
            return c0055n;
        } else if (!z2) {
            return c0055n;
        } else {
            c0055n = new C0055n(str, this, z);
            this.f167l.put(str, c0055n);
            return c0055n;
        }
    }

    public void m170a(Fragment fragment) {
    }

    void m171a(String str) {
        if (this.f167l != null) {
            C0055n c0055n = (C0055n) this.f167l.get(str);
            if (c0055n != null && !c0055n.f248g) {
                c0055n.m308h();
                this.f167l.remove(str);
            }
        }
    }

    void m172a(boolean z) {
        if (!this.f162g) {
            this.f162g = true;
            this.f163h = z;
            this.f156a.removeMessages(1);
            f_();
        }
    }

    protected boolean m173a(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    public void a_() {
        C0022a.m114b(this);
    }

    public void b_() {
        if (VERSION.SDK_INT >= 11) {
            C0024c.m116a(this);
        } else {
            this.f164i = true;
        }
    }

    public Object c_() {
        return null;
    }

    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String str2;
        if (VERSION.SDK_INT >= 11) {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.f159d);
            printWriter.print("mResumed=");
            printWriter.print(this.f160e);
            printWriter.print(" mStopped=");
            printWriter.print(this.f161f);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.f162g);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.f166k);
        } else {
            printWriter.print(str);
            printWriter.print("Local FragmentActivity ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this)));
            printWriter.println(" State:");
            str2 = str + "  ";
            printWriter.print(str2);
            printWriter.print("mCreated=");
            printWriter.print(this.f159d);
            printWriter.print("mResumed=");
            printWriter.print(this.f160e);
            printWriter.print(" mStopped=");
            printWriter.print(this.f161f);
            printWriter.print(" mReallyStopped=");
            printWriter.println(this.f162g);
            printWriter.print(str2);
            printWriter.print("mLoadersStarted=");
            printWriter.println(this.f166k);
        }
        if (this.f168m != null) {
            printWriter.print(str);
            printWriter.print("Loader Manager ");
            printWriter.print(Integer.toHexString(System.identityHashCode(this.f168m)));
            printWriter.println(":");
            this.f168m.m300a(str + "  ", fileDescriptor, printWriter, strArr);
        }
        this.f157b.m203a(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.println("View Hierarchy:");
        m168a(str + "  ", printWriter, getWindow().getDecorView());
    }

    protected void e_() {
        this.f157b.m232m();
    }

    public C0039h m174f() {
        return this.f157b;
    }

    void f_() {
        if (this.f166k) {
            this.f166k = false;
            if (this.f168m != null) {
                if (this.f163h) {
                    this.f168m.m304d();
                } else {
                    this.f168m.m303c();
                }
            }
        }
        this.f157b.m235p();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f157b.m228i();
        int i3 = i >> 16;
        if (i3 != 0) {
            i3--;
            if (this.f157b.f182f == null || i3 < 0 || i3 >= this.f157b.f182f.size()) {
                Log.w("FragmentActivity", "Activity result fragment index out of range: 0x" + Integer.toHexString(i));
                return;
            }
            Fragment fragment = (Fragment) this.f157b.f182f.get(i3);
            if (fragment == null) {
                Log.w("FragmentActivity", "Activity result no fragment exists for index: 0x" + Integer.toHexString(i));
                return;
            } else {
                fragment.m43a(65535 & i, i2, intent);
                return;
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (!this.f157b.m217c()) {
            a_();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f157b.m194a(configuration);
    }

    protected void onCreate(Bundle bundle) {
        this.f157b.m201a(this, this.f158c, null);
        if (getLayoutInflater().getFactory() == null) {
            getLayoutInflater().setFactory(this);
        }
        super.onCreate(bundle);
        C0036a c0036a = (C0036a) getLastNonConfigurationInstance();
        if (c0036a != null) {
            this.f167l = c0036a.f155e;
        }
        if (bundle != null) {
            this.f157b.m196a(bundle.getParcelable("android:support:fragments"), c0036a != null ? c0036a.f154d : null);
        }
        this.f157b.m229j();
    }

    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        return VERSION.SDK_INT >= 11 ? super.onCreatePanelMenu(i, menu) | this.f157b.m206a(menu, getMenuInflater()) : true;
    }

    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        if (!"fragment".equals(str)) {
            return super.onCreateView(str, context, attributeSet);
        }
        View onCreateView = this.f157b.onCreateView(str, context, attributeSet);
        return onCreateView == null ? super.onCreateView(str, context, attributeSet) : onCreateView;
    }

    protected void onDestroy() {
        super.onDestroy();
        m172a(false);
        this.f157b.m237r();
        if (this.f168m != null) {
            this.f168m.m308h();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (VERSION.SDK_INT >= 5 || i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        onBackPressed();
        return true;
    }

    public void onLowMemory() {
        super.onLowMemory();
        this.f157b.m238s();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        switch (i) {
            case C0627j.View_android_focusable /*0*/:
                return this.f157b.m207a(menuItem);
            case C0627j.Toolbar_contentInsetLeft /*6*/:
                return this.f157b.m214b(menuItem);
            default:
                return false;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f157b.m228i();
    }

    public void onPanelClosed(int i, Menu menu) {
        switch (i) {
            case C0627j.View_android_focusable /*0*/:
                this.f157b.m212b(menu);
                break;
        }
        super.onPanelClosed(i, menu);
    }

    protected void onPause() {
        super.onPause();
        this.f160e = false;
        if (this.f156a.hasMessages(2)) {
            this.f156a.removeMessages(2);
            e_();
        }
        this.f157b.m233n();
    }

    protected void onPostResume() {
        super.onPostResume();
        this.f156a.removeMessages(2);
        e_();
        this.f157b.m223e();
    }

    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        if (this.f164i) {
            this.f164i = false;
            menu.clear();
            onCreatePanelMenu(i, menu);
        }
        return m173a(view, menu) | this.f157b.m205a(menu);
    }

    protected void onResume() {
        super.onResume();
        this.f156a.sendEmptyMessage(2);
        this.f160e = true;
        this.f157b.m223e();
    }

    public final Object onRetainNonConfigurationInstance() {
        int i = 0;
        if (this.f161f) {
            m172a(true);
        }
        Object c_ = c_();
        ArrayList g = this.f157b.m226g();
        int i2;
        if (this.f167l != null) {
            int size = this.f167l.size();
            C0055n[] c0055nArr = new C0055n[size];
            for (int i3 = size - 1; i3 >= 0; i3--) {
                c0055nArr[i3] = (C0055n) this.f167l.m437c(i3);
            }
            i2 = 0;
            while (i < size) {
                C0055n c0055n = c0055nArr[i];
                if (c0055n.f248g) {
                    i2 = true;
                } else {
                    c0055n.m308h();
                    this.f167l.remove(c0055n.f245d);
                }
                i++;
            }
        } else {
            i2 = 0;
        }
        if (g == null && r0 == 0 && c_ == null) {
            return null;
        }
        C0036a c0036a = new C0036a();
        c0036a.f151a = null;
        c0036a.f152b = c_;
        c0036a.f153c = null;
        c0036a.f154d = g;
        c0036a.f155e = this.f167l;
        return c0036a;
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Parcelable h = this.f157b.m227h();
        if (h != null) {
            bundle.putParcelable("android:support:fragments", h);
        }
    }

    protected void onStart() {
        super.onStart();
        this.f161f = false;
        this.f162g = false;
        this.f156a.removeMessages(1);
        if (!this.f159d) {
            this.f159d = true;
            this.f157b.m230k();
        }
        this.f157b.m228i();
        this.f157b.m223e();
        if (!this.f166k) {
            this.f166k = true;
            if (this.f168m != null) {
                this.f168m.m302b();
            } else if (!this.f165j) {
                this.f168m = m169a("(root)", this.f166k, false);
                if (!(this.f168m == null || this.f168m.f247f)) {
                    this.f168m.m302b();
                }
            }
            this.f165j = true;
        }
        this.f157b.m231l();
        if (this.f167l != null) {
            int size = this.f167l.size();
            C0055n[] c0055nArr = new C0055n[size];
            for (int i = size - 1; i >= 0; i--) {
                c0055nArr[i] = (C0055n) this.f167l.m437c(i);
            }
            for (int i2 = 0; i2 < size; i2++) {
                C0055n c0055n = c0055nArr[i2];
                c0055n.m305e();
                c0055n.m307g();
            }
        }
    }

    protected void onStop() {
        super.onStop();
        this.f161f = true;
        this.f156a.sendEmptyMessage(1);
        this.f157b.m234o();
    }

    public void startActivityForResult(Intent intent, int i) {
        if (i == -1 || (-65536 & i) == 0) {
            super.startActivityForResult(intent, i);
            return;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }
}
