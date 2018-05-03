package com.dong.beautifulgirl.modular.searchmodular;

import android.content.Context;

import com.dong.beautifulgirl.test.TestBean;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class SearchPresent implements SearchContract.Presenter, SearchServerHelper.OnSearchDataChangedListener{

    private SearchContract.View view;
    private SearchServerHelper serverHelper;

    public SearchPresent(SearchContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new SearchServerHelper();
        serverHelper.setOnSearchDataChangedListener(this);
    }

    @Override
    public void start(Context context) {
        loadHotSearchData(context);
        loadHistorySearchData(context);
    }

    @Override
    public void loadHistorySearchData(Context context) {
        serverHelper.loadHistorySearchData(context);
    }

    @Override
    public void loadHotSearchData(Context context) {
        serverHelper.loadHotSearchData(context);
    }

    @Override
    public void addHistory(SearchBean searchBean) {
        serverHelper.addHistory(searchBean);
    }

    @Override
    public void clearHistory() {
        serverHelper.clearHistory();
    }

    @Override
    public void onHistorySearchDataChanged(List<SearchBean> searchBeans) {
        view.historyDataChanged(searchBeans);
    }

    @Override
    public void onHotSearchDataChanged(List<SearchBean> searchBeans) {
        view.hotDataChanged(searchBeans);
    }
}
