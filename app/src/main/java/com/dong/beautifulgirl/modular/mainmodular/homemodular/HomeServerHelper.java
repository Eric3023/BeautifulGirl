package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HomeServerHelper {

    private int pageNum = 0;
    private int resourceNum = 20;
    private final String TAG = "小清新";

//    public static final int[] IMG_IDS = {R.drawable.guide_img1, R.drawable.guide_img2, R.drawable.guide_img3, R.drawable.guide_img4};
//    public static final String TITLE = "这是标题内容";
//    public static final String CONTENT = "这是详细说明---这是详细说明---";

    public OnHomeDataChangedListener onHomeDataChangedListener;

    public void loadHomeData(Context context) {

        HeadModel.getHomeData(context)
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean != null) {
                            Log.i("Dong", "加载Home数据：" + homeBean.getData().size());
                            List<HomeBean.DataBean> results = homeBean.getData();
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

        HeadModel.getHomeHeadData(context)
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean != null) {
                            Log.i("Dong", "加载Home Head数据：" + homeBean.getData().size());
                            List<HomeBean.DataBean> results = homeBean.getData();

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

        HeadModel.getHomeCardData(context)
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        if (homeBean != null) {
                            Log.i("Dong", "加载Home 数据：" + homeBean.getData().size());
                            List<HomeBean.DataBean> results = homeBean.getData();

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

        void OnHomeDataChanged(List<HomeBean.DataBean> resultsBeans);

        void OnHomeDataHeadChanged(List<HomeBean.DataBean> resultsBeans);

        void OnHomeDataCardChanged(List<HomeBean.DataBean> resultsBeans);
    }

}
