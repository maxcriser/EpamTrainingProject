package com.wakdev.libs.commons;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import java.util.Random;

public class TaskerIntent extends Intent {
    private static Random f1118a;
    private int f1119b;

    static {
        f1118a = new Random();
    }

    public TaskerIntent() {
        super("net.dinglisch.android.tasker.ACTION_TASK");
        this.f1119b = 0;
        m2041c();
        m2038a(m2039b());
    }

    public static Intent m2036a() {
        return new Intent("net.dinglisch.android.tasker.ACTION_TASK_SELECT").setFlags(1082392576);
    }

    public static String m2037a(Context context) {
        String str = null;
        try {
            context.getPackageManager().getPackageInfo("net.dinglisch.android.tasker", 0);
            str = "net.dinglisch.android.tasker";
        } catch (NameNotFoundException e) {
        }
        try {
            context.getPackageManager().getPackageInfo("net.dinglisch.android.taskerm", 0);
            return "net.dinglisch.android.taskerm";
        } catch (NameNotFoundException e2) {
            return str;
        }
    }

    private void m2038a(String str) {
        putExtra("version_number", "1.1");
        putExtra("task_name", str);
    }

    private String m2039b() {
        return Long.toString(f1118a.nextLong());
    }

    public static boolean m2040b(Context context) {
        return m2037a(context) != null;
    }

    private void m2041c() {
        setData(Uri.parse("id:" + m2039b()));
    }
}
