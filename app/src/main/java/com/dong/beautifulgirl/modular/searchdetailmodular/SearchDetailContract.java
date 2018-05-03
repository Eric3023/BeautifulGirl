package com.dong.beautifulgirl.modular.searchdetailmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendTabBean;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface SearchDetailContract {

    interface Presenter extends BasePresenter{

        void loadSearchDetail(Context context, String tag);

    }

    interface View extends BaseView<Presenter>{

        void searchDetailDataChanged(List<SearchDetailBean.DataBean> dataBeans);
    }

}
