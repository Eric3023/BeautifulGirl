package com.dong.beautifulgirl.application;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class BeautifulApplication extends Application {

    private final String BUGLY_APP_ID = "a01107c8c8";

    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), BUGLY_APP_ID, false);
        LeakCanary.install(this);
    }
}
