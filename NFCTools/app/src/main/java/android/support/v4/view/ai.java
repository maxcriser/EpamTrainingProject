package android.support.v4.view;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.view.View;

class ai {

    /* renamed from: android.support.v4.view.ai.1 */
    static class C01801 implements AnimatorUpdateListener {
        final /* synthetic */ al f417a;
        final /* synthetic */ View f418b;

        C01801(al alVar, View view) {
            this.f417a = alVar;
            this.f418b = view;
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            this.f417a.m845a(this.f418b);
        }
    }

    public static void m844a(View view, al alVar) {
        view.animate().setUpdateListener(new C01801(alVar, view));
    }
}
