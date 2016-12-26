package android.support.v4.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.Transition.EpicenterCallback;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/* renamed from: android.support.v4.app.l */
class C0051l {

    /* renamed from: android.support.v4.app.l.b */
    public interface C0026b {
        View m118a();
    }

    /* renamed from: android.support.v4.app.l.1 */
    static class C00461 extends EpicenterCallback {
        final /* synthetic */ Rect f206a;

        C00461(Rect rect) {
            this.f206a = rect;
        }

        public Rect onGetEpicenter(Transition transition) {
            return this.f206a;
        }
    }

    /* renamed from: android.support.v4.app.l.2 */
    static class C00472 implements OnPreDrawListener {
        final /* synthetic */ View f207a;
        final /* synthetic */ C0026b f208b;
        final /* synthetic */ Map f209c;
        final /* synthetic */ Map f210d;
        final /* synthetic */ Transition f211e;
        final /* synthetic */ ArrayList f212f;

        C00472(View view, C0026b c0026b, Map map, Map map2, Transition transition, ArrayList arrayList) {
            this.f207a = view;
            this.f208b = c0026b;
            this.f209c = map;
            this.f210d = map2;
            this.f211e = transition;
            this.f212f = arrayList;
        }

        public boolean onPreDraw() {
            this.f207a.getViewTreeObserver().removeOnPreDrawListener(this);
            View a = this.f208b.m118a();
            if (a != null) {
                if (!this.f209c.isEmpty()) {
                    C0051l.m282a(this.f210d, a);
                    this.f210d.keySet().retainAll(this.f209c.values());
                    for (Entry entry : this.f209c.entrySet()) {
                        View view = (View) this.f210d.get((String) entry.getValue());
                        if (view != null) {
                            view.setTransitionName((String) entry.getKey());
                        }
                    }
                }
                if (this.f211e != null) {
                    C0051l.m285b(this.f212f, a);
                    this.f212f.removeAll(this.f210d.values());
                    C0051l.m284b(this.f211e, this.f212f);
                }
            }
            return true;
        }
    }

    /* renamed from: android.support.v4.app.l.3 */
    static class C00483 extends EpicenterCallback {
        final /* synthetic */ C0050a f213a;
        private Rect f214b;

        C00483(C0050a c0050a) {
            this.f213a = c0050a;
        }

        public Rect onGetEpicenter(Transition transition) {
            if (this.f214b == null && this.f213a.f226a != null) {
                this.f214b = C0051l.m286c(this.f213a.f226a);
            }
            return this.f214b;
        }
    }

    /* renamed from: android.support.v4.app.l.4 */
    static class C00494 implements OnPreDrawListener {
        final /* synthetic */ View f215a;
        final /* synthetic */ Transition f216b;
        final /* synthetic */ View f217c;
        final /* synthetic */ ArrayList f218d;
        final /* synthetic */ Transition f219e;
        final /* synthetic */ ArrayList f220f;
        final /* synthetic */ Transition f221g;
        final /* synthetic */ ArrayList f222h;
        final /* synthetic */ Map f223i;
        final /* synthetic */ ArrayList f224j;
        final /* synthetic */ Transition f225k;

        C00494(View view, Transition transition, View view2, ArrayList arrayList, Transition transition2, ArrayList arrayList2, Transition transition3, ArrayList arrayList3, Map map, ArrayList arrayList4, Transition transition4) {
            this.f215a = view;
            this.f216b = transition;
            this.f217c = view2;
            this.f218d = arrayList;
            this.f219e = transition2;
            this.f220f = arrayList2;
            this.f221g = transition3;
            this.f222h = arrayList3;
            this.f223i = map;
            this.f224j = arrayList4;
            this.f225k = transition4;
        }

        public boolean onPreDraw() {
            this.f215a.getViewTreeObserver().removeOnPreDrawListener(this);
            if (this.f216b != null) {
                this.f216b.removeTarget(this.f217c);
                C0051l.m280a(this.f216b, this.f218d);
            }
            if (this.f219e != null) {
                C0051l.m280a(this.f219e, this.f220f);
            }
            if (this.f221g != null) {
                C0051l.m280a(this.f221g, this.f222h);
            }
            for (Entry entry : this.f223i.entrySet()) {
                ((View) entry.getValue()).setTransitionName((String) entry.getKey());
            }
            int size = this.f224j.size();
            for (int i = 0; i < size; i++) {
                this.f225k.excludeTarget((View) this.f224j.get(i), false);
            }
            this.f225k.excludeTarget(this.f217c, false);
            return true;
        }
    }

