package com.dong.beautifulgirl.modular.detailmodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class DetailPresent implements DetailContract.Presenter, DetailServerHelper.OnDetailDataChangedListener{

    private DetailContract.View view;
    private DetailServerHelper serverHelper;

    public DetailPresent(DetailContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new DetailServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
//        loadDetailData(context);
    }

    @Override
    public void loadDetailData(Context context, String tag, int rn) {
        serverHelper.loadLDetailData(context, tag, rn);
    }

    @Override
    public void onDetailDataChanged(List<TestBean.DataBean> dataBeans) {
        view.detailDataChanged(dataBeans);
    }
}
