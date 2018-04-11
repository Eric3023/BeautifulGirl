package com.dong.beautifulgirl.modular.homemodular;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HomeContract {

    interface Presenter extends BasePresenter{
        void loadHomeData();
    }

    interface View extends BaseView<Presenter>{

        void homeDataChanged(List<HomeBean> list);
    }
}
