package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class LogicServerHelper {

    private OnLogicDataChangedListener listener;

    public void loadLogicData(Context context, String account, String password){


        SharedPreferences sharedPreferences = context.getSharedPreferences("accounts", Context.MODE_PRIVATE); //私有数据

        String passwordOnLine = sharedPreferences.getString(account, "");

        if(TextUtils.equals(password, passwordOnLine)&&listener!=null){

            SharedPreferences sp = context.getSharedPreferences("account", Context.MODE_PRIVATE); //私有数据
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("account", account);
            edit.putString("password", password);
            edit.commit();

            LogicBean logicBean = new LogicBean();
            logicBean.setMesseage(true);
            listener.onLogicDataChanged(logicBean);
        }else{
            LogicBean logicBean = new LogicBean();
            logicBean.setMesseage(false);
            listener.onLogicDataChanged(logicBean);
        }
    }

    public void setOnRecommendDataChangedListener(OnLogicDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnLogicDataChangedListener{
        void onLogicDataChanged(LogicBean logicBean);
    }

}
