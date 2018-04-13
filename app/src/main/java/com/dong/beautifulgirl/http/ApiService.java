package com.dong.beautifulgirl.http;

import com.dong.beautifulgirl.test.TestBean;
import com.dong.beautifulgirl.modular.findmodular.FindBean;
import com.dong.beautifulgirl.modular.homemodular.HomeBean;
import com.dong.beautifulgirl.modular.recommendmodular.RecommendBean;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Dong on 2018/3/13.
 */

public interface ApiService {

    @GET(UrlConfig.TEST_URL)
    Call<TestBean> getTestData();

    @GET(UrlConfig.HOME_URL)
    Call<HomeBean> getHomeData();

    @GET(UrlConfig.HOME_HEAD_URL)
    Call<HomeBean> getHomeHeadData();

    @GET(UrlConfig.RECOMMEND_URL)
    Call<RecommendBean> getRecommendData();

    @GET(UrlConfig.FIND_URL)
    Call<FindBean> getFindData();

    @GET(UrlConfig.FIND_HEAD_URL)
    Call<FindBean> getFindHeadData();

}
