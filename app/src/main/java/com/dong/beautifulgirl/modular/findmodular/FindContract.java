package com.dong.beautifulgirl.modular.findmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class FindContract {

    interface Presenter extends BasePresenter{
        void loadFindData(Context context);

        void loadFindHeadData(Context context);
    }

    interface View extends BaseView<Presenter>{

        void findDataChanged(List<FindBean.ResultsBean> list);

        void findDataHeadChanged(List<FindBean.ResultsBean> list);
    }
}
