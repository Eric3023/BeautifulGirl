package com.dong.beautifulgirl.modular.searchmodular;

import android.content.Context;

import com.dong.beautifulgirl.base.BasePresenter;
import com.dong.beautifulgirl.base.BaseView;
import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public interface SearchContract {

    interface Presenter extends BasePresenter{

        void loadHistorySearchData(Context context);

        void loadHotSearchData(Context context);

        void addHistory(SearchBean searchBean);

        void clearHistory();

    }

    interface View extends BaseView<Presenter>{
        void historyDataChanged(List<SearchBean> searchBeans);
        void hotDataChanged(List<SearchBean> searchBeans);
    }

}
