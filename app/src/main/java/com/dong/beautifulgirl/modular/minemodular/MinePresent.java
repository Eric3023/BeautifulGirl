package com.dong.beautifulgirl.modular.minemodular;

import android.util.Log;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MinePresent implements MineContract.Presenter, MineServerHelper.OnRecommendDataChangedListener{

    private MineContract.View view;
    private MineServerHelper serverHelper;

    public MinePresent(MineContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new MineServerHelper();
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
    public void onRecommendDataChanged(List<MineBean> recommendBeans) {
        Log.i("Dong", "获取Recommend数据："+recommendBeans.size());
        view.RecommendDataChanged(recommendBeans);
    }
}