    /* renamed from: android.support.v4.app.l.a */
    public static class C0050a {
        public View f226a;
    }

    public static Object m270a(Object obj) {
        return obj != null ? ((Transition) obj).clone() : obj;
    }

    public static Object m271a(Object obj, View view, ArrayList<View> arrayList, Map<String, View> map) {
        if (obj == null) {
            return obj;
        }
        C0051l.m285b((ArrayList) arrayList, view);
        if (map != null) {
            arrayList.removeAll(map.values());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        C0051l.m284b((Transition) obj, (ArrayList) arrayList);
        return obj;
    }

    public static Object m272a(Object obj, Object obj2, Object obj3, boolean z) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        if (transition == null || transition2 == null) {
            z = true;
        }
        TransitionSet transitionSet;
        if (z) {
            transitionSet = new TransitionSet();
            if (transition != null) {
                transitionSet.addTransition(transition);
            }
            if (transition2 != null) {
                transitionSet.addTransition(transition2);
            }
            if (transition3 == null) {
                return transitionSet;
            }
            transitionSet.addTransition(transition3);
            return transitionSet;
        }
        Transition transition4 = null;
        if (transition2 != null && transition != null) {
            transition4 = new TransitionSet().addTransition(transition2).addTransition(transition).setOrdering(1);
        } else if (transition2 != null) {
            transition4 = transition2;
        } else if (transition != null) {
            transition4 = transition;
        }
        if (transition3 == null) {
            return transition4;
        }
        transitionSet = new TransitionSet();
        if (transition4 != null) {
            transitionSet.addTransition(transition4);
        }
        transitionSet.addTransition(transition3);
        return transitionSet;
    }

    public static String m273a(View view) {
        return view.getTransitionName();
    }

    private static void m274a(Transition transition, C0050a c0050a) {
        if (transition != null) {
            transition.setEpicenterCallback(new C00483(c0050a));
        }
    }

    public static void m275a(View view, View view2, Object obj, ArrayList<View> arrayList, Object obj2, ArrayList<View> arrayList2, Object obj3, ArrayList<View> arrayList3, Object obj4, ArrayList<View> arrayList4, Map<String, View> map) {
        Transition transition = (Transition) obj;
        Transition transition2 = (Transition) obj2;
        Transition transition3 = (Transition) obj3;
        Transition transition4 = (Transition) obj4;
        if (transition4 != null) {
            view.getViewTreeObserver().addOnPreDrawListener(new C00494(view, transition, view2, arrayList, transition2, arrayList2, transition3, arrayList3, map, arrayList4, transition4));
        }
    }

    public static void m276a(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, (Transition) obj);
    }

    public static void m277a(Object obj, View view) {
        ((Transition) obj).setEpicenterCallback(new C00461(C0051l.m286c(view)));
    }

    public static void m278a(Object obj, View view, boolean z) {
        ((Transition) obj).excludeTarget(view, z);
    }

    public static void m279a(Object obj, Object obj2, View view, C0026b c0026b, View view2, C0050a c0050a, Map<String, String> map, ArrayList<View> arrayList, Map<String, View> map2, ArrayList<View> arrayList2) {
        if (obj != null || obj2 != null) {
            Transition transition = (Transition) obj;
            if (transition != null) {
                transition.addTarget(view2);
            }
            if (obj2 != null) {
                C0051l.m284b((Transition) obj2, (ArrayList) arrayList2);
            }
            if (c0026b != null) {
                view.getViewTreeObserver().addOnPreDrawListener(new C00472(view, c0026b, map, map2, transition, arrayList));
            }
            C0051l.m274a(transition, c0050a);
        }
    }

    public static void m280a(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            transition.removeTarget((View) arrayList.get(i));
        }
    }

    public static void m282a(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String transitionName = view.getTransitionName();
            if (transitionName != null) {
                map.put(transitionName, view);
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    C0051l.m282a((Map) map, viewGroup.getChildAt(i));
                }
            }
        }
    }

    public static void m284b(Object obj, ArrayList<View> arrayList) {
        Transition transition = (Transition) obj;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            transition.addTarget((View) arrayList.get(i));
        }
    }

    private static void m285b(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) {
            return;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.isTransitionGroup()) {
                arrayList.add(viewGroup);
                return;
            }
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                C0051l.m285b((ArrayList) arrayList, viewGroup.getChildAt(i));
            }
            return;
        }
        arrayList.add(view);
    }

    private static Rect m286c(View view) {
        Rect rect = new Rect();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        rect.set(iArr[0], iArr[1], iArr[0] + view.getWidth(), iArr[1] + view.getHeight());
        return rect;
    }
}
