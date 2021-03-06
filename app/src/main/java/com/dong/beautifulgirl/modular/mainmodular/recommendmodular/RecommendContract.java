package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface RecommendContract {

    interface Presenter extends BasePresenter{

        void loadRecommendTab(Context context);

        void loadRecommend(Context context, String tag, int page);

    }

    interface View extends BaseView<Presenter>{

        void RecommendTabChanged(List<RecommendTabBean> tabBeans);

        void RecommendDataChanged(List<TestBean.DataBean> resultsBeans);
    }

}
