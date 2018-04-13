package com.dong.beautifulgirl.http;

import android.content.Context;

import com.dong.beautifulgirl.test.TestBean;
import com.dong.beautifulgirl.modular.findmodular.FindBean;
import com.dong.beautifulgirl.modular.homemodular.HomeBean;
import com.dong.beautifulgirl.modular.recommendmodular.RecommendBean;

import retrofit2.Callback;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HeadModel {

    public static void getTestData(Context context, Callback<TestBean> callback){
        RetrofitHelper.getInstance()
                .getService(context)
                .getTestData()
                .enqueue(callback);
    }

    public static void getHomeData(Context context, Callback<HomeBean> callback){
        RetrofitHelper.getInstance()
                .getService(context)
                .getHomeData()
                .enqueue(callback);
    }

    public static void getHomeHeadData(Context context, Callback<HomeBean> callback){
        RetrofitHelper.getInstance()
                .getService(context)
                .getHomeHeadData()
                .enqueue(callback);
    }

    public static void getRecommendData(Context context, Callback<RecommendBean> callback){
        RetrofitHelper.getInstance()
                .getService(context)
                .getRecommendData()
                .enqueue(callback);
    }

    public static void getFindData(Context context, Callback<FindBean> callback){
        RetrofitHelper.getInstance()
                .getService(context)
                .getFindData()
                .enqueue(callback);
    }

    public static void getFindHeadData(Context context, Callback<FindBean> callback){
        RetrofitHelper.getInstance()
                .getService(context)
                .getFindHeadData()
                .enqueue(callback);
    }
}
