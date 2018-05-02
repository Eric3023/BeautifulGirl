package com.dong.beautifulgirl.modular.mainmodular.findmodular;

import android.content.Context;

import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class FindPresenter implements FindContract.Presenter, FindServerHelper.OnFindDataChangedListener{

    private FindContract.View view;
    private FindServerHelper findServerHelper;

    public FindPresenter(FindContract.View view) {
        this.view = view;
        view.setPresenter(this);

        findServerHelper = new FindServerHelper();
        findServerHelper.setOnHomeDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadFindData(context);
        loadFindHeadData(context);
    }


    @Override
    public void OnFindDataChanged(List<TestBean.DataBean> resultsBeans) {
        view.findDataChanged(resultsBeans);
    }

    @Override
    public void OnFindDataHeadChanged(List<TestBean.DataBean> resultsBeans) {
        view.findDataHeadChanged(resultsBeans);
    }

    @Override
    public void loadFindData(Context context) {
        findServerHelper.loadFindData(context);
    }

    @Override
    public void loadFindHeadData(Context context) {
        findServerHelper.loadFindHeadData(context);
    }
}
