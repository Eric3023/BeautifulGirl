package com.dong.beautifulgirl.modular.mainmodular.mainmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.modular.mainmodular.minemodular.MineLikeBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface MainContract {

    interface Presenter extends BasePresenter{

        void loadMainSlideData(Context context);
    }

    interface View extends BaseView<Presenter>{

        void mainSlideDataChanged(MainSlideBean mineBean);
    }

}
