package com.dong.beautifulgirl.modular.mainmodular.minemodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface MineContract {

    interface Presenter extends BasePresenter{

        void loadMineData(Context context);

        void loadMineLikeData(Context context);

    }

    interface View extends BaseView<Presenter>{
        void mineDataChanged(MineBean mineBean);

        void mineLikeDataChanged(List<TestBean.DataBean> dataBeans);

    }

}
