package android.support.v7.internal.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

/* renamed from: android.support.v7.internal.widget.a */
class C0397a extends Drawable {
    final ActionBarContainer f847a;

    public C0397a(ActionBarContainer actionBarContainer) {
        this.f847a = actionBarContainer;
    }

    public void draw(Canvas canvas) {
        if (!this.f847a.mIsSplit) {
            if (this.f847a.mBackground != null) {
                this.f847a.mBackground.draw(canvas);
            }
            if (this.f847a.mStackedBackground != null && this.f847a.mIsStacked) {
                this.f847a.mStackedBackground.draw(canvas);
            }
        } else if (this.f847a.mSplitBackground != null) {
            this.f847a.mSplitBackground.draw(canvas);
        }
    }

    public int getOpacity() {
        return 0;
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
