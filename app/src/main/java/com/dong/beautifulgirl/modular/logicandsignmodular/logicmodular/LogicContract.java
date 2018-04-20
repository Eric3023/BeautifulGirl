package com.dong.beautifulgirl.modular.logicandsignmodular.logicmodular;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface LogicContract {

    interface Presenter extends BasePresenter{

        void loadLogicData();

    }

    interface View extends BaseView<Presenter>{
        void logicDataChanged(LogicBean logicBean);
    }

}
