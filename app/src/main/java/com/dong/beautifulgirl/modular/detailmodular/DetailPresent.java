package com.dong.beautifulgirl.modular.detailmodular;

import android.content.Context;

import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class DetailPresent implements DetailContract.Presenter, DetailServerHelper.OnDetailDataChangedListener{

    private DetailContract.View view;
    private DetailServerHelper serverHelper;

    public DetailPresent() {
        serverHelper = new DetailServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
//        loadDetailData(context);
    }

    @Override
    public void viewCreated(DetailContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);
    }

    @Override
    public void viewDestroyed() {
        this.view = null;
    }

    @Override
    public void loadDetailData(Context context, String tag, int rn, int page) {
        serverHelper.loadLDetailData(context, tag, rn, page);
    }

    @Override
    public void onDetailDataChanged(List<TestBean.DataBean> dataBeans) {
        view.detailDataChanged(dataBeans);
    }
}
