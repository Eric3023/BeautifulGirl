package com.dong.beautifulgirl.modular.homemodular;

import android.util.Log;

import com.dong.beautifulgirl.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by donghuadong on 2018/4/11.
 */

public class HomeServerHelper {

    public static final int[] IMG_IDS = {R.drawable.guide_img1,R.drawable.guide_img2,R.drawable.guide_img3,R.drawable.guide_img4};
    public static final String TITLE = "这是标题内容";
    public static final String CONTENT = "这是详细说明---这是详细说明---";

    public OnHomeDataChangedListener onHomeDataChangedListener;

    public void loadHomeData(){

        List<HomeBean> homeBeans = new ArrayList<HomeBean>();
        for (int i = 0; i < 50; i++) {
            HomeBean bean = new HomeBean();
            bean.setImgId(IMG_IDS[i%IMG_IDS.length]);
            bean.setTitle(TITLE);
            bean.setContent(CONTENT);

            homeBeans.add(bean);
        }

        Log.i("Dong", "加载Home数据："+homeBeans.size());
        if(onHomeDataChangedListener!=null)
            onHomeDataChangedListener.OnHomeDataChanged(homeBeans);
    }

    public void setOnHomeDataChangedListener(OnHomeDataChangedListener onHomeDataChangedListener) {
        this.onHomeDataChangedListener = onHomeDataChangedListener;
    }

    public interface OnHomeDataChangedListener{
        void OnHomeDataChanged(List<HomeBean> homeBeans);
    }

}
