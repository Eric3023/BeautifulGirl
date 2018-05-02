package com.dong.beautifulgirl.modular.passwordmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface PasswordContract {

    interface Presenter extends BasePresenter{

        void changePassword(Context context, String oldPassword, String newPassword, String newPasswordConfirm);

    }

    interface View extends BaseView<Presenter>{
        void passwordChanged(PasswordBean logicBean);
    }

}
