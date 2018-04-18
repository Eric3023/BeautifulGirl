package com.dong.beautifulgirl.modular.mainmodular.homemodular;

import android.content.Context;
import android.util.Log;

import com.dong.beautifulgirl.http.HeadModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HomeServerHelper {

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
                            Log.i("Dong", "加载Home数据：" + homeBean.getResults().size());
                            List<HomeBean.ResultsBean> results = homeBean.getResults();
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

//        for (int i = 0; i < 50; i++) {
//            HomeBean.ResultsBean bean = new HomeBean.ResultsBean();
//            bean.setCreatedAt(TITLE);
//            bean.setDesc(CONTENT);
//
//            resultsBeans.add(bean);
//        }
//        if (onHomeDataChangedListener != null)
//            onHomeDataChangedListener.OnHomeDataChanged(resultsBeans);
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
                            Log.i("Dong", "加载Home Head数据：" + homeBean.getResults().size());
                            List<HomeBean.ResultsBean> results = homeBean.getResults();
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

//        for (int i = 0; i < 50; i++) {
//            HomeBean.ResultsBean bean = new HomeBean.ResultsBean();
//            bean.setCreatedAt(TITLE);
//            bean.setDesc(CONTENT);
//
//            resultsBeans.add(bean);
//        }
//        if (onHomeDataChangedListener != null)
//            onHomeDataChangedListener.OnHomeDataChanged(resultsBeans);
    }


    public void setOnHomeDataChangedListener(OnHomeDataChangedListener onHomeDataChangedListener) {
        this.onHomeDataChangedListener = onHomeDataChangedListener;
    }

    public interface OnHomeDataChangedListener {

        void OnHomeDataChanged(List<HomeBean.ResultsBean> resultsBeans);

        void OnHomeDataHeadChanged(List<HomeBean.ResultsBean> resultsBeans);
    }

}
