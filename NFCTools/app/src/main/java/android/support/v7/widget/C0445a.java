package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.p000a.C0000a;
import android.support.v4.widget.C0281j;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0295f;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.WeakHashMap;

/* renamed from: android.support.v7.widget.a */
class C0445a extends C0281j implements OnClickListener {
    private final SearchManager f916j;
    private final SearchView f917k;
    private final SearchableInfo f918l;
    private final Context f919m;
    private final WeakHashMap<String, ConstantState> f920n;
    private final int f921o;
    private boolean f922p;
    private int f923q;
    private ColorStateList f924r;
    private int f925s;
    private int f926t;
    private int f927u;
    private int f928v;
    private int f929w;
    private int f930x;

    /* renamed from: android.support.v7.widget.a.a */
    private static final class C0444a {
        public final TextView f911a;
        public final TextView f912b;
        public final ImageView f913c;
        public final ImageView f914d;
        public final ImageView f915e;

        public C0444a(View view) {
            this.f911a = (TextView) view.findViewById(16908308);
            this.f912b = (TextView) view.findViewById(16908309);
            this.f913c = (ImageView) view.findViewById(16908295);
            this.f914d = (ImageView) view.findViewById(16908296);
            this.f915e = (ImageView) view.findViewById(C0295f.edit_query);
        }
    }

    public C0445a(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.f922p = false;
        this.f923q = 1;
        this.f925s = -1;
        this.f926t = -1;
        this.f927u = -1;
        this.f928v = -1;
        this.f929w = -1;
        this.f930x = -1;
        this.f916j = (SearchManager) this.d.getSystemService("search");
        this.f917k = searchView;
        this.f918l = searchableInfo;
        this.f921o = searchView.getSuggestionCommitIconResId();
        this.f919m = context;
        this.f920n = weakHashMap;
    }

    private Drawable m1778a(ComponentName componentName) {
        Object obj = null;
        String flattenToShortString = componentName.flattenToShortString();
        if (this.f920n.containsKey(flattenToShortString)) {
            ConstantState constantState = (ConstantState) this.f920n.get(flattenToShortString);
            return constantState == null ? null : constantState.newDrawable(this.f919m.getResources());
        } else {
            Drawable b = m1785b(componentName);
            if (b != null) {
                obj = b.getConstantState();
            }
            this.f920n.put(flattenToShortString, obj);
            return b;
        }
    }

    private Drawable m1779a(String str) {
        if (str == null || str.length() == 0 || "0".equals(str)) {
            return null;
        }
        Drawable b;
        try {
            int parseInt = Integer.parseInt(str);
            String str2 = "android.resource://" + this.f919m.getPackageName() + "/" + parseInt;
            b = m1787b(str2);
            if (b != null) {
                return b;
            }
            b = C0000a.m0a(this.f919m, parseInt);
            m1784a(str2, b);
            return b;
        } catch (NumberFormatException e) {
            b = m1787b(str);
            if (b != null) {
                return b;
            }
            b = m1786b(Uri.parse(str));
            m1784a(str, b);
            return b;
        } catch (NotFoundException e2) {
            Log.w("SuggestionsAdapter", "Icon resource not found: " + str);
            return null;
        }
    }

