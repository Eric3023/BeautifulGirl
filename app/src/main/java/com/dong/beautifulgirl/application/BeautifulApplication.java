package com.dong.beautifulgirl.application;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class BeautifulApplication extends Application {

    private final String BUGLY_APP_ID = "a01107c8c8";

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext(), BUGLY_APP_ID, false);
        LeakCanary.install(this);
    }

    public static Context getBeatifulApplicationContext() {
        return context;
    }


}
