package com.dong.beautifulgirl.http;

import com.dong.beautifulgirl.modular.findmodular.FindBean;
import com.dong.beautifulgirl.modular.homemodular.HomeBean;
import com.dong.beautifulgirl.modular.recommendmodular.RecommendBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Dong on 2018/3/13.
 */

public interface ApiService {

    @GET(UrlConfig.HOME_URL)
    Observable<HomeBean> getHomeData();

    @GET(UrlConfig.HOME_HEAD_URL)
    Observable<HomeBean> getHomeHeadData();

    @GET(UrlConfig.RECOMMEND_URL)
    Observable<RecommendBean> getRecommendData();

    @GET(UrlConfig.FIND_URL)
    Observable<FindBean> getFindData();

    @GET(UrlConfig.FIND_HEAD_URL)
    Observable<FindBean> getFindHeadData();

}
