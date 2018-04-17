package com.dong.beautifulgirl.http;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.util.NetworkUtil;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by donghuadong on 2018/4/17.
 */

public class CachInterceptor implements Interceptor {

    private static final int TIMEOUT_CONNECT = 60*5; //默认缓存时间

    @Override
    public Response intercept(Chain chain) throws IOException {

        //获取retrofit @headers里面的参数，参数可以自己定义，跟@headers里面对应就可以了
        //请求服务器
        String cache = chain.request().header("cache");

        //服务器返回
        okhttp3.Response originalResponse = chain.proceed(chain.request());
        String cacheControl = originalResponse.header("Cache-Control");

        //如果cacheControl为空，就让他TIMEOUT_CONNECT秒的缓存。注意这里的cacheControl是服务器返回的
        if (cacheControl == null) {
            //如果cache没值，缓存时间为TIMEOUT_CONNECT，有的话就为cache的值
            if (cache == null || "".equals(cache)) {
                cache = TIMEOUT_CONNECT + "";
            }
            originalResponse = originalResponse.newBuilder()
                    .header("Cache-Control", "public, max-age=" + cache)
                    .build();
            return originalResponse;
        } else {
            return originalResponse;
        }

    }
}
