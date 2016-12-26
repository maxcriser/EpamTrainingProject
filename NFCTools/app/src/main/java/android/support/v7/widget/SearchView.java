package android.support.v7.widget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.app.SearchableInfo;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.ResultReceiver;
import android.support.v4.view.C0197g;
import android.support.v4.widget.C0267b;
import android.support.v7.internal.widget.RtlSpacingHelper;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.internal.widget.ViewUtils;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0293d;
import android.support.v7.p009a.C0301a.C0295f;
import android.support.v7.p009a.C0301a.C0300k;
import android.support.v7.p010b.C0321b;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.KeyEvent.DispatcherState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.wakdev.nfctools.C0628m.C0627j;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

public class SearchView extends LinearLayoutCompat implements C0321b {
    private static final boolean DBG = false;
    static final C0434a HIDDEN_METHOD_INVOKER;
    private static final String IME_OPTION_NO_MICROPHONE = "nm";
    private static final boolean IS_AT_LEAST_FROYO;
    private static final String LOG_TAG = "SearchView";
    private Bundle mAppSearchData;
    private boolean mClearingFocus;
    private final ImageView mCloseButton;
    private int mCollapsedImeOptions;
    private final View mDropDownAnchor;
    private boolean mExpandedInActionView;
    private boolean mIconified;
    private boolean mIconifiedByDefault;
    private int mMaxWidth;
    private CharSequence mOldQueryText;
    private final OnClickListener mOnClickListener;
    private OnCloseListener mOnCloseListener;
    private final OnEditorActionListener mOnEditorActionListener;
    private final OnItemClickListener mOnItemClickListener;
    private final OnItemSelectedListener mOnItemSelectedListener;
    private OnQueryTextListener mOnQueryChangeListener;
    private OnFocusChangeListener mOnQueryTextFocusChangeListener;
    private OnClickListener mOnSearchClickListener;
    private OnSuggestionListener mOnSuggestionListener;
    private final WeakHashMap<String, ConstantState> mOutsideDrawablesCache;
    private CharSequence mQueryHint;
    private boolean mQueryRefinement;
    private final SearchAutoComplete mQueryTextView;
    private Runnable mReleaseCursorRunnable;
    private final ImageView mSearchButton;
    private final View mSearchEditFrame;
    private final ImageView mSearchHintIcon;
    private final int mSearchIconResId;
    private final View mSearchPlate;
    private SearchableInfo mSearchable;
    private Runnable mShowImeRunnable;
    private final View mSubmitArea;
    private final ImageView mSubmitButton;
    private boolean mSubmitButtonEnabled;
    private final int mSuggestionCommitIconResId;
    private final int mSuggestionRowLayout;
    private C0267b mSuggestionsAdapter;
    OnKeyListener mTextKeyListener;
    private TextWatcher mTextWatcher;
    private final TintManager mTintManager;
    private final Runnable mUpdateDrawableStateRunnable;
    private CharSequence mUserQuery;
    private final Intent mVoiceAppSearchIntent;
    private final ImageView mVoiceButton;
    private boolean mVoiceButtonEnabled;
    private final Intent mVoiceWebSearchIntent;

    /* renamed from: android.support.v7.widget.SearchView.1 */
    class C04251 implements Runnable {
        final /* synthetic */ SearchView f885a;

        C04251(SearchView searchView) {
            this.f885a = searchView;
        }

