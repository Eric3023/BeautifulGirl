package com.dong.beautifulgirl.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dong on 2016/10/31.
 */

public class RetrofitHelper {

    private static volatile RetrofitHelper retrofitHelper = null;
    private final int CACH_SIZE = 1024*1024*100;

    private RetrofitHelper() {

    }
    //获取单例
    public static RetrofitHelper getInstance() {
        if (retrofitHelper == null) {
            synchronized (RetrofitHelper.class) {
                if (retrofitHelper == null) {
                    retrofitHelper = new RetrofitHelper();
                }
            }
        }
        return retrofitHelper;
    }

    /**
     * 创建Retrofit
     */
    public Retrofit createRetrofit(Context context) {
        // String tmp = UrlConfig.BASE_URL;
        return new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .client(createHttpClient(context))
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public ApiService getService(Context context) {
        Retrofit retrofit = createRetrofit(context);
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    /**
     * 创建Gson
     */
    public Gson createGson() {
        return new GsonBuilder().serializeNulls()
                .enableComplexMapKeySerialization()
                // .setDateFormat("")
                .create();
    }

    /**
     *OKHttpClient中添加拦截器
     */
    public OkHttpClient createHttpClient(Context context){
        File cachFile=new File(context.getCacheDir(),"");
        Cache cache=new Cache(cachFile,CACH_SIZE);
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(new CookieInterceptor())
                .addNetworkInterceptor(new CachInterceptor())
                .addInterceptor(new CachNoNetInterceptor(context))
                .cache(cache)
                .build();
        return  client;
    }
}
