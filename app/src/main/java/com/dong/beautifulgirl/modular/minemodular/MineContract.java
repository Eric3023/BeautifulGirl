package com.dong.beautifulgirl.modular.minemodular;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface MineContract {

    interface Presenter extends BasePresenter{

        void loadRecommend();

    }

    interface View extends BaseView<Presenter>{
        void RecommendDataChanged(List<MineBean> recommendBeans);
    }

}
