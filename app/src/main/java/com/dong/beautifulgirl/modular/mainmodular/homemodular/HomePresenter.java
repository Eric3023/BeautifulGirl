package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;

import com.dong.beautifulgirl.test.TestBean;

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
        loadHomeData(context, 0);
        loadHomeHeadData(context);
        loadHomeCardData(context);
    }


    @Override
    public void loadHomeData(Context context, int page) {
        homeServerHelper.loadHomeData(context, page);
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
    public void OnHomeDataChanged(List<TestBean.DataBean> resultsBeans) {
        view.homeDataChanged(resultsBeans);
    }

    @Override
    public void OnHomeDataHeadChanged(List<TestBean.DataBean> resultsBeans) {
        view.homeDataHeadChanged(resultsBeans);
    }

    @Override
    public void OnHomeDataCardChanged(List<TestBean.DataBean> resultsBeans) {
        view.homeDataCardChanged(resultsBeans);
    }
}
