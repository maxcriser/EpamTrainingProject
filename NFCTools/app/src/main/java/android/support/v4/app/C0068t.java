package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.p000a.C0000a;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: android.support.v4.app.t */
public class C0068t implements Iterable<Intent> {
    private static final C0065b f250a;
    private final ArrayList<Intent> f251b;
    private final Context f252c;

    /* renamed from: android.support.v4.app.t.a */
    public interface C0064a {
        Intent m336a();
    }

    /* renamed from: android.support.v4.app.t.b */
    interface C0065b {
    }

    /* renamed from: android.support.v4.app.t.c */
    static class C0066c implements C0065b {
        C0066c() {
        }
    }

    /* renamed from: android.support.v4.app.t.d */
    static class C0067d implements C0065b {
        C0067d() {
        }
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            f250a = new C0067d();
        } else {
            f250a = new C0066c();
        }
    }

    private C0068t(Context context) {
        this.f251b = new ArrayList();
        this.f252c = context;
    }

    public static C0068t m337a(Context context) {
        return new C0068t(context);
    }

    public C0068t m338a(Activity activity) {
        Intent intent = null;
        if (activity instanceof C0064a) {
            intent = ((C0064a) activity).m336a();
        }
        Intent a = intent == null ? C0059o.m322a(activity) : intent;
        if (a != null) {
            ComponentName component = a.getComponent();
            if (component == null) {
                component = a.resolveActivity(this.f252c.getPackageManager());
            }
            m339a(component);
            m340a(a);
        }
        return this;
    }

    public C0068t m339a(ComponentName componentName) {
        int size = this.f251b.size();
        try {
            Intent a = C0059o.m323a(this.f252c, componentName);
            while (a != null) {
                this.f251b.add(size, a);
                a = C0059o.m323a(this.f252c, a.getComponent());
            }
            return this;
        } catch (Throwable e) {
            Log.e("TaskStackBuilder", "Bad ComponentName while traversing activity parent metadata");
            throw new IllegalArgumentException(e);
        }
    }

    public C0068t m340a(Intent intent) {
        this.f251b.add(intent);
        return this;
    }

    public void m341a() {
        m342a(null);
    }

    public void m342a(Bundle bundle) {
        if (this.f251b.isEmpty()) {
            throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
        }
        Intent[] intentArr = (Intent[]) this.f251b.toArray(new Intent[this.f251b.size()]);
        intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
        if (!C0000a.m1a(this.f252c, intentArr, bundle)) {
            Intent intent = new Intent(intentArr[intentArr.length - 1]);
            intent.addFlags(268435456);
            this.f252c.startActivity(intent);
        }
    }

    public Iterator<Intent> iterator() {
        return this.f251b.iterator();
    }
}
