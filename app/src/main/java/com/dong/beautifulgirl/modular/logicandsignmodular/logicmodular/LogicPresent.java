package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

import android.content.Context;
import android.util.Log;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LogicPresent implements LogicContract.Presenter, LogicServerHelper.OnLogicDataChangedListener{

    private LogicContract.View view;
    private LogicServerHelper serverHelper;

    public LogicPresent(LogicContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new LogicServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadLogicData();
    }

    @Override
    public void loadLogicData() {
        serverHelper.loadLogicData();
    }

    @Override
    public void onLogicDataChanged(LogicBean logicBean) {
        Log.i("Dong", "获取Logic数据："+logicBean.getName());
        view.logicDataChanged(logicBean);
    }

}
