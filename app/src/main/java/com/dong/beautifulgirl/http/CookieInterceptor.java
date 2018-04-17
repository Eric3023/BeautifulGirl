package com.dong.beautifulgirl.http;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by donghuadong on 2018/4/17.
 */

public class CookieInterceptor implements Interceptor {

    private String cookie;

    public CookieInterceptor() {
        initCookie();
    }

    /**
     * 从配置文件获取Cookie
     */
    private void initCookie() {

    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        if(TextUtils.isEmpty(cookie)){

            Response response = chain.proceed(chain.request());

            String s = response.headers().toString();

//            Log.i("Dong", "header:"+s);

            //向配置文件中保存cookie

            return response;
        }else{
            Request.Builder builder = chain.request().newBuilder();
            builder.addHeader("Cookie", cookie);
            return chain.proceed(builder.build());
        }

    }
}
