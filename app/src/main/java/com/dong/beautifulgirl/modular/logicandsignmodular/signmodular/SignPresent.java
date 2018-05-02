package com.dong.beautifulgirl.modular.logicandsignmodular.signmodular;

import android.content.Context;
import android.util.Log;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class SignPresent implements SignContract.Presenter, SignServerHelper.OnSignDataChangedListener{

    private SignContract.View view;
    private SignServerHelper serverHelper;

    public SignPresent(SignContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new SignServerHelper();
        serverHelper.setOnRecommendDataChangedListener(this);
    }

    @Override
    public void start(Context context) {

    }

    @Override
    public void loadSignData(Context context, String accout, String password) {
        serverHelper.loadSignData(context, accout, password);
    }

    @Override
    public void onSignDataChanged(SignBean signBean) {
        view.signDataChanged(signBean);
    }
}
