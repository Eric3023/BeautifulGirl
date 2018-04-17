package com.dong.beautifulgirl.http;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.util.NetworkUtil;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by donghuadong on 2018/4/17.
 */

public class CachNoNetInterceptor implements Interceptor {


    private Context context;
    private static final int TIMEOUT_DISCONNECT = 60 * 60 * 24 * 7; //默认离线缓存时间

    public CachNoNetInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (!NetworkUtil.isNetAvailable(context)) {
            //离线缓存
            request = request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + TIMEOUT_DISCONNECT)
                    .build();
        }
        return chain.proceed(request);
    }
}
