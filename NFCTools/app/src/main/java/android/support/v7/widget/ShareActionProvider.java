package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v4.view.C0187d;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.support.v7.internal.widget.ActivityChooserModel.OnChooseActivityListener;
import android.support.v7.internal.widget.ActivityChooserView;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.p009a.C0301a.C0290a;
import android.support.v7.p009a.C0301a.C0298i;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;

public class ShareActionProvider extends C0187d {
    private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    private final Context mContext;
    private int mMaxShownActivityCount;
    private OnChooseActivityListener mOnChooseActivityListener;
    private final C0437b mOnMenuItemClickListener;
    private OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    private String mShareHistoryFileName;

    public interface OnShareTargetSelectedListener {
        boolean onShareTargetSelected(ShareActionProvider shareActionProvider, Intent intent);
    }

    /* renamed from: android.support.v7.widget.ShareActionProvider.a */
    private class C0436a implements OnChooseActivityListener {
        final /* synthetic */ ShareActionProvider f898a;

        private C0436a(ShareActionProvider shareActionProvider) {
            this.f898a = shareActionProvider;
        }

        public boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent) {
            if (this.f898a.mOnShareTargetSelectedListener != null) {
                this.f898a.mOnShareTargetSelectedListener.onShareTargetSelected(this.f898a, intent);
            }
            return false;
        }
    }

    /* renamed from: android.support.v7.widget.ShareActionProvider.b */
    private class C0437b implements OnMenuItemClickListener {
        final /* synthetic */ ShareActionProvider f899a;

        private C0437b(ShareActionProvider shareActionProvider) {
            this.f899a = shareActionProvider;
        }

        public boolean onMenuItemClick(MenuItem menuItem) {
            Intent chooseActivity = ActivityChooserModel.get(this.f899a.mContext, this.f899a.mShareHistoryFileName).chooseActivity(menuItem.getItemId());
            if (chooseActivity != null) {
                chooseActivity.addFlags(524288);
                this.f899a.mContext.startActivity(chooseActivity);
            }
            return true;
        }
    }

    public ShareActionProvider(Context context) {
        super(context);
        this.mMaxShownActivityCount = DEFAULT_INITIAL_ACTIVITY_COUNT;
        this.mOnMenuItemClickListener = new C0437b();
        this.mShareHistoryFileName = DEFAULT_SHARE_HISTORY_FILE_NAME;
        this.mContext = context;
    }

    private void setActivityChooserPolicyIfNeeded() {
        if (this.mOnShareTargetSelectedListener != null) {
            if (this.mOnChooseActivityListener == null) {
                this.mOnChooseActivityListener = new C0436a();
            }
            ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setOnChooseActivityListener(this.mOnChooseActivityListener);
        }
    }

    public boolean hasSubMenu() {
        return true;
    }

    public View onCreateActionView() {
        ActivityChooserModel activityChooserModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
        View activityChooserView = new ActivityChooserView(this.mContext);
        activityChooserView.setActivityChooserModel(activityChooserModel);
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(C0290a.actionModeShareDrawable, typedValue, true);
        activityChooserView.setExpandActivityOverflowButtonDrawable(TintManager.getDrawable(this.mContext, typedValue.resourceId));
        activityChooserView.setProvider(this);
        activityChooserView.setDefaultActionButtonContentDescription(C0298i.abc_shareactionprovider_share_with_application);
        activityChooserView.setExpandActivityOverflowButtonContentDescription(C0298i.abc_shareactionprovider_share_with);
        return activityChooserView;
    }

    public void onPrepareSubMenu(SubMenu subMenu) {
        int i;
        subMenu.clear();
        ActivityChooserModel activityChooserModel = ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName);
        PackageManager packageManager = this.mContext.getPackageManager();
        int activityCount = activityChooserModel.getActivityCount();
        int min = Math.min(activityCount, this.mMaxShownActivityCount);
        for (i = 0; i < min; i++) {
            ResolveInfo activity = activityChooserModel.getActivity(i);
            subMenu.add(0, i, i, activity.loadLabel(packageManager)).setIcon(activity.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
        }
        if (min < activityCount) {
            SubMenu addSubMenu = subMenu.addSubMenu(0, min, min, this.mContext.getString(C0298i.abc_activity_chooser_view_see_all));
            for (i = 0; i < activityCount; i++) {
                activity = activityChooserModel.getActivity(i);
                addSubMenu.add(0, i, i, activity.loadLabel(packageManager)).setIcon(activity.loadIcon(packageManager)).setOnMenuItemClickListener(this.mOnMenuItemClickListener);
            }
        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener onShareTargetSelectedListener) {
        this.mOnShareTargetSelectedListener = onShareTargetSelectedListener;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareHistoryFileName(String str) {
        this.mShareHistoryFileName = str;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareIntent(Intent intent) {
        ActivityChooserModel.get(this.mContext, this.mShareHistoryFileName).setIntent(intent);
    }
}
