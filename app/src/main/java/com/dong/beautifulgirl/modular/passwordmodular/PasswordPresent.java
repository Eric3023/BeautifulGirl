package com.dong.beautifulgirl.modular.passwordmodular;

import android.content.Context;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class PasswordPresent implements PasswordContract.Presenter, PasswordServerHelper.OnPasswordDataChangedListener{

    private PasswordContract.View view;
    private PasswordServerHelper serverHelper;

    public PasswordPresent(PasswordContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new PasswordServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {

    }

    @Override
    public void onPasswordChanged(PasswordBean signBean) {
        view.passwordChanged(signBean);
    }

    @Override
    public void changePassword(Context context, String oldPassword, String newPassword, String newPasswordConfirm) {
        serverHelper.changePassword(context, oldPassword, newPassword, newPasswordConfirm);
    }
}
