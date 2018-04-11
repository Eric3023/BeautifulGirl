package com.dong.beautifulgirl.modular.homemodular;

import com.dong.beautifulgirl.base.BasePresenter;

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
    public void start() {
        loadHomeData();
    }


    @Override
    public void loadHomeData() {
        homeServerHelper.loadHomeData();
    }

    @Override
    public void OnHomeDataChanged(List<HomeBean> homeBeans) {
        view.homeDataChanged(homeBeans);
    }
}
