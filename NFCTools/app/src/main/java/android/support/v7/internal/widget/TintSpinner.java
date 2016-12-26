package android.support.v7.internal.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ListPopupWindow;
import android.widget.Spinner;
import java.lang.reflect.Field;

public class TintSpinner extends Spinner {
    private static final int[] TINT_ATTRS;

    static {
        TINT_ATTRS = new int[]{16842964, 16843126};
    }

    public TintSpinner(Context context) {
        this(context, null);
    }

    public TintSpinner(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842881);
    }

    public TintSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, TINT_ATTRS, i, 0);
        setBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
        if (obtainStyledAttributes.hasValue(1)) {
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            if (VERSION.SDK_INT >= 16) {
                setPopupBackgroundDrawable(drawable);
            } else if (VERSION.SDK_INT >= 11) {
                setPopupBackgroundDrawableV11(this, drawable);
            }
        }
        obtainStyledAttributes.recycle();
    }

    @TargetApi(11)
    private static void setPopupBackgroundDrawableV11(Spinner spinner, Drawable drawable) {
        try {
            Field declaredField = Spinner.class.getDeclaredField("mPopup");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(spinner);
            if (obj instanceof ListPopupWindow) {
                ((ListPopupWindow) obj).setBackgroundDrawable(drawable);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        }
    }
}
