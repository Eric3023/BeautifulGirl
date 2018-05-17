package com.dong.beautifulgirl.modular.mainmodular.homemodular;

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
 * Created by donghuadong on 2018/4/11.
 */

public class HomeServerHelper {

    private int pn_head;//页码
    private final int rn_head = 5;//一页显示的数量

    private int pn_card;//页码
    private final int rn_card = 4;//一页显示的数量

    private int pn_con;//页码
    private final int rn_con = 30;//一页显示的数量

    public OnHomeDataChangedListener onHomeDataChangedListener;

    public void loadHomeData(Context context, int page) {

        HeadModel.getTestData(context, page, rn_con, UrlConfig.TAG_ROOT, UrlConfig.TAG_THIRD, UrlConfig.IE)
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean homeBean) {
                        if (homeBean != null) {
                            Log.i("Dong", "加载Home数据：" + homeBean.getData().size());
                            List<TestBean.DataBean> results = homeBean.getData();
                            if(results!=null&&results.size()>0)
                                results.remove(results.size()-1);
                            if (onHomeDataChangedListener != null)
                                onHomeDataChangedListener.OnHomeDataChanged(results);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Dong", "Home数据加载失败：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void loadHomeHeadData(Context context) {

        HeadModel.getTestData(context, pn_head, rn_head, UrlConfig.TAG_ROOT, UrlConfig.TAG_SEVEVTH, UrlConfig.IE)
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean homeBean) {
                        if (homeBean != null) {
                            Log.i("Dong", "加载Home Head数据：" + homeBean.getData().size());
                            List<TestBean.DataBean> results = homeBean.getData();

                            if(results!=null&&results.size()>0)
                                results.remove(results.size()-1);
                            if (onHomeDataChangedListener != null)
                                onHomeDataChangedListener.OnHomeDataHeadChanged(results);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Dong", "Home Head数据加载失败：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void loadHomeCardData(Context context) {

        HeadModel.getTestData(context, pn_card, rn_card, UrlConfig.TAG_ROOT, UrlConfig.TAG_SECOND, UrlConfig.IE)
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean homeBean) {
                        if (homeBean != null) {
                            Log.i("Dong", "加载Home Card数据：" + homeBean.getData().size());
                            List<TestBean.DataBean> results = homeBean.getData();

                            if(results!=null&&results.size()>0)
                                results.remove(results.size()-1);
                            if (onHomeDataChangedListener != null)
                                onHomeDataChangedListener.OnHomeDataCardChanged(results);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Dong", "Home Head数据加载失败：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void setOnHomeDataChangedListener(OnHomeDataChangedListener onHomeDataChangedListener) {
        this.onHomeDataChangedListener = onHomeDataChangedListener;
    }

    public interface OnHomeDataChangedListener {

        void OnHomeDataChanged(List<TestBean.DataBean> resultsBeans);

        void OnHomeDataHeadChanged(List<TestBean.DataBean> resultsBeans);

        void OnHomeDataCardChanged(List<TestBean.DataBean> resultsBeans);
    }

}
