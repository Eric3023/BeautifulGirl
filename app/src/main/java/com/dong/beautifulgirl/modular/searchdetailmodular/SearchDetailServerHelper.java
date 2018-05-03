package com.dong.beautifulgirl.modular.searchdetailmodular;

import android.content.Context;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.modular.mainmodular.recommendmodular.RecommendTabBean;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class SearchDetailServerHelper {

    private final String TN = "resultjson_com";
    private final String IPN = "rj";
    private int pn;//页码
    private final int rn = 30;//一页显示的数量

    private OnSearchDetailDataChangedListener listener;
    private List<SearchDetailBean.DataBean> resultsBeans;

    public void setOnSearchDetailDataChangedListener(OnSearchDetailDataChangedListener listener) {
        this.listener = listener;
    }

    public void loadSearchDetail(Context context, String tag) {

        resultsBeans = new ArrayList<SearchDetailBean.DataBean>();

        HeadModel.getSearchDetailData(context, TN ,IPN, tag, pn, rn, UrlConfig.IE)
                .subscribe(new Observer<SearchDetailBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchDetailBean searchDetailBean) {
                        if(searchDetailBean!=null){
                            List<SearchDetailBean.DataBean> results = searchDetailBean.getData();
                            if(results!=null){
                                if(results!=null&& results.size()>1)
                                    results.remove(results.size()-1);
                                resultsBeans.addAll(results);
                                if(listener!=null)
                                    listener.onSearchDetailDataChanged(resultsBeans);
                            }

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface OnSearchDetailDataChangedListener{

        void onSearchDetailDataChanged(List<SearchDetailBean.DataBean> dataBeans);
    }

}
