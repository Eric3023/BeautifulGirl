package com.dong.beautifulgirl.modular.logicandsignmodular.signmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface SignContract {

    interface Presenter extends BasePresenter{

        void loadSignData(Context context, String accout, String password);

    }

    interface View extends BaseView<Presenter>{
        void signDataChanged(SignBean logicBean);
    }

}
