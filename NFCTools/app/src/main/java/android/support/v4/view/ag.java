package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;

class ag {

    /* renamed from: android.support.v4.view.ag.1 */
    static class C01781 extends AnimatorListenerAdapter {
        final /* synthetic */ aj f413a;
        final /* synthetic */ View f414b;

        C01781(aj ajVar, View view) {
            this.f413a = ajVar;
            this.f414b = view;
        }

        public void onAnimationCancel(Animator animator) {
            this.f413a.onAnimationCancel(this.f414b);
        }

        public void onAnimationEnd(Animator animator) {
            this.f413a.onAnimationEnd(this.f414b);
        }

        public void onAnimationStart(Animator animator) {
            this.f413a.onAnimationStart(this.f414b);
        }
    }

    public static void m834a(View view) {
        view.animate().cancel();
    }

    public static void m835a(View view, float f) {
        view.animate().alpha(f);
    }

    public static void m836a(View view, long j) {
        view.animate().setDuration(j);
    }

    public static void m837a(View view, aj ajVar) {
        if (ajVar != null) {
            view.animate().setListener(new C01781(ajVar, view));
        } else {
            view.animate().setListener(null);
        }
    }

    public static void m838a(View view, Interpolator interpolator) {
        view.animate().setInterpolator(interpolator);
    }

    public static void m839b(View view) {
        view.animate().start();
    }

    public static void m840b(View view, float f) {
        view.animate().translationX(f);
    }

    public static void m841c(View view, float f) {
        view.animate().translationY(f);
    }

    public static void m842d(View view, float f) {
        view.animate().scaleY(f);
    }
}
