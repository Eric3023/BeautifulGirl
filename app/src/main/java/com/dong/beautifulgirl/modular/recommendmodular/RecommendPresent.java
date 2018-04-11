package com.dong.beautifulgirl.modular.recommendmodular;

import android.util.Log;

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
    public void start() {
        loadRecommend();
    }

    @Override
    public void loadRecommend() {
        serverHelper.loadRecommend();
    }

    @Override
    public void onRecommendDataChanged(List<RecommendBean> recommendBeans) {
        Log.i("Dong", "获取Recommend数据："+recommendBeans.size());
        view.RecommendDataChanged(recommendBeans);
    }
}
