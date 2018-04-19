package com.dong.beautifulgirl.modular.mainmodular.recommendmodular;

import android.content.Context;

import com.dong.beautifulgirl.http.HeadModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class RecommendServerHelper {

//    public static final int[] IMG_IDS = {R.drawable.guide_img1,R.drawable.guide_img2,R.drawable.guide_img3,R.drawable.guide_img4};
//    public static final String CONTENT = "这是详细说明---这是详细说明---";

    private OnRecommendDataChangedListener listener;
    private List<RecommendBean.DataBean> resultsBeans;

    public void loadRecommend(Context context){

        resultsBeans = new ArrayList<RecommendBean.DataBean>();

        HeadModel.getRecommendData(context)
                .subscribe(new Observer<RecommendBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendBean recommendBean) {
                        if(recommendBean!=null){
                            List<RecommendBean.DataBean> results = recommendBean.getData();
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

//        List<RecommendBean> recommendBeans = new ArrayList<RecommendBean>();
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        for (int i = 0; i < 20; i++) {
//            if(i%3 == 0) {
//                stringBuilder = new StringBuilder();
//            }
//            stringBuilder.append(CONTENT);
//
//            RecommendBean recommendBean = new RecommendBean();
//            recommendBean.setImgId(IMG_IDS[i%IMG_IDS.length]);
//            recommendBean.setContent(stringBuilder.toString());
//            recommendBeans.add(recommendBean);
//        }
//
//        if(listener!=null)
//            listener.onRecommendDataChanged(recommendBeans);
    }

    public void setOnRecommendDataChangedListener(OnRecommendDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnRecommendDataChangedListener{
        void onRecommendDataChanged(List<RecommendBean.DataBean> resultsBeans);
    }

}
