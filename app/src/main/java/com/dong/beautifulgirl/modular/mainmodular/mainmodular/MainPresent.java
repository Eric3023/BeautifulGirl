package com.dong.beautifulgirl.modular.mainmodular.mainmodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.modular.mainmodular.minemodular.MineLikeBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MainPresent implements MainContract.Presenter, MainServerHelper.OnMainSlideDataChangedListener{

    private MainContract.View view;
    private MainServerHelper serverHelper;

    public MainPresent(MainContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new MainServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadMainSlideData(context);
    }

    @Override
    public void onMainSlideDataChanged(MainSlideBean mineBean) {
        Log.i("Dong", "获取Mine数据："+mineBean.getName());
        view.mainSlideDataChanged(mineBean);
    }


    @Override
    public void loadMainSlideData(Context context) {
        serverHelper.loadMainSlideData(context);
    }
}
