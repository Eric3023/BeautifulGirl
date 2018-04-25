package com.dong.beautifulgirl.modular.detailmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface DetailContract {

    interface Presenter extends BasePresenter{

        void loadDetailData(Context context, String tag, int rn);

    }

    interface View extends BaseView<Presenter>{
        void detailDataChanged(List<DetailBean.DataBean> dataBeans);
    }

}
