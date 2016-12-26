package android.support.v7.internal.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.support.v4.p005d.C0081a;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlSerializer;

public class ActivityChooserModel extends DataSetObservable {
    private static final String ATTRIBUTE_ACTIVITY = "activity";
    private static final String ATTRIBUTE_TIME = "time";
    private static final String ATTRIBUTE_WEIGHT = "weight";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    private static final String LOG_TAG;
    private static final String TAG_HISTORICAL_RECORD = "historical-record";
    private static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map<String, ActivityChooserModel> sDataModelRegistry;
    private static final Object sRegistryLock;
    private final List<ActivityResolveInfo> mActivities;
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    private boolean mCanReadHistoricalData;
    private final Context mContext;
    private final List<HistoricalRecord> mHistoricalRecords;
    private boolean mHistoricalRecordsChanged;
    private final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock;
    private Intent mIntent;
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;

    public interface ActivityChooserModelClient {
        void setActivityChooserModel(ActivityChooserModel activityChooserModel);
    }

    public final class ActivityResolveInfo implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }

        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits(activityResolveInfo.weight) - Float.floatToIntBits(this.weight);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return ActivityChooserModel.DEBUG;
            }
            if (getClass() != obj.getClass()) {
                return ActivityChooserModel.DEBUG;
            }
            return Float.floatToIntBits(this.weight) != Float.floatToIntBits(((ActivityResolveInfo) obj).weight) ? ActivityChooserModel.DEBUG : true;
        }

        public int hashCode() {
            return Float.floatToIntBits(this.weight) + 31;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("resolveInfo:").append(this.resolveInfo.toString());
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.weight));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public interface ActivitySorter {
        void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2);
    }

    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(ComponentName componentName, long j, float f) {
            this.activity = componentName;
            this.time = j;
            this.weight = f;
        }

        public HistoricalRecord(String str, long j, float f) {
            this(ComponentName.unflattenFromString(str), j, f);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return ActivityChooserModel.DEBUG;
            }
            if (getClass() != obj.getClass()) {
                return ActivityChooserModel.DEBUG;
            }
            HistoricalRecord historicalRecord = (HistoricalRecord) obj;
            if (this.activity == null) {
                if (historicalRecord.activity != null) {
                    return ActivityChooserModel.DEBUG;
                }
            } else if (!this.activity.equals(historicalRecord.activity)) {
                return ActivityChooserModel.DEBUG;
            }
            return this.time != historicalRecord.time ? ActivityChooserModel.DEBUG : Float.floatToIntBits(this.weight) != Float.floatToIntBits(historicalRecord.weight) ? ActivityChooserModel.DEBUG : true;
        }

        public int hashCode() {
            return (((((this.activity == null ? 0 : this.activity.hashCode()) + 31) * 31) + ((int) (this.time ^ (this.time >>> 32)))) * 31) + Float.floatToIntBits(this.weight);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("; activity:").append(this.activity);
            stringBuilder.append("; time:").append(this.time);
            stringBuilder.append("; weight:").append(new BigDecimal((double) this.weight));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public interface OnChooseActivityListener {
        boolean onChooseActivity(ActivityChooserModel activityChooserModel, Intent intent);
    }

    /* renamed from: android.support.v7.internal.widget.ActivityChooserModel.a */
    private final class C0369a implements ActivitySorter {
        final /* synthetic */ ActivityChooserModel f791a;
        private final Map<String, ActivityResolveInfo> f792b;

        private C0369a(ActivityChooserModel activityChooserModel) {
            this.f791a = activityChooserModel;
            this.f792b = new HashMap();
        }

        public void sort(Intent intent, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            Map map = this.f792b;
            map.clear();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo) list.get(i);
                activityResolveInfo.weight = 0.0f;
                map.put(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo);
            }
            int size2 = list2.size() + ActivityChooserModel.INVALID_INDEX;
            float f = ActivityChooserModel.DEFAULT_HISTORICAL_RECORD_WEIGHT;
            int i2 = size2;
            while (i2 >= 0) {
                float f2;
                HistoricalRecord historicalRecord = (HistoricalRecord) list2.get(i2);
                ActivityResolveInfo activityResolveInfo2 = (ActivityResolveInfo) map.get(historicalRecord.activity.getPackageName());
                if (activityResolveInfo2 != null) {
                    activityResolveInfo2.weight = (historicalRecord.weight * f) + activityResolveInfo2.weight;
                    f2 = 0.95f * f;
                } else {
                    f2 = f;
                }
                i2 += ActivityChooserModel.INVALID_INDEX;
                f = f2;
            }
            Collections.sort(list);
        }
    }

    /* renamed from: android.support.v7.internal.widget.ActivityChooserModel.b */
    private final class C0370b extends AsyncTask<Object, Void, Void> {
        final /* synthetic */ ActivityChooserModel f793a;

        private C0370b(ActivityChooserModel activityChooserModel) {
            this.f793a = activityChooserModel;
        }

        public Void m1732a(Object... objArr) {
            int i = 0;
            List list = (List) objArr[0];
            String str = (String) objArr[1];
            try {
                OutputStream openFileOutput = this.f793a.mContext.openFileOutput(str, 0);
                XmlSerializer newSerializer = Xml.newSerializer();
                try {
                    newSerializer.setOutput(openFileOutput, null);
                    newSerializer.startDocument("UTF-8", Boolean.valueOf(true));
                    newSerializer.startTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                    int size = list.size();
                    while (i < size) {
                        HistoricalRecord historicalRecord = (HistoricalRecord) list.remove(0);
                        newSerializer.startTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                        newSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_ACTIVITY, historicalRecord.activity.flattenToString());
                        newSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_TIME, String.valueOf(historicalRecord.time));
                        newSerializer.attribute(null, ActivityChooserModel.ATTRIBUTE_WEIGHT, String.valueOf(historicalRecord.weight));
                        newSerializer.endTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORD);
                        i++;
                    }
                    newSerializer.endTag(null, ActivityChooserModel.TAG_HISTORICAL_RECORDS);
                    newSerializer.endDocument();
                    this.f793a.mCanReadHistoricalData = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e) {
                        }
                    }
                } catch (Throwable e2) {
                    Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + this.f793a.mHistoryFileName, e2);
                    this.f793a.mCanReadHistoricalData = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (Throwable e22) {
                    Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + this.f793a.mHistoryFileName, e22);
                    this.f793a.mCanReadHistoricalData = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e4) {
                        }
                    }
                } catch (Throwable e222) {
                    Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + this.f793a.mHistoryFileName, e222);
                    this.f793a.mCanReadHistoricalData = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e5) {
                        }
                    }
                } catch (Throwable th) {
                    this.f793a.mCanReadHistoricalData = true;
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException e6) {
                        }
                    }
                }
            } catch (Throwable e2222) {
                Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + str, e2222);
            }
            return null;
        }

        public /* synthetic */ Object doInBackground(Object[] objArr) {
            return m1732a(objArr);
        }
    }

    static {
        LOG_TAG = ActivityChooserModel.class.getSimpleName();
        sRegistryLock = new Object();
        sDataModelRegistry = new HashMap();
    }

    private ActivityChooserModel(Context context, String str) {
        this.mInstanceLock = new Object();
        this.mActivities = new ArrayList();
        this.mHistoricalRecords = new ArrayList();
        this.mActivitySorter = new C0369a();
        this.mHistoryMaxSize = DEFAULT_HISTORY_MAX_LENGTH;
        this.mCanReadHistoricalData = true;
        this.mReadShareHistoryCalled = DEBUG;
        this.mHistoricalRecordsChanged = true;
        this.mReloadActivities = DEBUG;
        this.mContext = context.getApplicationContext();
        if (TextUtils.isEmpty(str) || str.endsWith(HISTORY_FILE_EXTENSION)) {
            this.mHistoryFileName = str;
        } else {
            this.mHistoryFileName = str + HISTORY_FILE_EXTENSION;
        }
    }

    private boolean addHisoricalRecord(HistoricalRecord historicalRecord) {
        boolean add = this.mHistoricalRecords.add(historicalRecord);
        if (add) {
            this.mHistoricalRecordsChanged = true;
            pruneExcessiveHistoricalRecordsIfNeeded();
            persistHistoricalDataIfNeeded();
            sortActivitiesIfNeeded();
            notifyChanged();
        }
        return add;
    }

    private void ensureConsistentState() {
        int loadActivitiesIfNeeded = loadActivitiesIfNeeded() | readHistoricalDataIfNeeded();
        pruneExcessiveHistoricalRecordsIfNeeded();
        if (loadActivitiesIfNeeded != 0) {
            sortActivitiesIfNeeded();
            notifyChanged();
        }
    }

    public static ActivityChooserModel get(Context context, String str) {
        ActivityChooserModel activityChooserModel;
        synchronized (sRegistryLock) {
            activityChooserModel = (ActivityChooserModel) sDataModelRegistry.get(str);
            if (activityChooserModel == null) {
                activityChooserModel = new ActivityChooserModel(context, str);
                sDataModelRegistry.put(str, activityChooserModel);
            }
        }
        return activityChooserModel;
    }

    private boolean loadActivitiesIfNeeded() {
        if (!this.mReloadActivities || this.mIntent == null) {
            return DEBUG;
        }
        this.mReloadActivities = DEBUG;
        this.mActivities.clear();
        List queryIntentActivities = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
        int size = queryIntentActivities.size();
        for (int i = 0; i < size; i++) {
            this.mActivities.add(new ActivityResolveInfo((ResolveInfo) queryIntentActivities.get(i)));
        }
        return true;
    }

    private void persistHistoricalDataIfNeeded() {
        if (!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        } else if (this.mHistoricalRecordsChanged) {
            this.mHistoricalRecordsChanged = DEBUG;
            if (!TextUtils.isEmpty(this.mHistoryFileName)) {
                C0081a.m383a(new C0370b(), this.mHistoricalRecords, this.mHistoryFileName);
            }
        }
    }

    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        int size = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (size > 0) {
            this.mHistoricalRecordsChanged = true;
            for (int i = 0; i < size; i++) {
                HistoricalRecord historicalRecord = (HistoricalRecord) this.mHistoricalRecords.remove(0);
            }
        }
    }

    private boolean readHistoricalDataIfNeeded() {
        if (!this.mCanReadHistoricalData || !this.mHistoricalRecordsChanged || TextUtils.isEmpty(this.mHistoryFileName)) {
            return DEBUG;
        }
        this.mCanReadHistoricalData = DEBUG;
        this.mReadShareHistoryCalled = true;
        readHistoricalDataImpl();
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readHistoricalDataImpl() {
        /*
        r9 = this;
        r8 = 1;
        r0 = r9.mContext;	 Catch:{ FileNotFoundException -> 0x00d2 }
        r1 = r9.mHistoryFileName;	 Catch:{ FileNotFoundException -> 0x00d2 }
        r1 = r0.openFileInput(r1);	 Catch:{ FileNotFoundException -> 0x00d2 }
        r2 = android.util.Xml.newPullParser();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0 = 0;
        r2.setInput(r1, r0);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0 = 0;
    L_0x0012:
        if (r0 == r8) goto L_0x001c;
    L_0x0014:
        r3 = 2;
        if (r0 == r3) goto L_0x001c;
    L_0x0017:
        r0 = r2.next();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        goto L_0x0012;
    L_0x001c:
        r0 = "historical-records";
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0 = r0.equals(r3);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        if (r0 != 0) goto L_0x0051;
    L_0x0028:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r2 = "Share records file does not start with historical-records tag.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
    L_0x0030:
        r0 = move-exception;
        r2 = LOG_TAG;	 Catch:{ all -> 0x00c7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c7 }
        r3.<init>();	 Catch:{ all -> 0x00c7 }
        r4 = "Error reading historical recrod file: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r4 = r9.mHistoryFileName;	 Catch:{ all -> 0x00c7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c7 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x00c7 }
        if (r1 == 0) goto L_0x0050;
    L_0x004d:
        r1.close();	 Catch:{ IOException -> 0x00ce }
    L_0x0050:
        return;
    L_0x0051:
        r0 = r9.mHistoricalRecords;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0.clear();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
    L_0x0056:
        r3 = r2.next();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        if (r3 != r8) goto L_0x0064;
    L_0x005c:
        if (r1 == 0) goto L_0x0050;
    L_0x005e:
        r1.close();	 Catch:{ IOException -> 0x0062 }
        goto L_0x0050;
    L_0x0062:
        r0 = move-exception;
        goto L_0x0050;
    L_0x0064:
        r4 = 3;
        if (r3 == r4) goto L_0x0056;
    L_0x0067:
        r4 = 4;
        if (r3 == r4) goto L_0x0056;
    L_0x006a:
        r3 = r2.getName();	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r4 = "historical-record";
        r3 = r4.equals(r3);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        if (r3 != 0) goto L_0x00a1;
    L_0x0076:
        r0 = new org.xmlpull.v1.XmlPullParserException;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r2 = "Share records file not well-formed.";
        r0.<init>(r2);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        throw r0;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
    L_0x007e:
        r0 = move-exception;
        r2 = LOG_TAG;	 Catch:{ all -> 0x00c7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c7 }
        r3.<init>();	 Catch:{ all -> 0x00c7 }
        r4 = "Error reading historical recrod file: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r4 = r9.mHistoryFileName;	 Catch:{ all -> 0x00c7 }
        r3 = r3.append(r4);	 Catch:{ all -> 0x00c7 }
        r3 = r3.toString();	 Catch:{ all -> 0x00c7 }
        android.util.Log.e(r2, r3, r0);	 Catch:{ all -> 0x00c7 }
        if (r1 == 0) goto L_0x0050;
    L_0x009b:
        r1.close();	 Catch:{ IOException -> 0x009f }
        goto L_0x0050;
    L_0x009f:
        r0 = move-exception;
        goto L_0x0050;
    L_0x00a1:
        r3 = 0;
        r4 = "activity";
        r3 = r2.getAttributeValue(r3, r4);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r4 = 0;
        r5 = "time";
        r4 = r2.getAttributeValue(r4, r5);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r4 = java.lang.Long.parseLong(r4);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r6 = 0;
        r7 = "weight";
        r6 = r2.getAttributeValue(r6, r7);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r6 = java.lang.Float.parseFloat(r6);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r7 = new android.support.v7.internal.widget.ActivityChooserModel$HistoricalRecord;	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r7.<init>(r3, r4, r6);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        r0.add(r7);	 Catch:{ XmlPullParserException -> 0x0030, IOException -> 0x007e }
        goto L_0x0056;
    L_0x00c7:
        r0 = move-exception;
        if (r1 == 0) goto L_0x00cd;
    L_0x00ca:
        r1.close();	 Catch:{ IOException -> 0x00d0 }
    L_0x00cd:
        throw r0;
    L_0x00ce:
        r0 = move-exception;
        goto L_0x0050;
    L_0x00d0:
        r1 = move-exception;
        goto L_0x00cd;
    L_0x00d2:
        r0 = move-exception;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.internal.widget.ActivityChooserModel.readHistoricalDataImpl():void");
    }

    private boolean sortActivitiesIfNeeded() {
        if (this.mActivitySorter == null || this.mIntent == null || this.mActivities.isEmpty() || this.mHistoricalRecords.isEmpty()) {
            return DEBUG;
        }
        this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
        return true;
    }

    public Intent chooseActivity(int i) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == null) {
                return null;
            }
            ensureConsistentState();
            ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo) this.mActivities.get(i);
            ComponentName componentName = new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name);
            Intent intent = new Intent(this.mIntent);
            intent.setComponent(componentName);
            if (this.mActivityChoserModelPolicy != null) {
                if (this.mActivityChoserModelPolicy.onChooseActivity(this, new Intent(intent))) {
                    return null;
                }
            }
            addHisoricalRecord(new HistoricalRecord(componentName, System.currentTimeMillis(), (float) DEFAULT_HISTORICAL_RECORD_WEIGHT));
            return intent;
        }
    }

    public ResolveInfo getActivity(int i) {
        ResolveInfo resolveInfo;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            resolveInfo = ((ActivityResolveInfo) this.mActivities.get(i)).resolveInfo;
        }
        return resolveInfo;
    }

    public int getActivityCount() {
        int size;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            size = this.mActivities.size();
        }
        return size;
    }

    public int getActivityIndex(ResolveInfo resolveInfo) {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            List list = this.mActivities;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (((ActivityResolveInfo) list.get(i)).resolveInfo == resolveInfo) {
                    return i;
                }
            }
            return INVALID_INDEX;
        }
    }

    public ResolveInfo getDefaultActivity() {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            if (this.mActivities.isEmpty()) {
                return null;
            }
            ResolveInfo resolveInfo = ((ActivityResolveInfo) this.mActivities.get(0)).resolveInfo;
            return resolveInfo;
        }
    }

    public int getHistoryMaxSize() {
        int i;
        synchronized (this.mInstanceLock) {
            i = this.mHistoryMaxSize;
        }
        return i;
    }

    public int getHistorySize() {
        int size;
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            size = this.mHistoricalRecords.size();
        }
        return size;
    }

    public Intent getIntent() {
        Intent intent;
        synchronized (this.mInstanceLock) {
            intent = this.mIntent;
        }
        return intent;
    }

    public void setActivitySorter(ActivitySorter activitySorter) {
        synchronized (this.mInstanceLock) {
            if (this.mActivitySorter == activitySorter) {
                return;
            }
            this.mActivitySorter = activitySorter;
            if (sortActivitiesIfNeeded()) {
                notifyChanged();
            }
        }
    }

    public void setDefaultActivity(int i) {
        synchronized (this.mInstanceLock) {
            ensureConsistentState();
            ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo) this.mActivities.get(i);
            ActivityResolveInfo activityResolveInfo2 = (ActivityResolveInfo) this.mActivities.get(0);
            addHisoricalRecord(new HistoricalRecord(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), activityResolveInfo2 != null ? (activityResolveInfo2.weight - activityResolveInfo.weight) + 5.0f : DEFAULT_HISTORICAL_RECORD_WEIGHT));
        }
    }

    public void setHistoryMaxSize(int i) {
        synchronized (this.mInstanceLock) {
            if (this.mHistoryMaxSize == i) {
                return;
            }
            this.mHistoryMaxSize = i;
            pruneExcessiveHistoricalRecordsIfNeeded();
            if (sortActivitiesIfNeeded()) {
                notifyChanged();
            }
        }
    }

    public void setIntent(Intent intent) {
        synchronized (this.mInstanceLock) {
            if (this.mIntent == intent) {
                return;
            }
            this.mIntent = intent;
            this.mReloadActivities = true;
            ensureConsistentState();
        }
    }

    public void setOnChooseActivityListener(OnChooseActivityListener onChooseActivityListener) {
        synchronized (this.mInstanceLock) {
            this.mActivityChoserModelPolicy = onChooseActivityListener;
        }
    }
}
