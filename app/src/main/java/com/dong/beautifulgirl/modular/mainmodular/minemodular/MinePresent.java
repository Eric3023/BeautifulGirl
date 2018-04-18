package com.dong.beautifulgirl.modular.mainmodular.minemodular;

import android.content.Context;
import android.util.Log;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MinePresent implements MineContract.Presenter, MineServerHelper.OnMineDataChangedListener{

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
    public void start(Context context) {
        loadMineData();
    }

    @Override
    public void loadMineData() {
        serverHelper.loadMineData();
    }

    @Override
    public void onMineDataChanged(MineBean mineBean) {
        Log.i("Dong", "获取Mine数据："+mineBean.getName());
        view.mineDataChanged(mineBean);
    }

}
