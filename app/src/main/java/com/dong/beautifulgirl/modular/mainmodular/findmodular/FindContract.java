package com.dong.beautifulgirl.modular.mainmodular.findmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class FindContract {

    interface Presenter extends BasePresenter{
        void loadFindData(Context context, int page);

        void loadFindHeadData(Context context);
    }

    interface View extends BaseView<Presenter>{

        void findDataChanged(List<TestBean.DataBean> list);

        void findDataHeadChanged(List<TestBean.DataBean> list);
    }
}
