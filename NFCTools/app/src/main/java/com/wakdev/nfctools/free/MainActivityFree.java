package com.wakdev.nfctools.free;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.wakdev.libs.core.C0507a;
import com.wakdev.nfctools.C0604l;

public class MainActivityFree extends C0604l {
    private C0507a f2395o;

    public void moreTasksOptions(View view) {
        startActivityForResult(new Intent(this, ChooseTasksOptionActivity.class), 1);
    }

    public void moreWriteOptions(View view) {
        startActivityForResult(new Intent(this, ChooseWriteOptionActivity.class), 1);
    }

    protected void onCreate(Bundle bundle) {
        this.f2395o = C0507a.m2175a();
        this.f2395o.m2186a(false);
        super.onCreate(bundle);
    }
}
