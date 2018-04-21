package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HomeContract {

    interface Presenter extends BasePresenter{
        void loadHomeData(Context context);

        void loadHomeHeadData(Context context);

        void loadHomeCardData(Context context);
    }

    interface View extends BaseView<Presenter>{

        void homeDataChanged(List<HomeBean.DataBean> list);

        void homeDataHeadChanged(List<HomeBean.DataBean> list);

        void homeDataCardChanged(List<HomeBean.DataBean> list);
    }
}
