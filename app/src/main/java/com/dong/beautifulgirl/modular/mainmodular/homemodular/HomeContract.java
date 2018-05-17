package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HomeContract {

    interface Presenter extends BasePresenter{
        void loadHomeData(Context context, int page);

        void loadHomeHeadData(Context context);

        void loadHomeCardData(Context context);
    }

    interface View extends BaseView<Presenter>{

        void homeDataChanged(List<TestBean.DataBean> list);

        void homeDataHeadChanged(List<TestBean.DataBean> list);

        void homeDataCardChanged(List<TestBean.DataBean> list);
    }
}
