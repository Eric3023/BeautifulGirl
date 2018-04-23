package com.dong.beautifulgirl.http;

import android.content.Context;

import com.dong.beautifulgirl.modular.mainmodular.findmodular.FindBean;
import com.dong.beautifulgirl.modular.mainmodular.homemodular.HomeBean;
import com.dong.beautifulgirl.modular.mainmodular.minemodular.MineLikeBean;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HeadModel {

    public static Observable<HomeBean> getHomeData(Context context, int pn, int rn , String tag1, String tag2, String ie){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getHomeData(pn, rn, tag1, tag2, ie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<HomeBean> getHomeHeadData(Context context, int pn, int rn , String tag1, String tag2, String ie){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getHomeHeadData(pn, rn, tag1, tag2, ie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<HomeBean> getHomeCardData(Context context, int pn, int rn , String tag1, String tag2, String ie){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getHomeCardData(pn, rn, tag1, tag2, ie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<RecommendBean> getRecommendData(Context context, int pn, int rn , String tag1, String tag2, String ie){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getRecommendData(pn, rn, tag1, tag2, ie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<FindBean> getFindData(Context context, int pn, int rn , String tag1, String tag2, String ie){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getFindData(pn, rn, tag1, tag2, ie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<FindBean> getFindHeadData(Context context, int pn, int rn , String tag1, String tag2, String ie){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getFindHeadData(pn, rn, tag1, tag2, ie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<MineLikeBean> getMineLikeData(Context context, int pn, int rn , String tag1, String tag2, String ie){
        return RetrofitHelper.getInstance()
                .getService(context)
                .getMineLikeData(pn, rn, tag1, tag2, ie)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