    private static String m1780a(Cursor cursor, int i) {
        String str = null;
        if (i != -1) {
            try {
                str = cursor.getString(i);
            } catch (Throwable e) {
                Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e);
            }
        }
        return str;
    }

    public static String m1781a(Cursor cursor, String str) {
        return C0445a.m1780a(cursor, cursor.getColumnIndex(str));
    }

    private void m1782a(ImageView imageView, Drawable drawable, int i) {
        imageView.setImageDrawable(drawable);
        if (drawable == null) {
            imageView.setVisibility(i);
            return;
        }
        imageView.setVisibility(0);
        drawable.setVisible(false, false);
        drawable.setVisible(true, false);
    }

    private void m1783a(TextView textView, CharSequence charSequence) {
        textView.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
        }
    }

    private void m1784a(String str, Drawable drawable) {
        if (drawable != null) {
            this.f920n.put(str, drawable.getConstantState());
        }
    }

    private Drawable m1785b(ComponentName componentName) {
        PackageManager packageManager = this.d.getPackageManager();
        try {
            ActivityInfo activityInfo = packageManager.getActivityInfo(componentName, 128);
            int iconResource = activityInfo.getIconResource();
            if (iconResource == 0) {
                return null;
            }
            Drawable drawable = packageManager.getDrawable(componentName.getPackageName(), iconResource, activityInfo.applicationInfo);
            if (drawable != null) {
                return drawable;
            }
            Log.w("SuggestionsAdapter", "Invalid icon resource " + iconResource + " for " + componentName.flattenToShortString());
            return null;
        } catch (NameNotFoundException e) {
            Log.w("SuggestionsAdapter", e.toString());
            return null;
        }
    }

    private Drawable m1786b(Uri uri) {
        InputStream openInputStream;
        try {
            if ("android.resource".equals(uri.getScheme())) {
                return m1795a(uri);
            }
            openInputStream = this.f919m.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                throw new FileNotFoundException("Failed to open " + uri);
            }
            Drawable createFromStream = Drawable.createFromStream(openInputStream, null);
            try {
                openInputStream.close();
                return createFromStream;
            } catch (Throwable e) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e);
                return createFromStream;
            }
        } catch (NotFoundException e2) {
            throw new FileNotFoundException("Resource does not exist: " + uri);
        } catch (FileNotFoundException e3) {
            Log.w("SuggestionsAdapter", "Icon not found: " + uri + ", " + e3.getMessage());
            return null;
        } catch (Throwable th) {
            try {
                openInputStream.close();
            } catch (Throwable e4) {
                Log.e("SuggestionsAdapter", "Error closing icon stream for " + uri, e4);
            }
        }
    }

    private Drawable m1787b(String str) {
        ConstantState constantState = (ConstantState) this.f920n.get(str);
        return constantState == null ? null : constantState.newDrawable();
    }

    private CharSequence m1788b(CharSequence charSequence) {
        if (this.f924r == null) {
            TypedValue typedValue = new TypedValue();
            this.d.getTheme().resolveAttribute(C0290a.textColorSearchUrl, typedValue, true);
            this.f924r = this.d.getResources().getColorStateList(typedValue.resourceId);
        }
        CharSequence spannableString = new SpannableString(charSequence);
        spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.f924r, null), 0, charSequence.length(), 33);
        return spannableString;
    }

    private void m1789d(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras != null && !extras.getBoolean("in_progress")) {
        }
    }

    private Drawable m1790e(Cursor cursor) {
        if (this.f928v == -1) {
            return null;
        }
        Drawable a = m1779a(cursor.getString(this.f928v));
        return a == null ? m1792g(cursor) : a;
    }

    private Drawable m1791f(Cursor cursor) {
        return this.f929w == -1 ? null : m1779a(cursor.getString(this.f929w));
    }

    private Drawable m1792g(Cursor cursor) {
        Drawable a = m1778a(this.f918l.getSearchActivity());
        return a != null ? a : this.d.getPackageManager().getDefaultActivityIcon();
    }

    Cursor m1793a(SearchableInfo searchableInfo, String str, int i) {
        if (searchableInfo == null) {
            return null;
        }
        String suggestAuthority = searchableInfo.getSuggestAuthority();
        if (suggestAuthority == null) {
            return null;
        }
        String[] strArr;
        Builder fragment = new Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            fragment.appendEncodedPath(suggestPath);
        }
        fragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            fragment.appendPath(str);
            strArr = null;
        }
        if (i > 0) {
            fragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.d.getContentResolver().query(fragment.build(), null, suggestSelection, strArr, null);
    }

    public Cursor m1794a(CharSequence charSequence) {
        String charSequence2 = charSequence == null ? "" : charSequence.toString();
        if (this.f917k.getVisibility() != 0 || this.f917k.getWindowVisibility() != 0) {
            return null;
        }
        try {
            Cursor a = m1793a(this.f918l, charSequence2, 50);
            if (a != null) {
                a.getCount();
                return a;
            }
        } catch (Throwable e) {
            Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", e);
        }
        return null;
    }

    Drawable m1795a(Uri uri) {
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.d.getPackageManager().getResourcesForApplication(authority);
            List pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    size = Integer.parseInt((String) pathSegments.get(0));
                } catch (NumberFormatException e) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else if (size == 2) {
                size = resourcesForApplication.getIdentifier((String) pathSegments.get(1), (String) pathSegments.get(0), authority);
            } else {
                throw new FileNotFoundException("More than two path segments: " + uri);
            }
            if (size != 0) {
                return resourcesForApplication.getDrawable(size);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (NameNotFoundException e2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    public View m1796a(Context context, Cursor cursor, ViewGroup viewGroup) {
        View a = super.m1320a(context, cursor, viewGroup);
        a.setTag(new C0444a(a));
        ((ImageView) a.findViewById(C0295f.edit_query)).setImageResource(this.f921o);
        return a;
    }

    public void m1797a(int i) {
        this.f923q = i;
    }

    public void m1798a(Cursor cursor) {
        if (this.f922p) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.m1269a(cursor);
            if (cursor != null) {
                this.f925s = cursor.getColumnIndex("suggest_text_1");
                this.f926t = cursor.getColumnIndex("suggest_text_2");
                this.f927u = cursor.getColumnIndex("suggest_text_2_url");
                this.f928v = cursor.getColumnIndex("suggest_icon_1");
                this.f929w = cursor.getColumnIndex("suggest_icon_2");
                this.f930x = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Throwable e) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e);
        }
    }

    public void m1799a(View view, Context context, Cursor cursor) {
        C0444a c0444a = (C0444a) view.getTag();
        int i = this.f930x != -1 ? cursor.getInt(this.f930x) : 0;
        if (c0444a.f911a != null) {
            m1783a(c0444a.f911a, C0445a.m1780a(cursor, this.f925s));
        }
        if (c0444a.f912b != null) {
            CharSequence a = C0445a.m1780a(cursor, this.f927u);
            a = a != null ? m1788b(a) : C0445a.m1780a(cursor, this.f926t);
            if (TextUtils.isEmpty(a)) {
                if (c0444a.f911a != null) {
                    c0444a.f911a.setSingleLine(false);
                    c0444a.f911a.setMaxLines(2);
                }
            } else if (c0444a.f911a != null) {
                c0444a.f911a.setSingleLine(true);
                c0444a.f911a.setMaxLines(1);
            }
            m1783a(c0444a.f912b, a);
        }
        if (c0444a.f913c != null) {
            m1782a(c0444a.f913c, m1790e(cursor), 4);
        }
        if (c0444a.f914d != null) {
            m1782a(c0444a.f914d, m1791f(cursor), 8);
        }
        if (this.f923q == 2 || (this.f923q == 1 && (i & 1) != 0)) {
            c0444a.f915e.setVisibility(0);
            c0444a.f915e.setTag(c0444a.f911a.getText());
            c0444a.f915e.setOnClickListener(this);
            return;
        }
        c0444a.f915e.setVisibility(8);
    }

    public CharSequence m1800c(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        String a = C0445a.m1781a(cursor, "suggest_intent_query");
        if (a != null) {
            return a;
        }
        if (this.f918l.shouldRewriteQueryFromData()) {
            a = C0445a.m1781a(cursor, "suggest_intent_data");
            if (a != null) {
                return a;
            }
        }
        if (!this.f918l.shouldRewriteQueryFromText()) {
            return null;
        }
        a = C0445a.m1781a(cursor, "suggest_text_1");
        return a != null ? a : null;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (Throwable e) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e);
            View a = m1796a(this.d, this.c, viewGroup);
            if (a != null) {
                ((C0444a) a.getTag()).f911a.setText(e.toString());
            }
            return a;
        }
    }

    public boolean hasStableIds() {
        return false;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        m1789d(m1265a());
    }

    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        m1789d(m1265a());
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.f917k.onQueryRefine((CharSequence) tag);
        }
    }
}
