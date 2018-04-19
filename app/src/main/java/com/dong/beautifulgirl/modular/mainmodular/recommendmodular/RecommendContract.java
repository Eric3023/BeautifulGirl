package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface RecommendContract {

    interface Presenter extends BasePresenter{

        void loadRecommend(Context context);

    }

    interface View extends BaseView<Presenter>{
        void RecommendDataChanged(List<RecommendBean.DataBean> resultsBeans);
    }

}
