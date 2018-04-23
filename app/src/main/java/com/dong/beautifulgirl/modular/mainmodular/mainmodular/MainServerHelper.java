package com.dong.beautifulgirl.modular.mainmodular.mainmodular;

import android.content.Context;

import com.dong.beautifulgirl.http.HeadModel;
import com.dong.beautifulgirl.http.UrlConfig;
import com.dong.beautifulgirl.modular.mainmodular.minemodular.MineLikeBean;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by donghuadong on 2018/4/10.
 */

public class MainServerHelper {

    private final String IMG_URL = "http://a.hiphotos.baidu.com/image/pic/item/902397dda144ad34e98003fedca20cf431ad8588.jpg";
    private final String NAME = "Eric";
    private final String UID = "827142667";

    private OnMainSlideDataChangedListener listener;

    public void loadMainSlideData(){

        MainSlideBean mineBean = new MainSlideBean();
        mineBean.setName(NAME);
        mineBean.setHeadImgUrl(IMG_URL);
        mineBean.setUid(UID);

        if(listener!=null)
            listener.onMainSlideDataChanged(mineBean);
    }

    public void setOnRecommendDataChangedListener(OnMainSlideDataChangedListener listener) {
        this.listener = listener;
    }


    public interface OnMainSlideDataChangedListener{
        void onMainSlideDataChanged(MainSlideBean mineBean);
    }

}
