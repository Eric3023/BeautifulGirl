package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HomePresenter implements HomeContract.Presenter, HomeServerHelper.OnHomeDataChangedListener{

    private HomeContract.View view;
    private HomeServerHelper homeServerHelper;

    public HomePresenter(HomeContract.View view) {
        this.view = view;
        view.setPresenter(this);

        homeServerHelper = new HomeServerHelper();
        homeServerHelper.setOnHomeDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadHomeData(context);
        loadHomeHeadData(context);
        loadHomeCardData(context);
    }


    @Override
    public void loadHomeData(Context context) {
        homeServerHelper.loadHomeData(context);
    }

    @Override
    public void loadHomeHeadData(Context context) {
        homeServerHelper.loadHomeHeadData(context);
    }

    @Override
    public void loadHomeCardData(Context context) {
        homeServerHelper.loadHomeCardData(context);
    }

    @Override
    public void OnHomeDataChanged(List<HomeBean.DataBean> resultsBeans) {
        view.homeDataChanged(resultsBeans);
    }

    @Override
    public void OnHomeDataHeadChanged(List<HomeBean.DataBean> resultsBeans) {
        view.homeDataHeadChanged(resultsBeans);
    }

    @Override
    public void OnHomeDataCardChanged(List<HomeBean.DataBean> resultsBeans) {
        view.homeDataCardChanged(resultsBeans);
    }
}