        public void run() {
            InputMethodManager inputMethodManager = (InputMethodManager) this.f885a.getContext().getSystemService("input_method");
            if (inputMethodManager != null) {
                SearchView.HIDDEN_METHOD_INVOKER.m1772a(inputMethodManager, this.f885a, 0);
            }
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.2 */
    class C04262 implements OnItemClickListener {
        final /* synthetic */ SearchView f886a;

        C04262(SearchView searchView) {
            this.f886a = searchView;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            this.f886a.onItemClicked(i, 0, null);
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.3 */
    class C04273 implements OnItemSelectedListener {
        final /* synthetic */ SearchView f887a;

        C04273(SearchView searchView) {
            this.f887a = searchView;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            this.f887a.onItemSelected(i);
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.4 */
    class C04284 implements TextWatcher {
        final /* synthetic */ SearchView f888a;

        C04284(SearchView searchView) {
            this.f888a = searchView;
        }

        public void afterTextChanged(Editable editable) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.f888a.onTextChanged(charSequence);
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.5 */
    class C04295 implements Runnable {
        final /* synthetic */ SearchView f889a;

        C04295(SearchView searchView) {
            this.f889a = searchView;
        }

        public void run() {
            this.f889a.updateFocusedState();
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.6 */
    class C04306 implements Runnable {
        final /* synthetic */ SearchView f890a;

        C04306(SearchView searchView) {
            this.f890a = searchView;
        }

        public void run() {
            if (this.f890a.mSuggestionsAdapter != null && (this.f890a.mSuggestionsAdapter instanceof C0445a)) {
                this.f890a.mSuggestionsAdapter.m1269a(null);
            }
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.7 */
    class C04317 implements OnFocusChangeListener {
        final /* synthetic */ SearchView f891a;

        C04317(SearchView searchView) {
            this.f891a = searchView;
        }

        public void onFocusChange(View view, boolean z) {
            if (this.f891a.mOnQueryTextFocusChangeListener != null) {
                this.f891a.mOnQueryTextFocusChangeListener.onFocusChange(this.f891a, z);
            }
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.8 */
    class C04328 implements OnLayoutChangeListener {
        final /* synthetic */ SearchView f892a;

        C04328(SearchView searchView) {
            this.f892a = searchView;
        }

        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.f892a.adjustDropDownSizeAndPosition();
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.9 */
    class C04339 implements OnGlobalLayoutListener {
        final /* synthetic */ SearchView f893a;

        C04339(SearchView searchView) {
            this.f893a = searchView;
        }

        public void onGlobalLayout() {
            this.f893a.adjustDropDownSizeAndPosition();
        }
    }

    public interface OnCloseListener {
        boolean onClose();
    }

    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    public interface OnSuggestionListener {
        boolean onSuggestionClick(int i);

        boolean onSuggestionSelect(int i);
    }

    public static class SearchAutoComplete extends AutoCompleteTextView {
        private final int[] POPUP_WINDOW_ATTRS;
        private SearchView mSearchView;
        private int mThreshold;
        private final TintManager mTintManager;

        public SearchAutoComplete(Context context) {
            this(context, null);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet) {
            this(context, attributeSet, 16842859);
        }

        public SearchAutoComplete(Context context, AttributeSet attributeSet, int i) {
            super(context, attributeSet, i);
            this.POPUP_WINDOW_ATTRS = new int[]{16843126};
            this.mThreshold = getThreshold();
            TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, this.POPUP_WINDOW_ATTRS, i, 0);
            if (obtainStyledAttributes.hasValue(0)) {
                setDropDownBackgroundDrawable(obtainStyledAttributes.getDrawable(0));
            }
            obtainStyledAttributes.recycle();
            this.mTintManager = obtainStyledAttributes.getTintManager();
        }

        private boolean isEmpty() {
            return TextUtils.getTrimmedLength(getText()) == 0 ? true : SearchView.IS_AT_LEAST_FROYO;
        }

        public boolean enoughToFilter() {
            return (this.mThreshold <= 0 || super.enoughToFilter()) ? true : SearchView.IS_AT_LEAST_FROYO;
        }

        protected void onFocusChanged(boolean z, int i, Rect rect) {
            super.onFocusChanged(z, i, rect);
            this.mSearchView.onTextFocusChanged();
        }

        public boolean onKeyPreIme(int i, KeyEvent keyEvent) {
            if (i == 4) {
                DispatcherState keyDispatcherState;
                if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                    keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState == null) {
                        return true;
                    }
                    keyDispatcherState.startTracking(keyEvent, this);
                    return true;
                } else if (keyEvent.getAction() == 1) {
                    keyDispatcherState = getKeyDispatcherState();
                    if (keyDispatcherState != null) {
                        keyDispatcherState.handleUpEvent(keyEvent);
                    }
                    if (keyEvent.isTracking() && !keyEvent.isCanceled()) {
                        this.mSearchView.clearFocus();
                        this.mSearchView.setImeVisibility(SearchView.IS_AT_LEAST_FROYO);
                        return true;
                    }
                }
            }
            return super.onKeyPreIme(i, keyEvent);
        }

        public void onWindowFocusChanged(boolean z) {
            super.onWindowFocusChanged(z);
            if (z && this.mSearchView.hasFocus() && getVisibility() == 0) {
                ((InputMethodManager) getContext().getSystemService("input_method")).showSoftInput(this, 0);
                if (SearchView.isLandscapeMode(getContext())) {
                    SearchView.HIDDEN_METHOD_INVOKER.m1774a(this, true);
                }
            }
        }

        public void performCompletion() {
        }

        protected void replaceText(CharSequence charSequence) {
        }

        public void setDropDownBackgroundResource(int i) {
            setDropDownBackgroundDrawable(this.mTintManager.getDrawable(i));
        }

        void setSearchView(SearchView searchView) {
            this.mSearchView = searchView;
        }

        public void setThreshold(int i) {
            super.setThreshold(i);
            this.mThreshold = i;
        }
    }

    /* renamed from: android.support.v7.widget.SearchView.a */
    private static class C0434a {
        private Method f894a;
        private Method f895b;
        private Method f896c;
        private Method f897d;

        C0434a() {
            try {
                this.f894a = AutoCompleteTextView.class.getDeclaredMethod("doBeforeTextChanged", new Class[0]);
                this.f894a.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            try {
                this.f895b = AutoCompleteTextView.class.getDeclaredMethod("doAfterTextChanged", new Class[0]);
                this.f895b.setAccessible(true);
            } catch (NoSuchMethodException e2) {
            }
            try {
                this.f896c = AutoCompleteTextView.class.getMethod("ensureImeVisible", new Class[]{Boolean.TYPE});
                this.f896c.setAccessible(true);
            } catch (NoSuchMethodException e3) {
            }
            try {
                this.f897d = InputMethodManager.class.getMethod("showSoftInputUnchecked", new Class[]{Integer.TYPE, ResultReceiver.class});
                this.f897d.setAccessible(true);
            } catch (NoSuchMethodException e4) {
            }
        }

        void m1772a(InputMethodManager inputMethodManager, View view, int i) {
            if (this.f897d != null) {
                try {
                    this.f897d.invoke(inputMethodManager, new Object[]{Integer.valueOf(i), null});
                    return;
                } catch (Exception e) {
                }
            }
            inputMethodManager.showSoftInput(view, i);
        }

        void m1773a(AutoCompleteTextView autoCompleteTextView) {
            if (this.f894a != null) {
                try {
                    this.f894a.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }

        void m1774a(AutoCompleteTextView autoCompleteTextView, boolean z) {
            if (this.f896c != null) {
                try {
                    this.f896c.invoke(autoCompleteTextView, new Object[]{Boolean.valueOf(z)});
                } catch (Exception e) {
                }
            }
        }

        void m1775b(AutoCompleteTextView autoCompleteTextView) {
            if (this.f895b != null) {
                try {
                    this.f895b.invoke(autoCompleteTextView, new Object[0]);
                } catch (Exception e) {
                }
            }
        }
    }

    static {
        IS_AT_LEAST_FROYO = VERSION.SDK_INT >= 8 ? true : IS_AT_LEAST_FROYO;
        HIDDEN_METHOD_INVOKER = new C0434a();
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C0290a.searchViewStyle);
    }

    public SearchView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowImeRunnable = new C04251(this);
        this.mUpdateDrawableStateRunnable = new C04295(this);
        this.mReleaseCursorRunnable = new C04306(this);
        this.mOutsideDrawablesCache = new WeakHashMap();
        this.mOnClickListener = new OnClickListener() {
            final /* synthetic */ SearchView f882a;

            {
                this.f882a = r1;
            }

            public void onClick(View view) {
                if (view == this.f882a.mSearchButton) {
                    this.f882a.onSearchClicked();
                } else if (view == this.f882a.mCloseButton) {
                    this.f882a.onCloseClicked();
                } else if (view == this.f882a.mSubmitButton) {
                    this.f882a.onSubmitQuery();
                } else if (view == this.f882a.mVoiceButton) {
                    if (SearchView.IS_AT_LEAST_FROYO) {
                        this.f882a.onVoiceClicked();
                    }
                } else if (view == this.f882a.mQueryTextView) {
                    this.f882a.forceSuggestionQuery();
                }
            }
        };
        this.mTextKeyListener = new OnKeyListener() {
            final /* synthetic */ SearchView f883a;

            {
                this.f883a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (this.f883a.mSearchable == null) {
                    return SearchView.IS_AT_LEAST_FROYO;
                }
                if (this.f883a.mQueryTextView.isPopupShowing() && this.f883a.mQueryTextView.getListSelection() != -1) {
                    return this.f883a.onSuggestionsKey(view, i, keyEvent);
                }
                if (this.f883a.mQueryTextView.isEmpty() || !C0197g.m887a(keyEvent) || keyEvent.getAction() != 1 || i != 66) {
                    return SearchView.IS_AT_LEAST_FROYO;
                }
                view.cancelLongPress();
                this.f883a.launchQuerySearch(0, null, this.f883a.mQueryTextView.getText().toString());
                return true;
            }
        };
        this.mOnEditorActionListener = new OnEditorActionListener() {
            final /* synthetic */ SearchView f884a;

            {
                this.f884a = r1;
            }

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                this.f884a.onSubmitQuery();
                return true;
            }
        };
        this.mOnItemClickListener = new C04262(this);
        this.mOnItemSelectedListener = new C04273(this);
        this.mTextWatcher = new C04284(this);
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, C0300k.SearchView, i, 0);
        this.mTintManager = obtainStyledAttributes.getTintManager();
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(obtainStyledAttributes.getResourceId(C0300k.SearchView_layout, 0), this, true);
        this.mQueryTextView = (SearchAutoComplete) findViewById(C0295f.search_src_text);
        this.mQueryTextView.setSearchView(this);
        this.mSearchEditFrame = findViewById(C0295f.search_edit_frame);
        this.mSearchPlate = findViewById(C0295f.search_plate);
        this.mSubmitArea = findViewById(C0295f.submit_area);
        this.mSearchButton = (ImageView) findViewById(C0295f.search_button);
        this.mSubmitButton = (ImageView) findViewById(C0295f.search_go_btn);
        this.mCloseButton = (ImageView) findViewById(C0295f.search_close_btn);
        this.mVoiceButton = (ImageView) findViewById(C0295f.search_voice_btn);
        this.mSearchHintIcon = (ImageView) findViewById(C0295f.search_mag_icon);
        this.mSearchPlate.setBackgroundDrawable(obtainStyledAttributes.getDrawable(C0300k.SearchView_queryBackground));
        this.mSubmitArea.setBackgroundDrawable(obtainStyledAttributes.getDrawable(C0300k.SearchView_submitBackground));
        this.mSearchIconResId = obtainStyledAttributes.getResourceId(C0300k.SearchView_searchIcon, 0);
        this.mSearchButton.setImageResource(this.mSearchIconResId);
        this.mSubmitButton.setImageDrawable(obtainStyledAttributes.getDrawable(C0300k.SearchView_goIcon));
        this.mCloseButton.setImageDrawable(obtainStyledAttributes.getDrawable(C0300k.SearchView_closeIcon));
        this.mVoiceButton.setImageDrawable(obtainStyledAttributes.getDrawable(C0300k.SearchView_voiceIcon));
        this.mSearchHintIcon.setImageDrawable(obtainStyledAttributes.getDrawable(C0300k.SearchView_searchIcon));
        this.mSuggestionRowLayout = obtainStyledAttributes.getResourceId(C0300k.SearchView_suggestionRowLayout, 0);
        this.mSuggestionCommitIconResId = obtainStyledAttributes.getResourceId(C0300k.SearchView_commitIcon, 0);
        this.mSearchButton.setOnClickListener(this.mOnClickListener);
        this.mCloseButton.setOnClickListener(this.mOnClickListener);
        this.mSubmitButton.setOnClickListener(this.mOnClickListener);
        this.mVoiceButton.setOnClickListener(this.mOnClickListener);
        this.mQueryTextView.setOnClickListener(this.mOnClickListener);
        this.mQueryTextView.addTextChangedListener(this.mTextWatcher);
        this.mQueryTextView.setOnEditorActionListener(this.mOnEditorActionListener);
        this.mQueryTextView.setOnItemClickListener(this.mOnItemClickListener);
        this.mQueryTextView.setOnItemSelectedListener(this.mOnItemSelectedListener);
        this.mQueryTextView.setOnKeyListener(this.mTextKeyListener);
        this.mQueryTextView.setOnFocusChangeListener(new C04317(this));
        setIconifiedByDefault(obtainStyledAttributes.getBoolean(C0300k.SearchView_iconifiedByDefault, true));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(C0300k.SearchView_android_maxWidth, -1);
        if (dimensionPixelSize != -1) {
            setMaxWidth(dimensionPixelSize);
        }
        CharSequence text = obtainStyledAttributes.getText(C0300k.SearchView_queryHint);
        if (!TextUtils.isEmpty(text)) {
            setQueryHint(text);
        }
        dimensionPixelSize = obtainStyledAttributes.getInt(C0300k.SearchView_android_imeOptions, -1);
        if (dimensionPixelSize != -1) {
            setImeOptions(dimensionPixelSize);
        }
        dimensionPixelSize = obtainStyledAttributes.getInt(C0300k.SearchView_android_inputType, -1);
        if (dimensionPixelSize != -1) {
            setInputType(dimensionPixelSize);
        }
        setFocusable(obtainStyledAttributes.getBoolean(C0300k.SearchView_android_focusable, true));
        obtainStyledAttributes.recycle();
        this.mVoiceWebSearchIntent = new Intent("android.speech.action.WEB_SEARCH");
        this.mVoiceWebSearchIntent.addFlags(268435456);
        this.mVoiceWebSearchIntent.putExtra("android.speech.extra.LANGUAGE_MODEL", "web_search");
        this.mVoiceAppSearchIntent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        this.mVoiceAppSearchIntent.addFlags(268435456);
        this.mDropDownAnchor = findViewById(this.mQueryTextView.getDropDownAnchor());
        if (this.mDropDownAnchor != null) {
            if (VERSION.SDK_INT >= 11) {
                addOnLayoutChangeListenerToDropDownAnchorSDK11();
            } else {
                addOnLayoutChangeListenerToDropDownAnchorBase();
            }
        }
        updateViewsVisibility(this.mIconifiedByDefault);
        updateQueryHint();
    }

    private void addOnLayoutChangeListenerToDropDownAnchorBase() {
        this.mDropDownAnchor.getViewTreeObserver().addOnGlobalLayoutListener(new C04339(this));
    }

    @TargetApi(11)
    private void addOnLayoutChangeListenerToDropDownAnchorSDK11() {
        this.mDropDownAnchor.addOnLayoutChangeListener(new C04328(this));
    }

    private void adjustDropDownSizeAndPosition() {
        if (this.mDropDownAnchor.getWidth() > 1) {
            Resources resources = getContext().getResources();
            int paddingLeft = this.mSearchPlate.getPaddingLeft();
            Rect rect = new Rect();
            boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
            int dimensionPixelSize = this.mIconifiedByDefault ? resources.getDimensionPixelSize(C0293d.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(C0293d.abc_dropdownitem_icon_width) : 0;
            this.mQueryTextView.getDropDownBackground().getPadding(rect);
            this.mQueryTextView.setDropDownHorizontalOffset(isLayoutRtl ? -rect.left : paddingLeft - (rect.left + dimensionPixelSize));
            this.mQueryTextView.setDropDownWidth((dimensionPixelSize + ((this.mDropDownAnchor.getWidth() + rect.left) + rect.right)) - paddingLeft);
        }
    }

    private Intent createIntent(String str, Uri uri, String str2, String str3, int i, String str4) {
        Intent intent = new Intent(str);
        intent.addFlags(268435456);
        if (uri != null) {
            intent.setData(uri);
        }
        intent.putExtra("user_query", this.mUserQuery);
        if (str3 != null) {
            intent.putExtra("query", str3);
        }
        if (str2 != null) {
            intent.putExtra("intent_extra_data_key", str2);
        }
        if (this.mAppSearchData != null) {
            intent.putExtra("app_data", this.mAppSearchData);
        }
        if (i != 0) {
            intent.putExtra("action_key", i);
            intent.putExtra("action_msg", str4);
        }
        if (IS_AT_LEAST_FROYO) {
            intent.setComponent(this.mSearchable.getSearchActivity());
        }
        return intent;
    }

    private Intent createIntentFromSuggestion(Cursor cursor, int i, String str) {
        try {
            String a = C0445a.m1781a(cursor, "suggest_intent_action");
            if (a == null && VERSION.SDK_INT >= 8) {
                a = this.mSearchable.getSuggestIntentAction();
            }
            if (a == null) {
                a = "android.intent.action.SEARCH";
            }
            String a2 = C0445a.m1781a(cursor, "suggest_intent_data");
            if (IS_AT_LEAST_FROYO && a2 == null) {
                a2 = this.mSearchable.getSuggestIntentData();
            }
            if (a2 != null) {
                String a3 = C0445a.m1781a(cursor, "suggest_intent_data_id");
                if (a3 != null) {
                    a2 = a2 + "/" + Uri.encode(a3);
                }
            }
            return createIntent(a, a2 == null ? null : Uri.parse(a2), C0445a.m1781a(cursor, "suggest_intent_extra_data"), C0445a.m1781a(cursor, "suggest_intent_query"), i, str);
        } catch (Throwable e) {
            int position;
            Throwable th = e;
            try {
                position = cursor.getPosition();
            } catch (RuntimeException e2) {
                position = -1;
            }
            Log.w(LOG_TAG, "Search suggestions cursor at row " + position + " returned exception.", th);
            return null;
        }
    }

    @TargetApi(8)
    private Intent createVoiceAppSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        String string;
        String string2;
        String str;
        int voiceMaxResults;
        String str2 = null;
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        Intent intent2 = new Intent("android.intent.action.SEARCH");
        intent2.setComponent(searchActivity);
        Parcelable activity = PendingIntent.getActivity(getContext(), 0, intent2, 1073741824);
        Bundle bundle = new Bundle();
        if (this.mAppSearchData != null) {
            bundle.putParcelable("app_data", this.mAppSearchData);
        }
        Intent intent3 = new Intent(intent);
        String str3 = "free_form";
        if (VERSION.SDK_INT >= 8) {
            Resources resources = getResources();
            if (searchableInfo.getVoiceLanguageModeId() != 0) {
                str3 = resources.getString(searchableInfo.getVoiceLanguageModeId());
            }
            string = searchableInfo.getVoicePromptTextId() != 0 ? resources.getString(searchableInfo.getVoicePromptTextId()) : null;
            string2 = searchableInfo.getVoiceLanguageId() != 0 ? resources.getString(searchableInfo.getVoiceLanguageId()) : null;
            if (searchableInfo.getVoiceMaxResults() != 0) {
                str = str3;
                voiceMaxResults = searchableInfo.getVoiceMaxResults();
            } else {
                str = str3;
                voiceMaxResults = 1;
            }
        } else {
            string2 = null;
            string = null;
            str = str3;
            voiceMaxResults = 1;
        }
        intent3.putExtra("android.speech.extra.LANGUAGE_MODEL", str);
        intent3.putExtra("android.speech.extra.PROMPT", string);
        intent3.putExtra("android.speech.extra.LANGUAGE", string2);
        intent3.putExtra("android.speech.extra.MAX_RESULTS", voiceMaxResults);
        str3 = "calling_package";
        if (searchActivity != null) {
            str2 = searchActivity.flattenToShortString();
        }
        intent3.putExtra(str3, str2);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT", activity);
        intent3.putExtra("android.speech.extra.RESULTS_PENDINGINTENT_BUNDLE", bundle);
        return intent3;
    }

    @TargetApi(8)
    private Intent createVoiceWebSearchIntent(Intent intent, SearchableInfo searchableInfo) {
        Intent intent2 = new Intent(intent);
        ComponentName searchActivity = searchableInfo.getSearchActivity();
        intent2.putExtra("calling_package", searchActivity == null ? null : searchActivity.flattenToShortString());
        return intent2;
    }

    private void dismissSuggestions() {
        this.mQueryTextView.dismissDropDown();
    }

    private void forceSuggestionQuery() {
        HIDDEN_METHOD_INVOKER.m1773a(this.mQueryTextView);
        HIDDEN_METHOD_INVOKER.m1775b(this.mQueryTextView);
    }

    private CharSequence getDecoratedHint(CharSequence charSequence) {
        if (!this.mIconifiedByDefault) {
            return charSequence;
        }
        Drawable drawable = this.mTintManager.getDrawable(this.mSearchIconResId);
        int textSize = (int) (((double) this.mQueryTextView.getTextSize()) * 1.25d);
        drawable.setBounds(0, 0, textSize, textSize);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder("   ");
        spannableStringBuilder.append(charSequence);
        spannableStringBuilder.setSpan(new ImageSpan(drawable), 1, 2, 33);
        return spannableStringBuilder;
    }

    private int getPreferredWidth() {
        return getContext().getResources().getDimensionPixelSize(C0293d.abc_search_view_preferred_width);
    }

    @TargetApi(8)
    private boolean hasVoiceSearch() {
        if (this.mSearchable == null || !this.mSearchable.getVoiceSearchEnabled()) {
            return IS_AT_LEAST_FROYO;
        }
        Intent intent = null;
        if (this.mSearchable.getVoiceSearchLaunchWebSearch()) {
            intent = this.mVoiceWebSearchIntent;
        } else if (this.mSearchable.getVoiceSearchLaunchRecognizer()) {
            intent = this.mVoiceAppSearchIntent;
        }
        return (intent == null || getContext().getPackageManager().resolveActivity(intent, 65536) == null) ? IS_AT_LEAST_FROYO : true;
    }

    static boolean isLandscapeMode(Context context) {
        return context.getResources().getConfiguration().orientation == 2 ? true : IS_AT_LEAST_FROYO;
    }

    private boolean isSubmitAreaEnabled() {
        return ((this.mSubmitButtonEnabled || this.mVoiceButtonEnabled) && !isIconified()) ? true : IS_AT_LEAST_FROYO;
    }

    private void launchIntent(Intent intent) {
        if (intent != null) {
            try {
                getContext().startActivity(intent);
            } catch (Throwable e) {
                Log.e(LOG_TAG, "Failed launch activity: " + intent, e);
            }
        }
    }

    private void launchQuerySearch(int i, String str, String str2) {
        getContext().startActivity(createIntent("android.intent.action.SEARCH", null, null, str2, i, str));
    }

    private boolean launchSuggestion(int i, int i2, String str) {
        Cursor a = this.mSuggestionsAdapter.m1265a();
        if (a == null || !a.moveToPosition(i)) {
            return IS_AT_LEAST_FROYO;
        }
        launchIntent(createIntentFromSuggestion(a, i2, str));
        return true;
    }

    private void onCloseClicked() {
        if (!TextUtils.isEmpty(this.mQueryTextView.getText())) {
            this.mQueryTextView.setText("");
            this.mQueryTextView.requestFocus();
            setImeVisibility(true);
        } else if (!this.mIconifiedByDefault) {
        } else {
            if (this.mOnCloseListener == null || !this.mOnCloseListener.onClose()) {
                clearFocus();
                updateViewsVisibility(true);
            }
        }
    }

    private boolean onItemClicked(int i, int i2, String str) {
        if (this.mOnSuggestionListener != null && this.mOnSuggestionListener.onSuggestionClick(i)) {
            return IS_AT_LEAST_FROYO;
        }
        launchSuggestion(i, 0, null);
        setImeVisibility(IS_AT_LEAST_FROYO);
        dismissSuggestions();
        return true;
    }

    private boolean onItemSelected(int i) {
        if (this.mOnSuggestionListener != null && this.mOnSuggestionListener.onSuggestionSelect(i)) {
            return IS_AT_LEAST_FROYO;
        }
        rewriteQueryFromSuggestion(i);
        return true;
    }

    private void onSearchClicked() {
        updateViewsVisibility(IS_AT_LEAST_FROYO);
        this.mQueryTextView.requestFocus();
        setImeVisibility(true);
        if (this.mOnSearchClickListener != null) {
            this.mOnSearchClickListener.onClick(this);
        }
    }

    private void onSubmitQuery() {
        CharSequence text = this.mQueryTextView.getText();
        if (text != null && TextUtils.getTrimmedLength(text) > 0) {
            if (this.mOnQueryChangeListener == null || !this.mOnQueryChangeListener.onQueryTextSubmit(text.toString())) {
                if (this.mSearchable != null) {
                    launchQuerySearch(0, null, text.toString());
                }
                setImeVisibility(IS_AT_LEAST_FROYO);
                dismissSuggestions();
            }
        }
    }

    private boolean onSuggestionsKey(View view, int i, KeyEvent keyEvent) {
        if (this.mSearchable == null || this.mSuggestionsAdapter == null || keyEvent.getAction() != 0 || !C0197g.m887a(keyEvent)) {
            return IS_AT_LEAST_FROYO;
        }
        if (i == 66 || i == 84 || i == 61) {
            return onItemClicked(this.mQueryTextView.getListSelection(), 0, null);
        }
        if (i != 21 && i != 22) {
            return (i == 19 && this.mQueryTextView.getListSelection() == 0) ? IS_AT_LEAST_FROYO : IS_AT_LEAST_FROYO;
        } else {
            this.mQueryTextView.setSelection(i == 21 ? 0 : this.mQueryTextView.length());
            this.mQueryTextView.setListSelection(0);
            this.mQueryTextView.clearListSelection();
            HIDDEN_METHOD_INVOKER.m1774a(this.mQueryTextView, true);
            return true;
        }
    }

    private void onTextChanged(CharSequence charSequence) {
        boolean z = true;
        CharSequence text = this.mQueryTextView.getText();
        this.mUserQuery = text;
        boolean z2 = !TextUtils.isEmpty(text) ? true : IS_AT_LEAST_FROYO;
        updateSubmitButton(z2);
        if (z2) {
            z = IS_AT_LEAST_FROYO;
        }
        updateVoiceButton(z);
        updateCloseButton();
        updateSubmitArea();
        if (!(this.mOnQueryChangeListener == null || TextUtils.equals(charSequence, this.mOldQueryText))) {
            this.mOnQueryChangeListener.onQueryTextChange(charSequence.toString());
        }
        this.mOldQueryText = charSequence.toString();
    }

    @TargetApi(8)
    private void onVoiceClicked() {
        if (this.mSearchable != null) {
            SearchableInfo searchableInfo = this.mSearchable;
            try {
                if (searchableInfo.getVoiceSearchLaunchWebSearch()) {
                    getContext().startActivity(createVoiceWebSearchIntent(this.mVoiceWebSearchIntent, searchableInfo));
                } else if (searchableInfo.getVoiceSearchLaunchRecognizer()) {
                    getContext().startActivity(createVoiceAppSearchIntent(this.mVoiceAppSearchIntent, searchableInfo));
                }
            } catch (ActivityNotFoundException e) {
                Log.w(LOG_TAG, "Could not find voice search activity");
            }
        }
    }

    private void postUpdateFocusedState() {
        post(this.mUpdateDrawableStateRunnable);
    }

    private void rewriteQueryFromSuggestion(int i) {
        CharSequence text = this.mQueryTextView.getText();
        Cursor a = this.mSuggestionsAdapter.m1265a();
        if (a != null) {
            if (a.moveToPosition(i)) {
                CharSequence c = this.mSuggestionsAdapter.m1274c(a);
                if (c != null) {
                    setQuery(c);
                    return;
                } else {
                    setQuery(text);
                    return;
                }
            }
            setQuery(text);
        }
    }

    private void setImeVisibility(boolean z) {
        if (z) {
            post(this.mShowImeRunnable);
            return;
        }
        removeCallbacks(this.mShowImeRunnable);
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    private void setQuery(CharSequence charSequence) {
        this.mQueryTextView.setText(charSequence);
        this.mQueryTextView.setSelection(TextUtils.isEmpty(charSequence) ? 0 : charSequence.length());
    }

    private void updateCloseButton() {
        int i = 1;
        int i2 = 0;
        int i3 = !TextUtils.isEmpty(this.mQueryTextView.getText()) ? 1 : 0;
        if (i3 == 0 && (!this.mIconifiedByDefault || this.mExpandedInActionView)) {
            i = 0;
        }
        ImageView imageView = this.mCloseButton;
        if (i == 0) {
            i2 = 8;
        }
        imageView.setVisibility(i2);
        this.mCloseButton.getDrawable().setState(i3 != 0 ? ENABLED_STATE_SET : EMPTY_STATE_SET);
    }

    private void updateFocusedState() {
        boolean hasFocus = this.mQueryTextView.hasFocus();
        this.mSearchPlate.getBackground().setState(hasFocus ? ENABLED_FOCUSED_STATE_SET : EMPTY_STATE_SET);
        this.mSubmitArea.getBackground().setState(hasFocus ? ENABLED_FOCUSED_STATE_SET : EMPTY_STATE_SET);
        invalidate();
    }

    private void updateQueryHint() {
        if (this.mQueryHint != null) {
            this.mQueryTextView.setHint(getDecoratedHint(this.mQueryHint));
        } else if (!IS_AT_LEAST_FROYO || this.mSearchable == null) {
            this.mQueryTextView.setHint(getDecoratedHint(""));
        } else {
            CharSequence charSequence = null;
            int hintId = this.mSearchable.getHintId();
            if (hintId != 0) {
                charSequence = getContext().getString(hintId);
            }
            if (charSequence != null) {
                this.mQueryTextView.setHint(getDecoratedHint(charSequence));
            }
        }
    }

    @TargetApi(8)
    private void updateSearchAutoComplete() {
        int i = 1;
        this.mQueryTextView.setThreshold(this.mSearchable.getSuggestThreshold());
        this.mQueryTextView.setImeOptions(this.mSearchable.getImeOptions());
        int inputType = this.mSearchable.getInputType();
        if ((inputType & 15) == 1) {
            inputType &= -65537;
            if (this.mSearchable.getSuggestAuthority() != null) {
                inputType = (inputType | 65536) | 524288;
            }
        }
        this.mQueryTextView.setInputType(inputType);
        if (this.mSuggestionsAdapter != null) {
            this.mSuggestionsAdapter.m1269a(null);
        }
        if (this.mSearchable.getSuggestAuthority() != null) {
            this.mSuggestionsAdapter = new C0445a(getContext(), this, this.mSearchable, this.mOutsideDrawablesCache);
            this.mQueryTextView.setAdapter(this.mSuggestionsAdapter);
            C0445a c0445a = (C0445a) this.mSuggestionsAdapter;
            if (this.mQueryRefinement) {
                i = 2;
            }
            c0445a.m1797a(i);
        }
    }

    private void updateSubmitArea() {
        int i = 8;
        if (isSubmitAreaEnabled() && (this.mSubmitButton.getVisibility() == 0 || this.mVoiceButton.getVisibility() == 0)) {
            i = 0;
        }
        this.mSubmitArea.setVisibility(i);
    }

    private void updateSubmitButton(boolean z) {
        int i = 8;
        if (this.mSubmitButtonEnabled && isSubmitAreaEnabled() && hasFocus() && (z || !this.mVoiceButtonEnabled)) {
            i = 0;
        }
        this.mSubmitButton.setVisibility(i);
    }

    private void updateViewsVisibility(boolean z) {
        boolean z2 = true;
        int i = 8;
        this.mIconified = z;
        int i2 = z ? 0 : 8;
        boolean z3 = !TextUtils.isEmpty(this.mQueryTextView.getText()) ? true : IS_AT_LEAST_FROYO;
        this.mSearchButton.setVisibility(i2);
        updateSubmitButton(z3);
        this.mSearchEditFrame.setVisibility(z ? 8 : 0);
        ImageView imageView = this.mSearchHintIcon;
        if (!this.mIconifiedByDefault) {
            i = 0;
        }
        imageView.setVisibility(i);
        updateCloseButton();
        if (z3) {
            z2 = IS_AT_LEAST_FROYO;
        }
        updateVoiceButton(z2);
        updateSubmitArea();
    }

    private void updateVoiceButton(boolean z) {
        int i;
        if (this.mVoiceButtonEnabled && !isIconified() && z) {
            i = 0;
            this.mSubmitButton.setVisibility(8);
        } else {
            i = 8;
        }
        this.mVoiceButton.setVisibility(i);
    }

    public void clearFocus() {
        this.mClearingFocus = true;
        setImeVisibility(IS_AT_LEAST_FROYO);
        super.clearFocus();
        this.mQueryTextView.clearFocus();
        this.mClearingFocus = IS_AT_LEAST_FROYO;
    }

    public int getImeOptions() {
        return this.mQueryTextView.getImeOptions();
    }

    public int getInputType() {
        return this.mQueryTextView.getInputType();
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public CharSequence getQuery() {
        return this.mQueryTextView.getText();
    }

    public CharSequence getQueryHint() {
        if (this.mQueryHint != null) {
            return this.mQueryHint;
        }
        if (!IS_AT_LEAST_FROYO || this.mSearchable == null) {
            return null;
        }
        int hintId = this.mSearchable.getHintId();
        return hintId != 0 ? getContext().getString(hintId) : null;
    }

    int getSuggestionCommitIconResId() {
        return this.mSuggestionCommitIconResId;
    }

    int getSuggestionRowLayout() {
        return this.mSuggestionRowLayout;
    }

    public C0267b getSuggestionsAdapter() {
        return this.mSuggestionsAdapter;
    }

    public boolean isIconfiedByDefault() {
        return this.mIconifiedByDefault;
    }

    public boolean isIconified() {
        return this.mIconified;
    }

    public boolean isQueryRefinementEnabled() {
        return this.mQueryRefinement;
    }

    public boolean isSubmitButtonEnabled() {
        return this.mSubmitButtonEnabled;
    }

    public void onActionViewCollapsed() {
        setQuery("", IS_AT_LEAST_FROYO);
        clearFocus();
        updateViewsVisibility(true);
        this.mQueryTextView.setImeOptions(this.mCollapsedImeOptions);
        this.mExpandedInActionView = IS_AT_LEAST_FROYO;
    }

    public void onActionViewExpanded() {
        if (!this.mExpandedInActionView) {
            this.mExpandedInActionView = true;
            this.mCollapsedImeOptions = this.mQueryTextView.getImeOptions();
            this.mQueryTextView.setImeOptions(this.mCollapsedImeOptions | 33554432);
            this.mQueryTextView.setText("");
            setIconified(IS_AT_LEAST_FROYO);
        }
    }

    protected void onDetachedFromWindow() {
        removeCallbacks(this.mUpdateDrawableStateRunnable);
        post(this.mReleaseCursorRunnable);
        super.onDetachedFromWindow();
    }

    protected void onMeasure(int i, int i2) {
        if (isIconified()) {
            super.onMeasure(i, i2);
            return;
        }
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        switch (mode) {
            case RtlSpacingHelper.UNDEFINED /*-2147483648*/:
                if (this.mMaxWidth <= 0) {
                    size = Math.min(getPreferredWidth(), size);
                    break;
                } else {
                    size = Math.min(this.mMaxWidth, size);
                    break;
                }
            case C0627j.View_android_focusable /*0*/:
                if (this.mMaxWidth <= 0) {
                    size = getPreferredWidth();
                    break;
                } else {
                    size = this.mMaxWidth;
                    break;
                }
            case 1073741824:
                if (this.mMaxWidth > 0) {
                    size = Math.min(this.mMaxWidth, size);
                    break;
                }
                break;
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, 1073741824), i2);
    }

    void onQueryRefine(CharSequence charSequence) {
        setQuery(charSequence);
    }

    void onTextFocusChanged() {
        updateViewsVisibility(isIconified());
        postUpdateFocusedState();
        if (this.mQueryTextView.hasFocus()) {
            forceSuggestionQuery();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        postUpdateFocusedState();
    }

    public boolean requestFocus(int i, Rect rect) {
        if (this.mClearingFocus || !isFocusable()) {
            return IS_AT_LEAST_FROYO;
        }
        if (isIconified()) {
            return super.requestFocus(i, rect);
        }
        boolean requestFocus = this.mQueryTextView.requestFocus(i, rect);
        if (requestFocus) {
            updateViewsVisibility(IS_AT_LEAST_FROYO);
        }
        return requestFocus;
    }

    public void setAppSearchData(Bundle bundle) {
        this.mAppSearchData = bundle;
    }

    public void setIconified(boolean z) {
        if (z) {
            onCloseClicked();
        } else {
            onSearchClicked();
        }
    }

    public void setIconifiedByDefault(boolean z) {
        if (this.mIconifiedByDefault != z) {
            this.mIconifiedByDefault = z;
            updateViewsVisibility(z);
            updateQueryHint();
        }
    }

    public void setImeOptions(int i) {
        this.mQueryTextView.setImeOptions(i);
    }

    public void setInputType(int i) {
        this.mQueryTextView.setInputType(i);
    }

    public void setMaxWidth(int i) {
        this.mMaxWidth = i;
        requestLayout();
    }

    public void setOnCloseListener(OnCloseListener onCloseListener) {
        this.mOnCloseListener = onCloseListener;
    }

    public void setOnQueryTextFocusChangeListener(OnFocusChangeListener onFocusChangeListener) {
        this.mOnQueryTextFocusChangeListener = onFocusChangeListener;
    }

    public void setOnQueryTextListener(OnQueryTextListener onQueryTextListener) {
        this.mOnQueryChangeListener = onQueryTextListener;
    }

    public void setOnSearchClickListener(OnClickListener onClickListener) {
        this.mOnSearchClickListener = onClickListener;
    }

    public void setOnSuggestionListener(OnSuggestionListener onSuggestionListener) {
        this.mOnSuggestionListener = onSuggestionListener;
    }

    public void setQuery(CharSequence charSequence, boolean z) {
        this.mQueryTextView.setText(charSequence);
        if (charSequence != null) {
            this.mQueryTextView.setSelection(this.mQueryTextView.length());
            this.mUserQuery = charSequence;
        }
        if (z && !TextUtils.isEmpty(charSequence)) {
            onSubmitQuery();
        }
    }

    public void setQueryHint(CharSequence charSequence) {
        this.mQueryHint = charSequence;
        updateQueryHint();
    }

    public void setQueryRefinementEnabled(boolean z) {
        this.mQueryRefinement = z;
        if (this.mSuggestionsAdapter instanceof C0445a) {
            ((C0445a) this.mSuggestionsAdapter).m1797a(z ? 2 : 1);
        }
    }

    public void setSearchableInfo(SearchableInfo searchableInfo) {
        this.mSearchable = searchableInfo;
        if (this.mSearchable != null) {
            if (IS_AT_LEAST_FROYO) {
                updateSearchAutoComplete();
            }
            updateQueryHint();
        }
        boolean z = (IS_AT_LEAST_FROYO && hasVoiceSearch()) ? true : IS_AT_LEAST_FROYO;
        this.mVoiceButtonEnabled = z;
        if (this.mVoiceButtonEnabled) {
            this.mQueryTextView.setPrivateImeOptions(IME_OPTION_NO_MICROPHONE);
        }
        updateViewsVisibility(isIconified());
    }

    public void setSubmitButtonEnabled(boolean z) {
        this.mSubmitButtonEnabled = z;
        updateViewsVisibility(isIconified());
    }

    public void setSuggestionsAdapter(C0267b c0267b) {
        this.mSuggestionsAdapter = c0267b;
        this.mQueryTextView.setAdapter(this.mSuggestionsAdapter);
    }
}
