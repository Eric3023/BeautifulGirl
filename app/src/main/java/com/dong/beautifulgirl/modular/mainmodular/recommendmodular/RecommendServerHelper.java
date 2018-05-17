package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.test.TestBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class RecommendServerHelper {

    private final int rn = 30;//一页显示的数量

    private OnRecommendDataChangedListener listener;
    private List<TestBean.DataBean> resultsBeans;

    public void loadRecommend(Context context, String tag, int page){

        resultsBeans = new ArrayList<TestBean.DataBean>();

        Log.i("Dong", "page:"+page);

        HeadModel.getTestData(context, page ,rn, UrlConfig.TAG_ROOT, tag, UrlConfig.IE)
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean recommendBean) {
                        if(recommendBean!=null){
                            List<TestBean.DataBean> results = recommendBean.getData();
                            if(results!=null){
                                if(results!=null&& results.size()>1)
                                    results.remove(results.size()-1);
                                resultsBeans.addAll(results);
                                if(listener!=null)
                                    listener.onRecommendDataChanged(resultsBeans);
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

    public void loadRecommendTab(Context context) {

        List<RecommendTabBean> tabBeans = new ArrayList<RecommendTabBean>();

        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_FIRST));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_SECOND));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_THIRD));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_FOURTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_FIFTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_SIXTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_SEVEVTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_EIGHTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_NINETH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_TENTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_ELEVENTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_TWELTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_THIRTEENTH));
        tabBeans.add(new RecommendTabBean(UrlConfig.TAG_FOURTEENTH));

        if(listener!=null)
            listener.onRecommendTabDataChanged(tabBeans);
    }

    public void setOnRecommendDataChangedListener(OnRecommendDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnRecommendDataChangedListener{

        void onRecommendTabDataChanged(List<RecommendTabBean> tabBeans);

        void onRecommendDataChanged(List<TestBean.DataBean> resultsBeans);
    }

}
