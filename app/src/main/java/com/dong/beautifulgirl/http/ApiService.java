package com.dong.beautifulgirl.http;

import com.dong.beautifulgirl.modular.mainmodular.findmodular.FindBean;
import com.dong.beautifulgirl.modular.mainmodular.homemodular.HomeBean;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Dong on 2018/3/13.
 */

public interface ApiService {

    @Headers("cache:600")
    @GET(UrlConfig.HOME_URL)
    Observable<HomeBean> getHomeData();

    @Headers("cache:600")
    @GET(UrlConfig.HOME_HEAD_URL)
    Observable<HomeBean> getHomeHeadData();

    @Headers("cache:600")
    @GET(UrlConfig.HOME_CARD_URL)
    Observable<HomeBean> getHomeCardData();

    @Headers("cache:600")
    @GET(UrlConfig.RECOMMEND_URL)
    Observable<RecommendBean> getRecommendData();

    @Headers("cache:600")
    @GET(UrlConfig.FIND_URL)
    Observable<FindBean> getFindData();

    @Headers("cache:600")
    @GET(UrlConfig.FIND_HEAD_URL)
    Observable<FindBean> getFindHeadData();

}
