package com.dong.beautifulgirl.http;

import android.content.Context;

import com.dong.beautifulgirl.modular.mainmodular.findmodular.FindBean;
import com.dong.beautifulgirl.modular.mainmodular.homemodular.HomeBean;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HeadModel {

    public static Observable<HomeBean> getHomeData(Context context){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getHomeData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<HomeBean> getHomeHeadData(Context context){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getHomeHeadData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<RecommendBean> getRecommendData(Context context){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getRecommendData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<FindBean> getFindData(Context context){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getFindData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<FindBean> getFindHeadData(Context context){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getFindHeadData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
