package com.dong.beautifulgirl.http;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Dong on 2016/10/31.
 */

public class RetrofitHelper {

    private static volatile RetrofitHelper retrofitHelper = null;

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
                .addConverterFactory(
                        GsonConverterFactory.create(createGson()))
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

    /*
      再OKHttpClient中添加缓存,由于云端未设置缓存，此处无效
     */
    public OkHttpClient createHttpClient(Context context){
        File cachFile=new File(context.getExternalCacheDir(),"");
        Cache cache=new Cache(cachFile,24*60*60*1000);
        OkHttpClient client=new OkHttpClient.Builder()
                .cache(cache)
                .build();
        return  client;
    }
}
