package com.dong.beautifulgirl.modular.mainmodular.mainmodular;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

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

    private OnMainSlideDataChangedListener listener;

    public void loadMainSlideData(Context context){

        SharedPreferences sharedPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE); //私有数据

        String acountKey =  "account";
        String passwordKey =  "password";

        MainSlideBean mineBean = new MainSlideBean();
        String acount = sharedPreferences.getString(acountKey, "");
        String password = sharedPreferences.getString(passwordKey, "");

        if(!TextUtils.isEmpty(acount)&&!TextUtils.isEmpty(password)){
            mineBean.setName(acount);
            mineBean.setHeadImgUrl(IMG_URL);
            mineBean.setUid(password);
        }

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
