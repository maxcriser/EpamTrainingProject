package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.p000a.C0000a;
import android.support.v4.p007f.C0101e;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0294e;
import android.util.TypedValue;

public class TintManager {
    private static final C0393a COLOR_FILTER_CACHE;
    private static final int[] CONTAINERS_WITH_TINT_CHILDREN;
    private static final boolean DEBUG = false;
    static final Mode DEFAULT_MODE;
    private static final String TAG;
    private static final int[] TINT_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] TINT_COLOR_CONTROL_ACTIVATED;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private final Context mContext;
    private ColorStateList mDefaultColorStateList;
    private final Resources mResources;
    private ColorStateList mSwitchThumbStateList;
    private ColorStateList mSwitchTrackStateList;
    private final TypedValue mTypedValue;

    /* renamed from: android.support.v7.internal.widget.TintManager.a */
    private static class C0393a extends C0101e<Integer, PorterDuffColorFilter> {
        public C0393a(int i) {
            super(i);
        }

        private static int m1761b(int i, Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }

        PorterDuffColorFilter m1762a(int i, Mode mode) {
            return (PorterDuffColorFilter) m448a((Object) Integer.valueOf(C0393a.m1761b(i, mode)));
        }

        PorterDuffColorFilter m1763a(int i, Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return (PorterDuffColorFilter) m449a(Integer.valueOf(C0393a.m1761b(i, mode)), porterDuffColorFilter);
        }
    }

    static {
        TAG = TintManager.class.getSimpleName();
        DEFAULT_MODE = Mode.SRC_IN;
        COLOR_FILTER_CACHE = new C0393a(6);
        TINT_COLOR_CONTROL_NORMAL = new int[]{C0294e.abc_ic_ab_back_mtrl_am_alpha, C0294e.abc_ic_go_search_api_mtrl_alpha, C0294e.abc_ic_search_api_mtrl_alpha, C0294e.abc_ic_commit_search_api_mtrl_alpha, C0294e.abc_ic_clear_mtrl_alpha, C0294e.abc_ic_menu_share_mtrl_alpha, C0294e.abc_ic_menu_copy_mtrl_am_alpha, C0294e.abc_ic_menu_cut_mtrl_alpha, C0294e.abc_ic_menu_selectall_mtrl_alpha, C0294e.abc_ic_menu_paste_mtrl_am_alpha, C0294e.abc_ic_menu_moreoverflow_mtrl_alpha, C0294e.abc_ic_voice_search_api_mtrl_alpha, C0294e.abc_textfield_search_default_mtrl_alpha, C0294e.abc_textfield_default_mtrl_alpha};
        TINT_COLOR_CONTROL_ACTIVATED = new int[]{C0294e.abc_textfield_activated_mtrl_alpha, C0294e.abc_textfield_search_activated_mtrl_alpha, C0294e.abc_cab_background_top_mtrl_alpha};
        TINT_COLOR_BACKGROUND_MULTIPLY = new int[]{C0294e.abc_popup_background_mtrl_mult, C0294e.abc_cab_background_internal_bg, C0294e.abc_menu_hardkey_panel_mtrl_mult};
        TINT_COLOR_CONTROL_STATE_LIST = new int[]{C0294e.abc_edit_text_material, C0294e.abc_tab_indicator_material, C0294e.abc_textfield_search_material, C0294e.abc_spinner_mtrl_am_alpha, C0294e.abc_btn_check_material, C0294e.abc_btn_radio_material};
        CONTAINERS_WITH_TINT_CHILDREN = new int[]{C0294e.abc_cab_background_top_material};
    }

    public TintManager(Context context) {
        this.mContext = context;
        this.mResources = new C0400e(context.getResources(), this);
        this.mTypedValue = new TypedValue();
    }

    private static boolean arrayContains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private ColorStateList getDefaultColorStateList() {
        if (this.mDefaultColorStateList == null) {
            int themeAttrColor = getThemeAttrColor(C0290a.colorControlNormal);
            int themeAttrColor2 = getThemeAttrColor(C0290a.colorControlActivated);
            r2 = new int[7][];
            r3 = new int[7];
            r2[0] = new int[]{-16842910};
            r3[0] = getDisabledThemeAttrColor(C0290a.colorControlNormal);
            r2[1] = new int[]{16842908};
            r3[1] = themeAttrColor2;
            r2[2] = new int[]{16843518};
            r3[2] = themeAttrColor2;
            r2[3] = new int[]{16842919};
            r3[3] = themeAttrColor2;
            r2[4] = new int[]{16842912};
            r3[4] = themeAttrColor2;
            r2[5] = new int[]{16842913};
            r3[5] = themeAttrColor2;
            r2[6] = new int[0];
            r3[6] = themeAttrColor;
            this.mDefaultColorStateList = new ColorStateList(r2, r3);
        }
        return this.mDefaultColorStateList;
    }

    public static Drawable getDrawable(Context context, int i) {
        return isInTintList(i) ? new TintManager(context).getDrawable(i) : C0000a.m0a(context, i);
    }

    private ColorStateList getSwitchThumbColorStateList() {
        if (this.mSwitchThumbStateList == null) {
            r0 = new int[3][];
            r1 = new int[3];
            r0[0] = new int[]{-16842910};
            r1[0] = getDisabledThemeAttrColor(C0290a.colorSwitchThumbNormal);
            r0[1] = new int[]{16842912};
            r1[1] = getThemeAttrColor(C0290a.colorControlActivated);
            r0[2] = new int[0];
            r1[2] = getThemeAttrColor(C0290a.colorSwitchThumbNormal);
            this.mSwitchThumbStateList = new ColorStateList(r0, r1);
        }
        return this.mSwitchThumbStateList;
    }

    private ColorStateList getSwitchTrackColorStateList() {
        if (this.mSwitchTrackStateList == null) {
            r0 = new int[3][];
            r1 = new int[3];
            r0[0] = new int[]{-16842910};
            r1[0] = getThemeAttrColor(16842800, 0.1f);
            r0[1] = new int[]{16842912};
            r1[1] = getThemeAttrColor(C0290a.colorControlActivated, 0.3f);
            r0[2] = new int[0];
            r1[2] = getThemeAttrColor(16842800, 0.3f);
            this.mSwitchTrackStateList = new ColorStateList(r0, r1);
        }
        return this.mSwitchTrackStateList;
    }

    private static boolean isInTintList(int i) {
        return arrayContains(TINT_COLOR_BACKGROUND_MULTIPLY, i) || arrayContains(TINT_COLOR_CONTROL_NORMAL, i) || arrayContains(TINT_COLOR_CONTROL_ACTIVATED, i) || arrayContains(TINT_COLOR_CONTROL_STATE_LIST, i) || arrayContains(CONTAINERS_WITH_TINT_CHILDREN, i);
    }

    int getDisabledThemeAttrColor(int i) {
        this.mContext.getTheme().resolveAttribute(16842803, this.mTypedValue, true);
        return getThemeAttrColor(i, this.mTypedValue.getFloat());
    }

    public Drawable getDrawable(int i) {
        Drawable a = C0000a.m0a(this.mContext, i);
        if (a != null) {
            if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, i)) {
                return new C0399d(a, getDefaultColorStateList());
            }
            if (i == C0294e.abc_switch_track_mtrl_alpha) {
                return new C0399d(a, getSwitchTrackColorStateList());
            }
            if (i == C0294e.abc_switch_thumb_material) {
                return new C0399d(a, getSwitchThumbColorStateList(), Mode.MULTIPLY);
            }
            if (arrayContains(CONTAINERS_WITH_TINT_CHILDREN, i)) {
                return this.mResources.getDrawable(i);
            }
            tintDrawable(i, a);
        }
        return a;
    }

    int getThemeAttrColor(int i) {
        if (this.mContext.getTheme().resolveAttribute(i, this.mTypedValue, true)) {
            if (this.mTypedValue.type >= 16 && this.mTypedValue.type <= 31) {
                return this.mTypedValue.data;
            }
            if (this.mTypedValue.type == 3) {
                return this.mResources.getColor(this.mTypedValue.resourceId);
            }
        }
        return 0;
    }

    int getThemeAttrColor(int i, float f) {
        int themeAttrColor = getThemeAttrColor(i);
        return (themeAttrColor & 16777215) | (Math.round(((float) Color.alpha(themeAttrColor)) * f) << 24);
    }

    void tintDrawable(int i, Drawable drawable) {
        int i2;
        Mode mode;
        Object obj;
        int i3;
        int i4;
        if (arrayContains(TINT_COLOR_CONTROL_NORMAL, i)) {
            i2 = C0290a.colorControlNormal;
            mode = null;
            obj = 1;
            i3 = -1;
        } else if (arrayContains(TINT_COLOR_CONTROL_ACTIVATED, i)) {
            i2 = C0290a.colorControlActivated;
            mode = null;
            i4 = 1;
            i3 = -1;
        } else if (arrayContains(TINT_COLOR_BACKGROUND_MULTIPLY, i)) {
            i4 = 1;
            i3 = -1;
            mode = Mode.MULTIPLY;
            i2 = 16842801;
        } else if (i == C0294e.abc_list_divider_mtrl_alpha) {
            i2 = 16842800;
            mode = null;
            i4 = 1;
            i3 = Math.round(40.8f);
        } else {
            i3 = -1;
            i2 = 0;
            mode = null;
            obj = null;
        }
        if (obj != null) {
            ColorFilter colorFilter;
            if (mode == null) {
                mode = DEFAULT_MODE;
            }
            i4 = getThemeAttrColor(i2);
            ColorFilter a = COLOR_FILTER_CACHE.m1762a(i4, mode);
            if (a == null) {
                a = new PorterDuffColorFilter(i4, mode);
                COLOR_FILTER_CACHE.m1763a(i4, mode, a);
                colorFilter = a;
            } else {
                colorFilter = a;
            }
            drawable.setColorFilter(colorFilter);
            if (i3 != -1) {
                drawable.setAlpha(i3);
            }
        }
    }
}
