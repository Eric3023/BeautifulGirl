package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class RecommendPresent implements RecommendContract.Presenter, RecommendServerHelper.OnRecommendDataChangedListener{

    private RecommendContract.View view;
    private RecommendServerHelper serverHelper;

    public RecommendPresent(RecommendContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new RecommendServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadRecommendTab(context);
        loadRecommend(context, UrlConfig.TAG_FIRST);
    }

    @Override
    public void loadRecommendTab(Context context) {
        serverHelper.loadRecommendTab(context);
    }

    @Override
    public void loadRecommend(Context context, String tag) {
        serverHelper.loadRecommend(context, tag);
    }

    @Override
    public void onRecommendTabDataChanged(List<RecommendTabBean> tabBeans) {
        if(tabBeans!=null){
        Log.i("Dong", "获取Recommend Tab数据："+tabBeans.size());
        view.RecommendTabChanged(tabBeans);
        }
    }

    @Override
    public void onRecommendDataChanged(List<TestBean.DataBean> resultsBeans) {
        if(resultsBeans!=null){
        Log.i("Dong", "获取Recommend数据："+resultsBeans.size());
        view.RecommendDataChanged(resultsBeans);}
    }
}
