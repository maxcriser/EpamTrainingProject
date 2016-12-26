package android.support.v7.internal.widget;

import android.graphics.Outline;

/* renamed from: android.support.v7.internal.widget.b */
class C0398b extends C0397a {
    public C0398b(ActionBarContainer actionBarContainer) {
        super(actionBarContainer);
    }

    public void getOutline(Outline outline) {
        if (this.a.mIsSplit) {
            if (this.a.mSplitBackground != null) {
                this.a.mSplitBackground.getOutline(outline);
            }
        } else if (this.a.mBackground != null) {
            this.a.mBackground.getOutline(outline);
        }
    }
}
