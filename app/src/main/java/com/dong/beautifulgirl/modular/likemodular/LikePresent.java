package com.dong.beautifulgirl.modular.likemodular;

import android.content.Context;

import com.dong.beautifulgirl.modular.detailmodular.DetailContract;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LikePresent implements LikeContract.Presenter, LikeServerHelper.OnLikeDataChangedListener{

    private LikeContract.View view;
    private LikeServerHelper serverHelper;

    public LikePresent() {
        serverHelper = new LikeServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadLikeData(context, 0);
    }

    @Override
    public void viewCreated(LikeContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);
    }

    @Override
    public void viewDestroyed() {
        this.view =view;
    }

    @Override
    public void loadLikeData(Context context, int page) {
        serverHelper.loadLikeData(context, page);
    }

    @Override
    public void onLikeDataChanged(List<TestBean.DataBean> dataBeans) {
        view.likeDataChanged(dataBeans);
    }
}
