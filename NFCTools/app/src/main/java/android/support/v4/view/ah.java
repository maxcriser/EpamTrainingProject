package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

class ah {

    /* renamed from: android.support.v4.view.ah.1 */
    static class C01791 extends AnimatorListenerAdapter {
        final /* synthetic */ aj f415a;
        final /* synthetic */ View f416b;

        C01791(aj ajVar, View view) {
            this.f415a = ajVar;
            this.f416b = view;
        }

        public void onAnimationCancel(Animator animator) {
            this.f415a.onAnimationCancel(this.f416b);
        }

        public void onAnimationEnd(Animator animator) {
            this.f415a.onAnimationEnd(this.f416b);
        }

        public void onAnimationStart(Animator animator) {
            this.f415a.onAnimationStart(this.f416b);
        }
    }

    public static void m843a(View view, aj ajVar) {
        if (ajVar != null) {
            view.animate().setListener(new C01791(ajVar, view));
        } else {
            view.animate().setListener(null);
        }
    }
}
