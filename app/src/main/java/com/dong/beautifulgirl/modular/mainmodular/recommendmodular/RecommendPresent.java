package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;
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
    public void start(Context context) {
        loadRecommend(context);
    }

    @Override
    public void loadRecommend(Context context) {
        serverHelper.loadRecommend(context);
    }

    @Override
    public void onRecommendDataChanged(List<RecommendBean.ResultsBean> resultsBeans) {
        Log.i("Dong", "获取Recommend数据："+resultsBeans.size());
        view.RecommendDataChanged(resultsBeans);
    }
}
