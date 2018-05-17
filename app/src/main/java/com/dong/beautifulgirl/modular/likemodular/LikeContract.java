package com.dong.beautifulgirl.modular.likemodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.modular.detailmodular.DetailContract;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface LikeContract {

    interface Presenter extends BasePresenter{

        void viewCreated(LikeContract.View view);

        void viewDestroyed();

        void loadLikeData(Context context, int page);

    }

    interface View extends BaseView<Presenter>{
        void likeDataChanged(List<TestBean.DataBean> dataBeans);
    }

}
