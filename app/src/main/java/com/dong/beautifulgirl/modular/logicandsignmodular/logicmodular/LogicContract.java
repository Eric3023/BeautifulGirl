package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.modular.likemodular.LikeContract;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface LogicContract {

    interface Presenter extends BasePresenter{

        void viewCreated(LogicContract.View view);

        void viewDestroyed();

        void loadLogicData(Context context, String account, String password);

    }

    interface View extends BaseView<Presenter>{
        void logicDataChanged(LogicBean logicBean);
    }

}
