package android.support.v7.internal.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.p003c.p004a.C0079b;
import android.support.v4.view.C0187d.C0186b;
import android.support.v7.internal.view.menu.MenuItemWrapperICS.C0349a;
import android.view.ActionProvider;
import android.view.ActionProvider.VisibilityListener;
import android.view.MenuItem;
import android.view.View;

@TargetApi(16)
/* renamed from: android.support.v7.internal.view.menu.c */
class C0357c extends MenuItemWrapperICS {

    /* renamed from: android.support.v7.internal.view.menu.c.a */
    class C0356a extends C0349a implements VisibilityListener {
        C0186b f768c;
        final /* synthetic */ C0357c f769d;

        public C0356a(C0357c c0357c, Context context, ActionProvider actionProvider) {
            this.f769d = c0357c;
            super(c0357c, context, actionProvider);
        }

        public boolean isVisible() {
            return this.a.isVisible();
        }

        public void onActionProviderVisibilityChanged(boolean z) {
            if (this.f768c != null) {
                this.f768c.m869a(z);
            }
        }

        public View onCreateActionView(MenuItem menuItem) {
            return this.a.onCreateActionView(menuItem);
        }

        public boolean overridesItemVisibility() {
            return this.a.overridesItemVisibility();
        }

        public void refreshVisibility() {
            this.a.refreshVisibility();
        }

        public void setVisibilityListener(C0186b c0186b) {
            VisibilityListener visibilityListener;
            this.f768c = c0186b;
            ActionProvider actionProvider = this.a;
            if (c0186b == null) {
                visibilityListener = null;
            }
            actionProvider.setVisibilityListener(visibilityListener);
        }
    }

    C0357c(Context context, C0079b c0079b) {
        super(context, c0079b);
    }

    C0349a createActionProviderWrapper(ActionProvider actionProvider) {
        return new C0356a(this, this.mContext, actionProvider);
    }
}
