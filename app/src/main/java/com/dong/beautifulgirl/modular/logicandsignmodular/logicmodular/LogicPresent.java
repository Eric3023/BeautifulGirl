package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

import android.content.Context;
import android.util.Log;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LogicPresent implements LogicContract.Presenter, LogicServerHelper.OnLogicDataChangedListener{

    private LogicContract.View view;
    private LogicServerHelper serverHelper;

    public LogicPresent() {
        serverHelper = new LogicServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
    }

    @Override
    public void viewCreated(LogicContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);
    }

    @Override
    public void viewDestroyed() {
        this.view =null;
    }

    @Override
    public void loadLogicData(Context context, String account, String password) {
        serverHelper.loadLogicData(context, account, password);
    }

    @Override
    public void onLogicDataChanged(LogicBean logicBean) {
        view.logicDataChanged(logicBean);
    }

}
