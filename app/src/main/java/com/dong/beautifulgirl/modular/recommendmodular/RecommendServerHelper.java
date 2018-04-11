package com.dong.beautifulgirl.modular.recommendmodular;

import com.dong.beautifulgirl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class RecommendServerHelper {

    public static final int[] IMG_IDS = {R.drawable.guide_img1,R.drawable.guide_img2,R.drawable.guide_img3,R.drawable.guide_img4};
    public static final String CONTENT = "这是详细说明---这是详细说明---";

    private OnRecommendDataChangedListener listener;

    public void loadRecommend(){

        List<RecommendBean> recommendBeans = new ArrayList<RecommendBean>();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 20; i++) {
            if(i%3 == 0) {
                stringBuilder = new StringBuilder();
            }
            stringBuilder.append(CONTENT);

            RecommendBean recommendBean = new RecommendBean();
            recommendBean.setImgId(IMG_IDS[i%IMG_IDS.length]);
            recommendBean.setContent(stringBuilder.toString());
            recommendBeans.add(recommendBean);
        }

        if(listener!=null)
            listener.onRecommendDataChanged(recommendBeans);
    }

    public void setOnRecommendDataChangedListener(OnRecommendDataChangedListener listener) {
        this.listener = listener;
    }

    public interface OnRecommendDataChangedListener{
        void onRecommendDataChanged(List<RecommendBean> recommendBeans);
    }

}