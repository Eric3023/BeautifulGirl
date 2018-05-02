package com.dong.beautifulgirl.http;

import com.dong.beautifulgirl.modular.detailmodular.DetailBean;
import com.dong.beautifulgirl.modular.mainmodular.findmodular.FindBean;
import com.dong.beautifulgirl.modular.mainmodular.homemodular.HomeBean;
import com.dong.beautifulgirl.modular.mainmodular.minemodular.MineLikeBean;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendBean;
import com.dong.beautifulgirl.test.TestBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Dong on 2018/3/13.
 */

public interface ApiService {

    @Headers("cache:600")
    @GET(UrlConfig.HOME_URL)
    Observable<TestBean> getTestData(@Query("pn") int pn,
                                     @Query("rn") int rn,
                                     @Query("tag1") String tag1,
                                     @Query("tag2") String tag2,
                                     @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.HOME_URL)
    Observable<HomeBean> getHomeData(@Query("pn") int pn,
                                     @Query("rn") int rn,
                                     @Query("tag1") String tag1,
                                     @Query("tag2") String tag2,
                                     @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.HOME_HEAD_URL)
    Observable<HomeBean> getHomeHeadData(@Query("pn") int pn,
                                         @Query("rn") int rn,
                                         @Query("tag1") String tag1,
                                         @Query("tag2") String tag2,
                                         @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.HOME_CARD_URL)
    Observable<HomeBean> getHomeCardData(@Query("pn") int pn,
                                         @Query("rn") int rn,
                                         @Query("tag1") String tag1,
                                         @Query("tag2") String tag2,
                                         @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.RECOMMEND_URL)
    Observable<RecommendBean> getRecommendData(@Query("pn") int pn,
                                               @Query("rn") int rn,
                                               @Query("tag1") String tag1,
                                               @Query("tag2") String tag2,
                                               @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.FIND_URL)
    Observable<FindBean> getFindData(@Query("pn") int pn,
                                     @Query("rn") int rn,
                                     @Query("tag1") String tag1,
                                     @Query("tag2") String tag2,
                                     @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.FIND_HEAD_URL)
    Observable<FindBean> getFindHeadData(@Query("pn") int pn,
                                         @Query("rn") int rn,
                                         @Query("tag1") String tag1,
                                         @Query("tag2") String tag2,
                                         @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.MINE_LIKE)
    Observable<MineLikeBean> getMineLikeData(@Query("pn") int pn,
                                             @Query("rn") int rn,
                                             @Query("tag1") String tag1,
                                             @Query("tag2") String tag2,
                                             @Query("ie") String ie);

    @Headers("cache:600")
    @GET(UrlConfig.DETAIL_URL)
    Observable<DetailBean> getDetailData(@Query("pn") int pn,
                                         @Query("rn") int rn,
                                         @Query("tag1") String tag1,
                                         @Query("tag2") String tag2,
                                         @Query("ie") String ie);


}
