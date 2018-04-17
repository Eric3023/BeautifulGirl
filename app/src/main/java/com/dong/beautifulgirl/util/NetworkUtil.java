package com.dong.beautifulgirl.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by donghuadong on 2018/4/17.
 */

public class NetworkUtil {

    /**
     * 网络是否可用
     * @param context
     * @return
     */
    public static boolean isNetAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return info != null && info.isAvailable();
    }
}
