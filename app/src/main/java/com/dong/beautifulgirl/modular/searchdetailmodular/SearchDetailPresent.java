package com.dong.beautifulgirl.modular.searchdetailmodular;

import android.content.Context;

import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class SearchDetailPresent implements SearchDetailContract.Presenter, SearchDetailServerHelper.OnSearchDetailDataChangedListener{

    private SearchDetailContract.View view;
    private SearchDetailServerHelper serverHelper;

    public SearchDetailPresent(SearchDetailContract.View view) {
        this.view = view;
        if(view!=null)
            view.setPresenter(this);

        serverHelper = new SearchDetailServerHelper();
        serverHelper.setOnSearchDetailDataChangedListener(this);
    }

    @Override
    public void start(Context context) {

    }

    @Override
    public void loadSearchDetail(Context context, String tag, int page) {
        serverHelper.loadSearchDetail(context, tag, page);
    }

    @Override
    public void onSearchDetailDataChanged(List<SearchDetailBean.DataBean> dataBeans) {
        view.searchDetailDataChanged(dataBeans);
    }
}
