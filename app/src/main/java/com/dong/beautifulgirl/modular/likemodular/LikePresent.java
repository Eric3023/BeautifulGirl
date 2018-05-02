package com.dong.beautifulgirl.modular.likemodular;

import android.content.Context;

import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LikePresent implements LikeContract.Presenter, LikeServerHelper.OnLikeDataChangedListener{

    private LikeContract.View view;
    private LikeServerHelper serverHelper;

    public LikePresent(LikeContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new LikeServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadLikeData(context);
    }

    @Override
    public void loadLikeData(Context context) {
        serverHelper.loadLikeData(context);
    }

    @Override
    public void onLikeDataChanged(List<TestBean.DataBean> dataBeans) {
        view.likeDataChanged(dataBeans);
    }
}
